package com.cloudtree.in.service;

import java.util.List;


import java.util.Optional;

import com.cloudtree.in.model.Uom;

public interface IUomService {
	Integer saveUom(Uom u);
	void updateUom(Uom u);
	void deleteUom(Integer id);
	Optional<Uom> getOneUom(Integer id);
	List<Uom> getAllUoms();
	boolean isUomExist(Integer id);
	
//	Map<Integer,String> getUomIdAndModel();
//	
//	Page<Uom> getAllUoms(Pageable pageable);
	
}
