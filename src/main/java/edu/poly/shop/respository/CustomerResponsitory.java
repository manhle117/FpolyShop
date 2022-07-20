package edu.poly.shop.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.shop.domain.Customer;
import edu.poly.shop.service.CustomerService;

public interface CustomerResponsitory extends JpaRepository<Customer, Long> {

	List<Customer> findByNameContaining(String name);
	Page<Customer> findByNameContaining(String name, Pageable pageable);


}
