package br.com.vivo.dto;

import br.com.vivo.model.Formato;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CriarCdrDto implements Serializable {

    @JsonProperty("origem")
    private String origem;

    @JsonProperty("destino")
    private String destino;

    @JsonProperty("formato")
    private Formato formato;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonProperty("dataRegistro")
    private LocalDateTime dataRegistro;

    @JsonProperty("duracao")
    private Integer duracao;

}
