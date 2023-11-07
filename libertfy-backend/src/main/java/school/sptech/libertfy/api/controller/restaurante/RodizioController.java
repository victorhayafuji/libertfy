package school.sptech.libertfy.api.controller.restaurante;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.libertfy.dominio.Restaurante;
import school.sptech.libertfy.dominio.Rodizio;
import school.sptech.libertfy.repository.restaurante.RodizioRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rodizios")
public class RodizioController {
    @Autowired
    private RodizioRepository rodizioRepository;

    @PostMapping
    public ResponseEntity<Rodizio> inserirRodizio (@RequestBody @Valid Rodizio novoRodizio) {

        Restaurante restauranteBanco = rodizioRepository.save(novoRodizio);
        return ResponseEntity.status(201).body(novoRodizio);
    }

    @GetMapping
    public ResponseEntity<List<Rodizio>> listarRodizio() {
        List<Rodizio> listaRodizio = rodizioRepository.findAll();

        if (listaRodizio.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaRodizio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rodizio> listarRodizioIndice(@PathVariable Integer id) {

        Optional<Rodizio> rodzioOpt = rodizioRepository.findById(id);

        if (rodzioOpt.isPresent()) {
            return ResponseEntity.status(200).body(rodzioOpt.get());
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerRodizio(@PathVariable Integer id) {
        if (rodizioRepository.existsById(id)) {
            rodizioRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }
}
