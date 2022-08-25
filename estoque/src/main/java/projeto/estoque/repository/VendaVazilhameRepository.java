package projeto.estoque.repository;
 import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.estoque.domain.VendaVazilhame;
 
public interface VendaVazilhameRepository extends JpaRepository<VendaVazilhame, Long>{
	List<VendaVazilhame> findByTipo(int tipo);
}
