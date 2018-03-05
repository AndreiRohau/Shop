package by.asrohau.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		System.out.println(request.getParameter("login"));
		User user = new User(request.getParameter("login"),  request.getParameter("password"));
		UserDTO userDTO;
		String goToPage;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		try {
			if(!request.getParameter("login").equals("Admin")){
				UserService userService = serviceFactory.getUserService();
				userDTO = userService.logination(user);
				goToPage = "/jsp/user/main.jsp";
			} else {
				AdminService adminService = serviceFactory.getAdminService();
				userDTO = adminService.logination(user);
				goToPage = "/jsp/admin/main.jsp";
			}
			if (userDTO != null) {
				HttpSession session = request.getSession(true);
				session.setAttribute("userName", userDTO.getLogin());

			} else {
				goToPage = "error.jsp";
				request.setAttribute("errorMessage", "no such user");
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			dispatcher.forward(request, response);

		} catch (ServiceException | ServletException | IOException e) {
			throw new ControllerException(e);
		}

	}

}
