package main;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Database.ConnectDatabase;
import XML.FromXML;
import XML.ToXML;
import form.ChatLeft;
import form.ChatLeftAvatar;
import form.ChatRight;
import form.User;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import swing.ImageAvatar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import net.miginfocom.swing.MigLayout;

public class ChatHome {

	ConnectDatabase con = new ConnectDatabase();
	private JTextArea txtMess;
	private JFrame frame;
	private ImageAvatar imageAvatar;
	private JPanel pnChat2;
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	private JPanel pnList;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatHome window = new ChatHome("anh");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChatHome(String user) {
		initialize(user);
		loadUser(user);
		loadAvatar(user);
		connectToServer();
		receiveMessages(user);
	}

	private void connectToServer() {
		try {
			client = new Socket("localhost", 1107);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void receiveMessages(String user) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					String line;
					while ((line = in.readLine()) != null) {
						FromXML fromXML = new FromXML(line);
						addReceiveMess(fromXML.getMess(), fromXML.getSender());
						loadUser(user);
						pnList.validate();
						pnList.repaint();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	private void initialize(String user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(user);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);

		JPanel pnListUser = new JPanel();
		pnListUser.setBackground(Color.WHITE);
		pnListUser.setBounds(6, 0, 194, 572);
		frame.getContentPane().add(pnListUser);
		pnListUser.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		pnListUser.add(scrollPane, BorderLayout.CENTER);

		pnList = new JPanel();
		pnList.setBackground(Color.WHITE);
		scrollPane.setViewportView(pnList);
		pnList.setLayout(new MigLayout("fillx", "5[175.00]", "5[]5"));

		JPanel pnAvatar = new JPanel();
		pnAvatar.setLayout(null);
		pnAvatar.setBackground(Color.WHITE);
		pnAvatar.setBounds(800, 0, 194, 572);
		frame.getContentPane().add(pnAvatar);

		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					try {
						byte[] imageData = Files.readAllBytes(selectedFile.toPath());
						con.updateAvatar(user, imageData);
						imageAvatar.setImage(con.getAvatar(user));

						imageAvatar.repaint();
						imageAvatar.validate();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(ChatHome.class.getResource("/img/editAvatar.png")));
		btnNewButton.setBounds(133, 320, 40, 40);
		pnAvatar.add(btnNewButton);

		imageAvatar = new ImageAvatar();
		imageAvatar.setBorderSize(0);
//		imageAvatar.setImage(new ImageIcon(ChatHome.class.getResource("/img/avatar.png")));
		imageAvatar.setBounds(6, 170, 182, 182);
		pnAvatar.add(imageAvatar);

		JPanel pnChat = new JPanel();
		pnChat.setBackground(new Color(238, 237, 238));
		pnChat.setBounds(212, 0, 576, 572);
		frame.getContentPane().add(pnChat);
		pnChat.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 532, 576, 40);
		pnChat.add(panel);
		panel.setLayout(null);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = txtMess.getText();
				if (msg.length() > 0 && msg != null) {
					out.println((new ToXML()).toXML(user, msg));
					txtMess.setText("");
					addSendMess(msg);
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(ChatHome.class.getResource("/img/send.png")));
		btnNewButton_1.setBounds(535, 0, 40, 40);
		panel.add(btnNewButton_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(0, 0, 523, 40);
		panel.add(scrollPane_1);

		txtMess = new JTextArea();
		scrollPane_1.setViewportView(txtMess);
		txtMess.setLineWrap(true);
		txtMess.setWrapStyleWord(true);

		JScrollPane scChat = new JScrollPane();
		scChat.setBounds(0, 0, 576, 512);
		pnChat.add(scChat);

		pnChat2 = new JPanel();
		pnChat2.setBackground(Color.WHITE);
		scChat.setViewportView(pnChat2);
		pnChat2.setLayout(new MigLayout("fillx", "", "5[]5"));

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void addReceiveMess(String text, String user) {
		ChatLeftAvatar item = new ChatLeftAvatar(user);
		item.setText(text);
		item.setAvatar(user);
		pnChat2.add(item, "wrap, w ::80%");
		pnChat2.repaint();
		pnChat2.validate();
	}

	public void addSendMess(String text) {
		ChatRight item = new ChatRight();
		item.setText(text);
		pnChat2.add(item, "wrap, al right, w ::80%");
		pnChat2.repaint();
		pnChat2.revalidate();
	}

	public void loadUser(String user) {
		pnList.removeAll();
		List<String> list = con.getListUser(user);
		for (String i : list) {
			pnList.add(new User(i), "wrap");
		}
	}

	public void loadAvatar(String user) {
		imageAvatar.setImage(con.getAvatar(user));
	}

}
