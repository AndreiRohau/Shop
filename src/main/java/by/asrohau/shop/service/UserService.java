package by.asrohau.shop.service;

import by.asrohau.shop.bean.User;
import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.service.exception.ServiceException;

import java.util.ArrayList;

public interface UserService {
	
	boolean validation(User user);
	UserDTO logination(User user) throws ServiceException;
	boolean registration(User user) throws ServiceException;
	boolean changePassword(User user) throws ServiceException;
	boolean deleteUser(User user) throws ServiceException;
	ArrayList<User> getAllUsers() throws ServiceException;
	User findUserWithId(User user) throws ServiceException;
	boolean updateUser(User user) throws ServiceException;
}
