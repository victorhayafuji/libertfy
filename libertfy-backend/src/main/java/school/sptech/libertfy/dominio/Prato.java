package school.sptech.libertfy.dominio;

import jakarta.persistence.*;

@Entity
public class Prato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Restaurante restaurante;
    private String prato1;
    private String prato2;
    private String prato3;

    public Prato() {

    }
    public Prato(Restaurante restaurante, String prato1, String prato2, String prato3) {
        this.restaurante = restaurante;
        this.prato1 = prato1;
        this.prato2 = prato2;
        this.prato3 = prato3;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public String getPrato1() {
        return prato1;
    }

    public void setPrato1(String prato1) {
        this.prato1 = prato1;
    }

    public String getPrato2() {
        return prato2;
    }

    public void setPrato2(String prato2) {
        this.prato2 = prato2;
    }

    public String getPrato3() {
        return prato3;
    }

    public void setPrato3(String prato3) {
        this.prato3 = prato3;
    }
}
