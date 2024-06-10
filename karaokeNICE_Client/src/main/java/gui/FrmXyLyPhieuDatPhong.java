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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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

import com.toedter.calendar.JDateChooser;
//
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
import entity.PhieuDatPhong;
import entity.Phong;

public class FrmXyLyPhieuDatPhong extends JFrame implements Runnable, ActionListener, MouseListener {
	private static final String URL = "rmi://26.173.247.75:7878/";

	DaoPhong dao_Phong = (DaoPhong) Naming.lookup(URL + "daoPhong");
	DaoHoaDon dao_HoaDon = (DaoHoaDon) Naming.lookup(URL + "hoaDon");
	DaoPhieuDatPhong dao_PhieuDatPhong = (DaoPhieuDatPhong) Naming.lookup(URL + "daoPhieuDatPhong");
	DaoChiTietHoaDon_Phong dao_CTHD_Phong = (DaoChiTietHoaDon_Phong) Naming.lookup(URL + "daoChiTietHoaDon_phong");
	DaoNhanVien dao_NhanVien = (DaoNhanVien) Naming.lookup(URL + "daoNhanVien");
	DaoKhachHang dao_KhachHang = (DaoKhachHang) Naming.lookup(URL + "daoKhachHang");

//	private DAO_Phong dao_Phong = new DAO_Phong();
//	private DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
//	private DAO_PhieuDatPhong dao_PhieuDatPhong = new DAO_PhieuDatPhong();
//	private DAO_ChiTietHoaDon_Phong dao_CTHD_Phong = new DAO_ChiTietHoaDon_Phong();
//	private DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
//	private DAO_KhachHang dao_KhachHang = new DAO_KhachHang();
	public static JPanel pnBorder;
	private JLabel txt_GioHT;
	private Thread thread = null;
	private JLabel txt_NgayHT;
	private int viTriNutDuocChon = -1;

	// tạo danh sách phòng
	public int button_Height = 70;

	Color color1 = new Color(138, 43, 226); // BlueViolet
	Color color2 = new Color(233, 221, 244);
	private ImageIcon icon_PhongTrong = new ImageIcon("src/main/java/img/room-mini-3.png");
	private ImageIcon icon_PhongCho = new ImageIcon("src/main/java/img/room-mini-2.png");
	private ImageIcon icon_PhongDSD = new ImageIcon("src/main/java/img/room-mini-1.png");
	private ImageIcon icon_PhongVip = new ImageIcon("src/main/java/img/vip-Mini.gif");
	private JPanel[] butt_danhSachPhong;
	private ArrayList<Phong> dsDLHienTai;
	private String maPhongDuocChon = "";
	private String maNhanVienTao = "";
	private JLabel iconVip;
	int kichThuocNutChucNang = 70;
	int viTriBanDauNutChucNang = 20;
	int khoangCachNutChucNang = 90;

	DateTimeFormatter forGioNgay = DateTimeFormatter.ofPattern("HH:mm:ss  dd/MM/yyyy");
	SimpleDateFormat df_Gio = new SimpleDateFormat("HH:mm:ss");
	SimpleDateFormat df_Ngay = new SimpleDateFormat("dd/MM/yyyy");
	DecimalFormat df_Tien = new DecimalFormat("###,###,###");
	private JComboBox cbx_LoaiHienThi;
	private JPanel panel_ThoiGian_1_2;
	private JLabel lbl_ThoiGianDat_5;
	private JLabel lbl_ThoiGianDat_6;
	private JLabel lbl_ThoiGianDat_7;
	private JLabel lbl_ThoiGianDat_8;
	private JLabel lbl_SoDT;
	private JComboBox<Object> txt_SoDT;
	private JLabel lbl_MaKH;
	private JLabel lbl_TenKH;
	private JTextField txt_TenKH;
	private JLabel lbl_TieuDe_Trai;
	private JPanel panel_Trai_1;
	private JLabel lbl_TieuDe_Trai_1;
	private JPanel panel_AnhNen;
	private JTextArea txt_ThongTin;
	private JLabel txt_MaPhong;
	private JPanel pn_dsPhieuDatPhong;
	private JScrollPane jSc_dsPhieuDatPhong;
	private KhachHang khachDuocChon;
	private JTable tb_dsPhieuDatPhong;
	private DefaultTableModel model_dSPhongDaChon;
	private JPanel panel_Trai_2;
	private JTextArea txt_ThongTinNhan;
	private JPanel panel_Phai;
	private JButton btn_HuyPhieu;
	private JButton btn_NhanPhong;
	private JTextField txt_Gio;
	private JTextField txt_Phut;
	private JTextField txt_MaKH;
	private JDateChooser time_VaoPhong;
	private JDateChooser time_RaDuKien;
	private JRadioButton rad_ChonHienTai;
	private JButton btn_LocDSTheoTG;
	private JButton btn_LocDSTheoKH;
	private JTextField txt_DiemTL;
	private ArrayList<PhieuDatPhong> dsAllPhieuDat = dao_PhieuDatPhong.getDSTheoTrangThai("Đang chờ");
	private JScrollPane jScrollPane_KH;
	private Panel pan_duLieuPhong = new Panel();
	private ScrollPane crPan_DSPhong;
	private JTextField txt_GioVao;
	private JTextField txt_GioRa;
	private JLabel lbl_GioVao;
	private JLabel lbl_GioRa;
	private JTextField textField;
	private JTextField txt_ThongBao;
	protected String hanhDong = "";

	Color color_DenGio = new Color(255, 167, 178);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmXyLyPhieuDatPhong frame = new FrmXyLyPhieuDatPhong("NV001", "");
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
	public FrmXyLyPhieuDatPhong(String nhanVien, String soDTTruyen) throws MalformedURLException, NotBoundException, RemoteException {
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
		panel_Menu.setBounds(1277, 610, 637, 280); // Mới chỉnh
		pnBorder.add(panel_Menu);

		JPanel panel_Trai = new JPanel();
		panel_Trai.setLayout(null);
		panel_Trai.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_Trai.setBackground(color_Xanh);
		panel_Trai.setBounds(10, 11, 310, 474);
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

		JPanel panel_ThoiGian_1 = new JPanel();
		panel_ThoiGian_1.setLayout(null);
		panel_ThoiGian_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"T\u00ECm ho\u00E1 \u0111\u01A1n trong kho\u1EA3ng th\u1EDDi gian", TitledBorder.LEFT, TitledBorder.TOP,
				null, new Color(255, 255, 255)));
		panel_ThoiGian_1.setBackground(new Color(60, 182, 192));
		panel_ThoiGian_1.setBounds(10, 95, 290, 365);
		panel_Trai.add(panel_ThoiGian_1);

		time_VaoPhong = new JDateChooser();
		time_VaoPhong.getCalendarButton()
				.setIcon(new ImageIcon("src/main/java/img/icons8-calendar-50.png"));
		time_VaoPhong.getCalendarButton().setFont(new Font("UTM Aurora", Font.BOLD, 100));
		time_VaoPhong.setLocale(new Locale("vi", "VN"));
		time_VaoPhong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		time_VaoPhong.setDateFormatString("HH:mm dd-MM-yyyy");
		time_VaoPhong.setBounds(11, 54, 267, 48);
		panel_ThoiGian_1.add(time_VaoPhong);

		JLabel lbl_GioDat = new JLabel("Bắt đầu:");
		lbl_GioDat.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_GioDat.setBounds(23, 11, 249, 48);
		panel_ThoiGian_1.add(lbl_GioDat);

		JLabel lbl_ThoiGianDat = new JLabel("Thời gian sử dụng:");
		lbl_ThoiGianDat.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat.setBounds(26, 211, 170, 48);
		panel_ThoiGian_1.add(lbl_ThoiGianDat);

		txt_Gio = new JTextField();
		txt_Gio.setText("0");
		txt_Gio.setHorizontalAlignment(SwingConstants.CENTER);
		txt_Gio.setFont(new Font("Dialog", Font.PLAIN, 20));
		txt_Gio.setColumns(10);
		txt_Gio.setBounds(26, 250, 49, 41);
		panel_ThoiGian_1.add(txt_Gio);
		txt_Gio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE)
					e.consume();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (!txt_Gio.getText().equals("")) {
					int soGioNhap = Integer.parseInt(txt_Gio.getText().trim());
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!txt_Gio.getText().equals("") && time_RaDuKien.getDate() != null) {
					int soGioNhap = Integer.parseInt(txt_Gio.getText().trim());
					LocalDateTime timeVao = time_VaoPhong.getDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDateTime();
					int soPhut = soGioNhap * 60 + Integer.parseInt(txt_Phut.getText());
					LocalDateTime timeRa = timeVao.plusMinutes(soPhut);
					Date timeRaDate = Date.from(timeRa.atZone(ZoneId.systemDefault()).toInstant());
					time_RaDuKien.setDate(timeRaDate);
				}
			}
		});

		txt_Phut = new JTextField();
		txt_Phut.setText("0");
		txt_Phut.setHorizontalAlignment(SwingConstants.CENTER);
		txt_Phut.setFont(new Font("Dialog", Font.PLAIN, 20));
		txt_Phut.setColumns(10);
		txt_Phut.setBounds(147, 250, 49, 41);
		panel_ThoiGian_1.add(txt_Phut);
		txt_Phut.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE)
					e.consume();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (!txt_Phut.getText().equals("0")) {
					int soPhutNhap = Integer.parseInt(txt_Gio.getText().trim());
					if (soPhutNhap > 59 || soPhutNhap < 0) {
						thongBaoLoi("Phút nhập phải từ 0-59", "Lỗi nhập thời gian");
						txt_Phut.setText("0");
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!txt_Phut.getText().equals("0") && !txt_Phut.getText().equals("")) {
					int soGioNhap = Integer.parseInt(txt_Gio.getText().trim());
					int soPhutNhap = Integer.parseInt(txt_Phut.getText().trim());
					int soPhut = soGioNhap * 60 + soPhutNhap;
					LocalDateTime timeVao = time_VaoPhong.getDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDateTime();
					LocalDateTime timeRa = timeVao.plusMinutes(soPhut);
					Date timeRaDate = Date.from(timeRa.atZone(ZoneId.systemDefault()).toInstant());
					time_RaDuKien.setDate(timeRaDate);
				}
			}
		});

		btn_LocDSTheoTG = new JButton("Lọc danh sách");
		btn_LocDSTheoTG.setIcon(new ImageIcon("src/main/java/img/Search-icon.png"));
		btn_LocDSTheoTG.setFont(new Font("Dialog", Font.PLAIN, 20));
		btn_LocDSTheoTG.setFocusable(false);
		btn_LocDSTheoTG.setBounds(23, 309, 249, 41);
		panel_ThoiGian_1.add(btn_LocDSTheoTG);

		JLabel lbl_ThoiGianDat_1 = new JLabel("giờ");
		lbl_ThoiGianDat_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_1.setBounds(85, 250, 49, 41);
		panel_ThoiGian_1.add(lbl_ThoiGianDat_1);

		JLabel lbl_ThoiGianDat_2 = new JLabel("phút");
		lbl_ThoiGianDat_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_2.setBounds(206, 246, 65, 48);
		panel_ThoiGian_1.add(lbl_ThoiGianDat_2);

		JLabel lbl_GioDat_1 = new JLabel("Kết thúc:");
		lbl_GioDat_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_GioDat_1.setBounds(23, 128, 217, 41);
		panel_ThoiGian_1.add(lbl_GioDat_1);

		time_RaDuKien = new JDateChooser();
		time_RaDuKien.getCalendarButton()
				.setIcon(new ImageIcon("src/main/java/img/icons8-calendar-50.png"));
		time_RaDuKien.getCalendarButton().setFont(new Font("UTM Aurora", Font.BOLD, 100));
		time_RaDuKien.setLocale(new Locale("vi", "VN"));
		time_RaDuKien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		time_RaDuKien.setDateFormatString("HH:mm dd-MM-yyyy");
		time_RaDuKien.setBounds(11, 164, 267, 48);
		panel_ThoiGian_1.add(time_RaDuKien);
		time_RaDuKien.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				if (time_VaoPhong.getDate() != null && time_RaDuKien.getDate() != null) {
					LocalDateTime timeVao = time_VaoPhong.getDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDateTime();
					LocalDateTime timeRa = time_RaDuKien.getDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDateTime();
					long soPhutLech = ChronoUnit.MINUTES.between(timeVao, timeRa);
					if (soPhutLech > 0) {
						int soGio = (int) (soPhutLech / 60);
						int soPhut = (int) (soPhutLech % 60);
						txt_Gio.setText(soGio + "");
						txt_Phut.setText(soPhut + "");
					} else if (soPhutLech < 0) {
//						thongBaoLoi("Giờ ra phải sau giờ vào", "Nhập thời gian vào");
						time_RaDuKien.setCalendar(null);
						txt_Gio.setText("0");
						txt_Phut.setText("0");
					}
				}
			}
		});

		rad_ChonHienTai = new JRadioButton("Lấy thời gian hiện tại");
		rad_ChonHienTai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		rad_ChonHienTai.setFocusable(false);
		rad_ChonHienTai.setBackground(new Color(60, 182, 192));
		rad_ChonHienTai.setBounds(99, 104, 185, 36);
		panel_ThoiGian_1.add(rad_ChonHienTai);

		model_dSPhongDaChon = new DefaultTableModel();
		model_dSPhongDaChon.addColumn("STT");
		model_dSPhongDaChon.addColumn("Giờ vào phòng");
		model_dSPhongDaChon.addColumn("Số giờ đặt");
		model_dSPhongDaChon.addColumn("Tên khách");

		panel_Phai = new JPanel();
		panel_Phai.setLayout(null);
		panel_Phai.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_Phai.setBackground(color_Xanh);
		panel_Phai.setBounds(329, 11, 934, 302);
		pnBorder.add(panel_Phai);

		txt_SoDT = new JComboBox<Object>();
		txt_SoDT.setEditable(true);
		txt_SoDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		// txt_SoDT.setColumns(10);
		txt_SoDT.setBounds(171, 64, 257, 37);
		panel_Phai.add(txt_SoDT);
		// tự động kiểm tra
		AutoCompleteDecorator.decorate(txt_SoDT);
		txt_SoDT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txt_SoDT.getSelectedItem() != null) {
					String soDT = txt_SoDT.getSelectedItem().toString().trim();
					try {
						kiemTraSDT(soDT);
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
					hienThiGioPhieu(false);
				}
			}
		});
		lbl_SoDT = new JLabel("SĐT khách: ");
		lbl_SoDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_SoDT.setBounds(23, 56, 166, 48);
		panel_Phai.add(lbl_SoDT);

		lbl_MaKH = new JLabel("Mã khách hàng: ");
		lbl_MaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_MaKH.setBounds(23, 117, 166, 48);
		panel_Phai.add(lbl_MaKH);

		lbl_TenKH = new JLabel("Tên khách hàng: ");
		lbl_TenKH.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_TenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_TenKH.setBounds(387, 125, 166, 48);
		panel_Phai.add(lbl_TenKH);

		txt_TenKH = new JTextField();
		txt_TenKH.setBackground(Color.WHITE);
		txt_TenKH.setEditable(false);
		txt_TenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_TenKH.setColumns(10);
		txt_TenKH.setBounds(561, 125, 303, 48);
		panel_Phai.add(txt_TenKH);

		lbl_TieuDe_Trai = new JLabel("Thông tin khách tạo phiếu");
		lbl_TieuDe_Trai.setBounds(0, 11, 934, 48);
		panel_Phai.add(lbl_TieuDe_Trai);
		lbl_TieuDe_Trai.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TieuDe_Trai.setForeground(Color.WHITE);
		lbl_TieuDe_Trai.setFont(new Font("Tahoma", Font.BOLD, 30));

		btn_LocDSTheoKH = new JButton("Lọc danh sách theo khách");
		btn_LocDSTheoKH.setIcon(new ImageIcon("src/main/java/img/Search-icon.png"));
		btn_LocDSTheoKH.setForeground(Color.WHITE);
		btn_LocDSTheoKH.setFont(new Font("Dialog", Font.BOLD, 20));
		btn_LocDSTheoKH.setFocusable(false);
		btn_LocDSTheoKH.setBackground(new Color(138, 43, 226));
		btn_LocDSTheoKH.setBounds(510, 62, 354, 41);
		panel_Phai.add(btn_LocDSTheoKH);

		txt_MaKH = new JTextField();
		txt_MaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_MaKH.setEditable(false);
		txt_MaKH.setColumns(10);
		txt_MaKH.setBackground(Color.WHITE);
		txt_MaKH.setBounds(171, 119, 112, 37);
		panel_Phai.add(txt_MaKH);

		txt_DiemTL = new JTextField();
		txt_DiemTL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_DiemTL.setEditable(false);
		txt_DiemTL.setColumns(10);
		txt_DiemTL.setBackground(Color.WHITE);
		txt_DiemTL.setBounds(171, 178, 112, 42);
		panel_Phai.add(txt_DiemTL);

		JLabel lbl_Diem = new JLabel("Điểm tích luỹ:");
		lbl_Diem.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Diem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_Diem.setBounds(23, 176, 166, 48);
		panel_Phai.add(lbl_Diem);

		lbl_GioVao = new JLabel("Giờ vào:");
		lbl_GioVao.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_GioVao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_GioVao.setBounds(23, 237, 162, 48);
		panel_Phai.add(lbl_GioVao);

		txt_GioVao = new JTextField();
		txt_GioVao.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_GioVao.setEditable(false);
		txt_GioVao.setColumns(10);
		txt_GioVao.setBackground(Color.WHITE);
		txt_GioVao.setBounds(171, 241, 257, 42);
		panel_Phai.add(txt_GioVao);

		lbl_GioRa = new JLabel("Giờ ra:");
		lbl_GioRa.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_GioRa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_GioRa.setBounds(480, 241, 162, 48);
		panel_Phai.add(lbl_GioRa);

		txt_GioRa = new JTextField();
		txt_GioRa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_GioRa.setEditable(false);
		txt_GioRa.setColumns(10);
		txt_GioRa.setBackground(Color.WHITE);
		txt_GioRa.setBounds(561, 239, 257, 42);
		panel_Phai.add(txt_GioRa);

		panel_Trai_2 = new JPanel();
		panel_Trai_2.setLayout(null);
		panel_Trai_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,

				TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_Trai_2.setBackground(new Color(60, 182, 192));
		panel_Trai_2.setBounds(10, 496, 310, 392);
		pnBorder.add(panel_Trai_2);

		panel_ThoiGian_1_2 = new JPanel();
		panel_ThoiGian_1_2.setBounds(10, 94, 290, 287);
		panel_Trai_2.add(panel_ThoiGian_1_2);
		panel_ThoiGian_1_2.setLayout(null);
		panel_ThoiGian_1_2.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Mô tả danh sách phòng có phiếu đặt", TitledBorder.LEFT, TitledBorder.TOP, null, Color.WHITE));
		panel_ThoiGian_1_2.setBackground(color_Xanh);

		lbl_ThoiGianDat_5 = new JLabel("Phòng chờ:");
		lbl_ThoiGianDat_5.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ThoiGianDat_5.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_5.setBounds(10, 24, 112, 48);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_5);

		lbl_ThoiGianDat_6 = new JLabel();
		lbl_ThoiGianDat_6.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_6.setBounds(209, 11, 70, 64);
		lbl_ThoiGianDat_6.setIcon(icon_PhongCho);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_6);

		lbl_ThoiGianDat_7 = new JLabel("Phòng đang sử dụng:");
		lbl_ThoiGianDat_7.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_ThoiGianDat_7.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_7.setBounds(10, 96, 187, 48);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_7);

		lbl_ThoiGianDat_8 = new JLabel();
		lbl_ThoiGianDat_8.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_8.setBounds(209, 83, 70, 64);
		lbl_ThoiGianDat_8.setIcon(icon_PhongDSD);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_8);

		JLabel lbl_ThoiGianDat_7_1 = new JLabel("Phòng VIP:");
		lbl_ThoiGianDat_7_1.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_ThoiGianDat_7_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_7_1.setBounds(10, 155, 139, 48);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_7_1);

		JLabel lbl_ThoiGianDat_8_1 = new JLabel();
		lbl_ThoiGianDat_8_1.setIcon(new ImageIcon("src/main/java/img/vip-Mini.gif"));
		lbl_ThoiGianDat_8_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_8_1.setBounds(209, 141, 70, 64);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_8_1);

		JLabel lbl_ThoiGianDat_7_1_1 = new JLabel("Phòng đến giờ nhận:");
		lbl_ThoiGianDat_7_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_ThoiGianDat_7_1_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_7_1_1.setBounds(10, 214, 200, 48);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_7_1_1);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(color_DenGio);
		textField.setBounds(209, 204, 70, 58);
		panel_ThoiGian_1_2.add(textField);

		JPanel panel_ThoiGian_1_1 = new JPanel();
		panel_ThoiGian_1_1.setBounds(10, 11, 290, 72);
		panel_Trai_2.add(panel_ThoiGian_1_1);
		panel_ThoiGian_1_1.setLayout(null);
		panel_ThoiGian_1_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Chế độ xem danh sách phòng", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_ThoiGian_1_1.setBackground(color_Xanh);

		cbx_LoaiHienThi = new JComboBox();
		cbx_LoaiHienThi.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		cbx_LoaiHienThi.setBackground(new Color(204, 204, 204));
		cbx_LoaiHienThi.setBounds(40, 18, 210, 43);
		cbx_LoaiHienThi.addItem("Mã phòng");
		cbx_LoaiHienThi.addItem("Trạng thái");
		cbx_LoaiHienThi.addItem("Loại phòng");
		panel_ThoiGian_1_1.add(cbx_LoaiHienThi);
		cbx_LoaiHienThi.addActionListener(this);

		txt_ThongTinNhan = new JTextArea();
		txt_ThongTinNhan.setForeground(Color.BLACK);
		txt_ThongTinNhan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_ThongTinNhan.setEditable(false);
		txt_ThongTinNhan.setBackground(Color.WHITE);
		txt_ThongTinNhan.setBounds(78, 11, 481, 119);
		panel_Menu.add(txt_ThongTinNhan);

		btn_NhanPhong = new JButton("Nhận phòng");
		btn_NhanPhong.setForeground(Color.WHITE);
		btn_NhanPhong.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_NhanPhong.setFocusable(false);
		btn_NhanPhong.setBackground(Color.BLACK);
		btn_NhanPhong.setBounds(203, 169, 260, 70);
		panel_Menu.add(btn_NhanPhong);

		panel_Trai_1 = new JPanel();
		panel_Trai_1.setBounds(1277, 11, 637, 588);
		pnBorder.add(panel_Trai_1);
		panel_Trai_1.setLayout(null);
		panel_Trai_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_Trai_1.setBackground(color_Xanh);

		panel_AnhNen = new JPanel();
		panel_AnhNen.setLayout(null);
		panel_AnhNen.setBackground(Color.WHITE);
		panel_AnhNen.setBounds(10, 57, 617, 223);
		panel_Trai_1.add(panel_AnhNen);

		txt_ThongTin = new JTextArea();
		txt_ThongTin.setForeground(Color.BLACK);
		txt_ThongTin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_ThongTin.setEditable(false);
		txt_ThongTin.setBackground(Color.WHITE);
		txt_ThongTin.setBounds(98, 58, 481, 154);
		panel_AnhNen.add(txt_ThongTin);

		txt_MaPhong = new JLabel("");
		txt_MaPhong.setHorizontalAlignment(SwingConstants.CENTER);
		txt_MaPhong.setForeground(Color.GRAY);
		txt_MaPhong.setFont(new Font("Tahoma", Font.BOLD, 30));
		txt_MaPhong.setBounds(0, 0, 617, 59);
		panel_AnhNen.add(txt_MaPhong);

		pn_dsPhieuDatPhong = new JPanel();
		pn_dsPhieuDatPhong.setLayout(null);
		pn_dsPhieuDatPhong.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Danh sách phiếu đã tạo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		pn_dsPhieuDatPhong.setBackground(new Color(149, 214, 224));
		pn_dsPhieuDatPhong.setBounds(10, 291, 617, 230);
		panel_Trai_1.add(pn_dsPhieuDatPhong);

		jSc_dsPhieuDatPhong = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jSc_dsPhieuDatPhong.setBounds(6, 16, 601, 203);
		pn_dsPhieuDatPhong.add(jSc_dsPhieuDatPhong);
		tb_dsPhieuDatPhong = new JTable(model_dSPhongDaChon);
		tb_dsPhieuDatPhong.setDefaultEditor(Object.class, null);
		tb_dsPhieuDatPhong.getColumnModel().getColumn(0).setPreferredWidth(5);
		jSc_dsPhieuDatPhong.setViewportView(tb_dsPhieuDatPhong);

		lbl_TieuDe_Trai_1 = new JLabel("Thông tin phòng");
		lbl_TieuDe_Trai_1.setBounds(2, 0, 633, 58);
		panel_Trai_1.add(lbl_TieuDe_Trai_1);
		lbl_TieuDe_Trai_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TieuDe_Trai_1.setForeground(Color.WHITE);
		lbl_TieuDe_Trai_1.setFont(new Font("Tahoma", Font.BOLD, 30));

		btn_HuyPhieu = new JButton("Huỷ phiếu đặt phòng");
		btn_HuyPhieu.setIcon(new ImageIcon("src/main/java/img/Close-2-icon.png"));
		btn_HuyPhieu.setForeground(Color.WHITE);
		btn_HuyPhieu.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_HuyPhieu.setFocusable(false);
		btn_HuyPhieu.setBackground(new Color(138, 43, 226));
		btn_HuyPhieu.setBounds(161, 532, 314, 45);
		panel_Trai_1.add(btn_HuyPhieu);

		JPanel panel_DSDatPhong = new JPanel();
		panel_DSDatPhong.setLayout(null);
		panel_DSDatPhong.setBackground(Color.WHITE);
		panel_DSDatPhong.setBounds(330, 319, 934, 572);
		pnBorder.add(panel_DSDatPhong);

		// tạo khung bảng danh sách phòng
		crPan_DSPhong = new ScrollPane();
		pan_duLieuPhong.setBackground(Color.WHITE);
		pan_duLieuPhong.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 20));
		crPan_DSPhong.add(pan_duLieuPhong);

		crPan_DSPhong.setBackground(Color.white);
		jScrollPane_KH = new JScrollPane(crPan_DSPhong, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane_KH.setBounds(0, 52, 934, 520); // Mới chỉnh
		panel_DSDatPhong.add(jScrollPane_KH);
		jScrollPane_KH.setAutoscrolls(true);

		JPanel panel_TieuDe = new JPanel();
		panel_TieuDe.setLayout(null);
		panel_TieuDe.setBackground(new Color(60, 182, 192));
		panel_TieuDe.setBounds(0, 0, 934, 56);
		panel_DSDatPhong.add(panel_TieuDe);

		JLabel lbl_TieuDe_1 = new JLabel("Danh sách phòng có phiếu đặt");
		lbl_TieuDe_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TieuDe_1.setForeground(Color.BLACK);
		lbl_TieuDe_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbl_TieuDe_1.setBounds(0, 5, 933, 45);
		panel_TieuDe.add(lbl_TieuDe_1);

		txt_ThongBao = new JTextField("Nhận phòng gần đây nhất");
		txt_ThongBao.setHorizontalAlignment(SwingConstants.CENTER);
		txt_ThongBao.setForeground(Color.RED);
		txt_ThongBao.setFont(new Font("Tahoma", Font.ITALIC, 15));
		txt_ThongBao.setEditable(false);
		txt_ThongBao.setColumns(10);
		txt_ThongBao.setBackground(Color.WHITE);
		txt_ThongBao.setBounds(10, 901, 1904, 31);
		pnBorder.add(txt_ThongBao);
		txt_ThongBao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (hanhDong.equals(""))
					thongBao("Không có nhận phòng nào gần đây", "Thông báo");
				else {
					if (hanhDong.equals("Nhận phòng")) {
						int hoiLai = JOptionPane.showConfirmDialog(null,
								"Xem phòng đã đặt của khách " + khachDuocChon.getTenKhachHang(), "Chuyển trang",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (hoiLai == JOptionPane.YES_OPTION) {
							// chuyển sang trang phòng đang sử dụng
							FrmXuLyPhongDangSuDung frm = null;
							try {
								frm = new FrmXuLyPhongDangSuDung(maNhanVienTao,
										khachDuocChon.getSoDienThoai());
							} catch (MalformedURLException ex) {
								throw new RuntimeException(ex);
							} catch (NotBoundException ex) {
								throw new RuntimeException(ex);
							} catch (RemoteException ex) {
								throw new RuntimeException(ex);
							}
							FrmTrangChu.jtab_NoiDung.remove(FrmTrangChu.jtab_NoiDung.getSelectedComponent());
							FrmTrangChu.jtab_NoiDung.add(frm.pnBorder);
						}
					}
				}
			}
		});

		// ---xử lý sự kiện---
		// Tạo luồng nhỏ chạy song song với chương trình.
		thread = new Thread(this);
		thread.start();
		// lấy tất cả danh sách dữ liệu
		dsDLHienTai = dao_Phong.getDSCoPhieuDat();
		docDLVaoBang();
		docSDTKhachCoPhieu();
		hienThiGioPhieu(false);
		// xử lý các nút
		maNhanVienTao = nhanVien;
		tb_dsPhieuDatPhong.addMouseListener(this);
		btn_HuyPhieu.addActionListener(this);
		rad_ChonHienTai.addActionListener(this);
		btn_LocDSTheoTG.addActionListener(this);
		btn_LocDSTheoKH.addActionListener(this);
		btn_NhanPhong.addActionListener(this);
		txt_ThongBao.addActionListener(this);
		// xử lý khi có truyền số điện thoại
		if (!soDTTruyen.equals("")) {
			txt_SoDT.setSelectedItem(soDTTruyen);
			dsDLHienTai = getDSPhongTheoKH();
			docDLVaoBang();
		}
	}

	// cập nhập thời gian
	public void run() {
		try {
			Date thoiGianHienTai = new Date();
			while (true) {
				thoiGianHienTai = new Date(); // lấy thời gian hiện tại
				String ngayTrongTuan = "";
				if (thoiGianHienTai.getDay() == 0)
					ngayTrongTuan = "Chủ nhật, ";
				else
					ngayTrongTuan = "Thứ " + (thoiGianHienTai.getDay() + 1) + ", ";// do getDay() tính từ 1.
				txt_GioHT.setText(df_Gio.format(thoiGianHienTai));
				txt_NgayHT.setText(ngayTrongTuan + df_Ngay.format(thoiGianHienTai));
				thread.sleep(1000); // cho phép ngủ trong khoảng 1000 mns =1s
				// lấy thời gian hiện tại vào giờ vào
				if (rad_ChonHienTai.isSelected()) {
					time_VaoPhong.setDate(new Date());
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void docSDTKhachCoPhieu() throws RemoteException {
		txt_SoDT.removeAllItems();
		txt_SoDT.addItem("Tất cả");
		dsAllPhieuDat = dao_PhieuDatPhong.getDSTheoTrangThai("Đang chờ");
		for (PhieuDatPhong phieu : dsAllPhieuDat) {
			int kiemTra = 0;
			for (int i = 0; i < txt_SoDT.getItemCount(); i++)
				if (txt_SoDT.getItemAt(i).toString().trim().equals(phieu.getKhachHang().getSoDienThoai()))
					kiemTra = 1;
			if (kiemTra == 0)
				txt_SoDT.addItem(phieu.getKhachHang().getSoDienThoai());
		}
	}

	public void docDLVaoBang() {
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
			setIcon(dem, phong);
			// xử lý khi nhấn 1 phòng bất kỳ
			butt_danhSachPhong[dem].addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					maPhongDuocChon = maPhong;// gán là phòng được chọn để xử lý về sau
					setPhongDuocChon();
					try {
						docThongTinPhong(dao_Phong.getPhongTheoMa(maPhongDuocChon));
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
					txt_SoDT.setSelectedIndex(0);
				}
			});
			dem++;
		}
		// thêmn nút vào danh sách
		if (cbx_LoaiHienThi.getSelectedIndex() == 0) {
			docDLVaoBangTheoMaPhong();
		} else if (cbx_LoaiHienThi.getSelectedIndex() == 1) {
			docDLVaoBangTheoTrangThai();
		} else if (cbx_LoaiHienThi.getSelectedIndex() == 2) {
			docDLVaoBangTheoLoaiPhong();
		}
	}

	public void docThongTinPhong(Phong phong) throws RemoteException {
		// đọc thông tin phòng được chọn
		txt_MaPhong.setText("Mã phòng: " + phong.getMaPhong());
		txt_ThongTin.setText(" Tên phòng:___________" + phong.getTenPhong() + "\n" + " Vị trí phòng:__________"
				+ phong.getViTri() + "\n" + " Trạng thái hiện tại:____" + phong.getTrangThaiPhong() + "\n"
				+ " Loại phòng:__________" + phong.getLoaiPhong().getTenLoaiPhong() + "\n" + " Giá mỗi giờ:__________"
				+ df_Tien.format(phong.getLoaiPhong().getGia()) + " VNĐ");
		// đọc phiếu đặt phòng của phòng đó
		docDSPhieuDat(phong);
	}

	public void docDSPhieuDat(Phong phong) throws RemoteException {
		ArrayList<PhieuDatPhong> dsPhieu = dao_PhieuDatPhong.getTheoMaPhong_TrangThai(phong.getMaPhong(), "Đang chờ");
		int i = 1;
		model_dSPhongDaChon.setRowCount(0);
		for (PhieuDatPhong phieu : dsPhieu) {
			long soPhutDat = (long) (phieu.getTongGioDat() * 60);
			String soGioDat = "Chưa xác định";
			if (soPhutDat != 0) {
				if (soPhutDat > 59)
					soGioDat = ((int) soPhutDat / 60) + " giờ ";
				if (soPhutDat % 60 != 0)
					soGioDat = soGioDat + (soPhutDat % 60) + " phút";
			}
			String row[] = { i + "", phieu.getGioVaoPhong().format(forGioNgay), soGioDat,
					phieu.getKhachHang().getTenKhachHang() };
			model_dSPhongDaChon.addRow(row);
			i++;
		}
		// đọc thông tin nhận phòng
		docThongTinNhanPhong(dsPhieu.get(dsPhieu.size() - 1));
	}

	public String dinhDangPhut(long soPhut) {
		String soPhutString = "";
		int ngay, gio, phut;
		ngay = (int) soPhut / 1440;
		gio = (int) soPhut % 1440 / 60;
		phut = (int) soPhut % 60;
		if (ngay != 0)
			soPhutString = soPhutString + ngay + " ngày ";
		if (gio != 0)
			soPhutString = soPhutString + gio + "  giờ ";
		if (phut != 0)
			soPhutString = soPhutString + phut + "  phút";
		if (soPhutString.equals(""))
			soPhutString = "0 phút";
		return soPhutString;

	}

	public void docThongTinNhanPhong(PhieuDatPhong phieuCu) {
		LocalDateTime thoiGianHT = LocalDateTime.now();
		// Thời lượng đặt
		String soPhutDung = "";
		LocalDateTime thoiGianRaPCu = phieuCu.getGioVaoPhong().plusMinutes((long) (phieuCu.getTongGioDat() * 60));
		if (thoiGianRaPCu.isAfter(thoiGianHT))
			soPhutDung = dinhDangPhut(ChronoUnit.MINUTES.between(thoiGianHT, thoiGianRaPCu));
		else
			soPhutDung = "0";
//		LocalDateTime thoiGianHTKT = thoiGianHT.plusMinutes(10);// cho đặt sớm 10 phút

		// số phút dư để tới thời gian đặt
		long soPhutDu = 0;
		if (thoiGianHT.isBefore(phieuCu.getGioVaoPhong())) {
			soPhutDu = ChronoUnit.MINUTES.between(thoiGianHT, phieuCu.getGioVaoPhong());
			soPhutDung = dinhDangPhut((long) phieuCu.getTongGioDat() * 60);
		} else {
			soPhutDu = ChronoUnit.MINUTES.between(phieuCu.getGioVaoPhong(), thoiGianHT);
		}
		String soPhutString = dinhDangPhut(soPhutDu);
		if (!thoiGianHT.isBefore(phieuCu.getGioVaoPhong()))
			soPhutString = " trễ " + soPhutString;

		txt_ThongTinNhan.setText("Tên khách: \t" + phieuCu.getKhachHang().getTenKhachHang() + "\nPhòng: \t"
				+ phieuCu.getPhong().getTenPhong() + "\nCách thời gian nhận: " + soPhutString + "\nThời lượng dùng: "
				+ soPhutDung);
	}

	private void docDLVaoBangTheoMaPhong() {
		// Thêm từng phần tử vào danh sách
		int i = 1;
		for (; i < butt_danhSachPhong.length + 1; i++) {
			pan_duLieuPhong.add(butt_danhSachPhong[i - 1]);
		}
		pan_duLieuPhong.setPreferredSize(new Dimension(900, (i / 4 + 1) * 175));
	}

	private void docDLVaoBangTheoLoaiPhong() {
		// Thêm các phòng thường
		Panel lbl_PThuong1 = new Panel();
		lbl_PThuong1.setBackground(Color.black);
		lbl_PThuong1.setPreferredSize(new Dimension(650, 5));
		JLabel txt_Thuong = new JLabel("Phòng thường", JLabel.LEFT);
		txt_Thuong.setBackground(color1);
		txt_Thuong.setFont(new Font("Tahoma", Font.BOLD, 30));
		pan_duLieuPhong.add(txt_Thuong);
		pan_duLieuPhong.add(lbl_PThuong1);
		for (int i = 1; i < butt_danhSachPhong.length + 1; i++) {
			if (dsDLHienTai.get(i - 1).getLoaiPhong().getMaLoaiPhong().equals("LP002"))
				pan_duLieuPhong.add(butt_danhSachPhong[i - 1]);
		}
		Panel lbl_PVip = new Panel();
		lbl_PVip.setBackground(Color.black);
		lbl_PVip.setPreferredSize(new Dimension(800, 5));
		pan_duLieuPhong.add(lbl_PVip);

		// Thêm các phòng Vip
		Panel lbl_PVip2 = new Panel();
		lbl_PVip2.setPreferredSize(new Dimension(1500, 25));
		pan_duLieuPhong.add(lbl_PVip2);
		Panel lbl_PVip1 = new Panel();
		lbl_PVip1.setBackground(Color.black);
		lbl_PVip1.setPreferredSize(new Dimension(700, 5));
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
		pan_duLieuPhong.add(lbl_Cuoi);
		pan_duLieuPhong.setPreferredSize(new Dimension(900, (butt_danhSachPhong.length / 4 + 3) * 190));

	}

	private void docDLVaoBangTheoTrangThai() {
		// Thêm các phòng trống
		Panel lbl_PTrong = new Panel();
		lbl_PTrong.setBackground(Color.black);
		lbl_PTrong.setPreferredSize(new Dimension(650, 5));
		JLabel txt_Thuong = new JLabel("Phòng chờ", JLabel.LEFT);
		txt_Thuong.setBackground(color1);
		txt_Thuong.setFont(new Font("Tahoma", Font.BOLD, 30));
		pan_duLieuPhong.add(txt_Thuong);
		pan_duLieuPhong.add(lbl_PTrong);
		for (int i = 1; i < butt_danhSachPhong.length + 1; i++) {
			if (dsDLHienTai.get(i - 1).getTrangThaiPhong().equals("Chờ"))
				pan_duLieuPhong.add(butt_danhSachPhong[i - 1]);
		}
		Panel lbl_PCho = new Panel();
		lbl_PCho.setBackground(Color.black);
		lbl_PCho.setPreferredSize(new Dimension(1500, 5));
		pan_duLieuPhong.add(lbl_PCho);

		// Thêm các phòng Chờ
		Panel lbl_PCho2 = new Panel();
		lbl_PCho2.setPreferredSize(new Dimension(1500, 25));
		pan_duLieuPhong.add(lbl_PCho2);
		Panel lbl_PCho1 = new Panel();
		lbl_PCho1.setBackground(Color.black);
		lbl_PCho1.setPreferredSize(new Dimension(500, 5));
		JLabel txt_Vip = new JLabel("Phòng đang sử dụng", JLabel.LEFT);
		txt_Vip.setBackground(color1);
		txt_Vip.setFont(new Font("Tahoma", Font.BOLD, 30));
		pan_duLieuPhong.add(txt_Vip);
		pan_duLieuPhong.add(lbl_PCho1);
		for (int i = 1; i < butt_danhSachPhong.length + 1; i++) {
			if (dsDLHienTai.get(i - 1).getTrangThaiPhong().equals("Đang sử dụng"))
				pan_duLieuPhong.add(butt_danhSachPhong[i - 1]);
		}
		// Panel đường kẻ chân cuối cùng
		Panel lbl_Cuoi = new Panel();
		lbl_Cuoi.setBackground(Color.black);
		lbl_Cuoi.setPreferredSize(new Dimension(1500, 5));
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
	public void setPhongDuocChon() {
		// cập nhập nút cũ từ vị trí được chọn cũ
		if (viTriNutDuocChon != -1) {
			butt_danhSachPhong[viTriNutDuocChon].setBorder(BorderFactory.createLineBorder(Color.black, 1));

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
	public void setIcon(int viTri, Phong phong) {
		String trangThai = phong.getTrangThaiPhong();
		if (phong.getLoaiPhong().getTenLoaiPhong().equals("Vip")) {
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
		// đổi màu phòng đến giờ đặt nếu đến giờ đặt
		LocalDateTime thoiGianHT = LocalDateTime.now();
		for (PhieuDatPhong phieu : dsAllPhieuDat) {
			if (phieu.getPhong().getMaPhong().equals(phong.getMaPhong()))
				if (phieu.getGioVaoPhong().isBefore(thoiGianHT))
					butt_danhSachPhong[viTri].setBackground(color_DenGio);
		}
	}

	public void xoaTrang() {
		crPan_DSPhong.removeAll();
		pan_duLieuPhong.removeAll();
		crPan_DSPhong.add(pan_duLieuPhong);
		model_dSPhongDaChon.setRowCount(0);
		maPhongDuocChon = "";
		viTriNutDuocChon = -1;
		txt_ThongTin.setText("");
		txt_MaPhong.setText("");
	}

	public boolean kiemTraSDT(String soDT) throws RemoteException {
		KhachHang khachHang = dao_KhachHang.getKhachHangTheoSoDT(soDT);
		if (khachHang != null) {
			txt_MaKH.setText(khachHang.getMaKhachHang());
			txt_TenKH.setText(khachHang.getTenKhachHang());
			txt_DiemTL.setText(khachHang.getDiem() + "");
			khachDuocChon = khachHang;
			return false;
		}
		txt_MaKH.setText("");
		txt_TenKH.setText("");
		txt_DiemTL.setText("");
		return true;
	}

	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	public boolean huyPhieuDat() throws RemoteException {
		// đổi trạng thái của phiếu thành huỷ, còn muốn xoá thì phải vào quản lý phiếu
		// đặt phòng
		int hangDuocChon = tb_dsPhieuDatPhong.getSelectedRow();
		PhieuDatPhong phieuDuocChon = dao_PhieuDatPhong.getTheoMaPhong_TrangThai(maPhongDuocChon, "Đang chờ")
				.get(hangDuocChon);
		phieuDuocChon.setTrangThai("Đã huỷ");
		return dao_PhieuDatPhong.capNhapPhieu(phieuDuocChon);
	}

	public void thongBao(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.INFORMATION_MESSAGE);
	}

	public boolean kiemTraDSPhong(Phong phong) throws RemoteException {
		// kiểm tra số lượng phiếu trong dan sách
		// không có phiếu nào thì cập nhập trạng thái và thoát huỷ
		ArrayList<PhieuDatPhong> dsPhong = dao_PhieuDatPhong.getTheoMaPhong_TrangThai(maPhongDuocChon, "Đang chờ");
		if (dsPhong.size() != 0)
			return true;
		if (!phong.getTrangThaiPhong().equals("Đang sử dụng")) {
			phong.setTrangThaiPhong("Trống");
			System.out.println("test");
			dao_Phong.capNhapPhong(phong);
		}
		return false;
	}

	public void hienThiGioPhieu(boolean bol) {
		if (bol) {
			lbl_GioVao.show();
			lbl_GioRa.show();
			txt_GioRa.show();
			txt_GioVao.show();
			return;
		}
		lbl_GioVao.hide();
		lbl_GioRa.hide();
		txt_GioRa.hide();
		txt_GioVao.hide();
	}

	public String taoMaHoaDon() throws RemoteException {
		ArrayList<HoaDon> dsHD = dao_HoaDon.getALLHoaDon();
		int i = 0; // thứ tự hàng
		while (i < dsHD.size()) {
			// Lấy mã ở hàng thứ i trong table bỏ "DV" và chuyển sang kiểu int.
			// Tìm mã không khớp với hàng thứ i. Mã không khớp sẽ có số cuối mã
			// - i > 1
			if (Integer.parseInt(dsHD.get(i).getMaHoaDon().substring(2)) == (i + 1))
				// nếu mã khớp thì tăng i lên để xét hàng tiếp theo.
				i++;
			else
				// nếu mã không khớp thì thoát vòng lập
				break;
		}
		return String.format("HD%04d", i + 1);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(cbx_LoaiHienThi)) {
			hienThiGioPhieu(false);
			docDLVaoBang();
		}
		if (obj.equals(btn_HuyPhieu)) {
			int hangDuocChon = tb_dsPhieuDatPhong.getSelectedRow();
			if (hangDuocChon != -1) {
				int hoiLai = JOptionPane.showConfirmDialog(this, "Bạn xác nhận huỷ phiếu đặt phòng này?",
						"Xác nhận xoá", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (hoiLai == JOptionPane.YES_OPTION) {
					try {
						if (huyPhieuDat()) {
							thongBao("Huỷ phiếu thành công !", "Thông báo");

							// đọc lại thông tin phòng
							Phong phong = dao_Phong.getPhongTheoMa(maPhongDuocChon);
							System.out.println("Phòng: "+phong);
							//mới sửa 2024
							phong.setTrangThaiPhong("Trống");
							docDSPhieuDat(phong);
							hienThiGioPhieu(false);
							txt_SoDT.setSelectedIndex(0);
							//mới sửa 2024
//							dao_Phong.capNhapPhong(phong);
							//
							System.out.println(dao_Phong.capNhapPhong(phong));

							// đọc lại danh sách
							dsDLHienTai = dao_Phong.getDSCoPhieuDat();
							docDLVaoBang();
							// kiểm tra phòng còn phiếu hay không
							if (!kiemTraDSPhong(phong)) {
								thongBao(
										"Phòng " + phong.getTenPhong() + " đã hết phiếu đặt.\n Tải lại danh sách phòng đặt",
										"Thông báo");
								// đọc lại số điện thoại
								docSDTKhachCoPhieu();
							}
						} else {
							thongBaoLoi("Huỷ không thành công !\nLỗi hệ thống ", "Huỷ phiếu đặt phòng");
						}
						dsDLHienTai = dao_Phong.getDSCoPhieuDat();
						docDLVaoBang();
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			} else {
				thongBaoLoi("Bạn chưa chọn phiếu !", "Lỗi chọn phiếu");
			}
		}
		if (obj.equals(btn_LocDSTheoKH)) {
			hienThiGioPhieu(false);
			// Tất cả
			if (txt_SoDT.getSelectedIndex() == 0) {
				try {
					dsDLHienTai = dao_Phong.getDSCoPhieuDat();
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				docDLVaoBang();
				return;
			}
			// Theo 1 khách hàng
			else if (txt_MaKH.getText().trim().equals("")) {
				thongBaoLoi("Khách hàng không hợp lệ !", "Lọc danh sách");
				return;
			}
			time_RaDuKien.setCalendar(null);
			time_VaoPhong.setCalendar(null);
			try {
				dsDLHienTai = getDSPhongTheoKH();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			docDLVaoBang();
		}
		if (obj.equals(btn_LocDSTheoTG)) {
			hienThiGioPhieu(false);
			try {
				dsDLHienTai = getDSPhongTheoTG();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			docDLVaoBang();
		}
		if (obj.equals(btn_NhanPhong)) {
			if (maPhongDuocChon.equals("")) {
				thongBaoLoi("Bạn chưa chọn phòng!", "Nhận phòng");
				return;
			}
			try {
				if (nhanPhong()) {
					JOptionPane.showMessageDialog(this, "Nhận phòng thành công", "Nhận phòng",
							JOptionPane.INFORMATION_MESSAGE);
					// đọc lại danh sách
					try {
						dsDLHienTai = dao_Phong.getDSCoPhieuDat();
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
					docDLVaoBang();
					// đọc lại số điện thoại
					try {
						docSDTKhachCoPhieu();
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
					// cập nhập thông báo
					txt_ThongBao.setText(khachDuocChon.getTenKhachHang() + " đã nhận phòng " + maPhongDuocChon);
					hanhDong = "Nhận phòng";
				} else {
					thongBaoLoi("Nhận phòng chờ thất bại !", "Nhận phòng chờ");
				}
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	public boolean nhanPhong() throws RemoteException {
		PhieuDatPhong phieuCu = layPhieuDatPhongCuoi();
		khachDuocChon = phieuCu.getKhachHang();
		// // bỏ trường hợp kiểm thử không có phiếu
//		if (phieuCu == null)
//			return false;
		// Kiểm tra thời gian nhận phòng
		LocalDateTime thoiGianHT = LocalDateTime.now();
		LocalDateTime thoiGianHTKT = thoiGianHT.plusMinutes(10);// cho đặt sớm 10 phút
		if (thoiGianHTKT.isBefore(phieuCu.getGioVaoPhong())) {
			long soPhutDu = ChronoUnit.MINUTES.between(thoiGianHT, phieuCu.getGioVaoPhong());
			thongBaoLoi("Chưa đến giờ nhận phòng !\nCòn " + dinhDangPhut(soPhutDu) + " mới tới giờ đặt", "Nhận phòng");
			return false;
		}
		// Kiểm tra thời gian vào phòng
		// Nếu thời gian vào phòng quá hạn thì thông báo
		LocalDateTime thoiGianRaPCu = phieuCu.getGioVaoPhong().plusMinutes((long) (phieuCu.getTongGioDat() * 60));
		if (thoiGianRaPCu.isBefore(thoiGianHT)) {
			thongBaoLoi("Đã quá hạn nhận phòng!", "Nhận phòng");
			return false;
		}
		String maHD = taoMaHoaDon().trim();
		HoaDon hd = new HoaDon(maHD, 0, dao_NhanVien.getNhanVienTheoMa(maNhanVienTao), phieuCu.getKhachHang());
		LocalDateTime thoiGianHienTai = LocalDateTime.now();
		ChiTietHoaDon_Phong cthd_P = new ChiTietHoaDon_Phong(hd, phieuCu.getPhong(), thoiGianHienTai, null);
		// chưa biết xử lý với thời gian họ đã đặt
		// thêm vào sql
		if (!dao_HoaDon.themHoaDon(hd) || !dao_CTHD_Phong.themCTHD_PMoi(cthd_P))
			return false;
//		dao_CTHD_DichVu.themCTHD_DVMoi(cthd_DV);
		// cập nhập phiếu cũ
		phieuCu.setTrangThai("Hoàn thành");
		if (!dao_PhieuDatPhong.capNhapPhieu(phieuCu))
			return false;
		// cập nhập trạng thái phòng
		Phong phong = phieuCu.getPhong();
		phong.setTrangThaiPhong("Đang sử dụng");
		if (!dao_Phong.capNhapPhong(phong))
			return false;
		return true;
	}

	public ArrayList<Phong> getDSPhongTheoTG() throws RemoteException {
		ArrayList<Phong> dsPhong = dsDLHienTai;
		dsPhong.removeAll(dsPhong);
		LocalDateTime timeVao = time_VaoPhong.getDate() == null ? null
				: time_VaoPhong.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime timeRa = time_RaDuKien.getDate() == null ? null
				: time_RaDuKien.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		if (timeVao == null && timeRa == null) {
			return dao_Phong.getDSCoPhieuDat();
		}
		if (timeVao == null) {
			for (PhieuDatPhong ctPhieu : dsAllPhieuDat) {
				int soPhut = (int) (ctPhieu.getTongGioDat() * 60);
				LocalDateTime timeRaPhieu = ctPhieu.getGioVaoPhong().plusMinutes(soPhut);
				System.out.println(ctPhieu.getGioVaoPhong());
				System.out.println(timeRaPhieu);
				if (timeRaPhieu.isBefore(timeRa)) {
					dsPhong.add(ctPhieu.getPhong());
				}
			}
		} else if (timeRa == null) {
			for (PhieuDatPhong ctPhieu : dsAllPhieuDat) {
				if (ctPhieu.getGioVaoPhong().isAfter(timeVao))
					dsPhong.add(ctPhieu.getPhong());
			}
		}
		// Thời gian vào và thời gian ra khác null - Có dữ liệu
		else {
			for (PhieuDatPhong ctPhieu : dsAllPhieuDat) {
				int soPhut = (int) (ctPhieu.getTongGioDat() * 60);
				LocalDateTime timeRaPhieu = ctPhieu.getGioVaoPhong().plusMinutes(soPhut);
				if (timeRaPhieu.isBefore(timeVao) && ctPhieu.getGioVaoPhong().isAfter(timeRa))
					dsPhong.add(ctPhieu.getPhong());
			}
		}
		// sử dụng LinkedHashSet lấy ra danh sách ko trùng nhau
		Set<Phong> set = new LinkedHashSet<Phong>(dsPhong);
		return new ArrayList<Phong>(set);
	}

	public ArrayList<Phong> getDSPhongTheoKH() throws RemoteException {
		dsAllPhieuDat = dao_PhieuDatPhong.getDSTheoTrangThai("Đang chờ");
		ArrayList<Phong> dsPhong = dsDLHienTai;
		dsPhong.removeAll(dsPhong);
		if (dsAllPhieuDat == null)
			return null;
		String soDT = txt_SoDT.getSelectedItem().toString().trim();
		for (PhieuDatPhong ctPhieu : dsAllPhieuDat)
			if (ctPhieu.getTrangThai().equals("Đang chờ"))
				if (ctPhieu.getKhachHang().getSoDienThoai().equals(soDT) && !dsPhong.contains(ctPhieu.getPhong()))
					dsPhong.add(ctPhieu.getPhong());
		return dsPhong;
	}

	public PhieuDatPhong layPhieuDatPhongCuoi() throws RemoteException {
		ArrayList<PhieuDatPhong> dsphieuDat = dao_PhieuDatPhong.getTheoMaPhong_TrangThai(maPhongDuocChon, "Đang chờ");
		if (dsphieuDat.size() == 0)
			return null;
		return dsphieuDat.get(0);// lấy phiếu đặt phòng đầu tiên;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// đọc thông tin khách hàng theo phiếu được chọn
		int hangDuocChon = tb_dsPhieuDatPhong.getSelectedRow();
		ArrayList<PhieuDatPhong> dsPhieu = null;
		try {
			dsPhieu = dao_PhieuDatPhong.getTheoMaPhong_TrangThai(maPhongDuocChon, "Đang chờ");
		} catch (RemoteException ex) {
			throw new RuntimeException(ex);
		}
		KhachHang khachHang = dsPhieu.get(hangDuocChon).getKhachHang();
		txt_SoDT.setSelectedItem(khachHang.getSoDienThoai());
		hienThiGioPhieu(true);
		txt_GioVao.setText(dsPhieu.get(hangDuocChon).getGioVaoPhong().format(forGioNgay));
		int soPhut = (int) (dsPhieu.get(hangDuocChon).getTongGioDat() * 60);
		if (soPhut == 0) {
			txt_GioRa.setText("Chưa xác định");
			return;
		}
		LocalDateTime timeRaPhieu = dsPhieu.get(hangDuocChon).getGioVaoPhong().plusMinutes(soPhut);
		txt_GioRa.setText(timeRaPhieu.format(forGioNgay));
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
