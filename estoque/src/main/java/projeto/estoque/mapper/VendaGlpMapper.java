package projeto.estoque.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers; 

import projeto.estoque.domain.VendaGlp;
import projeto.estoque.requests.VendaPostRequestBody;
import projeto.estoque.requests.VendaPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class VendaGlpMapper {
	public static final VendaGlpMapper INSTANCE = Mappers.getMapper(VendaGlpMapper.class);

    public abstract VendaGlp toVendaGlp(VendaPostRequestBody vendaPostRequestBody);

    public abstract VendaGlp toVendaGlp(VendaPutRequestBody vendaPutRequestBody);
}