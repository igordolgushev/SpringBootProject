package com.javamaster.spring_crud.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import lombok.Builder;
//import lombok.Builder;

@Entity
@Table(name = "users_table")
@Data //ломбок аннотация - генерирует геттеры, сеттеры, иквалс, хеш код методы
@NoArgsConstructor //ломбок аннотация - конструктор без аргументов
//@Builder

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //.IDENTITY - генераруем id автоматически
    private Integer id;

    @Column
    private String name;

    @Column
    private String login;

    @Column
    private String email;
}
