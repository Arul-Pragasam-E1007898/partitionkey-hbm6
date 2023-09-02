package org.hibernate.entities.contacts;

import java.io.Serializable;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.entities.custom.LongCustomFields2;
import org.hibernate.entities.custom.TextCustomField2;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name = "contact_custom_field2")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Entity
@NoArgsConstructor
public class ContactCustomField2
        extends AbstractContactCustomField implements Serializable {
    @Builder
    public ContactCustomField2(Long id, Long contactId, Long contactFormId, @NotNull Long accountId,
                               LongCustomFields2 longCustomFields2, TextCustomField2 textCustomField2) {
        super(id, accountId, contactFormId, contactId);
        this.longCustomFields2 = longCustomFields2;
        this.textCustomField2 = textCustomField2;
    }

    @Embedded
    private LongCustomFields2 longCustomFields2 = new LongCustomFields2();

    @Embedded
    private TextCustomField2 textCustomField2 = new TextCustomField2();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", referencedColumnName = "id", insertable = false, updatable = false)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", insertable = false, updatable = false)
    @JsonBackReference
    private SalesContact contact;

    public void setContact(SalesContact salesContact) {
        this.contact = salesContact;
        this.setAccountId(salesContact.getAccountId());
        this.setContactId(salesContact.getId());
    }
}
