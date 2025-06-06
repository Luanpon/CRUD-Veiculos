package com.luan.veiculos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import com.luan.veiculos.model.Veiculo;
import com.luan.veiculos.service.VeiculoService;
import com.luan.veiculos.repository.VeiculoRepository;
import com.luan.veiculos.dto.VeiculoDTO;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VeiculoServiceTest {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @BeforeEach
    void setUp() {
        veiculoRepository.deleteAll();
    }

    @Test
    @Order(1)
    void deveSalvarVeiculo() {
    	
        VeiculoDTO veiculo = new VeiculoDTO();
        veiculo.setMarca("Toyota");
        veiculo.setModelo("Corolla");
        veiculo.setAnoFabricacao(2020);
        veiculo.setPlaca("XYZ-1234");
        veiculo.setQuilometragem(1000);

        Veiculo salvo = veiculoService.salvar(veiculo);

        assertNotNull(salvo.getId());
        assertEquals("Toyota", salvo.getMarca());
        assertEquals("Corolla", salvo.getModelo());
        assertEquals(2020, salvo.getAnoFabricacao());
        assertEquals("XYZ-1234", salvo.getPlaca());
        assertEquals(1000, salvo.getQuilometragem());
        
    }

    @Test
    @Order(2)
    void deveListarTodosVeiculos() {
    	
        VeiculoDTO v1 = new VeiculoDTO();
        v1.setMarca("Ford");
        v1.setModelo("Ka");
        v1.setAnoFabricacao(2019);
        v1.setPlaca("AAA-1111");
        v1.setQuilometragem(3000);
        
        
        VeiculoDTO v2 = new VeiculoDTO();
        v2.setMarca("Chevrolet");
        v2.setModelo("Onix");
        v2.setAnoFabricacao(2021);
        v2.setPlaca("BBB-2222");
        v2.setQuilometragem(1500);
        
        veiculoService.salvar(v1);
        veiculoService.salvar(v2);

        List<Veiculo> lista = veiculoService.listarTodos();
        assertEquals(2, lista.size());
        
    }

    @Test
    @Order(3)
    void deveBuscarVeiculoPorId() {
        
    	VeiculoDTO veiculo = new VeiculoDTO();
    	veiculo.setMarca("Fiat");
    	veiculo.setModelo("Uno");
    	veiculo.setAnoFabricacao(2015);
    	veiculo.setPlaca("CCC-3333");
    	veiculo.setQuilometragem(1000);
    	
    	
        Veiculo salvo = veiculoService.salvar(veiculo);

        Veiculo encontrado = veiculoService.buscarPorId(salvo.getId());
        assertEquals("Fiat", encontrado.getMarca());
        assertEquals("Uno", encontrado.getModelo());
        
    }

    @Test
    @Order(4)
    void deveDeletarVeiculo() {
    	
		VeiculoDTO veiculo = new VeiculoDTO();
		veiculo.setMarca("VW");
    	veiculo.setModelo("Gol");
    	veiculo.setAnoFabricacao(2018);
    	veiculo.setPlaca("DDD-4444");
    	veiculo.setQuilometragem(6000);
		
	    Veiculo salvo = veiculoService.salvar(veiculo);
		
	    veiculoService.deletar(salvo.getId());
		
	    try {
	    	Veiculo encontrado = veiculoService.buscarPorId(salvo.getId());
	    	assertNull(encontrado.getId());
	    }catch(ResponseStatusException e) {
	    	ResponseStatusException e2 = new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado");
	    	assertEquals(e.getStatusCode(), e2.getStatusCode());
	    	assertEquals(e.getReason(), e2.getReason());
	    }
       
    }
    
}
