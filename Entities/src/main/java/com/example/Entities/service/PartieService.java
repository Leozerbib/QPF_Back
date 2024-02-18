package com.example.Entities.service;

import com.example.Entities.DTO.Partie.PArtieCreateDto;
import com.example.Entities.DTO.Partie.PartieDto;
import com.example.Entities.DTO.Partie.PartieTeamDto;

public interface PartieService {
    public PartieDto savePartie(PArtieCreateDto partieCreateDto);
    public PartieDto updatePArtie(Long id);
    public void deletePartie(Long id);
    public PartieTeamDto findById(Long id);

}
