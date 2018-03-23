package by.asrohau.shop.dao.impl;

import by.asrohau.shop.bean.Product;
import by.asrohau.shop.bean.User;
import by.asrohau.shop.service.OrderService;
import by.asrohau.shop.service.ProductService;
import by.asrohau.shop.service.ServiceFactory;
import by.asrohau.shop.service.UserService;
import by.asrohau.shop.service.exception.ServiceException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

public class OrderDAOImplTest {

    @Test
    public void selectAllReservedTest(){

        int user_id = 46; // login 1, psw 1
        int row = 12;
        ServiceFactory sf = ServiceFactory.getInstance();
        OrderService orderService = sf.getOrderService();

        try {
            ArrayList<Product> ids = orderService.getAllReserved(user_id, row);

            for(Product product : ids) {
                System.out.println(product);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void WselectAllReservedTest(){


        int user_id = 46; // login 1, psw 1
        int row = 12;
        ServiceFactory sf = ServiceFactory.getInstance();
        OrderService orderService = sf.getOrderService();
        ProductService productService = sf.getProductService();
        try {
            ArrayList<Product> reservedWithIdsList = orderService.getAllReserved(user_id, row);

            for(Product p : reservedWithIdsList) {
                System.out.println(p);
            }
            System.out.println();

            ArrayList<Product> productArray = new ArrayList<>();
            Product product;
            for(Product prod : reservedWithIdsList){
                product = productService.findProductWithId(prod);
                product.setReserve_id(prod.getReserve_id());
                productArray.add(product);
                product = new Product();
            }
            System.out.println();
            for (Product p : productArray){
                System.out.println(p);
            }

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void countReservedTest(){

        int user_id = 46; // login 1, psw 1

        ServiceFactory sf = ServiceFactory.getInstance();
        OrderService orderService = sf.getOrderService();

        try {
            int i = orderService.countReserved(user_id);
            System.out.println(i);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}
