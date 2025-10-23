package com.zexson.signor_p.Transaction;

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
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long fromCardId;
    @Column(nullable = false)
    private Long fromUserId;
    @Column(nullable = false)
    private Long toCardId;
    @Column(nullable = false)
    private Long toUserId;
    @Column(nullable = false)
    private double amount;
    @CreationTimestamp
    private LocalDateTime timestamp;
}
