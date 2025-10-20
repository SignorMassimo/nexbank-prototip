package com.zexson.signor_p.CardModule;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cards")
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private String cardNumber;
    private String cardHolder;
    private double balance;
    @CreationTimestamp
    @Column(updatable=false)
    LocalDateTime createAt;
    @CreationTimestamp
    @Column(updatable=true)
    LocalDateTime updataAt;
}
