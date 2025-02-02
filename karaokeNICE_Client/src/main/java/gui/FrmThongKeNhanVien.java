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
import dao.DaoPhieuDatPhong;
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
//import dao.DAO_PhieuDatPhong;
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

public class FrmThongKeNhanVien extends JFrame implements ActionListener {
	private static final String URL = "rmi://26.173.247.75:7878/";

	DaoHoaDon dao_HoaDon = (DaoHoaDon) Naming.lookup(URL + "hoaDon");
	DaoChiTietHoaDon_Phong dao_ChietTietHoaDon_Phong = (DaoChiTietHoaDon_Phong) Naming.lookup(URL + "daoChiTietHoaDon_phong");
	DaoChiTietHoaDon_DichVu dao_ChiTietHoaDon_DichVu = (DaoChiTietHoaDon_DichVu) Naming.lookup(URL + "daoChiTietHoaDon_dichVu");
	DaoPhieuDatPhong dao_PhieuDatPhong = (DaoPhieuDatPhong) Naming.lookup(URL + "daoPhieuDatPhong");

	private ArrayList<HoaDon> dsHoaDonDaThanhToan = dao_HoaDon.getHoaDonDaThanhToan();

//	private DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
//	private ArrayList<HoaDon> dsHoaDonDaThanhToan = dao_HoaDon.getHoaDonDaThanhToan();
//	private DAO_ChiTietHoaDon_Phong dao_ChietTietHoaDon_Phong = new DAO_ChiTietHoaDon_Phong();
//	private DAO_ChiTietHoaDon_DichVu dao_ChiTietHoaDon_DichVu = new DAO_ChiTietHoaDon_DichVu();
//	private DAO_PhieuDatPhong dao_PhieuDatPhong = new DAO_PhieuDatPhong();

	public static JPanel pnlBorder;
	private JButton btnPhieuDatPhong;
	private JButton btnHoaDon;
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
	private JLabel lblTongSoPhieuDatPhong;
	private JLabel lblTongSo;
	private JLabel lblCaoNhat;
	private JLabel lblThapNhat;
	private JTextField txtTongSoPhieuDatPhong;
	private JTextField txtTongSo;
	private JTextField txtCaoNhat;
	private JTextField txtThapNhat;
	private JButton btnBaoCao;
	private JButton btnXacNhan;
	private JDateChooser dchLuaChonThoiGian;
	private JLabel lblNhapLuaChon;

	public FrmThongKeNhanVien() throws MalformedURLException, NotBoundException, RemoteException {
		this.setTitle("Thống kê nhân viên");
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
		btnPhieuDatPhong = new JButton("Phiếu đặt phòng");
		btnPhieuDatPhong.setBounds(50, 60, 250, 50);
		btnPhieuDatPhong.setFont(font3);
		btnPhieuDatPhong.setBackground(color3);
		btnPhieuDatPhong.setForeground(Color.WHITE);
		btnHoaDon = new JButton("Hóa đơn");
		btnHoaDon.setFont(font3);
		btnHoaDon.setBounds(50, 130, 250, 50);
		btnHoaDon.setBackground(Color.WHITE);
		btnHoaDon.setForeground(color1);
		pnlChonMucThongKe.add(btnPhieuDatPhong);
		pnlChonMucThongKe.add(btnHoaDon);

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
		tblThongKe.setAutoCreateRowSorter(true);
		JScrollPane scr = new JScrollPane(tblThongKe, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnlTable.add(scr);

		JPanel pnlNDThongKeTongQuat = new JPanel();
		pnlNDThongKeTongQuat.setLayout(new BorderLayout());
		pnlNDThongKeTongQuat.setBorder(new EmptyBorder(50, 10, 50, 10));
		pnlNDThongKeTongQuat.setBackground(Color.WHITE);

		bThongKeTongQuat = Box.createVerticalBox();
		Box bLblTongDoanhThu = Box.createHorizontalBox();
		Box bTxtTongDoanhThu = Box.createHorizontalBox();
		Box bLblTongSo = Box.createHorizontalBox();
		Box bTxtTongSo = Box.createHorizontalBox();
		Box bLblCaoNhat = Box.createHorizontalBox();
		Box bTxtCaoNhat = Box.createHorizontalBox();
		Box bLblThapNhat = Box.createHorizontalBox();
		Box bTxtThapNhat = Box.createHorizontalBox();
		Box bBaoCao = Box.createHorizontalBox();

		lblTongSoPhieuDatPhong = new JLabel();
		lblTongSoPhieuDatPhong.setFont(font2);
		lblTongSo = new JLabel();
		lblTongSo.setFont(font2);
		lblCaoNhat = new JLabel();
		lblCaoNhat.setFont(font2);
		lblThapNhat = new JLabel();
		lblThapNhat.setFont(font2);

		txtTongSoPhieuDatPhong = new JTextField(14);
		txtTongSoPhieuDatPhong.setFont(font2);
		txtTongSoPhieuDatPhong.setBackground(color2);
		txtTongSoPhieuDatPhong.setEditable(false);

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

		bLblTongDoanhThu.add(lblTongSoPhieuDatPhong);
		bLblTongDoanhThu.add(Box.createVerticalStrut(0));
		bTxtTongDoanhThu.add(txtTongSoPhieuDatPhong);
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

		bThongKeTongQuat.add(bLblTongDoanhThu);
		bThongKeTongQuat.add(Box.createVerticalStrut(3));
		bThongKeTongQuat.add(bTxtTongDoanhThu);
		bThongKeTongQuat.add(Box.createVerticalStrut(20));
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
		btnPhieuDatPhong.addActionListener(this);
		btnHoaDon.addActionListener(this);
		btnBaoCao.addActionListener(this);
		btnXacNhan.addActionListener(this);
	}

	/**
	 * Gộp những dòng bị trùng nhân viên, bằng cách lấy số lượng hóa đơn lập và tổng
	 * tiền hóa đơn của dòng bị trùng ở sau cộng dồn vào số lượng hóa đơn lập và
	 * tổng tiền hóa đơn của dòng phía trước trùng với nó và xóa dòng bị trùng phía
	 * sau.
	 * 
	 * @param dsTK: ds thống kê nhân viên theo hóa đơn đã lấy theo ngày
	 * @return dsTK không còn dữ liệu trùng
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	private ArrayList<ArrayList<String>> gopDuLieuTrungTKTheoHoaDon(ArrayList<ArrayList<String>> dsTK)
			throws NumberFormatException, ParseException {
		for (int i = 0; i < dsTK.size() - 1; i++) {
			int slHoaDonLap = Integer.parseInt(dsTK.get(i).get(3));
			double tongTienHoaDon = Double.parseDouble(df.parse(dsTK.get(i).get(4)).toString());
			for (int j = i + 1; j < dsTK.size(); j++) {

				// Kiểm tra nếu bị trùng mã nhân viên thì cộng dồn số hóa đơn lập và tổng tiền
				// hóa đơn, sau
				// đó xóa phần tử trùng
				// 0 là vị trí cột mã nhân viên trong dsTK
				if (dsTK.get(i).get(0).equals(dsTK.get(j).get(0))) {

					// 3 là vị trí cột số lượng hóa đơn lập trong dsTK
					slHoaDonLap += Integer.parseInt(dsTK.get(j).get(3));

					// 4 là vị trí cột tổng tiền hóa đơn trong dsTK
					tongTienHoaDon += Double.parseDouble(df.parse(dsTK.get(j).get(4)).toString());
					dsTK.remove(j);
					j--;
				}
			}
			dsTK.get(i).set(3, slHoaDonLap + "");
			dsTK.get(i).set(4, df.format(tongTienHoaDon));
		}
		return dsTK;
	}

	/**
	 * Sắp xếp thống kê nhân viên tăng dần theo mã nhân viên
	 * 
	 * @param ds: danh sách thống kê nhân viên theo ngày đã gộp dữ liệu trùng
	 * @return danh sách thống kê nhân viên đã sắp xếp
	 */
	private ArrayList<ArrayList<String>> sapXepTKNhanVienTangTheoMa(ArrayList<ArrayList<String>> ds) {
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
	 * Lấy danh sách thống kê khách hàng theo tổng tiền hóa đơn từ ngày bắt đầu đến
	 * ngày kết thúc.
	 * 
	 * @param dsHoaDonDaThanhToan
	 * @return Danh sách khách hàng theo tổng tiền hóa đơn
	 * @throws ParseException
	 * @throws NumberFormatException
	 */
	private ArrayList<ArrayList<String>> getTKNhanVienTheoHoaDon(ArrayList<HoaDon> dsHoaDonDaThanhToan)
			throws NumberFormatException, ParseException, RemoteException {
		ArrayList<ArrayList<String>> ketQua = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < dsHoaDonDaThanhToan.size(); i++) {
			ArrayList<ChiTietHoaDon_Phong> dsPhong = dao_ChietTietHoaDon_Phong
					.getDSTheoMaHD(dsHoaDonDaThanhToan.get(i).getMaHoaDon());
			LocalDate ngayLap = dsHoaDonDaThanhToan.get(i).getGioVao(dsPhong).toLocalDate();
			if (ngayLap.compareTo(ngayBatDau) >= 0 && ngayLap.compareTo(ngayKetThuc) <= 0) {
				ArrayList<String> row = new ArrayList<String>();
				ArrayList<ChiTietHoaDon_DichVu> dsDichVu = (ArrayList<ChiTietHoaDon_DichVu>) dao_ChiTietHoaDon_DichVu
						.getDSTheoMaHD(dsHoaDonDaThanhToan.get(i).getMaHoaDon());
				row.add(dsHoaDonDaThanhToan.get(i).getNhanVien().getMaNhanVien());
				row.add(dsHoaDonDaThanhToan.get(i).getNhanVien().getTenNhanVien());
				row.add(dsHoaDonDaThanhToan.get(i).getNhanVien().getSoDienThoai());
				row.add(1 + "");
				row.add(df.format(dsHoaDonDaThanhToan.get(i).tinhTienThanhToan(dsPhong, dsDichVu)));
				ketQua.add(row);
			}
		}
		return sapXepTKNhanVienTangTheoMa(gopDuLieuTrungTKTheoHoaDon(ketQua));
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

	/**
	 * Lấy ra 10 nhân viên có số lượng phiếu đặt phòng nhiều nhất và sắp xếp theo
	 * thứ tự giảm dần số lượng phiếu đặt phòng.
	 * 
	 * @param dsTK: ds thống kê nhân viên theo phiếu đặt phòng
	 * @return ds 10 nhân viên nếu dsTK.size()>=10, ngược lại trả về dsTK.size()
	 *         nhân viên
	 */
	private ArrayList<ArrayList<String>> getMuoiNhanVienCoSoPhieuDatPhongNhieuNhat(ArrayList<ArrayList<String>> dsTK) {
		ArrayList<ArrayList<String>> ketQua = new ArrayList<ArrayList<String>>();
		int soLuong = 0;
		int soPhanTu = dsTK.size();

		while (soLuong < 10 && soPhanTu > 0) {
			double slPhieuLonNhat = Double.MIN_VALUE;
			int rowCaoNhat = -1;

			// Tìm nhân viên có số lượng phiếu đặt phòng lớn nhất
			for (int i = 0; i < dsTK.size(); i++) {
				// Kiểm tra xem danh sách con có ít nhất 4 phần tử không
				if (dsTK.get(i).size() < 4) {
					continue; // Bỏ qua nếu không đủ phần tử
				}
				double slPhieu = Double.parseDouble(dsTK.get(i).get(3));
				if (slPhieu > slPhieuLonNhat) {
					slPhieuLonNhat = slPhieu;
					rowCaoNhat = i;
				}
			}

			// Nếu tìm thấy nhân viên thì thêm vào danh sách kết quả và loại bỏ khỏi danh sách ban đầu
			if (rowCaoNhat != -1) {
				ketQua.add(dsTK.get(rowCaoNhat));
				dsTK.remove(rowCaoNhat);
				soLuong++;
				soPhanTu--;
			} else {
				// Nếu không còn nhân viên nào thỏa mãn thì thoát vòng lặp
				break;
			}
		}

		return ketQua;
	}

	/**
	 * Lấy ra 10 nhân viên có tổng tiền hóa đơn cao nhất và sắp xếp theo thứ tự giảm
	 * dần tổng tiền hóa đơn.
	 * 
	 * @param dsTK: ds thống kê nhân viên theo tổng tiền hóa đơn
	 * @return ds 10 nhân viên nếu dsTK.size()>=10, ngược lại trả về dsTK.size()
	 *         nhân viên
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	private ArrayList<ArrayList<String>> getMuoiNhanVienCoTongTienHoaDonCaoNhat(ArrayList<ArrayList<String>> dsTK)
			throws NumberFormatException, ParseException {
		ArrayList<ArrayList<String>> ketQua = new ArrayList<ArrayList<String>>();
		int soLuong = 0;
		int soPhanTu = dsTK.size();
		if (soPhanTu > 0)
			while (soLuong < 10 && soLuong < soPhanTu) {

				// 4 là vị trí của tổng tiền hóa đơn trong dsTK
				double giaTriLonNhat = Double.parseDouble(df.parse(dsTK.get(0).get(4)).toString());
				int rowCaoNhat = 0;
				for (int i = 0; i < dsTK.size(); i++) {
					double giaTri = Double.parseDouble(df.parse(dsTK.get(i).get(4)).toString());
					if (giaTriLonNhat < giaTri) {
						giaTriLonNhat = giaTri;
						rowCaoNhat = i;
					}
				}
				ketQua.add(dsTK.get(rowCaoNhat));
				dsTK.remove(rowCaoNhat);
				soLuong++;
			}
		return ketQua;
	}

	/**
	 * Tạo dữ liệu biểu đồ thống kê nhân viên theo phiếu đặt phòng
	 * 
	 * @param dsTK: ds thống kê nhân viên theo phiếu đặt phòng
	 * @return dữ liệu biểu đồ gồm có 10 nhân viên có số phiếu đặt phòng nhiều nhất
	 */
	private CategoryDataset createDatasetSoPhieuDatPhong(ArrayList<ArrayList<String>> dsTK) {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<ArrayList<String>> dsMuoiNhanVien = getMuoiNhanVienCoSoPhieuDatPhongNhieuNhat(dsTK);
		for (int i = 0; i < dsMuoiNhanVien.size(); i++) {

			// 3 là vị trí cột số lượng phiếu đặt phòng trong dsMuoiNhanVien
			int slPhieu = Integer.parseInt(dsMuoiNhanVien.get(i).get(3));

			// 0 là vị trí cột mã nhân viên trong dsMuoiNhanVien
			dataset.addValue(slPhieu, "Số phiếu đặt phòng", dsMuoiNhanVien.get(i).get(0));
		}
		return dataset;
	}

	/**
	 * Tạo dữ liệu biểu đồ thống kê nhân viên theo tổng tiền hóa đơn
	 * 
	 * @param dsTK: ds thống kê nhân viên theo phiếu đặt phòng
	 * @return dữ liệu biểu đồ gồm có 10 nhân viên có tổng tiền hóa đơn cao nhất
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	private CategoryDataset createDatasetHoaDon(ArrayList<ArrayList<String>> dsTK)
			throws NumberFormatException, ParseException {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		ArrayList<ArrayList<String>> dsMuoiNhanVien = getMuoiNhanVienCoTongTienHoaDonCaoNhat(dsTK);
		for (int i = 0; i < dsMuoiNhanVien.size(); i++) {

			// 4 là vị trí cột tổng tiền hóa đơn trong dsMuoiNhanVien
			double tongTien = Double.parseDouble(df.parse(dsMuoiNhanVien.get(i).get(4)).toString());

			// 0 là vị trí cột mã nhân viên trong dsMuoiNhanVien
			dataset.addValue(tongTien, "Tổng tiền hóa đơn", dsMuoiNhanVien.get(i).get(0));
		}
		return dataset;
	}

	private void setDataChartSoPhieuDatPhong() throws RemoteException {
		chartPanel.setChart(createChart(
				createDatasetSoPhieuDatPhong(dao_PhieuDatPhong.getTKNhanVienTheoPhieuDatPhong(ngayBatDau, ngayKetThuc)),
				"BIỂU ĐỒ TOP 10 NHÂN VIÊN CÓ SỐ PHIẾU ĐẶT PHÒNG NHIỀU NHẤT", "Nhân viên", "Số phiếu đặt phòng"));
	}

	private void setDataChartTongTienHoaDon() throws NumberFormatException, ParseException, RemoteException {
		chartPanel.setChart(createChart(createDatasetHoaDon(getTKNhanVienTheoHoaDon(dsHoaDonDaThanhToan)),
				"BIỂU ĐỒ TOP 10 NHÂN VIÊN CÓ TỔNG TIỀN HÓA ĐƠN CAO NHẤT", "Nhân viên", "Tổng tiền hóa đơn"));
	}

	/**
	 * Cập nhật dữ liệu phần thống kê tổng quát cho thống kê nhân viên theo số phiếu
	 * đặt phòng.
	 * 
	 * @param model: thống kê nhân viên theo số phiếu đặt phòng
	 */
	@SuppressWarnings("deprecation")
	private void setDataThongKeTongQuatSoPhieuDatPhong(DefaultTableModel model) {
		// Nếu model chưa có dữ liệu thì sẽ ẩn phần thống kê tổng quát
		if (model.getRowCount() == 0) {
			bThongKeTongQuat.hide();
		} else {
			double giaTriCaoNhat = Double.MIN_VALUE;
			double giaTriThapNhat = Double.MAX_VALUE;
			double tongSoPhieu = 0;
			int rowCaoNhat = 0;
			int rowThapNhat = 0;

			// Lặp qua các hàng của bảng để tính toán
			for (int i = 0; i < model.getRowCount(); i++) {
				Object value = model.getValueAt(i, 4);

				// Kiểm tra giá trị có null không trước khi chuyển đổi sang kiểu double
				if (value != null) {
					double giaTri = Double.parseDouble(value.toString());
					tongSoPhieu += giaTri;

					if (giaTri > giaTriCaoNhat) {
						giaTriCaoNhat = giaTri;
						rowCaoNhat = i;
					}
					if (giaTri < giaTriThapNhat) {
						giaTriThapNhat = giaTri;
						rowThapNhat = i;
					}
				}
			}

			// Hiển thị dữ liệu tính toán được
			lblTongSoPhieuDatPhong.setText("Tổng số phiếu đặt phòng:");
			lblTongSo.setText("Tổng số nhân viên đã tạo phiếu:");
			lblCaoNhat.setText("Nhân viên tạo nhiều nhất:");
			lblThapNhat.setText("Nhân viên tạo ít nhất:");

			txtTongSoPhieuDatPhong.setText(Double.toString(tongSoPhieu));
			txtTongSo.setText(Integer.toString(model.getRowCount()));

			if (rowCaoNhat >= 0 && rowCaoNhat < model.getRowCount()) {
				txtCaoNhat.setText(model.getValueAt(rowCaoNhat, 1).toString());
			}

			if (rowThapNhat >= 0 && rowThapNhat < model.getRowCount()) {
				txtThapNhat.setText(model.getValueAt(rowThapNhat, 1).toString());
			}

			bThongKeTongQuat.show();
		}
	}

	/**
	 * Cập nhật dữ liệu phần thống kê tổng quát cho thống kê nhân viên theo tổng
	 * tiền hóa đơn.
	 * 
	 * @param model: thống kê nhân viên theo tổng tiền hóa đơn
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	@SuppressWarnings("deprecation")
	private void setDataThongKeTongQuatTongTienHoaDon(DefaultTableModel model)
			throws NumberFormatException, ParseException {

		// Nếu model chưa có dữ liệu thì sẽ ẩn phần thống kê tổng quát
		if (model.getRowCount() == 0) {
			bThongKeTongQuat.hide();
		} else {

			// 5 là vị trí cột tổng tiền hóa đơn trong model
			double giaTriCaoNhat = Double.parseDouble(df.parse(model.getValueAt(0, 5).toString()).toString());
			double giaTriThapNhat = Double.parseDouble(df.parse(model.getValueAt(0, 5).toString()).toString());
			int tongSoHoaDonLap = 0;
			int rowCaoNhat = 0;
			int rowThapNhat = 0;
			for (int i = 0; i < model.getRowCount(); i++) {
				double giaTri = Double.parseDouble(df.parse(model.getValueAt(i, 5).toString()).toString());

				// 4 là vị trí cột số lượng hóa đơn lập trong model
				tongSoHoaDonLap += Integer.parseInt(model.getValueAt(i, 4).toString());
				if (giaTriCaoNhat < giaTri) {
					giaTriCaoNhat = giaTri;
					rowCaoNhat = i;
				}
				if (giaTriThapNhat > giaTri) {
					giaTriThapNhat = giaTri;
					rowThapNhat = i;
				}
			}
			lblTongSoPhieuDatPhong.setText("Tổng số hóa đơn lập:");
			lblTongSo.setText("Tổng số nhân viên:");
			lblCaoNhat.setText("Tổng tiền hóa đơn cao nhất:");
			lblThapNhat.setText("Tổng tiền hóa đơn thấp nhất:");

			txtTongSoPhieuDatPhong.setText(tongSoHoaDonLap + "");
			txtTongSo.setText(model.getRowCount() + "");
			txtCaoNhat.setText(model.getValueAt(rowCaoNhat, 1).toString());
			txtThapNhat.setText(model.getValueAt(rowThapNhat, 1).toString());

			bThongKeTongQuat.show();
		}
	}

	private void setAllData() throws RemoteException {
		if (btnPhieuDatPhong.getBackground().equals(color3)) {
			String colums[] = { "STT", "Mã nhân viên", "Tên Nhân viên", "Số điện thoại", "Số phiếu đặt phòng" };
			DefaultTableModel model = loadDataModel(colums,
					dao_PhieuDatPhong.getTKNhanVienTheoPhieuDatPhong(ngayBatDau, ngayKetThuc));
			setDataTable(model);
			setDataThongKeTongQuatSoPhieuDatPhong(model);
			setDataChartSoPhieuDatPhong();
		} else {
			if (btnHoaDon.getBackground().equals(color3)) {
				String colums[] = { "STT", "Mã nhân viên", "Tên nhân viên", "Số điện thoại", "Số lượng hóa đơn lập",
						"Tổng tiền hóa đơn" };
				try {
					DefaultTableModel model = loadDataModel(colums, getTKNhanVienTheoHoaDon(dsHoaDonDaThanhToan));
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
		if (!btn.equals(btnPhieuDatPhong)) {
			btnPhieuDatPhong.setBackground(Color.WHITE);
			btnPhieuDatPhong.setForeground(color1);
		}
		if (!btn.equals(btnHoaDon)) {
			btnHoaDon.setBackground(Color.WHITE);
			btnHoaDon.setForeground(color1);
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
		if (o.equals(btnPhieuDatPhong)) {
			setMauButton(btnPhieuDatPhong);
			try {
				showData();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if (o.equals(btnHoaDon)) {
			setMauButton(btnHoaDon);
			try {
				showData();
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if (o.equals(btnBaoCao)) {
			if (btnPhieuDatPhong.getBackground().equals(color3)) {
				try {
					ArrayList<ArrayList<String>> dsLoad = dao_PhieuDatPhong.getTKNhanVienTheoPhieuDatPhong(ngayBatDau,
							ngayKetThuc);
					JasperReport jasperReport = JasperCompileManager
							.compileReport("report/ThongKeSoPhieuDatPhongNhanVien.jrxml");
					ArrayList<Map<String, String>> ds = new ArrayList<Map<String, String>>();
					Map<String, String> map = new HashMap<String, String>();
					map.put("ngayBatDau", dtf.format(ngayBatDau));
					map.put("ngayKetThuc", dtf.format(ngayKetThuc));
					double tongSoNV = 0;
					for (int i = 0; i < dsLoad.size(); i++) {
						if (i != 0) {
							map = new HashMap<String, String>();
						}
						map.put("stt", (i + 1) + "");
						map.put("maNV", dsLoad.get(i).get(0));
						map.put("tenNV", dsLoad.get(i).get(1));
						map.put("sdt", dsLoad.get(i).get(2));
						map.put("soPhieuDatPhong", dsLoad.get(i).get(3));
						ds.add(map);
						tongSoNV++;
					}
					map.put("tongSoNV", tongSoNV + "");
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
			if (btnHoaDon.getBackground().equals(color3)) {
				try {
					JasperReport jasperReport = JasperCompileManager
							.compileReport("report/ThongKeTongTienHoaDonNhanVien.jrxml");
					ArrayList<Map<String, String>> ds = new ArrayList<Map<String, String>>();
					Map<String, String> map = new HashMap<String, String>();
					ArrayList<ArrayList<String>> dsLoad;
					try {
						dsLoad = getTKNhanVienTheoHoaDon(dsHoaDonDaThanhToan);
						map.put("ngayBatDau", dtf.format(ngayBatDau));
						map.put("ngayKetThuc", dtf.format(ngayKetThuc));
						double tongSoNV = 0;
						for (int i = 0; i < dsLoad.size(); i++) {
							if (i != 0) {
								map = new HashMap<String, String>();
							}
							map.put("stt", (i + 1) + "");
							map.put("maNV", dsLoad.get(i).get(0));
							map.put("tenNV", dsLoad.get(i).get(1));
							map.put("sdt", dsLoad.get(i).get(2));
							map.put("soHDLap", dsLoad.get(i).get(3));
							map.put("tongTienHD", dsLoad.get(i).get(4));
							ds.add(map);
							tongSoNV++;
						}
						map.put("tongSoNV", tongSoNV + "");
						map.put("ngayLapTK", dtf.format(LocalDate.now()));

					} catch (NumberFormatException | ParseException e1) {
						e1.printStackTrace();
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
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
