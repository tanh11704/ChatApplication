package form;

import javax.swing.JPanel;

import Database.ConnectDatabase;
import swing.ImageAvatar;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import net.miginfocom.swing.MigLayout;

public class User extends JPanel {
	
	private ImageAvatar imageAvatar;

	/**
	 * Create the panel.
	 */
	public User(String user) {
		setBackground(Color.WHITE);
		setLayout(new MigLayout("fillx, filly", "0[50]10[100]0", "0[50]0"));
		
		imageAvatar = new ImageAvatar();
		imageAvatar.setBorderSize(0);
		imageAvatar.setImage(new ImageIcon(User.class.getResource("/img/avatar.png")));
		add(imageAvatar, "cell 0 0,grow");
		
		JLabel lblNewLabel = new JLabel(user);
		add(lblNewLabel, "cell 1 0,grow");

		loadAvatar(user);
	}
	
	public void loadAvatar(String user) {
		ImageIcon icon = (new ConnectDatabase()).getAvatar(user);
		imageAvatar.setImage(icon);
	}
}
