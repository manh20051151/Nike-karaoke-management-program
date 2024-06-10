package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.DefaultComboBoxModel;
//import connectDB.ConnectDB;
//import dao.DAO_KhachHang;
//import dao.DAO_LoaiKhachHang;
//import dao.DAO_NhanVien;
import dao.DaoKhachHang;
import dao.DaoLoaiKhachHang;
import dao.DaoNhanVien;
import entity.KhachHang;
import entity.LoaiKhachHang;
import entity.NhanVien;

import javax.swing.SwingConstants;

public class FrmQuanLyKhachHang extends JFrame implements ActionListener, MouseListener {
	private static final String URL = "rmi://26.173.247.75:7878/";
	DaoKhachHang daoKH = (DaoKhachHang) Naming.lookup(URL + "daoKhachHang");
	DaoLoaiKhachHang daoLoaiKH = (DaoLoaiKhachHang) Naming.lookup(URL + "daoLoaiKhachHang");
	DaoNhanVien daoNhanVien = (DaoNhanVien) Naming.lookup(URL + "daoNhanVien");

//	private DAO_KhachHang daoKH = new DAO_KhachHang();
//	private DAO_LoaiKhachHang daoLoaiKH = new DAO_LoaiKhachHang();
//	private DAO_NhanVien daoNhanVien = new DAO_NhanVien();
	public static JPanel pnBorder;

	private DefaultTableModel modelKH;
	private JButton btnThem;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTextField txtDiem;
	private JButton btnSua;
	private JButton btnXoaTrang;
	private JTextArea taGhiChu;
	private JComboBox cbLoaiKH;
	private JButton btnXoa;
	private JButton btnLuu;
	private JTable tbKhachHang;
	private JButton btnTimKiem;
	private JComboBox cbLuaChon;
	private JTextField txtTimKiem;
	private JButton btnLamMoi;
	private String maNhanVienTruyen;

	public FrmQuanLyKhachHang(String maNhanVien, String sdtMoi, String trangTroVe) throws MalformedURLException, NotBoundException, RemoteException {
		this.setTitle("Quản lý khách hàng");
		this.setSize(1920, 1030);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Toàn màn hình
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.createUI(maNhanVien, sdtMoi, trangTroVe);
	}

	private void createUI(String maNhanVien, String sdtMoi, String trangTroVe) throws RemoteException {
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
		pnLeftContent.setBorder(new EmptyBorder(40, 40, 40, 40));
		pnLeftContent.setBackground(Color.WHITE);

		Box bThongTinKH = Box.createVerticalBox();
		Box bLbMaKH = Box.createHorizontalBox();
		Box bTxtMaKH = Box.createHorizontalBox();
		Box bLbTenKH = Box.createHorizontalBox();
		Box bTxtTenKH = Box.createHorizontalBox();
		Box bLbSDT = Box.createHorizontalBox();
		Box bTxtSDT = Box.createHorizontalBox();
		Box blbDiem = Box.createHorizontalBox();
		Box bTxtDiem = Box.createHorizontalBox();
		Box bLbLoaiKH = Box.createHorizontalBox();
		Box bCbLoaiKH = Box.createHorizontalBox();
		Box bLbGhiChu = Box.createHorizontalBox();
		Box bTaGhiChu = Box.createHorizontalBox();
		Box bThemSua = Box.createHorizontalBox();
		Box bXoaTrang = Box.createHorizontalBox();

		JLabel lbMaKH = new JLabel("Mã khách hàng:");
		lbMaKH.setFont(font2);
		JLabel lbTenKH = new JLabel("Tên khách hàng:");
		lbTenKH.setFont(font2);
		JLabel lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setFont(font2);
		JLabel lbDiem = new JLabel("Điểm:");
		lbDiem.setFont(font2);
		JLabel lbLoaiKH = new JLabel("Loại khách hàng:");
		lbLoaiKH.setFont(font2);
		JLabel lbGhiChu = new JLabel("Ghi chú:");
		lbGhiChu.setFont(font2);

		bLbMaKH.add(lbMaKH);
		bLbMaKH.add(Box.createVerticalStrut(0));
		bLbTenKH.add(lbTenKH);
		bLbTenKH.add(Box.createVerticalStrut(0));
		bLbSDT.add(lbSDT);
		bLbSDT.add(Box.createVerticalStrut(0));
		blbDiem.add(lbDiem);
		blbDiem.add(Box.createVerticalStrut(0));
		bLbLoaiKH.add(lbLoaiKH);
		bLbLoaiKH.add(Box.createVerticalStrut(0));
		bLbGhiChu.add(lbGhiChu);
		bLbGhiChu.add(Box.createVerticalStrut(0));

		txtMaKH = new JTextField(17);
		txtMaKH.setFont(font2);
		txtMaKH.setBackground(color2);
		txtTenKH = new JTextField(17);
		txtTenKH.setFont(font2);
		txtTenKH.setBackground(color2);
		txtSDT = new JTextField(17);
		txtSDT.setFont(font2);
		txtSDT.setBackground(color2);
		txtDiem = new JTextField(17);
		txtDiem.setFont(font2);
		txtDiem.setBackground(color2);
		cbLoaiKH = new JComboBox();
		// cbLoaiKH.setModel(new DefaultComboBoxModel(new String[] {"Thường", "VIP"}));

		ArrayList<LoaiKhachHang> loaiKhachHang = daoLoaiKH.getALLLoaiKhachHang();
		for (int i = 0; i < loaiKhachHang.size(); i++) {
			cbLoaiKH.addItem(loaiKhachHang.get(i).getTenLoaiKhachHang());
		}

		cbLoaiKH.setFont(font2);
		cbLoaiKH.setBackground(color2);

		taGhiChu = new JTextArea(5, 17);
		taGhiChu.setFont(font2);
		taGhiChu.setBackground(color2);

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

		bTxtMaKH.add(txtMaKH);
		bTxtTenKH.add(txtTenKH);
		bTxtSDT.add(txtSDT);
		bTxtDiem.add(txtDiem);
		bCbLoaiKH.add(cbLoaiKH);
		bTaGhiChu.add(taGhiChu);

		/*
		 * bThemSua.add(btnThem); bThemSua.add(Box.createHorizontalStrut(15));
		 * bThemSua.add(btnSua); bXoaTrang.add(btnXoaTrang);
		 */

		bThongTinKH.add(bLbMaKH);
		bThongTinKH.add(Box.createVerticalStrut(3));
		bThongTinKH.add(bTxtMaKH);
		bThongTinKH.add(Box.createVerticalStrut(20));
		bThongTinKH.add(bLbTenKH);
		bThongTinKH.add(Box.createVerticalStrut(3));
		bThongTinKH.add(bTxtTenKH);
		bThongTinKH.add(Box.createVerticalStrut(20));
		bThongTinKH.add(bLbSDT);
		bThongTinKH.add(Box.createVerticalStrut(3));
		bThongTinKH.add(bTxtSDT);
		bThongTinKH.add(Box.createVerticalStrut(20));
		bThongTinKH.add(blbDiem);
		bThongTinKH.add(Box.createVerticalStrut(3));
		bThongTinKH.add(bTxtDiem);
		bThongTinKH.add(Box.createVerticalStrut(20));
		bThongTinKH.add(bLbLoaiKH);
		bThongTinKH.add(Box.createVerticalStrut(3));
		bThongTinKH.add(bCbLoaiKH);
		bThongTinKH.add(Box.createVerticalStrut(20));
		bThongTinKH.add(bLbGhiChu);
		bThongTinKH.add(Box.createVerticalStrut(3));
		bThongTinKH.add(bTaGhiChu);

		bThongTinKH.add(Box.createVerticalStrut(30));
		bThongTinKH.add(bThemSua);
		bThongTinKH.add(Box.createVerticalStrut(15));
		bThongTinKH.add(bXoaTrang);
		bThongTinKH.add(Box.createVerticalStrut(50));

		pnLeftContent.add(bThongTinKH);

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

		JLabel lbDSKH = new JLabel("Danh sách khách hàng");
		lbDSKH.setFont(font1);
		lbDSKH.setForeground(color1);

		/*
		 * JPanel pnTimKiem = new JPanel(); pnTimKiem.setBackground(Color.WHITE);
		 * pnTimKiem.setLayout(new BoxLayout(pnTimKiem, BoxLayout.Y_AXIS));
		 * 
		 * Box bTimKiem = Box.createVerticalBox(); Box bTimKiem_LuaChon =
		 * Box.createHorizontalBox(); Box bTimKiem_Nhap = Box.createHorizontalBox();
		 * 
		 * JLabel lbLuaChon = new JLabel("Tìm theo:"); lbLuaChon.setFont(font2); JLabel
		 * lbNhap = new JLabel("Tìm kiếm:"); lbNhap.setFont(font2);
		 * lbLuaChon.setPreferredSize(lbNhap.getPreferredSize());
		 * 
		 * cbLuaChon = new JComboBox<String>(); cbLuaChon.addItem("Mã khách hàng");
		 * cbLuaChon.addItem("Tên khách hàng"); cbLuaChon.addItem("Loại khách hàng");
		 * cbLuaChon.addItem("Số điện thoại"); cbLuaChon.setFont(font2);
		 * cbLuaChon.setBackground(color2);
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
		pnRightTop.add(lbDSKH);

		// Right-Center
		JPanel pnRightCenter = new JPanel();
		pnRightCenter.setLayout(new BorderLayout());
		pnRightCenter.setBackground(Color.WHITE);
		pnRightCenter.setBorder(new EmptyBorder(20, 0, 20, 0));

		modelKH = new DefaultTableModel();
		modelKH.addColumn("Mã khách hàng");
		modelKH.addColumn("Tên khách hàng");
		modelKH.addColumn("Số điện thoại");
		modelKH.addColumn("Điểm");
		modelKH.addColumn("Loại khách hàng");
		modelKH.addColumn("Ghi chú");

		tbKhachHang = new JTable(modelKH);
		JScrollPane jScrollPane = new JScrollPane(tbKhachHang, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JTableHeader tbHeaderKH = tbKhachHang.getTableHeader();
		tbHeaderKH.setFont(font2);
		tbHeaderKH.setBackground(color2);
		tbKhachHang.setFont(font2);
		tbKhachHang.setRowHeight(35);
		// String row[] = {"KH001","Nguyễn Văn B","0123456789","0","Thường"," "};
		// modelKH.addRow(row);
		pnRightCenter.add(jScrollPane);
		loadData(daoKH.getALLKhachHang());

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

		pnRight.add(pnRightTop, BorderLayout.NORTH);
		pnRight.add(pnRightCenter, BorderLayout.CENTER);
		pnRight.add(pnRightBottom, BorderLayout.SOUTH);

		pnBorder.add(pnLeft, BorderLayout.WEST);
		pnBorder.add(pnRight, BorderLayout.CENTER);
		getContentPane().add(pnBorder);

		tbKhachHang.setDefaultEditor(Object.class, null);

		dongMoNhapLieu(false);

		btnXoaTrang.setEnabled(false);
		btnLuu.setEnabled(false);

		Component horizontalStrut = Box.createHorizontalStrut(7);
		pnRightBottom.add(horizontalStrut);
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		// btnTimKiem.addActionListener(this);
		btnLuu.addActionListener(this);
		// btnLamMoi.addActionListener(this);
		tbKhachHang.addMouseListener(this);

		// Xử lý khi có truyền khách hàng mới
		maNhanVienTruyen = maNhanVien;
		if (!sdtMoi.equals("")) {
			moNutThem();
			txtSDT.setText(sdtMoi);
			// Thêm nút trở về
			String troVeString = "";
			if (trangTroVe.equals("FrmDatPhongDangSuDung")) {
				troVeString = "Trở về trang phòng đang sử dụng";
			} else if (trangTroVe.equals("FrmDatPhongHat")) {
				troVeString = "Trở về trang đặt phòng";
			}
//			Không có trở về phiếu ạ
//			else if (trangTroVe.equals("FrmPhieuDatPhong")) {
//				troVeString = "Trở về phiếu đặt phòng";
//			}
			JButton btn_ChuyenPhong = new JButton(troVeString);
			btn_ChuyenPhong.setAlignmentY(Component.TOP_ALIGNMENT);
			btn_ChuyenPhong.setAlignmentX(Component.CENTER_ALIGNMENT);
			btn_ChuyenPhong.setForeground(Color.WHITE);
			btn_ChuyenPhong.setFont(new Font("Tahoma", Font.BOLD, 22));
			btn_ChuyenPhong.setFocusable(false);
			btn_ChuyenPhong.setBackground(new Color(138, 43, 226));
			bThongTinKH.add(btn_ChuyenPhong);
			btn_ChuyenPhong.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (trangTroVe.equals("FrmDatPhongDangSuDung")) {
						FrmXuLyPhongDangSuDung frmTroVe = null;
						try {
							frmTroVe = new FrmXuLyPhongDangSuDung(maNhanVienTruyen, sdtMoi);
						} catch (MalformedURLException ex) {
							throw new RuntimeException(ex);
						} catch (NotBoundException ex) {
							throw new RuntimeException(ex);
						} catch (RemoteException ex) {
							throw new RuntimeException(ex);
						}
						FrmTrangChu.jtab_NoiDung.remove(FrmTrangChu.jtab_NoiDung.getSelectedComponent());
						FrmTrangChu.jtab_NoiDung.add(frmTroVe.pnBorder);
					} else if (trangTroVe.equals("FrmDatPhongHat")) {
						FrmXyLyDatPhong frmTroVe = null;
						try {
							frmTroVe = new FrmXyLyDatPhong(maNhanVienTruyen);
						} catch (MalformedURLException ex) {
							throw new RuntimeException(ex);
						} catch (NotBoundException ex) {
							throw new RuntimeException(ex);
						} catch (RemoteException ex) {
							throw new RuntimeException(ex);
						}
						FrmTrangChu.jtab_NoiDung.remove(FrmTrangChu.jtab_NoiDung.getSelectedComponent());
						FrmTrangChu.jtab_NoiDung.add(frmTroVe.pnBorder);
					}
//					Không có trường hợp vậy nên đóng
//					else if (trangTroVe.equals("FrmPhieuDatPhong")) {
//						FrmDatPhongCho_Demo frmTroVe = new FrmDatPhongCho_Demo(maNhanVien);
//						FrmTrangChu.jtab_NoiDung.remove(FrmTrangChu.jtab_NoiDung.getSelectedComponent());
//						FrmTrangChu.jtab_NoiDung.add(frmTroVe.pnBorder);
//					}
				}
			});
		}
	}

	public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
		new FrmQuanLyKhachHang("admin", "", "").setVisible(true);
	}

	private void xoaRongNhapLieu() {
		txtTenKH.requestFocus();
		txtTenKH.setText("");
		txtSDT.setText("");
		txtDiem.setText("");
		cbLoaiKH.setSelectedIndex(0);
		taGhiChu.setText("");
	}

	private void dongMoNhapLieu(Boolean b) {
		txtMaKH.setEditable(false);
		txtTenKH.setEditable(b);
		txtSDT.setEnabled(b);
		txtDiem.setEnabled(b);
		cbLoaiKH.setEnabled(b);
		taGhiChu.setEditable(b);
	}

	private void loadData(KhachHang kh) {
		modelKH.setRowCount(0);
		String maKH = kh.getMaKhachHang();
		String tenKH = kh.getTenKhachHang();
		String sDT = kh.getSoDienThoai();
		String diem = kh.getDiem() + "";
		String tenLoaiKH = kh.getLoaiKhachHang().getTenLoaiKhachHang();
		String ghiChu = kh.getGhiChu();
		String row[] = { maKH, tenKH, sDT, diem, tenLoaiKH, ghiChu };
		modelKH.addRow(row);
	}

	// mới sửa
//	private void loadData(ArrayList<KhachHang> dsKH) {
//		modelKH.setRowCount(0);
//		dsKH.forEach(System.out::println);
//		for (int i = 0; i < dsKH.size(); i++) {
//			String maKH = dsKH.get(i).getMaKhachHang();
//			String tenKH = dsKH.get(i).getTenKhachHang();
//			String sDT = dsKH.get(i).getSoDienThoai();
//			String diem = dsKH.get(i).getDiem() + "";
//			String tenLoaiKH = dsKH.get(i).getLoaiKhachHang().getTenLoaiKhachHang();
//			String ghiChu = dsKH.get(i).getGhiChu();
//			String row[] = { maKH, tenKH, sDT, diem, tenLoaiKH, ghiChu };
//			modelKH.addRow(row);
//		}
//	}


	private void loadData(ArrayList<KhachHang> dsKH) {
		modelKH.setRowCount(0);
		for (KhachHang khachHang : dsKH) {
			String maKH = khachHang.getMaKhachHang();
			String tenKH = khachHang.getTenKhachHang();
			String sDT = khachHang.getSoDienThoai();
			String diem = String.valueOf(khachHang.getDiem());
			String tenLoaiKH = "";
			if (khachHang.getLoaiKhachHang() != null) {
				tenLoaiKH = khachHang.getLoaiKhachHang().getTenLoaiKhachHang();
			}
			String ghiChu = khachHang.getGhiChu();
			String[] row = {maKH, tenKH, sDT, diem, tenLoaiKH, ghiChu};
			modelKH.addRow(row);
		}
	}

	public void themKHMoi() throws RemoteException {
		if (validData()) {
			String maKH = txtMaKH.getText().trim();
			String tenKH = txtTenKH.getText().trim();
			String sDT = txtSDT.getText().trim();
			String diem = txtDiem.getText().trim();
			String ghiChu = taGhiChu.getText().trim();
			String tenLoaiKH = cbLoaiKH.getSelectedItem().toString();
			LoaiKhachHang loaiKH = daoLoaiKH.getLoaiKhachHangTheoTen(tenLoaiKH);
			if (daoKH.addKhachHang(new KhachHang(maKH, tenKH, sDT, Integer.parseInt(diem), ghiChu, loaiKH))) {
				loadData(daoKH.getALLKhachHang());
				JOptionPane.showMessageDialog(this, "Thêm khách hàng mới thành công");
				dongMoNhapLieu(false);
				btnThem.setText("Thêm");
				xoaRongNhapLieu();
				btnSua.setEnabled(true);
				btnXoa.setEnabled(true);
				btnXoaTrang.setEnabled(false);
				btnLuu.setEnabled(false);
				txtMaKH.setText("");
				tbKhachHang.addMouseListener(this);
			}
		}
	}

	public void moNutThem() {
		dongMoNhapLieu(true);
		btnLuu.setEnabled(true);
		btnSua.setEnabled(false);
		btnXoa.setEnabled(false);
		btnXoaTrang.setEnabled(true);
		btnThem.setText("Hủy");
		tbKhachHang.removeMouseListener(this);
		xoaRongNhapLieu();
		int soHang = modelKH.getRowCount();
		// Mã có dạng DVXXX, XXX: từ 001 -> 999
		if (soHang < 999) {
			int soCuoi = taoSoCuoiMaKH();
			// TH1: số cuối tìm được có 1 chữ số
			if (soCuoi < 10) {
				txtMaKH.setText("KH00" + soCuoi);
			} else {
				// TH2: Số cuối tìm được có 2 chữ số
				if (soCuoi < 100) {
					txtMaKH.setText("KH0" + soCuoi);
				} else {
					// TH3: Số cuối tìm được có 3 chữ số
					txtMaKH.setText("KH" + soCuoi);
				}
			}
		} else {
			JOptionPane.showMessageDialog(this, "Dữ liệu đầy, không thể thêm!");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btnThem)) {
			if (btnThem.getText().equals("Thêm")) {
				moNutThem();
			} else {
				tbKhachHang.addMouseListener(this);
				dongMoNhapLieu(false);
				btnThem.setText("Thêm");
				btnSua.setEnabled(true);
				btnXoa.setEnabled(true);
				btnXoaTrang.setEnabled(false);
				btnLuu.setEnabled(false);
				tbKhachHang.addMouseListener(this);
				txtMaKH.setText("");
				xoaRongNhapLieu();
				try {
					loadData(daoKH.getALLKhachHang());
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			}

		}
		if (obj.equals(btnXoaTrang)) {
			xoaRongNhapLieu();
		}
		if (obj.equals(btnXoa)) {
			int hangDuocChon = tbKhachHang.getSelectedRow();
			if (hangDuocChon > -1) {
				if (JOptionPane.showConfirmDialog(this, "Chắc chắn xóa dòng này?") == JOptionPane.YES_OPTION) {
					try {
						if (daoKH.deleteKhachHangTheoMa(modelKH.getValueAt(hangDuocChon, 0).toString())) {
							loadData(daoKH.getALLKhachHang());
							JOptionPane.showMessageDialog(this, "Xóa thành công.");
							txtMaKH.setText("");
							xoaRongNhapLieu();
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Chọn 1 hàng trong bảng danh sách khách hàng cần xóa!");
			}
		}
		if (obj.equals(btnSua)) {
			if (btnSua.getText().equals("Sửa")) {
				int hangDuocChon = tbKhachHang.getSelectedRow();
				if (hangDuocChon > -1) {
					dongMoNhapLieu(true);
					btnSua.setText("Hủy");
					btnThem.setEnabled(false);
					btnXoa.setEnabled(false);
					btnXoaTrang.setEnabled(true);
					btnLuu.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(this,
							"Vui lòng chọn 1 hàng trong bảng danh sách khách hàng, trước khi sửa!");
				}
			} else {
				dongMoNhapLieu(false);
				btnSua.setText("Sửa");
				btnSua.setText("Sửa");
				btnThem.setEnabled(true);
				btnXoa.setEnabled(true);
				btnXoaTrang.setEnabled(false);
				btnLuu.setEnabled(false);
				txtMaKH.setText("");
				xoaRongNhapLieu();
			}
		} /*
			 * if (obj.equals(btnTimKiem)) { if (cbLuaChon.getSelectedIndex() == 0) { String
			 * giaTriTimKiem = txtTimKiem.getText(); KhachHang khachHang =
			 * daoKH.getKhachHangTheoMa(giaTriTimKiem); if (khachHang != null) {
			 * loadData(khachHang); } else { JOptionPane.showMessageDialog(this,
			 * "Không tìm thấy!\nVui lòng kiểm tra lại thông tin tìm kiếm."); } } if
			 * (cbLuaChon.getSelectedIndex() == 1) { String giaTriTimKiem =
			 * txtTimKiem.getText(); ArrayList<KhachHang> dsTimDuoc =
			 * daoKH.getDSKhachHangTheoTenTuongDoi(giaTriTimKiem); if (dsTimDuoc.size() > 0)
			 * { loadData(dsTimDuoc); } else { JOptionPane.showMessageDialog(this,
			 * "Không tìm thấy!\nVui lòng kiểm tra lại thông tin tìm kiếm."); } } if
			 * (cbLuaChon.getSelectedIndex() == 2) { String giaTriTimKiem =
			 * txtTimKiem.getText(); ArrayList<KhachHang> dsTimDuoc =
			 * daoKH.getDSKhachHangTheoLoai(giaTriTimKiem); if (dsTimDuoc.size() > 0) {
			 * loadData(dsTimDuoc); } else { JOptionPane.showMessageDialog(this,
			 * "Không có loại khách hàng: " + giaTriTimKiem + "!"); } } if
			 * (cbLuaChon.getSelectedIndex() == 3) { String giaTriTimKiem =
			 * txtTimKiem.getText(); ArrayList<KhachHang> dsTimDuoc =
			 * daoKH.getDSKhachHangTheoSDT(giaTriTimKiem); if (dsTimDuoc.size() > 0) {
			 * loadData(dsTimDuoc); } else { JOptionPane.showMessageDialog(this,
			 * "Không tìm thấy!\nVui lòng kiểm tra lại thông tin tìm kiếm."); } } }
			 */
		if (obj.equals(btnLuu)) {
			if (btnThem.getText().equals("Hủy")) {
				try {
					themKHMoi();
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			}
			if (btnSua.getText().equals("Hủy")) {
				if (validData()) {
					String maKH = txtMaKH.getText().trim();
					String tenKH = txtTenKH.getText().trim();
					String sDT = txtSDT.getText().trim();
					String diem = txtDiem.getText().trim();
					String ghiChu = taGhiChu.getText().trim();
					String tenLoaiKH = cbLoaiKH.getSelectedItem().toString();
					LoaiKhachHang loaiKH = null;
					try {
						loaiKH = daoLoaiKH.getLoaiKhachHangTheoTen(tenLoaiKH);
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
					try {
						if (daoKH
								.updateKhachHang(new KhachHang(maKH, tenKH, sDT, Integer.parseInt(diem), ghiChu, loaiKH))) {
							loadData(daoKH.getALLKhachHang());
							JOptionPane.showMessageDialog(this, "Sửa thông tin khách hàng thành công");
							dongMoNhapLieu(false);
							btnSua.setText("Sửa");
							btnThem.setEnabled(true);
							btnXoa.setEnabled(true);
							btnXoaTrang.setEnabled(false);
							btnLuu.setEnabled(false);
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			}
		}

	}

	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	private boolean validData() {
		String tenKH = txtTenKH.getText().trim();
		String sdt = txtSDT.getText().trim();
		String diem = txtDiem.getText().trim();
		if (tenKH.length() == 0) {
			showMessage(txtTenKH, "Nhập tên khách hàng!");
			return false;
		}
		if (!tenKH.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]*\\s?)+$")) {
			showMessage(txtTenKH, "Tên khách hàng bao gồm chữ cái, chữ số tiếng Việt, không bao gồm ký tự đặc biệt!");
			return false;
		}
		if (sdt.length() == 0) {
			showMessage(txtSDT, "Nhập số điện thoại!");
			return false;
		}
		if (!sdt.matches("^0\\d{9}$")) {
			showMessage(txtSDT, "Số điện thoại 10 số, số đầu tiên là số 0");
			return false;
		}
		if (diem.length() == 0) {
			showMessage(txtDiem, "Nhập điểm!");
			return false;
		}
		if (!diem.matches("^\\d$") && Integer.parseInt(diem) < 0) {
			showMessage(txtDiem, "Điểm là số nguyên lớn hơn hoặc bằng 0");
			return false;
		}
		return true;
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int hangDuocChon = tbKhachHang.getSelectedRow();
		txtMaKH.setText(modelKH.getValueAt(hangDuocChon, 0).toString());
		txtTenKH.setText(modelKH.getValueAt(hangDuocChon, 1).toString());
		txtSDT.setText(modelKH.getValueAt(hangDuocChon, 2).toString());
		txtDiem.setText(modelKH.getValueAt(hangDuocChon, 3).toString());
		cbLoaiKH.setSelectedItem(modelKH.getValueAt(hangDuocChon, 4).toString());
		taGhiChu.setText(modelKH.getValueAt(hangDuocChon, 5).toString());

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

	/**
	 * Tạo số cuối của mã mới không trùng với các mã đã có trong table. Lưu ý: Cần
	 * sắp xếp mã trên table theo thứ tự tăng dần.
	 * 
	 * @return Số cuối của mả mới.
	 */
	private int taoSoCuoiMaKH() {
		int i = 0; // thứ tự hàng
		while (i < modelKH.getRowCount()) {
			// Lấy mã ở hàng thứ i trong table bỏ "DV" và chuyển sang kiểu int.
			// Tìm mã không khớp với hàng thứ i. Mã không khớp sẽ có số cuối mã
			// - i > 1
			if (Integer.parseInt(modelKH.getValueAt(i, 0).toString().substring(2)) == (i + 1))
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
