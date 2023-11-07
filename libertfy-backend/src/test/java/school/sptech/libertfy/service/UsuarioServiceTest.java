package school.sptech.libertfy.service;

import org.apache.commons.lang3.compare.ComparableUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import school.sptech.libertfy.builder.UsuarioBuilder;
import school.sptech.libertfy.repository.usuario.UsuarioRepository;
import school.sptech.libertfy.service.usuario.UsuarioService;
import school.sptech.libertfy.service.usuario.dto.UsuarioCriacaoDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;

    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioCriacaoDto usuarioCriacaoDto;

//    @Test
//    @DisplayName("Deve atualizar o usuario quando informar dados validos")
//    void deveAtualizarProdutoQuandoInformarDadosValidos() {
//
//        //given
//        UsuarioServiceTest usuario = ProdutoBuilder.criarProduto();
//        ProdutoAtualizacaoDto produtoAtualizacaoDto = ProdutoBuilder.criarProdutoAtualizacaoDto();
//
//        //when
//        Mockito.when(repository.existsById(Mockito.anyLong())).thenReturn(true);
//        Mockito.when(repository.save(Mockito.any(Produto.class))).thenReturn(produto);
//
//        //then
//        ProdutoConsultaDto resultado = produtoService.atualizar(1l, produtoAtualizacaoDto);
//
//        //assert
//        assertNotNull(resultado);
//        assertEquals(produto.getId(), resultado.getId());
//        assertEquals(produto.getNome(), resultado.getNome());
//        assertEquals(produto.getDescricao(), resultado.getDescricao());
//        assertEquals(produto.getPreco(), resultado.getPreco());
//
//        Mockito.verify(repository, Mockito.times(1)).existsById(Mockito.anyLong());
//        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(Produto.class));
//    }

    @Test
    @DisplayName("Listar quando acionado deve retornar itens")
    void listarQuandoAcionadoDeveRetornarObjetosValidos(){
        Integer resultadoEsperado = 3;
        List<UsuarioCriacaoDto> usuario = UsuarioBuilder.criarListaDeUsuarioDto();
        assertEquals(resultadoEsperado, usuario.size());
    }

    @Test
    @DisplayName("Listar quando acionado deve retornar lista vazia")
    void listarQuandoAcionadoRetornarListaPubliVazia(){
        Integer resultadoEsperado = 0;
        Mockito.when(repository.findAll()).thenReturn(List.of());
        List<UsuarioCriacaoDto> resultado = usuarioService.listar();
        assertTrue(resultado.isEmpty());
        assertEquals(resultadoEsperado, resultado.size());
    }

    @Test
    void retornarUsuario(){
        String nomeEsperado = "Alex";
        String usuario = UsuarioBuilder.criarUsuarioConsultaDto().getNome();
        assertEquals(nomeEsperado, usuario);
    }

    @Test
    void retornarUsuarioinvalido(){
        String nomeEsperado = null;
        List<UsuarioCriacaoDto> usuario = usuarioService.listar();
        assertTrue(usuario.isEmpty());
        assertEquals(nomeEsperado, usuarioCriacaoDto);
    }


}
