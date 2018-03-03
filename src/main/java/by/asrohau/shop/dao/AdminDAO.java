package by.asrohau.shop.dao;

import by.asrohau.shop.bean.User;
import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.dao.exception.DAOException;

/**
 * Created by rohau.andrei on 02-Mar-18.
 */
public interface AdminDAO {

    UserDTO findUserWithLoginAndPassword(User user) throws DAOException;

}
