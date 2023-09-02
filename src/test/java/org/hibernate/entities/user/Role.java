package org.hibernate.entities.user;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.PartitionKey;

@Entity
@Table(name = "roles")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role
        implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "account_id")
    @PartitionKey
    private Long accountId;

    @Size(max = 255)
    @Column(name = "name")
    private String name;

    @Size(max = 255)
    @Column(name = "privileges")
    private String privileges;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "default_role")
    private Boolean defaultRole;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Lob
    @Column(name = "scopes")
    private String scopes;

    @Column(name = "creator_id")
    private Long creatorId;

    @Column(name = "updater_id")
    private Long updaterId;

    @Column(name = "editable")
    private Boolean editable;

    @Lob
    @Column(name = "limits")
    private String limits;

    @Lob
    @Column(name = "meta_data")
    private String metaData;

    @Lob
    @Column(name = "feeder_product_privileges")
    private String feederProductPrivileges;

    @Column(name = "uuid")
    private Long uuid;

    @Size(max = 255)
    @Column(name = "internal_name")
    private String internalName;

    @Column(name = "licence_type")
    private Integer licenceType;

    @Lob
    @Column(name = "lnb_config")
    private String lnbConfig;

    @Lob
    @Column(name = "homepage_config")
    private String homepageConfig;

    @Lob
    @Column(name = "default_record_type_config")
    private String defaultRecordTypeConfig;

    @Size(max = 14)
    @Column(name = "lnb_version", length = 14)
    private String lnbVersion;

    @Column(name = "lnb_default_config")
    private Boolean lnbDefaultConfig;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<UserRole> userRole;
}
