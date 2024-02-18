package com.example.Entities.service.implementation;

import com.example.Entities.DTO.User.UserReturnDto;
import com.example.Entities.DTO.User.UserTeamDTO;
import com.example.Entities.model.Team;
import com.example.Entities.model.UserTeam;
import com.example.Entities.model.user;
import com.example.Entities.repository.TeamRepo;
import com.example.Entities.repository.UserTeamRepo;
import com.example.Entities.service.UserTeamService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service("userTeamService")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserteamServiceImpl implements UserTeamService {
    @Autowired
    private final UserTeamRepo userTeamRepo;
    @Autowired
    private final TeamRepo teamRepo;
    @Override
    public ResponseEntity<UserTeam> saveUserTeam(UserTeamDTO UserTeam) {
        if (userTeamRepo.existsById(UserTeam.getTeam_id())){
            return new ResponseEntity<UserTeam>(HttpStatus.CONFLICT);
        }
        userTeamRepo.save(mapUserTeamDTOToUserTeam(UserTeam));
        return new ResponseEntity<UserTeam>(HttpStatus.CREATED);

    }

    @Override
    public UserTeam updateUserTeam(UserTeam userTeam) {
        return userTeamRepo.save(userTeam);
    }

    @Override
    public void deleteUser(Long id) {
        userTeamRepo.deleteById(id);
    }

    @Override
    public Set<user> findUserByTeam(Long id) {
        Set<user> userofTeam = userTeamRepo.findByTeam_id(id);
        return userofTeam;
    }

    @Override
    public boolean checkIfExist(Long id){
        return userTeamRepo.existsById(id);
    }

    @Override
    public UserTeam checkIfExistDouble(Long id, Long idx) {
        return userTeamRepo.findByTeam_idAndUser_idEqualsAndTeam_idEquals(id, idx);
    }

    @Override
    public Collection<Team> findTeamByUser(Long id) {
        return null;
    }

    private UserTeamDTO mapUserTeamToUserTeamDTO(UserTeam userTeam){
        ModelMapper mapper = new ModelMapper();
        UserTeamDTO userTeamDTO = mapper.map(userTeam,UserTeamDTO.class);
        return userTeamDTO;
    }

    private UserTeam mapUserTeamDTOToUserTeam(UserTeamDTO userTeamDTO){
        ModelMapper mapper = new ModelMapper();
        UserTeam userTeam = new UserTeam(0L,userTeamDTO.getUser_id(),userTeamDTO.getTeam_id(),userTeamDTO.getDateCrea());
        return userTeam;
    }
    private UserReturnDto mapUserToUserReturnDTO(user user){
        ModelMapper mapper = new ModelMapper();
        UserReturnDto userReturnDTO = mapper.map(user,UserReturnDto.class);
        return userReturnDTO;
    }


}
