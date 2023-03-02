package com.company.customerdataservice.repository;

import com.company.customerdataservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTests {

    @Autowired
    CustomerRepository customerRepository;

    @Before
    public void setup() throws Exception {
        customerRepository.deleteAll();
    }

    @Test
    public void shouldAddCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Billy");
        customer.setLastName("Bob");
        customer.setEmail("billyBob@gmail.com");
        customer.setCompany("Pair LLC");
        customer.setPhone("816-223-7932");
        customer.setAddress1("4923 Cherry Hill Rd");
        customer.setAddress2("5903 Maple St");
        customer.setCity("Philadelphia");
        customer.setState("Pennsylvania");
        customer.setPostalCode("19092");
        customer.setCountry("United States of America");

        customerRepository.save(customer);

        Optional<Customer> customer1 = customerRepository.findById(customer.getCustomerId());
        assertEquals(customer1.get(), customer);
    }

    @Test
    public void shouldUpdateCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Witherspoon");
        customer.setEmail("jWither@gmail.com");
        customer.setCompany("Grape LLC");
        customer.setPhone("483-693-2966");
        customer.setAddress1("6928 Cherry Hill Rd");
        customer.setAddress2("751 Maple St");
        customer.setCity("Chicago");
        customer.setState("Illinois");
        customer.setPostalCode("60609");
        customer.setCountry("United States of America");
        customerRepository.save(customer);

        customer.setFirstName("Mike");
        customerRepository.save(customer);

        Optional<Customer> customer1 = customerRepository.findById(customer.getCustomerId());
        assertEquals(customer1.get(), customer);
    }

    @Test
    public void shouldDeleteCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Tony");
        customer.setLastName("Balony");
        customer.setEmail("tonyB@gmail.com");
        customer.setCompany("Orange LLC");
        customer.setPhone("193-685-6838");
        customer.setAddress1("9652 Cherry Hill Rd");
        customer.setAddress2("19472 Maple St");
        customer.setCity("San Francisco");
        customer.setState("California");
        customer.setPostalCode("94114");
        customer.setCountry("United States of America");
        customerRepository.save(customer);

        customerRepository.deleteById(customer.getCustomerId());

        Optional<Customer> customer1 = customerRepository.findById(customer.getCustomerId());
        assertFalse(customer1.isPresent());
    }

    @Test
    public void shouldGetCustomerById() {
        Customer customer = new Customer();
        customer.setFirstName("Junior");
        customer.setLastName("Sanchez");
        customer.setEmail("junSanchez@gmail.com");
        customer.setCompany("Blueberry LLC");
        customer.setPhone("857-495-1945");
        customer.setAddress1("95813 Cherry Hill Rd");
        customer.setAddress2("58921 Maple St");
        customer.setCity("Denver");
        customer.setState("Colorado");
        customer.setPostalCode("80110");
        customer.setCountry("United States of America");

        customerRepository.save(customer);

        Optional<Customer> customer1 = customerRepository.findById(customer.getCustomerId());
        assertEquals(customer1.get(), customer);
    }

    @Test
    public void shouldGetCustomerByState() {
        Customer customer = new Customer();
        customer.setFirstName("Steve");
        customer.setLastName("Rodgers");
        customer.setEmail("captainAmerica@gmail.com");
        customer.setCompany("Avengers");
        customer.setPhone("482-596-0285");
        customer.setAddress1("29737 Cherry Hill Rd");
        customer.setAddress2("95733 Maple St");
        customer.setCity("Ithaca");
        customer.setState("New York");
        customer.setPostalCode("14850");
        customer.setCountry("United States of America");

        customerRepository.save(customer);

        List<Customer> customer1 = customerRepository.findByState(customer.getState());
        assertEquals(customer1.size(), 1);
    }

}
