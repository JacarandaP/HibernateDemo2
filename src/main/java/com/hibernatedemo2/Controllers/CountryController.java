package com.hibernatedemo2.Controllers;

import com.hibernatedemo2.Models.Capital;
import com.hibernatedemo2.Models.Child;
import com.hibernatedemo2.Models.Country;
import com.hibernatedemo2.Repositories.CapitalRepository;
import com.hibernatedemo2.Repositories.ChildRepository;
import com.hibernatedemo2.Repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path="/country")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CapitalRepository capitalRepository;

    @Autowired
    private ChildRepository childRepository;

    @GetMapping(path = "/add")
    public String addCountry(@RequestParam String name, @RequestParam String capital) {
        Capital existingCapital = capitalRepository.findByName(capital);
        if (existingCapital == null) {
            Capital c = new Capital(capital);
            Country country = new Country(name, c);
            countryRepository.save(country);
        } else {
            Country country = new Country(name, existingCapital);
            countryRepository.save(country);
        }

        return  "was added";

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
    Iterable<Country> getAllCountriess() {
        return countryRepository.findAll();
    }

    @GetMapping(path="/getById")
    public Country getCountryById(@RequestParam long id){
        return countryRepository.findById(id).get();
    }

    @RequestMapping(path="/{countryID}/delete")
    public String deleteCountry(@PathVariable("countryID")Long countryID){
        //List<Child> childrenToUpdate = childRepository.findAllByCountry_Id(countryID);
        //childrenToUpdate.forEach(child -> child.setCountry(null));
        Country countryToDelete = countryRepository.findAllById(countryID);
        countryRepository.delete(countryToDelete);
        return "country deleted";
    }



}