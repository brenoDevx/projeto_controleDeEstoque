package projeto.estoque.repository;

import projeto.estoque.domain.CompraGlp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraGlpRepository extends JpaRepository<CompraGlp, Long>{
	List<CompraGlp> findByTipo(int tipo);
}
