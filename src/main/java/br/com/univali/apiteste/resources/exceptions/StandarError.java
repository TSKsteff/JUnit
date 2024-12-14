package br.com.univali.apiteste.resources.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class StandarError {

    private LocalDateTime timeStramp;
    private Integer sattus;
    private String error;
    private String path;

}
