package by.asrohau.shop.service.impl;

import by.asrohau.shop.bean.User;
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
	public UserDTO logination(User user) throws ServiceException {

		// validation!!! stub
		if (validation(user.getLogin(), user.getPassword())) {

			try {
				return userDAO.findUserWithLoginAndPassword(user);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}

		}

		return null;
	}

	@Override
	public boolean registration(User user) throws ServiceException {

		// validation!!! stub
		if (validation(user.getLogin(), user.getPassword())) {

			try {
				if (userDAO.findUserWithLogin(user) == null) {
					return userDAO.saveUser(user);
				}

			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}

		return false;
	}

	@Override
	public boolean changePassword(User user) throws ServiceException {

		// validation!!! stub
		if (validation(user.getLogin(), user.getPassword())) {

			try {
				if (userDAO.findUserWithLoginAndPassword(user) != null) {
					return userDAO.changePassword(user);
				}
				
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}

		return false;
	}

	@Override
	public boolean deleteUser(User user) throws ServiceException {

		// validation!!! stub
		if (validation(user.getLogin(), user.getPassword())) {

			try {
				if (userDAO.findUserWithLoginAndPassword(user) != null) {
					return userDAO.deleteUser(user);
				}
				
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}

		return false;
	}

}
