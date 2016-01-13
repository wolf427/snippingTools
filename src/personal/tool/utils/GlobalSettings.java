/**
 * Author : Yufei,T
 * Time : 4:25:23 PM
 * Description :
 */
package personal.tool.utils;
public class GlobalSettings {
	//imageHandleMode
	//stayOnScreenMode
	private static String mode="imageHandleMode";

	public static synchronized String getMode() {
		return mode;
	}

	public static synchronized void setMode(String mode) {
		GlobalSettings.mode = mode;
	}

}

