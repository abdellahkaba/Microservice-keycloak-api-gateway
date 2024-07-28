package com.isi.employe.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeNotFoundException extends RuntimeException{

    private final String message ;
}
