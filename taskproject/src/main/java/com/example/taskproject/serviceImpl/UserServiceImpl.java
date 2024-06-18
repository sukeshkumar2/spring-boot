package com.example.taskproject.serviceImpl;

import com.example.taskproject.entity.Users;
import com.example.taskproject.payload.UserDTO;
import com.example.taskproject.repository.UserRepository;
import com.example.taskproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;
    @Override
    public UserDTO createUser(UserDTO userDto) {
        Users user = userDtoToEntity(userDto);
        Users createdUser = userRepo.save(user);
        return entityToUserDto(createdUser);
    }

    private Users userDtoToEntity(UserDTO userDTO)
    {
        Users user = new Users();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        return user;
    }
    private UserDTO entityToUserDto(Users user)
    {
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());

        return userDto;
    }
}
