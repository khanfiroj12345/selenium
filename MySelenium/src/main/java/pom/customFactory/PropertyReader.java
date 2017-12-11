package pom.customFactory;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//read a property file, accepts file path, return "properties" Variable
public class PropertyReader 
{
	static Properties prop;
	static FileInputStream fis=null;
	//accepts property file path
	public static Properties read(String path)
	{
		prop=new Properties();
		
		try
		{
			File file=new File(path);
		   fis=new FileInputStream(file);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try 
		{
			prop.load(fis);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		//return Properties type variable
		return prop;
	}
}