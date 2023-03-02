package com.company.customerdataservice.controller;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.company.customerdataservice.model.Customer;
import com.company.customerdataservice.repository.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    private List<Customer> customerList;

    @Test
    public void shouldCreateCustomerOnPostRequest() throws Exception {
        Customer inputCustomer = new Customer();
        inputCustomer.setFirstName("Billy");
        inputCustomer.setLastName("Bob");
        inputCustomer.setEmail("billyBob@gmail.com");
        inputCustomer.setCompany("Pair LLC");
        inputCustomer.setPhone("816-223-7932");
        inputCustomer.setAddress1("4923 Cherry Hill Rd");
        inputCustomer.setAddress2("5903 Maple St");
        inputCustomer.setCity("Philadelphia");
        inputCustomer.setState("Pennsylvania");
        inputCustomer.setPostalCode("19092");
        inputCustomer.setCountry("United States of America");

        String inputJson = objectMapper.writeValueAsString(inputCustomer);

        mockMvc.perform(
                post("/customer")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void shouldUpdateCustomerOnPutRequest() throws Exception {
        Customer inputCustomer = new Customer();
        inputCustomer.setFirstName("Billy");
        inputCustomer.setLastName("Bob");
        inputCustomer.setEmail("billyBob@gmail.com");
        inputCustomer.setCompany("Pair LLC");
        inputCustomer.setPhone("816-223-7932");
        inputCustomer.setAddress1("4923 Cherry Hill Rd");
        inputCustomer.setAddress2("5903 Maple St");
        inputCustomer.setCity("Philadelphia");
        inputCustomer.setState("Pennsylvania");
        inputCustomer.setPostalCode("19092");
        inputCustomer.setCountry("United States of America");

        String inputJson = objectMapper.writeValueAsString(inputCustomer);

        mockMvc.perform(
                        put("/customer")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    public void shouldDeleteCustomerById() throws Exception{
        mockMvc.perform(delete("/customer/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnCustomerByIdAndOkStatus() throws Exception{
        Customer outputCustomer = new Customer();
        outputCustomer.setCustomerId(6);
        outputCustomer.setFirstName("Billy");
        outputCustomer.setLastName("Bob");
        outputCustomer.setEmail("billyBob@gmail.com");
        outputCustomer.setCompany("Pair LLC");
        outputCustomer.setPhone("816-223-7932");
        outputCustomer.setAddress1("4923 Cherry Hill Rd");
        outputCustomer.setAddress2("5903 Maple St");
        outputCustomer.setCity("Philadelphia");
        outputCustomer.setState("Pennsylvania");
        outputCustomer.setPostalCode("19092");
        outputCustomer.setCountry("United States of America");

        String outputJson = objectMapper.writeValueAsString(outputCustomer);

        mockMvc.perform(get("/customer/6"))
                        .andDo(print())
                        .andExpect(status().isOk());
    }

    @Test
    public void shouldGetCustomersByState() throws Exception{
        Customer outputCustomer = new Customer();
        outputCustomer.setFirstName("Billy");
        outputCustomer.setLastName("Bob");
        outputCustomer.setEmail("billyBob@gmail.com");
        outputCustomer.setCompany("Pair LLC");
        outputCustomer.setPhone("816-223-7932");
        outputCustomer.setAddress1("4923 Cherry Hill Rd");
        outputCustomer.setAddress2("5903 Maple St");
        outputCustomer.setCity("Philadelphia");
        outputCustomer.setState("Pennsylvania");
        outputCustomer.setPostalCode("19092");
        outputCustomer.setCountry("United States of America");

        String outputJson = objectMapper.writeValueAsString(outputCustomer);

        mockMvc.perform(get("/customer/state/Pennsylvania"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
