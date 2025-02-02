package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import dao.DaoChiTietHoaDon_DichVu;
import dao.DaoChiTietHoaDon_Phong;
import dao.DaoHoaDon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

//import dao.DAO_ChiTietHoaDon_DichVu;
//import dao.DAO_ChiTietHoaDon_Phong;
//import dao.DAO_HoaDon;
import entity.ChiTietHoaDon_DichVu;
import entity.ChiTietHoaDon_Phong;
import entity.HoaDon;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class FrmThongKeKhachHang extends JFrame implements ActionListener {

	private static final String URL = "rmi://26.173.247.75:7878/";

	DaoHoaDon dao_HoaDon = (DaoHoaDon) Naming.lookup(URL + "hoaDon");
	DaoChiTietHoaDon_Phong dao_ChietTietHoaDon_Phong = (DaoChiTietHoaDon_Phong) Naming.lookup(URL + "daoChiTietHoaDon_phong");
	DaoChiTietHoaDon_DichVu dao_ChiTietHoaDon_DichVu = (DaoChiTietHoaDon_DichVu) Naming.lookup(URL + "daoChiTietHoaDon_dichVu");
	private ArrayList<HoaDon> dsHoaDonDaThanhToan = dao_HoaDon.getHoaDonDaThanhToan();

//	private DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
//	private ArrayList<HoaDon> dsHoaDonDaThanhToan = dao_HoaDon.getHoaDonDaThanhToan();
//	private DAO_ChiTietHoaDon_Phong dao_ChietTietHoaDon_Phong = new DAO_ChiTietHoaDon_Phong();
//	private DAO_ChiTietHoaDon_DichVu dao_ChiTietHoaDon_DichVu = new DAO_ChiTietHoaDon_DichVu();

	public static JPanel pnlBorder;
	private JButton btnSoGioDenQuan;
	private JButton btnTongTienHoaDon;
	private Color color3;
	private Color color1;
	private JComboBox<String> cmbLuaChon;
	private ChartPanel chartPanel;
	private DecimalFormat df = new DecimalFormat("#,##0đ");
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy");

	private LocalDate ngayBatDau = LocalDate.now();
	private LocalDate ngayKetThuc = LocalDate.now();
	private JTable tblThongKe;
	private Box bThongKeTongQuat;
	private JLabel lblTongSo;
	private JLabel lblCaoNhat;
	private JLabel lblThapNhat;
	private JTextField txtTongDoanhThu;
	private JTextField txtTongSo;
	private JTextField txtCaoNhat;
	private JTextField txtThapNhat;
	private JButton btnBaoCao;
	private JButton btnXacNhan;
	private JDateChooser dchLuaChonThoiGian;
	private JLabel lblNhapLuaChon;

	public FrmThongKeKhachHang() throws MalformedURLException, NotBoundException, RemoteException {
		this.setTitle("Thống kê khách hàng");
		this.setSize(1920, 1040);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.createUI();
	}

	private void createUI() throws RemoteException {
		// Kiểu chữ
		Font font1 = new Font("Times New Roman", Font.BOLD, 36);
		Font font2 = new Font("Times New Roman", Font.PLAIN, 24);
		Font font3 = new Font("Times New Roman", Font.BOLD, 24);

		// Màu chữ
		color1 = new Color(138, 43, 226); // BlueViolet
		Color color2 = new Color(233, 221, 244);
		color3 = new Color(255, 153, 51);

		pnlBorder = new JPanel();
		pnlBorder.setBackground(color2);
		pnlBorder.setLayout(null);

		// Lựa chọn thời gian thống kê
		JPanel pnlThoiGian = new JPanel();
		pnlThoiGian.setLayout(null);
		pnlThoiGian.setBackground(Color.WHITE);
		pnlThoiGian.setBounds(20, 20, 350, 560);

		JPanel pnlTittleThoiGian = new JPanel();
		pnlTittleThoiGian.setBackground(color1);
		pnlTittleThoiGian.setBorder(new EmptyBorder(10, 0, 0, 0));
		pnlTittleThoiGian.setBounds(0, 0, 350, 60);
		JLabel lblTittleThoiGian = new JLabel("THỜI GIAN THỐNG KÊ");
		lblTittleThoiGian.setFont(font3);
		lblTittleThoiGian.setForeground(Color.WHITE);
		pnlTittleThoiGian.add(lblTittleThoiGian);

		JPanel pnlLuaChonThoiGian = new JPanel();
		pnlLuaChonThoiGian.setLayout(null);
		pnlLuaChonThoiGian.setBounds(0, 60, 350, 500);
		pnlLuaChonThoiGian.setBackground(Color.WHITE);

		JLabel lblLuaChon = new JLabel("Lựa chọn:");
		lblLuaChon.setBounds(50, 60, 250, 45);
		lblLuaChon.setFont(font2);
		lblNhapLuaChon = new JLabel("Chọn ngày:");
		lblNhapLuaChon.setBounds(50, 200, 250, 45);
		lblNhapLuaChon.setFont(font2);

		cmbLuaChon = new JComboBox<String>();
		cmbLuaChon.setBounds(50, 105, 250, 45);
		cmbLuaChon.addItem("Theo ngày");
		cmbLuaChon.addItem("Theo tháng");
		cmbLuaChon.addItem("Theo năm");
		cmbLuaChon.setFont(font2);
		dchLuaChonThoiGian = new JDateChooser();
		dchLuaChonThoiGian.setBounds(50, 245, 250, 45);
		dchLuaChonThoiGian.setFont(font2);
		dchLuaChonThoiGian.setDateFormatString("dd/MM/yyy");
		dchLuaChonThoiGian.setDate(new Date());
		btnXacNhan = new JButton("Xác nhận");
		btnXacNhan.setBounds(100, 365, 150, 45);
		btnXacNhan.setFont(font3);
		btnXacNhan.setBackground(color1);
		btnXacNhan.setForeground(Color.WHITE);

		pnlLuaChonThoiGian.add(lblLuaChon);
		pnlLuaChonThoiGian.add(cmbLuaChon);
		pnlLuaChonThoiGian.add(lblNhapLuaChon);
		pnlLuaChonThoiGian.add(dchLuaChonThoiGian);
		pnlLuaChonThoiGian.add(btnXacNhan);

		pnlThoiGian.add(pnlTittleThoiGian);
		pnlThoiGian.add(pnlLuaChonThoiGian);

		// Chọn mục thống kê
		JPanel pnlMucThonKe = new JPanel();
		pnlMucThonKe.setLayout(null);
		pnlMucThonKe.setBackground(Color.WHITE);
		pnlMucThonKe.setBounds(20, 600, 350, 318);

		JPanel pnlTittleMucThongKe = new JPanel();
		pnlTittleMucThongKe.setBackground(color1);
		pnlTittleMucThongKe.setBorder(new EmptyBorder(10, 0, 0, 0));
		pnlTittleMucThongKe.setBounds(0, 0, 350, 60);
		JLabel lblTittleMucThongKe = new JLabel("MỤC THỐNG KÊ");
		lblTittleMucThongKe.setFont(font3);
		lblTittleMucThongKe.setForeground(Color.WHITE);
		pnlTittleMucThongKe.add(lblTittleMucThongKe);

		JPanel pnlChonMucThongKe = new JPanel();
		pnlChonMucThongKe.setBounds(0, 60, 350, 255);
		pnlChonMucThongKe.setBackground(Color.WHITE);
		pnlChonMucThongKe.setLayout(null);
		btnSoGioDenQuan = new JButton("Số giờ đến quán");
		btnSoGioDenQuan.setBounds(50, 60, 250, 50);
		btnSoGioDenQuan.setFont(font3);
		btnSoGioDenQuan.setBackground(color3);
		btnSoGioDenQuan.setForeground(Color.WHITE);
		btnTongTienHoaDon = new JButton("Tổng tiền hóa đơn");
		btnTongTienHoaDon.setFont(font3);
		btnTongTienHoaDon.setBounds(50, 130, 250, 50);
		btnTongTienHoaDon.setBackground(Color.WHITE);
		btnTongTienHoaDon.setForeground(color1);
		pnlChonMucThongKe.add(btnSoGioDenQuan);
		pnlChonMucThongKe.add(btnTongTienHoaDon);

		pnlMucThonKe.add(pnlTittleMucThongKe);
		pnlMucThonKe.add(pnlChonMucThongKe);

		JPanel pnlThongKeTongQuat = new JPanel();
		pnlThongKeTongQuat.setBounds(1510, 20, 390, 560);
		pnlThongKeTongQuat.setBackground(Color.WHITE);
		pnlThongKeTongQuat.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(color1),
				"THỐNG KÊ TỔNG QUÁT", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font3, color1));

		JPanel pnlTable = new JPanel();
		pnlTable.setLayout(new BorderLayout());
		pnlTable.setBounds(390, 600, 1510, 318);
		pnlTable.setBackground(Color.WHITE);
		pnlTable.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(color1), "BẢNG THỐNG KÊ",
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font3, color1));
		tblThongKe = new JTable();
		JTableHeader tbhThongKe = tblThongKe.getTableHeader();
		tbhThongKe.setFont(font2);
		tblThongKe.setFont(font2);
		tblThongKe.setRowHeight(35);
		tblThongKe.setDefaultEditor(Object.class, null);
		// tblThongKe.setAutoCreateRowSorter(true);
		JScrollPane scr = new JScrollPane(tblThongKe, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnlTable.add(scr);

		JPanel pnlNDThongKeTongQuat = new JPanel();
		pnlNDThongKeTongQuat.setLayout(new BorderLayout());
		pnlNDThongKeTongQuat.setBorder(new EmptyBorder(50, 10, 50, 10));
		pnlNDThongKeTongQuat.setBackground(Color.WHITE);

		bThongKeTongQuat = Box.createVerticalBox();
		Box bLblTongSo = Box.createHorizontalBox();
		Box bTxtTongSo = Box.createHorizontalBox();
		Box bLblCaoNhat = Box.createHorizontalBox();
		Box bTxtCaoNhat = Box.createHorizontalBox();
		Box bLblThapNhat = Box.createHorizontalBox();
		Box bTxtThapNhat = Box.createHorizontalBox();
		Box bBaoCao = Box.createHorizontalBox();

		lblTongSo = new JLabel();
		lblTongSo.setFont(font2);
		lblCaoNhat = new JLabel();
		lblCaoNhat.setFont(font2);
		lblThapNhat = new JLabel();
		lblThapNhat.setFont(font2);

		txtTongSo = new JTextField(14);
		txtTongSo.setFont(font2);
		txtTongSo.setFont(font2);
		txtTongSo.setBackground(color2);
		txtTongSo.setEditable(false);

		txtCaoNhat = new JTextField(14);
		txtCaoNhat.setFont(font2);
		txtCaoNhat.setFont(font2);
		txtCaoNhat.setBackground(color2);
		txtCaoNhat.setEditable(false);

		txtThapNhat = new JTextField(14);
		txtThapNhat.setFont(font2);
		txtThapNhat.setFont(font2);
		txtThapNhat.setBackground(color2);
		txtThapNhat.setEditable(false);

		btnBaoCao = new JButton("Báo cáo");
		btnBaoCao.setBackground(color1);
		btnBaoCao.setForeground(Color.WHITE);
		btnBaoCao.setFont(font3);

		bLblTongSo.add(lblTongSo);
		bLblTongSo.add(Box.createVerticalStrut(0));
		bTxtTongSo.add(txtTongSo);
		bLblCaoNhat.add(lblCaoNhat);
		bLblCaoNhat.add(Box.createVerticalStrut(0));
		bTxtCaoNhat.add(txtCaoNhat);
		bLblThapNhat.add(lblThapNhat);
		bLblThapNhat.add(Box.createVerticalStrut(0));
		bTxtThapNhat.add(txtThapNhat);
		bBaoCao.add(btnBaoCao);

		bThongKeTongQuat.add(bLblTongSo);
		bThongKeTongQuat.add(Box.createVerticalStrut(3));
		bThongKeTongQuat.add(bTxtTongSo);
		bThongKeTongQuat.add(Box.createVerticalStrut(20));
		bThongKeTongQuat.add(bLblCaoNhat);
		bThongKeTongQuat.add(Box.createVerticalStrut(3));
		bThongKeTongQuat.add(bTxtCaoNhat);
		bThongKeTongQuat.add(Box.createVerticalStrut(20));
		bThongKeTongQuat.add(bLblThapNhat);
		bThongKeTongQuat.add(Box.createVerticalStrut(3));
		bThongKeTongQuat.add(bTxtThapNhat);
		bThongKeTongQuat.add(Box.createVerticalStrut(20));
		bThongKeTongQuat.add(bBaoCao);

		pnlNDThongKeTongQuat.add(bThongKeTongQuat);
		pnlThongKeTongQuat.add(pnlNDThongKeTongQuat);

		chartPanel = new ChartPanel(createChart(null, "", "", ""));
		chartPanel.setBounds(390, 20, 1100, 560);

		pnlBorder.add(pnlThoiGian);
		pnlBorder.add(pnlMucThonKe);
		pnlBorder.add(chartPanel);
		pnlBorder.add(pnlThongKeTongQuat);
		pnlBorder.add(pnlTable);
		this.add(pnlBorder);

		showData();

		cmbLuaChon.addActionListener(this);
		btnSoGioDenQuan.addActionListener(this);
		btnTongTienHoaDon.addActionListener(this);
		btnBaoCao.addActionListener(this);
		btnXacNhan.addActionListener(this);
	}

	/**
	 * Gộp những khách hàng bị trùng trong danh sách thống kê khách hàng theo số giờ
	 * đến quán
	 * 
	 * @param ds: danh sách thống kê khách hàng theo số giờ đến quán lấy theo ngày.
	 * @return danh sách thống kê khách hàng theo số giờ đến quán không còn trùng
	 *         khách hàng.
	 */
	private ArrayList<ArrayList<String>> gopDuLieuTrungDSTheoSoGioDenQuan(ArrayList<ArrayList<String>> ds) {
		for (int i = 0; i < ds.size() - 1; i++) {
			double soGio = Double.parseDouble(ds.get(i).get(4));
			for (int j = i + 1; j < ds.size(); j++) {

				// Kiểm tra nếu bị trùng thì cộng dồn số phút đến quán và xóa phần tử trùng
				if (ds.get(i).get(0).equals(ds.get(j).get(0))) {
					soGio += Double.parseDouble(ds.get(j).get(4));
					ds.remove(j);
					j--;
				}
			}
			ds.get(i).set(4, (double) Math.round(soGio * 1000) / 1000 + "");
		}
		return ds;
	}

	/**
	 * Gộp những khách hàng bị trùng trong danh sách thống kê khách hàng theo tổng
	 * tiền hóa đơn.
	 * 
	 * @param ds: danh sách thống kê khách hàng theo tổng tiền hóa đơn lấy theo
	 *            ngày.
	 * @return danh sách thống kê khách hàng theo tổng tiền hóa đơn không còn trùng
	 *         khách hàng.
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	private ArrayList<ArrayList<String>> gopDuLieuTrungDSTheoTongTienHoaDon(ArrayList<ArrayList<String>> ds)
			throws NumberFormatException, ParseException {
		for (int i = 0; i < ds.size() - 1; i++) {
			double tongTienHoaDon = Double.parseDouble(df.parse(ds.get(i).get(4)).toString());
			for (int j = i + 1; j < ds.size(); j++) {

				// Kiểm tra nếu bị trùng thì cộng dồn tổng tiền hóa đơn và xóa phần tử trùng
				if (ds.get(i).get(0).equals(ds.get(j).get(0))) {
					tongTienHoaDon += Double.parseDouble(df.parse(ds.get(j).get(4)).toString());
					ds.remove(j);
					j--;
				}
			}
			ds.get(i).set(4, df.format(tongTienHoaDon));
		}
		return ds;
	}

	/**
	 * Sắp xếp thống kê khách hàng tăng dần theo mã khách hàng
	 * 
	 * @param ds: danh sách thống kê khách hàng theo ngày đã gộp dữ liệu trùng
	 * @return danh sách thống kê khách hàng đã sắp xếp
	 */
	private ArrayList<ArrayList<String>> sapXepTKKhachHangTangTheoMa(ArrayList<ArrayList<String>> ds) {
		ArrayList<ArrayList<String>> ketQua = new ArrayList<ArrayList<String>>();
		int soPhanTu = ds.size();
		if (soPhanTu > 0)
			for (int i = 0; i < soPhanTu; i++) {
				String maNhoNhat = ds.get(0).get(0);
				int rowNhoNhat = 0;
				for (int j = 0; j < ds.size(); j++) {
					String ma = ds.get(j).get(0);
					if (maNhoNhat.compareTo(ma) > 0) {
						maNhoNhat = ma;
						rowNhoNhat = j;
					}
				}
				ketQua.add(ds.get(rowNhoNhat));
				ds.remove(rowNhoNhat);
			}
		return ketQua;
	}

	/**
	 * Lấy danh sách thống kê khách hàng theo số giờ đến quán từ ngày bắt đầu đến
	 * ngày kết thúc.
	 * 
	 * @param dsHoaDonDaThanhToan
	 * @return Danh sách khách hàng theo số giờ đến quán
	 */
	private ArrayList<ArrayList<String>> getTKKhachHangTheoSoGioDenQuan(ArrayList<HoaDon> dsHoaDonDaThanhToan) throws RemoteException {
		ArrayList<ArrayList<String>> ketQua = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < dsHoaDonDaThanhToan.size(); i++) {
			ArrayList<ChiTietHoaDon_Phong> dsPhong = dao_ChietTietHoaDon_Phong
					.getDSTheoMaHD(dsHoaDonDaThanhToan.get(i).getMaHoaDon());
			LocalDate ngayLap = dsHoaDonDaThanhToan.get(i).getGioVao(dsPhong).toLocalDate();
			if (ngayLap.compareTo(ngayBatDau) >= 0 && ngayLap.compareTo(ngayKetThuc) <= 0) {
				ArrayList<String> thongKeKhachHang = new ArrayList<String>();
				thongKeKhachHang.add(dsHoaDonDaThanhToan.get(i).getKhachHang().getMaKhachHang());
				thongKeKhachHang.add(dsHoaDonDaThanhToan.get(i).getKhachHang().getTenKhachHang());
				thongKeKhachHang.add(dsHoaDonDaThanhToan.get(i).getKhachHang().getSoDienThoai());
				thongKeKhachHang
						.add(dsHoaDonDaThanhToan.get(i).getKhachHang().getLoaiKhachHang().getTenLoaiKhachHang());
				int soPhut = 0;
				for (int j = 0; j < dsPhong.size(); j++) {
					soPhut += dsPhong.get(j).tinhSoPhut();
				}
				thongKeKhachHang.add((double) Math.round((double) soPhut / 60 * 1000) / 1000 + "");
				ketQua.add(thongKeKhachHang);
			}
		}
		return sapXepTKKhachHangTangTheoMa(gopDuLieuTrungDSTheoSoGioDenQuan(ketQua));
	}

	/**
	 * Lấy danh sách thống kê khách hàng theo tổng tiền hóa đơn từ ngày bắt đầu đến
	 * ngày kết thúc.
	 * 
	 * @param dsHoaDonDaThanhToan
	 * @return Danh sách khách hàng theo tổng tiền hóa đơn
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	private ArrayList<ArrayList<String>> getTKKhachHangTheoTongTienHoaDon(ArrayList<HoaDon> dsHoaDonDaThanhToan)
			throws NumberFormatException, ParseException, RemoteException {
		ArrayList<ArrayList<String>> ketQua = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < dsHoaDonDaThanhToan.size(); i++) {
			ArrayList<ChiTietHoaDon_Phong> dsPhong = dao_ChietTietHoaDon_Phong
					.getDSTheoMaHD(dsHoaDonDaThanhToan.get(i).getMaHoaDon());
			LocalDate ngayLap = dsHoaDonDaThanhToan.get(i).getGioVao(dsPhong).toLocalDate();
			if (ngayLap.compareTo(ngayBatDau) >= 0 && ngayLap.compareTo(ngayKetThuc) <= 0) {
				ArrayList<String> thongKeKhachHang = new ArrayList<String>();
				ArrayList<ChiTietHoaDon_DichVu> dsDichVu = (ArrayList<ChiTietHoaDon_DichVu>) dao_ChiTietHoaDon_DichVu
						.getDSTheoMaHD(dsHoaDonDaThanhToan.get(i).getMaHoaDon());
				thongKeKhachHang.add(dsHoaDonDaThanhToan.get(i).getKhachHang().getMaKhachHang());
				thongKeKhachHang.add(dsHoaDonDaThanhToan.get(i).getKhachHang().getTenKhachHang());
				thongKeKhachHang.add(dsHoaDonDaThanhToan.get(i).getKhachHang().getSoDienThoai());
				thongKeKhachHang
						.add(dsHoaDonDaThanhToan.get(i).getKhachHang().getLoaiKhachHang().getTenLoaiKhachHang());
				thongKeKhachHang.add(df.format(dsHoaDonDaThanhToan.get(i).tinhTienThanhToan(dsPhong, dsDichVu)));
				ketQua.add(thongKeKhachHang);
			}
		}
		return sapXepTKKhachHangTangTheoMa(gopDuLieuTrungDSTheoTongTienHoaDon(ketQua));
	}

	/**
	 * Tạo một model với các cột truyền vào
	 * 
	 * @param columns: ds các cột muốn tạo trong model
	 * @return model đã tạo
	 */
	private DefaultTableModel taoModel(String columns[]) {
		DefaultTableModel model = new DefaultTableModel();
		for (int i = 0; i < columns.length; i++) {
			model.addColumn(columns[i]);
		}
		return model;
	}

	/**
	 * Load dữ liệu từ danh sách đưa vào lên model
	 * 
	 * @param : ds các cột muốn tạo trong model
	 * @param ds:     cần load
	 * @return model đã load dữ liệu
	 */
	private DefaultTableModel loadDataModel(String columns[], ArrayList<ArrayList<String>> ds) {
		DefaultTableModel model = taoModel(columns);
		for (int i = 0; i < ds.size(); i++) {
			ds.get(i).add(0, (i + 1) + "");
			model.addRow(ds.get(i).toArray());
		}
		return model;
	}

	private void setDataTable(DefaultTableModel model) {
		tblThongKe.setModel(model);
		if (model.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Chưa có dữ liệu!");
		}
	}

	/**
	 * tạo một biểu đồ cột
	 * 
	 * @param dataset:           dữ liệu của biểu đồ
	 * @param title:             tiêu đề của biểu đồ
	 * @param categoryAxisLabel: tên của các giá trị trên trục x
	 * @param valueAxisLable:    tên của các giá trị trên trục y
	 * @return biểu đồ đã tạo
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	private JFreeChart createChart(CategoryDataset dataset, String title, String categoryAxisLabel,
			String valueAxisLable) {
		JFreeChart barChart = ChartFactory.createBarChart(title, categoryAxisLabel, valueAxisLable, dataset,
				PlotOrientation.VERTICAL, false, false, false);
		return barChart;
	}

	private ArrayList<ArrayList<String>> getMuoiKhachHangSoGioDenQuanCaoNhat(ArrayList<ArrayList<String>> ds) {
		ArrayList<ArrayList<String>> ketQua = new ArrayList<ArrayList<String>>();
		int soLuong = 0;
		int soPhanTu = ds.size();
		if (soPhanTu > 0)
			while (soLuong < 10 && soLuong < soPhanTu) {
				double soGioDenLonNhat = Double.parseDouble(ds.get(0).get(4));
				int rowCaoNhat = 0;
				for (int i = 0; i < ds.size(); i++) {
					double soGioDen = Double.parseDouble(ds.get(i).get(4));
					if (soGioDenLonNhat < soGioDen) {
						soGioDenLonNhat = soGioDen;
						rowCaoNhat = i;
					}
				}
				ketQua.add(ds.get(rowCaoNhat));
				ds.remove(rowCaoNhat);
				soLuong++;
			}
		return ketQua;
	}

	private ArrayList<ArrayList<String>> getMuoiKhachHangTongTienHoaDonCaoNhat(ArrayList<ArrayList<String>> ds)
			throws NumberFormatException, ParseException {
		ArrayList<ArrayList<String>> ketQua = new ArrayList<ArrayList<String>>();
		int soLuong = 0;
		int soPhanTu = ds.size();
		if (soPhanTu > 0)
			while (soLuong < 10 && soLuong < soPhanTu) {
				double giaTriLonNhat = Double.parseDouble(df.parse(ds.get(0).get(4)).toString());
				int rowCaoNhat = 0;
				for (int i = 0; i < ds.size(); i++) {
					double giaTri = Double.parseDouble(df.parse(ds.get(i).get(4)).toString());
					if (giaTriLonNhat < giaTri) {
						giaTriLonNhat = giaTri;
						rowCaoNhat = i;
					}
				}
				ketQua.add(ds.get(rowCaoNhat));
				ds.remove(rowCaoNhat);
				soLuong++;
			}
		return ketQua;
	}

	private CategoryDataset createDatasetSoGioDenQuan(ArrayList<ArrayList<String>> ds) {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<ArrayList<String>> dsMuoiPhanTuCaoNhat = getMuoiKhachHangSoGioDenQuanCaoNhat(ds);
		for (int i = 0; i < dsMuoiPhanTuCaoNhat.size(); i++) {
			double soGio = Double.parseDouble(dsMuoiPhanTuCaoNhat.get(i).get(4));
			dataset.addValue(soGio, "Số giờ đến quán", dsMuoiPhanTuCaoNhat.get(i).get(0));
		}
		return dataset;
	}

	private CategoryDataset createDatasetTongTienHoaDon(ArrayList<ArrayList<String>> ds)
			throws NumberFormatException, ParseException {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<ArrayList<String>> dsMuoiPhanTuCaoNhat = getMuoiKhachHangTongTienHoaDonCaoNhat(ds);
		for (int i = 0; i < dsMuoiPhanTuCaoNhat.size(); i++) {
			double tongTien = Double.parseDouble(df.parse(dsMuoiPhanTuCaoNhat.get(i).get(4)).toString());
			dataset.addValue(tongTien, "Tổng tiền hóa đơn", dsMuoiPhanTuCaoNhat.get(i).get(0));
		}
		return dataset;
	}

	private void setDataChartSoGioDenQuan() throws RemoteException {
		chartPanel.setChart(createChart(createDatasetSoGioDenQuan(getTKKhachHangTheoSoGioDenQuan(dsHoaDonDaThanhToan)),
				"BIỂU ĐỒ TOP 10 KHÁCH HÀNG CÓ SỐ GIỜ ĐẾN QUÁN CAO NHẤT", "Khách hàng", "Số giờ đến quán"));
	}

	private void setDataChartTongTienHoaDon() throws NumberFormatException, ParseException, RemoteException {
		chartPanel.setChart(
				createChart(createDatasetTongTienHoaDon(getTKKhachHangTheoTongTienHoaDon(dsHoaDonDaThanhToan)),
						"BIỂU ĐỒ TOP 10 KHÁCH HÀNG CÓ TỔNG TIỀN HÓA ĐƠN CAO NHẤT", "Khách hàng", "Tổng tiền hóa đơn"));
	}

	@SuppressWarnings("deprecation")
	private void setDataThongKeTongQuatSoGioDenQuan(DefaultTableModel model) {
		if (model.getRowCount() == 0) {
			bThongKeTongQuat.hide();
		} else {
			double giaTriCaoNhat = Double.parseDouble(model.getValueAt(0, 5).toString());
			double giaTriThapNhat = giaTriCaoNhat;
			int rowCaoNhat = 0;
			int rowThapNhat = 0;
			for (int i = 0; i < model.getRowCount(); i++) {
				double giaTri = Double.parseDouble(model.getValueAt(i, 5).toString());
				if (giaTriCaoNhat < giaTri) {
					giaTriCaoNhat = giaTri;
					rowCaoNhat = i;
				}
				if (giaTriThapNhat > giaTri) {
					giaTriThapNhat = giaTri;
					rowThapNhat = i;
				}
			}
			lblTongSo.setText("Tổng số khách hàng:");
			lblCaoNhat.setText("Giờ đến quán cao nhất:");
			lblThapNhat.setText("Giờ đến quán thấp nhất:");

			txtTongSo.setText(model.getRowCount() + "");
			txtCaoNhat.setText(model.getValueAt(rowCaoNhat, 1).toString());
			txtThapNhat.setText(model.getValueAt(rowThapNhat, 1).toString());

			bThongKeTongQuat.show();
		}
	}

	private void setDataThongKeTongQuatTongTienHoaDon(DefaultTableModel model)
			throws NumberFormatException, ParseException {
		if (model.getRowCount() == 0) {
			bThongKeTongQuat.hide();
		} else {
			double giaTriCaoNhat = Double.parseDouble(df.parse(model.getValueAt(0, 5).toString()).toString());
			double giaTriThapNhat = Double.parseDouble(df.parse(model.getValueAt(0, 5).toString()).toString());
			int rowCaoNhat = 0;
			int rowThapNhat = 0;
			for (int i = 0; i < model.getRowCount(); i++) {
				double giaTri = Double.parseDouble(df.parse(model.getValueAt(i, 5).toString()).toString());
				if (giaTriCaoNhat < giaTri) {
					giaTriCaoNhat = giaTri;
					rowCaoNhat = i;
				}
				if (giaTriThapNhat > giaTri) {
					giaTriThapNhat = giaTri;
					rowThapNhat = i;
				}
			}
			lblTongSo.setText("Tổng số khách hàng:");
			lblCaoNhat.setText("Tổng tiền hóa đơn cao nhất:");
			lblThapNhat.setText("Tổng tiền hóa đơn thấp nhất:");

			txtTongSo.setText(model.getRowCount() + "");
			txtCaoNhat.setText(model.getValueAt(rowCaoNhat, 1).toString());
			txtThapNhat.setText(model.getValueAt(rowThapNhat, 1).toString());

			bThongKeTongQuat.show();
		}
	}

	private void setAllData() throws RemoteException {
		if (btnSoGioDenQuan.getBackground().equals(color3)) {
			String colums[] = { "STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Loại khách hàng",
					"Số giờ đến quán" };
			DefaultTableModel model = loadDataModel(colums, getTKKhachHangTheoSoGioDenQuan(dsHoaDonDaThanhToan));
			setDataTable(model);
			setDataThongKeTongQuatSoGioDenQuan(model);
			setDataChartSoGioDenQuan();
		} else {
			if (btnTongTienHoaDon.getBackground().equals(color3)) {
				String colums[] = { "STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Loại khách hàng",
						"Tổng tiền hóa đơn" };
				try {
					DefaultTableModel model = loadDataModel(colums,
							getTKKhachHangTheoTongTienHoaDon(dsHoaDonDaThanhToan));
					setDataTable(model);
					setDataThongKeTongQuatTongTienHoaDon(model);
					setDataChartTongTienHoaDon();
				} catch (NumberFormatException | ParseException e) {
					e.printStackTrace();
				}

			}
		}
	}

	private void setMauButton(JButton btn) {
		btn.setBackground(color3);
		btn.setForeground(Color.WHITE);
		if (!btn.equals(btnSoGioDenQuan)) {
			btnSoGioDenQuan.setBackground(Color.WHITE);
			btnSoGioDenQuan.setForeground(color1);
		}
		if (!btn.equals(btnTongTienHoaDon)) {
			btnTongTienHoaDon.setBackground(Color.WHITE);
			btnTongTienHoaDon.setForeground(color1);
		}
	}

	private void showData() throws RemoteException {
		LocalDate ngayLuaChon = dchLuaChonThoiGian.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate ngayHienTai = LocalDate.now();
		if (cmbLuaChon.getSelectedItem().equals("Theo ngày")) {
			ngayBatDau = ngayKetThuc = ngayLuaChon;
			setAllData();
		} else {
			if (cmbLuaChon.getSelectedItem().equals("Theo tháng")) {

				// Nếu người dùng chọn tháng hiện tại thì chỉ get tới ngày hiện tại, ngược lại
				// thì get hết các ngày của tháng được chọn
				if (ngayLuaChon.getMonthValue() == ngayHienTai.getMonthValue()
						&& ngayLuaChon.getYear() == ngayHienTai.getYear()) {
					ngayBatDau = LocalDate.of(ngayLuaChon.getYear(), ngayLuaChon.getMonthValue(), 1);
					ngayKetThuc = ngayHienTai;
				} else {
					ngayBatDau = LocalDate.of(ngayLuaChon.getYear(), ngayLuaChon.getMonthValue(), 1);
					ngayKetThuc = LocalDate.of(ngayLuaChon.getYear(), ngayLuaChon.getMonthValue(),
							ngayLuaChon.lengthOfMonth());
				}
				setAllData();
			} else {
				if (ngayLuaChon.getYear() == ngayHienTai.getYear()) {
					ngayBatDau = LocalDate.of(ngayLuaChon.getYear(), ngayLuaChon.getMonthValue(), 1);
					ngayKetThuc = ngayHienTai;
				} else {
					ngayBatDau = LocalDate.of(ngayLuaChon.getYear(), 1, 1);
					ngayKetThuc = LocalDate.of(ngayLuaChon.getYear(), 12, 31);
				}
				setAllData();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(cmbLuaChon)) {
			if (cmbLuaChon.getSelectedItem().equals("Theo ngày")) {
				lblNhapLuaChon.setText("Chọn ngày:");
				dchLuaChonThoiGian.setDate(null);
				dchLuaChonThoiGian.setDateFormatString("dd/MM/yyyy");
			}
			if (cmbLuaChon.getSelectedItem().equals("Theo tháng")) {
				lblNhapLuaChon.setText("Chọn tháng:");
				dchLuaChonThoiGian.setDate(null);
				dchLuaChonThoiGian.setDateFormatString("MM/yyyy");
			}
			if (cmbLuaChon.getSelectedItem().equals("Theo năm")) {
				lblNhapLuaChon.setText("Chọn năm:");
				dchLuaChonThoiGian.setDate(null);
				dchLuaChonThoiGian.setDateFormatString("yyyy");
			}
		}
		if (o.equals(btnXacNhan)) {
			if (dchLuaChonThoiGian.getDate() == null) {
				if (cmbLuaChon.getSelectedItem().equals("Theo ngày")) {
					JOptionPane.showMessageDialog(this, "Bạn chưa chọn ngày thống kê!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				} else {
					if (cmbLuaChon.getSelectedItem().equals("Theo tháng")) {
						JOptionPane.showMessageDialog(this, "Bạn chưa chọn tháng thống kê!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(this, "Bạn chưa chọn năm thống kê!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} else {
				try {
					showData();
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			}
		}
		if (o.equals(btnSoGioDenQuan)) {
			setMauButton(btnSoGioDenQuan);
			try {
				showData();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if (o.equals(btnTongTienHoaDon)) {
			setMauButton(btnTongTienHoaDon);
			try {
				showData();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if (o.equals(btnBaoCao)) {
			if (btnSoGioDenQuan.getBackground().equals(color3)) {
				try {
					ArrayList<ArrayList<String>> dsLoad = getTKKhachHangTheoSoGioDenQuan(dsHoaDonDaThanhToan);
					JasperReport jasperReport = JasperCompileManager
							.compileReport("report/ThongKeSoGioDenKhachHang.jrxml");
					ArrayList<Map<String, String>> ds = new ArrayList<Map<String, String>>();
					Map<String, String> map = new HashMap<String, String>();
					map.put("ngayBatDau", dtf.format(ngayBatDau));
					map.put("ngayKetThuc", dtf.format(ngayKetThuc));
					int tongSoKH = 0;
					for (int i = 0; i < dsLoad.size(); i++) {
						if (i != 0) {
							map = new HashMap<String, String>();
						}
						map.put("stt", (i + 1) + "");
						map.put("maKH", dsLoad.get(i).get(0));
						map.put("tenKH", dsLoad.get(i).get(1));
						map.put("sdt", dsLoad.get(i).get(2));
						map.put("loaiKH", dsLoad.get(i).get(3));
						map.put("gioDenQuan", dsLoad.get(i).get(4));
						ds.add(map);
						tongSoKH++;
					}
					map.put("tongSoKH", tongSoKH + "");
					map.put("ngayLapTK", dtf.format(LocalDate.now()));
					JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
					JasperViewer.viewReport(jasperPrint, false);
				} catch (JRException e2) {
					e2.printStackTrace();
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			}
			if (btnTongTienHoaDon.getBackground().equals(color3)) {
				try {
					JasperReport jasperReport = JasperCompileManager
							.compileReport("report/ThongKeTongTienHoaDonKhachHang.jrxml");
					ArrayList<Map<String, String>> ds = new ArrayList<Map<String, String>>();
					Map<String, String> map = new HashMap<String, String>();
					map.put("ngayBatDau", dtf.format(ngayBatDau));
					map.put("ngayKetThuc", dtf.format(ngayKetThuc));
					int tongSoKH = 0;
					try {
						ArrayList<ArrayList<String>> dsLoad = getTKKhachHangTheoTongTienHoaDon(dsHoaDonDaThanhToan);
						for (int i = 0; i < dsLoad.size(); i++) {
							if (i != 0) {
								map = new HashMap<String, String>();
							}
							map.put("stt", (i + 1) + "");
							map.put("maKH", dsLoad.get(i).get(0));
							map.put("tenKH", dsLoad.get(i).get(1));
							map.put("sdt", dsLoad.get(i).get(2));
							map.put("loaiKH", dsLoad.get(i).get(3));
							map.put("tongTienHD", dsLoad.get(i).get(4));
							ds.add(map);
							tongSoKH++;
						}
					} catch (NumberFormatException | ParseException e1) {
						e1.printStackTrace();
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
					map.put("tongSoKH", tongSoKH + "");
					map.put("ngayLapTK", dtf.format(LocalDate.now()));
					JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
					JasperViewer.viewReport(jasperPrint, false);
				} catch (JRException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
