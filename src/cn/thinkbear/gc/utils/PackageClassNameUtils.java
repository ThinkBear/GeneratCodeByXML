package cn.thinkbear.gc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 通过此工具类，取得完整的包.类名称
 * 
 * @author ThinkBear
 * @date 2017年2月18日 下午5:12:23
 */
public class PackageClassNameUtils {
	private final String FILE_PATH = "source"+File.separator+"PackageClassName.properties";
	private Properties properties = null;

	private volatile static PackageClassNameUtils instance = null;
	
	
	public static PackageClassNameUtils getInstance() throws IOException{
		if(instance == null){
			synchronized(PackageClassNameUtils.class){
				if(instance == null){
					instance = new PackageClassNameUtils();
				}
			}
		}
		
		return instance;
	}
	
	private PackageClassNameUtils() throws IOException{
		File file = new File(FILE_PATH);
		if(!file.exists()){
			throw new FileNotFoundException(file.getAbsolutePath()+"文件不存在");
		}
		this.properties = new Properties();
		this.properties.load(new FileInputStream(file));
	}
	
	public String getValue(String key){
		return this.properties.getProperty(key);
	}
}
