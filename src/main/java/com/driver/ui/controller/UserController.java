package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.UserResponse;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
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
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{
		UserResponse returnValue=new UserResponse();
		UserDto userDto=userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto,returnValue);
		return returnValue;
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserResponse returnValue=new UserResponse();

		UserDto userDtoTemp=new UserDto();
		//copying property of userDetails to userDtoTemp
		BeanUtils.copyProperties(userDetails, userDtoTemp);

		UserDto userDto=userService.createUser(userDtoTemp);

		BeanUtils.copyProperties(userDto, returnValue);
		return returnValue;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
        UserResponse returnValue=new UserResponse();
		UserDto userDtoInput=new UserDto();
		BeanUtils.copyProperties(userDetails,userDtoInput);
		UserDto userDto=userService.updateUser(id,userDtoInput);
		BeanUtils.copyProperties(userDto,returnValue);
		return returnValue;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{
         userService.deleteUser(id);
		 OperationStatusModel statusModel=new OperationStatusModel();
		 statusModel.setOperationName("DELETE");
		 statusModel.setOperationResult("SUCCESS");
		 return statusModel;
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){
           List<UserResponse> returnValue=new ArrayList<>();
		   List<UserDto> userDtoList=userService.getUsers();
		   for(UserDto userDto:userDtoList){
			   UserResponse userResponse=new
					   UserResponse();
			   BeanUtils.copyProperties(userDto,userResponse);
			   returnValue.add(userResponse);
		   }
		return returnValue;
	}
//	BeanUtils.copyProperties(user, returnValue);
	
}
