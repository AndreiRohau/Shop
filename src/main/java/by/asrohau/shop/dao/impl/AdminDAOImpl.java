package by.asrohau.shop.dao.impl;

import by.asrohau.shop.bean.User;
import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.dao.AbstractDAO;
import by.asrohau.shop.dao.AdminDAO;
import by.asrohau.shop.dao.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl extends AbstractDAO<UserDTO> implements AdminDAO {

    private String FIND_USER_WITH_LOGIN_PASSWORD_QUERY = "SELECT * FROM shop.admins WHERE login = ? AND password = ?";

    @Override
    public UserDTO findUserWithLoginAndPassword(User user) throws DAOException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_USER_WITH_LOGIN_PASSWORD_QUERY)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
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
            System.out.println("Did not find User with login = " + user.getLogin());
            return null;
        } catch (SQLException e) {
            throw new DAOException(e);
        }

    }
}
