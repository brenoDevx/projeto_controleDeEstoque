package projeto.estoque.service;

import java.util.List;

import projeto.estoque.domain.Usuario;
import projeto.estoque.exception.BadRequestException;
import projeto.estoque.mapper.UsuarioMapper;
import projeto.estoque.repository.UsuarioRepository;
import projeto.estoque.requests.UsuarioPostRequestBody;
import projeto.estoque.requests.UsuarioPutRequestBody;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository = null;;
	
	
	public List<Usuario> listAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario findByIdOrThrowBadRequestException(long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Usuario not Found"));
	}
	
	@Transactional
	public Usuario save(UsuarioPostRequestBody usuarioPostRequestBody) {
		
		return usuarioRepository.save(UsuarioMapper.INSTANCE.toUsuario(usuarioPostRequestBody));
	}

	public void delete(long id) {
		usuarioRepository.delete(findByIdOrThrowBadRequestException(id));
	}

	public void replace(UsuarioPutRequestBody usuarioPutRequestBody) {
		 Usuario savedUsuario = findByIdOrThrowBadRequestException(usuarioPutRequestBody.getId());
	        Usuario usuario = UsuarioMapper.INSTANCE.toUsuario(usuarioPutRequestBody);
	        usuario.setId(savedUsuario.getId());

	        usuarioRepository.save(usuario);
		
	}
}
