package school.sptech.libertfy.builder;


import school.sptech.libertfy.service.usuario.dto.UsuarioCriacaoDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UsuarioBuilder {
    private UsuarioBuilder(){
        throw new IllegalStateException("Classe Utilitária");
    }

    public static UsuarioCriacaoDto criarUsuarioConsultaDto(){
        return new UsuarioCriacaoDto("Alex", "Masculino", "alex12@gmail.com",
                "1234", LocalDate.of(2000,01,01));
    }

    public static List<UsuarioCriacaoDto> criarListaDeUsuarioDto() {
        return List.of(
                new UsuarioCriacaoDto("Alex", "Masculino", "alex12@gmail.com",
                        "1234", LocalDate.of(2000,01,01)),

                new UsuarioCriacaoDto("Rubens", "Masculino", "rub@gmail.com",
                        "r345", LocalDate.of(2001,01,01)),

                new UsuarioCriacaoDto("Fernanda", "Feminino", "fefer@gmail.com",
                        "9876", LocalDate.of(2002,01,01))
        );
    }

}
