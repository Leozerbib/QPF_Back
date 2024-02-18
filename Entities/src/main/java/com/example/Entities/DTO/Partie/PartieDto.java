package com.example.Entities.DTO.Partie;

import com.example.Entities.Enums.stateGame;
import com.example.Entities.model.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.Set;
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartieDto {
    private Long id;
    private LocalDate endDate;
    private LocalDate startDate;
    private int nbr_manche;
    private stateGame state;
    private Set<Team> teams;
}
