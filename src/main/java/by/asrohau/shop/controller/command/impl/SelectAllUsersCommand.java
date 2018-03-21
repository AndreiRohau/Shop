package by.asrohau.shop.controller.command.impl;

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

public class SelectAllUsersCommand implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
        System.out.println("We got to SelectAllUsersCommand");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        String goToPage;
        int currentPage;
        int maxPage;
        int row;

        currentPage = Integer.parseInt(request.getParameter("page_num"));
        row = (currentPage - 1)*15;

        try {
            //count amount of all products
            maxPage = (int) Math.ceil(((double) userService.countUsers()) / 15);
            System.out.println("maxPage is " + maxPage);

            ArrayList<User> userArrayList = userService.getAllUsers(row);
            request.setAttribute("usersArray", userArrayList);

            request.setAttribute("maxPage", maxPage);
            request.setAttribute("currentPage", currentPage);
            request.getSession().setAttribute("lastCMD",
                    "FrontController?command=selectAllUsers&page_num=" + currentPage);

            goToPage = "/jsp/admin/manageClients.jsp";
            RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
            dispatcher.forward(request, response);

        } catch (ServiceException | ServletException | IOException e) {
            throw new ControllerException(e);
        }
    }
}
