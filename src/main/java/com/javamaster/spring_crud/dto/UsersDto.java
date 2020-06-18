package com.javamaster.spring_crud.dto;

import lombok.Builder;
import lombok.Data;

//Класс для передачи данных между слоями
//реализует трансфер между клиентом, контроллером и сервисом

@Data
@Builder
public class UsersDto {
    private Integer id;
    private String name;
    private String login;
    private String email;
}
