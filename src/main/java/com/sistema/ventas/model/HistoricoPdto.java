package com.sistema.ventas.model;

import com.fasterxml.jackson.annotation.*;
import com.sistema.ventas.util.View;
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
    @JsonView({View.ProductoShort.class})
    private Integer idHistPdto;

    @Column(name = "usu_alta")
    @NotNull
    private String usuAlta;

    @Column(name = "fecha_alta")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonView({View.ProductoShort.class})
    private Date fechaAlta;

    @ManyToOne
    @JoinColumn(name = "id_productos",
            foreignKey = @ForeignKey(name = "productos_historico_productos_fk"))
    @NotNull
    @JsonView({View.HistoricoProductoShort.class})
    private Producto idPdto;

    @Column(name = "detalle")
    @NotNull
    @JsonView({View.HistoricoProductoShort.class})
    private String detallePdto;

    @Column(name = "cantidad")
    @NotNull
    @JsonView({View.HistoricoProductoShort.class})
    private Integer cantidad;

    @Column(name = "precio")
    @NotNull
    @JsonView({View.HistoricoProductoShort.class})
    private Integer precio;

    @Column(name = "estado")
    @NotNull
    @JsonView({View.HistoricoProductoShort.class})
    private String estado;

}
