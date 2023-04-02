package com.backfcdev.customersapirest.controller;

import com.backfcdev.customersapirest.model.Customer;
import com.backfcdev.customersapirest.model.dto.CustomerDTO;
import com.backfcdev.customersapirest.service.ICustomerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final ICustomerService customerService;
    private final ModelMapper mapper;

    @GetMapping
    ResponseEntity<List<Customer>> findAll(){
        return ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> saveCustomer(@RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(customerService.save(customerDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<Customer> findCustomerById(@PathVariable long id){
        return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody CustomerDTO customerDTO){
        return new ResponseEntity<>(customerService.update(id, customerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Long> deleteCustomerById(@PathVariable Long id){
        return new ResponseEntity<>(customerService.deleteById(id), HttpStatus.NO_CONTENT);
    }


    public Customer convertToEntity(CustomerDTO dto){
        return mapper.map(dto, Customer.class);
    }

    public CustomerDTO convertToDto(Customer entity){
        return mapper.map(entity, CustomerDTO.class);
    }

}
