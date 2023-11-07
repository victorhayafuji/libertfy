package school.sptech.libertfy.dominio;

import jakarta.persistence.Entity;

@Entity
public class Rodizio extends Restaurante {
    private Double precoRodizio;

    public Rodizio(Integer id,
                   String nome,
                   String endereco,
                   String telefone,
                   String cnpj,
                   Integer categoriaPreco,
                   String especialidade,
                   Double precoRodizio) {
        super(id, nome, endereco, telefone, cnpj, categoriaPreco, especialidade);
        this.precoRodizio = precoRodizio;
    }

    public Rodizio () {

    }

    public Double getPrecoRodizio() {
        return precoRodizio;
    }

    public void setPrecoRodizio(Double precoRodizio) {
        this.precoRodizio = precoRodizio;
    }

    @Override
    public Double calcPrecoTotal() {
        Double taxaServico = 1.10;
        return getPrecoRodizio()*taxaServico;
    }
}
