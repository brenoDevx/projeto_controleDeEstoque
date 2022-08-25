package projeto.estoque.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
@Builder
public class Estoque{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int tipo;
	private int qtdCheio;
	private int qtdVazio;
	
	
	public void compraGlp(int qtdCompra){
		this.qtdCheio += qtdCompra;
		this.qtdVazio -= qtdCompra;
	}
	
	public void vendaGlp(int qtdVenda){
		this.qtdCheio -= qtdVenda;
		this.qtdVazio += qtdVenda;
	}
	
	public void compraVazilhame(int qtdCompra){
		this.qtdVazio += qtdCompra;
	}
	
	public void vendaVazilhame(int qtdVenda){
		this.qtdVazio -= qtdVenda;
	}
	
	
}
