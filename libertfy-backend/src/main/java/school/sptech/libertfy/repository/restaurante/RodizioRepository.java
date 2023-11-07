package school.sptech.libertfy.repository.restaurante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.libertfy.dominio.Rodizio;
@Repository
public interface RodizioRepository extends JpaRepository <Rodizio, Integer> {
}
