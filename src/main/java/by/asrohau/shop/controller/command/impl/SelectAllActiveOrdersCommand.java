package by.asrohau.shop.controller.command.impl;

import by.asrohau.shop.bean.Order;
import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;
import by.asrohau.shop.service.OrderService;
import by.asrohau.shop.service.ServiceFactory;
import by.asrohau.shop.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/*
returns new ORDERS list:   user_id - forward edit user!
                           order_id
                                forward to page with detailed list of products you can delete some of them
                                and total sum of payment
                           STATUS SET-ACTIVE adds command to change status
                           EDIT is .....
                            and here you can delete order
 */
public class SelectAllActiveOrdersCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to SelectAllNewOrdersCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();

        //return



        String goToPage;
        int currentPage;
        int maxPage;
        int row;

        currentPage = Integer.parseInt(request.getParameter("page_num"));
        row = (currentPage - 1)*15;

        try {
            //count amount of all NEW orders
            maxPage = (int) Math.ceil(((double) orderService.countNewOrders()) / 15);

            ArrayList<Order> newOrdersList = orderService.getAllActiveOrders(row);
            request.setAttribute("array", newOrdersList);

            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("command", "deleteOrder");  // todo
            request.getSession().setAttribute("lastCMD",
                    "FrontController?command=selectAllActiveOrders&page_num=" + currentPage);

            goToPage = "/jsp/admin/manageOrders.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
