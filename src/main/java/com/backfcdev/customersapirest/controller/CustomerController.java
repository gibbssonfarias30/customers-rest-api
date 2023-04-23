package com.backfcdev.customersapirest.controller;

import com.backfcdev.customersapirest.model.Customer;
import com.backfcdev.customersapirest.model.dto.CustomerDTO;
import com.backfcdev.customersapirest.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@Slf4j
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
    ResponseEntity<Void> save(@Validated @RequestBody CustomerDTO dto){
        Customer customer = customerService.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(customer.getId()).toUri();
        log.info("customer saved: {}", customer);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    ResponseEntity<CustomerDTO> findById(@PathVariable long id){
        return ResponseEntity.ok(convertToDto(customerService.findById(id)));
    }

    @PutMapping("/{id}")
    ResponseEntity<CustomerDTO> update(@PathVariable long id, @Validated @RequestBody CustomerDTO dto){
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
