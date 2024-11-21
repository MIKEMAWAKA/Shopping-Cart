package com.mike.shoppingcart.service.order;


import com.mike.shoppingcart.dto.OrderDto;
import com.mike.shoppingcart.enums.OrderStatus;
import com.mike.shoppingcart.exceptions.ProductNotFoundException;
import com.mike.shoppingcart.exceptions.ResourceNotFoundException;
import com.mike.shoppingcart.model.Cart;
import com.mike.shoppingcart.model.Order;
import com.mike.shoppingcart.model.OrderItem;
import com.mike.shoppingcart.model.Product;
import com.mike.shoppingcart.reposistory.OrderRepository;
import com.mike.shoppingcart.reposistory.ProductRepository;
import com.mike.shoppingcart.service.cart.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private CartService cartService;



    @Autowired
    private ModelMapper modelMapper;






    @Override
    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);

        Order order = createOrder(cart);
        List<OrderItem> orderItems = createOrderItems(order,cart);
        order.setOrderItems(new HashSet<>(orderItems));
        order.setTotalAmount(calculateTotalAmount(orderItems));

     Order saved= orderRepository.save(order);
     cartService.clearCart(cart.getId());

     return  saved;
    }


    private Order createOrder(Cart cart){
        Order order = new Order();

        //Set the user
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());

        return  order;


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
                          cartItem.getQuantity(),
                          cartItem.getUnitPrice()
                  );
              }
        ).toList();

    }



    @Override
    public OrderDto getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .map(this :: convertToDto)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }




    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList){
        BigDecimal totalamount;
        return  orderItemList.stream().map(item->item.getPrice()
                .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO,BigDecimal::add);


    }


    public List<OrderDto> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return  orders.stream().map(this :: convertToDto).toList();
    }


    private OrderDto convertToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

}
