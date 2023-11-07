package school.sptech.libertfy;

import java.time.LocalDate;
import java.time.Period;

public class Teste {
    public static void main(String[] args) {
        // Obtém a data de nascimento da pessoa
        LocalDate dataNascimento = LocalDate.of(1990, 5, 15);

        // Obtém a data atual
        LocalDate dataAtual = LocalDate.now();

        // Calcula a diferença entre as datas
        Period periodo = Period.between(dataNascimento, dataAtual);

        // Obtém a idade da pessoa
        int idade = periodo.getYears();

        // Exibe a idade
        System.out.println("A idade é: " + idade);
    }
}
