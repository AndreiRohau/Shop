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

public class ChangePasswordCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

		System.out.println("We got to changePassword");
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		boolean isChanged = false;

		try {
			isChanged = userService.changePassword(login.trim(), password.trim(), newPassword.trim());
			UserDTO userDTO = new UserDTO();
			userDTO.setLogin(login);

			String goToPage;
			if (isChanged) {
				request.setAttribute("myuser", userDTO);
				request.setAttribute("isChanged", "new password is: " + newPassword);
				goToPage = "/jsp/main.jsp";
			} else {
				goToPage = "error.jsp";
				request.setAttribute("errorMessage", "cannot change password");
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
