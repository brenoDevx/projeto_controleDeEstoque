package projeto.estoque.mapper;


import org.mapstruct.Mapper;   
import org.mapstruct.factory.Mappers;
import projeto.estoque.domain.Estoque;
import projeto.estoque.requests.EstoquePostRequestBody;
import projeto.estoque.requests.EstoquePutRequestBody;

@Mapper(componentModel = "spring")
public abstract class EstoqueMapper {
    public static final EstoqueMapper INSTANCE = Mappers.getMapper(EstoqueMapper.class);

    public abstract Estoque toEstoque(EstoquePostRequestBody estoquePostRequestBody);

    public abstract Estoque toEstoque(EstoquePutRequestBody estoquePutRequestBody);
}