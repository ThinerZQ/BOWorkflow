package com.sysu.workflow.database;

import org.junit.Test;

/**
 * Created by zhengshouzi on 2015/12/12.
 */
public class H2Test {

    @Test
    public void initDatabaseTest(){

        try {
          H2.initDatabase();
        } catch (Exception e) {

        }
    }

}
