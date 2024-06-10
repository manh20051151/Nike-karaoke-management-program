package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.*;
//import dao.DAO_ChiTietHoaDon_DichVu;
//import dao.DAO_ChiTietHoaDon_Phong;
//import dao.DAO_DichVu;
//import dao.DAO_HoaDon;
//import dao.DAO_LoaiDichVu;
//import dao.DAO_Phong;
import entity.ChiTietHoaDon_DichVu;
import entity.ChiTietHoaDon_Phong;
import entity.DichVu;
import entity.HoaDon;
import entity.Phong;

public class FrmThemDichVu extends JFrame implements ActionListener {
	private static final String URL = "rmi://26.173.247.75:7878/";

	DaoPhong dao_Phong = (DaoPhong) Naming.lookup(URL + "daoPhong");
	DaoHoaDon dao_HoaDon = (DaoHoaDon) Naming.lookup(URL + "hoaDon");
	DaoDichVu dao_DichVu = (DaoDichVu) Naming.lookup(URL + "daoDichVu");
	DaoLoaiDichVu dao_LoaiDichVu = (DaoLoaiDichVu) Naming.lookup(URL + "daoLoaiDichVu");
	DaoChiTietHoaDon_Phong dao_CTHD_Phong = (DaoChiTietHoaDon_Phong) Naming.lookup(URL + "daoChiTietHoaDon_phong");
	DaoChiTietHoaDon_DichVu dao_CTHD_DV = (DaoChiTietHoaDon_DichVu) Naming.lookup(URL + "daoChiTietHoaDon_dichVu");



//	private DAO_Phong dao_Phong = new DAO_Phong();
//	private DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
//	private DAO_DichVu dao_DichVu = new DAO_DichVu();
//	private DAO_LoaiDichVu dao_LoaiDichVu = new DAO_LoaiDichVu();
//	private DAO_ChiTietHoaDon_Phong dao_CTHD_Phong = new DAO_ChiTietHoaDon_Phong();
//	private DAO_ChiTietHoaDon_DichVu dao_CTHD_DV = new DAO_ChiTietHoaDon_DichVu();

	public static JPanel pnBorder;
	private JTextField txt_Tim;
	private JLabel lbl_TongCong;

	private JLabel lbl_MaPhong;
	private JLabel lbl_TenKH;
	private JPanel pn_BangTTPhong;
	private JLabel lbl_GioVao;
	private DefaultTableModel modelKhachHang;
	private Phong phongNhan;
	private DefaultTableModel modelKho;
	private JButton btn_Them;
	private JButton btn_HieuChinh;
	private JRadioButton rdbtn_TatCa;
	private JRadioButton rdbtn_ThucAn;
	private JRadioButton rdbtn_DoUong;
	private String maNhanVienTruyen;
	private JButton btn_TimKiem;
	private JButton btn_QuayLai;
	private String maPhongTruyen;
	private JTable tb_Kho;
	private JTable tb_KhachHang;
	private JButton btn_LamMoi;
	DecimalFormat df = new DecimalFormat("###,###,###.##");
	private JLabel lbl_TongThanhTien;
	private JLabel lbl_TieuDeTrai;
	private JButton btn_Xoa;
	private JLabel lbl_TieuDe_1;
	private JButton btnSua;
	private JButton btnXoa;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmThemDichVu frame = new FrmThemDichVu("P002", "NV001");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmThemDichVu(String maPhong, String maNhanVienNhan) throws MalformedURLException, NotBoundException, RemoteException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1582, 848);
		setLocationRelativeTo(null);
		setTitle("Thêm dịch vụ");

		// Kiểu chữ
		Font font1 = new Font("Times New Roman", Font.BOLD, 36);
		Font font2 = new Font("Times New Roman", Font.PLAIN, 24);

		// Màu chữ
		Color color1 = new Color(138, 43, 226); // BlueViolet
		Color color2 = new Color(233, 221, 244);
		Color color3 = new Color(0, 207, 210);
		Color color_trang = Color.white;
		Color color_xanh = color3;

		pnBorder = new JPanel();
		pnBorder.setBackground(new Color(255, 255, 255));
		pnBorder.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnBorder.setLayout(null);
		setContentPane(pnBorder);

		this.addWindowListener((WindowListener) new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					FrmXuLyPhongDangSuDung.dsALL_CTHD_DV = (ArrayList<ChiTietHoaDon_DichVu>) dao_CTHD_DV.getAllDanhSach();
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				try {
					FrmXuLyPhongDangSuDung.dsALL_CTHD_Phong = dao_CTHD_Phong.getALLDanhSach();
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				try {
					FrmXuLyPhongDangSuDung.docThongTinPhong();
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				dispose();
			}
		});

		JPanel panel_TieuDe = new JPanel();
		panel_TieuDe.setBounds(0, 0, 1566, 81);
		pnBorder.add(panel_TieuDe);
		panel_TieuDe.setLayout(null);
		panel_TieuDe.setBackground(color3);

		lbl_TieuDe_1 = new JLabel("Cập nhật dịch vụ");
		lbl_TieuDe_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TieuDe_1.setForeground(Color.WHITE);
		lbl_TieuDe_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbl_TieuDe_1.setBounds(0, 0, 1566, 81);
		panel_TieuDe.add(lbl_TieuDe_1);

		JLabel lbl_TieuDe = new JLabel("Cập nhật dịch vụ");
		lbl_TieuDe.setIcon(new ImageIcon(
				"src/main/java/img/vecteezy_abstract-purple-fluid-wave-background_.jpg"));
		lbl_TieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TieuDe.setForeground(Color.WHITE);
		lbl_TieuDe.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbl_TieuDe.setBounds(0, 0, 1566, 81);
		panel_TieuDe.add(lbl_TieuDe);

		JPanel panel_Trai = new JPanel();
		panel_Trai.setLayout(null);
		panel_Trai.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_Trai.setBackground(color_xanh);
		panel_Trai.setBounds(0, 85, 776, 724);
		pnBorder.add(panel_Trai);

		JLabel lbl_LoaiPhong = new JLabel("Loại dịch vụ:");
		lbl_LoaiPhong.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_LoaiPhong.setBounds(38, 59, 130, 48);
		panel_Trai.add(lbl_LoaiPhong);

		rdbtn_TatCa = new JRadioButton("Tất cả");
		rdbtn_TatCa.setFont(new Font("Tahoma", Font.BOLD, 20));
		rdbtn_TatCa.setBackground(color_xanh);
		rdbtn_TatCa.setBounds(174, 62, 137, 43);
		rdbtn_TatCa.setSelected(true);
		panel_Trai.add(rdbtn_TatCa);

		rdbtn_ThucAn = new JRadioButton("Thức ăn");
		rdbtn_ThucAn.setFont(new Font("Tahoma", Font.BOLD, 20));
		rdbtn_ThucAn.setBackground(color_xanh);
		rdbtn_ThucAn.setBounds(313, 62, 165, 43);
		panel_Trai.add(rdbtn_ThucAn);

		rdbtn_DoUong = new JRadioButton("Đồ uống");
		rdbtn_DoUong.setFont(new Font("Tahoma", Font.BOLD, 20));
		rdbtn_DoUong.setBackground(color_xanh);
		rdbtn_DoUong.setBounds(499, 62, 157, 43);
		panel_Trai.add(rdbtn_DoUong);

		JLabel lbl_txtTim = new JLabel("Tên dịch vụ:");
		lbl_txtTim.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_txtTim.setBounds(38, 118, 130, 48);
		panel_Trai.add(lbl_txtTim);

		txt_Tim = new JTextField();
		txt_Tim.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_Tim.setBounds(178, 124, 177, 37);
		panel_Trai.add(txt_Tim);
		txt_Tim.setColumns(10);

		btn_TimKiem = new JButton("Tìm");
		btn_TimKiem.setIcon(new ImageIcon("src/main/java/img/Search-icon.png"));
		btn_TimKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_TimKiem.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_TimKiem.setBounds(365, 124, 128, 37);
		panel_Trai.add(btn_TimKiem);

		// tạo bảng kho
		JPanel pn_BangKho = new JPanel();
		pn_BangKho.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Kho", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 51)));
		pn_BangKho.setLayout(new GridLayout(1, 0, 0, 0));
		pn_BangKho.setBackground(color_xanh);
		pn_BangKho.setBounds(10, 170, 756, 380);
		panel_Trai.add(pn_BangKho);
		pn_BangKho.setLayout(new BorderLayout());

		// tạo cột kho
		modelKho = new DefaultTableModel();
		modelKho.addColumn("Mã DV");
		modelKho.addColumn("Tên DV");
		modelKho.addColumn("Giá");
		modelKho.addColumn("Đơn vị");
		modelKho.addColumn("Tồn kho");

		tb_Kho = new JTable(modelKho);
		tb_Kho.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setNutThemXoa(false);
				btn_Them.setEnabled(true);
				btn_Them.setBackground(new Color(138, 43, 226));
			}
		});
		tb_Kho.getColumnModel().getColumn(0).setPreferredWidth(30);
		tb_Kho.getColumnModel().getColumn(1).setPreferredWidth(150);

		JScrollPane jScrollPane = new JScrollPane(tb_Kho, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setBounds(27, 151, 631, 394);
		pn_BangKho.add(jScrollPane);
		JTableHeader tbHeaderHoaDon = tb_Kho.getTableHeader();
		tbHeaderHoaDon.setFont(font2);
		tbHeaderHoaDon.setBackground(color2);
		tb_Kho.setFont(font2);
		tb_Kho.setRowHeight(35);

		btn_LamMoi = new JButton("Làm mới");
		btn_LamMoi.setForeground(Color.WHITE);
		btn_LamMoi.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_LamMoi.setIcon(new ImageIcon("src/main/java/img/Button-Refresh-icon.png"));
		btn_LamMoi.setBackground(new Color(52, 168, 83));
		btn_LamMoi.setBounds(517, 124, 178, 37);
		panel_Trai.add(btn_LamMoi);

		lbl_TieuDeTrai = new JLabel("Thông tin dịch vụ");
		lbl_TieuDeTrai.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TieuDeTrai.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbl_TieuDeTrai.setBounds(0, 0, 705, 48);
		panel_Trai.add(lbl_TieuDeTrai);

		btn_QuayLai = new JButton("Thoát");
		btn_QuayLai.setIcon(new ImageIcon("src/main/java/img/return.png"));
		btn_QuayLai.setBounds(10, 653, 213, 53);
		panel_Trai.add(btn_QuayLai);
		btn_QuayLai.setForeground(Color.WHITE);
		btn_QuayLai.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn_QuayLai.setBackground(new Color(5, 136, 227));

		btn_Them = new JButton("Thêm");
		btn_Them.setPreferredSize(new Dimension(119, 37));
		btn_Them.setForeground(Color.WHITE);
		btn_Them.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Them.png"));
		btn_Them.setFont(new Font("Times New Roman", Font.BOLD, 24));
		btn_Them.setBackground(new Color(138, 43, 226));
		btn_Them.setBounds(283, 566, 137, 37);
		panel_Trai.add(btn_Them);
		btn_Them.addActionListener(this);
		btn_QuayLai.addActionListener(this);

		// Panle trái
		JPanel panel_Phai = new JPanel();
		panel_Phai.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_Phai.setLayout(null);
		panel_Phai.setBackground(color_xanh);
		panel_Phai.setBounds(786, 85, 780, 724);
		pnBorder.add(panel_Phai);

		JLabel lbl_TieuDePhai = new JLabel("Thông tin hoá đơn");
		lbl_TieuDePhai.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TieuDePhai.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbl_TieuDePhai.setBounds(0, 0, 705, 48);
		panel_Phai.add(lbl_TieuDePhai);

		lbl_MaPhong = new JLabel("Mã phòng: P004           ");
		lbl_MaPhong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_MaPhong.setBounds(36, 57, 237, 48);
		panel_Phai.add(lbl_MaPhong);

		lbl_TenKH = new JLabel("Tên khách đặt:  Nguyễn Văn A");
		lbl_TenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_TenKH.setBounds(384, 57, 341, 48);
		panel_Phai.add(lbl_TenKH);

		// bảng dịch vụ phòng
		pn_BangTTPhong = new JPanel();
		pn_BangTTPhong.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dịch vụ đã thêm",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		pn_BangTTPhong.setBackground(color_xanh);
		pn_BangTTPhong.setBounds(10, 170, 760, 374);
		panel_Phai.add(pn_BangTTPhong);
		pn_BangTTPhong.setLayout(new GridLayout(1, 0, 0, 0));

		// tạo cột cho bảng của khách hàng
		modelKhachHang = new DefaultTableModel();
		modelKhachHang.addColumn("STT");
		modelKhachHang.addColumn("Tên DV");
		modelKhachHang.addColumn("Giá");
		modelKhachHang.addColumn("Đã thêm");
		modelKhachHang.addColumn("Thành tiền");
		modelKhachHang.addColumn("Mã dịch vụ");
		tb_KhachHang = new JTable(modelKhachHang);
		tb_KhachHang.setDefaultEditor(Object.class, null);
		tb_Kho.setDefaultEditor(Object.class, null);
		tb_KhachHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setNutThemXoa(true);
				btn_Them.setEnabled(false);
				btn_Them.setBackground(Color.gray);
			}
		});
		JScrollPane jScrollPane_KH = new JScrollPane(tb_KhachHang, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane_KH.setBounds(27, 151, 631, 394);
		pn_BangTTPhong.add(jScrollPane_KH);
		JTableHeader tbHeader_KH = tb_KhachHang.getTableHeader();
		tbHeader_KH.setFont(font2);
		tbHeader_KH.setBackground(color2);
		tb_KhachHang.setFont(font2);
		tb_KhachHang.setRowHeight(35);
		tb_KhachHang.getColumnModel().getColumn(5).setPreferredWidth(5);
		tb_KhachHang.getColumnModel().getColumn(4).setPreferredWidth(220);
		tb_KhachHang.getColumnModel().getColumn(3).setPreferredWidth(120);
		tb_KhachHang.getColumnModel().getColumn(2).setPreferredWidth(130);
		tb_KhachHang.getColumnModel().getColumn(1).setPreferredWidth(220);
		tb_KhachHang.getColumnModel().getColumn(0).setPreferredWidth(45);

		lbl_TongCong = new JLabel("Tổng thành tiền:");
		lbl_TongCong.setBackground(new Color(255, 51, 102));
		lbl_TongCong.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbl_TongCong.setBounds(10, 650, 692, 48);
		panel_Phai.add(lbl_TongCong);

		lbl_GioVao = new JLabel("Giờ vào:");
		lbl_GioVao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_GioVao.setBounds(36, 104, 367, 48);
		panel_Phai.add(lbl_GioVao);

		lbl_TongThanhTien = new JLabel("");
		lbl_TongThanhTien.setForeground(Color.RED);
		lbl_TongThanhTien.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_TongThanhTien.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbl_TongThanhTien.setBounds(324, 650, 378, 48);
		panel_Phai.add(lbl_TongThanhTien);

		btn_HieuChinh = new JButton("Sửa");
		btn_HieuChinh.setPreferredSize(new Dimension(119, 37));
		btn_HieuChinh.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Sua.png"));
		btn_HieuChinh.setForeground(Color.WHITE);
		btn_HieuChinh.setFont(new Font("Times New Roman", Font.BOLD, 24));
		btn_HieuChinh.setBackground(new Color(138, 43, 226));
		btn_HieuChinh.setBounds(158, 555, 119, 37);
		panel_Phai.add(btn_HieuChinh);

		btn_Xoa = new JButton("Xóa");
		btn_Xoa.setPreferredSize(new Dimension(119, 37));
		btn_Xoa.setForeground(Color.WHITE);
		btn_Xoa.setFont(new Font("Times New Roman", Font.BOLD, 24));
		btn_Xoa.setBackground(new Color(138, 43, 226));
		btn_Xoa.setIcon(new ImageIcon("src/main/java/img/Close-2-icon.png"));
		btn_Xoa.setBounds(466, 555, 119, 37);
		panel_Phai.add(btn_Xoa);

		btn_Xoa.addActionListener(this);
		btn_HieuChinh.addActionListener(this);

		layThongTinPhong(maPhong);
		docThongTinVaoKhachHang();
		docBangDichVu((ArrayList<DichVu>) dao_DichVu.getAllDichVu(), "Tất cả");
		laySoLuongTungLoai();
		setNutThemXoa(false);
		// sự kiện
		maPhongTruyen = maPhong;
		maNhanVienTruyen = maNhanVienNhan;
		btn_TimKiem.addActionListener(this);
		rdbtn_TatCa.addActionListener(this);
		rdbtn_DoUong.addActionListener(this);
		rdbtn_ThucAn.addActionListener(this);
		btn_LamMoi.addActionListener(this);
	}

	public void setNutThemXoa(boolean boll) {
		btn_Them.setEnabled(boll);
		btn_HieuChinh.setEnabled(boll);
		btn_Xoa.setEnabled(boll);
		if (boll) {
			btn_Them.setBackground(new Color(138, 43, 226));
			btn_HieuChinh.setBackground(new Color(138, 43, 226));
			btn_Xoa.setBackground(new Color(138, 43, 226));
		} else {
			btn_Them.setBackground(Color.gray);
			btn_HieuChinh.setBackground(Color.gray);
			btn_Xoa.setBackground(Color.gray);
		}
	}

	public void layThongTinPhong(String maPhongNhap) throws RemoteException {
		phongNhan = dao_Phong.getPhongTheoMa(maPhongNhap);
	}

	public void docThongTinVaoKhachHang() throws RemoteException {
		HoaDon hdHienTai = layHDHienTai();
		if (hdHienTai != null) {
			lbl_MaPhong.setText("Mã hoá đơn: " + hdHienTai.getMaHoaDon());
			lbl_GioVao.setText("Giờ vào phòng: " + layCTHD_PhongCuoi().getGioVao().toString());
			lbl_TenKH.setText("Tên khách: " + hdHienTai.getKhachHang().getTenKhachHang());
			docBangKhachHang();
			return;
		}
		lbl_MaPhong.setText("Mã hoá đơn:");
		lbl_GioVao.setText("Giờ vào phòng: ");
		lbl_TenKH.setText("Tên khách: ");
	}

	public HoaDon layHDHienTai() throws RemoteException {
		ArrayList<ChiTietHoaDon_Phong> dsHoaDon = dao_CTHD_Phong.getDSTheoMaPhong(phongNhan.getMaPhong());
		if (dsHoaDon.size() != 0) {// kiểm tra phòng có hoá đơn nào hay chưa
			// kiểm tra phòng có đang được sử dụng hay không
			ChiTietHoaDon_Phong cTHD_PCuoi = dsHoaDon.get(dsHoaDon.size() - 1);
			if (cTHD_PCuoi.getGioRa() == null) // phòng chưa trả - còn đang sử dụng
			{
				HoaDon hDon = cTHD_PCuoi.getHoaDon();// hoá đơn cuối cùng của phòng
				return hDon;
			}
		}
		return null;// phòng ko ở trạng thái đang sử dụng
	}

	public ChiTietHoaDon_Phong layCTHD_PhongCuoi() throws RemoteException {
		ArrayList<ChiTietHoaDon_Phong> dsHoaDon = dao_CTHD_Phong.getDSTheoMaPhong(phongNhan.getMaPhong());
		if (dsHoaDon.size() != 0) {// kiểm tra phòng có hoá đơn nào hay chưa
			return dsHoaDon.get(dsHoaDon.size() - 1);
		}
		return null;// phòng ko ở trạng thái đang sử dụng
	}

	public void docBangKhachHang() throws RemoteException {
		modelKhachHang.setRowCount(0);
		String maHD = layHDHienTai().getMaHoaDon();
		ArrayList<ChiTietHoaDon_DichVu> dsDichVu = (ArrayList<ChiTietHoaDon_DichVu>) dao_CTHD_DV.getDSTheoMaHD(maHD);
		int i = 1;
		// ẩn cột mã hoá đơn
		for (ChiTietHoaDon_DichVu ct : dsDichVu) {
			Long thanhTien = ct.getDichVu().getGia() * ct.getSoLuong();
			String row[] = { i++ + "", ct.getDichVu().getTenDV(), df.format(ct.getDichVu().getGia()),
					ct.getSoLuong() + "", df.format(thanhTien) + " VNĐ", ct.getDichVu().getMaDV() };
			modelKhachHang.addRow(row);
		}
		tinhTong();
	}

	public void laySoLuongTungLoai() throws RemoteException {
		int slDoUong = dao_DichVu.getDSDichVuTheoLoai(dao_LoaiDichVu.getLoaiTheoTen("Đồ uống")).size();
		int slThucAn = dao_DichVu.getDSDichVuTheoLoai(dao_LoaiDichVu.getLoaiTheoTen("Thức ăn")).size();
		int slTong = slDoUong + slThucAn;
		rdbtn_ThucAn.setText("Thức ăn(" + slThucAn + ")");
		rdbtn_DoUong.setText("Đồ uống(" + slDoUong + ")");
		rdbtn_TatCa.setText("Tất cả(" + slTong + ")");
	}

	public void docBangDichVu(ArrayList<DichVu> dsDichVu, String loai) {
		modelKho.setRowCount(0);// làm mới
		if (!loai.equals("Tất cả")) {
			for (DichVu dichVu : dsDichVu) {
				String row[] = { dichVu.getMaDV(), dichVu.getTenDV(), df.format(dichVu.getGia()),
						dichVu.getDonVi().getTenDonVi() + "", dichVu.getSoLuong() + "" };
				modelKho.addRow(row);
			}
			return;
		}
		for (DichVu dichVu : dsDichVu) {
			String row[] = { dichVu.getMaDV(), dichVu.getTenDV(), df.format(dichVu.getGia()),
					dichVu.getDonVi().getTenDonVi() + "", dichVu.getSoLuong() + "" };
			modelKho.addRow(row);
		}
	}

	public void setNut(boolean bl) {
		rdbtn_TatCa.setSelected(bl);
		rdbtn_DoUong.setSelected(bl);
		rdbtn_ThucAn.setSelected(bl);
	}

	public void themVaoHoaDon(String maDichVu, int soLuongThem) throws RemoteException {
		String maHD = layHDHienTai().getMaHoaDon();
		DichVu dVu = dao_DichVu.getDichVuTheoMa(maDichVu);
		dVu.setSoLuong(dVu.getSoLuong() - soLuongThem);
		ChiTietHoaDon_DichVu cthd_dVu = dao_CTHD_DV.getCTHD_DV(maDichVu, maHD, maPhongTruyen);
		// tồn tại thì tăng số lượng
		if (cthd_dVu != null) {
			cthd_dVu.setSoLuong(soLuongThem + cthd_dVu.getSoLuong());
			// lưu vào sql
			dao_CTHD_DV.capNhap(cthd_dVu);
		}
		// không tồn tại thì tạo ra cthoaDon_DV mới
		else {
			HoaDon hDon = dao_HoaDon.getHDTheoMaHD(maHD);
			ChiTietHoaDon_DichVu cth_DVMoi = new ChiTietHoaDon_DichVu(hDon, dVu, phongNhan, soLuongThem);
			// lưu vào sql
			dao_CTHD_DV.themCTHD_DVMoi(cth_DVMoi);
		}
		dao_DichVu.updateDichVu(dVu, false);// khong cap nhap anh
	}

	public int capNhapSoLuong(String maDichVu, int soLuongMoi, String soLuongHienTai) throws RemoteException {
		String maHD = layHDHienTai().getMaHoaDon();
		DichVu dVu = dao_DichVu.getDichVuTheoMa(maDichVu);
		ChiTietHoaDon_DichVu cthd_dVu = dao_CTHD_DV.getCTHD_DV(maDichVu, maHD, maPhongTruyen);
		int soLuongHT = Integer.parseInt(soLuongHienTai);
		int tongSoLuong = dVu.getSoLuong() + soLuongHT;
		if (soLuongMoi > tongSoLuong)
			return tongSoLuong;
		if (soLuongMoi == 0) {
			xoaDichVuKH(maDichVu, soLuongHienTai);
			return -1;
		}
		// thay đổi số lượng
		dVu.setSoLuong(tongSoLuong - soLuongMoi);
		cthd_dVu.setSoLuong(soLuongMoi);
		// lưu vào sql
		dao_CTHD_DV.capNhap(cthd_dVu);
		dao_DichVu.updateDichVu(dVu, false);// khong cap nhap anh
		return -1;
	}

	// xoá dịch vụ đã thêm
	public boolean xoaDichVuKH(String maDichVu, String soLuongHienTai) throws RemoteException {
		String maHD = layHDHienTai().getMaHoaDon();
		DichVu dVu = dao_DichVu.getDichVuTheoMa(maDichVu);
		ChiTietHoaDon_DichVu cthd_dVu = dao_CTHD_DV.getCTHD_DV(maDichVu, maHD, maPhongTruyen);
		int soLuongHT = Integer.parseInt(soLuongHienTai);
		int tongSoLuong = dVu.getSoLuong() + soLuongHT;
		// cập nhập vào trong tổng số dv
		dVu.setSoLuong(tongSoLuong);
		// lưu vào sql
		dao_DichVu.updateDichVu(dVu, false);// khong cap nhap anh
		return dao_CTHD_DV.xoaCTHD_DV(cthd_dVu);
	}

	public void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	public void tinhTong() {
		long tong = 0;
		for (int i = 0; i < modelKhachHang.getRowCount(); i++) {
			String tongThanhTien = modelKhachHang.getValueAt(i, 4).toString();
			try {
				tong = tong + Long.parseLong(df.parse(tongThanhTien).toString());
			} catch (NumberFormatException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		lbl_TongThanhTien.setText(df.format(tong) + " VNĐ");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		String loai = "Tất cả";
		if (obj.equals(btn_LamMoi)) {
			setNut(false);
			rdbtn_TatCa.setSelected(true);
			loai = "Tất cả";
			try {
				docBangDichVu((ArrayList<DichVu>) dao_DichVu.getAllDichVu(), "Tất cả");
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			txt_Tim.setText("");
		}
		if (obj.equals(btn_QuayLai)) {
			try {
				FrmXuLyPhongDangSuDung.dsALL_CTHD_DV = (ArrayList<ChiTietHoaDon_DichVu>) dao_CTHD_DV.getAllDanhSach();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			try {
				FrmXuLyPhongDangSuDung.dsALL_CTHD_Phong = dao_CTHD_Phong.getALLDanhSach();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			try {
				FrmXuLyPhongDangSuDung.docThongTinPhong();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			dispose();
		}
		if (obj.equals(rdbtn_TatCa)) {
			setNut(false);
			rdbtn_TatCa.setSelected(true);
			loai = "Tất cả";
			try {
				docBangDichVu((ArrayList<DichVu>) dao_DichVu.getAllDichVu(), loai);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if (obj.equals(rdbtn_DoUong)) {
			setNut(false);
			rdbtn_DoUong.setSelected(true);
			loai = "Đồ uống";
			try {
				docBangDichVu((ArrayList<DichVu>) dao_DichVu.getDSDichVuTheoLoai(dao_LoaiDichVu.getLoaiTheoTen("Đồ uống")), loai);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if (obj.equals(rdbtn_ThucAn)) {
			setNut(false);
			rdbtn_ThucAn.setSelected(true);
			loai = "Thức ăn";
			try {
				docBangDichVu((ArrayList<DichVu>) dao_DichVu.getDSDichVuTheoLoai(dao_LoaiDichVu.getLoaiTheoTen("Thức ăn")), loai);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if (obj.equals(btn_TimKiem)) {
			String tenTimString = txt_Tim.getText();
			try {
				docBangDichVu((ArrayList<DichVu>) dao_DichVu.getDSDichVuTheoTenTuongDoi(tenTimString), loai);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if (obj.equals(btn_Them)) {
			int hangDuocChon = tb_Kho.getSelectedRow();
			if (hangDuocChon == -1) {
				thongBaoLoi("Bạn cần phải chọn một hàng", "Thêm số lượng");
				return;
			}
			String maDV = modelKho.getValueAt(hangDuocChon, 0).toString();
			try {
				String soLuongString = JOptionPane.showInputDialog("Nhập số lượng thêm:");
				if (soLuongString == null)
					return;
				int soLuong = Integer.parseInt(soLuongString);
				int tonKho = Integer.parseInt(modelKho.getValueAt(hangDuocChon, 4).toString());
				if (soLuong > tonKho) {
					thongBaoLoi("Số lượng trong kho không đủ!\n Trong kho chỉ còn " + tonKho, "Thêm số lượng");
				} else if (soLuong != 0) {
					themVaoHoaDon(maDV, soLuong);
//					JOptionPane.showMessageDialog(this, "Thêm dịch vụ thành công", "Thêm dịch vụ",
//							JOptionPane.INFORMATION_MESSAGE);
					docBangDichVu((ArrayList<DichVu>) dao_DichVu.getAllDichVu(), loai);
					docBangKhachHang();
				}
			} catch (NumberFormatException | RemoteException e2) {
				thongBaoLoi("Bạn phải nhập số !", "Nhập thêm số lượng");
			}
		}
		if (obj.equals(btn_HieuChinh)) {
			int hangDuocChon = tb_KhachHang.getSelectedRow();
			if (hangDuocChon == -1) {
				thongBaoLoi("Bạn cần phải chọn một hàng để thay đổi số lượng", "Thay đổi số lượng");
				return;
			}
			String maDV = modelKhachHang.getValueAt(hangDuocChon, 5).toString();
			String soLuongCu = modelKhachHang.getValueAt(hangDuocChon, 3).toString();
			try {
				String soLuongString = JOptionPane.showInputDialog("Nhập số lượng mới:");
				if (soLuongString == null)
					return;
				int soLuongMoi = Integer.parseInt(soLuongString);
				int kiemTra = capNhapSoLuong(maDV, soLuongMoi, soLuongCu);
				if (kiemTra != -1) {
					thongBaoLoi(
							"Số lượng trong kho không đủ. Thay đổi số lượng không thành công !\nChỉ được nhập nhỏ hơn: "
									+ kiemTra,
							"Nhập thay đổi số lượng");
				} else {
					JOptionPane.showMessageDialog(this, "Thay đổi số lượng dịch vụ thành công", "Thay đổi số lượng",
							JOptionPane.INFORMATION_MESSAGE);
					docBangDichVu((ArrayList<DichVu>) dao_DichVu.getAllDichVu(), loai);
					docBangKhachHang();
				}
			} catch (NumberFormatException | RemoteException e3) {
				thongBaoLoi("Bạn phải nhập số !", "Thay đổi số lượng");
			}
		}
		if (obj.equals(btn_Xoa)) {
			int hangDuocChon = tb_KhachHang.getSelectedRow();
			if (hangDuocChon == -1) {
				thongBaoLoi("Bạn cần phải chọn một hàng để xoá", "Xoá dịch vụ");
				return;
			}
			String maDV = modelKhachHang.getValueAt(hangDuocChon, 5).toString();
			String soLuong = modelKhachHang.getValueAt(hangDuocChon, 3).toString();
			String tenDV = modelKhachHang.getValueAt(hangDuocChon, 1).toString();
			int hoiLai = JOptionPane.showConfirmDialog(this, "Bạn xác nhận xoá dịch vụ " + tenDV, "Xác nhận xoá",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (hoiLai == JOptionPane.YES_OPTION) {
				try {
					if (xoaDichVuKH(maDV, soLuong)) {
						JOptionPane.showMessageDialog(this, "Xoá thành công!", "Xoá dịch vụ",
								JOptionPane.INFORMATION_MESSAGE);
						docBangDichVu((ArrayList<DichVu>) dao_DichVu.getAllDichVu(), loai);
						docBangKhachHang();
					}
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			}
		}
	}

}
