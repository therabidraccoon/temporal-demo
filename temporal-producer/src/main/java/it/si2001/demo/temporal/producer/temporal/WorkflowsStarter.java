package it.si2001.demo.temporal.producer.temporal;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import it.si2001.demo.temporal.constants.Constants;
import it.si2001.demo.temporal.constants.MagazzinoTasks;
import it.si2001.demo.temporal.workflows.CaricoArticoliWorkflow;
import it.si2001.demo.temporal.workflows.ScaricoArticoliWorkflow;
import it.si2001.demo.temporal.workflows.TrasferimentoArticoliWorkflow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class WorkflowsStarter {

    public void carico(String nomeArticolo
            , Integer quantita
            , Integer idMagazzino) {
        // WorkflowServiceStubs is a gRPC stubs wrapper that talks to the local Docker instance of the Temporal server.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(MagazzinoTasks.CARICO.name() + Constants.QUEUE)
                // A WorkflowId prevents this it from having duplicate instances, remove it to duplicate.
                .setWorkflowId(MagazzinoTasks.CARICO.name() + "_" + System.currentTimeMillis())
                .build();
        // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);
        // WorkflowStubs enable calls to methods as if the Workflow object is local, but actually perform an RPC.
        CaricoArticoliWorkflow workflow = client.newWorkflowStub(CaricoArticoliWorkflow.class, options);

        // Asynchronous execution. This process will exit after making this call.
        WorkflowExecution we = WorkflowClient.start(workflow::carico, nomeArticolo, quantita, idMagazzino);
        log.info("Inizio workflow di carico articolo {} quantita {} nel magazzino {}", nomeArticolo, quantita, idMagazzino);
        log.info("WorkflowID: {} RunID: {}", we.getWorkflowId(), we.getRunId());
    }

    public void scarico(String nomeArticolo
            , Integer quantita
            , Integer idMagazzino) {

        // WorkflowServiceStubs is a gRPC stubs wrapper that talks to the local Docker instance of the Temporal server.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(MagazzinoTasks.SCARICO.name() + Constants.QUEUE)
                // A WorkflowId prevents this it from having duplicate instances, remove it to duplicate.
                .setWorkflowId(MagazzinoTasks.SCARICO.name() + "_" + System.currentTimeMillis())
                .build();
        // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);
        // WorkflowStubs enable calls to methods as if the Workflow object is local, but actually perform an RPC.
        ScaricoArticoliWorkflow workflow = client.newWorkflowStub(ScaricoArticoliWorkflow.class, options);

        // Asynchronous execution. This process will exit after making this call.
        WorkflowExecution we = WorkflowClient.start(workflow::scarico, nomeArticolo, quantita, idMagazzino);
        log.info("Inizio workflow di scarico articolo {} quantita {} dal magazzino {}", nomeArticolo, quantita, idMagazzino);
        log.info("WorkflowID: {} RunID: {}", we.getWorkflowId(), we.getRunId());

    }

    public void trasferimento(String nomeArticolo
            , Integer quantita
            , Integer idMagazzinoPartenza
            , Integer idMagazzinoDestinazione) {

        // WorkflowServiceStubs is a gRPC stubs wrapper that talks to the local Docker instance of the Temporal server.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(MagazzinoTasks.TRASFERIMENTO.name() + Constants.QUEUE)
                // A WorkflowId prevents this it from having duplicate instances, remove it to duplicate.
                .setWorkflowId(MagazzinoTasks.TRASFERIMENTO.name() + "_" + System.currentTimeMillis())
                .build();
        // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);
        // WorkflowStubs enable calls to methods as if the Workflow object is local, but actually perform an RPC.
        TrasferimentoArticoliWorkflow workflow = client.newWorkflowStub(TrasferimentoArticoliWorkflow.class, options);

        // Asynchronous execution. This process will exit after making this call.
        WorkflowExecution we = WorkflowClient.start(workflow::trasferimento, nomeArticolo, quantita, idMagazzinoPartenza, idMagazzinoDestinazione);
        log.info("Inizio workflow di trasferimento articolo {} quantita {} dal magazzino {} al magazzino {}", nomeArticolo, quantita, idMagazzinoPartenza, idMagazzinoDestinazione);
        log.info("WorkflowID: {} RunID: {}", we.getWorkflowId(), we.getRunId());

    }
}
