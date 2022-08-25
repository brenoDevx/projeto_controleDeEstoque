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
import projeto.estoque.domain.CompraGlp;
import projeto.estoque.requests.CompraPostRequestBody;
import projeto.estoque.requests.CompraPutRequestBody;
import projeto.estoque.service.CompraGlpService;

@RestController
@RequestMapping("compraGlp")
@RequiredArgsConstructor
public class CompraGlpController {

	private final CompraGlpService compraGlpService;

    @GetMapping
    public ResponseEntity<List<CompraGlp>> list(){
        return ResponseEntity.ok(compraGlpService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CompraGlp> findById(@PathVariable long id){
        return ResponseEntity.ok(compraGlpService.findByIdOrThrowBadRequestException(id));
    }
    
    @GetMapping(path = "/tipo")
    public ResponseEntity<List<CompraGlp>> findByTipo(@RequestParam int tipo) {
        return ResponseEntity.ok(compraGlpService.findByTipo(tipo));
    }
    
    @PostMapping
    public ResponseEntity<CompraGlp> save(@RequestBody CompraPostRequestBody compraPostRequestBody){
    	return new ResponseEntity<> (compraGlpService.save(compraPostRequestBody), HttpStatus.CREATED);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
    	compraGlpService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody CompraPutRequestBody compraPutRequestBody){
    	compraGlpService.replace(compraPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
	
}
