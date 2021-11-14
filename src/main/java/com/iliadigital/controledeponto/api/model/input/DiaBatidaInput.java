package com.iliadigital.controledeponto.api.model.input;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class DiaBatidaInput {

    @NotNull
    private LocalDate data;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
