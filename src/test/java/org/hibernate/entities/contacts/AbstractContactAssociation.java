package org.hibernate.entities.contacts;

import java.io.Serializable;
import java.time.Instant;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.entities.AbstractSalesEntity;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@MappedSuperclass
public class AbstractContactAssociation
        extends AbstractSalesEntity implements Serializable {
    public AbstractContactAssociation(SalesContact salesContact, Long id, Long contactId, @NotNull Instant createdAt,
                                      @NotNull Instant updatedAt) {
        super(contactId, createdAt, updatedAt);
        this.contact = salesContact;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    @JsonBackReference
    @EqualsAndHashCode.Exclude
    private SalesContact contact;

    public void setContact(SalesContact salesContact) {
        this.contact = salesContact;
        this.setAccountId(salesContact.getAccountId());
        this.setContactId(salesContact.getId());
    }
}
