package com.example.Entities.service;

import com.example.Entities.model.Partie;
import com.example.Entities.model.Partie_team;
import com.example.Entities.model.Team;

import java.util.Set;

public interface PartieTeamService {
    public Partie_team savePartieTeam(Partie_team partieTeam);
    public Partie_team updatePArtiTeam(Partie_team partieTeam);
    public void deltePArtieTeam(Partie_team partieTeam);
    public Set<Team> findTeamByPArtieID(Long id);
    public Set<Partie> findPartieByTeamID(Long id);
    public boolean checkIfExist(Long id);

    public Partie_team checkIfExistDouble(Long id, Long idx);
}
