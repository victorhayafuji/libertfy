package school.sptech.libertfy.service.comentario;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.libertfy.dominio.Comentario;
import school.sptech.libertfy.dominio.Restaurante;
import school.sptech.libertfy.dominio.Usuario;
import school.sptech.libertfy.repository.comentario.ComentarioRepository;
import school.sptech.libertfy.repository.restaurante.RestauranteRepository;
import school.sptech.libertfy.repository.usuario.UsuarioRepository;
import school.sptech.libertfy.service.comentario.dto.ComentarioCriacaoDto;
import school.sptech.libertfy.service.comentario.dto.ComentarioEdicaoDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ComentarioServico {

    @Autowired
    private ComentarioRepository comentarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RestauranteRepository restauranteRepository;

    public List<Comentario> listarComentarios() {
        List<Comentario> comentarios = comentarioRepository.findAll();
        return comentarios;
    }

    public Comentario buscarPorId(Integer id) {
        return comentarioRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentario não existe")
        );
    }

    public List<Comentario> buscarComentariosPorRestauranteId(Integer restauranteId) {
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurante não existe")
                );

        return comentarioRepository.findByRestaurante(restaurante);
    }

    public Comentario criarComentario(
            ComentarioCriacaoDto novoComentario) {

        Optional<Usuario> usuario = usuarioRepository.findById(novoComentario.getUsuarioId());
        Optional<Restaurante> restaurante = restauranteRepository.findById(novoComentario.getRestauranteId());

        if (usuario.isEmpty() || restaurante.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario ou restaurante não encontrado");
        }

        Comentario comentario = new Comentario(
                restaurante.get(),
                usuario.get(),
                novoComentario.getAvaliacao(),
                novoComentario.getComentario(),
                LocalDate.now()
        );

        return comentarioRepository.save(comentario);
    }

    public Comentario editarComentario(Integer id,
                                       @Valid ComentarioEdicaoDto comentario) {

        Optional<Comentario> comentarioExistente =
                comentarioRepository.findById(id);

        if (comentarioExistente.isPresent()) {
            Comentario comentarioEditado = comentarioExistente.get();

            if (comentario.getComentario() != null) {
                comentarioEditado.setComentario(comentario.getComentario());
            }
            if (comentario.getAvaliacao() != null) {
                comentarioEditado.setAvaliacao(comentario.getAvaliacao());
            }

            return comentarioRepository.save(comentarioEditado);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comentario não encontrada");
    }

//    public AvaliacaoCompradorProduto editarAvaliacao(Long id,
//                                                     AvaliacaoCompradorProdutoAlteracaoDto avaliacao) {
//
//        Optional<AvaliacaoCompradorProduto> avaliacaoExistente =
//                avaliacaoRepository.findById(id);
//
//        if (avaliacaoExistente.isPresent()) {
//            AvaliacaoCompradorProduto avaliacaoEditada = avaliacaoExistente.get();
//
//            if (avaliacao.getComentario() != null) {
//                avaliacaoEditada.setComentario(avaliacao.getComentario());
//            }
//            if (avaliacao.getNota() != null) {
//                avaliacaoEditada.setNota(avaliacao.getNota());
//            }
//
//            return avaliacaoRepository.save(avaliacaoEditada);
//        }
//
//        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada");
//    }
    @DeleteMapping("/{id}")
    public void removerComentario(Integer id) {
        if (comentarioRepository.existsById(id)) {
            comentarioRepository.deleteById(id);
        } else {
            throw new NoSuchElementException("Comentário não encontrado");
        }
    }

    public double calcularMediaAvaliacaoComentarios() {
        List<Comentario> comentarios = comentarioRepository.findAll();
        double somaAvaliacoes = 0;
        int totalComentarios = comentarios.size();

        for (Comentario comentario : comentarios) {
            somaAvaliacoes += comentario.getAvaliacao();
        }

        if (totalComentarios > 0) {
            return somaAvaliacoes / totalComentarios;
        } else {
            return 0.0;
        }
    }
}
