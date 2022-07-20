package edu.poly.shop.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.poly.shop.domain.Account;

public interface AccountResponsitory extends JpaRepository<Account, String>{

}
