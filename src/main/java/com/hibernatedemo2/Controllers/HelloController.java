package com.hibernatedemo2.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jacaranda Perez
 * Date: 2021-04-22
 * Project: HibernateDemo2
 */

@RestController
@RequestMapping(path="/")
public class HelloController {
    @GetMapping(path="/")
    public String helloWorld(){
        return "hello world";
    }
    @GetMapping(path="/es")
    public @ResponseBody String holaMundo(){
        return "hola mundo";
    }

}
