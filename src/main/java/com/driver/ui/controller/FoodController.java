package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
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
@RequestMapping("/foods")
public class FoodController {
    @Autowired
	FoodService foodService;
	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{
		FoodDetailsResponse returnValue=new FoodDetailsResponse();
		FoodDto foodDto =foodService.getFoodById(id);
		BeanUtils.copyProperties(foodDto,returnValue);
		return returnValue;
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {
        FoodDetailsResponse returnValue=new FoodDetailsResponse();
		FoodDto foodDtoInput=new FoodDto();
		BeanUtils.copyProperties(foodDetails,foodDtoInput);
		FoodDto foodDto=foodService.createFood(foodDtoInput);
		BeanUtils.copyProperties(foodDto,returnValue);
		return returnValue;
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		FoodDetailsResponse returnValue=new FoodDetailsResponse();
		FoodDto foodDtoInput=new FoodDto();
		BeanUtils.copyProperties(foodDetails,foodDtoInput);
		FoodDto foodDto=foodService.updateFoodDetails(id,foodDtoInput);
		BeanUtils.copyProperties(foodDto,returnValue);
		return returnValue;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
		foodService.deleteFoodItem(id);
		OperationStatusModel statusModel=new OperationStatusModel();
		statusModel.setOperationResult("SUCCESS");
		statusModel.setOperationName("DELETE");
		return statusModel;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
        List<FoodDetailsResponse> returnValue=new ArrayList<>();
		List<FoodDto> foodDtoList= foodService.getFoods();
		for(FoodDto food:foodDtoList){
			FoodDetailsResponse response=new FoodDetailsResponse();
			BeanUtils.copyProperties(food,response);
			returnValue.add(response);
		}
		return returnValue;
	}
}
