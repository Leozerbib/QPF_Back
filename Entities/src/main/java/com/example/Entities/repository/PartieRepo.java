package com.example.Entities.repository;

import com.example.Entities.model.Partie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartieRepo extends JpaRepository<Partie,Long> {
    public Partie findPartieById(Long id);
    public boolean existsById(Long id);

}
