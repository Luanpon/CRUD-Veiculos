package com.luan.veiculos.service;

import com.luan.veiculos.model.Veiculo;
import com.luan.veiculos.repository.VeiculoRepository;
import com.luan.veiculos.dto.VeiculoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VeiculoService {
	
    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> listarTodos() { return veiculoRepository.findAll(); }
    
    public Veiculo buscarPorId(Long id) { 
    	return veiculoRepository.findById(id)
    			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado")); 
	}
    
    public Veiculo salvar(VeiculoDTO veiculoDTO) {
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setAnoFabricacao(veiculoDTO.getAnoFabricacao());
        veiculo.setPlaca(veiculoDTO.getPlaca());
        veiculo.setQuilometragem(veiculoDTO.getQuilometragem());
        return veiculoRepository.save(veiculo);
    }
    
    public Veiculo atualizar(Long id, VeiculoDTO veiculoDTO) {
    	
    	Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado"));

        veiculo.setMarca(veiculoDTO.getMarca());
        veiculo.setModelo(veiculoDTO.getModelo());
        veiculo.setAnoFabricacao(veiculoDTO.getAnoFabricacao());
        veiculo.setPlaca(veiculoDTO.getPlaca());
        veiculo.setQuilometragem(veiculoDTO.getQuilometragem());

        return veiculoRepository.save(veiculo);

    }
    
    public void deletar(Long id) { 
    	if (!veiculoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Veículo não encontrado");
        }
    	veiculoRepository.deleteById(id); 
	}
    
}