package com.dailycodebuffer.user.entity;

import org.hibernate.annotations.Cascade;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private Long departmentId;
}
