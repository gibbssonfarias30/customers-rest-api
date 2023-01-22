package com.backfcdev.customersapirest.model.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String direction;

}
