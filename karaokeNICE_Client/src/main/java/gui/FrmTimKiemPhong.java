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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

//import connectDB.ConnectDB;
//import dao.DAO_DichVu;
//import dao.DAO_NhanVien;
//import dao.DAO_Phong;
import dao.DaoNhanVien;
import dao.DaoPhong;
import entity.DichVu;
import entity.NhanVien;
import entity.Phong;

public class FrmTimKiemPhong extends JFrame implements KeyListener, ActionListener {
	/**
	 * 
	 */
	private static final String URL = "rmi://26.173.247.75:7878/";
	DaoPhong dao_Phong = (DaoPhong) Naming.lookup(URL + "daoPhong");
	DaoNhanVien dao_NhanVien = (DaoNhanVien) Naming.lookup(URL + "daoNhanVien");


	private static final long serialVersionUID = 1L;
//	private DAO_Phong dao_Phong = new DAO_Phong();
//	private DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
	private DefaultTableModel model;
	private JTable tbl;
	private JTextField txtTimKiem;
	private JButton btnCapNhatPhong;
	protected static JPanel pnlBorder;
	private ArrayList<Phong> dsTimDuoc = dao_Phong.getDanhSachPhong();

	public FrmTimKiemPhong(String maNhanVien) throws MalformedURLException, NotBoundException, RemoteException {
		this.setTitle("Tìm kiếm phòng");
		this.setSize(1920, 1040);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.createUI(maNhanVien);
	}

	private void createUI(String maNhanVienTruyen) throws RemoteException {
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
		JLabel lblTittle = new JLabel("TÌM KIẾM PHÒNG");
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
		model.addColumn("Mã phòng");
		model.addColumn("Tên phòng");
		model.addColumn("Vị trí");
		model.addColumn("Loại phòng");
		model.addColumn("Sức chứa");
		model.addColumn("Trạng thái");
		tbl = new JTable(model);
		JScrollPane jScrollPane = new JScrollPane(tbl, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JTableHeader tbh = tbl.getTableHeader();
		tbh.setFont(font2);
		tbl.setFont(font2);
		tbl.setRowHeight(35);
		tbl.setDefaultEditor(Object.class, null);
		pnlTable.add(jScrollPane, BorderLayout.CENTER);

		pnlCenter.add(pnlThongTinTimKiem, BorderLayout.NORTH);
		pnlCenter.add(pnlTable, BorderLayout.CENTER);
		pnlCenter.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);

		// South
		JPanel pnlButton = new JPanel();
		pnlButton.setBackground(Color.WHITE);
		pnlButton.setBorder(new EmptyBorder(20, 20, 20, 20));
		btnCapNhatPhong = new JButton("Cập nhật phòng");
		btnCapNhatPhong.setFont(font3);
		btnCapNhatPhong.setBackground(color1);
		btnCapNhatPhong.setForeground(Color.WHITE);
		pnlButton.add(btnCapNhatPhong);

		pnlBorder.add(pnlTop, BorderLayout.NORTH);
		pnlBorder.add(pnlCenter, BorderLayout.CENTER);
//		Kiểm tra: nếu là người quản lý thì thêm panel Cập nhập phòng
		NhanVien nVien = dao_NhanVien.getNhanVienTheoMa(maNhanVienTruyen);
		if (maNhanVienTruyen.equals("admin") || nVien.getLoaiNhanVien().getMaLoaiVN().equals("LNV001"))
			pnlBorder.add(pnlButton, BorderLayout.SOUTH);
		this.add(pnlBorder);

		loadData(dao_Phong.getDanhSachPhong());

		txtTimKiem.addKeyListener(this);
		btnCapNhatPhong.addActionListener(this);
	}

	private void loadData(ArrayList<Phong> dsPhong) {
		model.setRowCount(0);
		for (int i = 0; i < dsPhong.size(); i++) {
			String maPhong = dsPhong.get(i).getMaPhong();
			String tenPhong = dsPhong.get(i).getTenPhong();
			String viTri = dsPhong.get(i).getViTri();
			String tenLoaiPhong = dsPhong.get(i).getLoaiPhong().getTenLoaiPhong();
			String sucChua = dsPhong.get(i).getSucChua() + "";
			String trangThai = dsPhong.get(i).getTrangThaiPhong();
			String row[] = { (i + 1) + "", maPhong, tenPhong, viTri, tenLoaiPhong, sucChua, trangThai };
			model.addRow(row);
		}
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
		try {
			dsTimDuoc = dao_Phong.search(txtTimKiem.getText().trim());
		} catch (RemoteException ex) {
			throw new RuntimeException(ex);
		}
		loadData(dsTimDuoc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnCapNhatPhong)) {
			FrmQuanLyPhong frmQuanLyPhong = null;
			try {
				frmQuanLyPhong = new FrmQuanLyPhong(dsTimDuoc);
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			FrmTrangChu.jtab_NoiDung.remove(FrmTrangChu.jtab_NoiDung.getSelectedComponent());
			FrmTrangChu.jtab_NoiDung.add(frmQuanLyPhong.pnlBorder);
		}
	}
}
