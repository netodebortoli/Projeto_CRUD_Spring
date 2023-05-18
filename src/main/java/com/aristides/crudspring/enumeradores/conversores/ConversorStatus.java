package com.aristides.crudspring.enumeradores.conversores;

import com.aristides.crudspring.enumeradores.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ConversorStatus implements AttributeConverter<Status, String> {

    @Override
    public String convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        return status.getValor();
    }

    @Override
    public Status convertToEntityAttribute(String status) {
        if (status == null) {
            return null;
        }
        return Stream.of(Status.values())
                .filter(c -> c.getValor().equals(status))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
