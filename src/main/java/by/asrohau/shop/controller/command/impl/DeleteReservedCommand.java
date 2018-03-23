package by.asrohau.shop.controller.command.impl;

import by.asrohau.shop.bean.Order;
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

public class DeleteReservedCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to DeleteReservedCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();

        User user = new User();

        try {
            String message;
            if (orderService.deleteReserved(Integer.parseInt(request.getParameter("reserveId")))) {
                message = "You have REMOVED product successfully";
                System.out.println(message);
            } else {
                message = "Can NOT remove this product, try again!";
                System.out.println(message);
            }

            response.sendRedirect(String.valueOf(request.getSession(true).getAttribute("lastCMD"))
                    + "&msg=" + message);

        } catch (ServiceException | IOException e) {
            throw new ControllerException(e);
        }

    }
}
