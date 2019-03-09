package com.fzy.scm.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String UserName;

}
