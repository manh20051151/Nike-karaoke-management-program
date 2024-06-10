package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

//import dao.DAO_ChiTietHoaDon_Phong;
//import dao.DAO_Phong;
import dao.DaoChiTietHoaDon_Phong;
import dao.DaoPhong;
import entity.ChiTietHoaDon_Phong;
import entity.HoaDon;
import entity.Phong;

public class FrmChuyenPhong extends JFrame implements ActionListener, MouseListener {
	private static final String URL = "rmi://26.173.247.75:7878/";

	DaoPhong dao_Phong = (DaoPhong) Naming.lookup(URL + "daoPhong");
	DaoChiTietHoaDon_Phong dao_CTHD_Phong = (DaoChiTietHoaDon_Phong) Naming.lookup(URL + "daoChiTietHoaDon_phong");

//	private DAO_Phong dao_Phong = new DAO_Phong();
//	private DAO_ChiTietHoaDon_Phong dao_CTHD_Phong = new DAO_ChiTietHoaDon_Phong();
	public static JPanel pnBorder;
	private static Phong phong;
	private static String maPhongTruyen;
	private static String maPhongDuocChon;

	private JButton btn_ChuyenPhong;
	private JTable tb_KhachHang;
	private DefaultTableModel modelPhong;
	private JTextField txt_TimKiem;
	private JLabel lbl_Phong;
	private JButton btn_TimKiem;
	private JButton btn_QuayLai;
	private String maNhanVienTruyen;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmChuyenPhong frame = new FrmChuyenPhong("P001", "NV001");
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
	// trở về trang đặt phòng
//	private void formWindowClosing(java.awt.event.WindowEvent evt) {// GEN-FIRST:event_formWindowClosing
//		FrmChuyenPhong main = new FrmChuyenPhong(maPhongTruyen, maNhanVienTruyen);
//
//	}

	public FrmChuyenPhong(String maPhongNhan, String maNhanVienNhan) throws MalformedURLException, NotBoundException, RemoteException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 926, 760);
		setLocationRelativeTo(null);
		setTitle("Chuyển phòng");
		// Kiểu chữ
		Font font1 = new Font("Times New Roman", Font.BOLD, 36);
		Font font2 = new Font("Times New Roman", Font.PLAIN, 24);

		// Màu chữ
		Color color1 = new Color(138, 43, 226); // BlueViolet
		Color color2 = new Color(233, 221, 244);
		Color color3 = new Color(0, 207, 210);
		Color color_trang = Color.white;
		Color color_xanh = SystemColor.decode("#76EEC6");

		pnBorder = new JPanel();
		pnBorder.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnBorder.setLayout(null);

		setContentPane(pnBorder);

		JPanel panel_TieuDe = new JPanel();
		panel_TieuDe.setBounds(0, 0, 910, 68);
		pnBorder.add(panel_TieuDe);
		panel_TieuDe.setLayout(null);
		panel_TieuDe.setBackground(color3);

		JLabel lbl_TieuDe_1 = new JLabel("Chuyển phòng");
		lbl_TieuDe_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TieuDe_1.setForeground(Color.WHITE);
		lbl_TieuDe_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbl_TieuDe_1.setBounds(0, 0, 911, 68);
		panel_TieuDe.add(lbl_TieuDe_1);

		JLabel lbl_TieuDe = new JLabel("Chuyển phòng");
		lbl_TieuDe.setIcon(new ImageIcon(
				"src/main/java/img/vecteezy_abstract-purple-fluid-wave-background_.jpg"));
		lbl_TieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_TieuDe.setForeground(Color.WHITE);
		lbl_TieuDe.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbl_TieuDe.setBounds(0, 0, 911, 68);
		panel_TieuDe.add(lbl_TieuDe);

		// Panle trái
		JPanel panel_Phai = new JPanel();
		panel_Phai.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 51)));
		panel_Phai.setLayout(null);
		panel_Phai.setBackground(Color.WHITE);
		panel_Phai.setBounds(0, 67, 910, 654);
		pnBorder.add(panel_Phai);

		// bảng dịch vụ phòng
		JPanel pn_BangTTPhong = new JPanel();
		pn_BangTTPhong.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Danh sách phòng còn trống", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 51)));
		pn_BangTTPhong.setBackground(Color.WHITE);
		pn_BangTTPhong.setBounds(10, 133, 890, 438);
		panel_Phai.add(pn_BangTTPhong);

		// tạo cột cho bảng của khách hàng
		modelPhong = new DefaultTableModel();
		modelPhong.addColumn("Mã phòng");
		modelPhong.addColumn("Trạng thái");
		modelPhong.addColumn("Sức chứa");
		modelPhong.addColumn("Giá tiền mỗi giờ");
		pn_BangTTPhong.setLayout(null);
		tb_KhachHang = new JTable(modelPhong);
		JScrollPane jScrollPane_KH = new JScrollPane(tb_KhachHang, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPane_KH.setBounds(6, 16, 874, 411);
		pn_BangTTPhong.add(jScrollPane_KH);
		JTableHeader tbHeader_KH = tb_KhachHang.getTableHeader();
		tbHeader_KH.setFont(font2);
		tbHeader_KH.setBackground(color2);
		tb_KhachHang.setFont(font2);
		tb_KhachHang.setRowHeight(35);
		tb_KhachHang.setDefaultEditor(Object.class, null);

		JLabel lbl_PhongHT = new JLabel("Phòng hiện tại:");
		lbl_PhongHT.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lbl_PhongHT.setBounds(23, 26, 203, 48);
		panel_Phai.add(lbl_PhongHT);

		lbl_Phong = new JLabel(maPhongNhan + "  ->");
		lbl_Phong.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbl_Phong.setBounds(236, 26, 252, 48);
		panel_Phai.add(lbl_Phong);

		JLabel lbl_TimPhong = new JLabel("Mã phòng:");
		lbl_TimPhong.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbl_TimPhong.setBounds(450, 73, 130, 48);
		panel_Phai.add(lbl_TimPhong);

		txt_TimKiem = new JTextField();

		txt_TimKiem.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txt_TimKiem.setColumns(10);
		txt_TimKiem.setBounds(565, 79, 203, 37);
		panel_Phai.add(txt_TimKiem);

		btn_TimKiem = new JButton("Tìm ");
		btn_TimKiem.setIcon(new ImageIcon("src/main/java/img/Search-icon.png"));
		btn_TimKiem.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_TimKiem.setBounds(778, 79, 122, 37);
		panel_Phai.add(btn_TimKiem);

		btn_ChuyenPhong = new JButton();
		btn_ChuyenPhong.setHorizontalAlignment(SwingConstants.RIGHT);
		btn_ChuyenPhong.setIcon(new ImageIcon("src/main/java/img/icons8-transfer-50.png"));
		btn_ChuyenPhong.setForeground(Color.WHITE);
		btn_ChuyenPhong.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btn_ChuyenPhong.setBackground(new Color(138, 43, 226));
		btn_ChuyenPhong.setBounds(590, 590, 310, 53);
		panel_Phai.add(btn_ChuyenPhong);
		JLabel txt_ChuyenPhong = new JLabel("Chuyển phòng");
		txt_ChuyenPhong.setForeground(Color.WHITE);
		txt_ChuyenPhong.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn_ChuyenPhong.add(txt_ChuyenPhong);

		btn_QuayLai = new JButton("Quay lại");
		btn_QuayLai.setBackground(new Color(51, 153, 51));
		btn_QuayLai.setBounds(20, 590, 228, 53);
		panel_Phai.add(btn_QuayLai);
		btn_QuayLai.setForeground(Color.WHITE);
		btn_QuayLai.setFont(new Font("Tahoma", Font.BOLD, 30));
		btn_QuayLai.setIcon(new ImageIcon("src/main/java/img/return.png"));
		btn_QuayLai.setBackground(new Color(5, 136, 227));

//		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//		addWindowListener(new java.awt.event.WindowAdapter() {
//			public void windowClosing(java.awt.event.WindowEvent evt) {
//				formWindowClosing(evt);
//			}
//		});
		// xử lý sự kiện
		maPhongTruyen = maPhongNhan;
		maNhanVienTruyen = maNhanVienNhan;
		loadDuLieu();
		tb_KhachHang.addMouseListener(this);
		btn_TimKiem.addActionListener(this);
		btn_ChuyenPhong.addActionListener(this);
		btn_QuayLai.addActionListener(this);
	}

	public void loadDuLieu() throws RemoteException {
		ArrayList<Phong> dsPhong = dao_Phong.getDanhSachPhong();
		DecimalFormat df = new DecimalFormat("###,###,###");
		for (Phong p : dsPhong) {
			if (!p.getMaPhong().equals(maPhongTruyen) && p.getTrangThaiPhong().equals("Trống")) {
				String row[] = { p.getMaPhong(), p.getTrangThaiPhong(), p.getSucChua() + "",
						df.format(p.getLoaiPhong().getGia()) + " VNĐ" };
				modelPhong.addRow(row);
			} else {
				phong = p;
			}
		}
	}

	public int timTheoMaPhong(String maPhongTim) {
		for (int i = 0; i < modelPhong.getRowCount(); i++) {
			if (modelPhong.getValueAt(i, 0).toString().equals(maPhongTim)) {
				return i;
			}
		}
		return -1;
	}

	public void capNhapTheoTim() {
		for (int i = 0; i < modelPhong.getRowCount(); i++) {
			modelPhong.removeRow(i);
		}
	}

	public boolean setTrangThaiPhong(String maPhong, String trangThai) throws RemoteException {
		Phong phong = dao_Phong.getPhongTheoMa(maPhong);
		phong.setTrangThaiPhong(trangThai);
		return dao_Phong.capNhapPhong(phong);
	}

	public boolean chuyenPhong() throws RemoteException {
		ArrayList<ChiTietHoaDon_Phong> ds_CTHD_Phong = dao_CTHD_Phong.getDSTheoMaPhong(maPhongTruyen);
		HoaDon hoaDon = ds_CTHD_Phong.get(ds_CTHD_Phong.size() - 1).getHoaDon();
		LocalDateTime thoiGianHienTai = LocalDateTime.now();
		Phong phongDuocChonPhong = dao_Phong.getPhongTheoMa(maPhongDuocChon);
		ChiTietHoaDon_Phong cthd_PMoi = new ChiTietHoaDon_Phong(hoaDon, phongDuocChonPhong, thoiGianHienTai, null);
		ChiTietHoaDon_Phong cthd_PCu = dao_CTHD_Phong.getCTHD_Phong(maPhongTruyen, hoaDon.getMaHoaDon());
		ChiTietHoaDon_Phong cthd_PCuCapNhap = new ChiTietHoaDon_Phong(cthd_PCu.getHoaDon(), cthd_PCu.getPhong(),
				cthd_PCu.getGioVao(), thoiGianHienTai);
		// cập nhập hoá đơn cũ
		System.err.println(cthd_PCuCapNhap);
		System.out.println(cthd_PCuCapNhap);
		if (!dao_CTHD_Phong.capNhap(cthd_PCuCapNhap))
			return false;
		// cập nhập trạng thái phòng hiện tại
		if (!setTrangThaiPhong(maPhongTruyen, "Trống"))
			return false;
		// thêm chi tiết hoá đơn _ phòng mới vào
		if (!dao_CTHD_Phong.themCTHD_PMoi(cthd_PMoi))
			return false;
		// cập nhập trạng thái phòng mới
		if (!setTrangThaiPhong(maPhongDuocChon, "Đang sử dụng"))
			return false;
		return true;
	}

	public void thongBao(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.INFORMATION_MESSAGE);
	}

	public void thongBaoLoi(String noiDung, String tieuDe) {
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btn_QuayLai)) {
			try {
				FrmXuLyPhongDangSuDung.dsDLHienTai = dao_Phong.getDSTheoTTphong("Đang sử dụng");
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			FrmXuLyPhongDangSuDung.docDLVaoBang();
			dispose();
		}
		if (obj.equals(btn_TimKiem)) {
			String maTim = txt_TimKiem.getText();
			if (timTheoMaPhong(maTim) == -1)
				thongBaoLoi("Mã phòng nhập '" + maTim + "' không tồn tại !", "Lỗi tìm kiếm");
			else {
				int viTri = timTheoMaPhong(maTim);
				tb_KhachHang.setRowSelectionInterval(viTri, viTri);
			}
		}
		if (obj.equals(btn_ChuyenPhong)) {
			if (maPhongDuocChon != null) {
				int hoiLai = JOptionPane.showConfirmDialog(this, "Bạn xác nhận chuyển sang phòng " + maPhongDuocChon,
						"Xác nhận xoá", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (hoiLai == JOptionPane.YES_OPTION) {
					try {
						if (chuyenPhong()) {
							thongBao("Chuyển phòng thành công !\n Phòng " + maPhongTruyen + " được truyển thành phòng "
									+ maPhongDuocChon, "Thông báo");
							FrmXuLyPhongDangSuDung.dsDLHienTai = dao_Phong.getDSTheoTTphong("Đang sử dụng");
							FrmXuLyPhongDangSuDung.docDLVaoBang();
							dispose();
						} else {
							thongBaoLoi("Chuyển phòng không thành công !", "Lỗi hệ thống ");
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			} else {
				thongBaoLoi("Bạn chưa chọn phòng chuyển !", "Lỗi chuyển phòng ");
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int hangDuocChon = tb_KhachHang.getSelectedRow();
		String maPhong = modelPhong.getValueAt(hangDuocChon, 0).toString();
		maPhongDuocChon = maPhong;
		lbl_Phong.setText(maPhongTruyen + "  ->  " + maPhong);
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object obj = e.getSource();
		if (obj.equals(txt_TimKiem)) {
			String maTim = txt_TimKiem.getText();
			if (timTheoMaPhong(maTim) == -1)
				JOptionPane.showMessageDialog(this, "Mã phòng nhập '" + maTim + "' không tồn tại !", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			else {
				int viTri = timTheoMaPhong(maTim);
				tb_KhachHang.setRowSelectionInterval(viTri, viTri);
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
