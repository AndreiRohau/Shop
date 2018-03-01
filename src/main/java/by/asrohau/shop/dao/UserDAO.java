package by.asrohau.shop.dao;

import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.dao.exception.DAOException;

public interface UserDAO {
	
	UserDTO findUserWithLoginAndPassword(String login, String password) throws DAOException;
	
	UserDTO findUserWithLogin(String login) throws DAOException;
	
	boolean saveUser(String login, String password) throws DAOException;
	
	boolean changePassword(String login, String password, String newPassword) throws DAOException;
	
	boolean deleteUser(String login, String password) throws DAOException;
	
	
}
