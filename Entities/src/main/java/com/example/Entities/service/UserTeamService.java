package com.example.Entities.service;

import com.example.Entities.DTO.User.UserTeamDTO;
import com.example.Entities.model.Team;
import com.example.Entities.model.UserTeam;
import com.example.Entities.model.user;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Set;

public interface UserTeamService {
    public ResponseEntity<UserTeam> saveUserTeam(UserTeamDTO UserTeam);
    public UserTeam updateUserTeam(UserTeam userTeam);
    public void deleteUser(Long id);
    public Set<user> findUserByTeam(Long id);
    public Collection<Team> findTeamByUser(Long id);
    public boolean checkIfExist(Long id);

    public UserTeam checkIfExistDouble(Long id,Long idx);
}
