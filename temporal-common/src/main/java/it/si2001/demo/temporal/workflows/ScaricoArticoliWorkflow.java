package it.si2001.demo.temporal.workflows;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface ScaricoArticoliWorkflow {

    @WorkflowMethod
    void scarico(String nomeArticolo, Integer quantita, Integer idMagazzino);

}
