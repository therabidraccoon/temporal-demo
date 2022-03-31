package it.si2001.demo.temporal.consumer.temporal.activity;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface ArticoloActivity {

    @ActivityMethod
    public void carico(String nomeArticolo
            , Integer quantita
            , Integer idMagazzino);

    @ActivityMethod
    public void scarico(String nomeArticolo
            , Integer quantita
            , Integer idMagazzino);
}
