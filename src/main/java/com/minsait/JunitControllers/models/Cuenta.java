package com.minsait.JunitControllers.models;

import com.minsait.JunitControllers.exceptions.DineroInsuficienteException;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class Cuenta {
    private Long id;
    private String persona;
    private BigDecimal saldo;

    public void retirar(BigDecimal monto) {
        BigDecimal nuevoSaldo = this.saldo.subtract(monto);
        if(nuevoSaldo.compareTo(BigDecimal.ZERO) < 0){
            throw new DineroInsuficienteException("Dinero Insuficiente en la cuenta.");
        }
        this.saldo = nuevoSaldo;
    }

    public void depositar(BigDecimal monto){
        this.saldo = this.saldo.add(monto);
    }
}
