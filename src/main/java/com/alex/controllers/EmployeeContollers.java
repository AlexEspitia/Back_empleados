package com.alex.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.alex.models.EmployeeEntity;
import com.alex.services.EmployeeServices;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class EmployeeContollers {
	
	@Autowired
    private EmployeeServices employeeServices;
    
	@GetMapping("/employees")
    public ResponseEntity<List<EmployeeEntity>> getEmployees(){
    	return new ResponseEntity<List<EmployeeEntity>>(employeeServices.getEmployees(), HttpStatus.OK);
    } 
    
    /*@GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id ){
        return new ResponseEntity<EmployeeEntity>(employeeServices.getEmployeeEntityById(id), HttpStatus.OK);
    }*/
    @GetMapping("/employees/{id}")
	public ResponseEntity<?>  getEmployeeById(@PathVariable Long id) {
    	EmployeeEntity employee = null;
		Map<String, Object> response = new HashMap<>();
		try {
			employee = employeeServices.getEmployeeEntityById(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos ");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (employee == null) {
			response.put("mensaje", "El empleado id: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<EmployeeEntity>(employee, HttpStatus.OK) ;
	}
  
    
    @PostMapping("/employees")
    public ResponseEntity<EmployeeEntity> postEmployee(@RequestBody EmployeeEntity employee){
        return new ResponseEntity<EmployeeEntity>(employeeServices.createEmployee(employee), HttpStatus.OK);
    }
 
    @PutMapping("/employees/update/{id}")
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeEntity employee){
       return new ResponseEntity<EmployeeEntity>(employeeServices.updateEmployee(id, employee), HttpStatus.OK);
    }
    
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<EmployeeEntity> deleteEmployee(@PathVariable("id") Long id){
        return new ResponseEntity<>(employeeServices.deleteEmployee(id),HttpStatus.OK);
    }
}
