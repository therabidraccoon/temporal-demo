package it.si2001.demo.temporal.consumer.temporal.workers;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import it.si2001.demo.temporal.constants.Constants;
import it.si2001.demo.temporal.constants.MagazzinoTasks;
import it.si2001.demo.temporal.consumer.temporal.activity.ArticoloActivity;
import it.si2001.demo.temporal.consumer.temporal.workflow.impl.CaricoArticoliWorkflowImpl;
import it.si2001.demo.temporal.consumer.temporal.workflow.impl.TestWorkflowImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestWorker {

    @PostConstruct
    public void init() {
        // WorkflowServiceStubs is a gRPC stubs wrapper that talks to the local Docker instance of the Temporal server.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        // Worker factory is used to create Workers that poll specific Task Queues.
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(MagazzinoTasks.TEST.name() + Constants.QUEUE);
        // This Worker hosts both Workflow and Activity implementations.
        // Workflows are stateful so a type is needed to create instances.
        worker.registerWorkflowImplementationTypes(TestWorkflowImpl.class);
        // Start listening to the Task Queue.
        factory.start();
    }

}
