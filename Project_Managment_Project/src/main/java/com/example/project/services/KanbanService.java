package com.example.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.project.entity.CardStatus;
import com.example.project.entity.KanbanBoard;
import com.example.project.entity.TaskCards;
import com.example.project.repository.KanbanRepository;
import com.example.project.repository.TaskCardRepository;

import jakarta.transaction.Transactional;

@Service
public class KanbanService {

    private final KanbanRepository kanbanRepo;
    private final TaskCardRepository taskCardRepo;

    @Autowired
    public KanbanService(KanbanRepository kanbanRepo, TaskCardRepository taskCardRepo) {
        this.kanbanRepo = kanbanRepo;
        this.taskCardRepo = taskCardRepo;
    }

    public KanbanBoard GetTaskCardsStatus(Long card_id) {
        TaskCards taskCard = taskCardRepo.findById(card_id)
                .orElseThrow(() -> new IllegalStateException("TaskCard with id " + card_id + " does not exist"));

        return taskCard.getKanbanBoard();
    }

    
    @Transactional
    public void RegisterTaskCard(TaskCards cards) {
        KanbanBoard kanban = new KanbanBoard(null, cards, null);
        kanban.setCard_IDs(cards.getCardId());
        kanban.setStatus(CardStatus.TO_DO);
        kanban.setTaskCards(cards);

        kanbanRepo.save(kanban);
    }
}
