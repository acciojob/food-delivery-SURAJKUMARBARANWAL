package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderService orderService;
	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{
		OrderDetailsResponse returnValue=new OrderDetailsResponse();
		OrderDto orderDto=orderService.getOrderById(id);
		BeanUtils.copyProperties(orderDto,returnValue);

		return returnValue;
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		OrderDetailsResponse returnValue=new OrderDetailsResponse();
		OrderDto orderDtoInput=new OrderDto();
		BeanUtils.copyProperties(order,orderDtoInput);

		OrderDto orderDto=orderService.createOrder(orderDtoInput);
		BeanUtils.copyProperties(orderDto,returnValue);

		return returnValue;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		OrderDetailsResponse returnValue=new OrderDetailsResponse();
		OrderDto orderDtoInput=new OrderDto();
		BeanUtils.copyProperties(order,orderDtoInput);

		OrderDto orderDto=orderService.updateOrderDetails(id,orderDtoInput);
		BeanUtils.copyProperties(orderDto,returnValue);

		return returnValue;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		orderService.deleteOrder(id);
		OperationStatusModel statusModel=new OperationStatusModel();
		statusModel.setOperationName("DELETE");
		statusModel.setOperationResult("SUCCESS");

		return statusModel;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDetailsResponse> returnValue=new ArrayList<>();
		List<OrderDto> orderDtoList=orderService.getOrders();
		for(OrderDto order:orderDtoList){
			OrderDetailsResponse detailsResponse=new OrderDetailsResponse();
			BeanUtils.copyProperties(
					order,detailsResponse
			);
			returnValue.add(detailsResponse);

		}
		return returnValue;
	}
}
