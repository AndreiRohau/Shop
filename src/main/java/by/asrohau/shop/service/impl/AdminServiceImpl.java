package by.asrohau.shop.service.impl;

import by.asrohau.shop.bean.User;
import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.dao.AdminDAO;
import by.asrohau.shop.dao.DAOFactory;
import by.asrohau.shop.dao.UserDAO;
import by.asrohau.shop.dao.exception.DAOException;
import by.asrohau.shop.service.AdminService;
import by.asrohau.shop.service.exception.ServiceException;

public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();

    public AdminServiceImpl() {
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
                return adminDAO.findUserWithLoginAndPassword(user);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

}
