package com.example.Entities.DTO.Partie;

import com.example.Entities.Enums.stateGame;

import java.time.LocalDate;

public class PartieInfoDto {
    private Long id;
    private LocalDate endDate;
    private LocalDate startDate;
    private int nbr_manche;
    private stateGame state;
}
