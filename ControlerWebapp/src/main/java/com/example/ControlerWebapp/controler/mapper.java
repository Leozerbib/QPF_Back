package com.example.ControlerWebapp.controler;

import com.example.Entities.DTO.User.UserDTO;
import com.example.Entities.DTO.User.UserReturnDto;
import com.example.Entities.model.user;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class mapper {
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
    public Optional<UserReturnDto> mapUserToUserDTORetur(Optional<user> user) {
        ModelMapper mapper = new ModelMapper();
        Optional<UserReturnDto> UserDTO = Optional.ofNullable(mapper.map(user, UserReturnDto.class));
        return UserDTO;
    }
    public UserReturnDto mapUserToUserDTOReturn(user user) {
        ModelMapper mapper = new ModelMapper();
        UserReturnDto UserDTO = mapper.map(user, UserReturnDto.class);
        return UserDTO;
    }
}
