/**
 * Author : Yufei,T
 * Time : 10:20:27 AM
 * Description :
 */
package personal.tool.frame;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import personal.tool.panel.ToolsbarPanel;

public class StartFrame extends JFrame {

    /**
     * Creates new form StartFrame
     */
    public StartFrame() {
        initComponents();
    }
    
//    private static StartFrame instance=null;
//    public static StartFrame getInstance(){
//    	if(instance == null)
//    		instance = new StartFrame();
//    	return instance;
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {
    	setTitle("Snapshot");
    	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    	int x = (int)(dimension.getWidth())*2/3;
    	int y = (int)(dimension.getHeight())>>2;
    	setLocation(x, y);

        toolsbarpanel = new ToolsbarPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(180, 100));
        getContentPane().add(toolsbarpanel, BorderLayout.PAGE_START);
        
        pack();
    }                       

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                   UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StartFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(StartFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(StartFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(StartFrame.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartFrame().setVisible(true);
            }
        });
    }

    private ToolsbarPanel toolsbarpanel;
}

