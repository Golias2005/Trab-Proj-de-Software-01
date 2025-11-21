package br.projeto.redemaissocial.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido_afiliacao")
public class PedidoAfiliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="requester_name", nullable = false)
    private String requesterName;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    public PedidoAfiliacao() {
        this.status = Status.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public PedidoAfiliacao(String requesterName, String email) {
        this();
        this.requesterName = requesterName;
        this.email = email;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRequesterName() { return requesterName; }
    public void setRequesterName(String requesterName) { this.requesterName = requesterName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public enum Status {
        PENDING,
        APPROVED,
        REJECTED
    }
}
