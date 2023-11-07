package school.sptech.libertfy.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.libertfy.builder.ComentarioBuilder;
import school.sptech.libertfy.dominio.Comentario;
import school.sptech.libertfy.repository.comentario.ComentarioRepository;
import school.sptech.libertfy.service.comentario.ComentarioServico;
import school.sptech.libertfy.service.comentario.dto.ComentarioCriacaoDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(MockitoExtension.class)
public class ComentarioServiceTest {

    @Mock
    private ComentarioRepository repository;

    @InjectMocks
    private ComentarioServico comentarioServico;

    @Test
    @DisplayName("Listar quando acionado, deve retornar itens")
    void listarQuandoAcionadoDeveRetornarObjetosValidos(){
        Integer resultadoEsperado = 3;
        List<ComentarioCriacaoDto> comentario = ComentarioBuilder.criarListaDeComentarioDto();
        assertEquals(resultadoEsperado, comentario.size());
    }

    @Test
    @DisplayName("Listar quando acionado deve retornar lista vazia")
    void listarQuandoAcionadoRetornarListaPubliVazia(){
        Integer resultadoEsperado = 0;
        Mockito.when(repository.findAll()).thenReturn(List.of());
        List<Comentario> resultado = comentarioServico.listarComentarios();
        assertTrue(resultado.isEmpty());
        assertEquals(resultadoEsperado, resultado.size());
    }
}
