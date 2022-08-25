package projeto.estoque.service;
import java.util.List; 
import projeto.estoque.domain.Estoque;
import projeto.estoque.exception.BadRequestException;
import projeto.estoque.mapper.EstoqueMapper;
import projeto.estoque.repository.EstoqueRepository;
import projeto.estoque.requests.EstoquePostRequestBody;
import projeto.estoque.requests.EstoquePutRequestBody;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EstoqueService {
	private final EstoqueRepository estoqueRepository;
	
	public List<Estoque> listAll(){
		return estoqueRepository.findAll();
	}
	
	public Estoque findByIdOrThrowBadRequestException(long id) {
        return estoqueRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Botijao not Found"));
	}
	
	public Estoque findByTipo(int tipo) {
        return estoqueRepository.findByTipo(tipo);
    }
	@Transactional
	public Estoque save(EstoquePostRequestBody estoquePostRequestBody) {
		
		return estoqueRepository.save(EstoqueMapper.INSTANCE.toEstoque(estoquePostRequestBody));
	}

	public void delete(long id) {
		estoqueRepository.delete(findByIdOrThrowBadRequestException(id));
	}

	public void replace(EstoquePutRequestBody estoquePutRequestBody) {
		 Estoque savedEstoque = findByIdOrThrowBadRequestException(estoquePutRequestBody.getId());
	        Estoque estoque = EstoqueMapper.INSTANCE.toEstoque(estoquePutRequestBody);
	        estoque.setId(savedEstoque.getId());

	        estoqueRepository.save(estoque);
		
	}
}
