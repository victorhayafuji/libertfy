package school.sptech.libertfy.repository.comentario;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.libertfy.dominio.Comentario;
import school.sptech.libertfy.dominio.Restaurante;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    List<Comentario> findByRestaurante(Restaurante restaurante);
}
