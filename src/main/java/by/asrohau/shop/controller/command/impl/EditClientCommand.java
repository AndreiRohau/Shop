package by.asrohau.shop.controller.command.impl;

import by.asrohau.shop.bean.User;
import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;
import by.asrohau.shop.service.AdminService;
import by.asrohau.shop.service.ProductService;
import by.asrohau.shop.service.ServiceFactory;
import by.asrohau.shop.service.exception.ServiceException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class EditClientCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to EditClientCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        AdminService adminService = serviceFactory.getAdminService();
        request.getSession().setAttribute("address", "/jsp/admin/editClient.jsp");
        String goToPage;
        User user  = new User();
        user.setId(Integer.parseInt(request.getParameter("userId")));
        try {
            user = adminService.findUserWithId(user);
            if(user.getLogin() != null){
                request.setAttribute("userToEdit", user);
                goToPage = (String) request.getSession().getAttribute("address");
                System.out.println(user.toString());
            } else {
                goToPage = "error.jsp";
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
