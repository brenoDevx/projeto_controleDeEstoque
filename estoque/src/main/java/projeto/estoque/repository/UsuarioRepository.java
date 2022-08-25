package projeto.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import projeto.estoque.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
}
