package by.asrohau.shop.dao.impl;

import by.asrohau.shop.bean.Order;
import by.asrohau.shop.dao.AbstractDAO;
import by.asrohau.shop.dao.exception.DAOException;
import by.asrohau.shop.dao.OrderDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


public class OrderDAOImpl extends AbstractDAO<Order> implements OrderDAO {

    private String SAVE_RESERVATION_QUERY = "INSERT INTO shop.reserve (user_id, product_id) VALUES (?,?)";
    private String SELECT_ALL_RESERVED_QUERY = "SELECT * FROM shop.reserve WHERE user_id = ? LIMIT ?, ?";
    private String COUNT_RESERVED_QUERY = "SELECT COUNT(*) FROM shop.reserve WHERE user_id = ?";

    @Override
    public boolean saveNewReservation(Order order) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(SAVE_RESERVATION_QUERY)) {
            statement.setInt(1, order.getUser_id());
            statement.setInt(2, order.getProduct_id());

            int result = statement.executeUpdate();
            statement.close();
            connection.close();
            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public LinkedList<Integer> selectAllReserved(int user_id, int row) throws DAOException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(SELECT_ALL_RESERVED_QUERY)) {
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, 15);
            LinkedList<Integer> productIdList = new LinkedList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            int id;
            while (resultSet.next()) {
                id = resultSet.getInt(3);
                productIdList.add(id);
            }
            preparedStatement.close();
            connection.close();
            return productIdList;

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
}
