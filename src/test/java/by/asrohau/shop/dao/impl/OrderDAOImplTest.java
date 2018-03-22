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
            LinkedList<Integer> ids = orderService.getAllReserved(user_id, row);

            for(int id : ids) {
                System.out.println(id);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void WselectAllReservedTest(){


        int user_id = 46; // login 1, psw 1
        int row = 14;
        ServiceFactory sf = ServiceFactory.getInstance();
        OrderService orderService = sf.getOrderService();
        ProductService productService = sf.getProductService();
        try {
            LinkedList<Integer> ids = orderService.getAllReserved(user_id, row);

            for(int id : ids) {
                System.out.println(id);
            }
            System.out.println();
            ArrayList<Product> reservedList = new ArrayList<>();
            Product product = new Product();
            for(int id : ids){

                product.setId(id);
                reservedList.add(productService.findProductWithId(product));
                product = new Product();
            }

//            while(ids.size() != 0){
//                Product product = new Product();
//                product.setId(ids.pollFirst());
//                product = productService.findProductWithId(product);
//                System.out.println(product);
//
//                reservedList.add(product);
//            }
            System.out.println();
            for (Product p : reservedList){
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
