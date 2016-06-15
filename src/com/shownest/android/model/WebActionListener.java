package com.shownest.android.model;

/**
 * WebView动作的回调接口
 * 
 * @author bonepeople
 */
public interface WebActionListener
{
	/**
	 * 网页加载完毕回调的接口
	 */
	void onFinished(String _tag);

	/**
	 * 网页关闭时回调的接口
	 */
	void onClose(String _tag);

	/**
	 * 其他网页动作的回调接口
	 * 
	 * @param _action
	 *            自定义的动作
	 */
	void onAction(String _tag, String _action);
}
