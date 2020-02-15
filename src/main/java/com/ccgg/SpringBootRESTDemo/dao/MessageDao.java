package com.ccgg.SpringBootRESTDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccgg.SpringBootRESTDemo.beans.Message;

public interface MessageDao extends JpaRepository<Message, Integer>{
	Message findByMessageId(int id);
}
