package com.alex.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alex.models.EmployeeEntity;

@Repository
public interface EmployeeRepo extends JpaRepository <EmployeeEntity, Long>{

	@Query("SELECT e FROM EmployeeEntity e WHERE  e.state=1 ORDER BY e.id ASC")
	public List<EmployeeEntity> findAllEmployees();

}
