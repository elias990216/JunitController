package com.minsait.JunitControllers.models;

import lombok.Data;

@Data
public class Banco {
    private Long id;
    private String nombre;
    private Integer totalTransferencias;
}
