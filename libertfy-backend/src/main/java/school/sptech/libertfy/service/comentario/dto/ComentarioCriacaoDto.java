package school.sptech.libertfy.service.comentario.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ComentarioCriacaoDto {

    @Size(min = 1, max = 255)
    private String comentario;

    @Min(0)
    @Max(5)
    @NotNull
    private Double avaliacao;

    @Min(1)
    @NotNull
    private Integer usuarioId;

    @Min(1)
    @NotNull
    private Integer restauranteId;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Integer restauranteId) {
        this.restauranteId = restauranteId;
    }

    public ComentarioCriacaoDto(String comentario, Double avaliacao, Integer usuarioId, Integer restauranteId) {
        this.comentario = comentario;
        this.avaliacao = avaliacao;
        this.usuarioId = usuarioId;
        this.restauranteId = restauranteId;
    }
}
