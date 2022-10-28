package com.example.rest.rest.service;

import com.example.rest.rest.model.Dto.UserDto;
import com.example.rest.rest.model.User;
import com.example.rest.rest.model.UserForm;
import com.example.rest.rest.model.form.UserUpdateForm;
import com.example.rest.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserDto createUser(UserForm form) {
        User user = convertToBusiness(form);
        user = userRepository.save(user);
        return convertToDto(user);

    }

    public List<UserDto> findAllUsers() {
        List<User> all = userRepository.findAll();
        return convertListToDto(all);

    }

    public UserDto findUserById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            return convertToDto(optional.get());

        }
        return null;

    }

    public void deletById(Long id){
        userRepository.findById(id);
        userRepository.deleteById(id);

    }

    public UserDto updateUserById(UserUpdateForm form, Long id){
        Optional<User> op = userRepository.findById(id);
        if(op.isPresent()){
            User obj = op.get();
            if(form.getName() != null){
                obj.setName(form.getName());
            }
            if(form.getEmail()!= null){
                obj.setEmail(form.getEmail());
            }
            userRepository.save(obj);
            return convertToDto(obj);
        }
        return null;
    }

    private User convertToBusiness(UserForm form) {

        User user = new User();
        user.setName(form.getName());
        user.setEmail(form.getEmail());
        user.setCpf(form.getCpf());
        user.setBirthDate(form.getBirthDate());
        return user;

    }

    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setCpf(user.getCpf());
        dto.setBirthDate(user.getBirthDate());
        return dto;
    }

    private static List<UserDto> convertListToDto(List<User> users) {
        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }


}
