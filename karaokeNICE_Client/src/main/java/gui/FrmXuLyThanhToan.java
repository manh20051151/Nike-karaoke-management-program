package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.*;
//import dao.DAO_ChiTietHoaDon_DichVu;
//import dao.DAO_ChiTietHoaDon_Phong;
//import dao.DAO_HoaDon;
//import dao.DAO_KhachHang;
//import dao.DAO_NhanVien;
//import dao.DAO_PhieuDatPhong;
//import dao.DAO_Phong;
import entity.ChiTietHoaDon_DichVu;
import entity.ChiTietHoaDon_Phong;
import entity.HoaDon;
import entity.KhachHang;
import entity.LoaiKhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class FrmXuLyThanhToan extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final String URL = "rmi://26.173.247.75:7878/";

	DaoHoaDon dao_HoaDon = (DaoHoaDon) Naming.lookup(URL + "hoaDon");
	DaoChiTietHoaDon_Phong dao_ChietTietHoaDon_Phong = (DaoChiTietHoaDon_Phong) Naming
			.lookup(URL + "daoChiTietHoaDon_phong");
	DaoChiTietHoaDon_DichVu dao_ChietTietHoaDon_DichVu = (DaoChiTietHoaDon_DichVu) Naming
			.lookup(URL + "daoChiTietHoaDon_dichVu");
	DaoPhieuDatPhong dao_PhieuDatPhong = (DaoPhieuDatPhong) Naming.lookup(URL + "daoPhieuDatPhong");
	DaoPhong dao_Phong = (DaoPhong) Naming.lookup(URL + "daoPhong");

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

	private static DaoNhanVien dao_NhanVien;

	static {
		try {
			dao_NhanVien = (DaoNhanVien) Naming.lookup(URL + "daoNhanVien");
		} catch (NotBoundException e) {
			throw new RuntimeException(e);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

//	private DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
//	private DAO_ChiTietHoaDon_Phong dao_ChietTietHoaDon_Phong = new DAO_ChiTietHoaDon_Phong();
//	private DAO_ChiTietHoaDon_DichVu dao_ChietTietHoaDon_DichVu = new DAO_ChiTietHoaDon_DichVu();
//	private DAO_PhieuDatPhong dao_PhieuDatPhong = new DAO_PhieuDatPhong();
//	private DAO_Phong dao_Phong = new DAO_Phong();

//	private static DAO_ChiTietHoaDon_Phong dao_CTHD_Phong = new DAO_ChiTietHoaDon_Phong();
//	private static DAO_ChiTietHoaDon_DichVu dao_CTHD_DV = new DAO_ChiTietHoaDon_DichVu();
//	private static DAO_KhachHang dao_KhachHang = new DAO_KhachHang();
//	private static DAO_NhanVien dao_NhanVien = new DAO_NhanVien();

	private static final long serialVersionUID = 1L;

	private JTable tbl;
	private JButton btnThanhToan;
	private ArrayList<ChiTietHoaDon_Phong> dscthd_Phong = new ArrayList<ChiTietHoaDon_Phong>();
	private ArrayList<ChiTietHoaDon_DichVu> dscthd_DV = new ArrayList<ChiTietHoaDon_DichVu>();
	private DecimalFormat df = new DecimalFormat("#,##0 đ");
	private DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/yyy");
	private DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyy");
	private DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("HH:mm");
	private DefaultTableModel model;
	private JLabel lblGiaTriMaHoaDon;
	private JLabel lblGiaTriNhanVienThuNgan;
	private JLabel lblGiaTriNgayLap;
	private JLabel lblGiaTriGioRa;
	private JLabel lblGiaTriGioVao;
	private JLabel lblGiaTriTienThua;
	private JLabel lblGiaTriTongTienPhong;
	private JLabel lblGiaTriTongTienDichVu;
	private JLabel lblGiaTriChietKhau;
	private JLabel lblGiaTriTongTienThanhToan;
	private JTextField txtGiaTriTienNhan;
	private JComboBox<Object> cbx_SDTKH;
	private JLabel lblGiaTriTenKhach;
	private long tongTienDV = 0;
	private long tongTienPhong = 0;
	private int chietKhau = 0;
	private long tongTien = 0;
	private long tienThua = -1;
	private long tienNhan = 0;
	private NhanVien nhanVien;

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

	static ArrayList<KhachHang> dsALL_KH;

	static {
		try {
			dsALL_KH = dao_KhachHang.getALLKhachHang();
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	private ArrayList<PhieuDatPhong> dsALL_PhieuDatP = dao_PhieuDatPhong.getDSTheoTrangThai("Chờ");
	private JCheckBox checkInHoaDon;
	private HoaDon hdCapNhap;
	static ArrayList<HoaDon> dsHoaDon;
	static LocalDateTime thoiGianHT = LocalDateTime.now();

	public FrmXuLyThanhToan(ArrayList<HoaDon> dsHD, NhanVien nhanVienTao)
			throws MalformedURLException, NotBoundException, RemoteException {
		this.setTitle("Thanh toán");
		this.setSize(859, 1040);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.createUI(dsHD, nhanVienTao);
		this.setVisible(true);
	}

	private void createUI(ArrayList<HoaDon> dsHD, NhanVien nhanVienTao) throws RemoteException {
		// Font
		Font font1 = new Font("Times New Roman", Font.PLAIN, 20);
		Font font2 = new Font("Times New Roman", Font.BOLD, 20);
		Font font3 = new Font("Times New Roman", Font.BOLD, 32);

		// Màu chữ
		Color color = new Color(138, 43, 226); // BlueViolet

		JPanel pnlBorder = new JPanel();
		pnlBorder.setBackground(color);
		pnlBorder.setLayout(new BoxLayout(pnlBorder, BoxLayout.Y_AXIS));

		// Top
		JPanel pnlTop = new JPanel();
		pnlTop.setBackground(Color.WHITE);
		pnlTop.setBorder(new EmptyBorder(0, 180, 10, 180));
		pnlTop.setLayout(new BoxLayout(pnlTop, BoxLayout.Y_AXIS));

		Box bTenQuan = Box.createHorizontalBox();
		Box bDiaChi = Box.createHorizontalBox();
		Box bSoDienThoai = Box.createHorizontalBox();

		JLabel lblTenQuan = new JLabel("KARAOKE NICE");
		lblTenQuan.setFont(new Font("Times New Roman", Font.BOLD, 20));
		JLabel lblDiaChi = new JLabel("12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Thành phố Hồ Chí Minh");
		lblDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		JLabel lblSoDienThoai = new JLabel("SĐT: 0123 456 789");
		lblSoDienThoai.setFont(new Font("Times New Roman", Font.ITALIC, 18));

		bTenQuan.add(lblTenQuan);
		bDiaChi.add(lblDiaChi);
		bSoDienThoai.add(lblSoDienThoai);

		pnlTop.add(bTenQuan);
		pnlTop.add(bDiaChi);
		pnlTop.add(bSoDienThoai);

		// Tieu de
		JPanel pnlTieuDe = new JPanel();
		pnlTieuDe.setBackground(Color.WHITE);
		pnlTieuDe.setBorder(new EmptyBorder(10, 20, 20, 20));
		JLabel lblTieuDe = new JLabel("HÓA ĐƠN TÍNH TIỀN");
		lblTieuDe.setFont(font3);
		lblTieuDe.setForeground(color);
		pnlTieuDe.add(lblTieuDe);

		// Thông tin
		JPanel pnlThongTin = new JPanel();
		pnlThongTin.setBackground(Color.WHITE);
		pnlThongTin.setBorder(new EmptyBorder(0, 25, 10, 25));
		pnlThongTin.setLayout(new BorderLayout());

		Box bLeft = Box.createVerticalBox();
		Box bLeft1 = Box.createHorizontalBox();
		Box bLeft2 = Box.createHorizontalBox();
		Box bLeft3 = Box.createHorizontalBox();

		JLabel lblMaHoaDon = new JLabel("Mã hóa đơn: ");
		lblMaHoaDon.setFont(font1);
		lblMaHoaDon.setBorder(new EmptyBorder(0, 0, 0, 40));
		lblGiaTriMaHoaDon = new JLabel();
		lblGiaTriMaHoaDon.setFont(font1);
		JLabel lblNgayLap = new JLabel("Ngày lập:");
		lblNgayLap.setFont(font1);
		lblGiaTriNgayLap = new JLabel();
		lblGiaTriNgayLap.setFont(font1);
		JLabel lblKhachHang = new JLabel("SĐT khách:");
		lblKhachHang.setFont(font1);

		lblKhachHang.setPreferredSize(lblMaHoaDon.getPreferredSize());
		lblNgayLap.setPreferredSize(lblMaHoaDon.getPreferredSize());

		bLeft1.add(lblMaHoaDon);
		bLeft1.add(lblGiaTriMaHoaDon);
		bLeft1.add(Box.createVerticalStrut(35));
		bLeft2.add(lblNgayLap);
		bLeft2.add(lblGiaTriNgayLap);
		bLeft2.add(Box.createVerticalStrut(35));
		bLeft3.add(lblKhachHang);

		cbx_SDTKH = new JComboBox<Object>();
		cbx_SDTKH.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bLeft3.add(cbx_SDTKH);
		bLeft3.add(Box.createVerticalStrut(35));

		bLeft.add(bLeft1);
		bLeft.add(bLeft2);
		bLeft.add(bLeft3);

		Box bRight = Box.createVerticalBox();
		Box bRight1 = Box.createHorizontalBox();
		Box bRight2 = Box.createHorizontalBox();
		Box bRight3 = Box.createHorizontalBox();

		JLabel lblGioVao = new JLabel("Giờ vào: ");
		lblGioVao.setFont(font1);
		lblGiaTriGioVao = new JLabel();
		lblGiaTriGioVao.setFont(font1);
		JLabel lblGioRa = new JLabel("Giờ ra: ");
		lblGioRa.setFont(font1);
		lblGiaTriGioRa = new JLabel();
		lblGiaTriGioRa.setFont(font1);
		JLabel lblGiaTriTongThoiLuong = new JLabel();
		lblGiaTriTongThoiLuong.setFont(font1);
		JLabel lblNhanVienThuNgan = new JLabel("Nhân viên:");
		lblNhanVienThuNgan.setBorder(new EmptyBorder(0, 0, 0, 40));
		lblNhanVienThuNgan.setFont(font1);
		lblGiaTriNhanVienThuNgan = new JLabel();
		lblGiaTriNhanVienThuNgan.setFont(font1);

		lblGioVao.setPreferredSize(lblNhanVienThuNgan.getPreferredSize());
		lblGioRa.setPreferredSize(lblNhanVienThuNgan.getPreferredSize());

		bRight1.add(lblGioVao);
		bRight1.add(lblGiaTriGioVao);
		bRight1.add(Box.createVerticalStrut(35));
		bRight2.add(lblGioRa);
		bRight2.add(lblGiaTriGioRa);
		bRight2.add(Box.createVerticalStrut(35));
		bRight3.add(lblNhanVienThuNgan);
		bRight3.add(lblGiaTriNhanVienThuNgan);
		bRight3.add(Box.createVerticalStrut(35));

		bRight.add(bRight1);
		bRight.add(bRight2);
		bRight.add(bRight3);

		pnlThongTin.add(bLeft, BorderLayout.WEST);

		Box bLeft3_1 = Box.createHorizontalBox();
		bLeft.add(bLeft3_1);

		JLabel lblTnKhch = new JLabel("Tên khách:");
		lblTnKhch.setPreferredSize(new Dimension(145, 24));
		lblTnKhch.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		bLeft3_1.add(lblTnKhch);

		lblGiaTriTenKhach = new JLabel();
		lblGiaTriTenKhach.setText("Nguyễn Văn A");
		lblGiaTriTenKhach.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		bLeft3_1.add(lblGiaTriTenKhach);

		Component verticalStrut = Box.createVerticalStrut(35);
		bLeft3_1.add(verticalStrut);
		pnlThongTin.add(bRight, BorderLayout.EAST);

		// Dịch vụ
		JPanel pnlDichVu = new JPanel();
		pnlDichVu.setBackground(Color.WHITE);
		pnlDichVu.setBorder(new EmptyBorder(0, 15, 10, 15));
		pnlDichVu.setLayout(new BorderLayout());

		model = new DefaultTableModel();
		model.addColumn("STT");
		model.addColumn("Phòng/Dịch vụ");
		model.addColumn("Thời gian/SL");
		model.addColumn("Giá");
		model.addColumn("Thành tiền");
		tbl = new JTable(model);
		tbl.setRowHeight(35);
		tbl.setDefaultEditor(Object.class, null);
		JScrollPane spDichVu = new JScrollPane(tbl);

		dieuChinhDoRongCotTable("Phòng/Dịch vụ", 250);
		dieuChinhDoRongCotTable("Thời gian/SL", 170);
		dieuChinhDoRongCotTable("Giá", 130);
		dieuChinhDoRongCotTable("Thành tiền", 150);

		((DefaultTableCellRenderer) tbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		canChinhCotTable("STT", DefaultTableCellRenderer.CENTER);
		canChinhCotTable("Giá", DefaultTableCellRenderer.RIGHT);
		canChinhCotTable("Thành tiền", DefaultTableCellRenderer.RIGHT);

		JTableHeader tbh = tbl.getTableHeader();
		tbh.setFont(font2);
		tbl.setFont(font1);
		pnlDichVu.add(spDichVu);

		// Tiền thanh toán
		JPanel pnlThanhToan = new JPanel();
		pnlThanhToan.setBackground(Color.WHITE);
		pnlThanhToan.setBorder(new EmptyBorder(0, 25, 20, 25));
		pnlThanhToan.setLayout(new BoxLayout(pnlThanhToan, BoxLayout.Y_AXIS));

		Box bThanhToan1 = Box.createHorizontalBox();
		Box bThanhToan2 = Box.createHorizontalBox();
		Box bThanhToan3 = Box.createHorizontalBox();
		Box bThanhToan4 = Box.createHorizontalBox();
		Box bThanhToan5 = Box.createHorizontalBox();
		Box bThanhToan6 = Box.createHorizontalBox();

		JLabel lblTongTienPhong = new JLabel("Tổng tiền phòng:");
		lblTongTienPhong.setFont(font1);
		lblGiaTriTongTienPhong = new JLabel();
		lblGiaTriTongTienPhong.setFont(font1);
		JLabel lblTongTienDichVu = new JLabel("Tổng tiền dịch vụ:");
		lblTongTienDichVu.setFont(font1);
		lblGiaTriTongTienDichVu = new JLabel();
		lblGiaTriTongTienDichVu.setFont(font1);
		JLabel lblChietKhau = new JLabel("Chiết khấu:");
		lblChietKhau.setFont(font1);
		lblGiaTriChietKhau = new JLabel();
		lblGiaTriChietKhau.setFont(font1);
		JLabel lblTongTienThanhToan = new JLabel("Tổng tiền thanh toán:");
		lblTongTienThanhToan.setForeground(Color.RED);
		lblTongTienThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblGiaTriTongTienThanhToan = new JLabel();
		lblGiaTriTongTienThanhToan.setForeground(Color.RED);
		lblGiaTriTongTienThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 28));
		JLabel lblTienNhan = new JLabel("Tiền nhận:");
		lblTienNhan.setFont(font1);
		lblTienNhan.setBorder(new EmptyBorder(0, 0, 0, 450));
		txtGiaTriTienNhan = new JTextField(15);
		txtGiaTriTienNhan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c)) || c == KeyEvent.VK_BACK_SPACE)
					e.consume();
			}

			@Override
			public void keyReleased(KeyEvent e) {
				tinhTienNhan();
			}
		});
		txtGiaTriTienNhan.setFont(font1);
		txtGiaTriTienNhan.setHorizontalAlignment(JTextField.RIGHT);
		JLabel lblTienThua = new JLabel("Tiền thừa:");
		lblTienThua.setFont(font1);
		lblGiaTriTienThua = new JLabel();
		lblGiaTriTienThua.setFont(font1);

		bThanhToan2.add(lblTongTienPhong);
		bThanhToan2.add(Box.createVerticalStrut(35));
		bThanhToan2.add(lblGiaTriTongTienPhong);
		bThanhToan1.add(lblTongTienDichVu);
		bThanhToan1.add(Box.createVerticalStrut(35));
		bThanhToan1.add(lblGiaTriTongTienDichVu);
		bThanhToan3.add(lblChietKhau);
		bThanhToan3.add(Box.createVerticalStrut(35));
		bThanhToan3.add(lblGiaTriChietKhau);
		bThanhToan4.add(lblTongTienThanhToan);
		bThanhToan4.add(Box.createVerticalStrut(45));
		bThanhToan4.add(lblGiaTriTongTienThanhToan);
		bThanhToan5.add(lblTienNhan);
		bThanhToan5.add(Box.createVerticalStrut(35));
		bThanhToan5.add(txtGiaTriTienNhan);
		bThanhToan6.add(lblTienThua);
		bThanhToan6.add(Box.createVerticalStrut(35));
		bThanhToan6.add(lblGiaTriTienThua);

		pnlThanhToan.add(bThanhToan1);
		pnlThanhToan.add(bThanhToan2);
		pnlThanhToan.add(bThanhToan3);
		pnlThanhToan.add(bThanhToan4);
		pnlThanhToan.add(bThanhToan5);
		pnlThanhToan.add(bThanhToan6);

		// In hóa đơn
		JPanel pnlButton = new JPanel();
		pnlButton.setBackground(Color.WHITE);
		pnlButton.setBorder(new EmptyBorder(15, 15, 15, 15));
		pnlButton.setLayout(new BoxLayout(pnlButton, BoxLayout.X_AXIS));

		btnThanhToan = new JButton("Thanh toán");
		btnThanhToan.setBackground(color);
		btnThanhToan.setForeground(Color.WHITE);
		btnThanhToan.setFont(font2);

		checkInHoaDon = new JCheckBox("In hóa đơn");
		checkInHoaDon.setSelected(true);
		checkInHoaDon.setBackground(Color.WHITE);
		checkInHoaDon.setFont(font1);

		pnlButton.add(Box.createVerticalStrut(0));
		pnlButton.add(checkInHoaDon);
		pnlButton.add(btnThanhToan);

		pnlBorder.add(pnlTop);
		pnlBorder.add(Box.createVerticalStrut(2));
		pnlBorder.add(pnlTieuDe);
		pnlBorder.add(pnlThongTin);
		pnlBorder.add(pnlDichVu);
		pnlBorder.add(pnlThanhToan);
		pnlBorder.add(Box.createVerticalStrut(2));
		pnlBorder.add(pnlButton);
		getContentPane().add(pnlBorder);
		btnThanhToan.addActionListener(this);
		cbx_SDTKH.addActionListener(this);
		// đọc thông tin hoá đơn
		loadDL(dsHD, nhanVienTao);
	}

	private void dieuChinhDoRongCotTable(String tenCot, int doRong) {
		tbl.getColumn(tenCot).setPreferredWidth(doRong);
	}

	private void canChinhCotTable(String tenCot, int canChinh) {
		DefaultTableCellRenderer dRenderer = new DefaultTableCellRenderer();
		dRenderer.setHorizontalAlignment(canChinh);
		tbl.getColumn(tenCot).setCellRenderer(dRenderer);
	}

	private void loadDL(ArrayList<HoaDon> dsHD, NhanVien nhanVienTao) throws RemoteException {
		dsHoaDon = dsHD;
		nhanVien = nhanVienTao;
		if (dsHD.size() == 1) {
			lblGiaTriMaHoaDon.setText(dsHD.get(0).getMaHoaDon());
			cbx_SDTKH.addItem(dsHoaDon.get(0).getKhachHang().getSoDienThoai().toString());
			lblGiaTriTenKhach.setText(dsHoaDon.get(0).getKhachHang().getTenKhachHang().toString());
		} else {
			lblGiaTriMaHoaDon.setText(layMaHDNhoNhat());
			for (HoaDon hDon : dsHoaDon)
				cbx_SDTKH.addItem(hDon.getKhachHang().getSoDienThoai());
		}
		lblGiaTriNhanVienThuNgan.setText(nhanVienTao.getTenNhanVien());
		lblGiaTriNgayLap.setText(dtf2.format(thoiGianHT));
		lblGiaTriGioRa.setText(dtf2.format(LocalDateTime.now()));
		if (dsHoaDon.get(0).getKhachHang().getLoaiKhachHang().getTenLoaiKhachHang().equals("Vip")) {
			lblGiaTriChietKhau.setText(10 + " %");
			chietKhau = 10;
		} else {
			chietKhau = 0;
			lblGiaTriChietKhau.setText(0 + " %");
		}
		// thêm vào bảng
		docThongTinDSHD();
		docDSPhong();
		docDSDichVu();
		// tổng tiền thanh toán
		tinhTongTien();
	}

	private void tinhTongTien() {
		tongTien = tongTienDV + tongTienPhong;
//		System.err.println(tienPhong);
//		System.err.println(tienDV);
		tongTien = tongTien - chietKhau * tongTien / 100;
		lblGiaTriTongTienThanhToan.setText(df.format(tongTien));
	}

	public void tinhTienNhan() {
		if (txtGiaTriTienNhan.getText().trim().equals(""))
			tienNhan = 0;
		else
			tienNhan = Long.parseLong(txtGiaTriTienNhan.getText().trim());
		tienThua = tienNhan - tongTien;
		lblGiaTriTienThua.setText(df.format(tienThua));
		if (tienThua >= 0)
			lblGiaTriTienThua.setForeground(Color.green);
		else
			lblGiaTriTienThua.setForeground(Color.red);
	}

	private void docThongTinDSHD() throws RemoteException {
		dscthd_Phong = new ArrayList<ChiTietHoaDon_Phong>();
		dscthd_DV = new ArrayList<ChiTietHoaDon_DichVu>();
		dscthd_Phong.removeAll(dscthd_Phong);
		dscthd_DV.removeAll(dscthd_DV);
		model.setRowCount(0);
		LocalDateTime gioVao = thoiGianHT;
//		dsALL_CTHD_Phong = new ArrayList<ChiTietHoaDon_Phong>();
		dsALL_CTHD_Phong = dao_ChietTietHoaDon_Phong.getALLDanhSach();
		// thêm lần lượt các hoá đơn
		for (HoaDon hd : dsHoaDon) {
			for (ChiTietHoaDon_Phong cthd_P : dsALL_CTHD_Phong)
				if (cthd_P.getHoaDon().getMaHoaDon().equals(hd.getMaHoaDon())) {
					dscthd_Phong.add(cthd_P);
					gioVao = cthd_P.getGioVao();
				}
			dsALL_CTHD_DV = (ArrayList<ChiTietHoaDon_DichVu>) dao_ChietTietHoaDon_DichVu.getAllDanhSach();
			for (ChiTietHoaDon_DichVu cthd_DV : dsALL_CTHD_DV)
				if (cthd_DV.getHoaDon().getMaHoaDon().equals(hd.getMaHoaDon()))
					dscthd_DV.add(cthd_DV);
		}
		lblGiaTriGioVao.setText(dtf2.format(gioVao));
	}

	public void docDSDichVu() {
		for (int i = 0; i < dscthd_DV.size(); i++) {
			String tenDV = dscthd_DV.get(i).getDichVu().getTenDV();
			String soLuong = dscthd_DV.get(i).getSoLuong() + "";
			String giaDV = df.format(dscthd_DV.get(i).getDichVu().getGia());
			String phong = dscthd_DV.get(i).getPhong().getMaPhong();
			long thanhTienDV = dscthd_DV.get(i).tinhTienDichVu();
			String rowDV[] = { (dscthd_Phong.size() + 1 + i) + "", phong + "_" + tenDV, soLuong, giaDV,
					df.format(thanhTienDV) };
			model.addRow(rowDV);
			tongTienDV += dscthd_DV.get(i).getSoLuong() * dscthd_DV.get(i).getDichVu().getGia();
		}
		lblGiaTriTongTienDichVu.setText(df.format(tongTienDV));
	}

	public void docDSPhong() throws RemoteException {
		String thoiGian;
		docThongTinDSHD();
		for (int i = 0; i < dscthd_Phong.size(); i++) {
			if (dscthd_Phong.get(i).getGioRa() == null)
				dscthd_Phong.get(i).setGioRa(LocalDateTime.now());
			String tenPhong = dscthd_Phong.get(i).getPhong().getMaPhong() + " từ: "
					+ dtf3.format(dscthd_Phong.get(i).getGioVao()) + " đến: "
					+ dtf3.format(dscthd_Phong.get(i).getGioRa());
			int phut = dscthd_Phong.get(i).tinhSoPhut() % 60;
			int gio = (dscthd_Phong.get(i).tinhSoPhut() - phut) / 60;
			if (gio == 0) {
				thoiGian = phut + " phút";
			} else {
				if (phut == 0) {
					thoiGian = gio + " giờ";
				} else {
					thoiGian = gio + " giờ " + phut + " phút";
				}
			}
			String giaPhong = df.format(dscthd_Phong.get(i).getPhong().getLoaiPhong().getGia());
			long thanhTienPhong = dscthd_Phong.get(i).tinhTienPhong();

			String rowPhong[] = { (i + 1) + "", tenPhong, thoiGian, giaPhong, df.format(thanhTienPhong) };
			model.addRow(rowPhong);
			tongTienPhong += dscthd_Phong.get(i).tinhTienPhong();
		}
		// tính tổng phòng
		lblGiaTriTongTienPhong.setText(df.format(tongTienPhong));
	}

	public String layMaHDNhoNhat() {
		// lấy mã hoá đơn nhỏ nhất
		int dem = Integer.parseInt(dsHoaDon.get(0).getMaHoaDon().toString().trim().substring(2));
		for (int i = 1; i < dsHoaDon.size(); i++) {
			int bonSoCuoi = Integer.parseInt(dsHoaDon.get(i).getMaHoaDon().toString().trim().substring(2));
			if (bonSoCuoi < dem)
				dem = bonSoCuoi;
		}
		return String.format("HD%04d", dem);
	}

	public void inHoaDon() {
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport("report/HoaDon.jrxml");
			ArrayList<Map<String, String>> ds = new ArrayList<Map<String, String>>();

			Map<String, String> map = new HashMap<String, String>();
			map = new HashMap<String, String>();
			map.put("maHD", hdCapNhap.getMaHoaDon());
			map.put("ngayLap", dtf1.format(thoiGianHT));
			map.put("khachHang", hdCapNhap.getKhachHang().getTenKhachHang());
			map.put("gioVao", dtf2.format(hdCapNhap.getGioVao(dscthd_Phong)));
			map.put("gioRa", dtf2.format(hdCapNhap.getGioRa(dscthd_Phong)));
			map.put("nhanVien", hdCapNhap.getNhanVien().getTenNhanVien());

			String thoiGian;
			for (int i = 0; i < dscthd_Phong.size(); i++) {
				if (i != 0) {
					map = new HashMap<String, String>();
				}
				map.put("stt", (i + 1) + "");
				map.put("phong_DV",
						dscthd_Phong.get(i).getPhong().getMaPhong() + " từ: "
								+ dtf3.format(dscthd_Phong.get(i).getGioVao()) + " đến: "
								+ dtf3.format(dscthd_Phong.get(i).getGioRa()));
				int phut = dscthd_Phong.get(i).tinhSoPhut() % 60;
				int gio = (dscthd_Phong.get(i).tinhSoPhut() - phut) / 60;
				if (gio == 0) {
					thoiGian = phut + " phút";
				} else {
					if (phut == 0) {
						thoiGian = gio + " giờ";
					} else {
						thoiGian = gio + " giờ " + phut + " phút";
					}
				}
				map.put("tg_sl", thoiGian);
				map.put("gia", df.format(dscthd_Phong.get(i).getPhong().getLoaiPhong().getGia()));
				map.put("thanhTien", df.format(dscthd_Phong.get(i).tinhTienPhong()));
				ds.add(map);
			}
			for (int i = 0; i < dscthd_DV.size(); i++) {
				map = new HashMap<String, String>();
				map.put("stt", (dscthd_Phong.size() + 1) + "");
				map.put("phong_DV", dscthd_DV.get(i).getDichVu().getTenDV());
				map.put("tg_sl", dscthd_DV.get(i).getSoLuong() + "");
				map.put("gia", df.format(dscthd_DV.get(i).getDichVu().getGia()));
				map.put("thanhTien", df.format(dscthd_DV.get(i).tinhTienDichVu()));
				ds.add(map);
			}
			map.put("tongTienPhong", df.format(hdCapNhap.tinhTongTienPhong(dscthd_Phong)));
			map.put("tongTienDV", df.format(hdCapNhap.tinhTongTienDichVu(dscthd_DV)));
			map.put("chietKhau", hdCapNhap.getChietKhau() + "%");
			long tienThanhToan = hdCapNhap.tinhTienThanhToan(dscthd_Phong, dscthd_DV);
			map.put("tongTienThanhToan", df.format(tienThanhToan));
			long tienNhan = hdCapNhap.getTienNhan();
			map.put("tienNhan", df.format(tienNhan));
			long tienThua = tienNhan - tienThanhToan;
			map.put("tienThua", df.format(tienThua));

			JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
			JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e1) {
			e1.printStackTrace();
		}
	}

	private void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	private boolean luuThanhToan() throws RemoteException {
		String maHD = lblGiaTriMaHoaDon.getText().trim();
		// Lấy ds tên mã hoá đơn thuận tiện cho viẹc update
		// Vì trong sql chỉ lưu trữ 1 cột nên việc này dễ dàng hơn nhiều
		ArrayList<String> chuoiMaHD = new ArrayList<String>();
		for (HoaDon hD : dsHoaDon) {
			chuoiMaHD.add(hD.getMaHoaDon());
		}
		// Cập nhập mã hoá đơn mới cho chi tiết hoá đơn_ dịch vụ
		dao_ChietTietHoaDon_DichVu.thayDoiHoaDon(maHD, chuoiMaHD);

		// Cập nhập chi tiết hoá đơn_ phòng
		dao_ChietTietHoaDon_Phong.thayDoiHoaDon(thoiGianHT, maHD, chuoiMaHD);
		for (ChiTietHoaDon_Phong ct_Phong : dscthd_Phong) {
			ct_Phong.setGioRa(LocalDateTime.now());
			dao_ChietTietHoaDon_Phong.capNhap(ct_Phong);
		}

		// Cập nhập hoá đơn
		KhachHang khachHang = new KhachHang();
		for (KhachHang kh : dsALL_KH)
			if (kh.getSoDienThoai().equals(cbx_SDTKH.getSelectedItem().toString().trim())) {
				khachHang = kh;
				break;
			}
		hdCapNhap = new HoaDon(maHD, chietKhau, nhanVien, khachHang, tienNhan);
		if (!dao_HoaDon.capNhapHonDon(hdCapNhap))
			return false;
		// cập nhập trạng thái phòng
		for (ChiTietHoaDon_Phong ct_Phong : dscthd_Phong) {
			ct_Phong.getPhong().setTrangThaiPhong("Trống");
			for (PhieuDatPhong phieu : dsALL_PhieuDatP)
				if (phieu.getPhong().getMaPhong().equals(phieu.getPhong().getMaPhong()))
					ct_Phong.getPhong().setTrangThaiPhong("Chờ");
			dao_Phong.capNhapPhong(ct_Phong.getPhong());
		}
		// xoá hoá đơn cũ
		for (HoaDon hD : dsHoaDon) {
			if (!hD.getMaHoaDon().equals(hdCapNhap.getMaHoaDon()))
				dao_HoaDon.xoaHoaDonTheoMa(hD.getMaHoaDon());
		}
		// cập nhập điểm tích luỹ khách hàng
		int diemMoi = khachHang.getDiem() + (int) tongTien / 100000;
		khachHang.setDiem(diemMoi);
		if (diemMoi >= 5000) {
			// đạt đủ 5k điểm tích luỹ để lên khách Vip
			LoaiKhachHang khVipHang = new LoaiKhachHang("LKH1", "VIP");
			khachHang.setLoaiKhachHang(khVipHang);
			JOptionPane.showMessageDialog(this,
					"Khách " + khachHang.getTenKhachHang() + " đã trở thành khách Vip(trên 5000 điểm tích luỹ).");
		}
		dao_KhachHang.updateKhachHang(khachHang);
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(cbx_SDTKH)) {
			String sdt = cbx_SDTKH.getSelectedItem().toString().trim();
			for (KhachHang kh : dsALL_KH)
				if (kh.getSoDienThoai().equals(sdt)) {
					lblGiaTriTenKhach.setText(kh.getTenKhachHang());
					if (kh.getLoaiKhachHang().getTenLoaiKhachHang().equals("Vip")) {
						lblGiaTriChietKhau.setText(10 + " %");
						chietKhau = 10;
					} else {
						lblGiaTriChietKhau.setText(0 + " %");
						chietKhau = 0;
					}
					tinhTongTien();
					tinhTienNhan();
				}
		}
		if (o.equals(btnThanhToan)) {
			if (tienThua < 0) {
				thongBaoLoi("Số tiền khách đưa không đủ!", "Thanh toán");
				return;
			}
			try {
				if (!luuThanhToan()) {
					thongBaoLoi("Thanh toán không thành công", "Thanh toán");
					return;
				}
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}

			JOptionPane.showMessageDialog(this, "Thanh toán thành công! ", "Thanh toán",
					JOptionPane.INFORMATION_MESSAGE);
			// Làm mới danh sách phòng đang xử dụng
			try {
				FrmXuLyPhongDangSuDung.dsDLHienTai = dao_Phong.getDSTheoTTphong("Đang sử dụng");
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			FrmXuLyPhongDangSuDung.docDLVaoBang();
			FrmXuLyPhongDangSuDung.txt_SoDT.setSelectedItem("");
			FrmXuLyPhongDangSuDung.txt_MaKH.setText("");
			FrmXuLyPhongDangSuDung.txt_MaPhong.setText("");
			FrmXuLyPhongDangSuDung.txt_ThongTin.setText("");
			FrmXuLyPhongDangSuDung.modelHoaDon.setRowCount(0);
			FrmXuLyPhongDangSuDung.txt_ThongTinTong.setText("Bạn chưa chọn phòng thanh toán");
			FrmXuLyPhongDangSuDung.model_dSPhongDaChon.setRowCount(0);
			FrmXuLyPhongDangSuDung.dsHDThanhToan = new ArrayList<HoaDon>();
			FrmXuLyPhongDangSuDung.viTriNutDuocChon = -1;
			try {
				FrmXuLyPhongDangSuDung.themSDTKhachDDP();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			//
			if (checkInHoaDon.isSelected()) {
				inHoaDon();
			}
			dispose();// đóng from
		}
	}
}
