package it.si2001.demo.temporal.consumer.temporal.workflow.impl;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import it.si2001.demo.temporal.consumer.temporal.activity.ArticoloActivity;
import it.si2001.demo.temporal.workflows.TrasferimentoArticoliWorkflow;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class TrasferimentoArticoliWorkflowImpl implements TrasferimentoArticoliWorkflow {

    private final RetryOptions retryoptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(100))
            .setBackoffCoefficient(2)
            .setMaximumAttempts(2)
            .build();

    private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
            // Timeout options specify when to automatically timeout Activities if the process is taking too long.
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            // Optionally provide customized RetryOptions.
            // Temporal retries failures by default, this is simply an example.
            .setRetryOptions(retryoptions)
            .build();

    // ActivityStubs enable calls to methods as if the Activity object is local, but actually perform an RPC.
    private final ArticoloActivity articoloActivity = Workflow.newActivityStub(ArticoloActivity.class, defaultActivityOptions);

    @Override
    public void trasferimento(String nomeArticolo, Integer quantita, Integer idMagazzinoPartenza, Integer idMagazzinoDestinazione) {
        articoloActivity.scarico(nomeArticolo, quantita, idMagazzinoPartenza);
        articoloActivity.carico(nomeArticolo, quantita, idMagazzinoDestinazione);
    }


//    try {
//        Thread.sleep(10000);
//    } catch (InterruptedException e) {
//        log.error("Error sleeping thread", e);
//    }
}
