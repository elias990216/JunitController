package com.minsait.JunitControllers.repositories;


import com.minsait.JunitControllers.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    /*List<Cuenta> findAll();
    Cuenta findById(Long id);
    void update(Cuenta banco);*/

}
