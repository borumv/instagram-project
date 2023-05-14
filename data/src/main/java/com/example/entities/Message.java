package com.example.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "messages")
public class Message implements BaseEntity<Long>, Seenable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;

    private String message;

    @Column(name = "date_of_send")
    private Timestamp dateOfSend;

    @Column(name = "seen_status")
    private boolean seen;

}
