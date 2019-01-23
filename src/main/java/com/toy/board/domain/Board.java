package com.toy.board.domain;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="Board")
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "writer") private String writer;
	@Column(name = "title") private String title;
	@Column(name = "text", columnDefinition="TEXT") private String text;
	@Column(name = "created_on") private ZonedDateTime createdOn;
	@Column(name = "updated_on") private ZonedDateTime updatedOn;
	@Column(name = "hit") private int hit;
	
	public Board() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ZonedDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(ZonedDateTime createdOn) {
		this.createdOn = createdOn;
	}
	
	public ZonedDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(ZonedDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	@PrePersist
	void createdOn() {
		this.createdOn = this.updatedOn = ZonedDateTime.now();
	}
	
	@PreUpdate
	void updatedOn() {
		this.updatedOn = ZonedDateTime.now();
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

}
