package com.isi.departement.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DepartementNotFoundException extends RuntimeException {
    private final String message ;

}
