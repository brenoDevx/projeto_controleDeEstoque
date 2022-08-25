package projeto.estoque.controller;

import java.util.List;

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

import lombok.RequiredArgsConstructor;
import projeto.estoque.domain.CompraVazilhame;
import projeto.estoque.requests.CompraPostRequestBody;
import projeto.estoque.requests.CompraPutRequestBody;
import projeto.estoque.service.CompraVazilhameService;

@RestController
@RequestMapping("compraVazilhame")
@RequiredArgsConstructor
public class CompraVazilhameController {
	private final CompraVazilhameService compraVazilhameService;

    @GetMapping
    public ResponseEntity<List<CompraVazilhame>> list(){
        return ResponseEntity.ok(compraVazilhameService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CompraVazilhame> findById(@PathVariable long id){
        return ResponseEntity.ok(compraVazilhameService.findByIdOrThrowBadRequestException(id));
    }
    
    @GetMapping(path = "/tipo")
    public ResponseEntity<List<CompraVazilhame>> findByTipo(@RequestParam int tipo) {
        return ResponseEntity.ok(compraVazilhameService.findByTipo(tipo));
    }
    
    @PostMapping
    public ResponseEntity<CompraVazilhame> save(@RequestBody CompraPostRequestBody compraPostRequestBody){
    	return new ResponseEntity<> (compraVazilhameService.save(compraPostRequestBody), HttpStatus.CREATED);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
    	compraVazilhameService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody CompraPutRequestBody compraPutRequestBody){
    	compraVazilhameService.replace(compraPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
