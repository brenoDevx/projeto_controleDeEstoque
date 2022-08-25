package projeto.estoque.requests;

import lombok.Data;

@Data
public class CompraPutRequestBody {
	private Long id;
	private int tipo;
	private int qtdCompra;
	private String data;
}
