package it.si2001.demo.temporal.consumer.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "articolo")
public class Articolo {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "quantita")
    private Integer quantita;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_magazzino")
    private Magazzino magazzino;

}
