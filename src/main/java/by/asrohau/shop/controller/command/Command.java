package by.asrohau.shop.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.asrohau.shop.controller.exception.ControllerException;

public interface Command {

	void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException;
	
}
