package main.server.rewardprogram.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "purchase")
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customerEntity;

    @Column(name = "cost")
    private Long cost;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}

