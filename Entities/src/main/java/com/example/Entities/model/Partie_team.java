package com.example.Entities.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "partie_team")
public class Partie_team implements Serializable {
    @Id
    private Long id;

    @Column(name = "partie_id")
    private Long id_Partie;

    @Column(name = "team_id")
    private Long id_Team;



}
