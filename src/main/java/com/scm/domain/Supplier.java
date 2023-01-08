package com.scm.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "tbl_supplier")
@Entity
public class Supplier extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 120, nullable = false)
    private String password;

    @Column(length = 80, nullable = false, unique = true)
    private String email;

    @Column(length = 14, nullable = false)
    private String phoneNumber;
}
