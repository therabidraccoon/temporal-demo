package it.si2001.demo.temporal.workflows;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface TrasferimentoArticoliWorkflow {

    @WorkflowMethod
    void trasferimento(String nomeArticolo, Integer quantita, Integer idMagazzinoPartenza, Integer idMagazzinoDestinazione);

}
