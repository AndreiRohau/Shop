package by.asrohau.shop.dao;

import by.asrohau.shop.bean.Order;
import by.asrohau.shop.bean.Product;
import by.asrohau.shop.dao.exception.DAOException;

import java.util.ArrayList;

public interface OrderDAO {

    boolean saveNewReservation(Order order) throws DAOException;
    ArrayList<Product> selectAllReserved(int user_id, int row) throws DAOException;
    int countReserved(int user_id) throws DAOException;
    boolean deleteReserved(int reserveId) throws DAOException;
}
