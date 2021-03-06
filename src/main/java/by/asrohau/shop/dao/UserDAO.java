package by.asrohau.shop.dao;

import by.asrohau.shop.bean.User;
import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.dao.exception.DAOException;

import java.util.ArrayList;

public interface UserDAO {
	
	UserDTO findUserWithLoginAndPassword(User user) throws DAOException;
	UserDTO findUserWithLogin(User user) throws DAOException;
	boolean saveUser(User user) throws DAOException;
	boolean changePassword(User user) throws DAOException;
	boolean deleteUser(User user) throws DAOException;
	User findUserWithId(User user) throws DAOException;
	ArrayList<User> selectAllUsers(int row) throws DAOException;
	boolean updateUser(User user) throws DAOException;
	int countProducts() throws DAOException;
	
}
