package projeto.estoque.requests;

import lombok.Data;
@Data
public class CompraPostRequestBody {
	private int tipo;
	private int qtdCompra;
	private String data;
}
