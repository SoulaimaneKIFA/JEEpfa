package ma.zahkifa.orderservice.service;

import ma.zahkifa.orderservice.model.Order;

import java.util.List;

public interface OrderService {
    String getProductDetails(Long productId);
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order createOrder(Order order);
    Order updateOrder(Long id, Order order);
    void deleteOrder(Long id);
}
