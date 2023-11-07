package school.sptech.libertfy.service.prato;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import school.sptech.libertfy.dominio.Comentario;
import school.sptech.libertfy.dominio.Prato;
import school.sptech.libertfy.dominio.Restaurante;
import school.sptech.libertfy.dominio.Usuario;
import school.sptech.libertfy.repository.prato.PratoRepository;
import school.sptech.libertfy.repository.restaurante.RestauranteRepository;
import school.sptech.libertfy.service.comentario.dto.ComentarioCriacaoDto;
import school.sptech.libertfy.service.prato.dto.PratoDto;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PratoServico {
    @Autowired
    public PratoRepository pratoRepository;
    @Autowired
    public RestauranteRepository restauranteRepository;

    public Prato criarPrato(
            PratoDto novoPrato) {

        Optional<Restaurante> restaurante = restauranteRepository.findById(novoPrato.getRestauranteId());

        if (restaurante.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Restaurante não encontrado");
        }

        Prato prato = new Prato(
                restaurante.get(),
                novoPrato.getPrato1(),
                novoPrato.getPrato2(),
                novoPrato.getPrato3()
        );

        return pratoRepository.save(prato);
    }

    public Prato buscarPorId(Integer id) {
        return pratoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prato não existe")
        );
    }

    public Prato buscarPratoPorRestaurante(Integer restauranteId) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);

        if (restaurante.isEmpty()) {
            throw new NoSuchElementException("Restaurante não encontrado");
        }

        return pratoRepository.findById(restauranteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prato não encontrado para o restaurante especificado"));
    }
}
