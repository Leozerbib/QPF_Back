package com.example.Entities.service.implementation;

import com.example.Entities.DTO.Partie.PArtieCreateDto;
import com.example.Entities.DTO.Partie.PartieDto;
import com.example.Entities.DTO.Partie.PartieTeamDto;
import com.example.Entities.repository.PartieRepo;
import com.example.Entities.service.PartieService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("partieService")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PartieServiceImpl implements PartieService {


    @Autowired
    private final PartieRepo partieRepo ;

    @Override
    public PartieDto savePartie(PArtieCreateDto partieCreateDto) {
        return null;
    }

    @Override
    public PartieDto updatePArtie(Long id) {
        return null;
    }

    @Override
    public void deletePartie(Long id) {

    }

    @Override
    public PartieTeamDto findById(Long id) {
        return null;
    }
}
