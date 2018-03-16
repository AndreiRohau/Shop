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

public class EditProductCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to EditProductCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ProductService productService = serviceFactory.getProductService();
        String lastCMD;
        String goToPage;
        Product product  = new Product();
        product.setId(Integer.parseInt(request.getParameter("productId")));
        try {
            product = productService.findProductWithId(product);
            if(product != null){
                request.setAttribute("productToEdit", product);
                goToPage = "/jsp/admin/editProduct.jsp";
                lastCMD = "FrontController?command=editProduct&productId=" + request.getParameter("productId");

            } else {
                goToPage = "error.jsp";
                lastCMD = "FrontController?command=goToPage&address=manageProducts.jsp";
            }
            request.getSession().setAttribute("lastCMD", lastCMD);
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
