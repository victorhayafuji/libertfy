package school.sptech.libertfy.api.controller.restaurante;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.libertfy.dominio.LaCarte;
import school.sptech.libertfy.dominio.Restaurante;
import school.sptech.libertfy.repository.restaurante.LaCarteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lacartes")
public class LaCarteController {
    @Autowired
    private LaCarteRepository laCarteRepository;

    @PostMapping
    public ResponseEntity<LaCarte> inserirLaCarte (@RequestBody @Valid LaCarte novoLaCarte) {

        Restaurante restauranteBanco = laCarteRepository.save(novoLaCarte);
        return ResponseEntity.status(201).body(novoLaCarte);
    }

    @GetMapping
    public ResponseEntity<List<LaCarte>> listarLaCarte() {
        List<LaCarte> listaLaCarte = laCarteRepository.findAll();

        if (listaLaCarte.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaLaCarte);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaCarte> listarLaCarteIndice(@PathVariable Integer id) {

        Optional<LaCarte> laCarteOpt = laCarteRepository.findById(id);

        if (laCarteOpt.isPresent()) {
            return ResponseEntity.status(200).body(laCarteOpt.get());
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerLaCarte(@PathVariable Integer id) {
        if (laCarteRepository.existsById(id)) {
            laCarteRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }
}
