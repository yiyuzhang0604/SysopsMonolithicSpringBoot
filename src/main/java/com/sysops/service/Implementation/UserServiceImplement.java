package com.sysops.service.Implementation;

import java.util.List;

import com.sysops.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sysops.dao.UserDaoInterface;
import com.sysops.entity.User;

@Service
public class UserServiceImplement implements UserServiceInterface {

	@Autowired
	private UserDaoInterface userDao;

	@Override
	public boolean findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	@Override
	public void registerUser(User user) {
		userDao.registerUser(user);
	}

	@Override
	public boolean validateUser(User user) {
		List<User> userList = userDao.getAllUsers();
		boolean flag=false;
		for (User userData : userList) {
			if (userData.getUsername().equals(user.getUsername()) && userData.getPassword().equals(user.getPassword())) {
				flag = true;
			}
		}
		System.out.println(flag);
		return flag;
	}

	@Override
	public boolean validateConfirmPassword(User user) {
		if (user.getPassword().equals(user.getConfirmPassword())) {
			return true;
		}
		return false;
	}
}
