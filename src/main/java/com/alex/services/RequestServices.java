package com.alex.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.alex.models.RequestEntity;
import com.alex.repository.RequestRepo;

@Service
public class RequestServices {
	
	@Autowired
    private RequestRepo requestRepo;

     /**
     * @return creacion servicio consultar solicitud
     * @author Alexander Espitia <paespitia6@outlook.com> 2023-03-06
     */
     public List<RequestEntity> getRequest(){
        return requestRepo.findAll();
    }
    /**
     * @return creacion servicio Crear solicitud
     * @author Alexander Espitia <paespitia6@outlook.com> 2023-03-06
     */
    public RequestEntity createRequest(RequestEntity requestEntity){
        return requestRepo.save(requestEntity);
    }
    /**
     * @return creacion servicio listar by id
     * @author Alexander Espitia <paespitia6@outlook.com> 2023-03-06
     */
    public RequestEntity getRequestEntityById(Long id){
        return requestRepo.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Solicitud no encontrada con el id %d ", id)));
    }
    /**
     * @return creacion servicio actualizar solicitud
     * @author Alexander Espitia <paespitia6@outlook.com> 2023-03-06
     */
    public RequestEntity updateRequest(Long id, RequestEntity request){
        Optional<RequestEntity> result = requestRepo.findById(id);
        RequestEntity requestUpdate = new RequestEntity();
        if(result.isPresent()){
        	requestUpdate.setId(result.get().getId());
            requestUpdate.setCode(request.getCode());
            requestUpdate.setDescription(request.getDescription());
            requestUpdate.setSummary(request.getSummary());
            requestUpdate.setEmployeeEntity(request.getEmployeeEntity());
            return requestRepo.save(requestUpdate);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("La solicitud ID %d no existe", id));
        }
    }
    @Transactional
	public RequestEntity deleteRequest(Long id) {
		RequestEntity result = requestRepo.findById(id).get();
        if (result!=null){
        	requestRepo.deleteById(id); 
            return result;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("La solicitud Id %d no existe", id));
        }
    }
}
