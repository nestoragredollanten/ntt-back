package com.nray.nttback.service.impl;

import com.nray.nttback.dto.CustomerDTO;
import com.nray.nttback.service.CustomerService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class CustomerServiceImplTest {

    private final CustomerService service = new CustomerServiceImpl();

    @Test
    void getByTypeAndId_customerFound() {
        CustomerDTO customer = service.getByTypeAndId("C", "23445322");
        assertNotNull(customer);
        assertEquals("Juan", customer.getFirstName());
        assertEquals("23445322", customer.getDocumentNumber());
    }

    @Test
    void getByTypeAndId_customerNoFound1() {
        CustomerDTO customer = service.getByTypeAndId("P", "23445322");
        assertNull(customer);
    }

    @Test
    void getByTypeAndId_customerNoFound2() {
        CustomerDTO customer = service.getByTypeAndId("", "23445322");
        assertNull(customer);
    }

    @Test
    void getByTypeAndId_customerNoFound3() {
        CustomerDTO customer = service.getByTypeAndId("C", "23445321");
        assertNull(customer);
    }

    @Test
    void getByTypeAndId_customerNoFound4() {
        CustomerDTO customer = service.getByTypeAndId("C", "");
        assertNull(customer);
    }

    @Test
    void getByTypeAndId_customerNoFound5() {
        CustomerDTO customer = service.getByTypeAndId("", "");
        assertNull(customer);
    }

    @Test
    void getByTypeAndId_customerNoFound6() {
        CustomerDTO customer = service.getByTypeAndId("", "23445321");
        assertNull(customer);
    }
}