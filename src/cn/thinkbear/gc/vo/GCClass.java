package cn.thinkbear.gc.vo;

import java.io.IOException;

import cn.thinkbear.gc.utils.PackageClassNameUtils;

/**
 * 
 * Sample:
 * 源数据：
 *  <TextView
        android:id="@+id/info"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Hello World!" />
        
 * 正则匹配后：
 * <TextView         android:id="@+id/info"
 * 
 * 进一步数据分析：
 * className: TextView
 * id: info
 * packageClassName: android.widget.TextView
 * 
 * 
 * @author ThinkBear
 * @date 2017年2月18日 下午9:51:14
 */
public class GCClass {
	
	private GCClass(){
		
	}
	
	public static GCClass getInstance(String data){
		String className = getClassNameByData(data);
		if(className == null){
			return null;
		}
		String id = getIdByData(data);
		if(id == null){
			return null;
		}
		

		GCClass gc = new GCClass();
		gc.setId(id);
		
		int dotIndex = className.lastIndexOf(".");
		if(dotIndex != -1){
			String newClassName = className.substring(dotIndex +1);
			gc.setClassName(newClassName);
			gc.setPackageClassName(className);
		}else{
			gc.setClassName(className);
			try {
				gc.setPackageClassName(PackageClassNameUtils.getInstance().getValue(className));
			} catch (IOException e) {
			}
		}
		
		
		return gc;
	}
	
	private static String getClassNameByData(String data){
		String lastKey = "<";
		String endKey = " ";
		
		int lastIndex = data.lastIndexOf(lastKey);
		if(lastIndex != -1){
			lastIndex = lastIndex + lastKey.length();
			int endIndex = data.indexOf(endKey,lastIndex);
			if(endIndex != -1){
				return data.substring(lastIndex, endIndex);
			}
		}
		
		return null;
	}
	
	private static String getIdByData(String data){
		String startKey = "android:id=\"@+id/";
		String endKey = "\"";
		
		int startIndex = data.indexOf(startKey);
		if(startIndex != -1){
			startIndex = startIndex + startKey.length();
			int endIndex = data.indexOf(endKey,startIndex);
			if(endIndex != -1){
				return data.substring(startIndex, endIndex);
			}
		}
		
		return null;
	}
	
	private String className;
	private String id;
	private String packageClassName;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPackageClassName() {
		return packageClassName;
	}
	public void setPackageClassName(String packageClassName) {
		this.packageClassName = packageClassName;
	}
	
	
	
}
