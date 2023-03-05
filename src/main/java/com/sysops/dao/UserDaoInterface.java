package com.sysops.dao;

import java.util.List;

import com.sysops.entity.User;

public interface UserDaoInterface {

	void registerUser(User user);

	boolean findByUsername(String username);

	List<User> getAllUsers();

}