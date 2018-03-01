package by.asrohau.shop.service;

import by.asrohau.shop.bean.Book;

public interface BookService {

	boolean validation(String title, String author);
	boolean addBook(String title, String author);
	Book[] findBookWithTitle(String title);
	Book[] findBookWithAuthor(String author);
	
}
