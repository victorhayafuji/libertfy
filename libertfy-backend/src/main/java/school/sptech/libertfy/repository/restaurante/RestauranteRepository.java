package school.sptech.libertfy.repository.restaurante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.libertfy.dominio.Restaurante;

import java.util.List;
import java.util.Optional;
@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Integer> {
        Optional<Restaurante> findByNome(String nome);
        Optional <Restaurante> findByEndereco(String endereco);
}
