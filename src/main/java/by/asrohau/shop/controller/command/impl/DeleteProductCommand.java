package by.asrohau.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asrohau.shop.bean.Product;
import by.asrohau.shop.bean.User;
import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;
import by.asrohau.shop.service.ProductService;
import by.asrohau.shop.service.ServiceFactory;
import by.asrohau.shop.service.UserService;
import by.asrohau.shop.service.exception.ServiceException;

public class DeleteProductCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to delete PRODUCT Command");

        Product product = new Product();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ProductService productService = serviceFactory.getProductService();
        product.setId(Integer.parseInt(request.getParameter("id")));
        boolean isChanged = false;
        String lastCMD;
        try {
            String goToPage;
            if (productService.deleteProduct(product)) {
                goToPage = "/jsp/admin/manageProducts.jsp";
                lastCMD = "FrontController?command=goToPage&address=manageProducts.jsp";
            } else {
                goToPage = "/jsp/admin/manageProducts.jsp";
                request.setAttribute("errorMessage", "cannot delete product");
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
