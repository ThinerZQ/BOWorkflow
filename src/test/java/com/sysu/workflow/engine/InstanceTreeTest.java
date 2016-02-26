package com.sysu.workflow.engine;

import org.junit.Test;

/**
 * Created by zhengshouzi on 2015/12/26.
 */
public class InstanceTreeTest {

    @Test
    public void testInstanceTree(){
        SCXMLInstanceTree scxmlInstanceTree = new SCXMLInstanceTree("3","zq");


        scxmlInstanceTree.insert("3","2","zq");
        scxmlInstanceTree.insert("3","6","zq");
        scxmlInstanceTree.insert("3","10","zq");
        scxmlInstanceTree.insert("2","5","jiade");
        scxmlInstanceTree.insert("6","7","zq");
        scxmlInstanceTree.insert("10","1","tianxia");
        scxmlInstanceTree.insert("10","9","zq");
        scxmlInstanceTree.insert("1","8","zq");


        //输出结果“3，2，5，6，7，10，1，8，9，”正确，
        scxmlInstanceTree.depthFirstSearch(scxmlInstanceTree.getRoot());
        System.out.println();
        System.out.println(scxmlInstanceTree.getChildTreeNodeSessionId("3"));



    }
}
