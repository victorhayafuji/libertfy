package school.sptech.libertfy.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.libertfy.api.configuration.security.jwt.GerenciadorTokenJwt;
import school.sptech.libertfy.dominio.Usuario;
import school.sptech.libertfy.repository.usuario.UsuarioRepository;
import school.sptech.libertfy.service.usuario.autenticacao.dto.UsuarioLoginDto;
import school.sptech.libertfy.service.usuario.autenticacao.dto.UsuarioTokenDto;
import school.sptech.libertfy.service.usuario.dto.UsuarioCriacaoDto;
import school.sptech.libertfy.service.usuario.dto.UsuarioMapper;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void criar(UsuarioCriacaoDto usuarioCriacaoDto) {
        final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        this.usuarioRepository.save(novoUsuario);
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }
    public List<UsuarioCriacaoDto> listar() {
        List<Usuario> usuario = this.usuarioRepository.findAll();
        return usuario.stream()
                .map(UsuarioMapper::mapProdutoToProdutoConsultaDto)
                .collect(Collectors.toList());
    }
    public double calcularMediaIdadeUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        int totalUsuarios = usuarios.size();
        int somaIdades = 0;

        LocalDate hoje = LocalDate.now();

        for (Usuario usuario : usuarios) {
            Period periodo = Period.between(usuario.getDataNascimento(), hoje);
            somaIdades += periodo.getYears();
        }

        if (totalUsuarios > 0) {
            return (double) somaIdades / totalUsuarios;
        } else {
            return 0.0;
        }
    }
}
