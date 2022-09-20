package com.minsait.JunitControllers;

import com.minsait.JunitControllers.repositories.BancoRepository;
import com.minsait.JunitControllers.repositories.CuentaRepository;
import com.minsait.JunitControllers.services.CuentaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class JunitControllersApplicationTests {

	@Mock
	BancoRepository bancoRepository;

	@Mock
	CuentaRepository cuentaRepository;

	@InjectMocks
	CuentaServiceImpl service;


	@Test
	void testFindCuentaById() {
		when(cuentaRepository.findById(anyLong())).thenReturn(Datos.CUENTA);
		assertEquals("Elias",service.findById(4L).getPersona());
	}

	@Test
	void testRevisarTotalTrans(){
		when(bancoRepository.findById(anyLong())).thenReturn(Datos.BANCO);
		assertEquals(Datos.BANCO.getTotalTransferencias(),service.revisarTotalTransferencias(4L));
	}

}
