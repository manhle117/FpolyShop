package edu.poly.shop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import edu.poly.shop.domain.Account;
import edu.poly.shop.respository.AccountResponsitory;
import edu.poly.shop.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountResponsitory accountResponsitory;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Account login(String username,String password) {
		Optional<Account> optExist = findById(username);
		
		if (optExist.isPresent() && bCryptPasswordEncoder.matches(password, optExist.get().getPassword())) {
			
			optExist.get().setPassword("");
			
			return optExist.get();
		}
		return null;
	}
	
	@Override
	public <S extends Account> S save(S entity) {
		Optional<Account> optExist = findById(entity.getUsername());
		if (optExist.isPresent()) {
			if (StringUtils.isEmpty(entity.getPassword())) {
				entity.setPassword(optExist.get().getPassword());
			}else {
				entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
			}
		}else {
			entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
		}
	
		return accountResponsitory.save(entity);
	}

	@Override
	public <S extends Account> Optional<S> findOne(Example<S> example) {
		return accountResponsitory.findOne(example);
	}

	@Override
	public List<Account> findAll() {
		return accountResponsitory.findAll();
	}

	@Override
	public Page<Account> findAll(Pageable pageable) {
		return accountResponsitory.findAll(pageable);
	}

	@Override
	public List<Account> findAll(Sort sort) {
		return accountResponsitory.findAll(sort);
	}

	@Override
	public List<Account> findAllById(Iterable<String> ids) {
		return accountResponsitory.findAllById(ids);
	}

	@Override
	public Optional<Account> findById(String id) {
		return accountResponsitory.findById(id);
	}

	@Override
	public <S extends Account> List<S> saveAll(Iterable<S> entities) {
		return accountResponsitory.saveAll(entities);
	}

	@Override
	public void flush() {
		accountResponsitory.flush();
	}

	@Override
	public boolean existsById(String id) {
		return accountResponsitory.existsById(id);
	}

	@Override
	public <S extends Account> S saveAndFlush(S entity) {
		return accountResponsitory.saveAndFlush(entity);
	}

	@Override
	public <S extends Account> List<S> saveAllAndFlush(Iterable<S> entities) {
		return accountResponsitory.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
		return accountResponsitory.findAll(example, pageable);
	}

	@Override
	public void deleteInBatch(Iterable<Account> entities) {
		accountResponsitory.deleteInBatch(entities);
	}

	@Override
	public <S extends Account> long count(Example<S> example) {
		return accountResponsitory.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Account> entities) {
		accountResponsitory.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return accountResponsitory.count();
	}

	@Override
	public <S extends Account> boolean exists(Example<S> example) {
		return accountResponsitory.exists(example);
	}

	@Override
	public void deleteById(String id) {
		accountResponsitory.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		accountResponsitory.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Account entity) {
		accountResponsitory.delete(entity);
	}

	@Override
	public <S extends Account, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return accountResponsitory.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		accountResponsitory.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		accountResponsitory.deleteAllInBatch();
	}

	@Override
	public Account getOne(String id) {
		return accountResponsitory.getOne(id);
	}

	@Override
	public void deleteAll(Iterable<? extends Account> entities) {
		accountResponsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		accountResponsitory.deleteAll();
	}

	@Override
	public Account getById(String id) {
		return accountResponsitory.getById(id);
	}

	@Override
	public Account getReferenceById(String id) {
		return accountResponsitory.getReferenceById(id);
	}

	@Override
	public <S extends Account> List<S> findAll(Example<S> example) {
		return accountResponsitory.findAll(example);
	}

	@Override
	public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
		return accountResponsitory.findAll(example, sort);
	}
	
	
}
