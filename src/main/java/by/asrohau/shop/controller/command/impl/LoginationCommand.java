package by.asrohau.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asrohau.shop.bean.UserDTO;
import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;
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
		UserService userService = serviceFactory.getUserService();

		UserDTO userDTO = null;

		try {
			userDTO = userService.logination(login.trim(), password.trim());

			String goToPage;
			if (userDTO != null) {
				//стартануть сессию юзера конкретного
				request.setAttribute("userName", userDTO.getLogin());
				goToPage = "/jsp/main.jsp";
			} else {
				goToPage = "error.jsp";
				request.setAttribute("errorMessage", "no such user");
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			dispatcher.forward(request, response);

		} catch (ServiceException e) {
			throw new ControllerException(e);
		} catch (ServletException e) {
			throw new ControllerException(e);
		} catch (IOException e) {
			throw new ControllerException(e);
		}

	}

}
