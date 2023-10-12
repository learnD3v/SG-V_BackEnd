package com.sistema.ventas.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.sistema.ventas.util.View;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Table(schema = "public", name = "usuario")
@Entity
@Setter
@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class Usuario  implements Serializable{

    @Id
    @SequenceGenerator(name = "USUARIO_ID_GENERATOR", sequenceName = "usuario_id_productos_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_ID_GENERATOR")
    @Column(name = "id_usuario", unique = true, nullable = false)
    @NotNull
    @JsonView({View.UsuarioShort.class})
    private Integer idUsuario;

    @Column(name = "nombre")
    @NotNull
    @JsonView({View.UsuarioShort.class})
    private String nombreUsu;

    @Column(name = "apellido")
    @NotNull
    @JsonView({View.UsuarioShort.class})
    private String apellidoUsu;

    @Column(name = "correo")
    @NotNull
    @JsonView({View.UsuarioShort.class})
    private String correoUsu;

    @Column(name = "estado")
    @NotNull
    @JsonView({View.UsuarioShort.class})
    private String estado;
}






















