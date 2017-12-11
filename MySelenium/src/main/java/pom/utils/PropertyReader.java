package pom.utils;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

//read a property file, accepts file path, return "properties" Variable
public class PropertyReader 
{
	static Properties prop=new Properties();
	static FileInputStream fis=null;
	//accepts property file path
	public static Properties read(String path)
	{
		try
		{
		   fis=new FileInputStream(path);
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try 
		{
			prop.load(fis);
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return prop;
	}
	

}
