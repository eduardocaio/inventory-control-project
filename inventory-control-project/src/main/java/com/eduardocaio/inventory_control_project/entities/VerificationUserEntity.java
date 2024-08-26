package com.eduardocaio.inventory_control_project.entities;

import java.time.Instant;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_verification_code")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class VerificationUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int code;

    private Instant expiration;
    
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    

    public VerificationUserEntity(UserEntity user){
        Random randomCode = new Random();
        this.code = 1000 + randomCode.nextInt(9000);
        this.expiration = Instant.now().plusSeconds(600);
        this.user = user;
    }

}
