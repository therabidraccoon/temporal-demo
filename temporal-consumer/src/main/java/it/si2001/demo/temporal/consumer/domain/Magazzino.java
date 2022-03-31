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
@Table(name = "magazzino")
public class Magazzino {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "citta")
    private String citta;

    @Column(name = "indirizzo")
    private String indirizzo;

}
