package by.asrohau.shop.service;

import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.service.exception.ServiceException;

/**
 * Created by rohau.andrei on 02-Mar-18.
 */
public interface AdminService {
    boolean validation(String login, String password);
    UserDTO logination(String login, String password) throws ServiceException;


}
