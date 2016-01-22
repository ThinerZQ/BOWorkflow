package com.sysu.workflow.model.extend;

import com.sysu.workflow.*;
import com.sysu.workflow.engine.SCXMLInstanceManager;
import com.sysu.workflow.engine.SCXMLInstanceTree;
import com.sysu.workflow.env.SimpleErrorReporter;
import com.sysu.workflow.io.SCXMLReader;
import com.sysu.workflow.model.Action;
import com.sysu.workflow.model.ModelException;
import com.sysu.workflow.model.SCXML;

import java.net.URL;

/**
 * Created by zhengshouzi on 2016/1/2.
 */
public class SubStateMachine extends Action {

    private String src;
    private int instances=1;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public int getInstances() {
        return instances;
    }

    public void setInstances(int instances) {
        this.instances = instances;
    }

    @Override
    public void execute(ActionExecutionContext exctx) throws ModelException, SCXMLExpressionException {

        //得到资源文件路径
        final URL url = this.getClass().getClassLoader().getResource(getSrc());
        SCXML scxml=null;
        //根据src的值，启动状态机
        try {
            scxml = SCXMLReader.read(url);
        } catch (Exception e) {
            System.out.println("couldn't find :"+getSrc());
            e.printStackTrace();
        }

        for (int i=0;i<getInstances();i++){

            Evaluator evaluator = EvaluatorFactory.getEvaluator(scxml);

            SCXMLExecutionContext currentExecutionContext = (SCXMLExecutionContext) exctx.getInternalIOProcessor();

            SCXMLInstanceTree instanceTree = currentExecutionContext.getInstanceTree();
            SCXMLExecutor executor =new SCXMLExecutor(evaluator,null,new SimpleErrorReporter(),null,instanceTree);
            executor.setStateMachine(scxml);

            //设置引擎执行的根上下文
            Context rootContext = evaluator.newContext(null);
            executor.setRootContext(rootContext);

            //开始启动流程
            executor.go();

            //维护关系
            String currentSessionId = (String) currentExecutionContext.getScInstance().getSystemContext().get(SCXMLSystemContext.SESSIONID_KEY);

            String subStateMachineSessionId = (String) executor.getGlobalContext().getSystemContext().get(SCXMLSystemContext.SESSIONID_KEY);

            instanceTree.insert(currentSessionId,subStateMachineSessionId,executor.getStateMachine().getName());

            //将当前的Executor加入到  实例管理器里面

            SCXMLInstanceManager.setSCXMLInstance(executor);

        }
    }
}
