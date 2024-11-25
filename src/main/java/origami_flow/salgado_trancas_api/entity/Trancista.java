package origami_flow.salgado_trancas_api.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import origami_flow.salgado_trancas_api.constans.UserRolesEnum;

import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Trancista extends UsuarioAbstract {

    private String telefone;

    private String tipo;
}
