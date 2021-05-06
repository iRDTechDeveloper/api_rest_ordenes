package com.ird.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ird.entity.Orden;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {

}
