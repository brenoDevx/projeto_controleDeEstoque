package projeto.estoque.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import projeto.estoque.domain.VendaVazilhame;
import projeto.estoque.requests.VendaPostRequestBody;
import projeto.estoque.requests.VendaPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class VendaVazilhameMapper {
	public static final VendaVazilhameMapper INSTANCE = Mappers.getMapper(VendaVazilhameMapper.class);

    public abstract VendaVazilhame toVendaVazilhame(VendaPostRequestBody vendaPostRequestBody);

    public abstract VendaVazilhame toVendaVazilhame(VendaPutRequestBody vendaPutRequestBody);
}
