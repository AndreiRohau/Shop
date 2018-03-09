package by.asrohau.shop.controller.command.impl;

import by.asrohau.shop.bean.Product;
import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;
import by.asrohau.shop.service.ProductService;
import by.asrohau.shop.service.ServiceFactory;
import by.asrohau.shop.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddNewProductCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to AddProductCommand");

        boolean isAdded; //= false
        Product newProduct = new Product(request.getParameter("name"), request.getParameter("type"), request.getParameter("price"));
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ProductService productService = serviceFactory.getProductService();
        request.getSession().setAttribute("address", "/jsp/admin/manageProducts.jsp");
        request.setAttribute("isAdded", null);
        try {
            isAdded = productService.addNewProduct(newProduct);
            String goToPage;
            if (isAdded) {
                request.setAttribute("isAdded", "You have added new product successfully");
                goToPage = (String) request.getSession().getAttribute("address");
            } else {
                goToPage = "error.jsp";
                request.setAttribute("errorMessage", "cant add this product, try again!");
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }

    }
}
