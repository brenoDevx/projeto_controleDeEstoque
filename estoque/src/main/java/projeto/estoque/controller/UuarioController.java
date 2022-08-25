package projeto.estoque.controller;

import projeto.estoque.domain.Usuario;
import projeto.estoque.requests.UsuarioPostRequestBody;
import projeto.estoque.requests.UsuarioPutRequestBody;
import lombok.RequiredArgsConstructor;
import projeto.estoque.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("usuario")
@RequiredArgsConstructor
public class UuarioController {
	
	private final UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> list(){
			return ResponseEntity.ok(usuarioService.listAll());
	    }

	@GetMapping(path = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable long id){
	     return ResponseEntity.ok(usuarioService.findByIdOrThrowBadRequestException(id));
	    
	}
	
	@PostMapping
    public ResponseEntity<Usuario> save(@RequestBody UsuarioPostRequestBody usuarioPostRequestBody){
    	return new ResponseEntity<> (usuarioService.save(usuarioPostRequestBody), HttpStatus.CREATED);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
    	usuarioService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody UsuarioPutRequestBody usuarioPutRequestBody){
    	usuarioService.replace(usuarioPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}




