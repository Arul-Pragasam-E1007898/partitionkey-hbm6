package org.hibernate.entities;

import java.time.Instant;

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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@Table(name = "entity_note_associations")
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "targetable_type", discriminatorType = DiscriminatorType.STRING)
@EqualsAndHashCode
public class EntityNoteAssociation {
    public EntityNoteAssociation(Long id, Long targetableId, Long noteId, Instant createdAt, Instant updatedAt) {
        this.targetableId = targetableId;
        this.noteId = noteId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id")
    @Setter
    private Long accountId;

    @Column(name = "targetable_id")
    @Setter
    protected Long targetableId;

    @Column(name = "note_id", nullable = false)
    protected Long noteId;

    @Column(name = "created_at", nullable = false)
    @EqualsAndHashCode.Exclude
    @Setter
    @CreatedDate
    protected Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    @EqualsAndHashCode.Exclude
    @Setter
    @LastModifiedDate
    protected Instant updatedAt;

}
