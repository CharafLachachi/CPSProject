package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestPlayer.class, 
        TestEditMap.class,
        TestCow.class,
        TestEngine.class,
        TestRessources.class
        })
public class RunTests  {
 public RunTests() {
}
}
