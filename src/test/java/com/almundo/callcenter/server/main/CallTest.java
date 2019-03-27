package com.almundo.callcenter.server.main;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

import com.almundo.callcenter.beans.Call;


//TODO: Auto-generated Javadoc
/**
* The Class CallTest. 
* 
* @author Marcelo Daniel Martini
* @version 1.0
* @since 23/03/2019
*
*/
public class CallTest {

    @Test(expected = NullPointerException.class)
    public void testCallCreationWithNullParameter() {
        new Call(null, null, null, null, null, null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBuildListOfRandomCallsCreationWithInvalidFirstParameter() {
    	CountDownLatch callConcurrent = new CountDownLatch(1);
    	Call.buildListOfRandomCalls(-1,callConcurrent);
    }

    @Test
    public void testRandomCallsCreationWithValidParameters() {
        Integer listSizeCall = 10;
        CountDownLatch callConcurrent = new CountDownLatch(1);
        List<Call> calls = Call.buildListOfRandomCalls(listSizeCall,callConcurrent);
        assertNotNull(calls);
        assertTrue(calls.size() == listSizeCall);
    }
}