package jpa.simstargram.Entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@SuperBuilder @NoArgsConstructor @AllArgsConstructor
@ToString
@Setter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mem_id")
    private Long id;

    @Column(name = "mem_username", unique = true)
    private String username;

    @Column(name = "mem_nickname", unique = true)
    private String nickname;

    @Column(name = "mem_name")
    private String name;

    @Column(name = "mem_password")
    private String password;

    @Column(name = "mem_state")
    private int state;

    @Column(name = "mem_mem_social")
    private int social;

    @Column(name = "mem_provider")
    private String provider;

    @Column(name = "mem_img")
    private String image;
}
