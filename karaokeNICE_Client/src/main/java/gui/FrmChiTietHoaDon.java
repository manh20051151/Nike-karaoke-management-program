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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

//import dao.DAO_ChiTietHoaDon_DichVu;
//import dao.DAO_ChiTietHoaDon_Phong;
//import dao.DAO_HoaDon;
import dao.DaoChiTietHoaDon_DichVu;
import dao.DaoChiTietHoaDon_Phong;
import dao.DaoHoaDon;
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

public class FrmChiTietHoaDon extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String URL = "rmi://26.173.247.75:7878/";

	DaoHoaDon dao_HoaDon = (DaoHoaDon) Naming.lookup(URL + "hoaDon");
	DaoChiTietHoaDon_Phong dao_ChietTietHoaDon_Phong = (DaoChiTietHoaDon_Phong) Naming.lookup(URL + "daoChiTietHoaDon_phong");
	DaoChiTietHoaDon_DichVu dao_ChietTietHoaDon_DichVu = (DaoChiTietHoaDon_DichVu) Naming.lookup(URL + "daoChiTietHoaDon_dichVu");


//	private DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
//	private DAO_ChiTietHoaDon_Phong dao_ChietTietHoaDon_Phong = new DAO_ChiTietHoaDon_Phong();
//	private DAO_ChiTietHoaDon_DichVu dao_ChietTietHoaDon_DichVu = new DAO_ChiTietHoaDon_DichVu();
	private JTable tbl;
	private JButton btnInHoaDon;
	private HoaDon hoaDon;
	private ArrayList<ChiTietHoaDon_Phong> dsPhong = new ArrayList<ChiTietHoaDon_Phong>();
	private ArrayList<ChiTietHoaDon_DichVu> dsDichVu = new ArrayList<ChiTietHoaDon_DichVu>();
	private DecimalFormat df = new DecimalFormat("#,##0đ");
	private DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM/yyy");
	private DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyy");
	private DateTimeFormatter dtf3 = DateTimeFormatter.ofPattern("HH:mm");

	public FrmChiTietHoaDon(String maHD) throws MalformedURLException, NotBoundException, RemoteException {
		this.setTitle("Chi tiết hóa đơn");
		this.setSize(800, 1040);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.createUI(maHD);
		this.setVisible(true);
	}

	private void createUI(String maHD) throws RemoteException {
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
		pnlTop.setBorder(new EmptyBorder(0, 151, 10, 151));
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

		hoaDon = dao_HoaDon.getHDTheoMaHD(maHD);
		dsPhong = dao_ChietTietHoaDon_Phong.getDSTheoMaHD(maHD);
		dsDichVu = (ArrayList<ChiTietHoaDon_DichVu>) dao_ChietTietHoaDon_DichVu.getDSTheoMaHD(maHD);

		JLabel lblMaHoaDon = new JLabel("Mã hóa đơn:");
		lblMaHoaDon.setFont(font1);
		lblMaHoaDon.setBorder(new EmptyBorder(0, 0, 0, 40));
		JLabel lblGiaTriMaHoaDon = new JLabel(maHD);
		lblGiaTriMaHoaDon.setFont(font1);
		JLabel lblNgayLap = new JLabel("Ngày lập:");
		lblNgayLap.setFont(font1);
		JLabel lblGiaTriNgayLap = new JLabel(dtf1.format(hoaDon.getGioVao(dsPhong).toLocalDate()));
		lblGiaTriNgayLap.setFont(font1);
		JLabel lblKhachHang = new JLabel("Khách hàng");
		lblKhachHang.setFont(font1);
		JLabel lblGiaTriKhachHang = new JLabel(hoaDon.getKhachHang().getTenKhachHang());
		lblGiaTriKhachHang.setFont(font1);

		lblKhachHang.setPreferredSize(lblMaHoaDon.getPreferredSize());
		lblNgayLap.setPreferredSize(lblMaHoaDon.getPreferredSize());

		bLeft1.add(lblMaHoaDon);
		bLeft1.add(lblGiaTriMaHoaDon);
		bLeft1.add(Box.createVerticalStrut(35));
		bLeft2.add(lblNgayLap);
		bLeft2.add(lblGiaTriNgayLap);
		bLeft2.add(Box.createVerticalStrut(35));
		bLeft3.add(lblKhachHang);
		bLeft3.add(lblGiaTriKhachHang);
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
		JLabel lblGiaTriGioVao = new JLabel(dtf2.format(hoaDon.getGioVao(dsPhong)));
		lblGiaTriGioVao.setFont(font1);
		JLabel lblGioRa = new JLabel("Giờ ra: ");
		lblGioRa.setFont(font1);
		JLabel lblGiaTriGioRa = new JLabel(dtf2.format(hoaDon.getGioRa(dsPhong)));
		lblGiaTriGioRa.setFont(font1);
		JLabel lblGiaTriTongThoiLuong = new JLabel();
		lblGiaTriTongThoiLuong.setFont(font1);
		JLabel lblNhanVienThuNgan = new JLabel("Nhân viên:");
		lblNhanVienThuNgan.setBorder(new EmptyBorder(0, 0, 0, 40));
		lblNhanVienThuNgan.setFont(font1);
		JLabel lblGiaTriNhanVienThuNgan = new JLabel(hoaDon.getNhanVien().getTenNhanVien());
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
		pnlThongTin.add(bRight, BorderLayout.EAST);

		// Dịch vụ
		JPanel pnlDichVu = new JPanel();
		pnlDichVu.setBackground(Color.WHITE);
		pnlDichVu.setBorder(new EmptyBorder(0, 15, 10, 15));
		pnlDichVu.setLayout(new BorderLayout());

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("STT");
		model.addColumn("Phòng/Dịch vụ");
		model.addColumn("Thời gian/SL");
		model.addColumn("Giá");
		model.addColumn("Thành tiền");
		tbl = new JTable(model);
		tbl.setRowHeight(35);
		JScrollPane spDichVu = new JScrollPane(tbl);

		dieuChinhDoRongCotTable("Phòng/Dịch vụ", 250);
		dieuChinhDoRongCotTable("Thời gian/SL", 170);
		dieuChinhDoRongCotTable("Giá", 130);
		dieuChinhDoRongCotTable("Thành tiền", 150);

		((DefaultTableCellRenderer) tbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		canChinhCotTable("STT", DefaultTableCellRenderer.CENTER);
		canChinhCotTable("Giá", DefaultTableCellRenderer.RIGHT);
		canChinhCotTable("Thành tiền", DefaultTableCellRenderer.RIGHT);

		String thoiGian;
		for (int i = 0; i < dsPhong.size(); i++) {
			String tenPhong = dsPhong.get(i).getPhong().getMaPhong() + " từ: " + dtf3.format(dsPhong.get(i).getGioVao())
					+ " đến: " + dtf3.format(dsPhong.get(i).getGioRa());
			int phut = dsPhong.get(i).tinhSoPhut() % 60;
			int gio = (dsPhong.get(i).tinhSoPhut() - phut) / 60;
			if (gio == 0) {
				thoiGian = phut + " phút";
			} else {
				if (phut == 0) {
					thoiGian = gio + " giờ";
				} else {
					thoiGian = gio + " giờ " + phut + " phút";
				}
			}
			String giaPhong = df.format(dsPhong.get(i).getPhong().getLoaiPhong().getGia());
			double thanhTienPhong = dsPhong.get(i).tinhTienPhong();
			String rowPhong[] = { (i + 1) + "", tenPhong, thoiGian, giaPhong, df.format(thanhTienPhong) };
			model.addRow(rowPhong);
		}
		for (int i = 0; i < dsDichVu.size(); i++) {
			String tenDV = dsDichVu.get(i).getDichVu().getTenDV();
			String soLuong = dsDichVu.get(i).getSoLuong() + "";
			String giaDV = df.format(dsDichVu.get(i).getDichVu().getGia());
			double thanhTienDV = dsDichVu.get(i).tinhTienDichVu();
			String rowDV[] = { (dsPhong.size() + 1 + i) + "", tenDV, soLuong, giaDV, df.format(thanhTienDV) };
			model.addRow(rowDV);
		}

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
		JLabel lblGiaTriTongTienPhong = new JLabel(df.format(hoaDon.tinhTongTienPhong(dsPhong)));
		lblGiaTriTongTienPhong.setFont(font1);
		JLabel lblTongTienDichVu = new JLabel("Tổng tiền dịch vụ:");
		lblTongTienDichVu.setFont(font1);
		JLabel lblGiaTriTongTienDichVu = new JLabel(df.format(hoaDon.tinhTongTienDichVu(dsDichVu)));
		lblGiaTriTongTienDichVu.setFont(font1);
		JLabel lblChietKhau = new JLabel("Chiết khấu:");
		lblChietKhau.setFont(font1);
		JLabel lblGiaTriChietKhau = new JLabel(hoaDon.getChietKhau() + " %");
		lblGiaTriChietKhau.setFont(font1);
		JLabel lblTongTienThanhToan = new JLabel("Tổng tiền thanh toán:");
		lblTongTienThanhToan.setForeground(Color.RED);
		lblTongTienThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 28));
		JLabel lblGiaTriTongTienThanhToan = new JLabel(df.format(hoaDon.tinhTienThanhToan(dsPhong, dsDichVu)));
		lblGiaTriTongTienThanhToan.setForeground(Color.RED);
		lblGiaTriTongTienThanhToan.setFont(new Font("Times New Roman", Font.BOLD, 28));
		JLabel lblTienNhan = new JLabel("Tiền nhận:");
		lblTienNhan.setFont(font1);
		JLabel lblGiaTriTienNhan = new JLabel(df.format(hoaDon.getTienNhan()));
		lblGiaTriTienNhan.setFont(font1);
		JLabel lblTienThua = new JLabel("Tiền thừa:");
		lblTienThua.setFont(font1);
		JLabel lblGiaTriTienThua = new JLabel(
				df.format(hoaDon.getTienNhan() - hoaDon.tinhTienThanhToan(dsPhong, dsDichVu)));
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
		bThanhToan5.add(lblGiaTriTienNhan);
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

		btnInHoaDon = new JButton("In hóa đơn");
		btnInHoaDon.setBackground(color);
		btnInHoaDon.setForeground(Color.WHITE);
		btnInHoaDon.setFont(font2);

		pnlButton.add(Box.createVerticalStrut(0));
		pnlButton.add(btnInHoaDon);

		pnlBorder.add(pnlTop);
		pnlBorder.add(Box.createVerticalStrut(2));
		pnlBorder.add(pnlTieuDe);
		pnlBorder.add(pnlThongTin);
		pnlBorder.add(pnlDichVu);
		pnlBorder.add(pnlThanhToan);
		pnlBorder.add(Box.createVerticalStrut(2));
		pnlBorder.add(pnlButton);
		this.add(pnlBorder);

		btnInHoaDon.addActionListener(this);
	}

	private void dieuChinhDoRongCotTable(String tenCot, int doRong) {
		tbl.getColumn(tenCot).setPreferredWidth(doRong);
	}

	private void canChinhCotTable(String tenCot, int canChinh) {
		DefaultTableCellRenderer dRenderer = new DefaultTableCellRenderer();
		dRenderer.setHorizontalAlignment(canChinh);
		tbl.getColumn(tenCot).setCellRenderer(dRenderer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnInHoaDon)) {
			try {
				JasperReport jasperReport = JasperCompileManager.compileReport("report/HoaDon.jrxml");
				ArrayList<Map<String, String>> ds = new ArrayList<Map<String, String>>();

				Map<String, String> map = new HashMap<String, String>();
				map = new HashMap<String, String>();
				map.put("maHD", hoaDon.getMaHoaDon());
				map.put("ngayLap", dtf1.format(hoaDon.getGioVao(dsPhong).toLocalDate()));
				map.put("khachHang", hoaDon.getKhachHang().getTenKhachHang());
				map.put("gioVao", dtf2.format(hoaDon.getGioVao(dsPhong)));
				map.put("gioRa", dtf2.format(hoaDon.getGioRa(dsPhong)));
				map.put("nhanVien", hoaDon.getNhanVien().getTenNhanVien());

				String thoiGian;
				for (int i = 0; i < dsPhong.size(); i++) {
					if (i != 0) {
						map = new HashMap<String, String>();
					}
					map.put("stt", (i + 1) + "");
					map.put("phong_DV",
							dsPhong.get(i).getPhong().getMaPhong() + " từ: " + dtf3.format(dsPhong.get(i).getGioVao())
									+ " đến: " + dtf3.format(dsPhong.get(i).getGioRa()));
					int phut = dsPhong.get(i).tinhSoPhut() % 60;
					int gio = (dsPhong.get(i).tinhSoPhut() - phut) / 60;
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
					map.put("gia", df.format(dsPhong.get(i).getPhong().getLoaiPhong().getGia()));
					map.put("thanhTien", df.format(dsPhong.get(i).tinhTienPhong()));
					ds.add(map);
				}
				for (int i = 0; i < dsDichVu.size(); i++) {
					map = new HashMap<String, String>();
					map.put("stt", (dsPhong.size() + 1) + "");
					map.put("phong_DV", dsDichVu.get(i).getDichVu().getTenDV());
					map.put("tg_sl", dsDichVu.get(i).getSoLuong() + "");
					map.put("gia", df.format(dsDichVu.get(i).getDichVu().getGia()));
					map.put("thanhTien", df.format(dsDichVu.get(i).tinhTienDichVu()));
					ds.add(map);
				}
				map.put("tongTienPhong", df.format(hoaDon.tinhTongTienPhong(dsPhong)));
				map.put("tongTienDV", df.format(hoaDon.tinhTongTienDichVu(dsDichVu)));
				map.put("chietKhau", hoaDon.getChietKhau() + "%");
				double tienThanhToan = hoaDon.tinhTienThanhToan(dsPhong, dsDichVu);
				map.put("tongTienThanhToan", df.format(tienThanhToan));
				double tienNhan = hoaDon.getTienNhan();
				map.put("tienNhan", df.format(tienNhan));
				double tienThua = tienNhan - tienThanhToan;
				map.put("tienThua", df.format(tienThua));

				JRDataSource dataSource = new JRBeanCollectionDataSource(ds);
				JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
				JasperViewer.viewReport(jasperPrint, false);
			} catch (JRException e1) {
				e1.printStackTrace();
			}

		}

	}
}
