package com.minsait.JunitControllers.repositories;

import com.minsait.JunitControllers.models.Banco;

import java.util.List;

public interface BancoRepository {
    List<Banco> findAll();
    Banco findById(Long id);
    void update(Banco banco);
}
