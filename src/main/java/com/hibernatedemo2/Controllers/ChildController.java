package com.hibernatedemo2.Controllers;


import com.hibernatedemo2.Models.Capital;
import com.hibernatedemo2.Models.Child;
import com.hibernatedemo2.Models.Country;
import com.hibernatedemo2.Models.Present;
import com.hibernatedemo2.Repositories.CapitalRepository;
import com.hibernatedemo2.Repositories.ChildRepository;
import com.hibernatedemo2.Repositories.CountryRepository;
import com.hibernatedemo2.Repositories.PresentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path="/child")
public class ChildController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private PresentRepository presentRepository;

    @GetMapping(path = "/add")
    public String addChild(@RequestParam String name, @RequestParam String country) {
        Country existingCountry = countryRepository.findByName(country);
        Child child = new Child(name, existingCountry);
        childRepository.save(child);

        return name + " was added";

       /* Om man returnerar en Optional<Capital> istället för Capital från CapitalController så kan man få det rätt mycket kortare också.
                Då räcker det med
        Capital capital = capitalRepository.findByName(name)
                .orElse(new Capital().setName(name));
        capitalRepository.save(capital);
        för att få till hela if/else*/
        /* existsBy är en trevlig funktion i CrudRepository också, kan returnera en boolean.*/

    }
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Child> getAllChildren() {
        return childRepository.findAll();
    }


    @GetMapping(path = "/addWithPresent")
    public String addChildWithPresent(@RequestParam String name, @RequestParam String country, @RequestParam String wish) {
        Country existingCountry = countryRepository.findByName(country);
        Present existingPresent = presentRepository.findByName(wish);
        List<Present> presentList = Arrays.asList(existingPresent);
        Child child = new Child(name, existingCountry, presentList);
        childRepository.save(child);

        return name + " was added";
    }



}