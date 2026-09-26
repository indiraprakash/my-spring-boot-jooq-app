package com.example.myspringbootapp.controller;

import com.example.myspringbootapp.model.Customer;
import com.example.myspringbootapp.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void getsCustomerById() throws Exception {
        when(customerService.getCustomerById(1L)).thenReturn(java.util.Optional.of(
                new Customer(1L, "John", "Doe", LocalDate.of(1990, 5, 21))
        ));

        mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.dateOfBirth").value("1990-05-21"));
    }

    @Test
    void searchesCustomersByFirstName() throws Exception {
        when(customerService.getCustomersByFirstName("John")).thenReturn(List.of(
                new Customer(1L, "John", "Doe", LocalDate.of(1990, 5, 21))
        ));

        mockMvc.perform(get("/api/customers/search/by-firstname")
                        .param("firstName", "John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].lastName").value("Doe"));
    }
}
