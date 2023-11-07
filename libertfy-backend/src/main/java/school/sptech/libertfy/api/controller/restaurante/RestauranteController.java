package school.sptech.libertfy.api.controller.restaurante;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.libertfy.dominio.Comentario;
import school.sptech.libertfy.dominio.LaCarte;
import school.sptech.libertfy.dominio.Restaurante;
import school.sptech.libertfy.repository.restaurante.LaCarteRepository;
import school.sptech.libertfy.repository.restaurante.RestauranteRepository;
import school.sptech.libertfy.service.comentario.ComentarioServico;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteRepository restauranteRepository;

    private ComentarioServico comentarioServico;

    @Autowired
    public RestauranteController(ComentarioServico comentarioServico) {
        this.comentarioServico = comentarioServico;
    }


    @GetMapping
    public ResponseEntity<List<Restaurante>> listarRestaurante() {
        List<Restaurante> listaRestaurante = restauranteRepository.findAll();

        if (listaRestaurante.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaRestaurante);
    }

        @GetMapping("/nome")
        public ResponseEntity<Restaurante> listarRestauranteNome(@RequestParam String nome) {

            return ResponseEntity.of(this.restauranteRepository.findByNome(nome));

    }
        @GetMapping("/endereco")
    public ResponseEntity<Restaurante> listarRestauranteEndereco(@RequestParam String endereco) {

        return ResponseEntity.of(this.restauranteRepository.findByEndereco(endereco));
    }

    @GetMapping("/{restauranteId}/comentarios")
    public ResponseEntity<List<Comentario>> buscarComentariosPorRestaurante(@PathVariable Integer restauranteId) {
        List<Comentario> comentarios = comentarioServico.buscarComentariosPorRestauranteId(restauranteId);
        return new ResponseEntity<>(comentarios, HttpStatus.OK);
    }
}
