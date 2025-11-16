package com.a3ppj.A3deSexta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.a3ppj.A3deSexta.model.Boleto;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, String> {

    // Buscar pelo hash (que é a chave primaria)
    @Override
    Optional<Boleto> findById(String hash);

    // Buscar pelo código de barras
    Optional<Boleto> findByCodigoBarras(String codigoBarras);

}