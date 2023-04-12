package form;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Database.ConnectDatabase;
import main.ChatHome;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PnLogin extends JPanel {
	private JTextField txtTdn;
	private JPasswordField txtPass;

	/**
	 * Create the panel.
	 */
	public PnLogin() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ĐĂNG NHẬP");
		lblNewLabel.setForeground(new Color(130, 153, 235));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 85, 300, 25);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 122, 284, 25);
		add(lblNewLabel_1);
		
		txtTdn = new JTextField();
		txtTdn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtTdn.setBounds(12, 159, 282, 26);
		add(txtTdn);
		txtTdn.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 197, 284, 25);
		add(lblNewLabel_1_1);
		
		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtPass.setBounds(12, 234, 282, 26);
		add(txtPass);
		
		JCheckBox jcbShowPass = new JCheckBox("Hiện mật khẩu");
		jcbShowPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jcbShowPass.isSelected()) {
					txtPass.setEchoChar((char) 0);
				} else {
					txtPass.setEchoChar('\u2022');
				}
			}
		});
		jcbShowPass.setBounds(10, 272, 128, 23);
		add(jcbShowPass);
		
		JButton btnNewButton = new JButton("Đăng nhập");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = txtTdn.getText();
				String pass = txtPass.getText();
				if ((new ConnectDatabase()).checkLogin(user, pass)) {
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
					new ChatHome(user);
					((Frame) SwingUtilities.getWindowAncestor(PnLogin.this)).dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
				}
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnNewButton.setBounds(90, 307, 120, 35);
		add(btnNewButton);

	}
}
