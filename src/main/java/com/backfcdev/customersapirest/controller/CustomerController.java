package com.backfcdev.customersapirest.controller;

import com.backfcdev.customersapirest.model.Customer;
import com.backfcdev.customersapirest.model.dto.CustomerDTO;
import com.backfcdev.customersapirest.service.ICustomerService;
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
    ResponseEntity<List<CustomerDTO>> findAll(){
        return ResponseEntity.ok(customerService.findAll()
                .stream()
                .map(this::convertToDto)
                .toList());
    }

    @PostMapping
    ResponseEntity<CustomerDTO> save(@RequestBody CustomerDTO dto){
        Customer customer = customerService.save(convertToEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(convertToDto(customer));
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerDTO> findById(@PathVariable long id){
        return ResponseEntity.ok(convertToDto(customerService.findById(id)));
    }

    @PutMapping("/{id}")
    ResponseEntity<CustomerDTO> update(@PathVariable long id, @RequestBody CustomerDTO dto){
        Customer customer = customerService.update(id, convertToEntity(dto));
        return ResponseEntity.ok(convertToDto(customer));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id){
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }


    public Customer convertToEntity(CustomerDTO dto){
        return mapper.map(dto, Customer.class);
    }

    public CustomerDTO convertToDto(Customer entity){
        return mapper.map(entity, CustomerDTO.class);
    }

}
