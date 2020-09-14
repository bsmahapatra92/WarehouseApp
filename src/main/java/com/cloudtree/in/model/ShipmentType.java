package com.cloudtree.in.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="shipment_type_tab")
public class ShipmentType {
	
	@Id
	@GeneratedValue
	@Column(name="ship_id_col")
	private Integer id;
	@Column(name="ship_mode_col")
	private String shipmentMode;
	@Column(name="ship_code_col")
	private String shipmentCode;
	@Column(name="enable_shipment")
	private String enableShipment;
	@Column(name="shipment_grade")
	private String shipmentGrade;
	@Column(name="description")
	private String description;
}
