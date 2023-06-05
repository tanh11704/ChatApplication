package Database;

import java.awt.Image;
import java.awt.Toolkit;
import java.beans.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

import org.mindrot.jbcrypt.BCrypt;

public class ConnectDatabase {
	PreparedStatement pst = null;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	public ConnectDatabase() {
		connect();
	}

	public void connect() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionURL = "jdbc:sqlserver://localhost:1433;databaseName=DOANJAVAHK2;"
					+ "encrypt=true;trustServerCertificate=true;sslProtocol=TLSv1.2";
			con = DriverManager.getConnection(connectionURL, "sa", "Password.1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TÀI KHOẢN
	//Tạo tài khoản
	public void addAccount(String username, String matkhau, byte[] image) {
		try {
			
			pst = con.prepareStatement("INSERT INTO TaiKhoan VALUES(?, ?, ?)");
			pst.setString(1, username);
			pst.setString(2, matkhau);
			pst.setBytes(3, image);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//Kiểm tra trùng tài khoản
	public boolean checkTrungTaiKhoan(String username) {
		try {
			
			pst = con.prepareStatement("SELECT * FROM TAIKHOAN WHERE TAIKHOAN = ?");
			pst.setString(1, username);
			rs = pst.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	//Check Login
	public boolean checkLogin(String username, String pass) {
		try {
			
			pst = con.prepareStatement("SELECT * FROM TAIKHOAN WHERE TAIKHOAN = ?");
			pst.setString(1, username);
			rs = pst.executeQuery();
			while (rs.next()) {
				if (BCrypt.checkpw(pass, rs.getString("MATKHAU"))) {
					return true;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//Update avatar
	public void updateAvatar(String user, byte[] image) {
		try {
			
			pst = con.prepareStatement("UPDATE TAIKHOAN SET AVATAR = ? WHERE TAIKHOAN = ?");
			pst.setBytes(1, image);
			pst.setString(2, user);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Get avatar
	public ImageIcon getAvatar(String user) {
		byte[] imageBytes = null;
		try {
			pst = con.prepareStatement("SELECT AVATAR FROM TAIKHOAN WHERE TAIKHOAN = ?");
			pst.setString(1, user);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				imageBytes = rs.getBytes("AVATAR");
				Image image = Toolkit.getDefaultToolkit().createImage(imageBytes);
			    ImageIcon icon = new ImageIcon(image);
			    return icon;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	//Get user
	public List<String> getListUser(String user) {
		try {
			List<String> listUser = new ArrayList<>();
			pst = con.prepareStatement("SELECT * FROM TAIKHOAN WHERE TAIKHOAN != ?");
			pst.setString(1, user);
			rs = pst.executeQuery();
			while (rs.next()) {
				listUser.add(rs.getString("TAIKHOAN"));
			}
			return listUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
