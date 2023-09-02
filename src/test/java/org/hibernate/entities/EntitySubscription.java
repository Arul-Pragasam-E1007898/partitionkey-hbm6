package org.hibernate.entities;

import java.io.Serializable;
import java.time.Instant;
import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Table(name = "entity_subscriptions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "entity_type", discriminatorType = DiscriminatorType.INTEGER)
@EqualsAndHashCode
public class EntitySubscription
        implements Serializable{
    public EntitySubscription(Long id, Long entityId, String subscriptionTypes, String unsubscriptionTypes,
                              Instant createdAt, Instant updatedAt) {
        this.entityId = entityId;
        this.subscriptionTypes = subscriptionTypes;
        this.unsubscriptionTypes = unsubscriptionTypes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", insertable = false, updatable = false)
    @Setter
    private Long accountId;

    @Column(name = "entity_id", insertable = false, updatable = false)
    @Setter
    protected Long entityId;

    @Lob
    @Column(name = "subscription_types")
    @Setter
    private String subscriptionTypes;

    @Lob
    @Column(name = "unsubscription_types")
    @Setter
    private String unsubscriptionTypes;

    @NotNull
    @Column(name = "created_at", nullable = false)
    @EqualsAndHashCode.Exclude
    @Setter
    @CreatedDate
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    @EqualsAndHashCode.Exclude
    @Setter
    @LastModifiedDate
    private Instant updatedAt;

}
