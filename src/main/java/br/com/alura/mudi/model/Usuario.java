package br.com.alura.mudi.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class Usuario {

    @Id
    private String username;
    private String password;
    private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario")
    private List<Pedido> pedidos;

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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
