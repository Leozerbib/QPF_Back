package com.example.Entities.service.implementation;

import com.example.Entities.model.Partie;
import com.example.Entities.model.Partie_team;
import com.example.Entities.model.Team;
import com.example.Entities.repository.PartieTeamRepo;
import com.example.Entities.service.PartieTeamService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service("partieTeamService")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PartieTeamServiceImpl implements PartieTeamService {

    @Autowired
    private final PartieTeamRepo partieTeamRepo;
    @Override
    public Partie_team savePartieTeam(Partie_team partieTeam) {
        return null;
    }

    @Override
    public Partie_team updatePArtiTeam(Partie_team partieTeam) {
        return null;
    }

    @Override
    public void deltePArtieTeam(Partie_team partieTeam) {

    }

    @Override
    public Set<Team> findTeamByPArtieID(Long id) {
        return null;
    }

    @Override
    public Set<Partie> findPartieByTeamID(Long id) {
        return null;
    }

    @Override
    public boolean checkIfExist(Long id) {
        return false;
    }

    @Override
    public Partie_team checkIfExistDouble(Long id, Long idx) {
        return null;
    }
}
