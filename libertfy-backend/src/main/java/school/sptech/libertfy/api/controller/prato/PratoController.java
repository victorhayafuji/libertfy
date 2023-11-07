package school.sptech.libertfy.api.controller.prato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.libertfy.dominio.Comentario;
import school.sptech.libertfy.dominio.Prato;
import school.sptech.libertfy.repository.prato.PratoRepository;
import school.sptech.libertfy.service.prato.PratoServico;
import school.sptech.libertfy.service.prato.dto.PratoDto;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pratos")
public class PratoController {
    private final PratoServico pratoServico;

    @Autowired
    public PratoController(PratoServico pratoServico) {
        this.pratoServico = pratoServico;
    }


    @PostMapping
    public ResponseEntity<Prato> criarPrato(@RequestBody PratoDto novoPrato) {
        try {
            Prato prato = pratoServico.criarPrato(novoPrato);
            return new ResponseEntity<>(prato, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurante não encontrado", e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prato> listarComentarioPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(pratoServico.buscarPorId(id));
    }

    @GetMapping("/{restauranteId}/restaurante")
    public ResponseEntity<Prato> buscarPratoPorRestaurante(@PathVariable Integer restauranteId) {
        Prato prato = pratoServico.buscarPratoPorRestaurante(restauranteId);
        return new ResponseEntity<>(prato, HttpStatus.OK);
    }
}
