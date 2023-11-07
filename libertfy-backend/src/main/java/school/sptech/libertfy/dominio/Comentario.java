package school.sptech.libertfy.dominio;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.libertfy.repository.restaurante.RodizioRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Restaurante restaurante;
    @ManyToOne
    private Usuario usuario;
    private Double avaliacao;
    private String comentario;

    private LocalDate dataPostagem;

    public Comentario() {
    }

    public Comentario(Restaurante restaurante, Usuario usuario, Double avaliacao, String comentario, LocalDate dataPostagem) {
        this.restaurante = restaurante;
        this.usuario = usuario;
        this.avaliacao = avaliacao;
        this.comentario = comentario;
        this.dataPostagem = dataPostagem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getDataPostagem() {
        return dataPostagem;
    }

    public void setDataPostagem(LocalDate dataPostagem) {
        this.dataPostagem = dataPostagem;
    }
}
