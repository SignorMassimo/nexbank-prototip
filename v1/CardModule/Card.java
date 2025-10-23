package com.zexson.signor_p.CardModule;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zexson.signor_p.UserModule.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cards")
@Getter
@Setter
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column(unique = true, nullable = false)
    private String cardNumber;
    @Column(nullable = false)
    private String cardHolder;

    @Column(nullable = false)
    private double balance;

    @CreationTimestamp
    @Column(updatable = false)
    LocalDateTime createAt;
    @CreationTimestamp
    @Column(updatable = true)
    LocalDateTime updataAt;
}
