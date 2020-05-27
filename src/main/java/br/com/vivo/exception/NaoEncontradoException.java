package br.com.vivo.exception;

import lombok.Getter;

@Getter
public class NaoEncontradoException extends RuntimeException {

    public NaoEncontradoException() {
        super("Não encontrado");
    }
}
