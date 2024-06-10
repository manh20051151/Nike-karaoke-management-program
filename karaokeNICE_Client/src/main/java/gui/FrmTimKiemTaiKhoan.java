package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

//import dao.DAO_NhanVien;
//import dao.DAO_TaiKhoan;
import dao.DaoLoaiNhanVien;
import dao.DaoNhanVien;
import dao.DaoTaiKhoan;
import entity.KhachHang;
import entity.LoaiKhachHang;
import entity.NhanVien;
import entity.TaiKhoan;

public class FrmTimKiemTaiKhoan extends JFrame implements ActionListener,KeyListener{

	private static final String URL = "rmi://26.173.247.75:7878/";
	DaoTaiKhoan daoTaiKhoan = (DaoTaiKhoan) Naming.lookup(URL + "daoTaiKhoan");
	DaoNhanVien daoNhanVien = (DaoNhanVien) Naming.lookup(URL + "daoNhanVien");

//	private DAO_NhanVien daoNhanVien = new DAO_NhanVien();
//	private DAO_TaiKhoan daoTaiKhoan = new DAO_TaiKhoan();
	public static JPanel pnBorder;
	private JTextField txtTenTK;
	private JComboBox<String> cbMaChuTK;

	private JButton btnThem;
	private JButton btnResetMK;
	private JButton btnXoaTrang;
	private JTextField txtMK;
	private Box bTimKiem_Nhap;
	private JLabel lbLuaChon;
	private JLabel lbNhap;
	private JComboBox<String> cbLuaChon;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private JLabel lbDSTK;
	private DefaultTableModel modelTK;
	private JTable tbTaiKhoan;
	private JButton btnXoa;
	private JButton btnLuu;
	private JButton btnLamMoi;
	private ArrayList<TaiKhoan> dsTimDuoc = daoTaiKhoan.getAllTaiKhoan();
	public FrmTimKiemTaiKhoan() throws MalformedURLException, NotBoundException, RemoteException {
		this.setTitle("Quản lý tài khoản");
		// this.setSize(1920, 1030);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Toàn màn hình
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.createUI();
	}
	private void createUI() throws RemoteException {
		// Kiểu chữ
		Font font1 = new Font("Times New Roman", Font.BOLD, 36);
		Font font2 = new Font("Times New Roman", Font.PLAIN, 24);

		// Màu chữ
		Color color1 = new Color(138, 43, 226); // BlueViolet
		Color color2 = new Color(233, 221, 244);
		
		pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());
		pnBorder.setBorder(new EmptyBorder(20, 20, 20, 20));
		pnBorder.setBackground(color2);
		
		// Left
		JPanel pnLeft = new JPanel();
		pnLeft.setLayout(new BorderLayout());
		pnLeft.setBackground(color2);

		// Left-Content
		JPanel pnLeftContent = new JPanel();
		pnLeftContent.setBorder(new EmptyBorder(40, 40, 40, 40));
		pnLeftContent.setBackground(Color.WHITE);
		
		Box bThongTinTK = Box.createVerticalBox();
		Box bLbTenTK = Box.createHorizontalBox();
		Box bTxtTenTK = Box.createHorizontalBox();
		Box bLbMK = Box.createHorizontalBox();
		Box bTxtMK = Box.createHorizontalBox();
		Box bLbMaChuTK = Box.createHorizontalBox();
		Box bCbMaChuTK = Box.createHorizontalBox();
		Box bThemSua = Box.createHorizontalBox();
		Box bXoaTrang = Box.createHorizontalBox();
		
		JLabel lbTenTK = new JLabel("Tên tài khoản:");
		lbTenTK.setFont(font2);
		JLabel lbMK = new JLabel("Mật khẩu:");
		lbMK.setFont(font2);
		JLabel lbMaChuTK = new JLabel("Mã chủ tài khoản:");
		lbMaChuTK.setFont(font2);
		
		bLbTenTK.add(lbTenTK);
		bLbTenTK.add(Box.createVerticalStrut(0));
		//bLbMK.add(lbMK);
		//bLbMK.add(Box.createVerticalStrut(0));
		bLbMaChuTK.add(lbMaChuTK);
		bLbMaChuTK.add(Box.createVerticalStrut(0));
		
		txtTenTK = new JTextField(17);
		txtTenTK.setFont(font2);
		txtTenTK.setBackground(color2);
		
		cbMaChuTK = new JComboBox<String>();
		cbMaChuTK.addItem("");
		ArrayList<NhanVien> maChuTK = daoNhanVien.getALLNhanVien();
		for (int i = 0; i < maChuTK.size(); i++) {
			cbMaChuTK.addItem(maChuTK.get(i).getMaNhanVien());
		}
		cbMaChuTK.setFont(font2);
		cbMaChuTK.setBackground(color2);
		
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon("src/main/java/img/Button-Refresh-icon2.png"));
		btnLamMoi.setBackground(color1);
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(font2);
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setIcon(new ImageIcon("src/main/java/img/Button-Refresh-icon2.png"));
		btnXoaTrang.setFont(font2);
		btnXoaTrang.setBackground(color1);
		btnXoaTrang.setForeground(Color.WHITE);
		
		bTxtTenTK.add(txtTenTK);
		//bTxtMK.add(txtMK);
		bCbMaChuTK.add(cbMaChuTK);
		
		bThemSua.add(btnLamMoi);
		bThemSua.add(Box.createHorizontalStrut(10));
		bThemSua.add(btnXoaTrang);
		bThemSua.add(Box.createHorizontalStrut(10));
		/*
		bThemSua.add(btnThem);
		bThemSua.add(Box.createHorizontalStrut(15));
		bThemSua.add(btnResetMK);
		bXoaTrang.add(btnXoaTrang);
		*/
		
		bThongTinTK.add(bLbTenTK);
		bThongTinTK.add(Box.createVerticalStrut(3));
		bThongTinTK.add(bTxtTenTK);
		bThongTinTK.add(Box.createVerticalStrut(15));
		bThongTinTK.add(bLbMK);
		bThongTinTK.add(Box.createVerticalStrut(3));
		bThongTinTK.add(bTxtMK);
		bThongTinTK.add(Box.createVerticalStrut(15));
		bThongTinTK.add(bLbMaChuTK);
		bThongTinTK.add(Box.createVerticalStrut(3));
		bThongTinTK.add(bCbMaChuTK);
		
		bThongTinTK.add(Box.createVerticalStrut(30));
		bThongTinTK.add(bThemSua);
		bThongTinTK.add(Box.createVerticalStrut(15));
		bThongTinTK.add(bXoaTrang);
		bThongTinTK.add(Box.createVerticalStrut(30));
		
		pnLeftContent.add(bThongTinTK);
		
		pnLeft.add(pnLeftContent, BorderLayout.CENTER);
		pnLeft.add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		
		// Right
		JPanel pnRight = new JPanel();
		pnRight.setBackground(Color.WHITE);
		pnRight.setBorder(new EmptyBorder(15, 15, 15, 15));
		pnRight.setLayout(new BorderLayout());

		// Right-Top
		JPanel pnRightTop = new JPanel();
		pnRightTop.setBackground(Color.WHITE);
		//pnRightTop.setLayout(new BorderLayout());

		lbDSTK = new JLabel("Danh sách tài khoản");
		lbDSTK.setFont(font1);
		lbDSTK.setForeground(color1);
		
		
		/*
		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBackground(Color.WHITE);
		pnTimKiem.setLayout(new BoxLayout(pnTimKiem, BoxLayout.Y_AXIS));

		
		Box bTimKiem = Box.createVerticalBox();
		Box bTimKiem_LuaChon = Box.createHorizontalBox();
		bTimKiem_Nhap = Box.createHorizontalBox();

		lbLuaChon = new JLabel("Tìm theo:");
		lbLuaChon.setFont(font2);
		lbNhap = new JLabel("Tìm kiếm:");
		lbNhap.setFont(font2);
		lbLuaChon.setPreferredSize(lbNhap.getPreferredSize());

		cbLuaChon = new JComboBox<String>();
		cbLuaChon.addItem("Tên tài khoản");
		cbLuaChon.addItem("Mã chủ tài khoản");
		cbLuaChon.setFont(font2);
		cbLuaChon.setBackground(color2);
		
		txtTimKiem = new JTextField(17);
		txtTimKiem.setFont(font2);
		txtTimKiem.setBackground(color2);
		
		btnTimKiem = new JButton();
		btnTimKiem.setIcon(new ImageIcon(FrmQuanLyHoaDon.class.getResource("/img/Toolbar-Browser-Search-icon.png")));
		btnTimKiem.setFont(font2);
		btnTimKiem.setBackground(color1);
		btnTimKiem.setForeground(Color.WHITE);
		
		bTimKiem_LuaChon.add(lbLuaChon);
		bTimKiem_LuaChon.add(Box.createHorizontalStrut(7));
		bTimKiem_LuaChon.add(cbLuaChon);
		bTimKiem_Nhap.add(lbNhap);
		bTimKiem_Nhap.add(Box.createHorizontalStrut(7));
		bTimKiem_Nhap.add(txtTimKiem);
		bTimKiem_Nhap.add(btnTimKiem);
		
		bTimKiem.add(bTimKiem_LuaChon);
		bTimKiem.add(Box.createVerticalStrut(10));
		bTimKiem.add(bTimKiem_Nhap);

		pnTimKiem.add(bTimKiem);
		
		pnRightTop.add(pnTimKiem, BorderLayout.EAST);
		*/
		pnRightTop.add(lbDSTK);

		// Right-Center
		JPanel pnRightCenter = new JPanel();
		pnRightCenter.setLayout(new BorderLayout());
		pnRightCenter.setBackground(Color.WHITE);
		pnRightCenter.setBorder(new EmptyBorder(20, 0, 20, 0));
		
		modelTK = new DefaultTableModel();
		modelTK.addColumn("Tên tài khoản");
		//modelTK.addColumn("Mật khẩu");
		modelTK.addColumn("Mã chủ tài khoản");
		
		tbTaiKhoan = new JTable(modelTK);
		JScrollPane jScrollPane = new JScrollPane(tbTaiKhoan,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JTableHeader tbHeaderKH = tbTaiKhoan.getTableHeader();
		tbHeaderKH.setFont(font2);
		tbHeaderKH.setBackground(color2);
		tbTaiKhoan.setFont(font2);
		tbTaiKhoan.setRowHeight(35);
		pnRightCenter.add(jScrollPane);
		loadData(daoTaiKhoan.getAllTaiKhoan());
		
		// Right-Bottom
		JPanel pnRightBottom = new JPanel();
		pnRightBottom.setBackground(Color.WHITE);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Xoa.png"));
		btnXoa.setBackground(color1);
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(font2);
		btnLuu = new JButton("Lưu");
		btnLuu.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Luu.png"));
		btnLuu.setBackground(color1);
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(font2);
		


	
		
		pnRight.add(pnRightTop, BorderLayout.NORTH);
		pnRight.add(pnRightCenter, BorderLayout.CENTER);
		pnRight.add(pnRightBottom, BorderLayout.SOUTH);

		pnBorder.add(pnLeft, BorderLayout.WEST);
		pnBorder.add(pnRight, BorderLayout.CENTER);
		getContentPane().add(pnBorder);
		
		tbTaiKhoan.setDefaultEditor(Object.class, null);
		
		
	
		btnXoaTrang.addActionListener(this);

		//btnTimKiem.addActionListener(this);
		btnLamMoi.addActionListener(this);
		txtTenTK.addKeyListener(this);
		cbMaChuTK.addActionListener(this);
		
	}
	private void xoaRongNhapLieu() {
		txtTenTK.requestFocus();
		txtTenTK.setText("");
		cbMaChuTK.setSelectedIndex(0);
	}
	private void loadData(TaiKhoan tk) {
		modelTK.setRowCount(0);
		String tenTK = tk.getTenTaiKhoan();
		String mk = tk.getMatKhau();
		String maChuTK = tk.getNhanVien().getMaNhanVien();
		String row[] = { tenTK, mk, maChuTK};
		modelTK.addRow(row);
	}
	private void loadData(ArrayList<TaiKhoan> dsTK) {
		modelTK.setRowCount(0);
		for (int i = 0; i < dsTK.size(); i++) {
			String tenTK = dsTK.get(i).getTenTaiKhoan();
			//String mk = dsTK.get(i).getMatKhau();
			String maChuTK = dsTK.get(i).getNhanVien().getMaNhanVien();
			String row[] = { tenTK, maChuTK};
			modelTK.addRow(row);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(btnXoaTrang)) {
			xoaRongNhapLieu();
		}
		
		if(obj.equals(btnLamMoi)) {
			try {
				loadData(daoTaiKhoan.getAllTaiKhoan());
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if(obj.equals(cbMaChuTK)) {
			if(cbMaChuTK.getSelectedItem() != null) {
				try {
					dsTimDuoc = daoTaiKhoan.getTimKiemDSTK(txtTenTK.getText(), cbMaChuTK.getSelectedItem().toString());
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				loadData(dsTimDuoc);
			}
		}
	}

	public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
		new FrmTimKiemTaiKhoan().setVisible(true);
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		try {
			dsTimDuoc = daoTaiKhoan.getTimKiemDSTK(txtTenTK.getText(), cbMaChuTK.getSelectedItem().toString());
		} catch (RemoteException ex) {
			throw new RuntimeException(ex);
		}
		loadData(dsTimDuoc);
		
	}

}
