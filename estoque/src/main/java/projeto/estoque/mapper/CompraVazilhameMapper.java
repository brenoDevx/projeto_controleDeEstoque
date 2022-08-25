package projeto.estoque.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import projeto.estoque.domain.CompraVazilhame;
import projeto.estoque.requests.CompraPostRequestBody;
import projeto.estoque.requests.CompraPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class CompraVazilhameMapper {
	public static final CompraVazilhameMapper INSTANCE = Mappers.getMapper(CompraVazilhameMapper.class);

    public abstract CompraVazilhame toCompraVazilhame(CompraPostRequestBody compraPostRequestBody);

    public abstract CompraVazilhame toCompraVazilhame(CompraPutRequestBody compraPutRequestBody);
}
