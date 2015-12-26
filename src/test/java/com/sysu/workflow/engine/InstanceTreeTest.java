package com.sysu.workflow.engine;

import org.junit.Test;

/**
 * Created by zhengshouzi on 2015/12/26.
 */
public class InstanceTreeTest {

    @Test
    public void testInstanceTree(){
        SCXMLInstanceTree scxmlInstanceTree = new SCXMLInstanceTree("3");


        scxmlInstanceTree.insert("3","2");
        scxmlInstanceTree.insert("3","6");
        scxmlInstanceTree.insert("3","10");
        scxmlInstanceTree.insert("2","5");
        scxmlInstanceTree.insert("6","7");
        scxmlInstanceTree.insert("10","1");
        scxmlInstanceTree.insert("10","9");
        scxmlInstanceTree.insert("1","8");


        //输出结果“3，2，5，6，7，10，1，8，9，”正确，
        scxmlInstanceTree.depthFirstSearch(scxmlInstanceTree.getRoot());



    }
}
