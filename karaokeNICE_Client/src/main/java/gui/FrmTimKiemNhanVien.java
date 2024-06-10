package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

//import connectDB.ConnectDB;
//import dao.DAO_LoaiNhanVien;
//import dao.DAO_NhanVien;
import dao.DaoLoaiNhanVien;
import dao.DaoNhanVien;
import entity.LoaiNhanVien;
import entity.NhanVien;

public class FrmTimKiemNhanVien extends JFrame implements ActionListener, KeyListener {
	private static final String URL = "rmi://26.173.247.75:7878/";

	DaoNhanVien daoNV = (DaoNhanVien) Naming.lookup(URL + "daoNhanVien");
	DaoLoaiNhanVien daoLoaiNV = (DaoLoaiNhanVien) Naming.lookup(URL + "daoLoaiNhanVien");

//	private DAO_NhanVien daoNV = new DAO_NhanVien();
//	private DAO_LoaiNhanVien daoLoaiNV = new DAO_LoaiNhanVien();

	private JButton btnThem;
	private JComboBox cbChucVu;
	private JTextField txtMaNV;
	private JTextField txtTenNV;
	private JTextField txtNgaySinh;
	private JTextField txtCMND;
	private JTextField txtSDT;
	private JTextField txtDiaChi;
	private JComboBox cbGioiTinh;
	public static JPanel pnBorder;

	private DefaultTableModel modelNV;
	private JTable tbNhanVien;
	private JButton btnTimKiem;
	private JComboBox cbLuaChon;
	private JButton btnLamMoi;
	private ArrayList<NhanVien> dsTimDuoc = daoNV.getALLNhanVienKhongCoADMIN();
	private JButton btnCapNhatNhanVien;

	// private String strAnh;
	public FrmTimKiemNhanVien(String maNhanVien) throws MalformedURLException, NotBoundException, RemoteException {
		this.setTitle("Quản lý nhân viên");
		// this.setSize(1920, 1030);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Toàn màn hình
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.createUI(maNhanVien);
		// this.setVisible(true);
	}

	private void createUI(String maNhanVien) throws RemoteException {
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
		txtNgaySinh = new JTextField(17);
		txtNgaySinh.setFont(font2);
		txtNgaySinh.setBackground(color2);
		// txtNgaySinh = new JDateChooser();
		// txtNgaySinh.setPreferredSize(new Dimension(30, 35));
		// txtNgaySinh.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 16));
		// borderLayout = (BorderLayout) txtNgaySinh.getLayout();
		// txtNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		// txtNgaySinh.setBackground(color2);
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
		cbGioiTinh.addItem("");
		cbGioiTinh.addItem("Nữ");
		cbGioiTinh.addItem("Nam");
		cbGioiTinh.setFont(font2);
//		cbGioiTinh.setBackground(color2);
		cbChucVu = new JComboBox();
		cbChucVu.addItem("");
		ArrayList<LoaiNhanVien> loaiNhanVien = daoLoaiNV.getALLLoaiNhanVien();
		for (int i = 0; i < loaiNhanVien.size(); i++) {
			cbChucVu.addItem(loaiNhanVien.get(i).getTenLoai());
		}
		cbChucVu.setFont(font2);
//		cbChucVu.setBackground(color2);

		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon("src/main/java/img/Button-Refresh-icon2.png"));
		btnLamMoi.setBackground(color1);
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(font2);

		bTxtMaNV.add(txtMaNV);
		bTxtTenNV.add(txtTenNV);
		bTxtNgaySinh.add(txtNgaySinh);
		bTxtCMND.add(txtCMND);
		bTxtSDT.add(txtSDT);
		bTxtDiaChi.add(txtDiaChi);
		bCbGioiTinh.add(cbGioiTinh);
		bCbChucVu.add(cbChucVu);

		bThemSua.add(btnLamMoi);
		bThemSua.add(Box.createHorizontalStrut(10));

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

		bThongTinNV.add(Box.createVerticalStrut(30));
		bThongTinNV.add(bThemSua);
		bThongTinNV.add(Box.createVerticalStrut(10));
		/*
		 * bThongTinNV.add(bThemAnh); bThongTinNV.add(Box.createVerticalStrut(30));
		 */

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

		JLabel lbDSNV = new JLabel("Tìm kiếm nhân viên");
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
		loadData(daoNV.getALLNhanVienKhongCoADMIN());

		// Right-Bottom
		JPanel pnRightBottom = new JPanel();
		pnRightBottom.setBackground(Color.WHITE);

		btnCapNhatNhanVien = new JButton("Cập nhật nhân viên");
		btnCapNhatNhanVien.setBackground(color1);
		btnCapNhatNhanVien.setForeground(Color.WHITE);
		btnCapNhatNhanVien.setFont(font2);

		pnRightBottom.add(btnCapNhatNhanVien);

		pnRight.add(pnRightTop, BorderLayout.NORTH);
		pnRight.add(pnRightCenter, BorderLayout.CENTER);

//		Kiểm tra: nếu là người quản lý thì thêm panel Cập nhập phòng
		NhanVien nVien = daoNV.getNhanVienTheoMa(maNhanVien);
		if (maNhanVien.equals("admin") || nVien.getLoaiNhanVien().getMaLoaiVN().equals("LNV001"))
			pnRight.add(pnRightBottom, BorderLayout.SOUTH);

		pnBorder.add(pnLeft, BorderLayout.WEST);
		pnBorder.add(pnRight, BorderLayout.CENTER);
		getContentPane().add(pnBorder);

		tbNhanVien.setDefaultEditor(Object.class, null);

		cbChucVu.addActionListener(this);
		cbGioiTinh.addActionListener(this);
		btnLamMoi.addActionListener(this);
		txtMaNV.addKeyListener(this);
		txtCMND.addKeyListener(this);
		txtDiaChi.addKeyListener(this);
		txtSDT.addKeyListener(this);
		txtTenNV.addKeyListener(this);
		txtNgaySinh.addKeyListener(this);
		btnCapNhatNhanVien.addActionListener(this);
	}

	private void xoaRongNhapLieu() {
		txtMaNV.setText("");
		txtTenNV.setText("");
//		txtTenNV.requestFocus();
		txtSDT.setText("");
		txtNgaySinh.setText("");
		// txtNgaySinh.setDateFormatString(toString());
		txtDiaChi.setText("");
		cbChucVu.setSelectedIndex(0);
		cbGioiTinh.setSelectedIndex(0);
		txtCMND.setText("");
		// txtTimKiem.setText("");
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
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj.equals(btnLamMoi)) {
			xoaRongNhapLieu();
			loadData(dsTimDuoc);
		}
		if (obj.equals(cbChucVu)) {
			String gioiTinh;
			if (cbGioiTinh.getSelectedIndex() <= 0) {
				gioiTinh = "";
			} else {
				gioiTinh = cbGioiTinh.getSelectedIndex() - 1 + "";
			}
			if (cbChucVu.getSelectedItem() != null) {
				System.out.println(cbChucVu.getSelectedItem().toString());
				try {
					dsTimDuoc = daoNV.getTimKiemDSNV(cbChucVu.getSelectedItem().toString(), txtMaNV.getText(),
							txtTenNV.getText(), txtNgaySinh.getText(), gioiTinh, txtCMND.getText(), txtDiaChi.getText(),
							txtSDT.getText());
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				loadData(dsTimDuoc);
			}
		}
		if (obj.equals(cbGioiTinh)) {
			String gioiTinh;
			if (cbGioiTinh.getSelectedIndex() <= 0) {
				gioiTinh = "";
			} else {
				gioiTinh = cbGioiTinh.getSelectedIndex() - 1 + "";
			}
			if (cbGioiTinh.getSelectedItem() != null) {
				try {
					dsTimDuoc = daoNV.getTimKiemDSNV(cbChucVu.getSelectedItem().toString(), txtMaNV.getText(),
							txtTenNV.getText(), txtNgaySinh.getText(), gioiTinh, txtCMND.getText(), txtDiaChi.getText(),
							txtSDT.getText());
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				loadData(dsTimDuoc);
			}
		}
		if (obj.equals(btnCapNhatNhanVien)) {
			FrmQuanLyNhanVien frmQuanLyNhanVien = null;
			try {
				frmQuanLyNhanVien = new FrmQuanLyNhanVien(dsTimDuoc);
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			FrmTrangChu.jtab_NoiDung.remove(FrmTrangChu.jtab_NoiDung.getSelectedComponent());
			FrmTrangChu.jtab_NoiDung.add(frmQuanLyNhanVien.pnBorder);
		}
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
		String gioiTinh;
		if (cbGioiTinh.getSelectedIndex() <= 0) {
			gioiTinh = "";
		} else {
			gioiTinh = cbGioiTinh.getSelectedIndex() - 1 + "";
		}
		try {
			dsTimDuoc = daoNV.getTimKiemDSNV(cbChucVu.getSelectedItem().toString(), txtMaNV.getText(), txtTenNV.getText(),
					txtNgaySinh.getText(), gioiTinh, txtCMND.getText(), txtDiaChi.getText(), txtSDT.getText());
		} catch (RemoteException ex) {
			throw new RuntimeException(ex);
		}
		loadData(dsTimDuoc);
	}
}
