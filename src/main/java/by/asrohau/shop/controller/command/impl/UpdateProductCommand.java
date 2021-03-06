package by.asrohau.shop.controller.command.impl;

import by.asrohau.shop.bean.Product;
import by.asrohau.shop.bean.User;
import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;
import by.asrohau.shop.service.AdminService;
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

public class UpdateProductCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to EditProductCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ProductService productService = serviceFactory.getProductService();
        String lastCMD;
        String goToPage;
        Product product = new Product(Integer.parseInt(request.getParameter("id")),
                request.getParameter("company"),
                request.getParameter("name"),
                request.getParameter("type"),
                request.getParameter("price"),
                request.getParameter("description"));

        try {
            if(!productService.updateProduct(product)){
                request.setAttribute("updateFailed", "Update failed");
                product = productService.findProductWithId(product);
            }
            request.setAttribute("productToEdit", product);
            lastCMD = "FrontController?command=editProduct&productId=" + request.getParameter("id");
            goToPage = "/jsp/admin/editProduct.jsp";

            request.getSession().setAttribute("lastCMD", lastCMD);
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}