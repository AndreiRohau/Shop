package by.asrohau.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asrohau.shop.bean.User;
import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;
import by.asrohau.shop.service.ServiceFactory;
import by.asrohau.shop.service.UserService;
import by.asrohau.shop.service.exception.ServiceException;

public class RegistrationCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		System.out.println("We got to REGISTRATION");
		boolean isRegistered = false;

		User user = new User(request.getParameter("login"), request.getParameter("password"));
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		try {
			isRegistered = userService.registration(user);
			
			String goToPage;
			if (isRegistered) {
				request.setAttribute("isRegistered", "You registered successfully");
				goToPage = "index.jsp";
			} else {
				goToPage = "error.jsp";
				request.setAttribute("errorMessage", "cant register");
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
