package jp.co.sysevo.batch;

import jp.co.sysevo.model.OutptData;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("updateWriter")
@Scope("step")
public class UpdateItemWriter implements ItemWriter<OutptData> {

    List<OutptData> stepItems;

    @Override
    public void write(List<? extends OutptData> itemL) throws Exception {

    }

    @BeforeStep
    public void retrieveInterstepData(StepExecution stepExecution) {
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();

        System.out.println("------resultWriter--- BeforeStep ----");

        this.stepItems = (List<OutptData>) jobContext.get("stepItems");

        for(OutptData outptData: stepItems) {
            System.out.println(outptData.toString());
        }
    }
}
