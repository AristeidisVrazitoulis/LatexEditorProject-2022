package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({CreateDocumentTest.class, CommandsTest.class,AddLatexCommandTest.class, VersionsManagerTest.class})
public class AllTests {
	
}
