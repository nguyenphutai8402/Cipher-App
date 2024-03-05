package Utils;

import java.awt.Component;

import javax.swing.JFrame;

public class Visibility {
	public static void hide(Component ...components) {
		for(Component component : components ) {
			component.setVisible(false);
		}
	}
	public static void show(Component ...components) {
		for(Component component : components ) {
			component.setVisible(true);
		}
	}
}
