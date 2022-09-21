package com.minsait.JunitControllers.repositories;

import com.minsait.JunitControllers.models.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BancoRepository extends JpaRepository<Banco,Long> {
    /*List<Banco> findAll();
    Banco findById(Long id);
    void update(Banco banco);*/

}
