package com.ccgg.SpringBootRESTDemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccgg.SpringBootRESTDemo.beans.User;

public interface UserDao extends JpaRepository<User, Integer> {
	User findByUsername(String username);
}
