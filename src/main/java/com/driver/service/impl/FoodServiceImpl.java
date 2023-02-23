package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService{
    @Autowired
    public FoodRepository foodRepository;
    @Override
    public FoodDto createFood(FoodDto food) {
        FoodDto returnValue=new FoodDto();
        FoodEntity foodEntity=new FoodEntity();
        BeanUtils.copyProperties(food,foodEntity);

        FoodEntity foodEntityOutput=foodRepository.save(foodEntity);
        BeanUtils.copyProperties(foodEntityOutput,returnValue);

        return returnValue;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodDto returnValue=new FoodDto();
        FoodEntity foodEntity=foodRepository.findByFoodId(foodId);
        BeanUtils.copyProperties(foodEntity,returnValue);

        return returnValue;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        FoodDto returnValue=new FoodDto();
        FoodEntity foodEntity=foodRepository.findByFoodId(foodId);
        BeanUtils.copyProperties(foodDetails,foodEntity);
        FoodEntity foodEntityOutput=foodRepository.save(foodEntity);
        BeanUtils.copyProperties(foodEntityOutput,returnValue);

        return returnValue;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {

        FoodEntity foodEntity=foodRepository.findByFoodId(id);
        foodRepository.delete(foodEntity);

    }

    @Override
    public List<FoodDto> getFoods() {
       List<FoodDto> returnValue=new ArrayList<>();
       List<FoodEntity> foodEntityList= (List<FoodEntity>) foodRepository.findAll();
       for(FoodEntity food:foodEntityList){
           FoodDto foodDto=new FoodDto();
           BeanUtils.copyProperties(food,foodDto);
           returnValue.add(foodDto);

       }
       return returnValue;
    }
}