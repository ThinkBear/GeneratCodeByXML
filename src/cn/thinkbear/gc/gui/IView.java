package cn.thinkbear.gc.gui;

/**
 * 视图接口
 * 
 * @author ThinkBear
 * @date 2017年2月18日 下午10:16:55
 */

public interface IView {
	/**
	 * 完成组件初始化
	 */
	public void doCase();
	/**
	 * 将组件添加到面板
	 */
	public void doAppend();
	/**
	 * 组件的属性设置
	 */
	public void doSet();

}
