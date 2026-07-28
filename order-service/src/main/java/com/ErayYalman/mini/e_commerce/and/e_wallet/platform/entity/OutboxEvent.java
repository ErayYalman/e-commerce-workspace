package com.ErayYalman.mini.e_commerce.and.e_wallet.platform.entity;

import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.enums.AggregateType;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.enums.EventType;
import com.ErayYalman.mini.e_commerce.and.e_wallet.platform.enums.OutboxStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tools.jackson.databind.JsonNode;

@Entity
@Table(name = "outbox_events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OutboxEvent {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name="aggregate_id", nullable = false)
    private UUID aggregateId;

    @Enumerated(EnumType.STRING)
    @Column(name="aggregate_type", nullable = false, length = 100)
    private AggregateType aggregateType;

    @Enumerated(EnumType.STRING)
    @Column(name="event_type", nullable = false, length = 100)
    private EventType eventType;

    @Column(name="payload", nullable = false)
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode payload;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false, length = 50)
    private OutboxStatus status;


    @Column(name="created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Instant createdAt;

}
