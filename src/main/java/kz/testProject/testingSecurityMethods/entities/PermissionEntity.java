package kz.testProject.testingSecurityMethods.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "t_permissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PermissionEntity extends BaseEntity implements GrantedAuthority {
    @Column(name = "name")
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
