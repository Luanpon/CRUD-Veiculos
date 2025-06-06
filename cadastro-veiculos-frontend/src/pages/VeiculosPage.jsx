import React, { useEffect, useState } from "react";
import { buscarTodosVeiculos, excluirVeiculo, criarVeiculo, atualizarVeiculo, buscarVeiculoPorId } from "../services/apiService";
import ListaVeiculos from "../components/ListaVeiculos";
import ModalVeiculo from "../components/ModalVeiculo";
import "../styles/ListaVeiculos.css";
import "../styles/CadastroVeiculo.css";

function VeiculosPage() {

  const [veiculos, setVeiculos] = useState([]);
  const [mostrarModal, setMostrarModal] = useState(false);
  const [veiculoSelecionado, setVeiculoSelecionado] = useState(null);
  const [idBusca, setIdBusca] = useState("");

  const carregarVeiculos = async () => {
    try{
      const dados = await buscarTodosVeiculos();
      setVeiculos(dados);
    }
    catch{}
  };

  useEffect(() => {
    carregarVeiculos();
  }, []);

  const handleExcluir = async (id) => {
    await excluirVeiculo(id);
    carregarVeiculos();
  };

  const abrirModal = (veiculo) => {
    if (veiculo) {
      setVeiculoSelecionado(veiculo);
    } else {
      setVeiculoSelecionado({
        marca: "",
        modelo: "",
        anoFabricacao: "",
        placa: "",
        quilometragem: ""
      });
    }
    setMostrarModal(true);
  };

  const fecharModal = () => {
    setMostrarModal(false);
    setVeiculoSelecionado(null);
  };

  const handleSubmit = async (veiculo) => {
    if (veiculo.id) {
      await atualizarVeiculo(veiculo.id, veiculo);
    } else {
      await criarVeiculo(veiculo);
    }
    fecharModal();
    carregarVeiculos();
  };

  const handleBuscarPorId = async () => {
    
    if (!idBusca) {
      carregarVeiculos();
      return;
    }
    
    try {
      const veiculo = await buscarVeiculoPorId(idBusca);
      if (veiculo) {
        setVeiculos([veiculo]);
        return;
      } 
    } catch{}

    setVeiculos([]);
    alert("Nenhum veículo encontrado com este ID.");

  };

  const handleLimparBusca = () => {
    setIdBusca("");
    carregarVeiculos();
  };

  return (
    <div className="container">
      <h1>Cadastro de Veículos</h1>
      <h3 className="autor">Desenvolvido por Luan Pontes</h3>
      <div className="botoes-topo">
        <button className="botao" onClick={() => abrirModal()}>Adicionar Veículo</button>
        <div className="busca-id">
          <input
            type="text"
            placeholder="Buscar por ID"
            value={idBusca}
            onChange={(e) => setIdBusca(e.target.value)}
          />
          <button className="botao" onClick={handleBuscarPorId}>Buscar</button>
          <button className="botao" onClick={handleLimparBusca}>Limpar Busca</button>
        </div>
      </div>
      <ListaVeiculos veiculos={veiculos} onEditar={abrirModal} onExcluir={handleExcluir} />
      {mostrarModal && (
        <ModalVeiculo veiculo={veiculoSelecionado} onClose={fecharModal} onSubmit={handleSubmit} />
      )}
    </div>
  );
}

export default VeiculosPage;