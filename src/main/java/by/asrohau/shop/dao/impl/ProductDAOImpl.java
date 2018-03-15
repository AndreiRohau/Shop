package by.asrohau.shop.dao.impl;

import by.asrohau.shop.bean.Product;
import by.asrohau.shop.dao.AbstractDAO;
import by.asrohau.shop.dao.ProductDAO;
import by.asrohau.shop.dao.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl extends AbstractDAO<Product> implements ProductDAO {

	private String FIND_EQUAL_PRODUCT_QUERY = "SELECT * FROM shop.products WHERE name = ? AND type = ? AND price = ?";
	private String ADD_NEW_PRODUCT_QUERY = "INSERT INTO shop.products (name, type, price, description) VALUES (?,?,?,?)";
	private String SELECT_ALL_PRODUCTS_QUERY = "SELECT * FROM shop.products";
	private String FIND_PRODUCT_WITH_ID_QUERY = "SELECT * FROM shop.products WHERE id = ?";
	private String UPDATE_PRODUCT_QUERY = "UPDATE shop.products SET name = ?, type = ?, price = ?, description = ? WHERE id = ?";
	private String DELETE_PRODUCT_QUERY = "DELETE FROM shop.products WHERE id = ?";

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
			statement.setString(4, product.getDescription());

			int result = statement.executeUpdate();
			statement.close();
			connection.close();
			return (result != 0);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public ArrayList<Product> selectAllProducts() throws DAOException {
		try (PreparedStatement preparedStatement = getConnection()
				.prepareStatement(SELECT_ALL_PRODUCTS_QUERY)) {
			ArrayList<Product> productArrayList = new ArrayList<Product>();
			ResultSet resultSet = preparedStatement.executeQuery();
			Product product;

			int id;
			String name;
			String type;
			String price;
			String description;
			while (resultSet.next()) {
				id = resultSet.getInt(1);
				name = resultSet.getString(2);
				type = resultSet.getString(3);
				price = resultSet.getString(4);
				description = resultSet.getString(5);
				product = new Product(id, name, type, price, description);
				productArrayList.add(product);
			}
			preparedStatement.close();
			connection.close();
			return productArrayList;

		} catch (SQLException e) {
			System.out.println("dao exception while get all products");
			throw new DAOException(e);
		}
	}

	@Override
	public Product findProductWithId(Product product) throws DAOException {
		try (PreparedStatement preparedStatement = getConnection()
				.prepareStatement(FIND_PRODUCT_WITH_ID_QUERY)) {
			preparedStatement.setInt(1, product.getId());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				product.setId(resultSet.getInt(1));
				product.setName(resultSet.getString(2));
				product.setType(resultSet.getString(3));
				product.setPrice(resultSet.getString(4));
				product.setDescription(resultSet.getString(5));
			}
			preparedStatement.close();
			connection.close();

			if (product.getName() != null) {
				return product;
			}
			System.out.println("Did not find User with id = " + product.getId());
			return null;
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
			statement.setString(4, product.getDescription());
			statement.setInt(5, product.getId());

			int result = statement.executeUpdate();
			statement.close();
			connection.close();
			return (result != 0);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean deleteProduct(Product product) throws DAOException {
		try (PreparedStatement statement = getConnection().prepareStatement(DELETE_PRODUCT_QUERY)) {
			statement.setInt(1, product.getId());

			int result = statement.executeUpdate();
			statement.close();
			connection.close();
			return (result != 0);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

}
