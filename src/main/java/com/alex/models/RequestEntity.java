package com.alex.models;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "requests")
public class RequestEntity implements Serializable {
	
	private static final long serialVersionUID = 7440971557838537124L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code",length = 40)
    private String code;

    @Column(name = "descripcion",length = 40)
    private String description;

    @Column(name = "summary",length = 40)
    private String summary;

    @ManyToOne
    @JoinColumn(name = "empleado_id", referencedColumnName = "id")
    private EmployeeEntity employeeEntity;

	public RequestEntity() {
		super();
	}

	public RequestEntity(Long id, String code, String description, String summary, EmployeeEntity employeeEntity) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.summary = summary;
		this.employeeEntity = employeeEntity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public EmployeeEntity getEmployeeEntity() {
		return employeeEntity;
	}

	public void setEmployeeEntity(EmployeeEntity employeeEntity) {
		this.employeeEntity = employeeEntity;
	}
    
}
