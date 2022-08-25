package projeto.estoque.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import projeto.estoque.domain.Usuario;
import projeto.estoque.requests.UsuarioPostRequestBody;
import projeto.estoque.requests.UsuarioPutRequestBody;

@Mapper(componentModel = "spring")
public abstract class UsuarioMapper {
	public static final UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    public abstract Usuario toUsuario(UsuarioPostRequestBody usuarioPostRequestBody);

    public abstract Usuario toUsuario(UsuarioPutRequestBody usuarioPutRequestBody);
}
