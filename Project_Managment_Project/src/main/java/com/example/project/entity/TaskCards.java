package com.example.project.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table

public class TaskCards {

@Id
	
	@SequenceGenerator(
			name = "Tasks_Cards_sequence" , 
			sequenceName = "Tasks_Cards_sequence_name" , 
			allocationSize = 1 
			)
	@GeneratedValue(
			
			strategy = GenerationType.SEQUENCE,
			generator = "Tasks_Cards_sequence"
			
			)

	private Long CardId;
	
	private String CardTitle;
	
	private String CardDescription;
	
	private Date DueDate;
	
	
	public TaskCards() {
		super();
	}
	
	
	
	
	
	public TaskCards(Long cardId, String cardTitle, String cardDescription, Date dueDate, Tasks taskForCards,
			KanbanBoard kanbanBoard) {
		super();
		CardId = cardId;
		CardTitle = cardTitle;
		CardDescription = cardDescription;
		DueDate = dueDate;
		TaskForCards = taskForCards;
		this.kanbanBoard = kanbanBoard;
	}



	public Long getCardId() {
		return CardId;
	}





	public void setCardId(Long cardId) {
		CardId = cardId;
	}





	public String getCardTitle() {
		return CardTitle;
	}





	public void setCardTitle(String cardTitle) {
		CardTitle = cardTitle;
	}





	public String getCardDescription() {
		return CardDescription;
	}





	public void setCardDescription(String cardDescription) {
		CardDescription = cardDescription;
	}





	public Date getDueDate() {
		return DueDate;
	}





	public void setDueDate(Date dueDate) {
		DueDate = dueDate;
	}





	public Tasks getTaskForCards() {
		return TaskForCards;
	}





	public void setTaskForCards(Tasks taskForCards) {
		TaskForCards = taskForCards;
	}





	public KanbanBoard getKanbanBoard() {
		return kanbanBoard;
	}





	public void setKanbanBoard(KanbanBoard kanbanBoard) {
		this.kanbanBoard = kanbanBoard;
	}





	@OneToOne(targetEntity = Tasks.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "TaskId" , referencedColumnName = "TaskId")
	private Tasks TaskForCards;
	
	@OneToOne(mappedBy = "taskCards" , targetEntity = KanbanBoard.class, cascade = CascadeType.ALL)
	private KanbanBoard kanbanBoard;
	
	
	
}
