package com.springapp.mvc.rest_api;

import com.springapp.mvc.service.MyServiceClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("rest")
public class MyRestControllerToAndroid {
	@Autowired
	MyServiceClass serviceClassToRest;

}
