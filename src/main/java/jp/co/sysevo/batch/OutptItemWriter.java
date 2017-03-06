package jp.co.sysevo.batch;

import jp.co.sysevo.model.OutptData;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("multiItemWriter")
@Scope("step")
@SuppressWarnings("unchecked")
public class OutptItemWriter implements ItemWriter<OutptData> {

    private StepExecution stepExecution;
    /** 写代理 */
    private List<ItemWriter<? super OutptData>> delegates;

    public void setDelegates(List<ItemWriter<? super OutptData >> delegates) {
        this.delegates = delegates;
    }

    @Override
    public void write(List<? extends OutptData> items) throws Exception {

        saveItemsToStepContext(items);

        // globa writer
        ItemWriter globalWriter = (ItemWriter) delegates.get(0);

        System.out.println("------>>>>>>>" + globalWriter.toString());
        // japan writer
        ItemWriter japanWriter = (ItemWriter) delegates.get(1);

        globalWriter.write(items);

        japanWriter.write(items);
    }

    @BeforeStep
    public void saveStepExecution(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    /**
     * save items to step context
     * @param items
     */
    private void saveItemsToStepContext(List<? extends OutptData> items) {

        System.out.println("------------------EntryItemWrite---------------------");

        List<OutptData> stepItems = new ArrayList<OutptData>();

        ExecutionContext stepContext = this.stepExecution.getExecutionContext();

        if (stepContext.get("stepItems") != null) {
            stepItems = (List<OutptData>) stepContext.get("stepItems");
        }

        stepItems.addAll(items);

        stepContext.put("stepItems", stepItems);
    }
}
