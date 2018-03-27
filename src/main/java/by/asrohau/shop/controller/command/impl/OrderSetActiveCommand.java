package by.asrohau.shop.controller.command.impl;

import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;
import by.asrohau.shop.service.OrderService;
import by.asrohau.shop.service.ServiceFactory;
import by.asrohau.shop.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderSetActiveCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to SetOrderActiveCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();

        try {
            String message;
            if (orderService.orderSetActive(Integer.parseInt(request.getParameter("orderId")))) {
                message = "You have Setted new ORDER as ACTIVE successfully";
                System.out.println(message);
            } else {
                message = "Can NOT SET this order as ACTIVE, try again!";
                System.out.println(message);
            }

            if(request.getParameter("from").matches("manageOrders")){
                response.sendRedirect(String.valueOf(request.getSession(true).getAttribute("lastCMD"))
                        + "&msg=" + message);
            }else{
                request.getSession().setAttribute("lastCMD",
                        "FrontController?command=goToPage&address=manageOrders.jsp");
                request.getRequestDispatcher("/jsp/admin/manageOrders.jsp").forward(request, response);
            }

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}