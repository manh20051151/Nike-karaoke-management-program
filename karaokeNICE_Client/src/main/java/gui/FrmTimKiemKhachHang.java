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
import java.util.ArrayList;

import javax.swing.Box;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

//import connectDB.ConnectDB;
//import dao.DAO_KhachHang;
//import dao.DAO_LoaiKhachHang;
import dao.DaoKhachHang;
import dao.DaoLoaiKhachHang;
import entity.KhachHang;
import entity.LoaiKhachHang;

public class FrmTimKiemKhachHang extends JFrame implements ActionListener, KeyListener {
	private static final String URL = "rmi://26.173.247.75:7878/";


	DaoKhachHang daoKH = (DaoKhachHang) Naming.lookup(URL + "daoKhachHang");
	DaoLoaiKhachHang daoLoaiKH = (DaoLoaiKhachHang) Naming.lookup(URL + "daoLoaiKhachHang");

//	private DAO_KhachHang daoKH = new DAO_KhachHang();
//	private DAO_LoaiKhachHang daoLoaiKH = new DAO_LoaiKhachHang();
	public static JPanel pnBorder;

	private DefaultTableModel modelKH;
	// private JButton btnTimKiem;
	private JTextField txtMaKH;
	private JTextField txtTenKH;
	private JTextField txtSDT;
	private JTextField txtDiem;
	private JTextArea taGhiChu;
	private JComboBox cbLoaiKH;
	private JButton btnXoa;
	private JButton btnLuu;
	private JTable tbKhachHang;
	private JButton btnTimKiem;
	private JComboBox cbLuaChon;
	private JTextField txtTimKiem;
	private JButton btnLamMoi;
	private ArrayList<KhachHang> dsTimDuoc = daoKH.getALLKhachHang();

	public FrmTimKiemKhachHang() throws MalformedURLException, NotBoundException, RemoteException {
		this.setTitle("Tìm kiếm khách hàng");
		// this.setSize(1920, 1030);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Toàn màn hình
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.createUI();
	}

	private void createUI() throws RemoteException {
//		ConnectDB.getInstance().connect();
		// Kiểu chữ
		Font font1 = new Font("Times New Roman", Font.BOLD, 36);
		Font font2 = new Font("Times New Roman", Font.PLAIN, 24);

		// Màu chữ
		Color color1 = new Color(138, 43, 226); // BlueViolet
		Color color2 = new Color(233, 221, 244);

		pnBorder = new JPanel();
		pnBorder.setLayout(new BorderLayout());
		pnBorder.setBorder(new EmptyBorder(20, 20, 20, 20));
		pnBorder.setBackground(color2);

		// Left
		JPanel pnLeft = new JPanel();
		pnLeft.setLayout(new BorderLayout());
		pnLeft.setBackground(color2);

		// Left-Content
		JPanel pnLeftContent = new JPanel();
		pnLeftContent.setBorder(new EmptyBorder(40, 40, 40, 40));
		pnLeftContent.setBackground(Color.WHITE);

		Box bThongTinKH = Box.createVerticalBox();
		Box bLbMaKH = Box.createHorizontalBox();
		Box bTxtMaKH = Box.createHorizontalBox();
		Box bLbTenKH = Box.createHorizontalBox();
		Box bTxtTenKH = Box.createHorizontalBox();
		Box bLbSDT = Box.createHorizontalBox();
		Box bTxtSDT = Box.createHorizontalBox();
		Box blbDiem = Box.createHorizontalBox();
		Box bTxtDiem = Box.createHorizontalBox();
		Box bLbLoaiKH = Box.createHorizontalBox();
		Box bCbLoaiKH = Box.createHorizontalBox();
		Box bLbGhiChu = Box.createHorizontalBox();
		Box bTaGhiChu = Box.createHorizontalBox();
		Box bTimKiem = Box.createHorizontalBox();
		Box bXoaTrang = Box.createHorizontalBox();

		JLabel lbMaKH = new JLabel("Mã khách hàng:");
		lbMaKH.setFont(font2);
		JLabel lbTenKH = new JLabel("Tên khách hàng:");
		lbTenKH.setFont(font2);
		JLabel lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setFont(font2);
		JLabel lbDiem = new JLabel("Điểm:");
		lbDiem.setFont(font2);
		JLabel lbLoaiKH = new JLabel("Loại khách hàng:");
		lbLoaiKH.setFont(font2);
		JLabel lbGhiChu = new JLabel("Ghi chú:");
		lbGhiChu.setFont(font2);

		bLbMaKH.add(lbMaKH);
		bLbMaKH.add(Box.createVerticalStrut(0));
		bLbTenKH.add(lbTenKH);
		bLbTenKH.add(Box.createVerticalStrut(0));
		bLbSDT.add(lbSDT);
		bLbSDT.add(Box.createVerticalStrut(0));
		blbDiem.add(lbDiem);
		blbDiem.add(Box.createVerticalStrut(0));
		bLbLoaiKH.add(lbLoaiKH);
		bLbLoaiKH.add(Box.createVerticalStrut(0));
		bLbGhiChu.add(lbGhiChu);
		bLbGhiChu.add(Box.createVerticalStrut(0));

		txtMaKH = new JTextField(17);
		txtMaKH.setFont(font2);
		txtMaKH.setBackground(color2);
		txtTenKH = new JTextField(17);
		txtTenKH.setFont(font2);
		txtTenKH.setBackground(color2);
		txtSDT = new JTextField(17);
		txtSDT.setFont(font2);
		txtSDT.setBackground(color2);
		txtDiem = new JTextField(17);
		txtDiem.setFont(font2);
		txtDiem.setBackground(color2);
		cbLoaiKH = new JComboBox();
		// cbLoaiKH.setModel(new DefaultComboBoxModel(new String[] {"Thường", "VIP"}));
		cbLoaiKH.addItem("");
		ArrayList<LoaiKhachHang> loaiKhachHang = daoLoaiKH.getALLLoaiKhachHang();
		for (int i = 0; i < loaiKhachHang.size(); i++) {
			cbLoaiKH.addItem(loaiKhachHang.get(i).getTenLoaiKhachHang());
		}

		cbLoaiKH.setFont(font2);
		cbLoaiKH.setBackground(color2);

		taGhiChu = new JTextArea(5, 17);
		taGhiChu.setFont(font2);
		taGhiChu.setBackground(color2);

		/*
		 * btnTimKiem = new JButton("Tìm kiếm"); btnTimKiem.setIcon(new
		 * ImageIcon("/src/main/javaimg/DanhMuc_Them.png")));
		 * btnTimKiem.setFont(font2); btnTimKiem.setBackground(color1);
		 * btnTimKiem.setForeground(Color.WHITE);
		 */
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setIcon(new ImageIcon("src/main/java/img/Button-Refresh-icon2.png"));
		btnLamMoi.setBackground(color1);
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(font2);
		/*
		 * btnSua = new JButton("Sửa"); btnSua.setIcon(new
		 * ImageIcon("/src/main/javaimg/DanhMuc_Sua.png")));
		 * btnSua.setFont(font2); btnSua.setBackground(color1);
		 * btnSua.setForeground(Color.WHITE);
		 */

		bTxtMaKH.add(txtMaKH);
		bTxtTenKH.add(txtTenKH);
		bTxtSDT.add(txtSDT);
		bTxtDiem.add(txtDiem);
		bCbLoaiKH.add(cbLoaiKH);
		bTaGhiChu.add(taGhiChu);
		bTimKiem.add(btnLamMoi);
		// bXoaTrang.add(btnXoaTrang);

		bThongTinKH.add(bLbMaKH);
		bThongTinKH.add(Box.createVerticalStrut(3));
		bThongTinKH.add(bTxtMaKH);
		bThongTinKH.add(Box.createVerticalStrut(20));
		bThongTinKH.add(bLbTenKH);
		bThongTinKH.add(Box.createVerticalStrut(3));
		bThongTinKH.add(bTxtTenKH);
		bThongTinKH.add(Box.createVerticalStrut(20));
		bThongTinKH.add(bLbSDT);
		bThongTinKH.add(Box.createVerticalStrut(3));
		bThongTinKH.add(bTxtSDT);
		bThongTinKH.add(Box.createVerticalStrut(20));
		bThongTinKH.add(blbDiem);
		bThongTinKH.add(Box.createVerticalStrut(3));
		bThongTinKH.add(bTxtDiem);
		bThongTinKH.add(Box.createVerticalStrut(20));
		bThongTinKH.add(bLbLoaiKH);
		bThongTinKH.add(Box.createVerticalStrut(3));
		bThongTinKH.add(bCbLoaiKH);
		bThongTinKH.add(Box.createVerticalStrut(20));
		bThongTinKH.add(bLbGhiChu);
		bThongTinKH.add(Box.createVerticalStrut(3));
		bThongTinKH.add(bTaGhiChu);

		bThongTinKH.add(Box.createVerticalStrut(30));
		bThongTinKH.add(bTimKiem);
		bThongTinKH.add(Box.createVerticalStrut(15));
		bThongTinKH.add(bXoaTrang);
		bThongTinKH.add(Box.createVerticalStrut(30));

		pnLeftContent.add(bThongTinKH);

		pnLeft.add(pnLeftContent, BorderLayout.CENTER);
		pnLeft.add(Box.createHorizontalStrut(20), BorderLayout.EAST);

		// Right
		JPanel pnRight = new JPanel();
		pnRight.setBackground(Color.WHITE);
		pnRight.setBorder(new EmptyBorder(15, 15, 15, 15));
		pnRight.setLayout(new BorderLayout());

		// Right-Top
		JPanel pnRightTop = new JPanel();
		pnRightTop.setBackground(Color.WHITE);
		// pnRightTop.setLayout(new BorderLayout());

		JLabel lbDSKH = new JLabel("Tìm kiếm khách hàng");
		lbDSKH.setFont(font1);
		lbDSKH.setForeground(color1);

		/*
		 * JPanel pnTimKiem = new JPanel(); pnTimKiem.setBackground(Color.WHITE);
		 * pnTimKiem.setLayout(new BoxLayout(pnTimKiem, BoxLayout.Y_AXIS));
		 * 
		 * Box bTimKiem = Box.createVerticalBox(); Box bTimKiem_LuaChon =
		 * Box.createHorizontalBox(); Box bTimKiem_Nhap = Box.createHorizontalBox();
		 * 
		 * JLabel lbLuaChon = new JLabel("Tìm theo:"); lbLuaChon.setFont(font2); JLabel
		 * lbNhap = new JLabel("Tìm kiếm:"); lbNhap.setFont(font2);
		 * lbLuaChon.setPreferredSize(lbNhap.getPreferredSize());
		 * 
		 * cbLuaChon = new JComboBox<String>(); cbLuaChon.addItem("Mã khách hàng");
		 * cbLuaChon.addItem("Tên khách hàng"); cbLuaChon.addItem("Loại khách hàng");
		 * cbLuaChon.addItem("Số điện thoại"); cbLuaChon.setFont(font2);
		 * cbLuaChon.setBackground(color2);
		 * 
		 * txtTimKiem = new JTextField(17); txtTimKiem.setFont(font2);
		 * txtTimKiem.setBackground(color2);
		 * 
		 * btnTimKiem = new JButton(); btnTimKiem.setIcon(new
		 * ImageIcon(FrmQuanLyHoaDon.class.getResource(
		 * "/img/Toolbar-Browser-Search-icon.png"))); btnTimKiem.setFont(font2);
		 * btnTimKiem.setBackground(color1); btnTimKiem.setForeground(Color.WHITE);
		 * 
		 * bTimKiem_LuaChon.add(lbLuaChon);
		 * bTimKiem_LuaChon.add(Box.createHorizontalStrut(7));
		 * bTimKiem_LuaChon.add(cbLuaChon); bTimKiem_Nhap.add(lbNhap);
		 * bTimKiem_Nhap.add(Box.createHorizontalStrut(7));
		 * bTimKiem_Nhap.add(txtTimKiem); bTimKiem_Nhap.add(btnTimKiem);
		 * 
		 * bTimKiem.add(bTimKiem_LuaChon); bTimKiem.add(Box.createVerticalStrut(10));
		 * bTimKiem.add(bTimKiem_Nhap);
		 * 
		 * pnTimKiem.add(bTimKiem);
		 * 
		 * pnRightTop.add(pnTimKiem, BorderLayout.EAST);
		 */
		pnRightTop.add(lbDSKH);

		// Right-Center
		JPanel pnRightCenter = new JPanel();
		pnRightCenter.setLayout(new BorderLayout());
		pnRightCenter.setBackground(Color.WHITE);
		pnRightCenter.setBorder(new EmptyBorder(20, 0, 20, 0));

		modelKH = new DefaultTableModel();
		modelKH.addColumn("Mã khách hàng");
		modelKH.addColumn("Tên khách hàng");
		modelKH.addColumn("Số điện thoại");
		modelKH.addColumn("Điểm");
		modelKH.addColumn("Loại khách hàng");
		modelKH.addColumn("Ghi chú");

		tbKhachHang = new JTable(modelKH);
		JScrollPane jScrollPane = new JScrollPane(tbKhachHang, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JTableHeader tbHeaderKH = tbKhachHang.getTableHeader();
		tbHeaderKH.setFont(font2);
		tbHeaderKH.setBackground(color2);
		tbKhachHang.setFont(font2);
		tbKhachHang.setRowHeight(35);
		// String row[] = {"KH001","Nguyễn Văn B","0123456789","0","Thường"," "};
		// modelKH.addRow(row);
		pnRightCenter.add(jScrollPane);
		loadData(daoKH.getALLKhachHang());

		// Right-Bottom
		JPanel pnRightBottom = new JPanel();
		pnRightBottom.setBackground(Color.WHITE);

		btnXoa = new JButton("Xóa");
		btnXoa.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Xoa.png"));
		btnXoa.setBackground(color1);
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(font2);
		btnLuu = new JButton("Lưu");
		btnLuu.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Luu.png"));
		btnLuu.setBackground(color1);
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(font2);

		/*
		 * btnLamMoi = new JButton("Làm mới"); btnLamMoi.setBackground(color1);
		 * btnLamMoi.setForeground(Color.WHITE); btnLamMoi.setFont(font2);
		 */
		/*
		 * pnRightBottom.add(btnTimKiem);
		 * pnRightBottom.add(Box.createHorizontalStrut(7)); pnRightBottom.add(btnSua);
		 * pnRightBottom.add(Box.createHorizontalStrut(7)); pnRightBottom.add(btnXoa);
		 * pnRightBottom.add(Box.createHorizontalStrut(7)); pnRightBottom.add(btnLuu);
		 * pnRightBottom.add(Box.createHorizontalStrut(7));
		 * pnRightBottom.add(btnXoaTrang);
		 */

		pnRight.add(pnRightTop, BorderLayout.NORTH);
		pnRight.add(pnRightCenter, BorderLayout.CENTER);
		pnRight.add(pnRightBottom, BorderLayout.SOUTH);

		pnBorder.add(pnLeft, BorderLayout.WEST);
		pnBorder.add(pnRight, BorderLayout.CENTER);
		getContentPane().add(pnBorder);

		tbKhachHang.setDefaultEditor(Object.class, null);

		// dongMoNhapLieu(false);
		btnLamMoi.addActionListener(this);

		// tbKhachHang.addMouseListener(this);
		/*
		 * String maKH = txtMaKH.getText(); String tenKH = txtTenKH.getText(); String
		 * sdt = txtSDT.getText(); String diem = txtDiem.getText(); String ghichu =
		 * taGhiChu.getText(); String loaiKH = cbLoaiKH.getSelectedItem().toString();
		 */
		txtMaKH.addKeyListener(this);
		txtTenKH.addKeyListener(this);
		txtSDT.addKeyListener(this);
		txtDiem.addKeyListener(this);
		taGhiChu.addKeyListener(this);
		cbLoaiKH.addActionListener(this);

	}

	public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
		new FrmTimKiemKhachHang().setVisible(true);
	}

	private void xoaRongNhapLieu() {
		txtMaKH.setText("");
		txtTenKH.setText("");
		txtSDT.setText("");
		txtDiem.setText("");
		cbLoaiKH.setSelectedIndex(0);
		taGhiChu.setText("");
	}

	private void loadData(KhachHang kh) {
		modelKH.setRowCount(0);
		String maKH = kh.getMaKhachHang();
		String tenKH = kh.getTenKhachHang();
		String sDT = kh.getSoDienThoai();
		String diem = kh.getDiem() + "";
		String tenLoaiKH = kh.getLoaiKhachHang().getTenLoaiKhachHang();
		String ghiChu = kh.getGhiChu();
		String row[] = { maKH, tenKH, sDT, diem, tenLoaiKH, ghiChu };
		modelKH.addRow(row);
	}

	private void loadData(ArrayList<KhachHang> dsKH) {
		modelKH.setRowCount(0);
		for (int i = 0; i < dsKH.size(); i++) {
			String maKH = dsKH.get(i).getMaKhachHang();
			String tenKH = dsKH.get(i).getTenKhachHang();
			String sDT = dsKH.get(i).getSoDienThoai();
			String diem = dsKH.get(i).getDiem() + "";
			String tenLoaiKH = dsKH.get(i).getLoaiKhachHang().getTenLoaiKhachHang();
			String ghiChu = dsKH.get(i).getGhiChu();
			String row[] = { maKH, tenKH, sDT, diem, tenLoaiKH, ghiChu };
			modelKH.addRow(row);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj.equals(btnTimKiem)) {
			String maKH = txtMaKH.getText();
			String tenKH = txtTenKH.getText();
			String sdt = txtSDT.getText();
			String diem = txtDiem.getText();
			String ghichu = taGhiChu.getText();
			String loaiKH = cbLoaiKH.getSelectedItem().toString();
			ArrayList<KhachHang> dsTimDuoc = null;
			try {
				dsTimDuoc = daoKH.getTimKiemDSKH(maKH, loaiKH, tenKH, sdt, Integer.valueOf(diem), ghichu);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			if (dsTimDuoc.size() > 0) {
				loadData(dsTimDuoc);
			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy!\nVui lòng kiểm tra lại thông tin tìm kiếm.");
			}
		}

		if (obj.equals(btnLamMoi)) {
			xoaRongNhapLieu();
			try {
				loadData(daoKH.getALLKhachHang());
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
		}
		if (obj.equals(cbLoaiKH)) {
			if (cbLoaiKH.getSelectedItem() != null) {
				try {
					dsTimDuoc = daoKH.getTimKiemDSKH(txtMaKH.getText(), cbLoaiKH.getSelectedItem().toString(),
							txtTenKH.getText(), txtSDT.getText(), Integer.valueOf(txtDiem.getText()), taGhiChu.getText());
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				loadData(dsTimDuoc);
			}
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
		/*
		 * String maKH = txtMaKH.getText(); String tenKH = txtTenKH.getText(); String
		 * sdt = txtSDT.getText(); String diem = txtDiem.getText(); String ghichu =
		 * taGhiChu.getText(); String loaiKH = cbLoaiKH.getSelectedItem().toString();
		 */
		try {
			dsTimDuoc = daoKH.getTimKiemDSKH(txtMaKH.getText(), cbLoaiKH.getSelectedItem().toString(), txtTenKH.getText(),
					txtSDT.getText(), Integer.valueOf(txtDiem.getText()), taGhiChu.getText());
		} catch (RemoteException ex) {
			throw new RuntimeException(ex);
		}
		loadData(dsTimDuoc);
	}

}