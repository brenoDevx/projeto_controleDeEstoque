package projeto.estoque.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import projeto.estoque.domain.Estoque;
import projeto.estoque.domain.VendaGlp;
import projeto.estoque.exception.BadRequestException;
import projeto.estoque.mapper.VendaGlpMapper;
import projeto.estoque.repository.EstoqueRepository;
import projeto.estoque.repository.VendaGlpRepository;
import projeto.estoque.requests.VendaPostRequestBody;
import projeto.estoque.requests.VendaPutRequestBody;
import projeto.estoque.util.DateUtil;

@Service
@RequiredArgsConstructor
public class VendaGlpService {
	private final VendaGlpRepository vendaGlpRepository;
	private final EstoqueRepository estoqueRepository;
	private final DateUtil dateUtil;
	
	public List<VendaGlp> listAll(){
		return vendaGlpRepository.findAll();		
	}
	
	public VendaGlp findByIdOrThrowBadRequestException(long id) {
		return vendaGlpRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("VendaGlp not Found"));
		
	}
	
	public List<VendaGlp> findByTipo(int tipo) {
        return vendaGlpRepository.findByTipo(tipo);
    }
	
	@Transactional
	public VendaGlp save(VendaPostRequestBody vendaPostRequestBody) {
		
		Estoque estoque = estoqueRepository.findByTipo(vendaPostRequestBody.getTipo());
        estoque.vendaGlp(vendaPostRequestBody.getQtdVenda());
        estoqueRepository.save(estoque);
        vendaPostRequestBody.setData(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
		return vendaGlpRepository.save(VendaGlpMapper.INSTANCE.toVendaGlp(vendaPostRequestBody));
	}

	public void delete(long id) {
		
		VendaGlp vendaGlpDelete = findByIdOrThrowBadRequestException(id);
		Estoque estoque = estoqueRepository.findByTipo(vendaGlpDelete.getTipo());
		estoque.compraGlp(vendaGlpDelete.getQtdVenda());
		estoqueRepository.save(estoque);
		vendaGlpRepository.delete(findByIdOrThrowBadRequestException(id));
	
	}
	
	public void replace(VendaPutRequestBody vendaPutRequestBody) {
		VendaGlp vendaAntiga = findByIdOrThrowBadRequestException(vendaPutRequestBody.getId());
		VendaGlp venda = VendaGlpMapper.INSTANCE.toVendaGlp(vendaPutRequestBody);
        venda.setId(vendaAntiga.getId());
        venda.setData(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        Estoque estoque = estoqueRepository.findByTipo(venda.getTipo());
        estoque.vendaGlp(vendaAntiga.getQtdVenda());
        estoque.compraGlp(venda.getQtdVenda());
        estoqueRepository.save(estoque);
        vendaGlpRepository.save(venda);
		
	
	}
}
