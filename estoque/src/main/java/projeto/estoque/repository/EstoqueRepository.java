package projeto.estoque.repository;
import projeto.estoque.domain.Estoque; 
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long>{
	Estoque findByTipo(int tipo);
	
}
