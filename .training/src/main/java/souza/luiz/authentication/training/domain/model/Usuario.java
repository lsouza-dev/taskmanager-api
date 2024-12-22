package souza.luiz.authentication.training.domain.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import souza.luiz.authentication.training.domain.dto.usuario.DadosCadastroUsuario;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String login;
    private String senha;

    @OneToOne(mappedBy = "usuario",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Perfil perfil;

    private boolean ativo;

    public Usuario() {
    }

    public Usuario(@Valid DadosCadastroUsuario dados) {
        this.login = dados.login();
        this.senha = dados.senha();
        this.ativo = true;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void desativar() {
        this.ativo = false;
    }
}
