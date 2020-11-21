package com.fox.david.AddressBook.data.DAO;

import com.fox.david.AddressBook.data.repositories.AddressRepository;
import org.springframework.stereotype.Component;

@Component
public class AddressDAO {
    AddressRepository addressRepository;

    public AddressDAO(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public String delete(Long id) {
        if (addressRepository.findById(id).isPresent()) {
            addressRepository.deleteById(id);
            return "deleted address with id " + id;
        }
        return "could not find address with id " + id;
    }
}
