package projeto.estoque.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import projeto.estoque.domain.CompraVazilhame;
import projeto.estoque.domain.Estoque;
import projeto.estoque.exception.BadRequestException;
import projeto.estoque.mapper.CompraVazilhameMapper;
import projeto.estoque.repository.CompraVazilhameRepository;
import projeto.estoque.repository.EstoqueRepository;
import projeto.estoque.requests.CompraPostRequestBody;
import projeto.estoque.requests.CompraPutRequestBody;
import projeto.estoque.util.DateUtil;

@Service
@RequiredArgsConstructor
public class CompraVazilhameService {
	private final CompraVazilhameRepository compraVazilhameRepository;
	private final EstoqueRepository estoqueRepository;
	private final DateUtil dateUtil;
	
	public List<CompraVazilhame> listAll(){
		return compraVazilhameRepository.findAll();		
	}
	
	public CompraVazilhame findByIdOrThrowBadRequestException(long id) {
		return compraVazilhameRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("CompraVazilhame not Found"));
		
	}
	
	public List<CompraVazilhame> findByTipo(int tipo) {
        return compraVazilhameRepository.findByTipo(tipo);
    }
	
	@Transactional
	public CompraVazilhame save(CompraPostRequestBody compraPostRequestBody) {
		
		Estoque estoque = estoqueRepository.findByTipo(compraPostRequestBody.getTipo());
        estoque.compraVazilhame(compraPostRequestBody.getQtdCompra());
        estoqueRepository.save(estoque);
        compraPostRequestBody.setData(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
		return compraVazilhameRepository.save(CompraVazilhameMapper.INSTANCE.toCompraVazilhame(compraPostRequestBody));
	}

	public void delete(long id) {
		
		CompraVazilhame compraVazilhameDelete = findByIdOrThrowBadRequestException(id);
		Estoque estoque = estoqueRepository.findByTipo(compraVazilhameDelete.getTipo());
		estoque.vendaVazilhame(compraVazilhameDelete.getQtdCompra());
		estoqueRepository.save(estoque);
		compraVazilhameRepository.delete(findByIdOrThrowBadRequestException(id));
	
	}
	
	public void replace(CompraPutRequestBody compraPutRequestBody) {
		CompraVazilhame compraAntiga = findByIdOrThrowBadRequestException(compraPutRequestBody.getId());
		CompraVazilhame compra = CompraVazilhameMapper.INSTANCE.toCompraVazilhame(compraPutRequestBody);
        compra.setId(compraAntiga.getId());
        compra.setData(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        Estoque estoque = estoqueRepository.findByTipo(compra.getTipo());
        estoque.vendaVazilhame(compraAntiga.getQtdCompra());
        estoque.compraVazilhame(compra.getQtdCompra());
        estoqueRepository.save(estoque);
        compraVazilhameRepository.save(compra);
		
	
	}
}
