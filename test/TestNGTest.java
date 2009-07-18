import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alexbleasdale.xramp.xmldb.ExistDBTools;


public class TestNGTest {
	
	@BeforeClass
	public void setUp() throws Exception{
		ExistDBTools edbt = new ExistDBTools();
		edbt.startEmbeddedExist();
	}
	
	@Test
	public void testCreateCollection() throws Exception{
		Assert.assertTrue(true);
	}

}
