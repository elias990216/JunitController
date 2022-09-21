package com.minsait.JunitControllers;

import com.minsait.JunitControllers.exceptions.DineroInsuficienteException;
import com.minsait.JunitControllers.models.Banco;
import com.minsait.JunitControllers.models.Cuenta;
import com.minsait.JunitControllers.repositories.BancoRepository;
import com.minsait.JunitControllers.repositories.CuentaRepository;
import com.minsait.JunitControllers.services.CuentaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class JunitControllersApplicationTests {

    Cuenta cuenta;
    Banco banco;

    @BeforeEach
    void setUp() {
        this.cuenta = new Cuenta();
        this.cuenta.setId(10L);
        this.cuenta.setPersona("PersonaTest");
        this.cuenta.setSaldo(new BigDecimal(10));
        this.banco = new Banco();
        this.banco.setId(10L);
        this.banco.setNombre("Banco");
        this.banco.setNombre(this.banco.getNombre() + "test");
        this.banco.setTotalTransferencias(0);
    }

    @Mock
    BancoRepository bancoRepository;

    @Mock
    CuentaRepository cuentaRepository;

    @InjectMocks
    CuentaServiceImpl service;


    @Test
    void testFindCuentaById() {
        when(cuentaRepository.findById(anyLong())).thenReturn(Optional.ofNullable(Datos.CUENTAS.get(0)));
        assertEquals("Ana", service.findById(1L).getPersona());
    }

    @Test
    void testRevisarTotalTrans() {
        when(bancoRepository.findById(anyLong())).thenReturn(Optional.of(Datos.BANCO));
        assertEquals(Datos.BANCO.getTotalTransferencias(), service.revisarTotalTransferencias(4L));
    }

    @Test
    void testRevisarSaldo() {
        when(cuentaRepository.findById(anyLong())).thenReturn(Optional.of(Datos.CUENTA));
        assertEquals(Datos.CUENTA.getSaldo(), service.revisarSaldo(4L));
    }

    @Test
    void testTransferir() {
        when(cuentaRepository.findById(1L)).thenReturn(Optional.ofNullable(Datos.CUENTAS.get(0)));
        when(cuentaRepository.findById(2L)).thenReturn(Optional.ofNullable(Datos.CUENTAS.get(1)));
        when(bancoRepository.findById(1L)).thenReturn(Optional.ofNullable(Datos.BANCOS.get(0)));
        service.transferir(1L, 2L, new BigDecimal(150), 1L);
        assertAll(
                () -> assertEquals(Datos.BANCOS.get(0).getTotalTransferencias(), 101),
                () -> assertEquals(Datos.CUENTAS.get(0).getSaldo(), new BigDecimal("1350.50")),
                () -> assertEquals(Datos.CUENTAS.get(1).getSaldo(), new BigDecimal("1550.50"))
        );
    }

    @Test
    void testDoThrowTransferir() {
        doThrow(DineroInsuficienteException.class).when(cuentaRepository).findById(anyLong());
        Cuenta cuentaOrigen = Datos.CUENTA;
        cuentaOrigen.setId(5L);
        cuentaOrigen.setPersona("Elias Muñoz");
        cuentaOrigen.setSaldo(new BigDecimal("0"));
        assertThrows(
                DineroInsuficienteException.class,
                () -> service.transferir(
                        cuentaOrigen.getId(),
                        Datos.CUENTAS.get(0).getId(),
                        new BigDecimal(100),
                        Datos.BANCOS.get(0).getId()
                )
        );
    }

    @Test
    void testExceptionRetirar() {
        Exception e = assertThrows(DineroInsuficienteException.class, () -> {
            cuenta.retirar(new BigDecimal(100));
        });
        assertEquals(
                "Dinero Insuficiente en la cuenta.",
                e.getMessage()
        );
    }

    @Test
    void testMain(){
        String[] arguments = {};
        JunitControllersApplication.main(arguments);
    }


}
