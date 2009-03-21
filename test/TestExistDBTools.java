import junit.framework.*; 

import com.alexbleasdale.xramp.xmldb.ExistDBTools;


public class TestExistDBTools extends TestCase {

	public void testCreateCollection(){
		ExistDBTools edbt = new ExistDBTools();
		
		try {
			
			edbt.startEmbeddedExist();
			
			edbt.getCollection("docs2");
			
			if (edbt.getCurrentCollection() != "docs2"){
				// current collection does not exist
				edbt.createCollection("docs2");
			}
			
			assertEquals("docs2", edbt.getCurrentCollection());
			int r = edbt.getResourceCount();
			System.out.println(r);
			
			edbt.storeXMLDoc("resources/conf.xml");
			assertEquals(r+1, edbt.getResourceCount());
			
			// second test - switch collections and add docs
			
			if(edbt.getCollection("docs") == null){
				edbt.createCollection("docs");
			}
			assertEquals("docs", edbt.getCurrentCollection());
			
			int r2 = edbt.getResourceCount();
			System.out.println(r2);
			
			edbt.storeXMLDoc("resources/conf.xml");
			assertEquals(r2+1, edbt.getResourceCount());
			
			
			if(edbt.getCollection("junit") == null){
				edbt.createCollection("junit");
			}
			assertEquals("junit", edbt.getCurrentCollection());
			edbt.storeXMLDoc("resources/conf.xml");
			edbt.getResources();
			
			// try creating a child collection from root
			if(edbt.getCollection("junit/test") == null){
				edbt.createCollection("junit/test");
			}
			assertEquals("junit/test", edbt.getCurrentCollection());
			edbt.storeXMLDoc("resources/conf.xml");
			edbt.getResources();
			
			if(edbt.getCollection("junit/test/09") == null){
				edbt.createCollection("junit/test/09");
			}
			assertEquals("junit/test/09", edbt.getCurrentCollection());
			//edbt.returnXMLDocAtIndex(1);
		    edbt.getXMLDocByName("accdd798.xml");
			edbt.storeXMLDoc("resources/conf.xml");
		    edbt.getResources();
		
			
			edbt.shutdownExist();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
