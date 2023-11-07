package school.sptech.libertfy.service.comentario.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ComentarioEdicaoDto {
    @Size(min = 1, max = 255)
    private String comentario;

    @Min(0)
    @Max(5)
    @NotNull
    private Double avaliacao;

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

    public ComentarioEdicaoDto(String comentario, Double avaliacao) {
        this.comentario = comentario;
        this.avaliacao = avaliacao;

    }
}
