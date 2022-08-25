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
import projeto.estoque.domain.VendaVazilhame;
import projeto.estoque.requests.VendaPostRequestBody;
import projeto.estoque.requests.VendaPutRequestBody;
import projeto.estoque.service.VendaVazilhameService;


@RestController
@RequestMapping("vendaVazilhame")
@RequiredArgsConstructor
public class VendaVazilhameController {
	private final VendaVazilhameService vendaVazilhameService;

    @GetMapping
    public ResponseEntity<List<VendaVazilhame>> list(){
        return ResponseEntity.ok(vendaVazilhameService.listAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<VendaVazilhame> findById(@PathVariable long id){
        return ResponseEntity.ok(vendaVazilhameService.findByIdOrThrowBadRequestException(id));
    }
    
    @GetMapping(path = "/tipo")
    public ResponseEntity<List<VendaVazilhame>> findByTipo(@RequestParam int tipo) {
        return ResponseEntity.ok(vendaVazilhameService.findByTipo(tipo));
    }
    
    @PostMapping
    public ResponseEntity<VendaVazilhame> save(@RequestBody VendaPostRequestBody vendaPostRequestBody){
    	return new ResponseEntity<> (vendaVazilhameService.save(vendaPostRequestBody), HttpStatus.CREATED);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
    	vendaVazilhameService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody VendaPutRequestBody vendaPutRequestBody){
    	vendaVazilhameService.replace(vendaPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
