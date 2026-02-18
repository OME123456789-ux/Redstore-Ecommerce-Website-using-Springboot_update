package com.example.Shopping.website.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Shopping.website.Entity.Address;
import com.example.Shopping.website.Repositry.AddressRepository;

@Service
public class AddressService {
    private final AddressRepository repository;

    public AddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public List<Address> list(String username) {
        return repository.findByUsernameOrderByCreatedAtDesc(username);
    }

    public Address create(Address address) {
        if (address.isDefault()) {
            unsetDefault(address.getUsername());
        }
        return repository.save(address);
    }

    public Address update(Long id, Address updated) {
        Address existing = repository.findById(id).orElseThrow();
        existing.setType(updated.getType());
        existing.setFullName(updated.getFullName());
        existing.setAddressLine1(updated.getAddressLine1());
        existing.setAddressLine2(updated.getAddressLine2());
        existing.setCity(updated.getCity());
        existing.setState(updated.getState());
        existing.setPincode(updated.getPincode());
        existing.setPhone(updated.getPhone());
        if (updated.isDefault()) {
            unsetDefault(existing.getUsername());
        }
        existing.setDefault(updated.isDefault());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Address setDefault(Long id) {
        Address ex = repository.findById(id).orElseThrow();
        unsetDefault(ex.getUsername());
        ex.setDefault(true);
        return repository.save(ex);
    }

    private void unsetDefault(String username) {
        repository.findByUsernameOrderByCreatedAtDesc(username)
                .forEach(a -> { if (a.isDefault()) { a.setDefault(false); repository.save(a); } });
    }
}


