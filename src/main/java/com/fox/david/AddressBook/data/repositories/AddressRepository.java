package com.fox.david.AddressBook.data.repositories;

import com.fox.david.AddressBook.data.Persistance.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address,Long> {
}
