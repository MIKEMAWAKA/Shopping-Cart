package com.mike.shoppingcart.service.order;


import com.mike.shoppingcart.exceptions.ProductNotFoundException;
import com.mike.shoppingcart.exceptions.ResourceNotFoundException;
import com.mike.shoppingcart.model.Cart;
import com.mike.shoppingcart.model.Order;
import com.mike.shoppingcart.model.OrderItem;
import com.mike.shoppingcart.model.Product;
import com.mike.shoppingcart.reposistory.OrderRepository;
import com.mike.shoppingcart.reposistory.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private ProductRepository productRepository;






    @Override
    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }


    private  List<OrderItem> createOrderItems(Order order, Cart cart){
        return cart.getItems().stream().map(
              cartItem ->   {
                  Product product = cartItem.getProduct();
                  product.setInventory(product.getInventory()-cartItem.getQuantity());

                  productRepository.save(product);

                  return  new OrderItem(
                          order,
                          product,
                          cartItem.getQuantity()


                  );
              }
        );

    }



    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).
                orElseThrow(()->{
                    throw new ProductNotFoundException("Product not found!");
                }
                );

    }




    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList){
        BigDecimal totalamount;
        return  orderItemList.stream().map(item->item.getPrice()
                .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);


    }

}
