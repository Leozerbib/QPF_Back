package com.example.Entities.DTO.Partie;

import com.example.Entities.Enums.stateGame;
import com.example.Entities.model.Team;

import java.util.Set;

public class PartieTeamDto {
    private Long id;
    private int nbr_manche;
    private stateGame state;
    private Set<Team> teams;
}
