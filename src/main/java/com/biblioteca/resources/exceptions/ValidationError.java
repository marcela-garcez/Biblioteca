package com.biblioteca.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationError() {super(); }

    public ValidationError(Long timeStamp, Integer status, String error, String message, String path) {
        super(timeStamp, status, error, message, path);
    }

    public List<FieldMessage> getErros() {
        return erros;
    }
    public void addErros(String fildName, String message) {
        this.erros.add(new FieldMessage(fildName, message));
    }
}
