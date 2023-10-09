package com.sistema.ventas.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Table(schema = "public", name = "historico_productos")
@Entity
@Setter
@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HistoricoPdto implements Serializable {
    @Id
    @SequenceGenerator(name = "HISTORICO_PDTO_ID_GENERATOR", sequenceName = "historico_productos_id_historico_pdto_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HISTORICO_PDTO_ID_GENERATOR")
    @Column(name = "id_historico_pdto", unique = true, nullable = false)
    @NotNull
    private Integer idHistPdto;

    @Column(name = "usu_alta")
    @NotNull
    private String usuAlta;

    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date fechaAlta;

    @ManyToOne
    @JoinColumn(name = "id_productos",
            foreignKey = @ForeignKey(name = "productos_historico_productos_fk"))
    @NotNull
    private Producto idPdto;

    @Column(name = "detalle")
    @NotNull
    private String detallePdto;

    @Column(name = "cantidad")
    @NotNull
    private Integer cantidad;

    @Column(name = "precio")
    @NotNull
    private Integer precio;

    @Column(name = "estado")
    @NotNull
    private String estado;

    @Column(name = "img_pdto")
    @JsonIgnore
    private byte[] imagen;

    @Column(name = "img_nombre_pdto")
    private String nombreImagen;

    @Column(name = "tipo_img_pdto")
    @JsonIgnore
    private String tipoImagen;

}
