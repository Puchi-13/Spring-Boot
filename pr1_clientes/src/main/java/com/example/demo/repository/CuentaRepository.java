package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cuenta;

@Repository
@Transactional
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    
    @Query(value = "SELECT cu FROM Cliente c JOIN c.listaCuentas cu WHERE c.id =:idcliente")
    public List<Cuenta> getCuentasByCliente(@Param("idcliente") Long idcliente);

}
