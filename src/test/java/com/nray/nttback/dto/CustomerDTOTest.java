package com.nray.nttback.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDTOTest {

    @Test
    void testCustomerDTOBuilder() {
        CustomerDTO customer = CustomerDTO.builder()
                .documentType("Cédula de ciudadanía")
                .documentNumber("23445322")
                .firstName("Juan")
                .middleName("Pepito")
                .lastName("Perez")
                .secondLastName("Prado")
                .phone("1234567890")
                .address("Carrera 10")
                .cityOfResidence("Popayán")
                .build();

        assertNotNull(customer);
        assertEquals("Cédula de ciudadanía", customer.getDocumentType());
        assertEquals("23445322", customer.getDocumentNumber());
        assertEquals("Juan", customer.getFirstName());
        assertEquals("Pepito", customer.getMiddleName());
        assertEquals("Perez", customer.getLastName());
        assertEquals("Prado", customer.getSecondLastName());
        assertEquals("1234567890", customer.getPhone());
        assertEquals("Carrera 10", customer.getAddress());
        assertEquals("Popayán", customer.getCityOfResidence());


        // Add assertions for other fields as needed
    }

    @Test
    void testToStringMethod(){
        CustomerDTO customer = CustomerDTO.builder()
                .documentType("Cédula")
                .documentNumber("1234567")
                .firstName("Test")
                .lastName("User")
                .build();

        assertNotNull(customer.toString());
    }
}