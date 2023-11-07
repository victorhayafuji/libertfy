package school.sptech.libertfy.repository.arquivo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.sptech.libertfy.dominio.Arquivo;


import java.util.List;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {

}