# 1、BOWorkFlow

一种全新的工作流引擎，通过状态机实现
(This is a full new workflow engine,it is business object-oriented.
                   i trying modify the apache commons SCXML to suppot this.)

# 2、介绍（Introduction）
本工作流引擎采用了状态机的思想，使用W3C 的SCXML 标准，在Apache Commons SCXML基础上做的更改。
This worfklow engine adopt the idea of state machine. we use W3C's SCXML standard and modify the Apache Commons SCXML
# 3、引擎架构（The architecture of engine）
## 3.1、Apache Commons SCXML architecture
![Apache Commons SCXML ARCHITECTURE](https://github.com/ThinerZQ/Picture/blob/master/SCXML%20architecture.png?raw=true)
## 3.2、BOWorkflow architecture

![BOWorkflow ARCHITECTURE](https://github.com/ThinerZQ/Picture/blob/master/BOWorkflow%20engine%20architecture.png?raw=true)

# 4、核心功能(Core function)
## 4.1、任务派发支持(support task assignment)

```xml
 <userTask id="judge" name="JudgeTask" assiginee="" candidateGroups="Judger" instancesExpr="judgeCount">
    <form name="" src="">
        <param name="taskName" expr="taskNameValue" type="input"></param>
        <param name="taskDescription" expr="taskDescriptionValue" type="input"></param>
    </form>
</userTask>
```
```xml
<ServiceTask>

</ServiceTask>
```
## 4.2、添加了丰富的子状态机支持(Rich sub-state machine support)
```xml
<subStateMachine src="subStateMachineProcessDefinePath" instances="numberOfSubStateMachine">
	<param name="" value=""></param>
	<param name="" value=""></param>
</subStateMachine>
```
## 4.3、自定义了消息模式(The custom message schema)
自定义的支持8种 状态机之间通信的消息模式(Custom 8 message mode to support communication between multi-state-machine )
```xml
<send event="eventName" type="scxml" messageMode="TO_PARENT" targetName="StateMachineName" targetState="currentStateOfTargetStateMachine"></send>
```
the messageMode include {BROADCAST, TO_OFFSPRING,TO_CHILD,TO_SIBLING,TO_ANCESTOR,MULTICAST,TO_PARENT,UNICAST;}
## 4.4、丰富的查询服务支持(Rich query service support)
### 4.4.1、IndentityService
> 
```java
IdentityService.createUserQuery().userName("").userEmail("").singleResult();
//more
```

### 4.4.2、RuntimeService
> 
```java
RuntimeService.createProcessInstanceQuery().processId("").singleResult();
RuntimeService.createProcessInstanceQuery().getAllProcessInstance();
//more
```

### 4.4.3、TaskService
支持用户任务，和群组任务，支持签收功能(support single user and group task query and sign in function)
```java
TaskService.createUserTaskQuery("").taskName("").taskId("").taskFinish("").taskAssignee("").taskProcessId("");
TaskService.createGroupTaskQuery("").taskName("").taskId("").taskCandidateGroup("");
//more
```
### 4.4.4、FormService
支持动态表单和外接表单（Support dynamic form and external form）
```java
FormService.createFormQuery().formId();
//more
```

