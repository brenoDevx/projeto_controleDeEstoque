package projeto.estoque.service;

import java.time.LocalDateTime;
import java.util.List; 

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import projeto.estoque.domain.Estoque;
import projeto.estoque.domain.VendaVazilhame;
import projeto.estoque.exception.BadRequestException;
import projeto.estoque.mapper.VendaVazilhameMapper;
import projeto.estoque.repository.EstoqueRepository;
import projeto.estoque.repository.VendaVazilhameRepository;
import projeto.estoque.requests.VendaPostRequestBody;
import projeto.estoque.requests.VendaPutRequestBody;
import projeto.estoque.util.DateUtil;

@Service
@RequiredArgsConstructor
public class VendaVazilhameService {
	private final VendaVazilhameRepository vendaVazilhameRepository;
	private final EstoqueRepository estoqueRepository;
	private final DateUtil dateUtil;
	
	public List<VendaVazilhame> listAll(){
		return vendaVazilhameRepository.findAll();		
	}
	
	public VendaVazilhame findByIdOrThrowBadRequestException(long id) {
		return vendaVazilhameRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("VendaVazilhame not Found"));
		
	}
	
	public List<VendaVazilhame> findByTipo(int tipo) {
        return vendaVazilhameRepository.findByTipo(tipo);
    }
	
	@Transactional
	public VendaVazilhame save(VendaPostRequestBody vendaPostRequestBody) {
		
		Estoque estoque = estoqueRepository.findByTipo(vendaPostRequestBody.getTipo());
        estoque.vendaVazilhame(vendaPostRequestBody.getQtdVenda());
        estoqueRepository.save(estoque);
        vendaPostRequestBody.setData(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
		return vendaVazilhameRepository.save(VendaVazilhameMapper.INSTANCE.toVendaVazilhame(vendaPostRequestBody));
	}

	public void delete(long id) {
		VendaVazilhame vendaVazilhameDelete = findByIdOrThrowBadRequestException(id);
		Estoque estoque = estoqueRepository.findByTipo(vendaVazilhameDelete.getTipo());
		estoque.compraVazilhame(vendaVazilhameDelete.getQtdVenda());
		estoqueRepository.save(estoque);
		vendaVazilhameRepository.delete(findByIdOrThrowBadRequestException(id));
	
	}
	
	public void replace(VendaPutRequestBody vendaPutRequestBody) {
		VendaVazilhame vendaAntiga = findByIdOrThrowBadRequestException(vendaPutRequestBody.getId());
		VendaVazilhame venda = VendaVazilhameMapper.INSTANCE.toVendaVazilhame(vendaPutRequestBody);
        venda.setId(vendaAntiga.getId());
        venda.setData(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        Estoque estoque = estoqueRepository.findByTipo(venda.getTipo());
        estoque.vendaVazilhame(vendaAntiga.getQtdVenda());
        estoque.compraVazilhame(venda.getQtdVenda());
        estoqueRepository.save(estoque);
        vendaVazilhameRepository.save(venda);
		
	
	}
}
