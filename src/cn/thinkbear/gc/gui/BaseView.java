package cn.thinkbear.gc.gui;
/**
 * 视图接口的抽象类
 * 项目中含有组件的类都extends此抽象类
 * 
 * 
 * 
 * @author ThinkBear
 * @date 2017年2月18日 下午10:20:01
 */
public abstract class BaseView implements IView{
	/**
	 * 完成视图的创建工作
	 */
	public void doCreateGui(){
		this.doCase();
		this.doAppend();
		this.doSet();
	}
}
