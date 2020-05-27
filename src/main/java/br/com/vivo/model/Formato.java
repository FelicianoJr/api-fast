package br.com.vivo.model;

import java.util.Arrays;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Formato {

    SMS(1, "SMS"),
    VOZ(2, "Voz"),
    DADOS(3, "Dados");

    private Integer codigo;

    private String descricao;

    public static Formato valueOf(Integer id) {

        if (id == null) {
            return null;
        }

        return Arrays.asList(values())
                .stream()
                .filter(t -> t.getCodigo().equals(id))
                .findAny()
                .orElse(null);
    }

}
