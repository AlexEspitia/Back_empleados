package com.alex.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "employees")
public class EmployeeEntity implements Serializable{
	
	private static final long serialVersionUID = 8019008430459743777L;

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id")
	 private Long id;

	 @Column(name = "name",length = 40)
	 private String name;

	 @Column(name = "salary")
	 private Integer salary;

	 @CreationTimestamp
	 @Column(name = "fecha_ingreso")
	 private Date fecha_ingreso;

	 @Column(name = "state")
	 private Integer state;

	public EmployeeEntity() {
		super();
	}

	public EmployeeEntity(Long id, String name, Integer salary, Date fecha_ingreso, Integer state) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.fecha_ingreso = fecha_ingreso;
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	 
}
