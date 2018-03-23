package by.asrohau.shop.controller.command.impl;

import by.asrohau.shop.bean.Reserve;
import by.asrohau.shop.bean.User;
import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;
import by.asrohau.shop.service.OrderService;
import by.asrohau.shop.service.ServiceFactory;
import by.asrohau.shop.service.UserService;
import by.asrohau.shop.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddToBasketCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to AddToBasketCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        UserService userService = serviceFactory.getUserService();

        Reserve reserve;
        User user = new User();

        try {
            user.setLogin((String) request.getSession().getAttribute("userName"));
            reserve = new Reserve(userService.findIdWithLogin(user).getId(),
                    Integer.parseInt(request.getParameter("productId")));
            String message;
            if (orderService.saveReserve(reserve)) {
                message = "You have added new product successfully";
            } else {
                message = "Can NOT add this product, try again!";
            }
            response.sendRedirect(String.valueOf(request.getSession(true).getAttribute("lastCMD"))
                    + "&msg=" + message);

        } catch (ServiceException | IOException e) {
            throw new ControllerException(e);
        }

    }
}
