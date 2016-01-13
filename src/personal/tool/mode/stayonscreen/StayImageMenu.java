/**
 * Author : Yufei,T
 * Time : 5:24:07 PM
 * Description :
 */
package personal.tool.mode.stayonscreen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JWindow;

public class StayImageMenu extends JPopupMenu{

	JMenuItem closeImageItem = new JMenuItem("close");
	public StayImageMenu() {
		super();
		closeImageItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				 closeImage();
			 }
		});
		this.add(closeImageItem);
	}
	
	public void closeImage() {
		((JWindow)getInvoker()).dispose();
	}
	

}

