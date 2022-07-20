package edu.poly.shop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import edu.poly.shop.domain.Category;
import edu.poly.shop.respository.CategoryResponsitory;
import edu.poly.shop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	CategoryResponsitory categoryResponsitory;
	
	
	public CategoryServiceImpl(CategoryResponsitory categoryResponsitory) {
		
		this.categoryResponsitory = categoryResponsitory;
	}
	
	
	@Override
	public Page<Category> findByNameContaining(String name, Pageable pageable) {
		return categoryResponsitory.findByNameContaining(name, pageable);
	}


	@Override
	public List<Category> findByNameContaining(String name) {
		return categoryResponsitory.findByNameContaining(name);
	}


	@Override
	public <S extends Category> S save(S entity) {
		return categoryResponsitory.save(entity);
	}

	@Override
	public List<Category> findAll() {
		return categoryResponsitory.findAll();
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return categoryResponsitory.findAll(pageable);
	}

	@Override
	public List<Category> findAll(Sort sort) {
		return categoryResponsitory.findAll(sort);
	}

	@Override
	public List<Category> findAllById(Iterable<Long> ids) {
		return categoryResponsitory.findAllById(ids);
	}

	@Override
	public Optional<Category> findById(Long id) {
		return categoryResponsitory.findById(id);
	}

	@Override
	public <S extends Category> List<S> saveAll(Iterable<S> entities) {
		return categoryResponsitory.saveAll(entities);
	}

	@Override
	public void flush() {
		categoryResponsitory.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return categoryResponsitory.existsById(id);
	}

	@Override
	public <S extends Category> S saveAndFlush(S entity) {
		return categoryResponsitory.saveAndFlush(entity);
	}

	@Override
	public <S extends Category> List<S> saveAllAndFlush(Iterable<S> entities) {
		return categoryResponsitory.saveAllAndFlush(entities);
	}

	@Override
	public void deleteAllInBatch(Iterable<Category> entities) {
		categoryResponsitory.deleteAllInBatch(entities);
	}

	@Override
	public <S extends Category> boolean exists(Example<S> example) {
		return categoryResponsitory.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		categoryResponsitory.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		categoryResponsitory.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Category entity) {
		categoryResponsitory.delete(entity);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		categoryResponsitory.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		categoryResponsitory.deleteAllInBatch();
	}

	@Override
	public void deleteAll(Iterable<? extends Category> entities) {
		categoryResponsitory.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		categoryResponsitory.deleteAll();
	}

	@Override
	public Category getById(Long id) {
		return categoryResponsitory.getById(id);
	}
	
}
