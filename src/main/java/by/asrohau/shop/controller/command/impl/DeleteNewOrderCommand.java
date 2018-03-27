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

public class DeleteNewOrderCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to DeleteNewOrderCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();

        try {
            String message;
            if (orderService.deleteNewOrder(Integer.parseInt(request.getParameter("orderId")))) {
                message = "You have REMOVED new ORDER successfully";
                System.out.println(message);
            } else {
                message = "Can NOT remove this order, try again!";
                System.out.println(message);
            }

            if(request.getParameter("from").matches("manageOrders")) {
                response.sendRedirect(String.valueOf(request.getSession(true).getAttribute("lastCMD"))
                        + "&msg=" + message
                        + "&from=" + request.getParameter("editOrder"));
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
