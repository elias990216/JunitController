package com.minsait.JunitControllers.services;

import com.minsait.JunitControllers.models.Cuenta;

import java.math.BigDecimal;

public interface CuentaService {
    Cuenta findById(Long id);
    Integer revisarTotalTransferencias(Long bancoId);
    BigDecimal revisarSaldo(Long cuentaId);
    void transferir(Long cuentaOrigen, Long cuentaDestino, BigDecimal monto ,Long bancoId);
}
