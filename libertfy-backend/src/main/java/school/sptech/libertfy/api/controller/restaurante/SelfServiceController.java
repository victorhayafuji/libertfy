package school.sptech.libertfy.api.controller.restaurante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.libertfy.dominio.Restaurante;
import school.sptech.libertfy.dominio.SelfService;
import school.sptech.libertfy.repository.restaurante.SelfServiceRepository;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/selfservices")
public class SelfServiceController {
    @Autowired
    private SelfServiceRepository selfServiceRepository;

    @PostMapping
    public ResponseEntity<SelfService> inserirSelfService (@RequestBody @Valid SelfService novoSelfService) {

        Restaurante restauranteBanco = selfServiceRepository.save(novoSelfService);
        return ResponseEntity.status(201).body(novoSelfService);
    }

    @GetMapping
    public ResponseEntity<List<SelfService>> listarSelfService() {
        List<SelfService> listaSelfService = selfServiceRepository.findAll();

        if (listaSelfService.isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(listaSelfService);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SelfService> listarSelfServiceIndice(@PathVariable Integer id) {

        Optional<SelfService> selfServiceOpt = selfServiceRepository.findById(id);

        if (selfServiceOpt.isPresent()) {
            return ResponseEntity.status(200).body(selfServiceOpt.get());
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerSelfService(@PathVariable Integer id) {
        if (selfServiceRepository.existsById(id)) {
            selfServiceRepository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }
}
