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
public class CompraVazilhame {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int tipo;
	private int qtdCompra;
	private String data;
}
