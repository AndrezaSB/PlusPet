package br.com.pluspet.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractService<T, I, R extends JpaRepository<T, I>> {

	@Autowired
	protected R repository;

	public List<T> findAll() {
		return repository.findAll();
	}

	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}
}
