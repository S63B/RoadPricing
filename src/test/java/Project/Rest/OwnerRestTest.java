package Project.Rest;

import Project.Rest.Utility.ExtendedRestTestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Nino Vrijman on 2-5-2017.
 */
public class OwnerRestTest extends ExtendedRestTestCase {

    public OwnerRestTest() {
        super("http://localhost:8080/");
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUserInfo() throws Exception {
        assertTrue(false);
    }

}