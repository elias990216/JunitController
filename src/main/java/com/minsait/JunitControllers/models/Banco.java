package com.minsait.JunitControllers.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banco {
    private Long id;
    private String nombre;
    private Integer totalTransferencias;
}
