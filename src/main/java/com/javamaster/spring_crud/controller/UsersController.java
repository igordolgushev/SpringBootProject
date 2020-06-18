package com.javamaster.spring_crud.controller;

import com.javamaster.spring_crud.dto.UsersDto;
import com.javamaster.spring_crud.exception.ValidationException;
import com.javamaster.spring_crud.service.UsersService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Log
public class UsersController {
    
   private final UsersService usersService;
    
   //http метод POST
   @PostMapping("/save")
   public UsersDto saveUsers(@RequestBody UsersDto usersDto) throws ValidationException {
       log.info("Handling save users: " + usersDto);
       System.out.println("Запрос на добавление пользователя");
       return usersService.saveUser(usersDto);
   }
   
   //http метод GET
   @GetMapping("/findAll")
   public List<UsersDto> findAllUsers() {
       log.info("Handling find all users request");
       return usersService.findAll();
   }
   
   //http метод GET
   @GetMapping("/findByLogin")
   public UsersDto findByLogin(@RequestParam String login) {
       log.info("Handling find by login request: " + login);
       return usersService.findByLogin(login);
   }
   
   //http метод DELETE
   @DeleteMapping("/delete/{id}")
   public ResponseEntity<Void> deleteUsers(@PathVariable Integer id) {
       log.info("Handling delete user request: " + id);
       usersService.deleteUser(id);
       return ResponseEntity.ok().build();
   }   
}
