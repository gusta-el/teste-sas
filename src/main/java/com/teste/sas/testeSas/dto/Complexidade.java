package com.teste.sas.testeSas.dto;

import java.util.Arrays;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public enum Complexidade {

	FÁCIL("F", "FÁCIL", 15),
	MÉDIA("M", "MÉDIA", 12),
	DIFÍCIL("D", "DIFÍCIL", 8);

    String name;
    String normalized;
    Integer value;
    
    public static Optional<Complexidade> getByName(String name) {
        return Arrays.stream(Complexidade.values()).filter(paymentType -> paymentType.getName().equals(name)).findAny();
    }
    
    public static Optional<Complexidade> getByNormalized(String normalized) {
        return Arrays.stream(Complexidade.values()).filter(paymentType -> paymentType.getNormalized().equals(normalized)).findAny();
    }
    
}
