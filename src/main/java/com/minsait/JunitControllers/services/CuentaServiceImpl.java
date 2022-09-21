package com.minsait.JunitControllers.services;

import com.minsait.JunitControllers.models.Banco;
import com.minsait.JunitControllers.models.Cuenta;
import com.minsait.JunitControllers.repositories.BancoRepository;
import com.minsait.JunitControllers.repositories.CuentaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService{

    private CuentaRepository cuentaRepository;
    private BancoRepository bancoRepository;

    @Override
    public Cuenta findById(Long id) {
        return cuentaRepository.findById(id).orElseThrow();
    }

    @Override
    public Integer revisarTotalTransferencias(Long bancoId) {
        Banco banco = bancoRepository.findById(bancoId).orElseThrow();
        return banco.getTotalTransferencias();
    }

    @Override
    public BigDecimal revisarSaldo(Long cuentaId) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId).orElseThrow();
        return cuenta.getSaldo();
    }

    @Override
    public void transferir(Long cuentaOrigen, Long cuentaDestino, BigDecimal monto, Long bancoId) {
        Cuenta cuentaOrign = cuentaRepository.findById(cuentaOrigen).orElseThrow();
        cuentaOrign.retirar(monto);
        cuentaRepository.save(cuentaOrign);

        Cuenta cuentaDestno = cuentaRepository.findById(cuentaDestino).orElseThrow();
        cuentaDestno.depositar(monto);
        cuentaRepository.save(cuentaDestno);

        Banco banco = bancoRepository.findById(bancoId).orElseThrow();
        Integer totalTransferencias = banco.getTotalTransferencias();
        banco.setTotalTransferencias(++totalTransferencias);
        bancoRepository.save(banco);
    }
}
