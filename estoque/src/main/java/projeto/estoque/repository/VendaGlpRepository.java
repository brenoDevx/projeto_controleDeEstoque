package projeto.estoque.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.estoque.domain.VendaGlp;

public interface VendaGlpRepository extends JpaRepository<VendaGlp, Long>{
	List<VendaGlp> findByTipo(int tipo);
}
