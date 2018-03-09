package by.asrohau.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asrohau.shop.bean.User;
import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;
import by.asrohau.shop.service.AdminService;
import by.asrohau.shop.service.ServiceFactory;
import by.asrohau.shop.service.UserService;
import by.asrohau.shop.service.exception.ServiceException;

public class LoginationCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		System.out.println("We got to logination");

		User user = new User(request.getParameter("login"),  request.getParameter("password"));
		UserDTO userDTO = null;
		String goToPage;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		try {
			if(!request.getParameter("login").equals("Admin") && request.getSession().getAttribute("userName") == null){
				UserService userService = serviceFactory.getUserService();
				userDTO = userService.logination(user);
				if (userDTO != null) {
					goToPage = "/jsp/user/main.jsp";
				} else {
					goToPage = "error.jsp";
				}
			} else if (request.getParameter("login").equals("Admin") && request.getSession().getAttribute("userName") == null) {
				AdminService adminService = serviceFactory.getAdminService();
				userDTO = adminService.logination(user);
				if(userDTO != null) {
					goToPage = "/jsp/admin/main.jsp";
				} else {
					goToPage = "error.jsp";
				}
			} else {
				goToPage = "error.jsp";
			}

			request.getSession(true).setAttribute("address", goToPage);

			if (userDTO != null) {
				request.getSession().setAttribute("userName", userDTO.getLogin());
			} else {
				request.setAttribute("errorMessage", "no such user");
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			dispatcher.forward(request, response);

		} catch (ServiceException | ServletException | IOException e) {
			throw new ControllerException(e);
		}

	}

}
