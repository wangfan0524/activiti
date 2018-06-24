package test1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.concurrent.Task;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

/**
 * @author : fan.wang03@hand-china.com
 * @version : 1.0
 * @Name : Demo
 * @Description : Description for this class
 * @Time : 2018/6/14 15:38
 */
public class Demo {


    @Test
    public void deployDemo(){
        ProcessEngineConfiguration pec = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine=pec.buildProcessEngine();
        RepositoryService repositoryService=processEngine.getRepositoryService();
        RuntimeService runtimeService=processEngine.getRuntimeService();
        Deployment deployment=repositoryService.createDeployment().addClasspathResource("diragram/DEMO.bpmn20.xml").name("测试流程DEMO").key("DEMO").deploy();
        /*Deployment deployment=processEngine.getRepositoryService().createDeployment().addClasspathResource("diragram/test.bpmn").name("测试流程部署").key("DEMO").deploy();*/


        ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        ProcessInstance processInstance=runtimeService.startProcessInstanceById(processDefinition.getId());
    }

    @Test
    public void applyTask(){
        ProcessEngineConfiguration pec = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        ProcessEngine processEngine=pec.buildProcessEngine();

        TaskService taskService=processEngine.getTaskService();

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("count",5);
        List<org.activiti.engine.task.Task> task=taskService.createTaskQuery().taskAssignee("kit").list();

        taskService.complete(task.get(0).getId(),map);
    }
    @Test
    public void manageerTask(){
        ProcessEngineConfiguration pec = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");

        ProcessEngine processEngine=pec.buildProcessEngine();

        TaskService taskService=processEngine.getTaskService();


        List<org.activiti.engine.task.Task> task=taskService.createTaskQuery().taskAssignee("perter").list();
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("approveResult","APPROVED");
        taskService.complete(task.get(0).getId(),map);
    }
}