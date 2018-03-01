package by.asrohau.shop.dao;

import java.sql.Connection;

import by.asrohau.shop.dao.util.JDBCFactory;

public abstract class AbstractDAO<T> {

	protected Connection connection;
	
	protected Connection getConnection() {
		return this.connection = JDBCFactory.getConnection();	
	}
	
}
