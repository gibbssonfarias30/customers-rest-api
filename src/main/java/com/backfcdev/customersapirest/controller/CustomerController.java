package com.backfcdev.customersapirest.controller;

import com.backfcdev.customersapirest.model.Customer;
import com.backfcdev.customersapirest.dto.CustomerDTO;
import com.backfcdev.customersapirest.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Customers", description = "Customer related operations")
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final ICustomerService customerService;
    private final ModelMapper mapper;


    @Operation(summary = "Get all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found books page")
    })
    @GetMapping
    ResponseEntity<List<CustomerDTO>> findAll(){
        return ResponseEntity.ok(customerService.findAll()
                .stream()
                .map(this::convertToDto)
                .toList());
    }


    @Operation(summary = "Create a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    @PostMapping
    ResponseEntity<Void> save(@Validated @RequestBody CustomerDTO dto){
        Customer customer = customerService.save(convertToEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(customer.getId()).toUri();
        log.info("customer saved: {}", customer);
        return ResponseEntity.created(location).build();
    }


    @Operation(summary = "Get customer by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("/{id}")
    ResponseEntity<CustomerDTO> findById(@PathVariable long id){
        return ResponseEntity.ok(convertToDto(customerService.findById(id)));
    }


    @Operation(summary = "Update an existing customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @PutMapping("/{id}")
    ResponseEntity<CustomerDTO> update(@PathVariable long id, @Validated @RequestBody CustomerDTO dto){
        Customer customer = customerService.update(id, convertToEntity(dto));
        return ResponseEntity.ok(convertToDto(customer));
    }


    @Operation(summary = "Delete an existing customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Customer successfully removed"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
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
