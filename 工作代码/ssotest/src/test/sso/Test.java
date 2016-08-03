package test.sso;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.POIXMLProperties.CoreProperties;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Test {
	
	public static void main(String[] args) {
		System.out.println(1111);
		try {
			testReadByExtractor();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	   /**
	    * 通过XWPFWordExtractor访问XWPFDocument的内容
	    * @throws Exception
	    */
	   public static void testReadByExtractor() throws Exception {
	      InputStream is = new FileInputStream("D:\\test.docx");
	      XWPFDocument doc = new XWPFDocument(is);
	      XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
	      String text = extractor.getText();
	      System.out.println(text);
	      CoreProperties coreProps = extractor.getCoreProperties();
	      printCoreProperties(coreProps);
	     close(is);
	   }
	  
	   /**
	    * 输出CoreProperties信息
	    * @param coreProps
	    */
	   private static void printCoreProperties(CoreProperties coreProps) {
	      System.out.println(coreProps.getCategory());   //分类
	      System.out.println(coreProps.getCreator()); //创建者
	      System.out.println(coreProps.getCreated()); //创建时间
	      System.out.println(coreProps.getTitle());   //标题
	   }
	  
	   /**
	    * 关闭输入流
	    * @param is
	    */
	   private static void close(InputStream is) {
	      if (is != null) {
	         try {
	            is.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
	      }
	   }
	  
	}
