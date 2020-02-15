package com.ccgg.SpringBootRESTDemo.beans;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="message")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ")
	@SequenceGenerator(name = "SEQ", sequenceName = "CCGG_USER_SEQ")
	private int messageId;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	@JsonIgnore
	private User user;
	
	@Column
	@DateTimeFormat(pattern = "mm-dd-yyyy")
	private Date messageDate;
	
	@Column
	private String comments;
	
	
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public Date getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	public Message() {
		super();
	}
	
	public Message(String comments,Date date,User user) {
		super();
		this.comments = comments;
		this.messageDate=date;
		this.user=user;
	}
	
	
	
	
}

