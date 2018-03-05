package by.asrohau.shop.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;

public class ChangeLanguageCommand  implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

		try {
			System.out.println("request.getContextPath(): " + request.getContextPath());
			System.out.println("request.getPathInfo(): " + request.getPathInfo());
			System.out.println("request.getRequestURI(): " + request.getRequestURI());
			System.out.println("request.getRequestURL(): " + request.getRequestURL());

			HttpSession httpSession = request.getSession(true);
			
			httpSession.setAttribute("local", request.getParameter("local"));
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);


		} catch (ServletException | IOException e) {
			throw new ControllerException(e);
		}
	
	}
}
