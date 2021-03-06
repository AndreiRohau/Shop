package by.asrohau.shop.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.command.CommandFactory;
import by.asrohau.shop.controller.exception.ControllerException;

public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final CommandFactory commandFactory = CommandFactory.getInstance();

	public FrontController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doExecute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doExecute(request, response);
	}

	private void doExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("in servlet " + request.getMethod() + ": command : " + request.getParameter("command"));
		Map commandMap = CommandFactory.getInstance().getCommandMap();
		Command command;
		try {
			if (request.getParameter("command").matches("logout") ||
					request.getParameter("command").matches("logination") ||
					request.getParameter("command").matches("registration") ||
					request.getParameter("command").matches("change_language") ||
					request.getSession().getAttribute("userName") != null) {
				command = (Command) commandMap.get(request.getParameter("command"));
			} else {
				command = (Command) commandMap.get("goToPage");
			}
			command.execute(request, response);

		} catch (ControllerException e) {
			request.setAttribute("errorMessage", e.toString());
			RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
			dispatcher.forward(request, response);
		}
	}



}
