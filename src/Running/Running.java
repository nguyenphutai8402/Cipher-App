package Running;

import java.awt.EventQueue;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import Controller.ControllerAll;
import ControllerOfView.DoiXungController;
import ControllerOfView.MenuController;
import View.Home;

public class Running {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Home home = new Home();
					home.setVisible(true);
					MenuController controller = new MenuController(home);
					ControllerAll controllerAll = new ControllerAll(home);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
}
