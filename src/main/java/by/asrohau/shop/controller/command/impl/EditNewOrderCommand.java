package by.asrohau.shop.controller.command.impl;

import by.asrohau.shop.bean.Order;
import by.asrohau.shop.bean.Product;
import by.asrohau.shop.bean.User;
import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;
import by.asrohau.shop.service.OrderService;
import by.asrohau.shop.service.ProductService;
import by.asrohau.shop.service.ServiceFactory;
import by.asrohau.shop.service.UserService;
import by.asrohau.shop.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class EditNewOrderCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to EditNewOrderCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        OrderService orderService = serviceFactory.getOrderService();
        ProductService productService = serviceFactory.getProductService();

        Product product = new Product();

        String goToPage;
        int currentPage;
        int maxPage;
        int row;

        currentPage = Integer.parseInt(request.getParameter("page_num"));

        try {

            //find and get all prod ids from order; put into  String[] as [x, x, x ...]
            int orderID = Integer.parseInt(request.getParameter("orderId"));
            Order order = orderService.findOrderWithID(orderID);

            //currentPage is 1||2||3||4||5.........

            String productIDsString = order.getProductIDs();
            goToPage = "/jsp/admin/editOrder.jsp";

            String[] productIDsArray = productIDsString.split(",");
            maxPage = (int) Math.ceil(((double) productIDsArray.length) / 15);
            row = (currentPage - 1)*15; //

            int finArrlength = currentPage < maxPage ? 15 : (productIDsArray.length % 15 == 0 ? 15 : productIDsArray.length % 15);

            int[] productIDs = new int[finArrlength];
            //доставать из productIDsArray промежуток if(1)[1-15] - if(2)[16-30] - if(3)[31-45]......
            for(int i = 0; i < finArrlength; i++){
                productIDs[i] = Integer.parseInt(productIDsArray[i+row]);
            }
            //find each product. create an arraylist
            ArrayList<Product> productArray = new ArrayList<>();

            for(int id : productIDs){
                product.setId(id);
                product = productService.findProductWithId(product);
                product.setOrder_id(orderID);
                productArray.add(product);
                product = new Product();
            }

            request.setAttribute("productArray", productArray);
            request.setAttribute("productIDsString", productIDsString);
            request.setAttribute("orderId", order.getId());
            request.setAttribute("userId", order.getUser_id());
            request.setAttribute("address", order.getUser_address());
            request.setAttribute("phone", order.getUser_phone());
            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.getSession().setAttribute("lastCMD",
                    "FrontController?command=editNewOrder&page_num=" + currentPage
                            + "&orderId=" + orderID
                            + "&userId" + order.getUser_id()
                            + "&address" + order.getUser_address()
                            + "&phone" + order.getUser_phone());



            //what if not null??
            request.setAttribute("msg", request.getParameter("msg"));
            // what if not null ??
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }

    }
}
