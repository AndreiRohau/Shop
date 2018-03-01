package by.asrohau.shop.service.impl;

import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.dao.DAOFactory;
import by.asrohau.shop.dao.UserDAO;
import by.asrohau.shop.dao.exception.DAOException;
import by.asrohau.shop.service.UserService;
import by.asrohau.shop.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

	private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

	public UserServiceImpl() {

	}

	@Override
	public boolean validation(String login, String password) {
		String toCompare = "";
		if (!toCompare.equals(login) && !toCompare.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public UserDTO logination(String login, String password) throws ServiceException {

		// validation!!! stub
		if (validation(login, password)) {

			try {
				return userDAO.findUserWithLoginAndPassword(login, password);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}

		}

		return null;
	}

	@Override
	public boolean registration(String login, String password) throws ServiceException {

		// validation!!! stub
		if (validation(login, password)) {

			try {
				if (userDAO.findUserWithLogin(login) == null) {
					return userDAO.saveUser(login, password);
				}

			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}

		return false;
	}

	@Override
	public boolean changePassword(String login, String password, String newPassword) throws ServiceException {

		// validation!!! stub
		if (validation(login, newPassword)) {

			try {
				if (userDAO.findUserWithLoginAndPassword(login, password) != null) {
					return userDAO.changePassword(login, password, newPassword);
				}
				
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}

		return false;
	}

	@Override
	public boolean deleteUser(String login, String password) throws ServiceException {

		// validation!!! stub
		if (validation(login, password)) {

			try {
				if (userDAO.findUserWithLoginAndPassword(login, password) != null) {
					return userDAO.deleteUser(login, password);
				}
				
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}

		return false;
	}

}
