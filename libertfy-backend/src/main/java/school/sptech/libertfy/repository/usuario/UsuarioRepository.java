package school.sptech.libertfy.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import school.sptech.libertfy.dominio.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

        Optional<Usuario> findByEmail(String email);
        Optional<Usuario> findByEmailAndSenha (String email , String senha);
        @Query("SELECT u FROM Usuario u ORDER BY u.nome")
        List<Usuario> listagemEmOrdemAlfabetica();

        @Query("SELECT COUNT(u) FROM Usuario u WHERE u.genero = 'masculino'")
        Long contarUsuariosMasculinos();

        @Query("SELECT COUNT(u) FROM Usuario u")
        Long contarTotalUsuarios();

        default Double calcularPercentualUsuariosMasculinos() {
                Long totalUsuarios = contarTotalUsuarios();
                if (totalUsuarios == 0) {
                        return 0.0;
                }
                Long usuariosMasculinos = contarUsuariosMasculinos();
                return (usuariosMasculinos * 100.0) / totalUsuarios;
        }

        @Query("SELECT COUNT(u) FROM Usuario u WHERE u.genero = 'feminino'")
        Long contarUsuariosFemininos();

        default Double calcularPercentualUsuariosFemininos() {
                Long totalUsuarios = contarTotalUsuarios();
                if (totalUsuarios == 0) {
                        return 0.0;
                }
                Long usuariosFemininos = contarUsuariosFemininos();
                return (usuariosFemininos * 100.0) / totalUsuarios;
        }
}
