package it.si2001.demo.temporal.consumer.repository;

import it.si2001.demo.temporal.consumer.domain.Articolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticoloRepository extends JpaRepository<Articolo, Integer> {
    public Optional<Articolo> findByNomeAndMagazzinoId(String nome, Integer idMagazzino);
}
