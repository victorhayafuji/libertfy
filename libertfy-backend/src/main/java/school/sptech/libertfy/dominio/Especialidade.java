package school.sptech.libertfy.dominio;

public class Especialidade {
    private Integer id;
    private String especialidade;

    public Especialidade(Integer id, String especialidade) {
        this.id = id;
        this.especialidade = especialidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
