package lv.citadele.auth.model;

import javax.persistence.*;

@Entity
public class UserCredentials {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private UserCredentials(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.username = builder.username;
        this.password = builder.password;
        this.role = builder.role;
    }

    public static Builder builder() {
        return new Builder();
    }

    public enum Role {
        CLIENT
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserCredentials() {
    }


    public static final class Builder {
        private Long id;
        private String name;
        private String username;
        private String password;
        private Role role;

        private Builder() {
        }

        public UserCredentials build() {
            return new UserCredentials(this);
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }
    }
}
