# 1、BOWorkFlow

一种全新的工作流引擎，通过状态机实现
(This is a full new workflow engine,it is business object-oriented.
                   i trying modify the apache commons SCXML to suppot this.)

# 2、介绍（Introduction）
本工作流引擎采用了状态机的思想，使用W3C 的SCXML 标准，在Apache Commons SCXML基础上做的更改。
This worfklow engine adopt the idea of state machine. we use W3C's SCXML standard and modify the Apache Commons SCXML
# 3、引擎架构（The architecture of engine）
## 3.1、Apache Commons SCXML architecture
![Apache Commons SCXML ARCHITECTURE](https://raw.githubusercontent.com/ThinerZQ/Picture/master/SCXML%20architecture.png)
## 3.2、BOWorkflow architecture

![BOWorkflow ARCHITECTURE](https://raw.githubusercontent.com/ThinerZQ/Picture/master/BOWorkflow%20engine%20architecture.png)

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
### 4.4.1、IdentityService
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
# 一个例子（a example for crowdsourcing task object）
filename: <code>crowdsourcingTest.xml</code>, you can find a crowdsourcing application here :!()[https://github.com/ThinerZQ/CrowdSourcing/tree/problem]
## its state machine diagram
![state machine diagram of crowdsourcing](https://raw.githubusercontent.com/ThinerZQ/Picture/master/crowdsourcing%20state%20machine%20diagram.jpg)
## its scxml document
```xml
<?xml version="1.0"?>
<scxml xmlns="http://www.w3.org/2005/07/scxml"
       version="1.0"
       datamodel="jexl"
       initial="init"
       name="crowdsourcing">

    <!-- some property needed -->
    <datamodel>
        <data id="deployee" expr=""></data>
        <data id="taskName" expr=""></data>
        <data id="taskDescription" expr=""></data>
        <data id="dueDate"></data>

        <data id="judgeCount" expr=""></data>
        <data id="decomposeCount" expr=""></data>
        <data id="decomposeVoteCount" expr=""></data>
        <data id="solveCount" expr=""></data>
        <data id="solveVoteCount" expr=""></data>
        <data id="steps" expr="0"></data>
    </datamodel>

<!-- the properties of current Object to be initialized-->
    <state id="init">
        <onentry>
            <log label="init" expr="'init state entry ,begin to initialization'"/>
            <script>
                taskName=crowdSourcingTask.getTaskName();
                taskDescription=crowdSourcingTask.getTaskDescription();
                judgeCount = crowdSourcingTask.getTaskJudgeCount();
                decomposeCount= crowdSourcingTask.getTaskDecomposeCount();
                decomposeVoteCount= crowdSourcingTask.getTaskDecomposeVoteCount();
                solveCount= crowdSourcingTask.getTaskSolveCount();
                solveVoteCount= crowdSourcingTask.getTaskSolveVoteCount();
            </script>
            <log label="init" expr="'initialization complete'"></log>
        </onentry>
        <transition target="judging">
            <log label="init" expr="'init state transition to judging'"/>
        </transition>
        <onexit>
            <log label="init" expr="'init state exit'"/>
        </onexit>
    </state>

    <!-- JudgeState,  To determine whether the current task is a simple task-->

    <state id="judging">
        <datamodel>
            <data id="complexCount" expr="0"></data>
            <data id="simpleCount" expr="0"></data>
        </datamodel>
        <onentry>
            <log label="judging" expr="'entry judging state'"/>
            <userTask id="judge" name="JudgeTask" candidateGroups="Judger" instancesExpr="judgeCount">
                <form src="form/judge.jsp">

                </form>
            </userTask>
        </onentry>
        <onexit>
            <log label="judging" expr="'exit judging state'"/>
        </onexit>

        <transition event="judgeComplete">
            <if cond="_event.data.simple eq 0">
                <assign location="simpleCount" expr="simpleCount + 1"></assign>
                <log label="judging" expr="' simple'"/>

                <elseif cond="_event.data.simple eq 1"/>
                <assign location="complexCount" expr="complexCount + 1"></assign>
                <log label="judging" expr="' complex'"/>

                <else/>
                <log label="judging" expr="_event.data.simple"/>
            </if>
            <if cond="simpleCount + complexCount eq judgeCount">
                <send event="judgeCompleted"></send>
                <log label="judging" expr="'send event judgeCompleted'"/>
            </if>
            <log label="judging" expr="'judge state inner transition'"/>

        </transition>

        <transition event="judgeCompleted" cond="simpleCount gt complexCount" target="solving">
            <log label="judging" expr="'judge state transition to solving'"/>
        </transition>

        <transition event="judgeCompleted" cond="simpleCount lt complexCount" target="decomposing">
            <log label="judging" expr="'judge state transition to decomposing'"/>
        </transition>

    </state>


    <!-- decomposing state : if current task is complex task ,it will enter this state and ,it will be decomposed by some Decomposer  -->
    <state id="decomposing">
        <datamodel>
            <data id="counted" expr="0"></data>
            <data id="step" expr="0"></data>
        </datamodel>
        <onentry>
            <log label="decomposing" expr="'entry decomposing state'"/>
            <userTask id="decompose" name="DecomposeTask" candidateGroups="Decomposer"
                      instancesExpr="decomposeCount">
                <form src="form/decompose.jsp">

                </form>
            </userTask>
        </onentry>

        <transition event="decomposeComplete">
            <assign location="counted" expr="counted + 1"></assign>
            <!-- 写入数据库: 谁分解成了什么 -->
            <log label="decomposing" expr="' inner transition ,decomposeComplete'"/>
        </transition>
        <transition cond="counted eq decomposeCount" target="decomposeVoting">

            <log label="decomposing" expr="' transition from decompose to decomposeVoting '"/>
        </transition>

        <onexit>
            <log label="decomposing" expr="'exit decomposing state'"/>
        </onexit>
    </state>

    <!-- DecomposeVoting state , when several decomposer have voted, current task will be voted for best decomposition strategy -->

    <state id="decomposeVoting">
        <datamodel>
            <data id="counted" expr="0"></data>
        </datamodel>
        <onentry>
            <userTask id="decompose" name="DecomposeVoteTask" candidateGroups="DecomposeVoter"
                      instancesExpr="decomposeVoteCount">
                <form src="form/decomposeVote.jsp">

                </form>
            </userTask>
            <log label="decomposeVoting" expr="'entry decomposeVoting state'"/>
        </onentry>

        <transition event="decomposeVoteComplete">
            <assign location="counted" expr="counted + 1"></assign>
            <log label="decomposeVoting" expr="' inner transition ,decomposeVoteComplete'"/>
        </transition>

        <transition event="startSubMachine" cond="counted eq decomposeVoteCount" target="waitResulting">

          <!-- start substaemachine, you can set up crowdSourcingTaskArrayList object in runtime -->

            <foreach array="crowdSourcingTaskArrayList" item="tempCrowdSourcingTask">
                <subStateMachine src="crowdsourcingTest.xml" instances="1">
                    <param name="crowdSourcingTask" expr="tempCrowdSourcingTask"></param>
                </subStateMachine>
                <assign location="steps" expr="steps + 1"></assign>
                <log label="decomposeVoting" expr="'startSubMachine '"/>
            </foreach>

            <log label="decomposeVoting" expr="'transition from decompose to decomposeVoting '"/>
        </transition>
    </state>

    <!-- wait children's event come -->
    <state id="waitResulting">
        <datamodel>
            <data id="count" expr="0"></data>
        </datamodel>
        <onentry>
            <log label="waitResulting" expr="'entry waitResulting state '"/>
        </onentry>
        <!-- receive event from child -->
        <transition event="to_waitResult">
            <assign location="count" expr="count + 1"></assign>
            <log label="waitResulting" expr="' inner transition ,subStateMachine_to_end'"/>
        </transition>
        <transition cond="count eq steps" target="merging">
            <log label="waitResulting" expr="' transition from waitResulting to merging '"/>
        </transition>
    </state>

    <!-- assign merging task -->
    <state id="merging">
        <datamodel>
            <data id="count" expr="0"></data>
        </datamodel>
        <onentry>
            <log label="merging" expr="'entry merging state '"/>
            <!-- Merge algorithm here called   -->
            <script>
                result = crowdSourcingTask.merge();
            </script>
        </onentry>

        <transition cond="result" target="end">
        <!-- send event to parent state machine  -->
            <send event="to_waitResult" type="scxml" messageMode="TO_PARENT"></send>
            <log label="merging" expr="'transition from merging state to end '"/>
        </transition>

    </state>

    <!-- Solving state  -->
    <state id="solving">
        <datamodel>
            <data id="counted" expr="0"></data>
        </datamodel>
        <onentry>
            <log label="solving" expr="'entry solving state '"/>
            <userTask id="solve" name="SolveTask" candidateGroups="Solver"
                      instancesExpr="solveCount">
                <form src="form/solve.jsp">

                </form>
            </userTask>
        </onentry>
        <transition event="solveComplete">
            <assign location="counted" expr="counted + 1"></assign>
            <log label="solving" expr="'solveComplete ,inner transition '"/>
        </transition>
        <transition cond="counted eq solveCount" target="solveVoting">
            <log label="solving" expr="' transition from solving to solveVoting'"/>
        </transition>
    </state>

    <!-- SolveVoting state  -->
    <state id="solveVoting">
        <datamodel>
            <data id="counted" expr="0"></data>
        </datamodel>
        <onentry>
            <log label="solveVoting" expr="' entry solveVoting state '"/>
            <userTask id="solveVoting" name="SolveVoteTask" candidateGroups="SolveVoter"
                      instancesExpr="solveVoteCount">
                <form src="form/solveVote.jsp">

                </form>
            </userTask>
        </onentry>
        <transition event="solveVoteComplete">
            <assign location="counted" expr="counted + 1"></assign>

            <log label="solveVoting" expr="'solveVoteComplete ,inner transition '"/>
        </transition>
        <transition cond="counted eq solveVoteCount" target="end">
        <!-- send evetn to parent state machine -->
            <send event="to_waitResult" type="scxml" messageMode="TO_PARENT"></send>
            <log label="solveVoting" expr="'transition from solveVoting to end '"/>
        </transition>
    </state>

<!-- end state -->
    <final id="end">
        <onentry>
            <log label="end" expr="' crowdsourcingTask end '"/>
        </onentry>
    </final>
</scxml>
```


