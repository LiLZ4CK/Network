package Items.Shop.Entity;

import Items.Shop.enums.Roles;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Table(name = "_users")
@Builder
@Data
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;  // Follow camelCase naming convention

    @Enumerated(EnumType.STRING)
    private Roles Role;

    @Column(unique = true)
    private String Username;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date SignUpDate;  // Follow camelCase naming convention

    private String Password;

    @OneToMany
    private List<Item> Items;

    @PrePersist
    protected void onCreate() {
        SignUpDate = new Date();  // Update field name
    }

    @Builder
    public Users(int id, Roles role, String username, Date signUpDate, String password, List<Item> items) {
        this.Id = id;  // Update field name
        this.Role = Roles.ADMIN;
        this.Username = username;
        this.SignUpDate = signUpDate;  // Update field name
        this.Password = password;
        this.Items = items;
    }

    public Users() {

    }
}
