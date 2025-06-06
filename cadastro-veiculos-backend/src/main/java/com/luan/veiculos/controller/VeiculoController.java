package com.luan.veiculos.controller;

import com.luan.veiculos.model.Veiculo;
import com.luan.veiculos.service.VeiculoService;
import com.luan.veiculos.dto.VeiculoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
@Tag(name = "Veículos", description = "métodos para gerenciamento de veículos")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
    @Operation(summary = "Listar todos os veículos")
    public List<Veiculo> listarTodos() {
        return veiculoService.listarTodos();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar veículo pelo ID")
    public Veiculo buscarPorId(@PathVariable Long id){
    	return veiculoService.buscarPorId(id);
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo veículo")
    public Veiculo salvar(@RequestBody VeiculoDTO veiculoDTO) {
        return veiculoService.salvar(veiculoDTO);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar os dados de um veículo")
    public Veiculo atualizar(@PathVariable Long id, @RequestBody VeiculoDTO veiculoDTO){
    	return veiculoService.atualizar(id, veiculoDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um veículo")
    public void deletar(@PathVariable Long id) {
        veiculoService.deletar(id);
    }
}