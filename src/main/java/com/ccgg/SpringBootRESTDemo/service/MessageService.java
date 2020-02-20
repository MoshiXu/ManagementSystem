package com.ccgg.SpringBootRESTDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ccgg.SpringBootRESTDemo.beans.Message;
import com.ccgg.SpringBootRESTDemo.beans.User;
import com.ccgg.SpringBootRESTDemo.dao.MessageDao;
import com.ccgg.SpringBootRESTDemo.dao.UserDao;
import com.ccgg.SpringBootRESTDemo.http.Response;

@Service
@Transactional
public class MessageService {
	@Autowired
	MessageDao messageDao;
	
	@Autowired
	UserDao userDao;

	//add message
	public Response addMessage(Message message,Authentication authentication) {
		try {
			//get user name from session
			String username=authentication.getName();
			message.setUser(userDao.findByUsername(username));
			messageDao.save(message);	
			return new Response(true,201,"Message Successful added.");
		}catch(Exception e){
			System.out.println(e);
			return new Response(false);
		}
	}
		
	//get user name by messageId
	public String getUsername(int messageId) {
		//messageId->userId->userName
		Message m=messageDao.findByMessageId(messageId);
		User u=m.getUser();
		return u.getUsername();
	}
	
	//edit the context of message by messageId
	public Response updateMessage(Message message) {
		try {
			Message m=messageDao.findByMessageId(message.getMessageId());
			m.setMessageId(message.getMessageId());
			m.setComments(message.getComments());
			m.setMessageDate(message.getMessageDate());
			messageDao.save(m);
			return new Response(true);
		}catch(Exception e) {
			System.out.println(e);
			return new Response(false);
		}
	}
	
	//delete by messageId
	public Response deleteMessage(int messageId) {
		if(messageDao.findOne(messageId)!=null) {
			messageDao.delete(messageId);
			return new Response(true, 222 , "Deleted successfully");
		}else {
			return new Response(false, "Message is not found!");
		}
	}
	
}
