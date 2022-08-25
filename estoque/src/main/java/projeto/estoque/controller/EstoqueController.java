package projeto.estoque.controller;

import projeto.estoque.domain.Estoque; 
import projeto.estoque.requests.EstoquePostRequestBody;
import projeto.estoque.requests.EstoquePutRequestBody;
import lombok.RequiredArgsConstructor;
import projeto.estoque.service.EstoqueService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("estoque")
@RequiredArgsConstructor
public class EstoqueController {
	private final EstoqueService estoqueService;

    @GetMapping
    public ResponseEntity<List<Estoque>> list(){
        return ResponseEntity.ok(estoqueService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Estoque> findById(@PathVariable long id){
        return ResponseEntity.ok(estoqueService.findByIdOrThrowBadRequestException(id));
    }
    
    @GetMapping(path = "/tipo")
    public ResponseEntity<Estoque> findByTipo(@RequestParam int tipo) {
        return ResponseEntity.ok(estoqueService.findByTipo(tipo));
    }
    
    @PostMapping
    public ResponseEntity<Estoque> save(@RequestBody EstoquePostRequestBody estoquePostRequestBody){
    	return new ResponseEntity<> (estoqueService.save(estoquePostRequestBody), HttpStatus.CREATED);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
    	estoqueService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody EstoquePutRequestBody estoquePutRequestBody){
    	estoqueService.replace(estoquePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


