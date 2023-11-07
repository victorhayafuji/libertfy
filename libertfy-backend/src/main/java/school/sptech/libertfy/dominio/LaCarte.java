package school.sptech.libertfy.dominio;

import jakarta.persistence.Entity;

@Entity
public class LaCarte extends Restaurante {
    private Double precoPrato;

    public LaCarte(Integer id,
                   String nome,
                   String endereco,
                   String telefone,
                   String cnpj,
                   Integer categoriaPreco,
                   String especialidade,
                   Double precoPrato) {
        super(id, nome, endereco, telefone, cnpj, categoriaPreco, especialidade);
        this.precoPrato = precoPrato;
    }

    public LaCarte() {

    }

    public Double getPrecoPrato() {
        return precoPrato;
    }

    public void setPrecoPrato(Double precoPrato) {
        this.precoPrato = precoPrato;
    }

    @Override
    public Double calcPrecoTotal() {
        Double taxaServico = 1.15;
        return getPrecoPrato()*taxaServico;
    }
}
