package br.edu.ifmg.produto.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandartError{

    private List<FieldMessage> fieldMessages = new ArrayList<>();

    public ValidationError(){

    }

    public List<FieldMessage> FieldMessages() {
        return fieldMessages;
    }

    public void setFields(List<FieldMessage> fields) {
        this.fieldMessages = fields;
    }

    public void addFieldMessage(String field, String message){
        this.fieldMessages.add(new FieldMessage(field, message));
    }
}
