package com.example.ControlerWebapp.controler;


import com.example.Entities.service.implementation.PartieServiceImpl;
import com.example.Entities.service.implementation.PartieTeamServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/Bibze/Partie")
@RequiredArgsConstructor
@RestController
public class Partie_Controler {

    @Autowired
    private final PartieTeamServiceImpl partieTeamService;

    @Autowired
    private final PartieServiceImpl partieService;



}
