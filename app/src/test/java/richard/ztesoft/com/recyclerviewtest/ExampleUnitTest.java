package richard.ztesoft.com.recyclerviewtest;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        for (int i=0;i<7;i++){
            int mod = i % 5;
            System.out.println("mode = "+mod);
        }

    }
}