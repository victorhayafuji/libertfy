package school.sptech.libertfy.service.usuario.autenticacao.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import school.sptech.libertfy.dominio.Usuario;

import java.time.LocalDate;
import java.util.Collection;

public class UsuarioDetalhesDto implements UserDetails {

    private final String nome;
    private final String genero;

    private final String email;

    private final String senha;

    private final LocalDate dataNascimento;

    public UsuarioDetalhesDto(Usuario usuario) {
        this.nome = usuario.getNome();
        this.genero = usuario.getGenero();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.dataNascimento = usuario.getDataNascimento();
    }

    public String getNome() {
        return nome;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
