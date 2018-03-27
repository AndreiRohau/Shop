package by.asrohau.shop.dao;

import by.asrohau.shop.bean.Order;
import by.asrohau.shop.bean.Reserve;
import by.asrohau.shop.bean.Product;
import by.asrohau.shop.bean.User;
import by.asrohau.shop.dao.exception.DAOException;

import java.util.ArrayList;
import java.util.LinkedList;

public interface OrderDAO {

    boolean saveNewReservation(Reserve reserve) throws DAOException;
    ArrayList<Product> selectAllReserved(int user_id, int row) throws DAOException;
    int countReserved(int user_id) throws DAOException;
    boolean deleteReserved(int reserveId) throws DAOException;

    LinkedList<Integer> selectAllReservedIds(int user_id) throws DAOException;

    boolean deleteAllReserved(int user_id) throws DAOException;

    boolean insertNewOrder(Order order) throws DAOException;

    int countNewOrders() throws DAOException;

    ArrayList<Order> selectAllNewOrders(int row) throws DAOException;

    boolean deleteNewOrder(int orderId) throws DAOException;

    boolean updateOrderSetActive(int orderId) throws DAOException;

    Order selectOrderWithID(int orderId) throws DAOException;

    boolean updateOrdersProducts(Order order) throws DAOException;

    ArrayList<Order> selectAllActiveOrders(int row) throws DAOException;

    ArrayList<Order> selectAllSuccessOrders(int row) throws DAOException;
}
