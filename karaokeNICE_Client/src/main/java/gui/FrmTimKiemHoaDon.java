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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
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

//import connectDB.ConnectDB;
//import dao.DAO_ChiTietHoaDon_DichVu;
//import dao.DAO_ChiTietHoaDon_Phong;
//import dao.DAO_HoaDon;
import dao.DaoChiTietHoaDon_DichVu;
import dao.DaoChiTietHoaDon_Phong;
import dao.DaoHoaDon;
import entity.ChiTietHoaDon_DichVu;
import entity.ChiTietHoaDon_Phong;
import entity.HoaDon;

public class FrmTimKiemHoaDon extends JFrame implements KeyListener, ActionListener {
	/**
	 * 
	 */
	private static final String URL = "rmi://26.173.247.75:7878/";


	DaoHoaDon dao_HoaDon = (DaoHoaDon) Naming.lookup(URL + "hoaDon");
	DaoChiTietHoaDon_Phong dao_ChietTietHoaDon_Phong = (DaoChiTietHoaDon_Phong) Naming.lookup(URL + "daoChiTietHoaDon_phong");
	DaoChiTietHoaDon_DichVu dao_ChietTietHoaDon_DichVu = (DaoChiTietHoaDon_DichVu) Naming.lookup(URL + "daoChiTietHoaDon_dichVu");

	private static final long serialVersionUID = 1L;
//	private DAO_HoaDon dao_HoaDon = new DAO_HoaDon();
//	private DAO_ChiTietHoaDon_Phong dao_ChietTietHoaDon_Phong = new DAO_ChiTietHoaDon_Phong();
//	private DAO_ChiTietHoaDon_DichVu dao_ChietTietHoaDon_DichVu = new DAO_ChiTietHoaDon_DichVu();
	private DefaultTableModel model;
	private JTable tbl;
	private JTextField txtTimKiem;
	private JButton btnXemChiTiet;
	protected static JPanel pnlBorder;
	private DecimalFormat df = new DecimalFormat("#,##0đ");
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy");
	private ArrayList<ArrayList<String>> dsHienThi;

	public FrmTimKiemHoaDon() throws MalformedURLException, NotBoundException, RemoteException {
		this.setTitle("Tìm kiếm hóa đơn");
		this.setSize(1920, 1040);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.createUI();
	}

	private void createUI() throws RemoteException {
//		ConnectDB.getInstance().connect();

		// Kiểu chữ
		Font font1 = new Font("Times New Roman", Font.BOLD, 36);
		Font font2 = new Font("Times New Roman", Font.PLAIN, 24);
		Font font3 = new Font("Times New Roman", Font.BOLD, 24);

		// Màu chữ
		Color color1 = new Color(138, 43, 226); // BlueViolet
		Color color2 = new Color(233, 221, 244);

		pnlBorder = new JPanel();
		pnlBorder.setLayout(new BorderLayout());
		pnlBorder.setBackground(color2);
		pnlBorder.setBorder(new EmptyBorder(20, 20, 20, 20));

		// North
		JPanel pnlTop = new JPanel();
		pnlTop.setLayout(new BorderLayout());
		pnlTop.setBackground(color2);

		// North-Content
		JPanel pnlTittle = new JPanel();
		pnlTittle.setBorder(new EmptyBorder(20, 20, 20, 20));
		pnlTittle.setBackground(Color.WHITE);
		JLabel lblTittle = new JLabel("TÌM KIẾM HÓA ĐƠN");
		lblTittle.setFont(font1);
		lblTittle.setForeground(color1);
		pnlTittle.add(lblTittle);

		pnlTop.add(pnlTittle, BorderLayout.CENTER);
		pnlTop.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);

		// Center
		JPanel pnlCenter = new JPanel();
		pnlCenter.setBackground(color2);
		pnlCenter.setLayout(new BorderLayout());

		// Center-Top
		JPanel pnlThongTinTimKiem = new JPanel();
		pnlThongTinTimKiem.setBackground(Color.WHITE);
		pnlThongTinTimKiem.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel lblTimKiem = new JLabel("Nhập thông tin tìm kiếm:");
		lblTimKiem.setFont(font2);
		txtTimKiem = new JTextField(50);
		txtTimKiem.setFont(font2);
		txtTimKiem.setBackground(color2);
		pnlThongTinTimKiem.add(lblTimKiem);
		pnlThongTinTimKiem.add(txtTimKiem);

		// Center-table
		JPanel pnlTable = new JPanel();
		pnlTable.setBackground(Color.WHITE);
		pnlTable.setLayout(new BorderLayout());
		model = new DefaultTableModel();
		model.addColumn("STT");
		model.addColumn("Mã hóa đơn");
		model.addColumn("Ngày lập");
		model.addColumn("Tên khách hàng");
		model.addColumn("SĐT khách");
		model.addColumn("Nhân viên");
		model.addColumn("Chiết khấu");
		model.addColumn("Tiền thanh toán");
		tbl = new JTable(model);
		JScrollPane jScrollPane = new JScrollPane(tbl, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JTableHeader tbh = tbl.getTableHeader();
		tbh.setFont(font2);
		tbl.setFont(font2);
		tbl.setRowHeight(35);
		tbl.setDefaultEditor(Object.class, null);
		pnlTable.add(jScrollPane, BorderLayout.CENTER);

		dieuChinhDoRongCotTable("Mã hóa đơn", 150);
		dieuChinhDoRongCotTable("Ngày lập", 200);
		dieuChinhDoRongCotTable("Tên khách hàng", 300);
		dieuChinhDoRongCotTable("SĐT khách", 150);
		dieuChinhDoRongCotTable("Nhân viên", 300);
		dieuChinhDoRongCotTable("Chiết khấu", 100);
		dieuChinhDoRongCotTable("Tiền thanh toán", 250);

		((DefaultTableCellRenderer) tbl.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		canChinhCotTable("STT", DefaultTableCellRenderer.CENTER);
		canChinhCotTable("Mã hóa đơn", DefaultTableCellRenderer.CENTER);
		canChinhCotTable("SĐT khách", DefaultTableCellRenderer.CENTER);
		canChinhCotTable("Chiết khấu", DefaultTableCellRenderer.CENTER);
		canChinhCotTable("Tiền thanh toán", DefaultTableCellRenderer.RIGHT);

		pnlCenter.add(pnlThongTinTimKiem, BorderLayout.NORTH);
		pnlCenter.add(pnlTable, BorderLayout.CENTER);
		pnlCenter.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);

		// South
		JPanel pnlButton = new JPanel();
		pnlButton.setBackground(Color.WHITE);
		pnlButton.setBorder(new EmptyBorder(20, 20, 20, 20));
		btnXemChiTiet = new JButton("Xem chi tiết");
		btnXemChiTiet.setFont(font3);
		btnXemChiTiet.setBackground(color1);
		btnXemChiTiet.setForeground(Color.WHITE);
		pnlButton.add(btnXemChiTiet);

		pnlBorder.add(pnlTop, BorderLayout.NORTH);
		pnlBorder.add(pnlCenter, BorderLayout.CENTER);
		pnlBorder.add(pnlButton, BorderLayout.SOUTH);
		this.add(pnlBorder);

		ArrayList<HoaDon> dsHoaDon = dao_HoaDon.getHoaDonDaThanhToan();
		dsHienThi = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < dsHoaDon.size(); i++) {
			String maHD = dsHoaDon.get(i).getMaHoaDon();
			ArrayList<String> hoaDon = new ArrayList<String>();
			ArrayList<ChiTietHoaDon_Phong> dsPhong = dao_ChietTietHoaDon_Phong.getDSTheoMaHD(maHD);
			ArrayList<ChiTietHoaDon_DichVu> dsDichVu = (ArrayList<ChiTietHoaDon_DichVu>) dao_ChietTietHoaDon_DichVu.getDSTheoMaHD(maHD);

			hoaDon.add(maHD);
			hoaDon.add(dtf.format(dsHoaDon.get(i).getGioVao(dsPhong).toLocalDate()));
			hoaDon.add(dsHoaDon.get(i).getKhachHang().getTenKhachHang());
			hoaDon.add(dsHoaDon.get(i).getKhachHang().getSoDienThoai());
			hoaDon.add(dsHoaDon.get(i).getNhanVien().getTenNhanVien());
			hoaDon.add(dsHoaDon.get(i).getChietKhau() + "%");
			hoaDon.add(df.format(dsHoaDon.get(i).tinhTienThanhToan(dsPhong, dsDichVu)));

			dsHienThi.add(hoaDon);
		}
		loadData(dsHienThi);

		txtTimKiem.addKeyListener(this);
		btnXemChiTiet.addActionListener(this);
	}

	private void dieuChinhDoRongCotTable(String tenCot, int doRong) {
		tbl.getColumn(tenCot).setPreferredWidth(doRong);
	}

	private void canChinhCotTable(String tenCot, int canChinh) {
		DefaultTableCellRenderer dRenderer = new DefaultTableCellRenderer();
		dRenderer.setHorizontalAlignment(canChinh);
		tbl.getColumn(tenCot).setCellRenderer(dRenderer);
	}

	private void loadData(ArrayList<ArrayList<String>> ds) {
		model.setRowCount(0);
		for (int i = 0; i < ds.size(); i++) {
			ds.get(i).add(0, (i + 1) + "");
			model.addRow(ds.get(i).toArray());
			ds.get(i).remove(0);
		}
	}

	private ArrayList<ArrayList<String>> search(String giaTriTimKiem) {
		ArrayList<ArrayList<String>> dsTimDuoc = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < dsHienThi.size(); i++) {
			for (int j = 0; j < dsHienThi.get(i).size() - 1; j++) {
				if (dsHienThi.get(i).get(j).toLowerCase().contains(giaTriTimKiem.toLowerCase())) {
					dsTimDuoc.add(dsHienThi.get(i));
					break;
				}
			}
			try {
				if (df.parse(dsHienThi.get(i).get(dsHienThi.get(i).size() - 1)).toString().equals(giaTriTimKiem)) {
					dsTimDuoc.add(dsHienThi.get(i));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return dsTimDuoc;
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
		loadData(search(txtTimKiem.getText().trim()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnXemChiTiet)) {
			int hangDuocChon = tbl.getSelectedRow();
			if (hangDuocChon < 0) {
				JOptionPane.showMessageDialog(this, "Chọn 1 hàng muốn xem!", "Warning", JOptionPane.WARNING_MESSAGE);
			} else {
				try {
					new FrmChiTietHoaDon(model.getValueAt(hangDuocChon, 1).toString()).setVisible(true);
				} catch (MalformedURLException ex) {
					throw new RuntimeException(ex);
				} catch (NotBoundException ex) {
					throw new RuntimeException(ex);
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			}
		}
	}

}
