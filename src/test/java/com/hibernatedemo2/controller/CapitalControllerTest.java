package com.hibernatedemo2.controller;
import static org.mockito.Mockito.*;

import com.hibernatedemo2.Models.Capital;
import com.hibernatedemo2.Repositories.CapitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Jacaranda Perez
 * Date: 2021-04-22
 * Project: HibernateDemo2
 */
@SpringBootTest
@AutoConfigureMockMvc
public class CapitalControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CapitalRepository mockRepository;

    @BeforeEach
    public void init(){
        Capital c1= new Capital( 1L, "Buenos Aires");
        Capital c2= new Capital(2L, "Santiago");
        Capital c3= new Capital(3L, "Lima");
        when(mockRepository.findById(1L)).thenReturn(Optional.of(c1));
        when(mockRepository.findAll()).thenReturn(Arrays.asList(c1, c2, c3));

    }

    @Test
    public void wrongURLShouldNotBeFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/notnow").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void AddCapitalTest()throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/capital/add?name=Paris").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Capital sparad")));
    }

    @Test
    public void getByIdTest1() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/capital/getById?id=1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":1, \"name\":\"Buenos Aires\"}"));
    }

    @Test
    public void getall() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/capital/all").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":1, \"name\":\"Buenos Aires\"}," +
                        "{\"id\":2, \"name\":\"Santiago\"}," +
                        "{\"id\":3, \"name\":\"Lima\"}]"));

/* For JAVA 15
        """
                        [{"id": 1, "capitalName": "Buenos Aires"},
                        {"id": 2, "capitalName": "Santiago"},
                        {"id": 3, "capitalName": "Lima"}]"""
*/
    }


}
