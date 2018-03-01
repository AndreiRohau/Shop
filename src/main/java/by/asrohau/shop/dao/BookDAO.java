package by.asrohau.shop.dao;

import by.asrohau.shop.bean.Book;

public interface BookDAO {

	Book findBook(String title, String author);

	boolean saveBook(String title, String author);
	
	Book[] findBookWithTitle(String title);
	
	Book[] findBookWithAuthor(String author);
}
