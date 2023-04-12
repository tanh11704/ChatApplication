package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import form.PnLogin;

import java.awt.Color;
import java.awt.CardLayout;
import form.PnRegister;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/img/background.jpg")));
		lblNewLabel.setBounds(6, 6, 679, 560);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new LineBorder(new Color(130, 153, 235)));
		panel.setBounds(694, 70, 300, 435);
		frame.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		PnLogin pnLogin = new PnLogin();
		panel.add(pnLogin, "name_28086450806167");
		
		PnRegister pnRegister = new PnRegister();
		panel.add(pnRegister, "name_9516254122500");
		
		JButton btgChange = new JButton("ĐĂNG KÍ");
		btgChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					CardLayout cardLayout = (CardLayout) panel.getLayout();
					cardLayout.next(panel);	
					if (btgChange.getText().equals("ĐĂNG NHẬP")) {
						btgChange.setText("ĐĂNG KÍ");
					} else {
						btgChange.setText("ĐĂNG NHẬP");
					}
			}
		});
		btgChange.setForeground(new Color(130, 154, 235));
		btgChange.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 16));
		btgChange.setBounds(250, 515, 161, 35);
		frame.getContentPane().add(btgChange);
		
		frame.setLocationRelativeTo(null);
	}
}
