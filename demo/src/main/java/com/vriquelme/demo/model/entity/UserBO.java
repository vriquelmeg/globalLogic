package com.vriquelme.demo.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
@Entity
@Table(name="tbUser",uniqueConstraints = {@UniqueConstraint(columnNames={"email"})} )
public class UserBO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private UUID id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phones;

    @Column
    private LocalDateTime dateCreate;

    @Column
    private LocalDateTime dateModified;

    @Column
    private LocalDateTime lastLogin;

    @Column
    private Boolean isActive;

    @Column
    private String token;

    @Column
    private String password;



}
