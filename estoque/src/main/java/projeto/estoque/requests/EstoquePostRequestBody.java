package projeto.estoque.requests;

import lombok.Data;

@Data
public class EstoquePostRequestBody {
	private int tipo;
	private int qtdCheio;
	private int qtdVazio;
}
