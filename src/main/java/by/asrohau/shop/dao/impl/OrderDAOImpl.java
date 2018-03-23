package by.asrohau.shop.dao.impl;

import by.asrohau.shop.bean.Order;
import by.asrohau.shop.bean.Reserve;
import by.asrohau.shop.bean.Product;
import by.asrohau.shop.dao.AbstractDAO;
import by.asrohau.shop.dao.exception.DAOException;
import by.asrohau.shop.dao.OrderDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;


public class OrderDAOImpl extends AbstractDAO<Reserve> implements OrderDAO {

    private String SAVE_RESERVATION_QUERY = "INSERT INTO shop.reserve (user_id, product_id) VALUES (?,?)";
    private String SELECT_ALL_RESERVED_QUERY = "SELECT * FROM shop.reserve WHERE user_id = ? LIMIT ?, ?";
    private String COUNT_RESERVED_QUERY = "SELECT COUNT(*) FROM shop.reserve WHERE user_id = ?";
    private String DELETE_RESERVED_QUERY = "DELETE FROM shop.reserve WHERE id = ?";
    private String SELECT_ALL_RESERVED_IDS_QUERY = "SELECT * FROM shop.reserve WHERE user_id = ?";
    private String DELETE_ALL_RESERVED_QUERY = "DELETE FROM shop.reserve WHERE user_id = ?";
    private String SAVE_NEW_ORDER_QUERY = "INSERT INTO shop.orders (user, products, address, phone) VALUES (?,?,?,?)";

    @Override
    public boolean saveNewReservation(Reserve reserve) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(SAVE_RESERVATION_QUERY)) {
            statement.setInt(1, reserve.getUser_id());
            statement.setInt(2, reserve.getProduct_id());

            int result = statement.executeUpdate();
            statement.close();
            connection.close();
            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public ArrayList<Product> selectAllReserved(int user_id, int row) throws DAOException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(SELECT_ALL_RESERVED_QUERY)) {
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, 15);
            ArrayList<Product> productList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            int reserve_id;
            int product_id;
            Product product;
            while (resultSet.next()) {

                reserve_id = resultSet.getInt(1);
                product_id = resultSet.getInt(3);
                product = new Product(product_id, reserve_id);
                productList.add(product);
            }
            preparedStatement.close();
            connection.close();
            return productList;

        } catch (SQLException e) {
            System.out.println("dao exception while get all reserved");
            throw new DAOException(e);
        }
    }

    @Override
    public int countReserved(int user_id) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(COUNT_RESERVED_QUERY)) {
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            int i = resultSet.getInt(1);
            statement.close();
            connection.close();
            return i;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean deleteReserved(int reserveId) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(DELETE_RESERVED_QUERY)) {
            statement.setInt(1, reserveId);

            int result = statement.executeUpdate();
            statement.close();
            connection.close();
            return (result != 0);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public LinkedList<Integer> selectAllReservedIds(int user_id) throws DAOException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(SELECT_ALL_RESERVED_IDS_QUERY)) {
            preparedStatement.setInt(1, user_id);
            LinkedList<Integer> productIdsList = new LinkedList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            int product_id;
            while (resultSet.next()) {
                product_id = resultSet.getInt(3);
                productIdsList.add(product_id);
            }
            preparedStatement.close();
            connection.close();
            return productIdsList;

        } catch (SQLException e) {
            System.out.println("dao exception while get all reserved IDs");
            throw new DAOException(e);
        }
    }

    @Override
    public boolean deleteAllReserved(int user_id) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(DELETE_ALL_RESERVED_QUERY)) {
            statement.setInt(1, user_id);

            int result = statement.executeUpdate();
            statement.close();
            connection.close();
            return (result != 0);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean insertNewOrder(Order order) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(SAVE_NEW_ORDER_QUERY)) {
            statement.setInt(1, order.getUser_id());
            statement.setString(2, order.getProductIDs());
            statement.setString(3, order.getUser_address());
            statement.setString(4, order.getUser_phone());

            int result = statement.executeUpdate();
            statement.close();
            connection.close();
            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
