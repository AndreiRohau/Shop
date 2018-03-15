package by.asrohau.shop.dao;

import by.asrohau.shop.bean.User;
import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.dao.exception.DAOException;

import java.util.ArrayList;

public interface AdminDAO {

    UserDTO findUserWithLoginAndPassword(User user) throws DAOException;

}
