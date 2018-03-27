package by.asrohau.shop.controller.command.impl;

import by.asrohau.shop.bean.Order;
import by.asrohau.shop.bean.Product;
import by.asrohau.shop.bean.User;
import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;
import by.asrohau.shop.service.OrderService;
import by.asrohau.shop.service.ServiceFactory;
import by.asrohau.shop.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteFromOrderCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to DeleteFromOrderCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();

        Order order = new Order();
        order.setId(Integer.parseInt(request.getParameter("orderId")));

        String goToPage;
        int currentPage;
        int maxPage;
        int row;

        String productIDsString = request.getParameter("productIDsString");
        currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int counter =  Integer.parseInt(request.getParameter("counter"));


        try {

            String[] productIDsArray = productIDsString.split(",");

            int indexOfdeletingProd = (currentPage - 1) * 15 + counter;

            String finalIDs = "";
            counter = 1;
            for(String id : productIDsArray){
                if(counter != indexOfdeletingProd && counter < productIDsArray.length) {
                    finalIDs = finalIDs + id + ",";
                }
                counter++;
            }

            System.out.println(finalIDs);
            order.setProductIDs(finalIDs);
            String message;
            if (orderService.deleteProductFromOrder(order)) {
                message = "You have REMOVED product successfully";
                System.out.println(message);
            } else {
                message = "Can NOT remove this product, try again!";
                System.out.println(message);
            }

            response.sendRedirect(String.valueOf(request.getSession(true).getAttribute("lastCMD"))
                    + "&msg=" + message + "&orderId=" + order.getId());

        } catch (ServiceException | IOException e) {
            throw new ControllerException(e);
        }


    }
}
