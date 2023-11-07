package school.sptech.libertfy.api.controller.comentario;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.libertfy.dominio.Comentario;
import school.sptech.libertfy.dominio.Restaurante;
import school.sptech.libertfy.service.comentario.ComentarioServico;
import school.sptech.libertfy.service.comentario.dto.ComentarioCriacaoDto;
import school.sptech.libertfy.service.comentario.dto.ComentarioEdicaoDto;

import java.util.List;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {
    @Autowired
    private ComentarioServico comentarioServico;

    @GetMapping
    public ResponseEntity<List<Comentario>> listarComentarios() {
        List<Comentario> avaliacoes = comentarioServico.listarComentarios();

        if (avaliacoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(avaliacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> listarComentarioPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(comentarioServico.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Comentario> criarComentario(
            @RequestBody @Valid ComentarioCriacaoDto novoComentario) {
        return ResponseEntity.created(null).body(comentarioServico.criarComentario(novoComentario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> editarAvaliacao(
            @PathVariable Integer id,
            @RequestBody @Valid ComentarioEdicaoDto editarComentario) {

        return ResponseEntity.ok(comentarioServico.editarComentario(id, editarComentario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerComentario(@PathVariable Integer id) {
        comentarioServico.removerComentario(id);
        return ResponseEntity.ok().build();
    }

    public ComentarioController(ComentarioServico comentarioService) {
        this.comentarioServico = comentarioService;
    }

    @GetMapping("/media-avaliacao")
    public ResponseEntity<Double> obterMediaAvaliacaoComentarios() {
        double mediaAvaliacao = comentarioServico.calcularMediaAvaliacaoComentarios();
        return new ResponseEntity<>(mediaAvaliacao, HttpStatus.OK);
    }
}
