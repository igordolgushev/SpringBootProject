package com.javamaster.spring_crud.service;

import com.javamaster.spring_crud.dto.UsersDto;
import com.javamaster.spring_crud.entity.Users;
import com.javamaster.spring_crud.exception.ValidationException;

import com.javamaster.spring_crud.repository.UsersRepository;
import java.util.List;

import static java.util.Objects.isNull;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultUserService implements UsersService {
    
    private final UsersRepository usersRepository;
    private final UsersConverter usersConverter;
    
    @Override
    public UsersDto saveUser(UsersDto usersDto) throws ValidationException {
        validateUserDto(usersDto);
        Users savedUser = usersRepository.save(usersConverter.fromUsersDtoToUsers(usersDto));
        return usersConverter.fromUserToUserDto(savedUser);
    }
    
    private void validateUserDto(UsersDto usersDto) throws ValidationException {
        if (isNull(usersDto)) {
            throw new ValidationException("Object user is null");
        }
        if (isNull(usersDto.getLogin()) || usersDto.getLogin().isEmpty()) {
            throw new ValidationException("Login is empty");
        }
    }
    
    @Override
    public void deleteUser(Integer userId) {
        usersRepository.deleteById(userId);
    }
    
    @Override
    public UsersDto findByLogin(String login) {
        Users users = usersRepository.findByLogin(login);
        if (users != null) {
            System.out.print(login);
            return usersConverter.fromUserToUserDto(users);
        }
        System.out.print("User not found");
        return null;
    }
    
    /*
    public List<UsersDto> findAll() {
        List<Users> listUsers = null;
        List<UsersDto> listUsersDto = null;
        listUsers.addAll(usersRepository.findAll()); 
        for(Users users: listUsers) {
            listUsersDto.add(usersConverter.fromUserToUserDto(users));
        }
        return listUsersDto;
    }
    */
    @Override
    public List<UsersDto> findAll() {
        return usersRepository.findAll()
                .stream()
                .map(usersConverter::fromUserToUserDto)
                .collect(Collectors.toList());
    }
}
