package by.asrohau.shop.service.impl;

import by.asrohau.shop.bean.User;
import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.dao.AdminDAO;
import by.asrohau.shop.dao.DAOFactory;
import by.asrohau.shop.dao.exception.DAOException;
import by.asrohau.shop.service.AdminService;
import by.asrohau.shop.service.exception.ServiceException;

import java.util.ArrayList;

public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();

    public AdminServiceImpl() {
    }

    @Override
    public boolean validation(User user) {
        String toCompare = "";
        if (!toCompare.equals(user.getLogin()) && !toCompare.equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserDTO logination(User user) throws ServiceException {
        // validation!!! stub
        if (validation(user)) {
            try {
                return adminDAO.findUserWithLoginAndPassword(user);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public ArrayList<User> getAllUsers() throws ServiceException {
        try {

            ArrayList<User> userArrayList = adminDAO.selectAllUsers();
            for(User userx : userArrayList){
                System.out.println("in sevice");
                System.out.println(userx.toString());
            }
            return userArrayList;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
