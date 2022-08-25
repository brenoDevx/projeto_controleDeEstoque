package projeto.estoque.repository;

import projeto.estoque.domain.CompraVazilhame;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
public interface CompraVazilhameRepository extends JpaRepository<CompraVazilhame, Long>{
	List<CompraVazilhame> findByTipo(int tipo);
}
