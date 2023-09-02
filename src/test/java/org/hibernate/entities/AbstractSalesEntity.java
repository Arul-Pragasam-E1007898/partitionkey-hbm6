package org.hibernate.entities;

import java.io.Serializable;
import java.time.Instant;
import javax.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.PartitionKey;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@MappedSuperclass
public abstract class AbstractSalesEntity
        implements Serializable {
    protected AbstractSalesEntity(Long contactId, Instant createdAt, Instant updatedAt) {
        this.contactId = contactId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @PartitionKey
    @Column(name = "account_id", insertable = false, updatable = false)
    @Setter
    private Long accountId;

    @Column(name = "contact_id", insertable = false, updatable = false)
    @Setter
    protected Long contactId;

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
