package com.a3ppj.A3deSexta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a3ppj.A3deSexta.model.Boleto;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, String> {
}