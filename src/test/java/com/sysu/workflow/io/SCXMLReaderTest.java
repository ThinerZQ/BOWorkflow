
package com.sysu.workflow.io;

import com.sysu.workflow.Evaluator;
import com.sysu.workflow.SCXMLExecutor;
import com.sysu.workflow.SCXMLTestHelper;
import com.sysu.workflow.TriggerEvent;
import com.sysu.workflow.engine.SCXMLInstanceManager;
import com.sysu.workflow.env.MulitStateMachineDispatcher;
import com.sysu.workflow.env.SimpleErrorReporter;
import com.sysu.workflow.env.jexl.JexlEvaluator;
import com.sysu.workflow.model.SCXML;
import org.junit.*;

import java.net.URL;
import java.util.Map;


/**
 * Unit tests
 */
public class SCXMLReaderTest {


    /**
     * Test the implementation
     */
    @Test
    public void testSCXMLReader() throws Exception {
        URL url = SCXMLTestHelper.getResource("subStateMachine.xml");
        SCXML scxml = new SCXMLReader().read(url);

        Assert.assertNotNull(scxml);
    }

    /**
     * Test the implementation
     */
    @Test
    public void testExecutor() throws Exception {
        URL url = SCXMLTestHelper.getResource("subStateMachine.xml");
        SCXML scxml = new SCXMLReader().read(url);
        //实例化数据模型解析器
        Evaluator evaluator = new JexlEvaluator();


        //实例化引擎
        SCXMLExecutor executor = new SCXMLExecutor(evaluator, new MulitStateMachineDispatcher(), new SimpleErrorReporter());

        executor.setStateMachine(scxml);

        executor.go();


        executor.triggerEvent(new TriggerEvent("start", TriggerEvent.SIGNAL_EVENT));

        executor.triggerEvent(new TriggerEvent("decomposeVoteComplete",TriggerEvent.SIGNAL_EVENT));

        Map<String,SCXMLExecutor> map = SCXMLInstanceManager.getRunningSCXMLInstanceExecutorMap();

        //将多个实例之间的关系保存起来了，
        Assert.assertEquals(4,map.size());

    }


}

