package com.example.Shopping.website.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shopping.website.Entity.Address;
import com.example.Shopping.website.Service.AddressService;

@RestController
@RequestMapping("/api/addresses")
@CrossOrigin(origins = "*")
public class AddressController {
    private final AddressService service;

    public AddressController(AddressService service) {

        this.service = service;
    }

    @GetMapping("/{username}")
    public List<Address> list(@PathVariable String username) {

        return service.list(username);
    }

    @PostMapping
    public Address create(@RequestBody Address address) {

        return service.create(address);
    }

    @PutMapping("/{id}")
    public Address update(@PathVariable Long id, @RequestBody Address address) {
        return service.update(id, address);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        service.delete(id);
    }

    @PutMapping("/{id}/default")
    public Address setDefault(@PathVariable Long id) {

        return service.setDefault(id);
    }
}


