package com.nray.nttback.service.impl;

import com.nray.nttback.dto.CustomerDTO;
import com.nray.nttback.service.CustomerService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CustomerServiceImpl implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Override
    public CustomerDTO getByTypeAndId(String type, String number) {

        logger.info("Received request to get customer with type: {} and number: {}", type, number);

        if (!type.isBlank() && !number.isBlank() && "C".equals(type) && "23445322".equals(number)) {
            logger.info("Customer found with type: {} and number: {}", type, number);

            return CustomerDTO.builder()
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
        }

        logger.warn("No customer found for type: {} and number: {}", type, number);

        return null;
    }
}
