package com.minsait.JunitControllers.services;

import com.minsait.JunitControllers.models.Banco;
import com.minsait.JunitControllers.models.Cuenta;
import com.minsait.JunitControllers.repositories.BancoRepository;
import com.minsait.JunitControllers.repositories.CuentaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CuentaServiceImpl implements CuentaService{

    private CuentaRepository cuentaRepository;
    private BancoRepository bancoRepository;

    @Override
    public Cuenta findById(Long id) {
        return cuentaRepository.findById(id);
    }

    @Override
    public Integer revisarTotalTransferencias(Long bancoId) {
        Banco banco = bancoRepository.findById(bancoId);
        return banco.getTotalTransferencias();
    }

    @Override
    public BigDecimal revisarSaldo(Long cuentaId) {
        Cuenta cuenta = cuentaRepository.findById(cuentaId);
        return cuenta.getSaldo();
    }

    @Override
    public void transferir(Long cuentaOrigen, Long cuentaDestino, BigDecimal monto, Long bancoId) {
        Cuenta cuentaOrign = cuentaRepository.findById(cuentaOrigen);
        cuentaOrign.retirar(monto);
        cuentaRepository.update(cuentaOrign);

        Cuenta cuentaDestno = cuentaRepository.findById(cuentaDestino);
        cuentaDestno.depositar(monto);
        cuentaRepository.update(cuentaDestno);

        Banco banco = bancoRepository.findById(bancoId);
        Integer totalTransferencias = banco.getTotalTransferencias();
        banco.setTotalTransferencias(++totalTransferencias);
        bancoRepository.update(banco);
    }
}
