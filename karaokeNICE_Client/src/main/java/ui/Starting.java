package ui;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

//import connectDB.ConnectDB;
import gui.FrmDangNhap;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Starting {
	public static <T> void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		// Cấu hình look and feel Nimbus
		// https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					e.printStackTrace();
				}
				break;
			}
		}
//		ConnectDB.getInstance().connect();
		FrmDangNhap frmDangNhap = new FrmDangNhap();
		frmDangNhap.setVisible(true);
		frmDangNhap.setLocationRelativeTo(null);
	}
}