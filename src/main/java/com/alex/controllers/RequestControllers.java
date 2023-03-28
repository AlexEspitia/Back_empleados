package com.alex.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alex.models.RequestEntity;
import com.alex.services.RequestServices;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class RequestControllers {
	
	@Autowired
    private RequestServices requestServices;
    
    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/requests")
    public ResponseEntity<List<RequestEntity>> getRequest(){
        return new ResponseEntity<List<RequestEntity>>(requestServices.getRequest(), HttpStatus.OK);
    }
    @GetMapping("/requests/{id}")
    public ResponseEntity<RequestEntity> getRequestEntityById(@PathVariable("id") Long id ){
        return new ResponseEntity<RequestEntity>(requestServices.getRequestEntityById(id), HttpStatus.OK);
    }
    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/requests")
    public ResponseEntity<RequestEntity> postRequest(@RequestBody RequestEntity request){
        return new ResponseEntity<RequestEntity>(requestServices.createRequest(request), HttpStatus.OK);
    }
    //@Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PutMapping("/requests/{id}")
    public ResponseEntity<RequestEntity> updateRequest(@PathVariable("id") Long id, @RequestBody RequestEntity request){
       return new ResponseEntity<RequestEntity>(requestServices.updateRequest(id, request), HttpStatus.OK);
    }
    @DeleteMapping("/requests/{id}")
    public ResponseEntity<RequestEntity> deleteRequest(@PathVariable("id") Long id){
        return new ResponseEntity<>(requestServices.deleteRequest(id),HttpStatus.OK);
    }
}
