package com.cloudtree.in.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudtree.in.model.Uom;
import com.cloudtree.in.repo.UomRepository;
import com.cloudtree.in.service.IUomService;

@Service
public class UomServiceImpl implements IUomService {

	@Autowired
	private UomRepository repo;

	@Transactional
	public Integer saveUom(Uom u) {
		Integer id = repo.save(u).getId();
		return id;
	}

	@Transactional
	public void updateUom(Uom u) {
		repo.save(u);
	}


	@Transactional
	public void deleteUom(Integer id) {
		repo.deleteById(id);
	}


	@Transactional(readOnly = true)
	public Optional<Uom> getOneUom(Integer id) {
		Optional<Uom> opt = repo.findById(id);
		return opt;
	}


	@Transactional(readOnly = true)
	public List<Uom> getAllUoms() {
		List<Uom> list = repo.findAll();
		return list;
	}


	@Transactional(readOnly = true)
	public boolean isUomExist(Integer id) {
		boolean exist = repo.existsById(id);
		return exist;
	}

}
