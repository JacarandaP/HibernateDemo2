package com.hibernatedemo2.Repositories;

import com.hibernatedemo2.Models.Capital;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Jacaranda Perez
 * Date: 2021-04-20
 * Project: HibernateDemo2
 */

public interface CapitalRepository extends CrudRepository <Capital, Long> {
    Capital findByName(String s);
}
