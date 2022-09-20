package com.minsait.JunitControllers;

import com.minsait.JunitControllers.models.Banco;
import com.minsait.JunitControllers.models.Cuenta;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Datos {
    public static final List<Banco> BANCOS = Arrays.asList(
            new Banco(1L,"BBVA",100),
            new Banco(2L,"Santander",90),
            new Banco(3L,"HCBC",80)
    );

    public static final Banco BANCO = new Banco(4L, "Banxico",70);

    public static final List<Cuenta> CUENTAS = Arrays.asList(
            new Cuenta(1L,"Ana",new BigDecimal("1500.50")),
            new Cuenta(2L,"Bob",new BigDecimal("1400.50")),
            new Cuenta(3L,"Carlos",new BigDecimal("1300.50"))
    );

    public static final Cuenta CUENTA = new Cuenta(4L,"Elias",new BigDecimal("1200.50"));


}
