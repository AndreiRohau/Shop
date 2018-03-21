package by.asrohau.shop.dao;

import by.asrohau.shop.bean.Product;
import by.asrohau.shop.dao.exception.DAOException;
import by.asrohau.shop.service.exception.ServiceException;

import java.util.ArrayList;

public interface ProductDAO {

	Product findProduct(Product product) throws DAOException;
	boolean addProduct(Product product) throws DAOException;
	ArrayList<Product> selectAllProducts(int row) throws DAOException;
	Product findProductWithId(Product product) throws DAOException;
	boolean updateProduct(Product product) throws DAOException;
	boolean deleteProduct(Product product) throws DAOException;
	int countProducts() throws DAOException;

}
