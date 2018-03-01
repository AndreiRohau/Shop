package by.asrohau.shop.dao.impl;

import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.dao.AdminDAO;
import by.asrohau.shop.dao.exception.DAOException;

/**
 * Created by rohau.andrei on 02-Mar-18.
 */
public class AdminDAOImpl implements AdminDAO {
    @Override
    public UserDTO findUserWithLoginAndPassword(String login, String password) throws DAOException {
        return null;
    }
}
