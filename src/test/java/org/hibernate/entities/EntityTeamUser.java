package org.hibernate.entities;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.PartitionKey;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Table(name = "entity_team_users")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "entity_type", discriminatorType = DiscriminatorType.INTEGER)
public class EntityTeamUser {
    public EntityTeamUser(Long id, Long entityId, Long designationId, Long userId, Instant createdAt,
                          Instant updatedAt) {

        this.entityId = entityId;
        this.designationId = designationId;
        this.userId = userId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id", insertable = false, updatable = false)
    @Setter
    @PartitionKey
    private Long accountId;
    @Column(name = "entity_id", insertable = false, updatable = false)
    @Setter
    protected Long entityId;

    @Column(name = "designation_id")
    protected Long designationId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    protected Long userId;

    @Column(name = "created_at", nullable = false)
    @Setter
    @CreatedDate
    protected Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @Setter
    @LastModifiedDate
    protected Instant updatedAt;
}
