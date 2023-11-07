package school.sptech.libertfy.builder;

import school.sptech.libertfy.service.comentario.dto.ComentarioCriacaoDto;

import java.util.List;

public class ComentarioBuilder {

    private ComentarioBuilder(){
        throw new IllegalStateException("Classe Utilitária");
    }

    public static ComentarioCriacaoDto criarComentarioConsultaDto() {
        return new ComentarioCriacaoDto("Restaurante Perfeito! ",7.0, 1, 2);
    }

    public static List<ComentarioCriacaoDto> criarListaDeComentarioDto(){
        return List.of(
                new ComentarioCriacaoDto("Restaurante Perfeito! ",7.0, 1, 2),
                new ComentarioCriacaoDto("Achei as comidas um pouco sem gosto!",8.0,
                        4, 1),
                new ComentarioCriacaoDto("Lugar é muito convidativo! Ótimo! ",8.5,
                        6, 2)
        );
    }
}
