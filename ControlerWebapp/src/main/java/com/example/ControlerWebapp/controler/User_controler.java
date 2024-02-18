package com.example.ControlerWebapp.controler;

import com.example.Entities.DTO.Team.UserRegisterTeamDto;
import com.example.Entities.DTO.User.*;
import com.example.Entities.model.user;
import com.example.Entities.service.implementation.UserServiceImpl;
import com.example.Entities.service.implementation.UserteamServiceImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/Bibz/User")
@RequiredArgsConstructor
public class User_controler {


    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private UserteamServiceImpl userteamService;


    private HttpStatus ok;

    /**
     * Met à jour les données d'un client dans la base de donnée H2. Si le client n'est pas retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
     * @param userRegisterTeamDto
     * @return
     */
    @PostMapping(path = "/save")
    public ResponseEntity<UserReturnDto> createNewUser(@RequestBody UserRegisterTeamDto userRegisterTeamDto){
//, UriComponentsBuilder uriComponentBuilder
        System.out.println(userRegisterTeamDto.toString());
        CreateUserDto createUserDto = new CreateUserDto(userRegisterTeamDto.getNames(), userRegisterTeamDto.getLastname(), userRegisterTeamDto.getUsername(), userRegisterTeamDto.getAge(), userRegisterTeamDto.getEmail(), userRegisterTeamDto.getPasswords());
        user user = userService.saveUser(createUserDto).getBody();
        UserTeamDTO userTeam = new UserTeamDTO(user.getId(),userRegisterTeamDto.getIdteam(), LocalDate.now());
        userteamService.saveUserTeam(userTeam);
        ModelMapper mapper = new ModelMapper();
        UserReturnDto userReturnDto = mapper.map(user,UserReturnDto.class);
        return new ResponseEntity<UserReturnDto>(userReturnDto,HttpStatusCode.valueOf(200));

    }


    /**
     * Met à jour les données d'un client dans la base de donnée H2. Si le client n'est pas retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
     * @param userDTORequest
     * @return
     */
    @PutMapping("/updateUser")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTORequest){
        if (!userService.checkIfIdexists((long) userDTORequest.getId())){
            return new ResponseEntity<>(HttpStatusCode.valueOf(404));
        }
        user userRequest = mapUserDTOToUser(userDTORequest);
        user userResponse = userService.updateUser(userRequest);
        if (userResponse != null ){
            UserDTO userDTO = mapUserToUserDTO(userResponse);
            return new ResponseEntity<UserDTO>(userDTO,HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<UserDTO>(HttpStatusCode.valueOf(304));
    }



    /**
     * Supprime un client dans la base de donnée H2. Si le client n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
     * @param user_id
     * @return
     */
    @DeleteMapping("/deleteUser/{user_id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long user_id){
        userService.deleteUser(user_id);
        return new ResponseEntity<String>(HttpStatusCode.valueOf(204));
    }



    /**
     * Retourne le client ayant l'adresse email passé en paramètre.
     * @param username
     * @return
     */
    @GetMapping("/searchByUsername")
    public ResponseEntity<UserDTO> searchUserByUsername(@RequestParam("username") String username) {
        //, UriComponentsBuilder uriComponentBuilder
        user user = userService.findUserByUsername(username);
        if (user != null) {
            UserDTO userDTO = mapUserToUserDTO(user);
            System.out.println(userDTO.getUsername());
            return new ResponseEntity<UserDTO>(userDTO, HttpStatusCode.valueOf(200));
        }
        System.out.println("noooonnnn");
        return new ResponseEntity<UserDTO>(HttpStatusCode.valueOf(304));

    }
    @GetMapping("/searchById")
    public ResponseEntity<Optional<UserReturnDto>> searchUserById(@RequestParam("id") Long id) {
        //, UriComponentsBuilder uriComponentBuilder
        Optional<user> user = userService.findById(id);
        if (user != null) {
            Optional<UserReturnDto> userDTO = mapUserToUserDTOReturn(user);
            System.out.println(userDTO.get().getId());
            return new ResponseEntity<Optional<UserReturnDto>>(userDTO, HttpStatusCode.valueOf(200));
        }
        System.out.println("non");
        return new ResponseEntity<Optional<UserReturnDto>>(HttpStatusCode.valueOf(304));
    }

    /**
     * Retourne le client ayant l'adresse email passé en paramètre.
     * @param userDTO
     * @return
     */
    @GetMapping("/searchByUsernameOrEmail")
    public ResponseEntity<UserDTO> searchUserByUsernameOrEmail(@RequestBody LoginDTO userDTO) {
        //, UriComponentsBuilder uriComponentBuilder
        UserDTO user = mapUserToUserDTO(userService.findByUsernameOrEmail(userDTO.getUsername(), userDTO.getUsername()));
        if (user != null) {
            return new ResponseEntity<UserDTO>(user, HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<UserDTO>(HttpStatusCode.valueOf(304));
    }

    /**
     * Met à jour les données d'un client dans la base de donnée H2. Si le client n'est pas retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
     *
     * @param LoginDTO
     * @return
     */
    @PostMapping("/login")
    public ResponseEntity<RestrictedUserDro> loginUser(@RequestBody LoginDTO LoginDTO){
        String msg = "";
        if (searchUserByUsernameOrEmail(LoginDTO).getBody() != null){
            String passwordTest = LoginDTO.getPasswords();
            String encodedPassword = searchUserByUsernameOrEmail(LoginDTO).getBody().getPasswords();
            if (passwordTest.equals(encodedPassword)){
                if (userService.findByIdAndPassword(searchUserByUsernameOrEmail(LoginDTO).getBody().getId(), passwordTest)!=null){
                    System.out.println("Login Success");
                    ResponseEntity<RestrictedUserDro> userResponseEntity=new ResponseEntity<RestrictedUserDro>(userService.findByIdAndPassword(searchUserByUsernameOrEmail(LoginDTO).getBody().getId(), passwordTest),HttpStatusCode.valueOf(200));
                    System.out.println(userResponseEntity);
                    return new ResponseEntity<RestrictedUserDro>(userService.findByIdAndPassword(searchUserByUsernameOrEmail(LoginDTO).getBody().getId(), passwordTest),HttpStatusCode.valueOf(200));
                }
                else {

                    System.out.println("Login Failed");
                    ResponseEntity<user> userResponseEntity=new ResponseEntity<user>(HttpStatusCode.valueOf(406));
                    System.out.println(userResponseEntity);
                    return new ResponseEntity<RestrictedUserDro>(HttpStatus.NOT_MODIFIED);
                }
            }
            else{

                System.out.println("Password not valid");
                ResponseEntity<user> userResponseEntity=new ResponseEntity<user>(HttpStatusCode.valueOf(406));
                System.out.println(userResponseEntity);
                return new ResponseEntity<RestrictedUserDro>(HttpStatus.NOT_ACCEPTABLE);
            }
        }
        else{

            System.out.println("Username or Email not valid");
            ResponseEntity<user> userResponseEntity=new ResponseEntity<user>(HttpStatusCode.valueOf(406));
            System.out.println(userResponseEntity);
            return new ResponseEntity<RestrictedUserDro>(HttpStatus.NOT_FOUND);
        }


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
        user user = mapper.map(UserDTO, user.class);
        return user;
    }
    private Optional<UserReturnDto> mapUserToUserDTOReturn(Optional<user> user) {
        ModelMapper mapper = new ModelMapper();
        Optional<UserReturnDto> UserDTO = Optional.ofNullable(mapper.map(user, UserReturnDto.class));
        return UserDTO;
    }




}
