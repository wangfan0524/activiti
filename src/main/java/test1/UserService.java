package test1;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.junit.Test;

/**
 * @author : fan.wang03@hand-china.com
 * @version : 1.0
 * @Name : UserService
 * @Description : Description for this class
 * @Time : 2018/6/10 20:15
 */
public class UserService {




    @Test
    public void createUser(){
        ProcessEngineConfiguration pec=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 获取流程引擎对象
        ProcessEngine processEngine1=pec.buildProcessEngine();

       IdentityService identityService= processEngine1.getIdentityService();

       User user=identityService.newUser("user1");
       user.setEmail("fan.wang03@hand-china.com");
       user.setFirstName("wang");
       user.setLastName("fan");
       identityService.saveUser(user);
    }
    @Test
    public void createGroup(){
        ProcessEngineConfiguration pec=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 获取流程引擎对象
        ProcessEngine processEngine1=pec.buildProcessEngine();

        IdentityService identityService= processEngine1.getIdentityService();

        Group group=identityService.newGroup("group1");
        group.setName("manager");
        group.setType("assignment");
        identityService.saveGroup(group);
    }

    @Test
    public  void putUserIntoGroup(){
        ProcessEngineConfiguration pec=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        // 获取流程引擎对象
        ProcessEngine processEngine1=pec.buildProcessEngine();

        IdentityService identityService= processEngine1.getIdentityService();

        identityService.createMembership("user1","group1");
    }
}