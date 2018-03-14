package by.asrohau.shop.dao.impl;

import by.asrohau.shop.bean.User;
import by.asrohau.shop.service.AdminService;
import by.asrohau.shop.service.ServiceFactory;
import by.asrohau.shop.service.exception.ServiceException;
import org.junit.Test;

public class AdminDAOImplTest {

    @Test
    public void updateClientTest(){

        //created in data base user with id=42, login=test, password=test
        User user = new User(42, "testupd", "testupd");

        ServiceFactory sf = ServiceFactory.getInstance();
        AdminService as = sf.getAdminService();

        try {
            as.updateUser(user);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}

