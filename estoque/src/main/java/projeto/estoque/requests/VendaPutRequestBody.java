package projeto.estoque.requests;

import lombok.Data;

@Data
public class VendaPutRequestBody {
	private Long id;
	private int tipo;
	private int qtdVenda;
	private String data;
}
