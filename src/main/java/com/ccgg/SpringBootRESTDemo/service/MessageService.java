package com.ccgg.SpringBootRESTDemo.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ccgg.SpringBootRESTDemo.beans.Message;
import com.ccgg.SpringBootRESTDemo.beans.User;
import com.ccgg.SpringBootRESTDemo.beans.UserProfile;
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

	//add
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
	public Response editMessage(Message message) {
		
		try {
			//only change comments
			Message m=messageDao.findByMessageId(message.getMessageId());
			m.setComments(message.getComments());
			m.setMessageDate(m.getMessageDate());
			messageDao.save(m);
			return new Response(true);
		}catch(Exception e) {
			System.out.println("Wrong content!");
			return new Response(false);
		}
	}
	
	//delete by messageId
	public Response deleteMessage(int messageId) {
		
		if(messageDao.findOne(messageId)!=null) {
			messageDao.delete(messageId);
			return new Response(true);
		}else {
			return new Response(false, "User is not found!");
		}
	}
	
	//get all info of messages
	/*
	public Response getMessage() {
		
		Message m=messageDao.findByMessageId(messageId);
		User u=m.getUser();
		return u.getUsername();
		
		List<Message>m=messageDao.findAll();
	}
	*/
	
	
	public List<String> getMessage(int id){
		List<String>result=new ArrayList<String>();
		Message m=messageDao.findByMessageId(id);
		User u=m.getUser();
		result.add("User name: "+u.getUsername());
		return result;
		
		//get user name and comments by joining two tables
		String sqlQuery="SELECT m.comments,u.username FROM message m LEFT JOIN ccgg_user u ON m.user_id=u.id";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(sqlQuery);
		
		
		/*
		List<Message>resultList=messageDao.findAll();
		//messageId->user->userId
		Message m=messageDao.findByMessageId(id);
		User u=m.getUser();
		int userId=u.getId();
		
		return u.getUsername();
		*/
		/*
		List<Message> groupList;
		 Session session = sessionFactory.getCurrentSession();
		    Query query = session.createQuery("select c from Category c join fetch c.events where c.parentCategory.categoryId = 1");
		    //query.setParameter("id", id);
		    groupList = query.list();
		    return groupList;
		 */
		/*
		List<Message> l = em.createQuery(
		        "SELECT e, d  FROM Professor e LEFT JOIN e.department d")
		        .getResultList();
		*/
		/*
		EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery("SELECT DISTINCT e FROM Employee e LEFT OUTER JOIN e.tasks t");
        List<Employee> resultList = query.getResultList();
        resultList.forEach(System.out::println);
        em.close();
        
        
        List<Person> persons = entityManager.createQuery(
        	    "select distinct pr " +
        	    "from Person pr " +
        	    "join pr.phones ph " +
        	    "where ph.type = :phoneType", Person.class )
        	.setParameter( "phoneType", PhoneType.MOBILE )
        	.getResultList();
        */
	}

}
