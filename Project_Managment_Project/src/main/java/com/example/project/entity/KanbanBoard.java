package com.example.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity

@Table

public class KanbanBoard {
	
	
@Id

	private Long Card_IDs;
	
	

	public KanbanBoard() {
	super();
}



	public KanbanBoard(Long card_IDs, TaskCards taskCards, CardStatus status) {
	super();
	Card_IDs = card_IDs;
	this.taskCards = taskCards;
	this.status = status;
}
	
	

	public Long getCard_IDs() {
		return Card_IDs;
	}


	public void setCard_IDs(Long card_IDs) {
		Card_IDs = card_IDs;
	}


	public TaskCards getTaskCards() {
		return taskCards;
	}


	public void setTaskCards(TaskCards taskCards) {
		this.taskCards = taskCards;
	}


	public CardStatus getStatus() {
		return status;
	}


	public void setStatus(CardStatus status) {
		this.status = status;
	}



	@OneToOne(targetEntity = TaskCards.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "Card_ID" , referencedColumnName = "card_id")
	@MapsId	
	@JsonIgnore
	private TaskCards taskCards;

	
	@Enumerated(EnumType.STRING)
	private CardStatus status;
	
	
}
