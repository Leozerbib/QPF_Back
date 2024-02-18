package com.example.Entities.service.implementation;

import com.example.Entities.DTO.User.CreateUserDto;
import com.example.Entities.DTO.User.RestrictedUserDro;
import com.example.Entities.DTO.User.UserDTO;
import com.example.Entities.Response.LoginResponse;
import com.example.Entities.repository.UserRepo;
import com.example.Entities.model.user;
import com.example.Entities.service.iuserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements iuserService {

    @Autowired
    private final UserRepo userRepo;
    @Override
    public ResponseEntity<user> saveUser(CreateUserDto user) {
        if (findUserByUsername(user.getUsername()) != null) {
            return new ResponseEntity<user>(HttpStatus.CONFLICT);
        }
        user customerRequest = new user(null, user.getNames(), user.getLastname(), user.getUsername(), user.getAge(), LocalDate.now(),LocalDate.now(), user.getEmail(), user.getPasswords(),"https://yoolk.ninja/wp-content/uploads/2022/04/OnePiece-Monkey-D-Luffy.png",null);
        System.out.println(customerRequest.getUsername());
        user customerResponse = userRepo.save(customerRequest);
        if (customerResponse != null) {
            return new ResponseEntity<user>(customerResponse, HttpStatus.CREATED);
        }
        return new ResponseEntity<user>(HttpStatus.NOT_MODIFIED);
    }

    @Override
    public user updateUser(user user) {
        log.info("user udpate : {}",user.getId());
        return userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("user delete : {}",id  );
        userRepo.deleteById(id);
    }

    @Override
    public RestrictedUserDro findByIdAndPassword(Long id, String passwords) {
        return mapUserToresUserDTO(userRepo.findByIdAndPasswords(id, passwords));
    }

    @Override
    public user findByUsernameOrEmail(String log, String logs) {
        return userRepo.findByUsernameOrEmail(log,logs);
    }

    @Override
    public user findUserByUsername(String username) {
        log.info("username : {}",username );
        return userRepo.findByUsername(username);
    }
    @Override
    public Optional<user> findById(Long id){
        return userRepo.findById(id);
    }

    @Override
    public user findUserByNames(String names) {

        return userRepo.findByNames(names);
    }

    @Override
    public boolean checkIfIdexists(Long id) {

        return false;
    }

    @Override
    public List<user> getUserByAge(int age) {

        return (List<user>) userRepo.findByAge(age);
    }

    @Override
    public Collection<user> getAllbyAge(int age){
        log.info("user by age : {}",age  );
        return (Collection<user>) userRepo.findByAge(age);
    }

    @Override
    public LoginResponse loginUser(user user) {
        return null;
    }
    /**
     * Transforme une entity Customer en un POJO CustomerDTO
     *
     * @param user
     * @return
     */
    private UserDTO mapUserToUserDTO(user user) {
        ModelMapper mapper = new ModelMapper();
        UserDTO UserDTO = mapper.map(user, UserDTO.class);
        return UserDTO;
    }
    /**
     * Transforme un POJO CustomerDTO en une entity Customer
     *
     * @param UserDTO
     * @return
     */
    private user mapUserDTOToUser(UserDTO UserDTO) {
        ModelMapper mapper = new ModelMapper();
        user user = new user(1L, UserDTO.getNames(), UserDTO.getLastname(), UserDTO.getUsername(), UserDTO.getAge(), LocalDate.now(),LocalDate.now(), UserDTO.getEmail(), UserDTO.getPasswords(),null,null);
        return user;
    }
    private RestrictedUserDro mapUserToresUserDTO(user user) {
        ModelMapper mapper = new ModelMapper();
        RestrictedUserDro UserDTO = mapper.map(user, RestrictedUserDro.class);
        return UserDTO;
    }
    /**
     * Transforme un POJO CustomerDTO en une entity Customer
     *
     * @param UserDTO
     * @return
     */
    private user mapresUserDTOToUser(RestrictedUserDro UserDTO) {
        ModelMapper mapper = new ModelMapper();
        user user = new user(1L, null, null, UserDTO.getUsername(), 0, LocalDate.now(),LocalDate.now(), null, null,null,null);
        return user;
    }
}
