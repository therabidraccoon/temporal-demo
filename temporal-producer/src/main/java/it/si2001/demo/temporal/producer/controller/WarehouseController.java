package it.si2001.demo.temporal.producer.controller;

import it.si2001.demo.temporal.producer.temporal.WorkflowsStarter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class WarehouseController {

    private final WorkflowsStarter workflowsStarter;

    @GetMapping("/articolo/carico")
    public ResponseEntity<Void> carico(@RequestParam("nomeArticolo") String nomeArticolo
            , @RequestParam("quantita") Integer quantita
            , @RequestParam("idMagazzino") Integer idMagazzino) {
        workflowsStarter.carico(nomeArticolo, quantita, idMagazzino);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/articolo/scarico")
    public ResponseEntity<Void> scarico(@RequestParam("nomeArticolo") String nomeArticolo
            , @RequestParam("quantita") Integer quantita
            , @RequestParam("idMagazzino") Integer idMagazzino) {
        workflowsStarter.scarico(nomeArticolo, quantita, idMagazzino);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/articolo/trasferimento")
    public ResponseEntity<Void> trasferimento(@RequestParam("nomeArticolo") String nomeArticolo
            , @RequestParam("quantita") Integer quantita
            , @RequestParam("idMagazzinoPartenza") Integer idMagazzinoPartenza
            , @RequestParam("idMagazzinoDestinazione") Integer idMagazzinoDestinazione) {
        workflowsStarter.trasferimento(nomeArticolo, quantita, idMagazzinoPartenza, idMagazzinoDestinazione);
        return ResponseEntity.accepted().build();
    }

}
