package by.asrohau.shop.dao;

import by.asrohau.shop.dao.impl.BookDAOImpl;
import by.asrohau.shop.dao.impl.UserDAOImpl;

public class DAOFactory {
	
	private static final DAOFactory INSTANCE = new DAOFactory();
	
	private final UserDAO userDAO = new UserDAOImpl();
	private final BookDAO bookDAO = new BookDAOImpl();
	
	private DAOFactory() {}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public BookDAO getBookDAO() {
		return bookDAO;
	}
	
	public static DAOFactory getInstance(){
		return INSTANCE;
	}

}
