package edu.poly.shop.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.poly.shop.domain.Product;

@Repository
public interface ProductReponsitory extends JpaRepository<Product, Long>{

	Page<Product> findByNameContaining(String name, Pageable pageable);

	List<Product> findByNameContaining(String name);

}
