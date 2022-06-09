package it.si2001.demo.temporal.consumer.temporal.workflow.impl;

import it.si2001.demo.temporal.workflows.TestWorkflow;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestWorkflowImpl implements TestWorkflow {


    @Override
    public String test(String testText) {
        log.info("Workflow test started..");
        return testText + " qualcuno";
    }
}
