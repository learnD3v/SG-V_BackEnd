package com.sistema.ventas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(schema = "public", name = "productos")
@Entity
@Setter
@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Producto implements Serializable {
    @Id
    @SequenceGenerator(name = "PRODUCTOS_ID_GENERATOR", sequenceName = "productos_id_productos_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCTOS_ID_GENERATOR")
    @Column(name = "id_productos", unique = true, nullable = false)
    @NotNull
    private Integer idProducto;

    @Column(name = "detalle")
    @NotNull
    private String detallePdto;

    @Column(name = "cantidad")
    @NotNull
    private Integer cantidad;

    @Column(name = "precio")
    @NotNull
    private Integer precio;

    @Column(name = "usu_alta")
    @NotNull
    private String usuAlta;

    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaAlta;

    @Column(name = "estado")
    @NotNull
    private String estado;
}