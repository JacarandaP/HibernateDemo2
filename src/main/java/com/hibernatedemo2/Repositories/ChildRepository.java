package com.hibernatedemo2.Repositories;

import com.hibernatedemo2.Models.Child;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Jacaranda Perez
 * Date: 2021-04-20
 * Project: HibernateDemo2
 */

public interface ChildRepository extends CrudRepository<Child, Long> {
    List<Child> findAllByCountry_Id(Long id);
}
