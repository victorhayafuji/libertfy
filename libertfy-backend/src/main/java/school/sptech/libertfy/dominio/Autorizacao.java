package school.sptech.libertfy.dominio;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Documentação de Swagger",
                description ="Implementação da Api do projeto LibertFy\n" +
                        "\nIntegrantes:\n" +
                        "\nBrenno Gomes \n" +
                        "\nGuilherme Anastácio,\n" +
                        "\nLeonardo Dillan,\n" +
                        "\nVictor Hayafuji,\n" +
                        "\nVinicius Carapia",
                contact = @Contact(
                        name = "LibertFy",
                        url = "https://github.com/leonardodillan/grupo9-semestre3"
                ),
                license = @License(name = "UNLICENSED"),
                version = "1.0.0"
        )
)


@SecurityScheme(
        name = "Bearer", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT"
)


public class Autorizacao {

}
