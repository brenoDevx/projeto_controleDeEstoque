package projeto.estoque.requests;

import lombok.Data;

@Data
public class VendaPostRequestBody {
	private int tipo;
	private int qtdVenda;
	private String data;
}
