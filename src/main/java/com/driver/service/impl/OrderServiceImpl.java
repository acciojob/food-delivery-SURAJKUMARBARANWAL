package com.driver.service.impl;

import com.driver.io.entity.OrderEntity;
import com.driver.io.entity.UserEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.io.repository.UserRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public OrderDto createOrder(OrderDto order) {

        OrderDto returnValue=new OrderDto();
        OrderEntity orderEntity=new OrderEntity();
        BeanUtils.copyProperties(order,orderEntity);

        UserEntity user=userRepository.findByUserId(order.getUserId());
        user.getOrderEntityList().add(orderEntity);

        userRepository.save(user);
        OrderEntity orderEntityOutput=orderRepository.findByOrderId(order.getOrderId());
        BeanUtils.copyProperties(orderEntityOutput,returnValue);
        return returnValue;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderDto returnValue=new OrderDto();
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        if(orderEntity==null) throw new Exception("Order not found");
        BeanUtils.copyProperties(orderEntity,returnValue);
        return returnValue;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderDto returnValue=new OrderDto();
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        if(orderEntity==null) throw new Exception("Order not found");
        BeanUtils.copyProperties(order,orderEntity);
        OrderEntity orderEntityOutput=orderRepository.save(orderEntity);
        BeanUtils.copyProperties(orderEntityOutput,returnValue);
        return returnValue;
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        if(orderEntity==null) throw new Exception("Order not exist");
        orderRepository.delete(orderEntity);
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderDto> returnValue=new ArrayList<>();
        List<OrderEntity> orderEntityList= (List<OrderEntity>) orderRepository.findAll();
        for(OrderEntity order:orderEntityList){
            OrderDto orderDto=new OrderDto();
            BeanUtils.copyProperties(order,orderDto);
            returnValue.add(orderDto);
        }
        return returnValue;
    }
}