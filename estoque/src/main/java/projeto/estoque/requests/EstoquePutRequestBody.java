package projeto.estoque.requests;

import lombok.Data;

@Data
public class EstoquePutRequestBody {
	private Long id;
	private int tipo;
	private int qtdCheio;
	private int qtdVazio;
}
