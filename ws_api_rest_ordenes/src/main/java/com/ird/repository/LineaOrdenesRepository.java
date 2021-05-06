package com.ird.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ird.entity.LineaOrden;

@Repository
public interface LineaOrdenesRepository extends JpaRepository<LineaOrden, Long> {

}
