package by.asrohau.shop.dao;

import by.asrohau.shop.bean.Product;

public interface ProductDAO {

	Product findProduct(Product product);
	boolean addProduct(Product product);
	boolean updateProduct(Product product, String[] productInfo);
	boolean deleteProduct(Product product);

}
