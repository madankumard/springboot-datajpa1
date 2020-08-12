package com.mk.springdata.product.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Product {

	// The following is for using id with mysql table providing the autoincrement value
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	/*
	 * //The following is used by hibernate to generate the id in provided table
	 * 
	 * @TableGenerator(name = "product_gen", table = "id_gen", pkColumnName =
	 * "gen_name", valueColumnName = "gen_val", allocationSize = 100)
	 * 
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.TABLE, generator = "product_gen")
	 * private int id;
	 */

	/*
	 * //The following is used during custom generator
	 * 
	 * @Id
	 * 
	 * @GenericGenerator(name = "product_id", strategy =
	 * "com.mk.springdata.product.idgenerators.CustomRandomIDGenerator")
	 * 
	 * @GeneratedValue(generator = "product_id") private int id;
	 */

	private String name;

	@Column(name = "description")
	private String desc;

	private Double price;

	public Product() {
	}

	public Product(String name, String desc, Double price) {
		super();
		this.name = name;
		this.desc = desc;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", desc=" + desc + ", price=" + price + "]";
	}

}
