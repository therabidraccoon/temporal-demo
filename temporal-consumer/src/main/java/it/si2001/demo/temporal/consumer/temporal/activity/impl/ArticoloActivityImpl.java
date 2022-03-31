package it.si2001.demo.temporal.consumer.temporal.activity.impl;

import it.si2001.demo.temporal.consumer.service.ArticoloService;
import it.si2001.demo.temporal.consumer.temporal.activity.ArticoloActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ArticoloActivityImpl implements ArticoloActivity {

    private final ArticoloService articoloService;

    @Override
    public void carico(String nomeArticolo, Integer quantita, Integer idMagazzino) {
        articoloService.caricaArticolo(nomeArticolo, quantita, idMagazzino);
    }

    @Override
    public void scarico(String nomeArticolo, Integer quantita, Integer idMagazzino) {
        articoloService.scaricaArticolo(nomeArticolo, quantita, idMagazzino);
    }
}
