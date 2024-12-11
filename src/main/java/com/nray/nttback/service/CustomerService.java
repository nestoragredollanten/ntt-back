package com.nray.nttback.service;

import com.nray.nttback.dto.CustomerDTO;

public interface CustomerService {
    CustomerDTO getByTypeAndId(String type, String number);
}
