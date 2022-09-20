package com.minsait.JunitControllers;

import com.minsait.JunitControllers.exceptions.DineroInsuficienteException;
import com.minsait.JunitControllers.models.Banco;
import com.minsait.JunitControllers.models.Cuenta;
import com.minsait.JunitControllers.repositories.BancoRepository;
import com.minsait.JunitControllers.repositories.CuentaRepository;
import com.minsait.JunitControllers.services.CuentaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class JunitControllersApplicationTests {

	Cuenta cuenta;
	Banco banco;

	@BeforeEach
	void setUp(){
		this.cuenta = new Cuenta();
		this.cuenta.setId(10L);
		this.cuenta.setPersona("PersonaTest");
		this.cuenta.setSaldo(new BigDecimal(10));
		this.banco = new Banco();
		this.banco.setId(10L);
		this.banco.setNombre("Banco");
		this.banco.setNombre(this.banco.getNombre()+"test");
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
		when(cuentaRepository.findById(anyLong())).thenReturn(Datos.CUENTAS.get(0));
		assertEquals("Ana",service.findById(1L).getPersona());
	}

	@Test
	void testRevisarTotalTrans(){
		when(bancoRepository.findById(anyLong())).thenReturn(Datos.BANCO);
		assertEquals(Datos.BANCO.getTotalTransferencias(),service.revisarTotalTransferencias(4L));
	}

	@Test
	void testRevisarSaldo(){
		when(cuentaRepository.findById(anyLong())).thenReturn(Datos.CUENTA);
		assertEquals(Datos.CUENTA.getSaldo(),service.revisarSaldo(4L));
	}

	@Test
	void testTransferir(){
		when(cuentaRepository.findById(anyLong())).thenReturn(Datos.CUENTAS.get(0));
		when(cuentaRepository.findById(anyLong())).thenReturn(Datos.CUENTAS.get(1));
		when(bancoRepository.findById(anyLong())).thenReturn(Datos.BANCOS.get(0));
		service.transferir(1L,2L,new BigDecimal(100),1L);
		assertEquals(Datos.CUENTAS.get(0).getSaldo(),new BigDecimal("1500.50"));
	}

	@Test
	void testDoThrowTransferir(){
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



}
