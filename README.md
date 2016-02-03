# BOWorkFlow

this is a full new workflow engine,it is business object-oriented.
i trying modify the apache commons SCXML to suppot this.

一种全新的工作流引擎，通过状态机实现。

# 介绍
本工作流引擎采用了状态机的思想，使用W3C 的SCXML 标准，在Apache Commons SCXML基础上做的更改。

# 核心结构
## 添加了任务派发支持

```xml
 <userTask id="judge" name="JudgeTask" assiginee="" candidateGroups="Judger" instancesExpr="judgeCount">
    <form name="" src="">
        <param name="任务名字" expr="taskName" type="input"></param>
        <param name="任务描述" expr="taskDescription" type="input"></param>
    </form>
</userTask>
```
## 添加了丰富的子状态机支持
```xml
<subStateMachine src="" instance="" messgeMode="">
	<param name="" value="">
	<param name="" value="">
</subStateMachine>
```
## 自定义了消息模式
> 自定义的支持8种 状态机之间通信的消息模式

## 丰富的查询服务支持
### IndentityService
> 
```java
IdentityService.createUserQuery().userName("").userEmail("").singleResult();
```

### RuntimeService
> 
```java
RuntimeService.createProcessInstanceQuery().processId("").singleResult();
RuntimeService.createProcessInstanceQuery().getAllProcessInstance();
```

### TaskService
支持用户任务，和群组任务，支持签收功能
### FormService
支持动态表单和外界表单
