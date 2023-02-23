package com.driver.service.impl;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements com.driver.service.UserService{
    @Autowired
    public UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) throws Exception {
        //return Value
        UserDto returnValue=new UserDto();
        UserEntity userEntity=new UserEntity();
        //setting userEntity attributes
        BeanUtils.copyProperties(user,userEntity);
        //saving to table
        BeanUtils.copyProperties(userRepository.save(userEntity),returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUser(String email) throws Exception {
        //return Value
        UserDto returnValue=new UserDto();
        UserEntity user=userRepository.findByEmail(email);
        BeanUtils.copyProperties(user,returnValue);
        return returnValue;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        //return Value
        UserDto returnValue=new UserDto();
        UserEntity user=userRepository.findByUserId(userId);
        BeanUtils.copyProperties(user,returnValue);
        return returnValue;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        //return Value
        UserDto returnValue=new UserDto();
        UserEntity userEntity=userRepository.findByUserId(userId);
        BeanUtils.copyProperties(user,userEntity);
        //save back to table
        UserEntity userEntity1=userRepository.save(userEntity);
        BeanUtils.copyProperties(userEntity1,returnValue);
        return returnValue;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        UserEntity userEntity=userRepository.findByUserId(userId);
        if(userEntity==null) throw new Exception("User is not exist");
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserDto> userDtoList=new ArrayList<>();
        List<UserEntity> userEntityList= (List<UserEntity>) userRepository.findAll();
        for(UserEntity user:userEntityList){
            UserDto userDto=new UserDto();
            BeanUtils.copyProperties(user,userDto);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}