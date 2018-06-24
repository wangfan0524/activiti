package test1;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * @author : fan.wang03@hand-china.com
 * @version : 1.0
 * @Name : JudgeCount
 * @Description : Description for this class
 * @Time : 2018/6/14 14:42
 */
@Component
public class JudgeCount implements JavaDelegate {

    public void execute(DelegateExecution delegateExecution) {

        Integer count= (Integer) delegateExecution.getVariable("count");

        if(count<=4){
            delegateExecution.setVariable("countResult","true");
        }else{
            delegateExecution.setVariable("countResult","false");
        }
    }
}