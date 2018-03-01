package by.asrohau.shop.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.asrohau.shop.bean.User;
import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.dao.AbstractDAO;
import by.asrohau.shop.dao.UserDAO;
import by.asrohau.shop.dao.exception.DAOException;

public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

	private String FIND_USER_WITH_LOGIN_PASSWORD_QUERY = "SELECT * FROM shop.users WHERE login = ? AND password = ?";
	private String FIND_USER_WITH_LOGIN_QUERY = "SELECT * FROM shop.users WHERE login = ?";
	private String SAVE_USER_QUERY = "INSERT INTO shop.users (login, password) VALUES (?,?)";
	private String CHANGE_PASSWORD_QUERY = "UPDATE shop.users SET password = ? WHERE login = ? AND password = ?";
	private String DELETE_USER_QUERY = "DELETE FROM shop.users WHERE login = ? AND password = ?";

	@Override
	public UserDTO findUserWithLoginAndPassword(String login, String password) throws DAOException {
		try (PreparedStatement preparedStatement = getConnection()
				.prepareStatement(FIND_USER_WITH_LOGIN_PASSWORD_QUERY)) {
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			UserDTO userDTO = new UserDTO();

			while (resultSet.next()) {
				userDTO.setLogin(resultSet.getString(2));
			}
			preparedStatement.close();
			connection.close();

			if (userDTO.getLogin() != null) {
				return userDTO;
			}
			System.out.println("Did not find User with login = " + login);
			return null;
		} catch (SQLException e) {
			throw new DAOException(e);
		}

	}

	@Override
	public UserDTO findUserWithLogin(String login) throws DAOException {
		try (PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_USER_WITH_LOGIN_QUERY)) {
			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();
			UserDTO userDTO = new UserDTO();

			while (resultSet.next()) {
				userDTO.setLogin(resultSet.getString(2));
			}
			preparedStatement.close();
			connection.close();

			if (userDTO.getLogin() != null) {
				return userDTO;
			}
			System.out.println("Did not find User with login = " + login);
			return null;
		} catch (SQLException e) {
			throw new DAOException(e);
		}

	}

	@Override
	public boolean saveUser(String login, String password) throws DAOException {
		try (PreparedStatement statement = getConnection().prepareStatement(SAVE_USER_QUERY)) {
			statement.setString(1, login);
			statement.setString(2, password);

			statement.executeUpdate();
			statement.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean changePassword(String login, String password, String newPassword) throws DAOException {
		try (PreparedStatement statement = getConnection().prepareStatement(CHANGE_PASSWORD_QUERY)) {
			statement.setString(1, newPassword);
			statement.setString(2, login);
			statement.setString(3, password);

			statement.executeUpdate();
			statement.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean deleteUser(String login, String password) throws DAOException {
		try (PreparedStatement statement = getConnection().prepareStatement(DELETE_USER_QUERY)) {
			statement.setString(1, login);
			statement.setString(2, password);

			statement.executeUpdate();
			statement.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
