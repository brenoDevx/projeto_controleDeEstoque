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
import projeto.estoque.domain.VendaGlp;
import projeto.estoque.requests.VendaPostRequestBody;
import projeto.estoque.requests.VendaPutRequestBody;
import projeto.estoque.service.VendaGlpService;


@RestController
@RequestMapping("vendaGlp")
@RequiredArgsConstructor
public class VendaGlpController {
	private final VendaGlpService vendaGlpService;

    @GetMapping
    public ResponseEntity<List<VendaGlp>> list(){
        return ResponseEntity.ok(vendaGlpService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<VendaGlp> findById(@PathVariable long id){
        return ResponseEntity.ok(vendaGlpService.findByIdOrThrowBadRequestException(id));
    }
    
    @GetMapping(path = "/tipo")
    public ResponseEntity<List<VendaGlp>> findByTipo(@RequestParam int tipo) {
        return ResponseEntity.ok(vendaGlpService.findByTipo(tipo));
    }
    
    @PostMapping
    public ResponseEntity<VendaGlp> save(@RequestBody VendaPostRequestBody vendaPostRequestBody){
    	return new ResponseEntity<> (vendaGlpService.save(vendaPostRequestBody), HttpStatus.CREATED);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
    	vendaGlpService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody VendaPutRequestBody vendaPutRequestBody){
    	vendaGlpService.replace(vendaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
