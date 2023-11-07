package school.sptech.libertfy.dominio;

import jakarta.persistence.Entity;

@Entity
public class SelfService extends Restaurante {
    private Double precoQuilo;

    public SelfService(Integer id,
                       String nome,
                       String endereco,
                       String telefone,
                       String cnpj,
                       Integer categoriaPreco,
                       String especialidade,
                       Double precoQuilo) {
        super(id, nome, endereco, telefone, cnpj, categoriaPreco, especialidade);
        this.precoQuilo = precoQuilo;
    }

    public SelfService() {

    }

    public Double getPrecoQuilo() {
        return precoQuilo;
    }

    public void setPrecoQuilo(Double precoQuilo) {
        this.precoQuilo = precoQuilo;
    }

    @Override
    public Double calcPrecoTotal() {
        Double taxaServico = 1.00;
        return getPrecoQuilo()*taxaServico;
    }
}
