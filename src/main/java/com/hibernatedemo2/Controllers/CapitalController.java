package com.hibernatedemo2.Controllers;

import com.hibernatedemo2.Models.Capital;
import com.hibernatedemo2.Repositories.CapitalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Jacaranda Perez
 * Date: 2021-04-20
 * Project: HibernateDemo2
 */


@RestController
@RequestMapping(path="/capital")
public class CapitalController {

    private static final Logger log = LoggerFactory.getLogger(CapitalController.class);

    @Autowired
    private CapitalRepository capitalRepository;

    @GetMapping(path = "/add")
    public String addCapital(@RequestParam String name) {
        Capital capital = new Capital(name);
        capitalRepository.save(capital);
        log.info("Capital " + name + " added");
        log.debug("debugging add capitals");
        return "Capital sparad";

    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Capital> getAllCapitals() {
        log.info("someone is looking at the cities");
        return capitalRepository.findAll();
    }

    @GetMapping(path="/getById")
    public Capital getCapitalById(@RequestParam long id){
        return capitalRepository.findById(id).get();
    }

}