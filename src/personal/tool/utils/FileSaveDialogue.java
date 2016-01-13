/**
 * Author : Yufei,T
 * Time : 9:42:05 AM
 * Description :
 */
package personal.tool.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
public class FileSaveDialogue {

	public String getSavePathByFileChooser() {
		JFileChooser fileChooser = new JFileChooser();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		fileChooser.setLocation(
				(int) (screenSize.getWidth() - fileChooser.getWidth()) / 2,
				(int) (screenSize.getHeight() - fileChooser.getHeight()) / 2);
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		String initPath = System.getProperties().getProperty("user.home");
		try {
			fileChooser.setCurrentDirectory(new File(initPath + "/Pictures"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		FileFilter pngFilter = new FileNameExtensionFilter("PNG(*.png;)", "png");
		
		fileChooser.setFileFilter(pngFilter);

		fileChooser.setAcceptAllFileFilterUsed(false);

		int userSelection = fileChooser.showSaveDialog(null);
		String userChoosePath = "";
		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToBeSaved = fileChooser.getSelectedFile();
			if (!fileToBeSaved.getAbsolutePath().endsWith(".png")) {
				fileToBeSaved = new File(fileChooser.getSelectedFile() + ".png");
			}
			userChoosePath = fileChooser.getSelectedFile() + ".png";
			fileChooser = null;
		}
		return userChoosePath;
	}
}
