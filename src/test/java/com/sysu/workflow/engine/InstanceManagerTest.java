package com.sysu.workflow.engine;

import com.sysu.workflow.*;
import com.sysu.workflow.env.MulitStateMachineDispatcher;
import com.sysu.workflow.env.SimpleErrorReporter;
import com.sysu.workflow.env.jexl.JexlEvaluator;
import com.sysu.workflow.io.SCXMLReader;

import com.sysu.workflow.model.SCXML;
import org.junit.Test;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Date: 2016/2/19
 * Time: 10:43
 * User: ThinerZQ
 * GitHub: <a>https://github.com/ThinerZQ</a>
 * Blog: <a>http://www.thinerzq.me</a>
 * Email: 601097836@qq.com
 */
public class InstanceManagerTest {
    @Test
    public void testInstanceManager() {

      /*  try {

            URL url = SCXMLTestHelper.getResource("crowdsourcingTest.xml");
            SCXML scxml = new SCXMLReader().read(url);
            //实例化数据模型解析器
            Evaluator evaluator = new JexlEvaluator();


            //实例化引擎
            SCXMLExecutor executor = new SCXMLExecutor(evaluator, new MulitStateMachineDispatcher(), new SimpleErrorReporter());

            executor.setStateMachine(scxml);

            executor.go();


            Map<String,SCXMLExecutor> map = SCXMLInstanceManager.getRunningSCXMLInstanceExecutorMap();
            SCXMLExecutor temp = map.get(executor.getGlobalContext().getSystemContext().get(SCXMLSystemContext.SESSIONID_KEY));

            Map<String, Object> dataMap = new HashMap<String, Object>();
            dataMap.put("simple", 1);


            temp.triggerEvent(new TriggerEvent("judgeComplete", TriggerEvent.SIGNAL_EVENT, dataMap));
            temp.triggerEvent(new TriggerEvent("judgeComplete", TriggerEvent.SIGNAL_EVENT, dataMap));
            temp.triggerEvent(new TriggerEvent("judgeComplete", TriggerEvent.SIGNAL_EVENT, dataMap));


        }catch (Exception e){
            e.printStackTrace();
        }*/
    }
}
