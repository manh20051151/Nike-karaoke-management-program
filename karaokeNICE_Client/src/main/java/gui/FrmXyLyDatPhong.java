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
import java.util.Locale;

import javax.swing.AbstractButton;
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

//import dao.DAO_ChiTietHoaDon_Phong;
//import dao.DAO_HoaDon;
//import dao.DAO_KhachHang;
//import dao.DAO_LoaiKhachHang;
//import dao.DAO_NhanVien;
//import dao.DAO_PhieuDatPhong;
//import dao.DAO_Phong;
import entity.ChiTietHoaDon_Phong;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;

public class FrmXyLyDatPhong extends JFrame implements Runnable, ActionListener, MouseListener {
	private static final String URL = "rmi://26.173.247.75:7878/";

	DaoPhong dao_Phong = (DaoPhong) Naming.lookup(URL + "daoPhong");
	DaoHoaDon dao_HoaDon = (DaoHoaDon) Naming.lookup(URL + "hoaDon");
	DaoPhieuDatPhong dao_PhieuDatPhong = (DaoPhieuDatPhong) Naming.lookup(URL + "daoPhieuDatPhong");
	DaoChiTietHoaDon_Phong dao_CTHD_Phong = (DaoChiTietHoaDon_Phong) Naming.lookup(URL + "daoChiTietHoaDon_phong");
	DaoNhanVien dao_NhanVien = (DaoNhanVien) Naming.lookup(URL + "daoNhanVien");
	DaoKhachHang dao_KhachHang = (DaoKhachHang) Naming.lookup(URL + "daoKhachHang");
	DaoLoaiKhachHang dao_LoaiKhachHang = (DaoLoaiKhachHang) Naming.lookup(URL + "daoLoaiKhachHang");

//	private DAO_Phong dao_Phong = new DAO_Phong();
//	private DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
//	private DAO_PhieuDatPhong dao_PhieuDatPhong = new DAO_PhieuDatPhong();
//	private DAO_ChiTietHoaDon_Phong dao_CTHD_Phong = new DAO_ChiTietHoaDon_Phong();
//	private DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
//	private DAO_KhachHang dao_KhachHang = new DAO_KhachHang();
//	private DAO_LoaiKhachHang dao_LoaiKhachHang = new DAO_LoaiKhachHang();
	public static JPanel pnBorder;
	private JLabel txt_GioHT;
	private Thread thread = null;
	private JLabel txt_NgayHT;
	private JButton btn_DatPhong;
	private int viTriNutDuocChon = -1;
	private JScrollPane jScrollPane_KH;
	private JLabel lbl_TieuDe_1;

	// tạo danh sách phòng
	public int button_Height = 70;
	private JPanel panel_DSDatPhong;
	private Panel pan_duLieuPhong = new Panel();

	Color color1 = new Color(138, 43, 226); // BlueViolet
	Color color2 = new Color(233, 221, 244);
	private ImageIcon icon_PhongTrong = new ImageIcon("src/main/java/img/room-mini-3.png");
	private ImageIcon icon_PhongCho = new ImageIcon("src/main/java/img/room-mini-2.png");
	private ImageIcon icon_PhongDSD = new ImageIcon("src/main/java/img/room-mini-1.png");
	private ImageIcon icon_PhongVip = new ImageIcon("src/main/java/img/vip-Mini.gif");

	private ScrollPane crPan_DSPhong;
	private JPanel[] butt_danhSachPhong;
	private ArrayList<Phong> dsDLHienTai;
	private String maPhongDuocChon = "";
	private String maNhanVienTao = "";
	private JLabel iconVip;
	int kichThuocNutChucNang = 70;
	int viTriBanDauNutChucNang = 20;
	int khoangCachNutChucNang = 90;

	DecimalFormat df_ThoiGian = new DecimalFormat("hh:mm dd-MM-yyyy");
	DecimalFormat df_Tien = new DecimalFormat("###,###,###");
	private JPanel panel_ThoiGian_1;
	private JDateChooser time_VaoPhong;
	private JLabel lbl_GioDat;
	private JLabel lbl_ThoiGianDat;
	private JTextField txt_Gio;
	private JButton btn_LocDanhSach;
	private JLabel lbl_ThoiGianDat_1;
	private JLabel lbl_ThoiGianDat_2;
	private JComboBox cbx_LoaiHienThi;
	private JPanel panel_ThoiGian_1_2;
	private JLabel lbl_ThoiGianDat_3;
	private JLabel lbl_ThoiGianDat_4;
	private JLabel lbl_ThoiGianDat_5;
	private JLabel lbl_ThoiGianDat_6;
	private JLabel lbl_ThoiGianDat_7;
	private JLabel lbl_ThoiGianDat_8;
	private JPanel panel_Phai;
	private JLabel lbl_SoDT;
	private JComboBox<Object> txt_SoDT;
	private JLabel lbl_MaKH;
	private JLabel lbl_TenKH;
	private JLabel lbl_Diem;
	private JLabel lbl_GhiChu;
	private JLabel lbl_LoaiKH;
	private JTextField txt_TenKH;
	private JTextField txt_Diem;
	private JTextArea txt_GhiChu;
	private JLabel lbl_TieuDe_Trai;
	private JPanel panel_Trai_1;
	private JLabel lbl_TieuDe_Trai_1;
	private JPanel panel_AnhNen;
	private JTextArea txt_ThongTin;
	private JLabel txt_MaPhong;
	private JScrollPane jSc_dsPhongDaChon;
	private KhachHang khachDuocChon;
	private String maKHMoi;
	private JDateChooser time_RaDuKien;
	private AbstractButton btn_ChonPhong;
	private JTable tb_dsPhongDaChon;
	private DefaultTableModel model_dSPhongDaChon;
	private JPanel panel_Trai_2;
	private JRadioButton rad_ChonHienTai;
	private JTextArea txt_ThongTinTong;
	private JTextField txt_MaKH;
	private JTextField txt_LoaiKhach;
	private JButton btn_ThemKH;
	private JTextField txt_Phut;
	private JTextField txt_ThongBao;
	private String hanhDong = "";
	private JPanel pn_dsPhongDaChon;
	private JTextField txt_SucChua;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmXyLyDatPhong frame = new FrmXyLyDatPhong("NV001");
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
	public FrmXyLyDatPhong(String nhanVien) throws MalformedURLException, NotBoundException, RemoteException {
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
		panel_Menu.setBounds(1277, 630, 637, 264); // Mới chỉnh
		pnBorder.add(panel_Menu);

		btn_DatPhong = new JButton("Đặt phòng");
		btn_DatPhong.setBackground(Color.BLACK);
		btn_DatPhong.setForeground(Color.WHITE);
		btn_DatPhong.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_DatPhong.setBounds(186, 164, 260, kichThuocNutChucNang);
		panel_Menu.add(btn_DatPhong);

		panel_DSDatPhong = new JPanel();
		panel_DSDatPhong.setLayout(null);
		panel_DSDatPhong.setBackground(Color.WHITE);
		panel_DSDatPhong.setBounds(330, 324, 934, 572); // Mới chỉnh
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
		panel_TieuDe.setBackground(color_Xanh);
		panel_TieuDe.setBounds(0, 0, 934, 56); // Mới chỉnh
		panel_DSDatPhong.add(panel_TieuDe);
		panel_TieuDe.setLayout(null);

		lbl_TieuDe_1 = new JLabel("Danh sách phòng có thể đặt");
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
		panel_Trai.setBounds(10, 11, 310, 508);
		pnBorder.add(panel_Trai);

		panel_ThoiGian_1 = new JPanel();
		panel_ThoiGian_1.setLayout(null);
		panel_ThoiGian_1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u1EDDi gian \u0111\u1EB7t", TitledBorder.LEFT, TitledBorder.TOP, null, Color.WHITE));
		panel_ThoiGian_1.setBackground(color_Xanh);
		panel_ThoiGian_1.setBounds(10, 95, 290, 301);
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

		lbl_GioDat = new JLabel("Giờ vào phòng: ");
		lbl_GioDat.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_GioDat.setBounds(23, 11, 146, 48);
		panel_ThoiGian_1.add(lbl_GioDat);

		lbl_ThoiGianDat = new JLabel("Thời gian sử dụng:");
		lbl_ThoiGianDat.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat.setBounds(23, 211, 170, 36);
		panel_ThoiGian_1.add(lbl_ThoiGianDat);

		txt_Gio = new JTextField();
		txt_Gio.setHorizontalAlignment(SwingConstants.CENTER);
		txt_Gio.setText("0");
		txt_Gio.setFont(new Font("Dialog", Font.PLAIN, 20));
		txt_Gio.setColumns(10);
		txt_Gio.setBounds(23, 247, 49, 41);
		panel_ThoiGian_1.add(txt_Gio);
		txt_Gio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE)
					e.consume();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!txt_Gio.getText().equals("")) {
					int soGioNhap = Integer.parseInt(txt_Gio.getText().trim());
					LocalDateTime thoiGianHienTai = LocalDateTime.now();
					LocalDateTime ngayMai = LocalDateTime.of(thoiGianHienTai.getYear(), thoiGianHienTai.getMonthValue(),
							thoiGianHienTai.getDayOfMonth() + 1, 0, 0, 0);
					int soPhutNhap = Integer.parseInt(txt_Phut.getText().trim());
					int soPhut = soGioNhap * 60 + soPhutNhap;
					LocalDateTime timeVao = time_VaoPhong.getDate().toInstant().atZone(ZoneId.systemDefault())
							.toLocalDateTime();
					LocalDateTime timeRa = timeVao.plusMinutes(soPhut);
					Date timeRaDate = Date.from(timeRa.atZone(ZoneId.systemDefault()).toInstant());
					time_RaDuKien.setDate(timeRaDate);
//					if (ngayMai.isBefore(timeRa)) {
//						thongBaoLoi(
//								"Thời gian vào và thời gian ra phải cùng ngày!"
//										+ "\nCơ sở bắt đầu họat động từ 8h sáng cho đến 24h tối.",
//								"Lỗi nhập thời gian sử dụng");
//						txt_Gio.setText("0");
//						return;
//					}
				}
			}
		});

		txt_Phut = new JTextField();
		txt_Phut.setText("0");
		txt_Phut.setHorizontalAlignment(SwingConstants.CENTER);
		txt_Phut.setFont(new Font("Dialog", Font.PLAIN, 20));
		txt_Phut.setColumns(10);
		txt_Phut.setBounds(144, 247, 49, 41);
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

					LocalDateTime thoiGianHienTai = LocalDateTime.now();
					LocalDateTime ngayMai = LocalDateTime.of(thoiGianHienTai.getYear(), thoiGianHienTai.getMonthValue(),
							thoiGianHienTai.getDayOfMonth() + 1, 0, 0, 0);
					time_RaDuKien.setDate(timeRaDate);
//					if (ngayMai.isBefore(timeRa)) {
//						thongBaoLoi(
//								"Thời gian vào và thời gian ra phải cùng ngày!"
//										+ "\nCơ sở bắt đầu họat động từ 8h sáng cho đến 24 tối.",
//								"Lỗi nhập thời gian sử dụng");
//						txt_Phut.setText("0");
//						return;
//					}
				}
			}
		});

		lbl_ThoiGianDat_1 = new JLabel("giờ");
		lbl_ThoiGianDat_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_1.setBounds(82, 247, 49, 41);
		panel_ThoiGian_1.add(lbl_ThoiGianDat_1);

		lbl_ThoiGianDat_2 = new JLabel("phút");
		lbl_ThoiGianDat_2.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_2.setBounds(203, 243, 65, 48);
		panel_ThoiGian_1.add(lbl_ThoiGianDat_2);

		JLabel lbl_GioDat_1 = new JLabel("Giờ ra dự kiến:");
		lbl_GioDat_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_GioDat_1.setBounds(23, 127, 146, 41);
		panel_ThoiGian_1.add(lbl_GioDat_1);

		time_RaDuKien = new JDateChooser();
		time_RaDuKien.getCalendarButton()
				.setIcon(new ImageIcon("src/main/java/img/icons8-calendar-50.png"));
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
						return;
					}
					if (soPhutLech < 0) {
//						thongBaoLoi("Giờ ra phải sau giờ vào", "Nhập thời gian vào");
						txt_Gio.setText("0");
						txt_Phut.setText("0");
						time_RaDuKien.setCalendar(null);
						return;
					}
				}
			}
		});
		time_RaDuKien.getCalendarButton().setFont(new Font("UTM Aurora", Font.BOLD, 100));
		time_RaDuKien.setLocale(new Locale("vi", "VN"));
		time_RaDuKien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		time_RaDuKien.setDateFormatString("HH:mm dd-MM-yyyy");
		time_RaDuKien.setBounds(11, 163, 267, 48);
		panel_ThoiGian_1.add(time_RaDuKien);

		rad_ChonHienTai = new JRadioButton("Lấy thời gian hiện tại");
		rad_ChonHienTai.setSelected(true);
		rad_ChonHienTai.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		rad_ChonHienTai.setBackground(color_Xanh);
		rad_ChonHienTai.setFocusable(false);
		rad_ChonHienTai.setBounds(99, 96, 185, 36);
		panel_ThoiGian_1.add(rad_ChonHienTai);

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
		model_dSPhongDaChon.addColumn("Trạng thái");
		model_dSPhongDaChon.addColumn("Giá tiền mỗi giờ");

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
		txt_SoDT.setBounds(198, 70, 257, 37);
		panel_Phai.add(txt_SoDT);
		txt_SoDT.addItem("");
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
				}
			}
		});
		for (KhachHang kH : dao_KhachHang.getALLKhachHang()) {
			txt_SoDT.addItem(kH.getSoDienThoai());
		}

		lbl_SoDT = new JLabel("SDT khách: ");
		lbl_SoDT.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_SoDT.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_SoDT.setBounds(22, 64, 166, 48);
		panel_Phai.add(lbl_SoDT);

		lbl_MaKH = new JLabel("Mã khách hàng: ");
		lbl_MaKH.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_MaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_MaKH.setBounds(22, 125, 166, 48);
		panel_Phai.add(lbl_MaKH);

		lbl_TenKH = new JLabel("Tên khách hàng: ");
		lbl_TenKH.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_TenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_TenKH.setBounds(422, 125, 155, 48);
		panel_Phai.add(lbl_TenKH);

		lbl_Diem = new JLabel("Điểm tích luỹ:");
		lbl_Diem.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_Diem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_Diem.setBounds(26, 243, 162, 48);
		panel_Phai.add(lbl_Diem);

		lbl_GhiChu = new JLabel("Ghi chú:");
		lbl_GhiChu.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_GhiChu.setVerticalAlignment(SwingConstants.TOP);
		lbl_GhiChu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_GhiChu.setBounds(422, 196, 155, 37);
		panel_Phai.add(lbl_GhiChu);

		lbl_LoaiKH = new JLabel("Loại khách hàng:");
		lbl_LoaiKH.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_LoaiKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbl_LoaiKH.setBounds(22, 184, 165, 48);
		panel_Phai.add(lbl_LoaiKH);

		txt_TenKH = new JTextField();
		txt_TenKH.setBackground(Color.WHITE);
		txt_TenKH.setEditable(false);
		txt_TenKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_TenKH.setColumns(10);
		txt_TenKH.setBounds(585, 125, 303, 48);
		panel_Phai.add(txt_TenKH);

		txt_Diem = new JTextField();
		txt_Diem.setBackground(Color.WHITE);
		txt_Diem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_Diem.setEditable(false);
		txt_Diem.setColumns(10);
		txt_Diem.setBounds(198, 249, 112, 42);
		panel_Phai.add(txt_Diem);

		txt_GhiChu = new JTextArea();
		txt_GhiChu.setForeground(Color.BLACK);
		txt_GhiChu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_GhiChu.setEditable(false);
		txt_GhiChu.setBackground(Color.WHITE);
		txt_GhiChu.setBounds(585, 192, 303, 99);
		panel_Phai.add(txt_GhiChu);

		lbl_TieuDe_Trai = new JLabel("Thông tin khách đặt");
		lbl_TieuDe_Trai.setBounds(0, 11, 934, 48);
		panel_Phai.add(lbl_TieuDe_Trai);
		lbl_TieuDe_Trai.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TieuDe_Trai.setForeground(Color.WHITE);
		lbl_TieuDe_Trai.setFont(new Font("Tahoma", Font.BOLD, 30));

		btn_DatPhong.setFocusable(false);

		panel_Trai_2 = new JPanel();
		panel_Trai_2.setLayout(null);
		panel_Trai_2.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,

				TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_Trai_2.setBackground(new Color(60, 182, 192));
		panel_Trai_2.setBounds(10, 530, 310, 364);
		pnBorder.add(panel_Trai_2);

		panel_ThoiGian_1_2 = new JPanel();
		panel_ThoiGian_1_2.setBounds(10, 83, 290, 267);
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
		lbl_ThoiGianDat_5.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_ThoiGianDat_5.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_5.setBounds(10, 86, 112, 48);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_5);

		lbl_ThoiGianDat_6 = new JLabel();
		lbl_ThoiGianDat_6.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_6.setBounds(209, 73, 70, 64);
		lbl_ThoiGianDat_6.setIcon(icon_PhongCho);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_6);

		lbl_ThoiGianDat_7 = new JLabel("Phòng đã chọn: ");
		lbl_ThoiGianDat_7.setHorizontalAlignment(SwingConstants.LEFT);
		lbl_ThoiGianDat_7.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_7.setBounds(10, 158, 187, 48);
		panel_ThoiGian_1_2.add(lbl_ThoiGianDat_7);

		lbl_ThoiGianDat_8 = new JLabel();
		lbl_ThoiGianDat_8.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_8.setBounds(209, 145, 70, 64);
		lbl_ThoiGianDat_8.setIcon(new ImageIcon("src/main/java/img/icons8-stop-button-32.png"));
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
		panel_ThoiGian_1_1.setBounds(10, 11, 290, 69);
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
		cbx_LoaiHienThi.addItem("Trạng thái");
		cbx_LoaiHienThi.addItem("Loại phòng");
		panel_ThoiGian_1_1.add(cbx_LoaiHienThi);
		cbx_LoaiHienThi.addActionListener(this);

		txt_ThongTinTong = new JTextArea();
		txt_ThongTinTong.setForeground(Color.BLACK);
		txt_ThongTinTong.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_ThongTinTong.setEditable(false);
		txt_ThongTinTong.setBackground(Color.WHITE);
		txt_ThongTinTong.setBounds(80, 24, 481, 119);
		panel_Menu.add(txt_ThongTinTong);
		rad_ChonHienTai.setFocusable(false);

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
		txt_ThongTin.setBounds(98, 58, 452, 154);
		panel_AnhNen.add(txt_ThongTin);

		txt_MaPhong = new JLabel("");
		txt_MaPhong.setHorizontalAlignment(SwingConstants.CENTER);
		txt_MaPhong.setForeground(Color.GRAY);
		txt_MaPhong.setFont(new Font("Tahoma", Font.BOLD, 30));
		txt_MaPhong.setBounds(0, 0, 617, 59);
		panel_AnhNen.add(txt_MaPhong);

		btn_ChonPhong = new JButton("Chọn phòng");
		btn_ChonPhong.setBounds(186, 291, 265, 48);
		panel_Trai_1.add(btn_ChonPhong);
		btn_ChonPhong.setIcon(new ImageIcon("src/main/java/img/icons8-down-60.png"));
		btn_ChonPhong.setForeground(Color.WHITE);
		btn_ChonPhong.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_ChonPhong.setBackground(new Color(138, 43, 226));
		btn_ChonPhong.setFocusable(false);

		pn_dsPhongDaChon = new JPanel();
		pn_dsPhongDaChon.setBounds(20, 350, 597, 223);
		panel_Trai_1.add(pn_dsPhongDaChon);
		pn_dsPhongDaChon.setLayout(null);
		pn_dsPhongDaChon.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh sách phòng đã chọn", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		pn_dsPhongDaChon.setBackground(new Color(149, 214, 224));

		jSc_dsPhongDaChon = new JScrollPane((Component) null, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jSc_dsPhongDaChon.setBounds(6, 16, 581, 196);
		pn_dsPhongDaChon.add(jSc_dsPhongDaChon);
		tb_dsPhongDaChon = new JTable(model_dSPhongDaChon);
		tb_dsPhongDaChon.setRowHeight(35);
		tb_dsPhongDaChon.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		tb_dsPhongDaChon.setDefaultEditor(Object.class, null);
		tb_dsPhongDaChon.getColumnModel().getColumn(0).setPreferredWidth(5);
		jSc_dsPhongDaChon.setViewportView(tb_dsPhongDaChon);

		lbl_TieuDe_Trai_1 = new JLabel("Thông tin phòng");
		lbl_TieuDe_Trai_1.setBounds(2, 0, 633, 58);
		panel_Trai_1.add(lbl_TieuDe_Trai_1);
		lbl_TieuDe_Trai_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TieuDe_Trai_1.setForeground(Color.WHITE);
		lbl_TieuDe_Trai_1.setFont(new Font("Tahoma", Font.BOLD, 30));

		txt_MaKH = new JTextField();
		txt_MaKH.setBackground(Color.WHITE);
		txt_MaKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_MaKH.setEditable(false);
		txt_MaKH.setColumns(10);
		txt_MaKH.setBounds(198, 118, 112, 48);
		panel_Phai.add(txt_MaKH);

		txt_ThongBao = new JTextField("Đặt phòng/tạo phiếu đặt phòng gần đây nhất");
		txt_ThongBao.setForeground(Color.RED);
		txt_ThongBao.setHorizontalAlignment(SwingConstants.CENTER);
		txt_ThongBao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (hanhDong.equals(""))
					thongBao("Không có hành động nào gần đây", "Thông báo");
				else {
					if (hanhDong.equals("Đặt phòng")) {
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
					if (hanhDong.equals("Tạo phiếu đặt")) {
						int hoiLai = JOptionPane.showConfirmDialog(null,
								"Xem phòng đã tạo phiếu của khách " + khachDuocChon.getTenKhachHang(), "Chuyển trang",
								JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (hoiLai == JOptionPane.YES_OPTION) {
							// chuyển sang trang phòng đang sử dụng
							FrmXyLyPhieuDatPhong frm = null;
							try {
								frm = new FrmXyLyPhieuDatPhong(maNhanVienTao,
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
		txt_ThongBao.setFont(new Font("Tahoma", Font.ITALIC, 15));
		txt_ThongBao.setEditable(false);
		txt_ThongBao.setColumns(10);
		txt_ThongBao.setBackground(Color.WHITE);
		txt_ThongBao.setBounds(10, 900, 1904, 31);

		txt_LoaiKhach = new JTextField();
		txt_LoaiKhach.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_LoaiKhach.setEditable(false);
		txt_LoaiKhach.setColumns(10);
		txt_LoaiKhach.setBackground(Color.WHITE);
		txt_LoaiKhach.setBounds(198, 185, 112, 48);
		panel_Phai.add(txt_LoaiKhach);

		btn_ThemKH = new JButton("Thêm khách hàng");
		btn_ThemKH.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Them.png"));
		btn_ThemKH.setForeground(Color.WHITE);
		btn_ThemKH.setFont(new Font("Tahoma", Font.BOLD, 22));
		btn_ThemKH.setFocusable(false);
		btn_ThemKH.setBackground(new Color(138, 43, 226));
		btn_ThemKH.setBounds(629, 65, 262, 45);
		btn_ThemKH.addActionListener(this);
		// ---xử lý sự kiện---
		// Tạo luồng nhỏ chạy song song với chương trình.
		thread = new Thread(this);
		thread.start();
		// lấy tất cả danh sách dữ liệu
		dsDLHienTai = dao_Phong.getDSTheoTTphong("Trống");
		docDLVaoBang();
		// xử lý các nút
		maNhanVienTao = nhanVien;
		btn_DatPhong.addActionListener(this);
		btn_DatPhong.addMouseListener(this);
		rad_ChonHienTai.addActionListener(this);
		btn_DatPhong.setFocusable(false);
		panel_Phai.add(btn_ThemKH);
		btn_ThemKH.hide();

		btn_LocDanhSach = new JButton("Lọc danh sách");
		btn_LocDanhSach.setBounds(28, 456, 249, 41);
		panel_Trai.add(btn_LocDanhSach);
		btn_LocDanhSach.setIcon(new ImageIcon("src/main/java/img/Search-icon.png"));
		btn_LocDanhSach.setFont(new Font("Dialog", Font.PLAIN, 20));
		btn_LocDanhSach.addActionListener(this);
		btn_LocDanhSach.setFocusable(false);

		txt_SucChua = new JTextField();
		txt_SucChua.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE)
					e.consume();
			}
		});
		txt_SucChua.setText("0");
		txt_SucChua.setHorizontalAlignment(SwingConstants.CENTER);
		txt_SucChua.setFont(new Font("Dialog", Font.PLAIN, 20));
		txt_SucChua.setColumns(10);
		txt_SucChua.setBounds(131, 404, 71, 41);
		panel_Trai.add(txt_SucChua);

		JLabel lbl_ThoiGianDat_9 = new JLabel("Số người:");
		lbl_ThoiGianDat_9.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_9.setBounds(33, 407, 143, 36);
		panel_Trai.add(lbl_ThoiGianDat_9);

		JLabel lbl_ThoiGianDat_2_1 = new JLabel("người");
		lbl_ThoiGianDat_2_1.setFont(new Font("Dialog", Font.PLAIN, 20));
		lbl_ThoiGianDat_2_1.setBounds(212, 400, 65, 48);
		panel_Trai.add(lbl_ThoiGianDat_2_1);
		btn_DatPhong.setFocusable(false);
		pnBorder.add(txt_ThongBao);
		tb_dsPhongDaChon.addMouseListener(this);
		btn_ChonPhong.addActionListener(this);
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
				if (rad_ChonHienTai.isSelected()) {
					time_VaoPhong.setDate(new Date());
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void docDLVaoBang() {
		xoaTrang();
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
					btn_ChonPhong.setText("Chọn phòng");
					btn_ChonPhong.setBackground(new Color(138, 43, 226));
					btn_ChonPhong.setIcon(new ImageIcon("src/main/java/img/icons8-down-60.png"));
					super.mouseClicked(e);
				}
			});
			dem++;
		}
		// thêm nút vào danh sách
		if (cbx_LoaiHienThi.getSelectedIndex() == 0) {
			docDLVaoBangTheoMaPhong();
		} else if (cbx_LoaiHienThi.getSelectedIndex() == 1) {
			docDLVaoBangTheoTrangThai();
		} else if (cbx_LoaiHienThi.getSelectedIndex() == 2) {
			docDLVaoBangTheoLoaiPhong();
		}
	}

	public void docThongTinPhong() throws RemoteException {
		Phong phong = dao_Phong.getPhongTheoMa(maPhongDuocChon);
		txt_MaPhong.setText("Mã phòng: " + phong.getMaPhong());
		txt_ThongTin.setText(" Tên phòng:___________" + phong.getTenPhong() + "\n" + " Vị trí phòng:__________"
				+ phong.getViTri() + "\n" + " Sức chứa:____________" + phong.getSucChua() + " người\n"
				+ " Loại phòng:__________" + phong.getLoaiPhong().getTenLoaiPhong() + "\n" + " Giá mỗi giờ:__________"
				+ df_Tien.format(phong.getLoaiPhong().getGia()) + " VNĐ");
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
		JLabel txt_Thuong = new JLabel("Phòng trống", JLabel.LEFT);
		txt_Thuong.setBackground(color1);
		txt_Thuong.setFont(new Font("Tahoma", Font.BOLD, 30));
		pan_duLieuPhong.add(txt_Thuong);
		pan_duLieuPhong.add(lbl_PTrong);
		for (int i = 1; i < butt_danhSachPhong.length + 1; i++) {
			if (dsDLHienTai.get(i - 1).getTrangThaiPhong().equals("Trống"))
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
		lbl_PCho1.setPreferredSize(new Dimension(650, 5));
		JLabel txt_Vip = new JLabel("Phòng chờ", JLabel.LEFT);
		txt_Vip.setBackground(color1);
		txt_Vip.setFont(new Font("Tahoma", Font.BOLD, 30));
		pan_duLieuPhong.add(txt_Vip);
		pan_duLieuPhong.add(lbl_PCho1);
		for (int i = 1; i < butt_danhSachPhong.length + 1; i++) {
			if (dsDLHienTai.get(i - 1).getTrangThaiPhong().equals("Chờ"))
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
//			butt_danhSachPhong[viTriNutDuocChon].removeAll();
//			setIcon(viTriNutDuocChon);
//			JLabel lblMaPhong = new JLabel(dsDLHienTai.get(viTriNutDuocChon).getMaPhong());
//			lblMaPhong.setHorizontalAlignment(JLabel.CENTER);
//			lblMaPhong.setFont(new Font("Arial", Font.BOLD, 20));
//			butt_danhSachPhong[viTriNutDuocChon].add(lblMaPhong, BorderLayout.SOUTH);
//			butt_danhSachPhong[viTriNutDuocChon].setBorder(null);
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
	public void setIcon(int viTri) {
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

	public void xoaTrang() {
		crPan_DSPhong.removeAll();
		pan_duLieuPhong.removeAll();
		crPan_DSPhong.add(pan_duLieuPhong);
		maPhongDuocChon = "";
		txt_ThongTinTong.setText("");
		txt_ThongTin.setText("");
		txt_MaPhong.setText("");
	}

	public boolean kiemTraSDT(String soDT) throws RemoteException {
		KhachHang khachHang = dao_KhachHang.getKhachHangTheoSoDT(soDT);
		if (khachHang != null) {
			txt_MaKH.setText(khachHang.getMaKhachHang());
			txt_TenKH.setText(khachHang.getTenKhachHang());
			txt_Diem.setText(khachHang.getDiem() + "");
			txt_GhiChu.setText(khachHang.getGhiChu());
			txt_LoaiKhach.setText(khachHang.getLoaiKhachHang().getTenLoaiKhachHang());
			btn_ThemKH.hide();
			khachDuocChon = khachHang;
			return false;
		}
		txt_MaKH.setText(maKHMoi);
		txt_TenKH.setText("");
		txt_Diem.setText("");
		txt_GhiChu.setText("");
		btn_ThemKH.show();
		return true;
	}

	public void capNhapTTTong() {
		// Cập nhập thông tin dựa trên bảng danh sách phòng đã chon
		int soLuongP = model_dSPhongDaChon.getRowCount();
		if (soLuongP == 0) {
			txt_ThongTinTong.setText("");
			return;
		}
		double giaMoiGio = 0;
		for (int i = 0; i < soLuongP; i++) {
			// chuyển giá trong bảng về định dạng double rồi mới cộng
			double gia = Double.valueOf(model_dSPhongDaChon.getValueAt(i, 3).toString().replace(",000 VNĐ", "000"));
			giaMoiGio = giaMoiGio + gia;
		}
		String loaiDatString = "";
		if (rad_ChonHienTai.isSelected()) {
			loaiDatString = "\t- ĐẶT PHÒNG -";
			txt_ThongTinTong.setText(loaiDatString + "\n- Số lượng phòng: " + soLuongP + "" + "\n- Tổng tiền mỗi giờ: "
					+ df_Tien.format(giaMoiGio) + " VNĐ");
		} else {
			int soPhutDatMoi = Integer.parseInt(txt_Gio.getText().toString()) * 60
					+ Integer.parseInt(txt_Phut.getText());
			double tongGioDat = soPhutDatMoi / 60;
			String tongTienString = "\n- Tổng tiền dự tính: Chưa xác định";
			if (tongGioDat != 0) {
				double tongTien = giaMoiGio * tongGioDat;
				tongTienString = "\n- Tổng tiền dự tính: " + df_Tien.format(tongTien) + " VNĐ";
			}
			loaiDatString = "\t- TẠO PHIẾU -";
			txt_ThongTinTong.setText(loaiDatString + "\n- Số lượng phòng: " + soLuongP + "\n- Tổng tiền mỗi giờ: "
					+ df_Tien.format(giaMoiGio) + " VNĐ" + tongTienString);
		}
	}

	public void themKhachHang() throws MalformedURLException, NotBoundException, RemoteException {
		String sdt = txt_SoDT.getSelectedItem().toString().trim();
		// chuyển sang trang quản lý khách hàng
		FrmQuanLyKhachHang frmQLKH = new FrmQuanLyKhachHang(maNhanVienTao, sdt + " ", "FrmDatPhongHat");
		FrmTrangChu.jtab_NoiDung.remove(FrmTrangChu.jtab_NoiDung.getSelectedComponent());
//		FrmTrangChu.jtab_NoiDung.geth
		FrmTrangChu.jtab_NoiDung.add(frmQLKH.pnBorder);
	}

	public boolean kiemTraTGVoiPhieuCu(ArrayList<PhieuDatPhong> dsphieuCu) {
		LocalDateTime timeVaoMoi = time_VaoPhong.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		int soPhutDatMoi = Integer.parseInt(txt_Gio.getText().toString()) * 60 + Integer.parseInt(txt_Phut.getText());
		LocalDateTime timeRaMoi = timeVaoMoi.plusMinutes(soPhutDatMoi);

		for (PhieuDatPhong phieuCu : dsphieuCu) {
			// TH1: Xác định đươc giờ vào và giờ ra
			if (phieuCu.getTongGioDat() != 0) {
				// Dành ra 1 tiếng cách 2 lần đặt phòng
				int soPhutDatCu = (int) (Math.round(phieuCu.getTongGioDat() * 60) + 60);
				LocalDateTime timeVaoCu = phieuCu.getGioVaoPhong();
				LocalDateTime timeRaCu = timeVaoCu.plusMinutes(soPhutDatCu);

				// TH1.1: Nếu giờ vào mới < giờ vào cũ
				// => thì giờ ra mới < giờ vào cũ => Sai
				if (timeVaoMoi.isBefore(timeVaoCu) && timeRaMoi.isAfter(timeVaoCu)) {
					long soPhutSai = ChronoUnit.MINUTES.between(timeRaMoi, timeVaoCu);
//					thongBaoLoi("Thời gian đặt phòng bị trùng !\n" + "Thời gian ra phải sớm hơn " + soPhutSai + " phút",
//							"Xung đột với mã " + i);
					return false;
				}
				// TH1.2: Nếu giờ vào phòng mới < giờ ra phòng của phòng cũ
				// => Sai
				if (timeVaoMoi.isBefore(timeRaCu)) {
					long soPhutSai = ChronoUnit.MINUTES.between(timeVaoMoi, timeRaCu);
//					thongBaoLoi("Thời gian đặt phòng bị trùng !.\n" + "Thời gian vào phòng phải sớm hơn " + soPhutSai
//							+ " phút", "Xung đột với mã " + i);
					return false;
				}
			} else {
				/*
				 * Cách cũ không phù hợp với hiện tại
				 * 
				 * // TH 2: Tổng giờ đặt phiếu cũ =0 // TH 2.1: Khách cũ và khách mới ko xác
				 * định được giờ ra // =>Thoát if (soPhutDatMoi == 0) { //
				 * thongBaoLoi("Không thể đặt phòng vì cả khách cũ đặt cũng không ước lượng thời gian ra"
				 * , // "Xung đột với mã " + i); return false; } // TH 2.2: Chỉ nhận đặt trước
				 * giờ vào cũ vì khách cũ chưa xác định được giờ ra // Giờ ra mới < Giờ vào cũ
				 * => đúng else { LocalDateTime timeVaoCu = phieuCu.getGioVaoPhong(); if
				 * (timeRaMoi.isAfter(timeVaoCu)) { return true; } else { return false; } }
				 */

				// Ý TƯỞNG: khi khi thời gian phiếu cũ =0
				// thì ta sẽ để thời gian tối đa của phiếu cũ là 00:00 giờ ngày hôm để để kiểm
				// tra
				LocalDateTime timeVaoCu = phieuCu.getGioVaoPhong();
				LocalDateTime timeRaCu = LocalDateTime.of(timeVaoCu.getYear(), timeVaoCu.getMonthValue(),
						timeVaoCu.getDayOfMonth() + 1, 0, 0, 0);

				// TH1.1: Nếu giờ vào mới < giờ vào cũ
				// => thì giờ ra mới < giờ vào cũ => Sai
				if (timeVaoMoi.isBefore(timeVaoCu) && timeRaMoi.isAfter(timeVaoCu)) {
					long soPhutSai = ChronoUnit.MINUTES.between(timeRaMoi, timeVaoCu);
					return false;
				}
				// TH1.2: Nếu giờ vào phòng mới < giờ ra phòng của phòng cũ
				// => Sai
				if (timeVaoMoi.isBefore(timeRaCu)) {
					long soPhutSai = ChronoUnit.MINUTES.between(timeVaoMoi, timeRaCu);
					return false;
				}
			}
		}
		return true;
	}

	public int kiemTraThoiGian(Phong phong) throws RemoteException {
		ArrayList<PhieuDatPhong> dsphieuCu = dao_PhieuDatPhong.getTheoMaPhong_TrangThai(phong.getMaPhong(), "Đang chờ");
		// kiểm tra nhập rỗng
		if (time_VaoPhong.getDate() == null) {
			thongBaoLoi("Không được để trống thời gian đặt phòng!", "Lỗi nhập thời gian vào");
			return -1;
		}
		if (txt_Gio.getText().equals("")) {
			thongBaoLoi("Không được để trống giờ", "Lỗi nhập thời gian sử dụng");
			txt_Gio.setText("0");
			time_RaDuKien.setCalendar(null);
			return -1;
		}
		if (txt_Phut.getText().equals("")) {
			thongBaoLoi("Không được để trống phút", "Lỗi nhập thời gian sử dụng");
			txt_Gio.setText("0");
			time_RaDuKien.setCalendar(null);
			return -1;
		}
		LocalDateTime timeVaoMoi = time_VaoPhong.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime thoiGianHienTai = LocalDateTime.now();
		// kiểm tra thời gian vào: phải trước hiện tại
		if (!rad_ChonHienTai.isSelected()) {
			DateTimeFormatter df_ThoiGian = DateTimeFormatter.ofPattern("HH:mm dd/MM/yy");
			if (thoiGianHienTai.isAfter(timeVaoMoi)) {
				thongBaoLoi(
						"Thời gian vào phòng phải sau thời điểm hiện tại (" + df_ThoiGian.format(thoiGianHienTai) + ")",
						"Lỗi nhập thời gian đặt");
				return -1;
			}
		}
		double tongGioDat = Integer.parseInt(txt_Gio.getText().trim()) + Double.parseDouble(txt_Phut.getText()) / 60;
		// kiểm tra thời gian vào phòng không quá 1 tháng so với hiện tại
		if (timeVaoMoi.getHour() < 5) {
			thongBaoLoi("Quán 5 giờ mới bắt đầu mở cửa!\n Giờ vào phòng phải >8", "Lỗi nhập thời gian đặt");
			return -1;
		}
		if (thoiGianHienTai.plusMinutes(43200).isBefore(timeVaoMoi)) {
			thongBaoLoi(
					"Chỉ được phép đặt phòng trước 1 tháng (" + thoiGianHienTai.plusMinutes(43200).toLocalDate() + ")",
					"Lỗi nhập thời gian đặt");
			return -1;
		}
		// kiểm tra thời gian sử dụng -- áp dụng cho tổng giờ đặt khác 0
		if (tongGioDat != 0) {
			if (tongGioDat < 0.5) {
				thongBaoLoi("Thời gian sử dụng phòng quá ít.\n Thời gian sử dụng phải > 30 phút",
						"Lỗi nhập thời gian sử dụng");
				txt_Gio.setText("0");
				txt_Phut.setText("0");
				time_RaDuKien.setCalendar(null);
				return -1;
			}
			// tạo ngày mai của giờ ra dự kiến để kiểm tra
			LocalDateTime ngayMai = LocalDateTime.of(timeVaoMoi.getYear(), timeVaoMoi.getMonthValue(),
					timeVaoMoi.getDayOfMonth() + 1, 0, 0, 0);
			LocalDateTime time_RaDuKien = timeVaoMoi.plusHours((long) tongGioDat);
			if (ngayMai.isBefore(time_RaDuKien)) {
				thongBaoLoi("Thời gian vào và thời gian ra phải cùng ngày!"
						+ "\nCơ sở bắt đầu họat động từ 8h sáng cho đến 24 tối.", "Lỗi nhập thời gian sử dụng");
				return -1;
			}
		}
		// nếu phòng có phiếu đặt trước thì kiểm tra với phiếu cũ
		// áp dụng đối với phòng chờ và phòng đang sử dụng
		if (dsphieuCu.size() != 0) {
			if (!kiemTraTGVoiPhieuCu(dsphieuCu))
				return 0;
		}
		return 1;
	}

	private ArrayList<Phong> locDanhSach() throws RemoteException {
		ArrayList<Phong> dsLocDuoc = new ArrayList<>();
		// quy ước lỗi
		// -1 : lỗi phát sinh làm ảnh hưởng đến quá trình lọc phòng -> Thoát ct
		// 0 : lỗi do phòng ko đủ điều kiện -> Tiếp tục tìm kiếm
		// 1 : phòng thoả mãn -> Nhận phòng
		int sucChua = Integer.parseInt(txt_SucChua.getText());
		for (Phong phong : dao_Phong.getDanhSachPhong()) {
			if (!phong.getTrangThaiPhong().equals("Đang sử dụng")) {
				// kiểm tra sức chứa
				if (phong.getSucChua() >= sucChua) {
					// kiểm tra thời gian
					int kTra = kiemTraThoiGian(phong);
					if (kTra == -1) {
						return null;
					} else if (kTra == 1) {
						dsLocDuoc.add(phong);
					}
				}
			}
		}
		return dsLocDuoc;
	}

	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	public void thongBao(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(cbx_LoaiHienThi)) {
			docDLVaoBang();
		}
		if (obj.equals(btn_ThemKH)) {
			try {
				themKhachHang();
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if (obj.equals(btn_LocDanhSach)) {
			viTriNutDuocChon = -1;
			model_dSPhongDaChon.setRowCount(0);
			// lấy ds
			try {
				dsDLHienTai = dao_Phong.getDSTheoTTphong("Trống", "Chờ");
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			ArrayList<Phong> dsLoc = null;
			try {
				dsLoc = locDanhSach();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			// lọc ds
			int sl = dsLoc == null ? 0 : dsLoc.size();
			lbl_TieuDe_1.setText("Danh sách phòng có thể đặt (" + sl + ")");
			if (sl != 0) {
				dsDLHienTai = dsLoc;
				docDLVaoBang();
			} else {
				xoaTrang();
				model_dSPhongDaChon.setRowCount(0);
			}
		}
		// Chọn phòng
		if (obj.equals(btn_ChonPhong)) {
			// Chọn phòng vào danh sách
			if (btn_ChonPhong.getText().equals("Chọn phòng")) {
				if (maPhongDuocChon == "") {
					thongBaoLoi("Bạn chưa chọn phòng trong 'Danh sách phòng có thể chọn'!", "Chọn phòng");
					return;
				}
				Phong p = null;
				try {
					p = dao_Phong.getPhongTheoMa(maPhongDuocChon);
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				int slPhong = model_dSPhongDaChon.getRowCount();
				boolean kiemTra = false;
				for (int j = 0; j < slPhong; j++) {
					if (model_dSPhongDaChon.getValueAt(j, 1).equals(maPhongDuocChon)) {
						kiemTra = true;
						break;
					}
				}
				if (!kiemTra) {
					String row[] = { slPhong + 1 + "", p.getMaPhong(), p.getTrangThaiPhong(),
							df_Tien.format(p.getLoaiPhong().getGia()) + " VNĐ" };
					model_dSPhongDaChon.addRow(row);
					capNhapTTTong();
				} else {
					thongBaoLoi("Phòng đã có trong 'Danh sách đã chọn'!", "Chọn phòng vào danh sách");
				}
			}
			// Xoá phòng khỏi danh sách
			if (btn_ChonPhong.getText().equals("Bỏ chọn phòng")) {
				int hangDuocChon = tb_dsPhongDaChon.getSelectedRow();
				if (hangDuocChon == -1) {
					thongBaoLoi("Bạn chưa chọn phòng trong 'Danh sách phòng đã chọn'!", "Bỏ chọn phòng");
				} else {
					// Xoá phòng khỏi danh sách
					setBorderPhong(hangDuocChon);
					txt_ThongTin.setText("");
					txt_MaPhong.setText("");
					model_dSPhongDaChon.removeRow(hangDuocChon);
					// cập nhập vị trí stt trong bảng
					for (int i = hangDuocChon; i < tb_dsPhongDaChon.getRowCount(); i++) {
						model_dSPhongDaChon.setValueAt(i + 1, hangDuocChon, 0);
					}
					capNhapTTTong();
				}
			}
		}
		if (obj.equals(rad_ChonHienTai)) {
			txt_SoDT.setSelectedIndex(0);
			txt_MaPhong.setText("");
			txt_ThongTin.setText("");
			txt_ThongTinTong.setText("");
			model_dSPhongDaChon.setRowCount(0);
			xoaTrang();
			if (rad_ChonHienTai.isSelected()) {
				btn_DatPhong.setText("Đặt phòng");
				docDLVaoBang();
			} else {
				btn_DatPhong.setText("Tạo phiếu đặt phòng");
			}
		}
		if (obj.equals(btn_DatPhong)) {
			// kiểm tra
			if (model_dSPhongDaChon.getRowCount() < 1) {
				thongBaoLoi("Lỗi: bạn chưa chọn phòng !", "Đặt phòng");
				return;
			}
			String soDT = txt_SoDT.getSelectedItem().toString().trim();
			try {
				if (kiemTraSDT(soDT)) {
					thongBaoLoi("Lỗi: bạn chưa chọn khách !", "Đặt phòng");
					return;
				}
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			// Đặt phòng
			if (btn_DatPhong.getText().equals("Đặt phòng")) {
				try {
					if (taoHDMoi()) {
						JOptionPane.showMessageDialog(this,
								"Khách " + khachDuocChon.getTenKhachHang() + " đặt phòng thành công!", "Đặt phòng",
								JOptionPane.INFORMATION_MESSAGE);
						try {
							dsDLHienTai = dao_Phong.getDSTheoTTphong("Trống");
						} catch (RemoteException ex) {
							throw new RuntimeException(ex);
						}
						docDLVaoBang();
						txt_ThongTin.setText("");
						txt_ThongTinTong.setText("");
						txt_SoDT.setSelectedIndex(0);
						btn_ThemKH.hide();
						// Cập nhập thông báo
						txt_ThongBao.setText("Khách " + khachDuocChon.getTenKhachHang() + " đặt phòng");
						hanhDong = "Đặt phòng";
					} else {
						thongBaoLoi("Đặt phòng không thành công !", "Đặt phòng");
					}
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			}
			// Tạo phiếu đặt phòng
			if (btn_DatPhong.getText().equals("Tạo phiếu đặt phòng")) {
				try {
					if (taoPhieuDatPhongMoi()) {
						thongBao("Đã tạo phiếu đặt phòng thành công", "Tạo phiếu đặt phòng");
						model_dSPhongDaChon.setRowCount(0);
						try {
							dsDLHienTai = dao_Phong.getDSTheoTTphong("Trống", "Chờ");
						} catch (RemoteException ex) {
							throw new RuntimeException(ex);
						}
						try {
							dsDLHienTai = locDanhSach();
						} catch (RemoteException ex) {
							throw new RuntimeException(ex);
						}
						docDLVaoBang();
						// Cập nhập thông báo
						txt_ThongBao.setText("Khách " + khachDuocChon.getTenKhachHang() + " tạo phiếu đặt phòng");
						hanhDong = "Tạo phiếu đặt";
					} else {
						thongBaoLoi("Tạo phiếu đặt phòng thất bại", "Tạo phiếu đặt phòng");
					}
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
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

	public boolean taoHDMoi() throws RemoteException {
		NhanVien nVTao = dao_NhanVien.getNhanVienTheoMa(maNhanVienTao);
		LocalDateTime thoiGianHienTai = LocalDateTime.now();
		// tạo cthd_Phong lần lượt cho các phòng được chọn
		for (int i = 0; i < model_dSPhongDaChon.getRowCount(); i++) {
			// tạo hoá đơn
			String maHD = taoMaHoaDon().trim();
			HoaDon hd = new HoaDon(maHD, 0, nVTao, khachDuocChon);
			if (!dao_HoaDon.themHoaDon(hd))
				return false;

			Phong phong = dao_Phong.getPhongTheoMa(model_dSPhongDaChon.getValueAt(i, 1).toString().trim());
			ChiTietHoaDon_Phong cthd_P = new ChiTietHoaDon_Phong(hd, phong, thoiGianHienTai, null);
			if (!dao_CTHD_Phong.themCTHD_PMoi(cthd_P))
				return false;
			// cập nhập trạng thái phòng
			phong.setTrangThaiPhong("Đang sử dụng");
			if (!dao_Phong.capNhapPhong(phong))
				return false;
		}
		model_dSPhongDaChon.setRowCount(0);
		return true;
	}

	public boolean taoPhieuDatPhongMoi() throws RemoteException {
		LocalDateTime gioHT = (new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime thoiGianVaoPhong = time_VaoPhong.getDate().toInstant().atZone(ZoneId.systemDefault())
				.toLocalDateTime();
		for (int i = 0; i < model_dSPhongDaChon.getRowCount(); i++) {
			Phong phongDat = dao_Phong.getPhongTheoMa(model_dSPhongDaChon.getValueAt(i, 1).toString().trim());
			NhanVien nhanVienTao = dao_NhanVien.getNhanVienTheoMa(maNhanVienTao);
			int soPhutDatMoi = Integer.parseInt(txt_Gio.getText().toString()) * 60
					+ Integer.parseInt(txt_Phut.getText());
			double tongGioDat = soPhutDatMoi / 60;
			String maPhieu = taoMaPhieuDat();
			PhieuDatPhong phieuMoi = new PhieuDatPhong(maPhieu, gioHT, thoiGianVaoPhong, tongGioDat, khachDuocChon,
					phongDat, nhanVienTao, "Đang chờ");
			// Lưu phiếu vào sql
			if (!dao_PhieuDatPhong.themPhieuDat(phieuMoi))
				return false;
			// cập nhập trạng thái phòng
			if (phongDat.getTrangThaiPhong().equals("Trống")) {
				phongDat.setTrangThaiPhong("Chờ");
				dao_Phong.capNhapPhong(phongDat);
			}
		}
		return true;
	}

	public boolean taoPhieu() throws RemoteException {
		PhieuDatPhong phieuCu = layPhieuDatPhongCuoi();
		// bỏ trường hợp kiểm thử không có phiếu vì ràng buộc nút ở ngoài rồi
		if (phieuCu == null)
			return false;
		/// dư
		LocalDateTime thoiGianHT = LocalDateTime.now();
		thoiGianHT.plusMinutes(10);// cho đặt sớm 10 phút
		if (thoiGianHT.isBefore(phieuCu.getGioVaoPhong())) {
			long soPhutDu = ChronoUnit.MINUTES.between(thoiGianHT, phieuCu.getGioVaoPhong());
			int hoiLai = JOptionPane.showConfirmDialog(this,
					"Chưa đến giờ so với thời gian trong phiếu tạo phòng\n" + "Sớm hơn <<" + soPhutDu
							+ "phút>> so với trong phiếu: " + phieuCu.getGioVaoPhong(),
					"Thông báo nhận đặt phòng", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (hoiLai != JOptionPane.YES_OPTION) {
				return false;
			}
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

	public String taoMaPhieuDat() throws RemoteException {
		// tạo 3 số cuối
		int i = 0;
		if (dao_PhieuDatPhong.getAllPhieu().size() != 0)
			for (PhieuDatPhong phieu : dao_PhieuDatPhong.getAllPhieu()) {
				int bonSoCuoi = Integer.parseInt(phieu.getMaPhieuDatPhong().toString().trim().substring(2));
				if (bonSoCuoi != i)
					return String.format("DP%03d", i);
				i++;
			}
		return String.format("DP%03d", i);
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
		System.out.println("sdadasdsa: "+i + 1);

		return String.format("HD%04d", i + 1);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		btn_ChonPhong.setText("Bỏ chọn phòng");
		btn_ChonPhong.setBackground(Color.red);
		btn_ChonPhong.setIcon(new ImageIcon("src/main/java/img/icons8-close-60.png"));
		int hangDuocChon = tb_dsPhongDaChon.getSelectedRow();
		maPhongDuocChon = model_dSPhongDaChon.getValueAt(hangDuocChon, 1).toString().trim();
		setPhongDuocChon();
		try {
			docThongTinPhong();
		} catch (RemoteException ex) {
			throw new RuntimeException(ex);
		}
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
