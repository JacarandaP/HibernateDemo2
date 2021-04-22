package com.hibernatedemo2.Controllers;


import com.hibernatedemo2.Models.Capital;
import com.hibernatedemo2.Models.Present;
import com.hibernatedemo2.Repositories.CapitalRepository;
import com.hibernatedemo2.Repositories.PresentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/present")
@Slf4j
public class PresentController {

    @Autowired
    private PresentRepository presentRepository;

    @GetMapping(path = "/add")
    public String addPresent(@RequestParam String name) {
        Present present = new Present(name);
        presentRepository.save(present);
        log.info("present " + name + " sparad");
        return "Present sparad";

    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Present> getAllPresent() {
        return presentRepository.findAll();
    }


}
