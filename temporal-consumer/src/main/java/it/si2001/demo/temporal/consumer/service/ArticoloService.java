package it.si2001.demo.temporal.consumer.service;

import it.si2001.demo.temporal.consumer.domain.Articolo;
import it.si2001.demo.temporal.consumer.domain.Magazzino;
import it.si2001.demo.temporal.consumer.repository.ArticoloRepository;
import it.si2001.demo.temporal.consumer.repository.MagazzinoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ArticoloService {

    private final ArticoloRepository articoloRepository;
    private final MagazzinoRepository magazzinoRepository;

    @Transactional
    public void caricaArticolo(String nomeArticolo, Integer quantita, Integer idMagazzino) {
        log.info("Carico articolo {} nel magazzino {}  in corso", nomeArticolo, idMagazzino);
        try {
            Magazzino magazzino = magazzinoRepository.findById(idMagazzino)
                    .orElseThrow(() -> new Exception("Nessun magazzino trovato"));

            Articolo articolo = articoloRepository.findByNomeAndMagazzinoId(nomeArticolo, idMagazzino)
                    .orElse(Articolo.builder()
                            .nome(nomeArticolo)
                            .quantita(0)
                            .magazzino(magazzino).build());

            articolo.setQuantita(articolo.getQuantita() + quantita);
            articolo = articoloRepository.save(articolo);

            log.info("Caricati {} unita di {} nel magazzino {}", quantita, articolo.getNome(),  magazzino.getNome());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Transactional
    public void scaricaArticolo(String nomeArticolo, Integer quantita, Integer idMagazzino) {
        log.info("Scarico articolo {} nel magazzino {}  in corso", nomeArticolo, idMagazzino);
        try {

            Magazzino magazzino = magazzinoRepository.findById(idMagazzino)
                    .orElseThrow(() -> new Exception("Nessun magazzino trovato"));

            Articolo articolo = articoloRepository.findByNomeAndMagazzinoId(nomeArticolo, idMagazzino)
                    .orElseThrow(() -> new Exception("Nessun articolo da scaricare trovato"));

            articolo.setQuantita(articolo.getQuantita() - quantita);
            articolo = articoloRepository.save(articolo);

            log.info("Scaricati {} unita di {} nel magazzino {}", quantita, articolo.getNome(), magazzino.getNome());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
