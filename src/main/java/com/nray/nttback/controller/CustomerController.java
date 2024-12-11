package com.nray.nttback.controller;

import com.nray.nttback.dto.CustomerDTO;
import com.nray.nttback.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customer")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<CustomerDTO> getByTypeAndId(@RequestParam String type, @RequestParam String number) {

        logger.info("Request received to get customer with type: {} and number: {}", type, number);

        if (type == null || type.isEmpty() || number == null || number.isEmpty()) {
            logger.warn("Invalid parameters: type or number is null or empty.");
            return ResponseEntity.badRequest().build();
        }

        try {
            CustomerDTO customer = service.getByTypeAndId(type, number);

            if (customer == null) {
                logger.warn("Customer not found for type: {} and number: {}", type, number);
                return ResponseEntity.notFound().build();
            }

            logger.info("Customer found: {}", customer);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            logger.error("An error occurred while fetching customer with type: {} and number: {}", type, number, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
