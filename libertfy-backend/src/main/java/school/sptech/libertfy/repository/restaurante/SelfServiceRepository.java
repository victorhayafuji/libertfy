package school.sptech.libertfy.repository.restaurante;

import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.libertfy.dominio.SelfService;
import school.sptech.libertfy.dominio.Usuario;
@Repository
public interface SelfServiceRepository extends JpaRepository <SelfService, Integer> {
}
