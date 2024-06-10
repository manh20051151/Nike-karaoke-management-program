package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;

import dao.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

//import dao.DAO_ChiTietHoaDon_DichVu;
//import dao.DAO_ChiTietHoaDon_Phong;
//import dao.DAO_HoaDon;
//import dao.DAO_KhachHang;
//import dao.DAO_LoaiKhachHang;
//import dao.DAO_NhanVien;
//import dao.DAO_PhieuDatPhong;
//import dao.DAO_Phong;
import entity.ChiTietHoaDon_DichVu;
import entity.ChiTietHoaDon_Phong;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;

public class FrmXuLyPhongDangSuDung extends JFrame implements Runnable, ActionListener, MouseListener {
	private static final String URL = "rmi://26.173.247.75:7878/";
	DaoPhong dao_Phong = (DaoPhong) Naming.lookup(URL + "daoPhong");
	DaoHoaDon dao_HoaDon = (DaoHoaDon) Naming.lookup(URL + "hoaDon");
	DaoPhieuDatPhong dao_PhieuDatPhong = (DaoPhieuDatPhong) Naming.lookup(URL + "daoPhieuDatPhong");
	private static DaoChiTietHoaDon_Phong dao_CTHD_Phong;

	static {
		try {
			dao_CTHD_Phong = (DaoChiTietHoaDon_Phong) Naming.lookup(URL + "daoChiTietHoaDon_phong");
		} catch (NotBoundException e) {
			throw new RuntimeException(e);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	DaoNhanVien dao_NhanVien = (DaoNhanVien) Naming.lookup(URL + "daoNhanVien");
	private static DaoKhachHang dao_KhachHang;

	static {
		try {
			dao_KhachHang = (DaoKhachHang) Naming.lookup(URL + "daoKhachHang");
		} catch (NotBoundException e) {
			throw new RuntimeException(e);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	DaoLoaiKhachHang dao_LoaiKhachHang = (DaoLoaiKhachHang) Naming.lookup(URL + "daoLoaiKhachHang");
	private static DaoChiTietHoaDon_DichVu dao_CTHD_DV;

	static {
		try {
			dao_CTHD_DV = (DaoChiTietHoaDon_DichVu) Naming.lookup(URL + "daoChiTietHoaDon_dichVu");
		} catch (NotBoundException e) {
			throw new RuntimeException(e);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}


//	private static DAO_Phong dao_Phong = new DAO_Phong();
//	private static DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
//	private static DAO_PhieuDatPhong dao_PhieuDatPhong = new DAO_PhieuDatPhong();
//	private static DAO_ChiTietHoaDon_Phong dao_CTHD_Phong = new DAO_ChiTietHoaDon_Phong();
//	private static DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
//	private static DAO_KhachHang dao_KhachHang = new DAO_KhachHang();
//	private static DAO_LoaiKhachHang dao_LoaiKhachHang = new DAO_LoaiKhachHang();
//	private static DAO_ChiTietHoaDon_DichVu dao_CTHD_DV = new DAO_ChiTietHoaDon_DichVu();

	public static JPanel pnBorder;
	private static JLabel txt_GioHT;
	private static Thread thread = null;
	private static JLabel txt_NgayHT;
	static int viTriNutDuocChon = -1;
	private static JScrollPane jScrollPane_KH;
	private static JLabel lbl_TieuDe_1;

	// tạo danh sách phòng
	public int button_Height = 70;
	private static JPanel panel_DSDatPhong;
	private static Panel pan_duLieuPhong = new Panel();

	static Color color1 = new Color(138, 43, 226); // BlueViolet
	Color color2 = new Color(233, 221, 244);
	private static ImageIcon icon_PhongTrong = new ImageIcon(
			"src/main/java/img/room-mini-3.png");
	private static ImageIcon icon_PhongCho = new ImageIcon(
			"src/main/java/img/room-mini-2.png");
	private static ImageIcon icon_PhongDSD = new ImageIcon(
			"src/main/java/img/room-mini-1.png");
	private static ImageIcon icon_PhongVip = new ImageIcon(
			"src/main/java/img/vip-Mini.gif");

	private static ScrollPane crPan_DSPhong;
	private static JPanel[] butt_danhSachPhong;
	static ArrayList<Phong> dsDLHienTai;
	private static String maPhongDuocChon = "";
	private static String maNhanVienTao = "";
	private static JLabel iconVip;
	int kichThuocNutChucNang = 70;
	int viTriBanDauNutChucNang = 20;
	int khoangCachNutChucNang = 90;
	private JTable tb_dsPhongDaChon;
	static ArrayList<HoaDon> dsHDThanhToan = new ArrayList<HoaDon>();
	private static JButton btn_ChonPhong;

	static DecimalFormat df_Tien = new DecimalFormat("###,###,###");
	private static JComboBox cbx_LoaiHienThi;
	private static JPanel panel_ThoiGian_1_2;
	private static JLabel lbl_ThoiGianDat_3;
	private static JLabel lbl_ThoiGianDat_4;
	private static JLabel lbl_ThoiGianDat_5;
	private static JLabel lbl_ThoiGianDat_6;
	private static JLabel lbl_ThoiGianDat_7;
	private static JLabel lbl_ThoiGianDat_8;
	private static JPanel panel_Phai;
	private static JLabel lbl_SoDT;
	static JComboBox<Object> txt_SoDT;
	private static JLabel lbl_MaKH;
	private static JLabel lbl_TenKH;
	private static JTextField txt_TenKH;
	private static JLabel lbl_TieuDe_Trai;
	private static JPanel panel_Trai_1;
	private static JLabel lbl_TieuDe_Trai_1;
	private static JPanel panel_AnhNen;
	static JTextArea txt_ThongTin;
	static JLabel txt_MaPhong;
	private static KhachHang khachDuocChon;
	private static String maKHMoi;
	static DefaultTableModel model_dSPhongDaChon;
	private static JPanel panel_Trai_2;
	static JTextArea txt_ThongTinTong;
	private static JButton btn_ChuyenPhong;
	private static JButton btn_ThanhToan;
	private static JButton btn_LocDSTheoKH;
	static DefaultTableModel modelHoaDon;
	private static JTable tb_QLDichVu;
	private static JButton btn_QLDichVu;
	private static AbstractButton btn_LamMoi;
	static JTextField txt_MaKH;
	static ArrayList<ChiTietHoaDon_Phong> dsALL_CTHD_Phong;

	static {
		try {
			dsALL_CTHD_Phong = dao_CTHD_Phong.getALLDanhSach();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	static ArrayList<ChiTietHoaDon_DichVu> dsALL_CTHD_DV;

	static {
		try {
			dsALL_CTHD_DV = (ArrayList<ChiTietHoaDon_DichVu>) dao_CTHD_DV.getAllDanhSach();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmXuLyPhongDangSuDung frame = new FrmXuLyPhongDangSuDung("NV001", "0745100443");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmXuLyPhongDangSuDung(String nhanVien, String soDTTruyen) throws MalformedURLException, NotBoundException, RemoteException {
		setTitle("Đặt phòng");

		// Màu
		Color color1 = new Color(138, 43, 226); // BlueViolet
		Color color2 = new Color(233, 221, 244);
		Color color_Xanh = new Color(60, 182, 192);
		Color color_Tim = new Color(212, 81, 253);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1980, 1040); // Mới chỉnh
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		pnBorder = new JPanel();
		pnBorder.setBackground(new Color(233, 221, 244)); // Mới thêm
		pnBorder.setLayout(null);
		setContentPane(pnBorder);

		JPanel panel_Menu = new JPanel();
		panel_Menu.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_Menu.setLayout(null);
		panel_Menu.setBackground(Color.WHITE); // Mới chỉnh
		panel_Menu.setBounds(1277, 523, 637, 389); // Mới chỉnh
		pnBorder.add(panel_Menu);

		panel_DSDatPhong = new JPanel();
		panel_DSDatPhong.setLayout(null);
		panel_DSDatPhong.setBackground(Color.WHITE);
		panel_DSDatPhong.setBounds(330, 289, 934, 623); // Mới chỉnh
		pnBorder.add(panel_DSDatPhong);

		// tạo khung bảng danh sách phòng
		crPan_DSPhong = new ScrollPane();
		pan_duLieuPhong.setBackground(Color.WHITE);
		pan_duLieuPhong.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 20));
		crPan_DSPhong.add(pan_duLieuPhong);

		crPan_DSPhong.setBackground(Color.white);
		jScrollPane_KH = new JScrollPane(crPan_DSPhong, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane_KH.setBounds(0, 52, 934, 571); // Mới chỉnh
		panel_DSDatPhong.add(jScrollPane_KH);
		jScrollPane_KH.setAutoscrolls(true);

		JPanel panel_TieuDe = new JPanel();
		panel_TieuDe.setBackground(color_Xanh);
		panel_TieuDe.setBounds(0, 0, 934, 56); // Mới chỉnh
		panel_DSDatPhong.add(panel_TieuDe);
		panel_TieuDe.setLayout(null);

		lbl_TieuDe_1 = new JLabel("Danh sách phòng đang sử dụng");
		lbl_TieuDe_1.setBounds(0, 5, 933, 45);
		panel_TieuDe.add(lbl_TieuDe_1);
		lbl_TieuDe_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TieuDe_1.setForeground(Color.BLACK);
		lbl_TieuDe_1.setFont(new Font("Tahoma", Font.BOLD, 30));

		JPanel panel_Trai = new JPanel();
		panel_Trai.setLayout(null);
		panel_Trai.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_Trai.setBackground(color_Xanh);
		panel_Trai.setBounds(10, 11, 310, 101);
		pnBorder.add(panel_Trai);

		JPanel panel_ThoiGian = new JPanel();
		panel_ThoiGian.setBounds(10, 11, 290, 73);
		panel_Trai.add(panel_ThoiGian);
		panel_ThoiGian.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		panel_ThoiGian.setLayout(null);
		panel_ThoiGian.setBackground(Color.WHITE);// Mới chỉnh

		txt_GioHT = new JLabel("1:32");
		txt_GioHT.setHorizontalAlignment(SwingConstants.CENTER);
		txt_GioHT.setFont(new Font("Tahoma", Font.BOLD, 20));
		txt_GioHT.setBounds(0, 0, 280, 45); // Mới chỉnh
		panel_ThoiGian.add(txt_GioHT);

		txt_NgayHT = new JLabel("12/10/2022");
		txt_NgayHT.setHorizontalAlignment(SwingConstants.CENTER);
		txt_NgayHT.setFont(new Font("Tahoma", Font.BOLD, 20));
		txt_NgayHT.setBounds(0, 34, 290, 39); // Mới chỉnh
		panel_ThoiGian.add(txt_NgayHT);

		model_dSPhongDaChon = new DefaultTableModel();
		model_dSPhongDaChon.addColumn("STT");
		model_dSPhongDaChon.addColumn("Mã phòng");
		model_dSPhongDaChon.addColumn("Tiền dịch vụ");
		model_dSPhongDaChon.addColumn("Tiền phòng");
		model_dSPhongDaChon.addColumn("Thành tiền");

		panel_Phai = new JPanel();
		panel_Phai.setLayout(null);
		panel_Phai.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_Phai.setBackground(color_Xanh);
		panel_Phai.setBounds(329, 11, 934, 267);
		pnBorder.add(panel_Phai);

		txt_SoDT = new JComboBox<Object>();
		txt_SoDT.setEditable(true);
		txt_SoDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		// txt_SoDT.setColumns(10);
		txt_SoDT.setBounds(198, 70, 257, 37);
		panel_Phai.add(txt_SoDT);
		txt_SoDT.addItem("");
		// tự động kiểm tra
		AutoCompleteDecorator.decorate(txt_SoDT);
		// txt_SoDT.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyPressed(KeyEvent e) {
		// String soDT = txt_SoDT.getEditor().getEditorComponent().toString().trim();
		// kiemTraSDT(soDT);
		// System.err.println("/" + txt_SoDT.getSelectedItem().toString().trim() + "/");
		// }
		// });

		txt_SoDT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String soDT = txt_SoDT.getSelectedItem() == null ? "" : txt_SoDT.getSelectedItem().toString().trim();
				try {
					kiemTraSDT(soDT, null);
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			}
		});

		lbl_SoDT = new JLabel("SDT khách: ");
		lbl_SoDT.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_SoDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_SoDT.setBounds(22, 64, 166, 48);
		panel_Phai.add(lbl_SoDT);

		lbl_MaKH = new JLabel("Mã khách hàng: ");
		lbl_MaKH.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_MaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_MaKH.setBounds(22, 125, 166, 48);
		panel_Phai.add(lbl_MaKH);

		lbl_TenKH = new JLabel("Tên khách hàng: ");
		lbl_TenKH.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_TenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_TenKH.setBounds(387, 125, 166, 48);
		panel_Phai.add(lbl_TenKH);

		txt_TenKH = new JTextField();
		txt_TenKH.setEditable(false);
		txt_TenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_TenKH.setColumns(10);
		txt_TenKH.setBounds(561, 125, 303, 48);
		panel_Phai.add(txt_TenKH);

		lbl_TieuDe_Trai = new JLabel("Thông tin khách đặt");
		lbl_TieuDe_Trai.setBounds(0, 11, 934, 48);
		panel_Phai.add(lbl_TieuDe_Trai);
		lbl_TieuDe_Trai.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TieuDe_Trai.setForeground(Color.WHITE);
		lbl_TieuDe_Trai.setFont(new Font("Tahoma", Font.BOLD, 30));

		panel_Trai_2 = new JPanel();
		panel_Trai_2.setLayout(null);
		panel_Trai_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,

				TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_Trai_2.setBackground(new Color(60, 182, 192));
		panel_Trai_2.setBounds(10, 123, 310, 372);
		pnBorder.add(panel_Trai_2);

		panel_ThoiGian_1_2 = new JPanel();
		panel_ThoiGian_1_2.setBounds(10, 94, 290, 267);
		panel_Trai_2.add(panel_ThoiGian_1_2);
		panel_ThoiGian_1_2.setLayout(null);
		panel_ThoiGian_1_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"M\u00F4 t\u1EA3 danh s\u00E1ch ph\u00F2ng c\u00F3 th\u1EC3 \u0111\u1EB7t", TitledBorder.LEFT,
				TitledBorder.TOP, null, Color.WHITE));
		panel_ThoiGian_1_2.setBackground(color_Xanh);

		lbl_ThoiGianDat_3 = new JLabel("Phòng trống:");
		lbl_ThoiGianDat_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ThoiGianDat_3.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_3.setBounds(10, 24, 112, 48);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_3);

		lbl_ThoiGianDat_4 = new JLabel("");
		lbl_ThoiGianDat_4.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_4.setBounds(209, 11, 70, 64);
		lbl_ThoiGianDat_4.setIcon(icon_PhongTrong);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_4);

		lbl_ThoiGianDat_5 = new JLabel("Phòng chờ:");
		lbl_ThoiGianDat_5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ThoiGianDat_5.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_5.setBounds(10, 86, 112, 48);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_5);

		lbl_ThoiGianDat_6 = new JLabel();
		lbl_ThoiGianDat_6.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_6.setBounds(209, 73, 70, 64);
		lbl_ThoiGianDat_6.setIcon(icon_PhongCho);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_6);

		lbl_ThoiGianDat_7 = new JLabel("Phòng đang sử dụng:");
		lbl_ThoiGianDat_7.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ThoiGianDat_7.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_7.setBounds(10, 158, 187, 48);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_7);

		lbl_ThoiGianDat_8 = new JLabel();
		lbl_ThoiGianDat_8.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_8.setBounds(209, 145, 70, 64);
		lbl_ThoiGianDat_8.setIcon(icon_PhongDSD);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_8);

		JLabel lbl_ThoiGianDat_7_1 = new JLabel("Phòng VIP:");
		lbl_ThoiGianDat_7_1.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_ThoiGianDat_7_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_7_1.setBounds(10, 217, 139, 48);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_7_1);

		JLabel lbl_ThoiGianDat_8_1 = new JLabel();
		lbl_ThoiGianDat_8_1.setIcon(new ImageIcon("src/main/java/img/vip-Mini.gif"));
		lbl_ThoiGianDat_8_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_8_1.setBounds(209, 203, 70, 64);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_8_1);

		JPanel panel_ThoiGian_1_1 = new JPanel();
		panel_ThoiGian_1_1.setBounds(10, 11, 290, 72);
		panel_Trai_2.add(panel_ThoiGian_1_1);
		panel_ThoiGian_1_1.setLayout(null);
		panel_ThoiGian_1_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Ch\u1EBF \u0111\u1ED9 xem danh s\u00E1ch ph\u00F2ng c\u00F3 th\u1EC3 \u0111\u1EB7t", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_ThoiGian_1_1.setBackground(color_Xanh);

		cbx_LoaiHienThi = new JComboBox();
		cbx_LoaiHienThi.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		cbx_LoaiHienThi.setBackground(new Color(204, 204, 204));
		cbx_LoaiHienThi.setBounds(40, 18, 210, 43);
		cbx_LoaiHienThi.addItem("Mã phòng");
		cbx_LoaiHienThi.addItem("Loại phòng");
		panel_ThoiGian_1_1.add(cbx_LoaiHienThi);
		cbx_LoaiHienThi.addActionListener(this);

		txt_ThongTinTong = new JTextArea();
		txt_ThongTinTong.setText("Bạn chưa chọn phòng thanh toán");
		txt_ThongTinTong.setForeground(Color.BLACK);
		txt_ThongTinTong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_ThongTinTong.setEditable(false);
		txt_ThongTinTong.setBackground(Color.WHITE);
		txt_ThongTinTong.setBounds(78, 200, 481, 97);
		panel_Menu.add(txt_ThongTinTong);

		panel_Trai_1 = new JPanel();
		panel_Trai_1.setBounds(1277, 11, 637, 501);
		pnBorder.add(panel_Trai_1);
		panel_Trai_1.setLayout(null);
		panel_Trai_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_Trai_1.setBackground(color_Xanh);

		panel_AnhNen = new JPanel();
		panel_AnhNen.setLayout(null);
		panel_AnhNen.setBackground(Color.WHITE);
		panel_AnhNen.setBounds(10, 57, 617, 188);
		panel_Trai_1.add(panel_AnhNen);

		txt_ThongTin = new JTextArea();
		txt_ThongTin.setForeground(Color.BLACK);
		txt_ThongTin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_ThongTin.setEditable(false);
		txt_ThongTin.setBackground(Color.WHITE);
		txt_ThongTin.setBounds(98, 58, 481, 120);
		panel_AnhNen.add(txt_ThongTin);

		txt_MaPhong = new JLabel("");
		txt_MaPhong.setHorizontalAlignment(SwingConstants.CENTER);
		txt_MaPhong.setForeground(Color.GRAY);
		txt_MaPhong.setFont(new Font("Tahoma", Font.BOLD, 30));
		txt_MaPhong.setBounds(0, 0, 617, 59);
		panel_AnhNen.add(txt_MaPhong);

		lbl_TieuDe_Trai_1 = new JLabel("Thông tin phòng");
		lbl_TieuDe_Trai_1.setBounds(2, 0, 633, 58);
		panel_Trai_1.add(lbl_TieuDe_Trai_1);
		lbl_TieuDe_Trai_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TieuDe_Trai_1.setForeground(Color.WHITE);
		lbl_TieuDe_Trai_1.setFont(new Font("Tahoma", Font.BOLD, 30));

		JPanel pn_BangTTPhong = new JPanel();
		pn_BangTTPhong.setLayout(null);
		pn_BangTTPhong.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dịch vụ đã thêm",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		pn_BangTTPhong.setBackground(new Color(149, 214, 224));
		pn_BangTTPhong.setBounds(10, 256, 617, 165);
		panel_Trai_1.add(pn_BangTTPhong);

		// tạo cột cho bảng của khách hàng
		modelHoaDon = new DefaultTableModel();
		modelHoaDon.addColumn("STT");
		modelHoaDon.addColumn("Tên DV");
		modelHoaDon.addColumn("Giá");
		modelHoaDon.addColumn("Số lượng");
		modelHoaDon.addColumn("Thành tiền");
		pn_BangTTPhong.setLayout(null);
		tb_QLDichVu = new JTable(modelHoaDon);
		JScrollPane jScrollPane_KH = new JScrollPane(tb_QLDichVu, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane_KH.setBounds(6, 16, 601, 138);
		pn_BangTTPhong.add(jScrollPane_KH);
		tb_QLDichVu.getColumnModel().getColumn(0).setPreferredWidth(3);

		btn_QLDichVu = new JButton("Quản lý dịch vụ");
		btn_QLDichVu.setIcon(new ImageIcon("src/main/java/img/icons8-manage-32.png"));
		btn_QLDichVu.setForeground(Color.WHITE);
		btn_QLDichVu.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_QLDichVu.setBackground(new Color(138, 43, 226));
		btn_QLDichVu.setBounds(350, 432, 262, 45);
		btn_QLDichVu.setFocusable(false);
		panel_Trai_1.add(btn_QLDichVu);

		btn_ChuyenPhong = new JButton("Chuyển phòng");
		btn_ChuyenPhong.setIcon(new ImageIcon("src/main/java/img/icons8-transfer-50.png"));
		btn_ChuyenPhong.setBounds(36, 432, 278, 45);
		panel_Trai_1.add(btn_ChuyenPhong);
		btn_ChuyenPhong.setForeground(Color.WHITE);
		btn_ChuyenPhong.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_ChuyenPhong.setFocusable(false);
		btn_ChuyenPhong.setBackground(color1);
		btn_ChuyenPhong.addActionListener(this);

		btn_ThanhToan = new JButton("Thanh toán");
		btn_ThanhToan.setForeground(Color.WHITE);
		btn_ThanhToan.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_ThanhToan.setFocusable(false);
		btn_ThanhToan.setBackground(Color.BLACK);
		btn_ThanhToan.setBounds(355, 308, 260, 70);
		panel_Menu.add(btn_ThanhToan);

		btn_ChonPhong = new JButton("Chọn phòng");
		btn_ChonPhong.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Them.png"));
		btn_ChonPhong.setForeground(Color.WHITE);
		btn_ChonPhong.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_ChonPhong.setFocusable(false);
		btn_ChonPhong.setBackground(new Color(138, 43, 226));
		btn_ChonPhong.setBounds(30, 328, 265, 48);
		panel_Menu.add(btn_ChonPhong);

		JPanel pn_dsPhongDaChon = new JPanel();
		pn_dsPhongDaChon.setLayout(null);
		pn_dsPhongDaChon.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh sách phòng đã chọn thanh toán", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pn_dsPhongDaChon.setBackground(new Color(192, 192, 192));
		pn_dsPhongDaChon.setBounds(10, 11, 617, 178);
		panel_Menu.add(pn_dsPhongDaChon);

		JScrollPane jSc_dsPhongDaChon = new JScrollPane((Component) null,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jSc_dsPhongDaChon.setBounds(6, 16, 601, 151);
		pn_dsPhongDaChon.add(jSc_dsPhongDaChon);
		tb_dsPhongDaChon = new JTable(model_dSPhongDaChon);

		tb_dsPhongDaChon.setRowHeight(30);
		tb_dsPhongDaChon.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		tb_dsPhongDaChon.setDefaultEditor(Object.class, null);
		jSc_dsPhongDaChon.setViewportView(tb_dsPhongDaChon);
		tb_dsPhongDaChon.getColumnModel().getColumn(0).setPreferredWidth(5);

		tb_dsPhongDaChon.getColumnModel().getColumn(0).setPreferredWidth(5);

		btn_LocDSTheoKH = new JButton("Lọc danh sách theo khách");
		btn_LocDSTheoKH.setForeground(Color.WHITE);
		btn_LocDSTheoKH.setIcon(new ImageIcon("src/main/java/img/Search-icon.png"));
		btn_LocDSTheoKH.setFont(new Font("Dialog", Font.BOLD, 20));
		btn_LocDSTheoKH.setBackground(color1);
		btn_LocDSTheoKH.setFocusable(false);
		btn_LocDSTheoKH.setBounds(493, 201, 371, 41);
		panel_Phai.add(btn_LocDSTheoKH);

		btn_LamMoi = new JButton("Làm mới");
		btn_LamMoi.setIcon(new ImageIcon("src/main/java/img/Button-Refresh-icon.png"));
		btn_LamMoi.setForeground(Color.WHITE);
		btn_LamMoi.setBackground(color1);
		btn_LamMoi.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_LamMoi.setFocusable(false);
		btn_LamMoi.setBounds(271, 201, 171, 41);
		panel_Phai.add(btn_LamMoi);

		// ---xử lý sự kiện---
		// Tạo luồng nhỏ chạy song song với chương trình.
		thread = new Thread(this);
		thread.start();
		// lấy tất cả danh sách dữ liệu
		dsDLHienTai = dao_Phong.getDSTheoTTphong("Đang sử dụng");
		docDLVaoBang();
		// xử lý các nút
		maNhanVienTao = nhanVien;

		txt_MaKH = new JTextField();
		txt_MaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_MaKH.setEditable(false);
		txt_MaKH.setColumns(10);
		txt_MaKH.setBackground(Color.WHITE);
		txt_MaKH.setBounds(198, 125, 112, 48);
		panel_Phai.add(txt_MaKH);
		btn_QLDichVu.addActionListener(this);
		btn_ThanhToan.addActionListener(this);
		btn_LocDSTheoKH.addActionListener(this);
		btn_LamMoi.addActionListener(this);
		btn_ChonPhong.addActionListener(this);
		// xử lý khi ấn phòng được chọn
		tb_dsPhongDaChon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btn_ChonPhong.setText("Bỏ chọn phòng");
				btn_ChonPhong.setBackground(Color.red);
				btn_ChonPhong.setIcon(new ImageIcon("src/main/java/img/icons8-close-60.png"));
				// lấy phòng được chọn
				int hangDuocChon = tb_dsPhongDaChon.getSelectedRow();
				maPhongDuocChon = model_dSPhongDaChon.getValueAt(hangDuocChon, 1).toString().trim();
				setPhongDuocChon();
				try {
					docThongTinPhong();
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			}
		});
		// xử lý khi có truyền số điện thoại
		if (!soDTTruyen.equals("")) {
			dsDLHienTai = new ArrayList<Phong>();
			KhachHang khTim = dao_KhachHang.getKhachHangTheoSoDT(soDTTruyen);
			txt_SoDT.setSelectedItem(soDTTruyen);
			if (khTim == null) {
				thongBaoLoi("Khách hàng chọn không hợp lệ !", "Lọc danh sách phòng theo khách hàng");
				return;
			}
			ArrayList<HoaDon> dshDon = dao_HoaDon.getDSTheoMaKhach(khTim.getMaKhachHang());
			if (dshDon.size() == 0) {
				dsDLHienTai = null;
			} else {
				for (ChiTietHoaDon_Phong ct : dsALL_CTHD_Phong) {
					if (ct.getHoaDon().getKhachHang().getMaKhachHang().equals(khTim.getMaKhachHang())
							&& ct.getPhong().getTrangThaiPhong().equals("Đang sử dụng"))
						dsDLHienTai.add(ct.getPhong());
				}
			}
			viTriNutDuocChon = -1;
			docDLVaoBang();
		}
//		Thêm số điện thoại các khách có phòng đặt
		themSDTKhachDDP();
		// làm mới ds phòng đã chọn

	}

	// cập nhập thời gian
	public void run() {
		try {
			Date thoiGianHienTai = new Date();
			SimpleDateFormat sdf_Gio = new SimpleDateFormat("HH:mm:ss");
			SimpleDateFormat sdf_Ngay = new SimpleDateFormat("dd/MM/yyyy");
			while (true) {
				thoiGianHienTai = new Date(); // lấy thời gian hiện tại
				String ngayTrongTuan = "";
				if (thoiGianHienTai.getDay() == 0)
					ngayTrongTuan = "Chủ nhật, ";
				else
					ngayTrongTuan = "Thứ " + (thoiGianHienTai.getDay() + 1) + ", ";// do getDay() tính từ 1.
				txt_GioHT.setText(sdf_Gio.format(thoiGianHienTai));
				txt_NgayHT.setText(ngayTrongTuan + sdf_Ngay.format(thoiGianHienTai));
				thread.sleep(1000); // cho phép ngủ trong khoảng 1000 mns =1s
				// lấy thời gian hiện tại vào giờ vào

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static void themSDTKhachDDP() throws RemoteException {
		txt_SoDT.removeAllItems();
		txt_SoDT.addItem("Tất cả");
		boolean kt;
		for (KhachHang kh : dao_KhachHang.getALLKhachHang()) {
			kt = false;
			for (ChiTietHoaDon_Phong cthd_Phong : dsALL_CTHD_Phong)
				if (cthd_Phong.getPhong().getTrangThaiPhong().equals("Đang sử dụng")
						&& cthd_Phong.getHoaDon().getKhachHang().getMaKhachHang().equals(kh.getMaKhachHang()))
					kt = true;
			if (kt)
				txt_SoDT.addItem(kh.getSoDienThoai());
		}

	}

	static void docDLVaoBang() {
		xoaTrang();
		// TH1: Danh sách rỗng
		if (dsDLHienTai == null || dsDLHienTai.size() == 0) {
			JLabel txt_Rong = new JLabel("Không có phòng nào cả ", JLabel.CENTER);
			txt_Rong.setFont(new Font("Tahoma", Font.ITALIC, 30));
			txt_MaPhong.setText("");
			pan_duLieuPhong.add(txt_Rong);
			return;
		}
		// TH2: Danh sách có phần tử
		butt_danhSachPhong = new JPanel[dsDLHienTai.size()];
		int dem = 0;
		// tạo nút
		for (Phong phong : dsDLHienTai) {
			String maPhong = phong.getMaPhong();
			butt_danhSachPhong[dem] = new JPanel();
			butt_danhSachPhong[dem].setLayout(new BorderLayout());
			JLabel lblMaPhong = new JLabel(dsDLHienTai.get(dem).getMaPhong());
			lblMaPhong.setHorizontalAlignment(JLabel.CENTER);
			lblMaPhong.setFont(new Font("Arial", Font.BOLD, 20));
			butt_danhSachPhong[dem].add(lblMaPhong, BorderLayout.SOUTH);
			butt_danhSachPhong[dem].setBackground(Color.white);
			butt_danhSachPhong[dem].setBorder(BorderFactory.createLineBorder(Color.black, 1));
			butt_danhSachPhong[dem].setPreferredSize(new DimensionUIResource(210, 150));
			butt_danhSachPhong[dem].setFocusable(false);
			setIcon(dem);
			// xử lý khi nhấn 1 phòng bất kỳ
			butt_danhSachPhong[dem].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					maPhongDuocChon = maPhong;// gán là phòng được chọn để xử lý về sau
					setPhongDuocChon();
					try {
						docThongTinPhong();
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
					// thay đổi chọn phòng
					btn_ChonPhong.setText("Chọn phòng");
					btn_ChonPhong.setBackground(new Color(138, 43, 226));
					btn_ChonPhong
							.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Them.png"));
				}
			});
			dem++;
		}
		// thêm nút vào danh sách
		if (cbx_LoaiHienThi.getSelectedIndex() == 0) {
			docDLVaoBangTheoMaPhong();
		} else if (cbx_LoaiHienThi.getSelectedIndex() == 1) {
			docDLVaoBangTheoLoaiPhong();
		}
	}

	public static void docThongTinPhong() throws RemoteException {
		ArrayList<ChiTietHoaDon_Phong> dscthd_P = new ArrayList<ChiTietHoaDon_Phong>();
		dsALL_CTHD_Phong = dao_CTHD_Phong.getALLDanhSach();
		for (ChiTietHoaDon_Phong cthd_Phong : dsALL_CTHD_Phong) {
			if (cthd_Phong.getPhong().getMaPhong().equals(maPhongDuocChon))
				dscthd_P.add(cthd_Phong);
		}
		ChiTietHoaDon_Phong cthd_P = dscthd_P.get(dscthd_P.size() - 1);
		KhachHang kH = cthd_P.getHoaDon().getKhachHang();

		// hiển thị khách đang sử dụng phòng đó
		txt_SoDT.setSelectedItem(kH.getSoDienThoai());
		kiemTraSDT(kH.getSoDienThoai(), kH);
		// đọc thông tin của phòng đó
		Phong phong = cthd_P.getPhong();
		txt_MaPhong.setText("Mã phòng: " + phong.getMaPhong());
		txt_ThongTin.setText(" Tên phòng:___________" + phong.getTenPhong() + "\n" + " Vị trí phòng:__________"
				+ phong.getViTri() + "\n Loại phòng:__________" + phong.getLoaiPhong().getTenLoaiPhong() + "\n"
				+ " Giá mỗi giờ:__________" + df_Tien.format(phong.getLoaiPhong().getGia()) + " VNĐ");

		// đọc thông tin dịch vụ được thêm vào
		docBangDichVu(cthd_P.getHoaDon());
	}

	public static void docBangDichVu(HoaDon hDHienTai) {
		modelHoaDon.setRowCount(0);
		String maHD = hDHienTai.getMaHoaDon();
		// lọc theo mã hoá đơn
		ArrayList<ChiTietHoaDon_DichVu> dsDichVu = new ArrayList<ChiTietHoaDon_DichVu>();
		for (ChiTietHoaDon_DichVu ct : dsALL_CTHD_DV) {
			if (ct.getHoaDon().getMaHoaDon().equals(hDHienTai.getMaHoaDon()))
				dsDichVu.add(ct);
		}
		// Thay thế bằng vòng for đỡ lag
//		ArrayList<ChiTietHoaDon_DichVu> dsDichVu = dao_CTHD_DV.getDSTheoMaHD(maHD);
		int i = 1;
		long sum = 0;
		for (ChiTietHoaDon_DichVu ct : dsDichVu) {
			long thanhTien = (long) (ct.getDichVu().getGia() * ct.getSoLuong());
			sum = sum + thanhTien;
			String row[] = { i++ + "", ct.getDichVu().getTenDV(), df_Tien.format(ct.getDichVu().getGia()) + " VNĐ",
					ct.getSoLuong() + "", df_Tien.format(thanhTien) + " VNĐ" };
			modelHoaDon.addRow(row);
		}
	}

	private static void docDLVaoBangTheoMaPhong() {
		// Thêm từng phần tử vào danh sách
		int i = 1;
		for (; i < butt_danhSachPhong.length + 1; i++) {
			pan_duLieuPhong.add(butt_danhSachPhong[i - 1]);
		}
		pan_duLieuPhong.setPreferredSize(new Dimension(900, (i / 4 + 1) * 175));
	}

	private static void docDLVaoBangTheoLoaiPhong() {
		// Thêm các phòng thường
		Panel lbl_PThuong1 = new Panel();
		lbl_PThuong1.setBackground(Color.black);
		lbl_PThuong1.setPreferredSize(new Dimension(1250, 5));
		JLabel txt_Thuong = new JLabel("", JLabel.LEFT);
		txt_Thuong.setBackground(color1);
		txt_Thuong.setFont(new Font("Tahoma", Font.BOLD, 30));
		pan_duLieuPhong.add(txt_Thuong);
		pan_duLieuPhong.add(lbl_PThuong1);
		int dem = 0;
		for (int i = 1; i < butt_danhSachPhong.length + 1; i++) {
			if (dsDLHienTai.get(i - 1).getLoaiPhong().getMaLoaiPhong().equals("LP002")) {
				pan_duLieuPhong.add(butt_danhSachPhong[i - 1]);
				dem++;
			}
		}
		txt_Thuong.setText("Phòng thường (" + dem + ")");

		// Thêm các phòng Vip
		Panel lbl_PVip = new Panel();
		lbl_PVip.setBackground(Color.black);
		lbl_PVip.setPreferredSize(new Dimension(1500, 5));
		pan_duLieuPhong.add(lbl_PVip);
		Panel lbl_PVip2 = new Panel();
		lbl_PVip2.setPreferredSize(new Dimension(1500, 25));
		pan_duLieuPhong.add(lbl_PVip2);
		Panel lbl_PVip1 = new Panel();
		lbl_PVip1.setBackground(Color.black);
		lbl_PVip1.setPreferredSize(new Dimension(1250, 5));
		JLabel txt_Vip = new JLabel("Phòng Vip", JLabel.LEFT);
		txt_Vip.setBackground(color1);
		txt_Vip.setFont(new Font("Tahoma", Font.BOLD, 30));
		pan_duLieuPhong.add(txt_Vip);
		pan_duLieuPhong.add(lbl_PVip1);
		for (int i = 1; i < butt_danhSachPhong.length + 1; i++) {
			if (dsDLHienTai.get(i - 1).getLoaiPhong().getMaLoaiPhong().equals("LP001"))
				pan_duLieuPhong.add(butt_danhSachPhong[i - 1]);
		}
		Panel lbl_Cuoi = new Panel();
		lbl_Cuoi.setBackground(Color.black);
		lbl_Cuoi.setPreferredSize(new Dimension(1500, 5));
		txt_Vip.setText("Phòng Vip (" + (butt_danhSachPhong.length - dem) + ")");
		pan_duLieuPhong.add(lbl_Cuoi);
		pan_duLieuPhong.setPreferredSize(new Dimension(900, (butt_danhSachPhong.length / 4 + 3) * 190));

	}

	// đổi màu Border nút được bị xoá khỏi ds chọn
	public void setBorderPhong(int viTriXoa) {
		for (int i = 0; i < dsDLHienTai.size(); i++) {
			if (dsDLHienTai.get(i).getMaPhong().equals(model_dSPhongDaChon.getValueAt(viTriXoa, 1)))
				butt_danhSachPhong[i].setBorder(BorderFactory.createLineBorder(Color.black, 1));
		}
	}

	// đổi màu nút trong ds theo mã phòng được chọn
	public static void setPhongDuocChon() {
		// cập nhập nút cũ từ vị trí được chọn cũ
		if (viTriNutDuocChon != -1) {
			butt_danhSachPhong[viTriNutDuocChon].setBorder(BorderFactory.createLineBorder(Color.black, 1));
			for (int i = 0; i < model_dSPhongDaChon.getRowCount(); i++) {
				if (dsDLHienTai.get(viTriNutDuocChon).getMaPhong().equals(model_dSPhongDaChon.getValueAt(i, 1)))
					butt_danhSachPhong[viTriNutDuocChon].setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
			}
		}
		// lấy vị trí mới của phòng được chọn, bắt đầu từ phần tử đầu tiên
		viTriNutDuocChon = 0;
		for (; viTriNutDuocChon < dsDLHienTai.size(); viTriNutDuocChon++) {
			if (dsDLHienTai.get(viTriNutDuocChon).getMaPhong().equals(maPhongDuocChon)) {
				break;
			}
		}
		// thay đổi màu Border nút được chọn
		butt_danhSachPhong[viTriNutDuocChon].setBorder(BorderFactory.createLineBorder(Color.red, 10));
	}

	// set Icon
	public static void setIcon(int viTri) {
		String trangThai = dsDLHienTai.get(viTri).getTrangThaiPhong();
		if (dsDLHienTai.get(viTri).getLoaiPhong().getTenLoaiPhong().equals("Vip")) {
			iconVip = new JLabel();
			iconVip.setIcon(new ImageIcon("src/main/java/img/VIP.gif"));
			JLabel lblIconPhongVip = new JLabel(icon_PhongVip);
			lblIconPhongVip.setHorizontalAlignment(JLabel.CENTER);
			butt_danhSachPhong[viTri].add(lblIconPhongVip, BorderLayout.NORTH);
		}
		JLabel lblIconPhong = new JLabel();
		lblIconPhong.setHorizontalAlignment(JLabel.CENTER);
		if (trangThai.equals("Trống"))
			lblIconPhong.setIcon(icon_PhongTrong);
		else if (trangThai.equals("Chờ"))
			lblIconPhong.setIcon(icon_PhongCho);
		else
			lblIconPhong.setIcon(icon_PhongDSD);
		butt_danhSachPhong[viTri].add(lblIconPhong, BorderLayout.CENTER);
	}

	public static void xoaTrang() {
		crPan_DSPhong.removeAll();
		pan_duLieuPhong.removeAll();
		crPan_DSPhong.add(pan_duLieuPhong);
		maPhongDuocChon = "";
	}

	public static boolean kiemTraSDT(String soDT, KhachHang khTruyen) throws RemoteException {
		KhachHang khachHang = dao_KhachHang.getKhachHangTheoSoDT(soDT);
		// TH1: Có truyền khách hàng
		if (khTruyen != null) {
			khachHang = khTruyen;
		} else {
			khachHang = dao_KhachHang.getKhachHangTheoSoDT(soDT);
		}
		// TH2:Không truyền khách hàng
		if (khachHang != null) {
			txt_MaKH.setText(khachHang.getMaKhachHang());
			txt_TenKH.setText(khachHang.getTenKhachHang());
			return false;
		}
		txt_MaKH.setText(maKHMoi);
		txt_TenKH.setText("");
		return true;
	}

	public void capNhapTTTong() {
		int soLuongP = model_dSPhongDaChon.getRowCount();
		if (soLuongP == 0) {
			txt_ThongTinTong.setText("Chưa chọn phòng thanh toán");
			return;
		}
		long tongTien = 0;
		for (int i = 0; i < soLuongP; i++) {
			String sum = model_dSPhongDaChon.getValueAt(i, 4).toString();
			// xoá dấu phẩy trong mảng
			for (int j = 0; j < sum.length(); j++)
				if (sum.charAt(j) == ',') {
					sum = sum.substring(0, j) + sum.substring(j + 1);
				}
			tongTien = tongTien + Long.parseLong(sum);
		}
		txt_ThongTinTong.setText(
				"Số lượng phòng thanh toán: " + soLuongP + "\nTổng tiền: " + df_Tien.format(tongTien) + " VNĐ");

	}

	public void themKhachHang() throws MalformedURLException, NotBoundException, RemoteException {
		String sdt = txt_SoDT.getSelectedItem().toString().trim();
		// chuyển sang trang quản lý khách hàng
		FrmQuanLyKhachHang frmQLKH = new FrmQuanLyKhachHang(maNhanVienTao, sdt + " ", "FrmDatPhongDangSuDung");
		FrmTrangChu.jtab_NoiDung.remove(FrmTrangChu.jtab_NoiDung.getSelectedComponent());
		FrmTrangChu.jtab_NoiDung.add(frmQLKH.pnBorder);

	}

	public void openFrom(FrmQuanLyKhachHang frmQLKH) {
		Component[] components = this.getComponents();
		Component com = null;
		for (int i = 0; i < components.length; i++) {
			com = components[i];
			if (com != null)
				com.setVisible(false);
		}
		getContentPane().add(frmQLKH);
		frmQLKH.setVisible(true);
	}

	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	public boolean taoHDMoi() throws RemoteException {
		NhanVien nVTao = dao_NhanVien.getNhanVienTheoMa(maNhanVienTao);
		LocalDateTime thoiGianHienTai = LocalDateTime.now();
		String maHD = taoMaHoaDon().trim();
		// tạo hoá đơn
		HoaDon hd = new HoaDon(maHD, 0, nVTao, khachDuocChon);
		if (!dao_HoaDon.themHoaDon(hd))
			return false;
		// tạo cthd_Phong lần lượt cho các phòng được chọn
		for (int i = 0; i < model_dSPhongDaChon.getRowCount(); i++) {
			Phong phong = dao_Phong.getPhongTheoMa(model_dSPhongDaChon.getValueAt(i, 1).toString());
			ChiTietHoaDon_Phong cthd_P = new ChiTietHoaDon_Phong(hd, phong, thoiGianHienTai, null);
			if (!dao_CTHD_Phong.themCTHD_PMoi(cthd_P))
				return false;
			// cập nhập trạng thái phòng
			phong.setTrangThaiPhong("Đang sử dụng");
			if (!dao_Phong.capNhapPhong(phong))
				return false;
			model_dSPhongDaChon.removeRow(i);
		}
		docDLVaoBang();
		return true;
	}

	public static void docTTPhongThanhToan(Phong phong) {
		long tienDV = 0, tienPhong = 0;
		// lấy mã hoá đơn
		ArrayList<ChiTietHoaDon_Phong> dscthd_P = new ArrayList<ChiTietHoaDon_Phong>();
		for (ChiTietHoaDon_Phong cthd_Phong : dsALL_CTHD_Phong) {
			if (cthd_Phong.getPhong().getMaPhong().equals(maPhongDuocChon))
				dscthd_P.add(cthd_Phong);
		}
		HoaDon hDon = dscthd_P.get(dscthd_P.size() - 1).getHoaDon();

		// tính Tiền dịch vụ
		for (ChiTietHoaDon_DichVu cthd_dv : dsALL_CTHD_DV) {
			if (cthd_dv.getHoaDon().getMaHoaDon().equals(hDon.getMaHoaDon()))
				tienDV += cthd_dv.tinhTienDichVu();
		}
		// tính tiền phòng
		dscthd_P = new ArrayList<ChiTietHoaDon_Phong>();
		for (ChiTietHoaDon_Phong cthd_Phong : dsALL_CTHD_Phong) {
			if (cthd_Phong.getHoaDon().getMaHoaDon().equals(hDon.getMaHoaDon())) {
				ChiTietHoaDon_Phong ct = cthd_Phong;
				ct.setGioRa(LocalDateTime.now());
				tienPhong = tienPhong + cthd_Phong.tinhTienPhong();
				dscthd_P.add(ct);
			}
		}
		tienPhong = (long) hDon.tinhTongTienPhong(dscthd_P);
		long tongTien = tienPhong + tienDV;
		String row[] = { model_dSPhongDaChon.getRowCount() + 1 + "", phong.getMaPhong(), df_Tien.format(tienDV),
				df_Tien.format(tienPhong), df_Tien.format(tongTien) };
		model_dSPhongDaChon.addRow(row);
		dsHDThanhToan.add(hDon);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// hiện thị bảng
		if (obj.equals(cbx_LoaiHienThi)) {
			docDLVaoBang();
		}

		// Làm mới danh sách phòng
		if (obj.equals(btn_LamMoi)) {
			try {
				dsDLHienTai = dao_Phong.getDSTheoTTphong("Đang sử dụng");
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			docDLVaoBang();
			txt_SoDT.setSelectedItem("");
			txt_MaKH.setText("");
			txt_ThongTin.setText("");
			modelHoaDon.setRowCount(0);
			try {
				themSDTKhachDDP();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		// Quản lý dịch vụ của phòng đc chọn
		if (obj.equals(btn_QLDichVu)) {
			if (maPhongDuocChon.length() != 0) {
				FrmThemDichVu fr_themDV = null;
				try {
					fr_themDV = new FrmThemDichVu(maPhongDuocChon, maNhanVienTao);
				} catch (MalformedURLException ex) {
					throw new RuntimeException(ex);
				} catch (NotBoundException ex) {
					throw new RuntimeException(ex);
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				fr_themDV.setVisible(true);
				fr_themDV.setResizable(false);
				fr_themDV.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			} else {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn phòng !");
			}
		}
//		Chuyển phòng
		boolean kiemTra;
		if (obj.equals(btn_ChuyenPhong)) {
			if (maPhongDuocChon.length() != 0) {
				FrmChuyenPhong fr_ChuyenPhong = null;
				try {
					fr_ChuyenPhong = new FrmChuyenPhong(maPhongDuocChon, maNhanVienTao);
				} catch (MalformedURLException ex) {
					throw new RuntimeException(ex);
				} catch (NotBoundException ex) {
					throw new RuntimeException(ex);
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				fr_ChuyenPhong.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fr_ChuyenPhong.setVisible(true);
				fr_ChuyenPhong.setResizable(false);
			} else {
				JOptionPane.showMessageDialog(this, "Bạn chưa chọn phòng !");
			}
		}
		// lọc theo khách hàng
		if (obj.equals(btn_LocDSTheoKH)) {
			if (txt_SoDT.getSelectedItem().equals("Tất cả")) {
				try {
					dsDLHienTai = dao_Phong.getDSTheoTTphong("Đang sử dụng");
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				viTriNutDuocChon = -1;
				docDLVaoBang();
				return;
			}
			KhachHang khTim = null;
			try {
				khTim = dao_KhachHang.getKhachHangTheoSoDT(txt_SoDT.getSelectedItem().toString().trim());
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			txt_ThongTin.setText("");
			txt_MaPhong.setText("");
			modelHoaDon.setRowCount(0);
			if (khTim == null) {
				thongBaoLoi("Khách hàng chọn không hợp lệ !", "Lọc danh sách phòng theo khách hàng");
				return;
			}
//			khách hàng có hoá đơn
			else {
				dsDLHienTai.removeAll(dsDLHienTai);
				for (ChiTietHoaDon_Phong ct : dsALL_CTHD_Phong) {
					if (ct.getHoaDon().getKhachHang().getMaKhachHang().equals(khTim.getMaKhachHang())
							&& ct.getPhong().getTrangThaiPhong().equals("Đang sử dụng"))
						dsDLHienTai.add(ct.getPhong());
				}
			}
			viTriNutDuocChon = -1;
			docDLVaoBang();
		}
		// Nút chọn phòng thanh toán
		if (obj.equals(btn_ChonPhong)) {
			// Chọn phòng thanh toán
			if (btn_ChonPhong.getText().equals("Chọn phòng")) {
				if (maPhongDuocChon == "") {
					thongBaoLoi("Bạn chưa chọn phòng !", "Chọn phòng");
					return;
				}
				Phong phong = null;
				try {
					phong = dao_Phong.getPhongTheoMa(maPhongDuocChon);
					System.out.println(phong.getMaPhong());
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				int slPhong = model_dSPhongDaChon.getRowCount();
				kiemTra = false;
				for (int j = 0; j < slPhong; j++) {
					if (model_dSPhongDaChon.getValueAt(j, 1).equals(maPhongDuocChon)) {
						kiemTra = true;
						break;
					}
				}
				if (!kiemTra) {
					docTTPhongThanhToan(phong);
					capNhapTTTong();
				} else {
					thongBaoLoi("Phòng đã có trong 'Danh sách đã chọn'!", "Chọn phòng vào danh sách");
				}
			}
			// Xoá phòng khỏi danh sách
			if (btn_ChonPhong.getText().equals("Bỏ chọn phòng")) {
				int hangDuocChon = tb_dsPhongDaChon.getSelectedRow();
				if (hangDuocChon == -1) {
					thongBaoLoi("Bạn chưa chọn phòng trong 'Danh sách phòng đã chọn thanh toán'!", "Bỏ chọn phòng");
				} else {
					// Xoá phòng khỏi danh sách
					setBorderPhong(hangDuocChon);
					txt_ThongTin.setText("");
					txt_MaPhong.setText("");
					model_dSPhongDaChon.removeRow(hangDuocChon);
					// xoá hoá đơn thanh toán trong ds ẩn
					dsHDThanhToan.remove(hangDuocChon);
					// cập nhập vị trí stt trong bảng
					for (int i = 0; i < tb_dsPhongDaChon.getRowCount(); i++) {
						model_dSPhongDaChon.setValueAt(i + 1, i, 0);
					}
					capNhapTTTong();
				}
			}
		}

		if (obj.equals(btn_ThanhToan)) {
			if (model_dSPhongDaChon.getRowCount() <= 0) {
				thongBaoLoi("Bạn chưa chọn phòng thanh toán", "Thanh toán");
				return;
			}
			NhanVien nhanVien = null;
			try {
				nhanVien = dao_NhanVien.getNhanVienTheoMa(maNhanVienTao);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			FrmXuLyThanhToan frmThanhToan = null;
			try {
				frmThanhToan = new FrmXuLyThanhToan(dsHDThanhToan, nhanVien);
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			frmThanhToan.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frmThanhToan.setVisible(true);
			frmThanhToan.setResizable(false);
		}
	}

	public void locDSPhongTheoKhach(KhachHang khTim) throws RemoteException {
		// Ta sẽ duyệt tuần tự danh sách phòng hiện tại
		// Trong mỗi lần duyệt thì ta sẽ kiểm tra xem thử có đúng với khách hàng truyền
		// vào không, đúng thì ta giữ lại phòng đó để hiển thị
		ArrayList<ChiTietHoaDon_Phong> dsCTHD_Phong = dao_CTHD_Phong.getALLDanhSach();
		for (int i = 0; i < dsDLHienTai.size(); i++) {
			for (int j = 0; j < dsCTHD_Phong.size(); j++) {
				if (dsCTHD_Phong.get(j).getPhong().getMaPhong().equals(dsDLHienTai.get(i).getMaPhong()))
					if (!dsCTHD_Phong.get(j).getHoaDon().getKhachHang().equals(khTim.getMaKhachHang())) {
						dsDLHienTai.remove(dsCTHD_Phong.get(j).getPhong());
					}
			}
		}
	}

	public PhieuDatPhong layPhieuDatPhongCuoi() throws RemoteException {
		ArrayList<PhieuDatPhong> dsphieuDat = dao_PhieuDatPhong.getTheoMaPhong_TrangThai(maPhongDuocChon, "Đang chờ");
		if (dsphieuDat.size() == 0)
			return null;
		return dsphieuDat.get(0);// lấy phiếu đặt phòng đầu tiên;
	}

	public String taoMaHoaDon() throws RemoteException {
		// tạo 4 số cuối
		int dem = 0;
		ArrayList<HoaDon> dsHD = dao_HoaDon.getALLHoaDon();
		ArrayList<Integer> dsSo = new ArrayList<Integer>();
		for (int i = 0; i < dsHD.size(); i++) {
			int bonSoCuoi = Integer.parseInt(dsHD.get(dem).getMaHoaDon().toString().trim().substring(2));
			dsSo.add(bonSoCuoi);
		}
		for (int i = 0; i < dsSo.size(); i++) {
			if (!dsSo.contains(dsSo))
				dem = i + 1;
		}
		return String.format("HD%04d", dem);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
