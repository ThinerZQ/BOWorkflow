
package com.sysu.workflow.io;

import com.sysu.workflow.SCXMLTestHelper;
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
    public void testSCXMLReaderMicrowave03Sample() throws Exception {
        URL url = SCXMLTestHelper.getResource("scxml-initial-attr.xml");
        SCXML scxml = new SCXMLReader().read(url);
        Assert.assertNotNull(scxml);
    }
    

    

}

