const API_URL = "http://localhost:8080/veiculos";

export async function buscarTodosVeiculos() {
  const response = await fetch(API_URL);
  return response.json();
}

export async function buscarVeiculoPorId(id) {
  const response = await fetch(`${API_URL}/${id}`);
  if (response.ok) {
    return response.json();
  } else {
    return null;
  }
}

export async function criarVeiculo(veiculo) {
  const response = await fetch(API_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(veiculo)
  });
  return response.json();
}

export async function atualizarVeiculo(id, veiculo) {
  const response = await fetch(`${API_URL}/${id}`, {
    method: "PUT",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(veiculo)
  });
  return response.json();
}

export async function excluirVeiculo(id) {
  await fetch(`${API_URL}/${id}`, { method: "DELETE" });
}