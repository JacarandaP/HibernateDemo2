package com.hibernatedemo2.controller;

import com.hibernatedemo2.Models.Capital;
import com.hibernatedemo2.Models.Country;
import com.hibernatedemo2.Repositories.CapitalRepository;
import com.hibernatedemo2.Repositories.CountryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Jacaranda Perez
 * Date: 2021-04-22
 * Project: HibernateDemo2
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CountryRepository mockRepository;

    @BeforeEach
    public void init(){
        Country c1= new Country( 1L, "Argentina",new Capital(1L, "Buenos Aires"));
        Country c2= new Country(2L, "Chile", new Capital(2L, "Santiago"));
        Country c3= new Country(3L, "Peru", new Capital(3L, "Lima"));
        when(mockRepository.findById(2L)).thenReturn(Optional.of(c2));
        when(mockRepository.findAll()).thenReturn(Arrays.asList(c1, c2, c3));
    }


    @Test
    public void AddCountryTest()throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/country/add?name=Argentina&capital=Buenos Aires").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("was added")));
    }

    @Test
    public void getByIdTest() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/country/getById?id=2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":2, \"name\":\"Chile\", \"capital\":{\"id\":2, \"name\":\"Santiago\"}}"));
    }

    @Test
    public void getallTest() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/country/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1, \"name\":\"Argentina\", \"capital\":{\"id\":1, \"name\": \"Buenos Aires\"}}," +
                        "{\"id\":2, \"name\":\"Chile\", \"capital\":{\"id\":2, \"name\":\"Santiago\"}}," +
                        "{\"id\":3, \"name\":\"Peru\", \"capital\":{\"id\":3, \"name\":\"Lima\"}}]"));

    }
}
