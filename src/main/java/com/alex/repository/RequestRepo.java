package com.alex.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alex.models.RequestEntity;

@Repository
public interface RequestRepo extends JpaRepository <RequestEntity, Long> {

}
