package com.hibernatedemo2.Repositories;

import com.hibernatedemo2.Models.Country;
import com.hibernatedemo2.Models.Present;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Jacaranda Perez
 * Date: 2021-04-22
 * Project: HibernateDemo2
 */

public interface PresentRepository extends CrudRepository <Present, Long>{
    Present findByName(String s);
}
