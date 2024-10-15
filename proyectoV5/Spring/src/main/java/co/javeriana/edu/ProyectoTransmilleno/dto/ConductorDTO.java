package co.javeriana.edu.ProyectoTransmilleno.dto;

import lombok.Data;

@Data
public class ConductorDTO {
    private Long id;
    private String name;
    private Integer cc;
    private Integer phone;
    private String adress;
}