package school.sptech.libertfy.service.prato.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PratoDto {
    @Size(min = 1, max = 40)
    private String prato1;
    @Size(min = 1, max = 40)
    private String prato2;
    @Size(min = 1, max = 40)
    private String prato3;
    @Min(1)
    @NotNull
    private Integer restauranteId;

    public PratoDto(String prato1, String prato2, String prato3, Integer restauranteId) {
        this.prato1 = prato1;
        this.prato2 = prato2;
        this.prato3 = prato3;
        this.restauranteId = restauranteId;
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

    public Integer getRestauranteId() {
        return restauranteId;
    }

    public void setRestauranteId(Integer restauranteId) {
        this.restauranteId = restauranteId;
    }
}
