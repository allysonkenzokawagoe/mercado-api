package com.teste.project.modulos.comum.exceptions;

public record ErrorResponse(
        int status,
        String message
) {
}
