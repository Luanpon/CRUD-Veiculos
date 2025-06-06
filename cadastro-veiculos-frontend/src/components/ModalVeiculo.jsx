import React, { useState, useEffect } from "react";
import "../styles/ModalVeiculo.css";

function ModalVeiculo({ veiculo, onClose, onSubmit }) {
  const [dados, setDados] = useState(veiculo);

  useEffect(() => {
    setDados(veiculo);
  }, [veiculo]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setDados({ ...dados, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit(dados);
  };

  return (
    <div className="modal-overlay">
      <div className="modal">
        <h2>{dados.id ? "Editar Veículo" : "Cadastrar Veículo"}</h2>
        <form onSubmit={handleSubmit}>
          <input name="marca" placeholder="Marca" value={dados.marca} onChange={handleChange} required />
          <input name="modelo" placeholder="Modelo" value={dados.modelo} onChange={handleChange} required />
          <input name="anoFabricacao" placeholder="Ano de Fabricação" value={dados.anoFabricacao} onChange={handleChange} required />
          <input name="placa" placeholder="Placa" value={dados.placa} onChange={handleChange} required />
          <input name="quilometragem" placeholder="Quilometragem" value={dados.quilometragem} onChange={handleChange} required />
          <div className="botoes-form">
            <button type="submit">Salvar</button>
            <button type="button" onClick={onClose} className="botao-excluir">Cancelar</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default ModalVeiculo;