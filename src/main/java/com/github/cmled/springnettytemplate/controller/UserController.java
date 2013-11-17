package com.github.cmled.springnettytemplate.controller;

import javax.ws.rs.Path;

import org.springframework.stereotype.Controller;

import com.github.cmled.springnettytemplate.model.User;

@Controller
@Path("/user")
public class UserController extends BaseController<User>{
	
	public UserController() {
		initGenericService(User.class);
	}
}
