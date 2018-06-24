package test1;

import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipInputStream;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.junit.Test;

/**
 * @author : fan.wang03@hand-china.com
 * @version : 1.0
 * @Name : ActivitTest
 * @Description : Description for this class
 * @Time : 2018/6/1 11:07
 */
public class ActivitTest {

    public static void main(String[] args) {


    }

    //脱离spring自己通过代码配置的方式
    @Test
    public void testCreateTables() {
        ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();
        //连接数据库的配置
        processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
        processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti");
        processEngineConfiguration.setJdbcUsername("root");
        processEngineConfiguration.setJdbcPassword("root");
        processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        //构建流程引擎
        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
        System.out.println("ProcessEngine: " + processEngine);
    }

    //部署流程定义
    @Test
    public void testCreateTablesByConf() {
        /*ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();*/
        // 引擎配置
        ProcessEngineConfiguration pec = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 获取流程引擎对象
        ProcessEngine processEngine1 = pec.buildProcessEngine();
        /*Deployment deployment=processEngine1.getRepositoryService().createDeployment().addClasspathResource("diragram/test.bpmn").name("测试流程部署").deploy();*/


        InputStream in = this.getClass().getClassLoader().getResourceAsStream("diragram/test.zip");
        ZipInputStream zipInputStream = new ZipInputStream(in);
        Deployment deployment = processEngine1.getRepositoryService()//与流程定义和部署对象相关的service
                .createDeployment()//创建一个部署对象
                .name("测试流程部署")//添加部署的名称
                .addZipInputStream(zipInputStream)//指定zip格式的文件完成部署
                .deploy();//完成部署
        System.out.println(deployment.getId());
        System.out.println(deployment.getName());


        /*	Deployment deployment=processEngine1.getRepositoryService().createDeployment().addClasspathResource("diragram/TEST.bpmn20.xml").name("测试流程部署").deploy();*/

        System.out.println(deployment.getId());
        System.out.println(deployment.getName());

	/*	for (ProcessDefinition processDefinition:processDefinitionList){

			System.out.println(processDefinition.getName());
			System.out.println(processDefinition.getKey());
			System.out.println(processDefinition.getId());
		}*/
	/*	TaskService taskService=processEngine1.getTaskService();
		List<Task> tasks=taskService.createTaskQuery().list();
		taskService.setAssignee(tasks.get(0).getId(),"lucy");*/

	/*	List<Task> tasks=taskService.createTaskQuery().taskAssignee("tom").list();
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("approveResult","APPROVED");

		taskService.complete(tasks.get(0).getId(),map);

		taskService.complete(tasks.get(0).getId());*/
	/*	RuntimeService runtimeService=processEngine1.getRuntimeService();
		ProcessInstance processInstance=runtimeService.startProcessInstanceById("myProcess_1:1:4");*/
        /*	System.out.println(processInstance+"wwwwwwwwww");*/


//完成了同意，拒绝在，转交

    }

    //启动一个流程
    @Test
    public void startPro() {
        /*ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();*/
        // 引擎配置
        ProcessEngineConfiguration pec = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 获取流程引擎对象
        ProcessEngine processEngine1 = pec.buildProcessEngine();

        ProcessDefinition processDefinition = processEngine1.getRepositoryService().createProcessDefinitionQuery().deploymentId("80001").singleResult();

        System.out.println(processDefinition.getName());
        System.out.println("key======" + processDefinition.getKey());
        System.out.println("id=======" + processDefinition.getId());

	/*	TaskService taskService=processEngine1.getTaskService();
		List<Task> tasks=taskService.createTaskQuery().list();
		taskService.setAssignee(tasks.get(0).getId(),"lucy");*/

	/*	List<Task> tasks=taskService.createTaskQuery().taskAssignee("tom").list();
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("approveResult","APPROVED");

		taskService.complete(tasks.get(0).getId(),map);

		taskService.complete(tasks.get(0).getId());*/
	/*	RuntimeService runtimeService=processEngine1.getRuntimeService();
		ProcessInstance processInstance=runtimeService.startProcessInstanceById("myProcess_1:1:4");*/
        /*	System.out.println(processInstance+"wwwwwwwwww");*/


//完成了同意，拒绝在，转交

    }


    @Test
    //办理流程任务
    public void queryTaskQuery() {
        /*ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();*/
        // 引擎配置
        ProcessEngineConfiguration pec = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 获取流程引擎对象
        ProcessEngine processEngine1 = pec.buildProcessEngine();


        TaskService taskService = processEngine1.getTaskService();
        //查询组任务
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("group1").list();
        //分配班里人
        taskService.claim(tasks.get(0).getId(), "tom");
        /*List<Task> tasks=taskService.createTaskQuery().taskCandidateUser("lucy").list();*/
        /*List<Task> tasks=taskService.createTaskQuery().taskAssignee("tom").list();*/
        /*taskService.setAssignee(tasks.get(0).getId(),"lucy");*/

	/*	List<Task> tasks=taskService.createTaskQuery().taskAssignee("tom").list();
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("approveResult","APPROVED");

		taskService.complete(tasks.get(0).getId(),map);

		taskService.complete(tasks.get(0).getId());*/
	/*	RuntimeService runtimeService=processEngine1.getRuntimeService();
		ProcessInstance processInstance=runtimeService.startProcessInstanceById("myProcess_1:1:4");*/
        /*	System.out.println(processInstance+"wwwwwwwwww");*/


//完成了同意，拒绝在，转交

    }

    @Test
    public void deleteProDefin(){
        ProcessEngineConfiguration pec = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 获取流程引擎对象
        ProcessEngine processEngine1 = pec.buildProcessEngine();
        /*查询流程定义信息*/
        ProcessDefinition processDefinition=processEngine1.getRepositoryService()
                                             .createProcessDefinitionQuery()
                                             .processDefinitionId("TEST:11:70004")
                                             .singleResult();
       String deploymentId=processDefinition.getDeploymentId();

       processEngine1.getRepositoryService().deleteDeployment(deploymentId);
    }



    @Test
    public void startprocessByKey() {
        ProcessEngineConfiguration pec = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 获取流程引擎对象
        ProcessEngine processEngine1 = pec.buildProcessEngine();

        IdentityService identityService = processEngine1.getIdentityService();
        RuntimeService runtimeService = processEngine1.getRuntimeService();
        TaskService taskService = processEngine1.getTaskService();
        String applyuser = "zyq";

        identityService.setAuthenticatedUserId(applyuser);

        Map<String, Object> variables = new HashMap();//设置流程变量

        variables.put("applyUserId", "qwer");

        variables.put("applyTitle", "test_请假申请流程");

        variables.put("applyTime", "7天");

        variables.put("applyCtreateTime", new Date());

        variables.put("applyReason", "休假");

        //设置候选人：上级领导审批

        List list = new ArrayList();

        list.add("zzz");

        list.add("xxx");

        variables.put("managerIds", list);
/*
		ProcessDefinition processDefinition=processEngine1.getRepositoryService().createProcessDefinitionQuery().deploymentId("80001").singleResult();

		ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(),variables);*/


        /*List<Task> tasks=taskService.createTaskQuery().taskCandidateUser("xxx").list();*/

        List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("xxx").

                orderByTaskCreateTime().desc().list();//查询所拥有的候选任务


    }


}