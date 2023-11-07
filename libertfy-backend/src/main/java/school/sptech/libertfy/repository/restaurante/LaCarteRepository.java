package school.sptech.libertfy.repository.restaurante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import school.sptech.libertfy.dominio.LaCarte;
import school.sptech.libertfy.dominio.Restaurante;

import java.util.List;
@Repository
public interface LaCarteRepository extends JpaRepository<LaCarte, Integer> {

}
