package com.sysops.service;

import com.sysops.entity.User;

public interface UserServiceInterface {

	boolean findByUsername(String username);

	void registerUser(User user);

	boolean validateUser(User user);

	boolean validateConfirmPassword(User user);

}