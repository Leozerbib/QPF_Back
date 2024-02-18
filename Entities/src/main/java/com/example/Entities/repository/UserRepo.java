package com.example.Entities.repository;

import com.example.Entities.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<user, Long> {
    public user findByAge(int age);
    public user findByUsername(String username);
    public user findByNames(String names);
    public user findByEmail(String email);
    public Optional<user> findById(Long id);
    public user findByIdAndPasswords(Long id, String passwords);
    public user findByUsernameOrEmail(String Log, String Logs);

}
