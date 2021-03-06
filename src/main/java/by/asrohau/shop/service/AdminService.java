package by.asrohau.shop.service;

import by.asrohau.shop.bean.User;
import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.service.exception.ServiceException;

public interface AdminService {
    boolean validation(User user);
    UserDTO logination(User user) throws ServiceException;
}
