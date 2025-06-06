
function ListaVeiculos({ veiculos, onEditar, onExcluir }) {
  return (
    <div className="lista-cards">
      {veiculos.map((veiculo) => (
        <div key={veiculo.id} className="card">
          <p className="id">id: {veiculo.id}</p>
          <h3>{veiculo.marca} {veiculo.modelo}</h3>
          <p><strong>Ano:</strong> {veiculo.anoFabricacao}</p>
          <p><strong>Placa:</strong> {veiculo.placa}</p>
          <p><strong>Quilometragem:</strong> {veiculo.quilometragem} km</p>
          <div className="botoes">
            <button className="botao" onClick={() => onEditar(veiculo)}>Editar</button>
            <button onClick={() => onExcluir(veiculo.id)} className="botao-excluir">Excluir</button>
          </div>
        </div>
      ))}
    </div>
  );
}

export default ListaVeiculos;