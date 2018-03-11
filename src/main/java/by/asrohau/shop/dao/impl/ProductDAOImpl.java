package by.asrohau.shop.dao.impl;

import by.asrohau.shop.bean.Product;
import by.asrohau.shop.dao.AbstractDAO;
import by.asrohau.shop.dao.ProductDAO;
import by.asrohau.shop.dao.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAOImpl extends AbstractDAO<Product> implements ProductDAO {

	private String FIND_EQUAL_PRODUCT_QUERY = "SELECT * FROM shop.products WHERE name = ? AND type = ? AND price = ?";
	private String ADD_NEW_PRODUCT_QUERY = "INSERT INTO shop.products (name, type, price) VALUES (?,?,?)";
	private String UPDATE_PRODUCT_QUERY = "UPDATE shop.products SET name = ? AND type = ? AND price = ? WHERE id = ?";
	private String DELETE_PRODUCT_QUERY = "DELETE FROM shop.products WHERE id = ? AND name = ? AND type = ? AND price = ?";

	@Override
	public Product findProduct(Product product) throws DAOException {
		try (PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_EQUAL_PRODUCT_QUERY)) {
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getType());
			preparedStatement.setString(3, product.getPrice());
			ResultSet resultSet = preparedStatement.executeQuery();
			Product foundProduct = new Product();

			while (resultSet.next()) {
				foundProduct.setId(resultSet.getInt(1));
				foundProduct.setName(resultSet.getString(2));
				foundProduct.setType(resultSet.getString(3));
				foundProduct.setPrice(resultSet.getString(4));
			}
			preparedStatement.close();
			connection.close();

			if (foundProduct.getName() != null) {
				System.out.println("foundProduct.getName() != null : " + foundProduct.toString());
				return foundProduct;
			}
			System.out.println("Did not find = " + product.toString());
			return null;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean addProduct(Product product) throws DAOException {
		try (PreparedStatement statement = getConnection().prepareStatement(ADD_NEW_PRODUCT_QUERY)) {
			statement.setString(1, product.getName());
			statement.setString(2, product.getType());
			statement.setString(3, product.getPrice());

			statement.executeUpdate();
			statement.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean updateProduct(Product product) throws DAOException {
		try (PreparedStatement statement = getConnection().prepareStatement(UPDATE_PRODUCT_QUERY)) {
			statement.setString(1, product.getName());
			statement.setString(2, product.getType());
			statement.setString(3, product.getPrice());
			statement.setInt(4, product.getId());

			statement.executeUpdate();
			statement.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean deleteProduct(Product product) throws DAOException {
		try (PreparedStatement statement = getConnection().prepareStatement(DELETE_PRODUCT_QUERY)) {
			statement.setInt(1, product.getId());
			statement.setString(2, product.getName());
			statement.setString(3, product.getType());
			statement.setString(4, product.getPrice());

			statement.executeUpdate();
			statement.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
