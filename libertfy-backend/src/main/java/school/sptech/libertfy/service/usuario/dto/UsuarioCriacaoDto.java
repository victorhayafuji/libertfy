package school.sptech.libertfy.service.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;


public class UsuarioCriacaoDto {

    @NotBlank
    @Size(min = 4, max = 20)
    private String nome;

    @NotBlank
    @Size(min = 4, max = 20)
    private String genero;

    @NotBlank
    @Size(min = 15)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    private LocalDate dataNascimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public UsuarioCriacaoDto(String nome, String genero, String email, String senha, LocalDate dataNascimento) {
        this.nome = nome;
        this.genero = genero;
        this.email = email;
        this.senha = senha;
        this.dataNascimento = dataNascimento;
    }
}
