
package com.sysu.workflow.io;

import com.sysu.workflow.Evaluator;
import com.sysu.workflow.SCXMLExecutor;
import com.sysu.workflow.SCXMLTestHelper;
import com.sysu.workflow.TriggerEvent;
import com.sysu.workflow.env.SimpleErrorReporter;
import com.sysu.workflow.env.jexl.JexlEvaluator;
import com.sysu.workflow.model.SCXML;
import org.junit.*;

import java.net.URL;


/**
 * Unit tests
 */
public class SCXMLReaderTest {


    /**
     * Test the implementation
     */    
    @Test
    public void testSCXMLReader() throws Exception {
        URL url = SCXMLTestHelper.getResource("scxml-initial-attr.xml");
        SCXML scxml = new SCXMLReader().read(url);

        Assert.assertNotNull(scxml);
    }
    /**
     * Test the implementation
     */
    @Test
    public void testExecutor() throws Exception {
        URL url = SCXMLTestHelper.getResource("scxml-initial-attr.xml");
        SCXML scxml = new SCXMLReader().read(url);
        //实例化数据模型解析器
        Evaluator evaluator = new JexlEvaluator();

        //实例化引擎
        SCXMLExecutor executor = new SCXMLExecutor(evaluator, null, new SimpleErrorReporter());

        executor.setStateMachine(scxml);

        executor.go();

        executor.triggerEvent(new TriggerEvent("to.end",TriggerEvent.SIGNAL_EVENT));



    }

    

}

