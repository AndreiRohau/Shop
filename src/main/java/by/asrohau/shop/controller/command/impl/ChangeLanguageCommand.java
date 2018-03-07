package by.asrohau.shop.controller.command.impl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.asrohau.shop.controller.command.Command;
import by.asrohau.shop.controller.exception.ControllerException;

import java.io.IOException;

public class ChangeLanguageCommand  implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {

		System.out.println("We got to ChangeLanguageCommand");
		try {
			HttpSession httpSession = request.getSession(true);
			httpSession.setAttribute("local", request.getParameter("local"));
			String goToPage = (String) request.getSession().getAttribute("address");

			if (goToPage == null){
				goToPage = "index.jsp";
			}

			request.getRequestDispatcher(goToPage).forward(request, response);
		} catch ( ServletException | IOException e) {
			throw new ControllerException(e);
		}


	}
}
