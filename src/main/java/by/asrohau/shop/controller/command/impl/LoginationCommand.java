package by.asrohau.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		ServiceFactory serviceFactory = ServiceFactory.getInstance();

		UserDTO userDTO;
		String goToPage;

		try {
			if(!login.equals("Admin")){
				UserService userService = serviceFactory.getUserService();
				userDTO = userService.logination(login.trim(), password.trim());
				goToPage = "/jsp/main.jsp";
			} else {
				AdminService adminService = serviceFactory.getAdminService();
				userDTO = adminService.logination(login.trim(), password.trim());
				goToPage = "/jsp/admin.jsp";
			}

			if (userDTO != null) {
				//стартануть сессию юзера конкретного
				request.setAttribute("userName", userDTO.getLogin());

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
