package com.fox.david.AddressBook.data.DAO;

import com.fox.david.AddressBook.data.Persistance.Address;
import com.fox.david.AddressBook.data.repositories.AddressRepository;
import org.springframework.stereotype.Component;

@Component
public class AddressDAO {
    AddressRepository addressRepository;

    public AddressDAO(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public String updateAddress(Long id, String street, String city, String state, String postalCode) {
        if (addressRepository.findById(id).isPresent()) {
            Address address = addressRepository.findById(id).get();
            if (street != null && !street.isEmpty()) {
                address.setStreet(street);
            }
            if (city != null && !city.isEmpty()) {
                address.setCity(city);
            }
            if (state != null && !state.isEmpty()) {
                address.setState(state);
            }
            if (postalCode != null && !postalCode.isEmpty()) {
                address.setPostalCode(postalCode);
            }
            return "successfully updated address";
        }
        return "could not find given address";
    }

    public String delete(Long id) {
        if (addressRepository.findById(id).isPresent()) {
            addressRepository.deleteById(id);
            return "deleted address with id " + id;
        }
        return "could not find address with id " + id;
    }
}
