package com.alex.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.alex.models.EmployeeEntity;
import com.alex.repository.EmployeeRepo;

@Service
public class EmployeeServices {
	
	@Autowired
    private EmployeeRepo employeeRepo;

     /**
     * @return creacion servicio consultar empleados
     * @author Alexander Espitia <paespitia6@outlook.com> 2023-03-06
     */

    public List<EmployeeEntity> getEmployees() {
		return  employeeRepo.findAllEmployees();
	}
    /**
     * @return creacion servicio Crear empleado
     * @author Alexander Espitia <paespitia6@outlook.com> 2023-03-06
     */
    public EmployeeEntity createEmployee(EmployeeEntity employeeEntity){
        employeeEntity.setState(1);
        return employeeRepo.save(employeeEntity);
    }
    /**
     * @return creacion servicio listar by id
     * @author Alexander Espitia <paespitia6@outlook.com> 2023-03-06
     */
    public EmployeeEntity getEmployeeEntityById(Long id){
        return employeeRepo.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("empleado no encontrado con el id %d ", id)));
    }
    /**
     * @return creacion servicio actualizar empleado
     * @author Alexander Espitia <paespitia6@outlook.com> 2023-03-06
     */
    public EmployeeEntity updateEmployee(Long id, EmployeeEntity employee){
        Optional<EmployeeEntity> result = employeeRepo.findById(id);
        EmployeeEntity employeeUpdate = new EmployeeEntity();
        if(result.isPresent()){
        	employeeUpdate.setId(result.get().getId());
            employeeUpdate.setFecha_ingreso(employee.getFecha_ingreso());
            employeeUpdate.setName(employee.getName());
            employeeUpdate.setSalary(employee.getSalary());
            employeeUpdate.setState(employee.getState());
            return employeeRepo.save(employeeUpdate);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("El empleado ID %d no existe", id));
        }
    }
    /**
     * @return creacion servicio eliminar empleado(cambiar estado)
     * @author Alexander Espitia <paespitia6@outlook.com> 2023-03-06
     */
    public EmployeeEntity deleteEmployee(Long id) {
        EmployeeEntity result = employeeRepo.findById(id).get();
        if (result!=null){
            result.setState(2);
            return employeeRepo.save(result);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("El empleado Id %d no existe", id));
        }
    }
    public List<EmployeeEntity> findAll() {
        return (List<EmployeeEntity>) employeeRepo.findAll();
    }
}
