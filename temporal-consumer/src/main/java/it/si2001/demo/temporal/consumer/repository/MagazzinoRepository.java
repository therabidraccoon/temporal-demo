package it.si2001.demo.temporal.consumer.repository;

import it.si2001.demo.temporal.consumer.domain.Magazzino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MagazzinoRepository extends JpaRepository<Magazzino, Integer> {
}
