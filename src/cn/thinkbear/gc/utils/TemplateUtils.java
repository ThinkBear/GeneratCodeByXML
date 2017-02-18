package cn.thinkbear.gc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/**
 * 通过此工具类，完成模板数据的读取、保存操作
 * 
 * @author ThinkBear
 * @date 2017年2月18日 下午10:36:11
 */
public class TemplateUtils {
	
	public static final String IMPORT_FLAG = "{Import}";
	public static final String DEF_FLAG = "{Def}";
	public static final String INIT_FLAG = "{Init}";
	
	private final String FILE_PATH = "source" + File.separator + "Template.thinkbear";
	private File file = null;
	private InputStreamReader input = null;
	private OutputStreamWriter out = null;

	private volatile static TemplateUtils instance = null;

	public static TemplateUtils getInstance() {
		if (instance == null) {
			synchronized (TemplateUtils.class) {
				if (instance == null) {
					instance = new TemplateUtils();
				}
			}
		}

		return instance;
	}

	private TemplateUtils() {
		this.file = new File(FILE_PATH);
	}

	public String getTemplateData() {
		if (this.file.exists()) {
			try {
				this.input = new InputStreamReader(new FileInputStream(this.file));
				StringBuffer buf = new StringBuffer();
				char[] data = new char[512];
				int temp = 0;
				while ((temp = this.input.read(data)) != -1) {
					buf.append(new String(data, 0, temp));
				}
				return buf.toString();
			} catch (Exception e) {
			} finally {
				if (this.input != null) {
					try {
						this.input.close();
					} catch (IOException e) {
					}
					this.input = null;
				}
			}

		}
		return null;
	}

	public boolean saveTemplateData(String data) {
		boolean flag = false;
		if (!this.file.getParentFile().exists()) {
			this.file.getParentFile().mkdirs();
		}

		try {
			this.out = new OutputStreamWriter(new FileOutputStream(this.file));
			this.out.write(data);
			this.out.flush();
			flag = true;
		} catch (Exception e) {
		} finally {
			if (this.out != null) {
				try {
					this.out.close();
				} catch (IOException e) {
				}
				this.out = null;
			}
		}
		return flag;
	}

}
