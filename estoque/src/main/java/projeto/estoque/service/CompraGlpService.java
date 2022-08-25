package projeto.estoque.service;

import java.time.LocalDateTime;
import java.util.List; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import projeto.estoque.domain.CompraGlp;
import projeto.estoque.domain.Estoque;
import projeto.estoque.exception.BadRequestException;
import projeto.estoque.mapper.CompraGlpMapper;
import projeto.estoque.repository.CompraGlpRepository;
import projeto.estoque.repository.EstoqueRepository;
import projeto.estoque.requests.CompraPostRequestBody;
import projeto.estoque.requests.CompraPutRequestBody;
import projeto.estoque.util.DateUtil;


@Service
@RequiredArgsConstructor
public class CompraGlpService {

	private final CompraGlpRepository compraGlpRepository;
	private final EstoqueRepository estoqueRepository;
	private final DateUtil dateUtil;
	
	public List<CompraGlp> listAll(){
		return compraGlpRepository.findAll();		
	}
	
	public CompraGlp findByIdOrThrowBadRequestException(long id) {
		return compraGlpRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("CompraGlp not Found"));
		
	}
	
	public List<CompraGlp> findByTipo(int tipo) {
        return compraGlpRepository.findByTipo(tipo);
    }
	
	@Transactional
	public CompraGlp save(CompraPostRequestBody compraPostRequestBody) {
		
		Estoque estoque = estoqueRepository.findByTipo(compraPostRequestBody.getTipo());
        estoque.compraGlp(compraPostRequestBody.getQtdCompra());
        estoqueRepository.save(estoque);
        compraPostRequestBody.setData(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
		return compraGlpRepository.save(CompraGlpMapper.INSTANCE.toCompraGlp(compraPostRequestBody));
	}

	public void delete(long id) {
		
		CompraGlp compraGlpDelete = findByIdOrThrowBadRequestException(id);
		Estoque estoque = estoqueRepository.findByTipo(compraGlpDelete.getTipo());
		estoque.vendaGlp(compraGlpDelete.getQtdCompra());
		estoqueRepository.save(estoque);
		compraGlpRepository.delete(findByIdOrThrowBadRequestException(id));
	
	}
	
	public void replace(CompraPutRequestBody compraPutRequestBody) {
		CompraGlp compraAntiga = findByIdOrThrowBadRequestException(compraPutRequestBody.getId());
		CompraGlp compra = CompraGlpMapper.INSTANCE.toCompraGlp(compraPutRequestBody);
        compra.setId(compraAntiga.getId());
        compra.setData(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        Estoque estoque = estoqueRepository.findByTipo(compra.getTipo());
        estoque.vendaGlp(compraAntiga.getQtdCompra());
        estoque.compraGlp(compra.getQtdCompra());
        estoqueRepository.save(estoque);
        compraGlpRepository.save(compra);
		
	
	}
	
}
