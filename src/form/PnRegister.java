package form;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import Database.ConnectDatabase;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;

public class PnRegister extends JPanel {
	
	ConnectDatabase con = new ConnectDatabase();
	private JTextField txtTdn;
	private JPasswordField txtPass;
	private JPasswordField txtPass2;

	/**
	 * Create the panel.
	 */
	public PnRegister() {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ĐĂNG KÍ");
		lblNewLabel.setForeground(new Color(130, 153, 235));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(0, 50, 300, 25);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tên đăng nhập");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 87, 284, 25);
		add(lblNewLabel_1);
		
		txtTdn = new JTextField();
		txtTdn.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtTdn.setBounds(10, 124, 284, 26);
		add(txtTdn);
		txtTdn.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mật khẩu");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(10, 162, 284, 25);
		add(lblNewLabel_1_1);
		
		txtPass = new JPasswordField();
		txtPass.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtPass.setBounds(10, 199, 284, 26);
		add(txtPass);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nhập lại mật khẩu");
		lblNewLabel_1_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		lblNewLabel_1_1_1.setBounds(10, 237, 284, 25);
		add(lblNewLabel_1_1_1);
		
		txtPass2 = new JPasswordField();
		txtPass2.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		txtPass2.setBounds(10, 274, 284, 26);
		add(txtPass2);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Hiện mật khẩu");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					txtPass.setEchoChar((char) 0);
					txtPass2.setEchoChar((char) 0);
				} else {
					txtPass.setEchoChar('\u2022');
					txtPass2.setEchoChar('\u2022');
				}
			}
		});
		chckbxNewCheckBox.setBounds(10, 312, 128, 23);
		add(chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("Đăng kí");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = txtTdn.getText();
				String pass = txtPass.getText();
				String pass2 = txtPass2.getText();
				byte[] imageData;

				if (user.equals("") || pass.equals("") || pass2.equals("")) {
					JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin!");
				} else if (!pass.equals(pass2)) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập 2 mật khẩu giống nhau!");
				} else if (con.checkTrungTaiKhoan(user) == true) {
					JOptionPane.showMessageDialog(null, "Tài khoản đã được đăng kí!");
				} else {
					try {
						imageData = Files.readAllBytes(Paths.get("/Users/trananh/Desktop/VKU/HK2/Lập Trình Java/Chat_Application/src/img/avatar.png"));
						con.addAccount(user, pass, imageData);
						JOptionPane.showMessageDialog(null, "Đăng ký thành công!");
						txtTdn.setText(""); txtPass.setText(""); txtPass2.setText("");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		});
		btnNewButton.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnNewButton.setBounds(90, 347, 120, 35);
		add(btnNewButton);

	}
}
