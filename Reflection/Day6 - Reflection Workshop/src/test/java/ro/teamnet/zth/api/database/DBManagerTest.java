package ro.teamnet.zth.api.database;

import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ionutz on 29.04.2015.
 */
public class DBManagerTest {

    @Test
    public void testGetConnection(){
        Connection connection = DBManager.getConnection();

        assertNotNull(connection);
    }

    @Test
    public void testCheckConnection(){
        Connection connection = DBManager.getConnection();

        assertTrue(DBManager.checkConnection(connection));
    }
}
