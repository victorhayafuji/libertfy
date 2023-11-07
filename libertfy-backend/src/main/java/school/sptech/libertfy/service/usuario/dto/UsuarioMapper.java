package school.sptech.libertfy.service.usuario.dto;

import school.sptech.libertfy.dominio.Usuario;
import school.sptech.libertfy.service.usuario.autenticacao.dto.UsuarioTokenDto;

import java.util.Objects;

public class UsuarioMapper {

    public static Usuario of(UsuarioCriacaoDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setGenero(usuarioCriacaoDto.getGenero());
        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setDataNascimento(usuarioCriacaoDto.getDataNascimento());

        return usuario;
    }

    public static UsuarioTokenDto of(Usuario usuario, String token) {
        UsuarioTokenDto usuarioTokenDto = new UsuarioTokenDto();

        usuarioTokenDto.setId(usuario.getId());
        usuarioTokenDto.setNome(usuario.getNome());
        usuarioTokenDto.setEmail(usuario.getEmail());
        usuarioTokenDto.setToken(token);

        return usuarioTokenDto;
    }

    public static UsuarioCriacaoDto mapProdutoToProdutoConsultaDto(Usuario usuario) {

        if (Objects.isNull(usuario)) {
            return null;
        }

        return new UsuarioCriacaoDto(
                usuario.getNome(),
                usuario.getGenero(),
                usuario.getSenha(),
                usuario.getEmail(),
                usuario.getDataNascimento()
        );
    }
}
