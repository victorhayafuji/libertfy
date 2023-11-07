package school.sptech.libertfy.dominio;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;

@Entity
public abstract class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(min = 4, max = 20)
    private String nome;

    @NotBlank
    @Size(min = 10)
    private String endereco;
    private String telefone;

    @CNPJ
    private String cnpj;

    @Positive
    private Integer categoriaPreco;
    @NotBlank
    @Size(min = 4, max = 20)
    private String especialidade;

    @OneToMany(mappedBy = "restaurante")
    private List<Prato> pratos;

    public Restaurante(Integer id, String nome, String endereco, String telefone, String cnpj, Integer categoriaPreco, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cnpj = cnpj;
        this.categoriaPreco = categoriaPreco;
        this.especialidade = especialidade;
    }

    public Restaurante() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Integer getCategoriaPreco() {
        return categoriaPreco;
    }

    public void setCategoriaPreco(Integer categoriaPreco) {
        this.categoriaPreco = categoriaPreco;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public abstract Double calcPrecoTotal();
}
