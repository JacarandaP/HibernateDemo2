package com.hibernatedemo2.Repositories;

import com.hibernatedemo2.Models.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Jacaranda Perez
 * Date: 2021-04-20
 * Project: HibernateDemo2
 */

public interface CountryRepository extends CrudRepository<Country, Long> {
    Country findByName(String s);
    void deleteByName(String s);
    Optional<Country> findById(Long id);
    Country findAllById(Long id);
    void deleteById(Long id);

   // void delete(Optional<Country> countryToDelete);
}
