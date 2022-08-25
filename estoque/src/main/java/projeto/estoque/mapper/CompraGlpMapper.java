package projeto.estoque.mapper;

import org.mapstruct.Mapper; 
import org.mapstruct.factory.Mappers;

import projeto.estoque.domain.CompraGlp;
import projeto.estoque.requests.CompraPostRequestBody;
import projeto.estoque.requests.CompraPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class CompraGlpMapper {

	public static final CompraGlpMapper INSTANCE = Mappers.getMapper(CompraGlpMapper.class);

    public abstract CompraGlp toCompraGlp(CompraPostRequestBody compraPostRequestBody);

    public abstract CompraGlp toCompraGlp(CompraPutRequestBody compraPutRequestBody);
}
