package com.almundo.callcenter.server.main;

import org.junit.Test;

import com.almundo.callcenter.beans.Client;

//TODO: Auto-generated Javadoc
/**
* The Class ClientTest. 
* 
* @author Marcelo Daniel Martini
* @version 1.0
* @since 23/03/2019
*
*/
public class ClientTest {
	
    @Test(expected = NullPointerException.class)
    public void testClientCreationWithNullParameter() {
        new Client(null,null,null,null);
    }
}
