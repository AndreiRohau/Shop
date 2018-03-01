package by.asrohau.shop.dao;

import by.asrohau.shop.dao.impl.AdminDAOImpl;
import by.asrohau.shop.dao.impl.ProductDAOImpl;
import by.asrohau.shop.dao.impl.UserDAOImpl;

public class DAOFactory {
	
	private static final DAOFactory INSTANCE = new DAOFactory();
	
	private final UserDAO userDAO = new UserDAOImpl();
	private final AdminDAO adminDAO = new AdminDAOImpl();
	private final ProductDAO productDAO = new ProductDAOImpl();
	
	private DAOFactory() {}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public AdminDAO getAdminDAO() {
		return adminDAO;
	}

	public static DAOFactory getInstance(){
		return INSTANCE;
	}

}
