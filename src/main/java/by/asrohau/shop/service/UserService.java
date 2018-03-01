package by.asrohau.shop.service;

import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.service.exception.ServiceException;

public interface UserService {
	
	boolean validation(String login, String password);
	UserDTO logination(String login, String password) throws ServiceException;
	boolean registration(String login, String password) throws ServiceException;
	boolean changePassword(String login, String password, String newPassword) throws ServiceException;
	boolean deleteUser(String login, String password) throws ServiceException;

}
