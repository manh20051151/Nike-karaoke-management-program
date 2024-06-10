package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.toedter.calendar.JDateChooser;

//import connectDB.ConnectDB;
//import dao.DAO_LoaiNhanVien;
//import dao.DAO_NhanVien;
import dao.DaoLoaiNhanVien;
import dao.DaoNhanVien;
import entity.LoaiNhanVien;
import entity.NhanVien;

public class FrmQuanLyNhanVien extends JFrame implements ActionListener, MouseListener {
	private static final String URL = "rmi://26.173.247.75:7878/";


	DaoLoaiNhanVien daoLoaiNV = (DaoLoaiNhanVien) Naming.lookup(URL + "daoLoaiNhanVien");
	DaoNhanVien daoNV = (DaoNhanVien) Naming.lookup(URL + "daoNhanVien");

//	private DAO_NhanVien daoNV = new DAO_NhanVien();
//	private DAO_LoaiNhanVien daoLoaiNV = new DAO_LoaiNhanVien();

	private JButton btnThem;
	private JComboBox cbChucVu;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JDateChooser txtNgaySinh;
	private JTextField txtCMND;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JComboBox cbGioiTinh;
	public static JPanel pnBorder;

	private DefaultTableModel modelNV;
	private JButton btnXoaTrang;
	private JButton btnSua;
	private JTable tbNhanVien;
	private JButton btnXoa;
	private JButton btnTimKiem;
	private JComboBox cbLuaChon;
	private JTextField txtTimKiem;
	private JButton btnLuu;
	private JButton btnLamMoi;
	private JButton btnThemAnh;
	private JLabel lbHinhAnh;
	private byte[] duongDanAnh;
	private String strAnh;
	private BorderLayout borderLayout;

	// private String strAnh;
	public FrmQuanLyNhanVien(ArrayList<NhanVien> ds) throws MalformedURLException, NotBoundException, RemoteException {
//		System.out.println(ds);
		this.setTitle("Quản lý nhân viên");
		// this.setSize(1920, 1030);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Toàn màn hình
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.createUI(ds);
		// this.setVisible(true);
	}

	private void createUI(ArrayList<NhanVien> ds) throws RemoteException {
//		ConnectDB.getInstance().connect();
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
		pnLeftContent.setBorder(new EmptyBorder(15, 40, 15, 40));
		pnLeftContent.setBackground(Color.WHITE);

		Box bThongTinNV = Box.createVerticalBox();
		Box bLbMaNV = Box.createHorizontalBox();
		Box bTxtMaNV = Box.createHorizontalBox();
		Box bLbTenNV = Box.createHorizontalBox();
		Box bTxtTenNV = Box.createHorizontalBox();
		Box bLbNgaySinh = Box.createHorizontalBox();
		Box bTxtNgaySinh = Box.createHorizontalBox();
		Box bLbCMND = Box.createHorizontalBox();
		Box bTxtCMND = Box.createHorizontalBox();
		Box bLbSDT = Box.createHorizontalBox();
		Box bTxtSDT = Box.createHorizontalBox();
		Box blbDiaChi = Box.createHorizontalBox();
		Box bTxtDiaChi = Box.createHorizontalBox();
		Box bLbGioiTinh = Box.createHorizontalBox();
		Box bCbGioiTinh = Box.createHorizontalBox();
		Box bLbTrangThai = Box.createHorizontalBox();
		Box bCbTrangThai = Box.createHorizontalBox();
		Box bLbChucVu = Box.createHorizontalBox();
		Box bCbChucVu = Box.createHorizontalBox();
		Box bLbMucLuong = Box.createHorizontalBox();
		Box bTxtMucLuong = Box.createHorizontalBox();
		Box bThemSua = Box.createHorizontalBox();
		Box bXoaTrang = Box.createHorizontalBox();
		Box bThemAnh = Box.createHorizontalBox();

		JLabel lbMaNV = new JLabel("Mã nhân viên:");
		lbMaNV.setFont(font2);
		JLabel lbTenNV = new JLabel("Tên nhân viên:");
		lbTenNV.setFont(font2);
		JLabel lbNgaySinh = new JLabel("Ngày sinh:");
		lbNgaySinh.setFont(font2);
		JLabel lbCMND = new JLabel("CMND:");
		lbCMND.setFont(font2);
		JLabel lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setFont(font2);
		JLabel lbDiaChi = new JLabel("Địa chỉ:");
		lbDiaChi.setFont(font2);
		JLabel lbGioiTinh = new JLabel("Giới tính:");
		lbGioiTinh.setFont(font2);
		JLabel lbTrangThai = new JLabel("Trạng thái:");
		lbTrangThai.setFont(font2);
		JLabel lbChucVu = new JLabel("Chức vụ:");
		lbChucVu.setFont(font2);
		JLabel lbMucLuong = new JLabel("Mức lương:");
		lbMucLuong.setFont(font2);

		bLbMaNV.add(lbMaNV);
		bLbMaNV.add(Box.createVerticalStrut(0));
		bLbTenNV.add(lbTenNV);
		bLbTenNV.add(Box.createVerticalStrut(0));
		bLbNgaySinh.add(lbNgaySinh);
		bLbNgaySinh.add(Box.createVerticalStrut(0));
		bLbCMND.add(lbCMND);
		bLbCMND.add(Box.createVerticalStrut(0));
		bLbSDT.add(lbSDT);
		bLbSDT.add(Box.createVerticalStrut(0));
		blbDiaChi.add(lbDiaChi);
		blbDiaChi.add(Box.createVerticalStrut(0));
		bLbGioiTinh.add(lbGioiTinh);
		bLbGioiTinh.add(Box.createVerticalStrut(0));
		bLbTrangThai.add(lbTrangThai);
		bLbTrangThai.add(Box.createVerticalStrut(0));
		bLbChucVu.add(lbChucVu);
		bLbChucVu.add(Box.createVerticalStrut(0));
		bLbMucLuong.add(lbMucLuong);
		bLbMucLuong.add(Box.createVerticalStrut(0));

		txtMaNV = new JTextField(17);
		txtMaNV.setFont(font2);
		txtMaNV.setBackground(color2);
		txtTenNV = new JTextField(17);
		txtTenNV.setFont(font2);
		txtTenNV.setBackground(color2);
		txtNgaySinh = new JDateChooser();
		txtNgaySinh.setPreferredSize(new Dimension(30, 35));
		txtNgaySinh.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 16));
		borderLayout = (BorderLayout) txtNgaySinh.getLayout();
		txtNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		txtNgaySinh.setBackground(color2);
		txtCMND = new JTextField(17);
		txtCMND.setFont(font2);
		txtCMND.setBackground(color2);
		txtSDT = new JTextField(17);
		txtSDT.setFont(font2);
		txtSDT.setBackground(color2);
		txtDiaChi = new JTextField(17);
		txtDiaChi.setFont(font2);
		txtDiaChi.setBackground(color2);
		cbGioiTinh = new JComboBox();
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.addItem("Nữ");
		cbGioiTinh.setFont(font2);
		cbChucVu = new JComboBox();
		ArrayList<LoaiNhanVien> loaiNhanVien = daoLoaiNV.getALLLoaiNhanVien();
		for (int i = 0; i < loaiNhanVien.size(); i++) {
			cbChucVu.addItem(loaiNhanVien.get(i).getTenLoai());
		}

		cbChucVu.setFont(font2);
		// JTextField txtGiaBan = new JTextField(17);
		// txtGiaBan.setFont(font2);
		// txtGiaBan.setBackground(color2);
		// JTextField txtMucLuong = new JTextField(17);
		// txtMucLuong.setFont(font2);
		// txtMucLuong.setBackground(color2);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Them.png"));
		btnThem.setFont(font2);
		btnThem.setBackground(color1);
		btnThem.setForeground(Color.WHITE);
		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Sua.png"));
		btnSua.setFont(font2);
		btnSua.setBackground(color1);
		btnSua.setForeground(Color.WHITE);
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setIcon(new ImageIcon("src/main/java/img/Button-Refresh-icon2.png"));
		btnXoaTrang.setFont(font2);
		btnXoaTrang.setBackground(color1);
		btnXoaTrang.setForeground(Color.WHITE);

		btnThemAnh = new JButton("Thêm ảnh");
		btnThemAnh.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Them.png"));
		btnThemAnh.setFont(font2);
		btnThemAnh.setBackground(color1);
		btnThemAnh.setForeground(Color.WHITE);
		bTxtMaNV.add(txtMaNV);
		bTxtTenNV.add(txtTenNV);
		bTxtNgaySinh.add(txtNgaySinh);
		bTxtCMND.add(txtCMND);
		bTxtSDT.add(txtSDT);
		bTxtDiaChi.add(txtDiaChi);
		bCbGioiTinh.add(cbGioiTinh);
		bCbChucVu.add(cbChucVu);

		/*
		 * bThemSua.add(btnThem); bThemSua.add(Box.createHorizontalStrut(10));
		 * bThemSua.add(btnSua); bThemSua.add(Box.createHorizontalStrut(10));
		 * bThemAnh.add(btnThemAnh); bThemAnh.add(Box.createHorizontalStrut(10));
		 * bThemAnh.add(btnXoaTrang);
		 */

		bThongTinNV.add(bLbMaNV);
		bThongTinNV.add(Box.createVerticalStrut(2));
		bThongTinNV.add(bTxtMaNV);
		bThongTinNV.add(Box.createVerticalStrut(14));
		bThongTinNV.add(bLbTenNV);
		bThongTinNV.add(Box.createVerticalStrut(2));
		bThongTinNV.add(bTxtTenNV);
		bThongTinNV.add(Box.createVerticalStrut(14));
		bThongTinNV.add(bLbNgaySinh);
		bThongTinNV.add(Box.createVerticalStrut(2));
		bThongTinNV.add(bTxtNgaySinh);
		bThongTinNV.add(Box.createVerticalStrut(14));
		bThongTinNV.add(bLbCMND);
		bThongTinNV.add(Box.createVerticalStrut(2));
		bThongTinNV.add(bTxtCMND);
		bThongTinNV.add(Box.createVerticalStrut(14));
		bThongTinNV.add(bLbSDT);
		bThongTinNV.add(Box.createVerticalStrut(2));
		bThongTinNV.add(bTxtSDT);
		bThongTinNV.add(Box.createVerticalStrut(14));
		bThongTinNV.add(blbDiaChi);
		bThongTinNV.add(Box.createVerticalStrut(2));
		bThongTinNV.add(bTxtDiaChi);
		bThongTinNV.add(Box.createVerticalStrut(14));
		bThongTinNV.add(bLbGioiTinh);
		bThongTinNV.add(Box.createVerticalStrut(2));
		bThongTinNV.add(bCbGioiTinh);
		bThongTinNV.add(Box.createVerticalStrut(14));
		// bThongTinNV.add(bLbTrangThai);
		// bThongTinNV.add(Box.createVerticalStrut(2));
		// bThongTinNV.add(bCbTrangThai);
		// bThongTinNV.add(Box.createVerticalStrut(14));
		bThongTinNV.add(bLbChucVu);
		bThongTinNV.add(Box.createVerticalStrut(2));
		bThongTinNV.add(bCbChucVu);
		// bThongTinNV.add(Box.createVerticalStrut(14));
		// bThongTinNV.add(bLbMucLuong);
		// bThongTinNV.add(Box.createVerticalStrut(2));
		// bThongTinNV.add(bTxtMucLuong);

		// bThongTinNV.add(Box.createVerticalStrut(30));
		// bThongTinNV.add(bThemSua);
		bThongTinNV.add(Box.createVerticalStrut(10));
		bThongTinNV.add(bThemAnh);
		bThongTinNV.add(Box.createVerticalStrut(20));

		JPanel pnHinhAnh = new JPanel();
		pnHinhAnh.setBackground(Color.WHITE);
		lbHinhAnh = new JLabel("");
		lbHinhAnh.setHorizontalAlignment(SwingConstants.CENTER);
		bThongTinNV.add(pnHinhAnh);
		GroupLayout gl_pnHinhAnh = new GroupLayout(pnHinhAnh);
		gl_pnHinhAnh.setHorizontalGroup(gl_pnHinhAnh.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnHinhAnh.createSequentialGroup().addGap(77)
						.addComponent(lbHinhAnh, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(109, Short.MAX_VALUE)));
		gl_pnHinhAnh.setVerticalGroup(gl_pnHinhAnh.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnHinhAnh.createSequentialGroup()
						.addComponent(lbHinhAnh, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(88, Short.MAX_VALUE)));
		pnHinhAnh.setLayout(gl_pnHinhAnh);
		// lbHinhAnh.setBounds(-20, 20, 200, 200);

		pnLeftContent.add(bThongTinNV);

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
		// pnRightTop.setLayout(new BorderLayout());

		JLabel lbDSNV = new JLabel("Danh sách nhân viên");
		lbDSNV.setFont(font1);
		lbDSNV.setForeground(color1);

		/*
		 * JPanel pnTimKiem = new JPanel(); pnTimKiem.setBackground(Color.WHITE);
		 * pnTimKiem.setLayout(new BoxLayout(pnTimKiem, BoxLayout.Y_AXIS));
		 * 
		 * Box bTimKiem = Box.createVerticalBox(); Box bTimKiem_LuaChon =
		 * Box.createHorizontalBox(); Box bTimKiem_Nhap = Box.createHorizontalBox();
		 * 
		 * JLabel lbLuaChon = new JLabel("Tìm theo:"); lbLuaChon.setFont(font2); JLabel
		 * lbNhap = new JLabel("Nhập mã:"); lbNhap.setFont(font2);
		 * lbLuaChon.setPreferredSize(lbNhap.getPreferredSize());
		 * 
		 * cbLuaChon = new JComboBox(); cbLuaChon.setModel(new DefaultComboBoxModel(new
		 * String[] { "Mã nhân viên", "Tên nhân viên", "Chức vụ","Số điện thoại" }));
		 * cbLuaChon.setFont(font2); cbLuaChon.setBackground(color2);
		 * 
		 * txtTimKiem = new JTextField(17); txtTimKiem.setFont(font2);
		 * txtTimKiem.setBackground(color2);
		 * 
		 * btnTimKiem = new JButton(); btnTimKiem.setIcon(new
		 * ImageIcon(FrmQuanLyHoaDon.class.getResource(
		 * "/img/Toolbar-Browser-Search-icon.png"))); btnTimKiem.setFont(font2);
		 * btnTimKiem.setBackground(color1); btnTimKiem.setForeground(Color.WHITE);
		 * 
		 * bTimKiem_LuaChon.add(lbLuaChon);
		 * bTimKiem_LuaChon.add(Box.createHorizontalStrut(7));
		 * bTimKiem_LuaChon.add(cbLuaChon); bTimKiem_Nhap.add(lbNhap);
		 * bTimKiem_Nhap.add(Box.createHorizontalStrut(7));
		 * bTimKiem_Nhap.add(txtTimKiem); bTimKiem_Nhap.add(btnTimKiem);
		 * 
		 * bTimKiem.add(bTimKiem_LuaChon); bTimKiem.add(Box.createVerticalStrut(10));
		 * bTimKiem.add(bTimKiem_Nhap);
		 * 
		 * pnTimKiem.add(bTimKiem);
		 * 
		 * pnRightTop.add(pnTimKiem, BorderLayout.EAST);
		 */
		pnRightTop.add(lbDSNV);

		// Right-Center
		JPanel pnRightCenter = new JPanel();
		pnRightCenter.setLayout(new BorderLayout());
		pnRightCenter.setBackground(Color.WHITE);
		pnRightCenter.setBorder(new EmptyBorder(20, 0, 20, 0));

		modelNV = new DefaultTableModel();
		modelNV.addColumn("Mã nhân viên");
		modelNV.addColumn("Tên nhân viên");
		modelNV.addColumn("Giới tính");
		modelNV.addColumn("Ngày sinh");
		modelNV.addColumn("CMND");
		modelNV.addColumn("Địa chỉ");
		modelNV.addColumn("Số điện thoại");
		modelNV.addColumn("Chức vụ");

		tbNhanVien = new JTable(modelNV);
		JScrollPane jScrollPane = new JScrollPane(tbNhanVien, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tbNhanVien.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JTableHeader tbHeaderNV = tbNhanVien.getTableHeader();
		tbHeaderNV.setFont(font2);
		tbHeaderNV.setBackground(color2);
		tbNhanVien.setFont(font2);
		tbNhanVien.setRowHeight(35);
		pnRightCenter.add(jScrollPane);
		loadData(ds);

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

		/*
		 * btnLamMoi = new JButton("Làm mới"); btnLamMoi.setBackground(color1);
		 * btnLamMoi.setForeground(Color.WHITE); btnLamMoi.setFont(font2);
		 */

		pnRightBottom.add(btnThem);
		pnRightBottom.add(Box.createHorizontalStrut(7));
		pnRightBottom.add(btnSua);
		pnRightBottom.add(Box.createHorizontalStrut(7));
		pnRightBottom.add(btnXoa);
		pnRightBottom.add(Box.createHorizontalStrut(7));
		pnRightBottom.add(btnLuu);
		pnRightBottom.add(Box.createHorizontalStrut(7));
		pnRightBottom.add(btnXoaTrang);
		pnRightBottom.add(Box.createHorizontalStrut(7));
		pnRightBottom.add(btnThemAnh);

		pnRight.add(pnRightTop, BorderLayout.NORTH);
		pnRight.add(pnRightCenter, BorderLayout.CENTER);
		pnRight.add(pnRightBottom, BorderLayout.SOUTH);

		pnBorder.add(pnLeft, BorderLayout.WEST);
		pnBorder.add(pnRight, BorderLayout.CENTER);
		getContentPane().add(pnBorder);

		tbNhanVien.setDefaultEditor(Object.class, null);

		dongMoNhapLieu(false);
		btnXoaTrang.setEnabled(false);
		btnLuu.setEnabled(false);
		btnThemAnh.setEnabled(false);

		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		// btnTimKiem.addActionListener(this);
		btnLuu.addActionListener(this);
		// btnLamMoi.addActionListener(this);
		btnThemAnh.addActionListener(this);
		tbNhanVien.addMouseListener(this);
	}

	public ImageIcon ResizeImage(String ImagePath) {
		ImageIcon MyImage = new ImageIcon(ImagePath);
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(lbHinhAnh.getWidth(), lbHinhAnh.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);

		return image;
	}

	private void xoaRongNhapLieu() {
		txtTenNV.setText("");
		txtTenNV.requestFocus();
		txtSDT.setText("");
		txtNgaySinh.setDateFormatString(toString());
		txtDiaChi.setText("");
		cbChucVu.setSelectedIndex(0);
		cbGioiTinh.setSelectedIndex(0);
		txtCMND.setText("");
		// txtTimKiem.setText("");
	}

	private void dongMoNhapLieu(Boolean b) {
		txtMaNV.setEditable(false);
		txtTenNV.setEditable(b);
		cbGioiTinh.setEnabled(b);
		txtNgaySinh.setEnabled(b);
		cbChucVu.setEnabled(b);
		txtDiaChi.setEditable(b);
		txtSDT.setEditable(b);
		txtCMND.setEditable(b);
	}

	private void loadData(NhanVien nv) {
		modelNV.setRowCount(0);
		String maNV = nv.getMaNhanVien();
		String tenNV = nv.getTenNhanVien();
		String gioiTinh = nv.getGioiTinh() ? "Nam" : "Nữ";
		String ngaySinh = nv.getNgaySinh() + "";
		String cmnd = nv.getCmnd();
		String diaChi = nv.getDiaChi();
		String sDT = nv.getSoDienThoai();
		String tenLoaiKH = nv.getLoaiNhanVien().getTenLoai();
		String row[] = { maNV, tenNV, gioiTinh, ngaySinh, cmnd, diaChi, sDT, tenLoaiKH };
		modelNV.addRow(row);
	}

	private void loadData(ArrayList<NhanVien> dsNV) {
		modelNV.setRowCount(0);
		dsNV.forEach(System.out::println);
		for (int i = 0; i < dsNV.size(); i++) {
			String maNV = dsNV.get(i).getMaNhanVien();
			String tenNV = dsNV.get(i).getTenNhanVien();
			String gioiTinh = dsNV.get(i).getGioiTinh() ? "Nam" : "Nữ";
			String ngaySinh = dsNV.get(i).getNgaySinh() + "";
			String cmnd = dsNV.get(i).getCmnd();
			String diaChi = dsNV.get(i).getDiaChi();
			String sDT = dsNV.get(i).getSoDienThoai();
			String tenLoaiKH = "";
			// Kiểm tra xem đối tượng LoaiNhanVien có khác null không
			if (dsNV.get(i).getLoaiNhanVien() != null) {
				// Nếu không null thì gán giá trị cho tenLoaiKH
				tenLoaiKH = dsNV.get(i).getLoaiNhanVien().getTenLoai();
			}
			String row[] = { maNV, tenNV, gioiTinh, ngaySinh, cmnd, diaChi, sDT, tenLoaiKH };
			modelNV.addRow(row);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int hangDuocChon = tbNhanVien.getSelectedRow();
		txtMaNV.setText(modelNV.getValueAt(hangDuocChon, 0).toString());
		txtTenNV.setText(modelNV.getValueAt(hangDuocChon, 1).toString());
		cbGioiTinh.setSelectedItem(modelNV.getValueAt(hangDuocChon, 2).toString());
		try {
			java.util.Date date2 = new SimpleDateFormat("yyyy-MM-dd")
					.parse((String) modelNV.getValueAt(hangDuocChon, 3));
			txtNgaySinh.setDate(date2);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		txtCMND.setText(modelNV.getValueAt(hangDuocChon, 4).toString());
		txtDiaChi.setText(modelNV.getValueAt(hangDuocChon, 5).toString());
		txtSDT.setText(modelNV.getValueAt(hangDuocChon, 6).toString());
		cbChucVu.setSelectedItem(modelNV.getValueAt(hangDuocChon, 7).toString());
		String maNV = modelNV.getValueAt(hangDuocChon, 0).toString();

		byte[] byteArray = new byte[0];
		try {
			byteArray = daoNV.getHinh(maNV);
		} catch (RemoteException ex) {
			throw new RuntimeException(ex);
		}
		if (byteArray == null) {
			lbHinhAnh.setIcon(new ImageIcon("src/main/java/img/anhtam.jpg"));
		} else {
			String str = new String(byteArray);
			lbHinhAnh.setIcon(ResizeImage(String.valueOf(str)));
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private boolean validData() {
		String tenNV = txtTenNV.getText().trim();
		String sdt = txtSDT.getText().trim();
		String cmnd = txtCMND.getText().trim();
		String diaChi = txtDiaChi.getText().trim();

		if (tenNV.length() == 0) {
			showMessage(txtTenNV, "Nhập tên nhân viên!");
			return false;
		}
		if (!tenNV.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]*\\s?)+$")) {
			showMessage(txtTenNV, "Tên nhân viên bao gồm chữ cái, chữ số tiếng Việt, không bao gồm ký tự đặc biệt!");
			return false;
		}
		if (cmnd.length() == 0) {
			showMessage(txtCMND, "Nhập CMND!");
			return false;
		}
		if (!cmnd.matches("^\\d{9,12}$") && Integer.parseInt(cmnd) < 0) {
			showMessage(txtCMND, "CMND là số nguyên (9-12 số)");
			return false;
		}
		if (sdt.length() == 0) {
			showMessage(txtSDT, "Nhập số điện thoại!");
			return false;
		}
		if (!sdt.matches("^0\\d{9}$")) {
			showMessage(txtSDT, "Số điện thoại là số nguyên (10 số, số đầu tiên là số 0)");
			return false;
		}
		if (diaChi.length() == 0) {
			showMessage(txtDiaChi, "Nhập địa chỉ!");
			return false;
		}
		return true;
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btnThem)) {
			lbHinhAnh.setIcon(new ImageIcon("src/main/java/img/anhtam.jpg"));
			if (btnThem.getText().equals("Thêm")) {
				dongMoNhapLieu(true);
				btnLuu.setEnabled(true);
				btnSua.setEnabled(false);
				btnXoaTrang.setEnabled(true);
				btnXoa.setEnabled(false);
				// btnLamMoi.setEnabled(false);
				btnThemAnh.setEnabled(true);
				btnThem.setText("Hủy");
				tbNhanVien.removeMouseListener(this);
				xoaRongNhapLieu();
				int soHang = modelNV.getRowCount();
				// Mã có dạng DVXXX, XXX: từ 001 -> 999
				if (soHang < 999) {
					int soCuoi = 0;
					try {
						soCuoi = taoSoCuoiMaDV();
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
					// TH1: số cuối tìm được có 1 chữ số
					if (soCuoi < 10) {
						txtMaNV.setText("NV00" + soCuoi);
					} else {
						// TH2: Số cuối tìm được có 2 chữ số
						if (soCuoi < 100) {
							txtMaNV.setText("NV0" + soCuoi);
						} else {
							// TH3: Số cuối tìm được có 3 chữ số
							txtMaNV.setText("NV" + soCuoi);
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Dữ liệu đầy, không thể thêm!");
				}
			} else {
				tbNhanVien.addMouseListener(this);
				dongMoNhapLieu(false);
				btnThem.setText("Thêm");
				btnSua.setEnabled(true);
				// btnLamMoi.setEnabled(true);
				btnXoa.setEnabled(true);
				btnXoaTrang.setEnabled(false);
				btnLuu.setEnabled(false);
				btnThemAnh.setEnabled(false);
				tbNhanVien.addMouseListener(this);
				txtMaNV.setText("");
				xoaRongNhapLieu();
				try {
					loadData(daoNV.getALLNhanVien());
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			}
		}
		if (obj.equals(btnXoaTrang)) {
			xoaRongNhapLieu();
		}
		if (obj.equals(btnXoa)) {
			int hangDuocChon = tbNhanVien.getSelectedRow();
			if (hangDuocChon > -1) {
				if (JOptionPane.showConfirmDialog(this, "Chắc chắn xóa dòng này?") == JOptionPane.YES_OPTION) {
					try {
						if (daoNV.deleteNhanVienTheoMa(modelNV.getValueAt(hangDuocChon, 0).toString())) {
							loadData(daoNV.getALLNhanVien());
							JOptionPane.showMessageDialog(this, "Xóa thành công.");
							txtMaNV.setText("");
							xoaRongNhapLieu();
						} else {
							JOptionPane.showMessageDialog(this, "Xóa không thành công. Vì ràng buộc.");
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Chọn 1 hàng trong bảng danh sách nhân viên cần xóa!");
			}
		}
		if (obj.equals(btnSua)) {
			if (btnSua.getText().equals("Sửa")) {
				int hangDuocChon = tbNhanVien.getSelectedRow();
				if (hangDuocChon > -1) {
					dongMoNhapLieu(true);
					btnSua.setText("Hủy");
					btnThem.setEnabled(false);
					// btnLamMoi.setEnabled(false);
					btnXoa.setEnabled(false);
					btnXoaTrang.setEnabled(true);
					btnLuu.setEnabled(true);
					btnThemAnh.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(this,
							"Vui lòng chọn 1 hàng trong bảng danh sách nhân viên, trước khi sửa!");
				}
			} else {
				dongMoNhapLieu(false);
				btnSua.setText("Sửa");
				btnThem.setEnabled(true);
				// btnLamMoi.setEnabled(true);
				btnXoa.setEnabled(true);
				btnXoaTrang.setEnabled(false);
				btnLuu.setEnabled(false);
				btnThemAnh.setEnabled(false);
				txtMaNV.setText("");
				xoaRongNhapLieu();
			}
		}
		/*
		 * if (obj.equals(btnTimKiem)) { if (cbLuaChon.getSelectedIndex() == 0) { String
		 * giaTriTimKiem = txtTimKiem.getText(); NhanVien nhanVien =
		 * daoNV.getNhanVienTheoMa(giaTriTimKiem); if (nhanVien != null) {
		 * loadData(nhanVien); } else { JOptionPane.showMessageDialog(this,
		 * "Không tìm thấy!\nVui lòng kiểm tra lại thông tin tìm kiếm."); } } if
		 * (cbLuaChon.getSelectedIndex() == 1) { String giaTriTimKiem =
		 * txtTimKiem.getText(); ArrayList<NhanVien> dsTimDuoc =
		 * daoNV.getDSNhanVienTheoTenTuongDoi(giaTriTimKiem); if (dsTimDuoc.size() > 0)
		 * { loadData(dsTimDuoc); } else { JOptionPane.showMessageDialog(this,
		 * "Không tìm thấy!\nVui lòng kiểm tra lại thông tin tìm kiếm."); } } if
		 * (cbLuaChon.getSelectedIndex() == 2) { String giaTriTimKiem =
		 * txtTimKiem.getText(); ArrayList<NhanVien> dsTimDuoc =
		 * daoNV.getDsNhanVienTheoLoai(giaTriTimKiem); if (dsTimDuoc.size() > 0) {
		 * loadData(dsTimDuoc); } else { JOptionPane.showMessageDialog(this,
		 * "Không có loại dịch vu: " + giaTriTimKiem + "!"); } } if
		 * (cbLuaChon.getSelectedIndex() == 3) { String giaTriTimKiem =
		 * txtTimKiem.getText(); ArrayList<NhanVien> dsTimDuoc =
		 * daoNV.getDSNhanVienTheoSDT(giaTriTimKiem); if (dsTimDuoc.size() > 0) {
		 * loadData(dsTimDuoc); } else { JOptionPane.showMessageDialog(this,
		 * "Không tìm thấy!\nVui lòng kiểm tra lại thông tin tìm kiếm."); } }
		 * 
		 * }
		 */
		if (obj.equals(btnThemAnh)) {
			// strAnh = "D:\\anh\\anh.JPG";
			try {

				btnLuu.setEnabled(true);
				JFileChooser f = new JFileChooser();
				f.setDialogTitle("Mở file");
				f.showOpenDialog(null);

				File fileAnh = f.getSelectedFile();
				strAnh = fileAnh.getAbsolutePath();

				lbHinhAnh.setIcon(ResizeImage(String.valueOf(strAnh)));
				duongDanAnh = strAnh.getBytes();

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Chưa chọn ảnh");
			}
		}
		if (obj.equals(btnThemAnh)) {
			try {
				btnLuu.setEnabled(true);
				JFileChooser f = new JFileChooser();
				f.setDialogTitle("Mở file");
				int result = f.showOpenDialog(null);

				if (result == JFileChooser.APPROVE_OPTION) {
					File fileAnh = f.getSelectedFile();
					strAnh = fileAnh.getAbsolutePath();

					// Hiển thị hình ảnh trên JLabel lbHinhAnh
					ImageIcon imageIcon = new ImageIcon(strAnh);
					Image image = imageIcon.getImage().getScaledInstance(lbHinhAnh.getWidth(), lbHinhAnh.getHeight(), Image.SCALE_SMOOTH);
					lbHinhAnh.setIcon(new ImageIcon(image));

					// Lưu đường dẫn hình ảnh
					duongDanAnh = strAnh.getBytes();
				} else {
					JOptionPane.showMessageDialog(this, "Chưa chọn ảnh");
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "Lỗi: " + e2.getMessage());
			}
		}
		if (obj.equals(btnLuu)) {
			if (btnThem.getText().equals("Hủy")) {
				if (validData()) {
					String maNV = txtMaNV.getText().trim();
					String tenNV = txtTenNV.getText().trim();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String ngaySinh = sdf.format(txtNgaySinh.getDate());
					LocalDate date = LocalDate.parse(ngaySinh);
					String cmnd = txtCMND.getText().trim();
					String diaChi = txtDiaChi.getText().trim();
					String sdt = txtSDT.getText().trim();
					boolean gioiTinh = cbGioiTinh.getSelectedItem().toString().equals("Nam") ? true : false;
					String chucVu = cbChucVu.getSelectedItem().toString();
					LoaiNhanVien loaiNV = null;
					try {
						loaiNV = daoLoaiNV.getLoaiNhanVienTheoTen(chucVu);
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
					String gmail = null;

					String hinhAnh = String.valueOf(duongDanAnh); // Thay đổi thành kiểu String

					NhanVien nVien = new NhanVien(maNV, tenNV, gioiTinh, date, cmnd, diaChi, sdt, loaiNV, hinhAnh.getBytes(), gmail);
					try {
						if (daoNV.addNhanVien(nVien)) {
							loadData(daoNV.getALLNhanVien());
							JOptionPane.showMessageDialog(this, "Thêm nhân viên mới thành công");
							dongMoNhapLieu(false);
							btnThem.setText("Thêm");
							xoaRongNhapLieu();
							btnSua.setEnabled(true);
							// btnLamMoi.setEnabled(true);
							btnXoa.setEnabled(true);
							btnXoaTrang.setEnabled(false);
							btnLuu.setEnabled(false);
							btnThemAnh.setEnabled(false);
							txtMaNV.setText("");
							tbNhanVien.addMouseListener(this);
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			}

			if (btnSua.getText().equals("Hủy")) {
				if (validData()) {
					String maNV = txtMaNV.getText().trim();
					String tenNV = txtTenNV.getText().trim();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String ngaySinh = sdf.format(txtNgaySinh.getDate());
					LocalDate date = LocalDate.parse(ngaySinh);
					String cmnd = txtCMND.getText().trim();
					String diaChi = txtDiaChi.getText().trim();
					String sdt = txtSDT.getText().trim();
					boolean gioiTinh = cbGioiTinh.getSelectedItem().toString().equals("Nam");
					String chucVu = cbChucVu.getSelectedItem().toString();
					LoaiNhanVien loaiNV = null;
					try {
						loaiNV = daoLoaiNV.getLoaiNhanVienTheoTen(chucVu);
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
					byte[] hinhAnh = duongDanAnh;
					String gmail = null;
					try {
						if (daoNV.updateNhanVien(
								new NhanVien(maNV, tenNV, gioiTinh, date, cmnd, diaChi, sdt, loaiNV, hinhAnh, gmail))) {
							loadData(daoNV.getALLNhanVien());
							JOptionPane.showMessageDialog(this, "Sửa thông tin nhân viên thành công");
							dongMoNhapLieu(false);
							btnSua.setText("Sửa");
							xoaRongNhapLieu();
							btnThem.setEnabled(true);
							// btnLamMoi.setEnabled(true);
							btnXoa.setEnabled(true);
							btnXoaTrang.setEnabled(false);
							btnThemAnh.setEnabled(false);
							btnLuu.setEnabled(false);
							txtMaNV.setText("");
							tbNhanVien.addMouseListener(this);
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			}

		}
		/*
		 * if (obj.equals(btnLamMoi)) { loadData(daoNV.getALLNhanVien());
		 * xoaRongNhapLieu(); }
		 */

	}

	/**
	 * Tạo số cuối của mã mới không trùng với các mã đã có trong table. Lưu ý: Cần
	 * sắp xếp mã trên table theo thứ tự tăng dần.
	 * 
	 * @return Số cuối của mả mới.
	 */
	private int taoSoCuoiMaDV() throws RemoteException {
		ArrayList<NhanVien> ds = daoNV.getALLNhanVienKhongCoADMIN();
		int i = 0; // thứ tự hàng
		while (i < ds.size()) {
			// Lấy mã ở hàng thứ i trong table bỏ "NV" và chuyển sang kiểu int.
			// Tìm mã không khớp với hàng thứ i. Mã không khớp sẽ có số cuối mã
			// - i > 1
			if (Integer.parseInt(ds.get(i).getMaNhanVien().substring(2)) == (i + 1))
				// nếu mã khớp thì tăng i lên để xét hàng tiếp theo.
				i++;
			else
				// nếu mã không khớp thì thoát vòng lập
				break;
		}
		// i ra khỏi vòng lập là hàng có mã không khớp hoặc bằng với số hàng, ta
		// chỉ cần + 1 để có số cuối của mã mới.
		return i + 1;
	}

}
