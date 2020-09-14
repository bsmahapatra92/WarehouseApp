package com.cloudtree.in.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cloudtree.in.model.ShipmentType;
import com.cloudtree.in.repo.ShipmentTypeRepository;
import com.cloudtree.in.service.IShipmentTypeService;

@Service
public class ShipmentTypeServiceImpl implements IShipmentTypeService {
	@Autowired
	private ShipmentTypeRepository repo;
	
	
	@Transactional
	public Integer saveShipmentType(ShipmentType st) {
		Integer id = repo.save(st).getId();
		return id;
	}

	@Transactional
	public void updateShipmentType(ShipmentType st) {
		repo.save(st);

	}

	@Transactional
	public void deleteShipmentType(Integer id) {
		repo.deleteById(id);

	}

	@Transactional(readOnly = true)
	public Optional<ShipmentType> getOneShipmentType(Integer id) {
		Optional<ShipmentType> opt = repo.findById(id);
			return opt;
		
	}

	@Transactional(readOnly = true)
	public List<ShipmentType> getAllShipmentTypes() {
		 List<ShipmentType> list=repo.findAll();
		return list;
	}

	@Transactional(readOnly = true)
	public boolean isShipmentTypeExist(Integer id) {
		boolean exist = repo.existsById(id);
		return exist;
	}

}
