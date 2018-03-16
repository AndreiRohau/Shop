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

public class ChangePasswordCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		System.out.println("We got to ChangePasswordCommand");

		User user = new User(request.getParameter("login").trim(),
				request.getParameter("password").trim(),
				request.getParameter("newPassword").trim());
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		UserService userService = serviceFactory.getUserService();

		request.getSession().setAttribute("lastCMD", "FrontController?command=goToPage&address=profile.jsp");
		boolean isChanged; //was false

		try {
			isChanged = request.getSession().getAttribute("userName").equals(user.getLogin()) && userService.changePassword(user);
			String goToPage;
			if (isChanged) {
				request.setAttribute("isChanged", "new password is: " + user.getNewPassword());
				goToPage = "/jsp/user/profile.jsp"; //was just address
			} else {
				goToPage = "error.jsp";
				request.setAttribute("errorMessage", "Can NOT change password.");
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher(goToPage);
			dispatcher.forward(request, response);

		} catch (ServiceException | ServletException | IOException e) {
			throw new ControllerException(e);
		}
	}

}
