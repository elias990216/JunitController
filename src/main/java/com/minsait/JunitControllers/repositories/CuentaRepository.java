package com.minsait.JunitControllers.repositories;


import com.minsait.JunitControllers.models.Cuenta;

import java.util.List;

public interface CuentaRepository {
    List<Cuenta> findAll();
    Cuenta findById(Long id);
    void update(Cuenta banco);
}
