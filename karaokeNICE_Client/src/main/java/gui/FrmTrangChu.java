package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;


import dao.DaoDichVu;
import dao.DaoNhanVien;
import dao.DaoPhong;
import entity.DichVu;
import entity.NhanVien;

public class FrmTrangChu extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final String URL = "rmi://26.173.247.75:7878/";
//	private DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
//	private DAO_DichVu dao_DichVu = new DAO_DichVu();
//	private DAO_Phong dao_Phong = new DAO_Phong();
	DaoNhanVien dao_NhanVien = (DaoNhanVien) Naming.lookup(URL + "daoNhanVien");
	DaoDichVu dao_DichVu = (DaoDichVu) Naming.lookup(URL + "daoDichVu");
	DaoPhong dao_Phong = (DaoPhong) Naming.lookup(URL + "daoPhong");


	private static final long serialVersionUID = 1L;
	private JPanel panel_1;
	private JPanel panel_2;
	private JMenuBar jmenu_Border;
	private JMenu mn_HeThong;
	private JMenuItem mntm_TrangChu;
	private JMenuItem mntm_ThongTinCaNhan;
	private JMenuItem mntm_NhanVien;
	private JMenuItem mntm_DichVu;
	private JMenuItem mntm_LoaiDichVu;
	private JMenuItem mntm_TroGiup;
	private JMenuItem mntm_DangXuat;
	private JMenuItem mntm_Thoat;
	private JMenuItem mntm_KhachHang;
	private JMenu mn_XuLy;
	private JMenuItem mntm_DatPhongHat;
	private JMenu mn_TimKiem;
	private JMenuItem mntm_TimPhieuDat;
	private JMenu mn_ThongKe;
	private JMenuItem mntm_ThongKeDoanhThu;
	private JMenuItem mntm_ThongKeKH;
	protected static JTabbedPane jtab_NoiDung;
	public static String maNhanVienTruyen;
	private JPanel panel_TrangChu;
	private JLabel txt_ThongTin;

	private JMenuItem mntm_PhongHat;
	private JMenu mn_DanhMuc;
	private JMenuItem mntm_TaiKhoan;
	private boolean chucNangQL = false, chucNangKT = false, chucNangTN = false;
	private JMenuItem mntm_DonVi;
	private JMenuItem mntm_LoaiPhongHat;
	private JMenuItem mntm_TimKiemDichVu;
	private JMenuItem mntm_TimKiemPhong;
	private JMenuItem mntm_TimKiemHoaDon;
	private JMenuItem mntm_TimKiemNhanVien;
	private JMenuItem mntm_PhongDangSD;
	private JMenuItem mntm_PhongCho;
	private JMenuItem mntm_TimKiemKhachHang;
	private JMenuItem mntm_TimKiemTaiKhoan;
	private JMenuItem mntm_LoaiKhachHang;
	private JMenuItem mntm_LoaiNhanVien;
	private JMenuItem mntm_ThongKeNV;

	public FrmTrangChu(String maNV) throws MalformedURLException, NotBoundException, RemoteException {

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("src/main/java/img/karaoke-logo-template-design-vector_316488-16272.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("QUẢN LÝ QUÁN KARAOKE");
		setBounds(100, 100, 1920, 1040); // Mới chỉnh
		getContentPane().setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1920, 73); // Mới chỉnh
		getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.CENTER);
		kiemTraChucVu(maNV);

		jmenu_Border = new JMenuBar() {
			@Override
			public void paintComponent(final Graphics g) {
				g.setColor(new Color(138, 43, 226));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		jmenu_Border.setForeground(Color.WHITE);
		jmenu_Border.setBorderPainted(false);
		jmenu_Border.setBackground(new Color(138, 43, 226));

		// Hệ thống
		mn_HeThong = new JMenu("Hệ thống ");
		mn_HeThong.setIcon(new ImageIcon("src/main/java/img/system-settings-icon.png"));
		mn_HeThong.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_HeThong.setForeground(Color.WHITE);
		mn_HeThong.setBorder(new EmptyBorder(0, 15, 0, 15));
		jmenu_Border.add(mn_HeThong);

		mntm_TrangChu = new JMenuItem("Trang chủ");
		mntm_TrangChu.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_HeThong.add(mntm_TrangChu);

		mn_HeThong.addSeparator();
		mntm_ThongTinCaNhan = new JMenuItem("Thông tin cá nhân");
		mntm_ThongTinCaNhan.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_HeThong.add(mntm_ThongTinCaNhan);

		mn_HeThong.addSeparator();
		mntm_TroGiup = new JMenuItem("Trợ giúp");
		mntm_TroGiup.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_HeThong.add(mntm_TroGiup);

		mn_HeThong.addSeparator();
		mntm_DangXuat = new JMenuItem("Đăng xuất");
		mntm_DangXuat.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_HeThong.add(mntm_DangXuat);

		mn_HeThong.addSeparator();
		mntm_Thoat = new JMenuItem("Thoát");
		mntm_Thoat.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_HeThong.add(mntm_Thoat);

		// Danh mục
		mn_DanhMuc = new JMenu("Danh mục   ");
		mn_DanhMuc.setIcon(new ImageIcon("src/main/java/img/Checklist-icon.png"));
		mn_DanhMuc.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_DanhMuc.setBackground(new Color(255, 255, 0));
		mn_DanhMuc.setForeground(Color.WHITE);
		mn_DanhMuc.setBorder(new EmptyBorder(0, 15, 0, 15));
		if (chucNangQL)
			jmenu_Border.add(mn_DanhMuc);

		mn_DanhMuc.addSeparator();
		mntm_KhachHang = new JMenuItem("Khách hàng");
		mntm_KhachHang.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_DanhMuc.add(mntm_KhachHang);

		mntm_LoaiKhachHang = new JMenuItem("Loại khách hàng");
		mntm_LoaiKhachHang.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_DanhMuc.add(mntm_LoaiKhachHang);

		mn_DanhMuc.addSeparator();
		mntm_NhanVien = new JMenuItem("Nhân viên");
		mntm_NhanVien.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_DanhMuc.add(mntm_NhanVien);

		mntm_LoaiNhanVien = new JMenuItem("Loại nhân viên");
		mntm_LoaiNhanVien.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_DanhMuc.add(mntm_LoaiNhanVien);

		mn_DanhMuc.addSeparator();
		mntm_DichVu = new JMenuItem("Dịch vụ");
		mntm_DichVu.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_DanhMuc.add(mntm_DichVu);

		mntm_LoaiDichVu = new JMenuItem("Loại dịch vụ");
		mntm_LoaiDichVu.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_DanhMuc.add(mntm_LoaiDichVu);

		mntm_DonVi = new JMenuItem("Đơn vị");
		mntm_DonVi.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_DanhMuc.add(mntm_DonVi);

		mn_DanhMuc.addSeparator();
		mntm_PhongHat = new JMenuItem("Phòng hát");
		mntm_PhongHat.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_DanhMuc.add(mntm_PhongHat);

		mntm_LoaiPhongHat = new JMenuItem("Loại phòng hát");
		mntm_LoaiPhongHat.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_DanhMuc.add(mntm_LoaiPhongHat);

		mn_DanhMuc.addSeparator();
		mntm_TaiKhoan = new JMenuItem("Tài khoản");
		mntm_TaiKhoan.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_DanhMuc.add(mntm_TaiKhoan);
		// Xử lý
		mn_XuLy = new JMenu("Xử lý ");
		mn_XuLy.setIcon(new ImageIcon("src/main/java/img/teamwork.jpg"));
		mn_XuLy.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_XuLy.setBackground(new Color(255, 255, 0));
		mn_XuLy.setForeground(Color.WHITE);
		mn_XuLy.setBorder(new EmptyBorder(0, 15, 0, 15));
		if (chucNangTN)
			jmenu_Border.add(mn_XuLy);

		mntm_DatPhongHat = new JMenuItem("Đặt phòng hát");
		mntm_DatPhongHat.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_XuLy.add(mntm_DatPhongHat);

		mntm_PhongCho = new JMenuItem("Phiếu đặt phòng");
		mntm_PhongCho.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_XuLy.add(mntm_PhongCho);

		mntm_PhongDangSD = new JMenuItem("Phòng đang sử dụng");
		mntm_PhongDangSD.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_XuLy.add(mntm_PhongDangSD);

		// Tìm kiếm
		mn_TimKiem = new JMenu("Tìm kiếm");
		mn_TimKiem.setIcon(new ImageIcon("src/main/java/img/Search-icon.png"));
		mn_TimKiem.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_TimKiem.setBackground(new Color(255, 255, 0));
		mn_TimKiem.setForeground(Color.WHITE);
		mn_TimKiem.setBorder(new EmptyBorder(0, 15, 0, 15));
		jmenu_Border.add(mn_TimKiem);

		mntm_TimKiemPhong = new JMenuItem("Tìm kiếm phòng");
		mntm_TimKiemPhong.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_TimKiem.add(mntm_TimKiemPhong);

		mn_TimKiem.addSeparator();
		mntm_TimPhieuDat = new JMenuItem("Tìm phiếu đặt phòng");
		mntm_TimPhieuDat.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_TimKiem.add(mntm_TimPhieuDat);

		mn_TimKiem.addSeparator();
		mntm_TimKiemDichVu = new JMenuItem("Tìm kiếm dịch vụ");
		mntm_TimKiemDichVu.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_TimKiem.add(mntm_TimKiemDichVu);

		mn_TimKiem.addSeparator();
		mntm_TimKiemNhanVien = new JMenuItem("Tìm kiếm nhân viên");
		mntm_TimKiemNhanVien.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_TimKiem.add(mntm_TimKiemNhanVien);

		mn_TimKiem.addSeparator();
		mntm_TimKiemHoaDon = new JMenuItem("Tìm kiếm hoá đơn");
		mntm_TimKiemHoaDon.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_TimKiem.add(mntm_TimKiemHoaDon);

		mn_TimKiem.addSeparator();
		mntm_TimKiemKhachHang = new JMenuItem("Tìm kiếm khách hàng");
		mntm_TimKiemKhachHang.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_TimKiem.add(mntm_TimKiemKhachHang);

		mn_TimKiem.addSeparator();
		mntm_TimKiemTaiKhoan = new JMenuItem("Tìm kiếm tài khoản");
		mntm_TimKiemTaiKhoan.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_TimKiem.add(mntm_TimKiemTaiKhoan);

		// Thống kê
		mn_ThongKe = new JMenu("Thống kê");
		mn_ThongKe.setIcon(new ImageIcon("src/main/java/img/statistics-market-icon.png"));
		mn_ThongKe.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_ThongKe.setBackground(new Color(255, 255, 0));
		mn_ThongKe.setForeground(Color.WHITE);
		mn_ThongKe.setBorder(new EmptyBorder(0, 15, 0, 15));
		jmenu_Border.add(mn_ThongKe);

		mntm_ThongKeDoanhThu = new JMenuItem("Doanh thu");
		mntm_ThongKeDoanhThu.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_ThongKe.add(mntm_ThongKeDoanhThu);

		mn_ThongKe.addSeparator();
		mntm_ThongKeKH = new JMenuItem("Khách hàng");
		mntm_ThongKeKH.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_ThongKe.add(mntm_ThongKeKH);

		mn_ThongKe.addSeparator();
		mntm_ThongKeNV = new JMenuItem("Nhân viên");
		mntm_ThongKeNV.setFont(new Font("UTM Aircona", Font.BOLD, 20));
		mn_ThongKe.add(mntm_ThongKeNV);

		// Nội dung
		jtab_NoiDung = new JTabbedPane(JTabbedPane.BOTTOM); // Mới chỉnh
		jtab_NoiDung.setBounds(0, 71, 1920, 968); // Mới chỉnh
		jtab_NoiDung.setBackground(Color.red);
		getContentPane().add(jtab_NoiDung);

		// Trang chủ
		panel_TrangChu = new JPanel();
		panel_TrangChu.setBackground(Color.white);
		panel_TrangChu.setLayout(null);
		jtab_NoiDung.add(panel_TrangChu, BorderLayout.CENTER);
		jtab_NoiDung.setTabLayoutPolicy(HIDE_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("Karaoke NICE");
		lblNewLabel.setForeground(SystemColor.textInactiveText);
		lblNewLabel.setBounds(790, 386, 334, 44);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 45));
		panel_TrangChu.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("12 Nguyễn Văn Bảo, Phường 4, Gò Vấp, Thành phố Hồ Chí Minh");
		lblNewLabel_1.setIcon(new ImageIcon("src/main/java/img/icons8-address-30.png"));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(747, 456, 729, 37);
		panel_TrangChu.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("0123 456 789");
		lblNewLabel_2.setIcon(new ImageIcon("src/main/java/img/icons8-phone-30.png"));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(747, 504, 241, 37);
		panel_TrangChu.add(lblNewLabel_2);

		JTextPane txtpnViSologanht = new JTextPane();
		txtpnViSologanht.setBackground(Color.white);
		txtpnViSologanht.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtpnViSologanht.setEditable(false);
		txtpnViSologanht.setText(
				"\tHệ thống karaoke NNICE được phát triển bởi ba thành viên là: Bùi Gia Đại, Nguyễn Viết Mạnh và Nguyễn Tấn Huy.\n\tHệ thống karaoke NNice hiện kinh doanh về phòng thuê để khách sử dụng dịch vụ karaoke. Khách hàng đến sử dụng dịch vụ karaoke chủ yếu là giới trẻ, và giới doanh nghiệp có nhu cầu giải trí."
						+ "Ngoài việc kinh doanh về phòng thuê hát karaoke, NNice còn kinh doanh nhiều loại dịch vụ khác như bổ sung như phục vụ thức ăn, nước uống, phục vụ tiệc cho khách hàng có nhu cầu. \r\n"
						+ "\tCác nhân viên trong cơ sở làm việc theo ca mỗi ngày:\r\n"
						+ "\t\t+Ca 1: 8h sáng đến 13 h.\r\n" + "\t\t+Ca 2: 13h sáng đến 18 h.\r\n"
						+ "\t\t+Ca 3: 18h sáng đến 24 h.\r\n"
						+ "Tùy theo số ca làm việc và doanh thu trong tháng đó, nhân viên sẽ nhận được tiền lương và tiền thưởng tương ứng.\r\n");
		txtpnViSologanht.setBounds(57, 600, 1800, 293);
		panel_TrangChu.add(txtpnViSologanht);

		JLabel lblNewLabel_4 = new JLabel("Giới thiệu:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 28));
		lblNewLabel_4.setBounds(70, 552, 170, 37);
		panel_TrangChu.add(lblNewLabel_4);

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(
				"src/main/java/img/karaoke-logo-template-design-vector_316488-16272.jpg"));
		lblNewLabel_3.setBounds(628, 445, 100, 100);
		panel_TrangChu.add(lblNewLabel_3);

		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("src/main/java/img/Karaoke-LUXURY-Làn-Sóng-Xanh-10.jpg"));
		lblNewLabel_5.setBounds(0, -13, 1943, 375);
		panel_TrangChu.add(lblNewLabel_5);

		JPanel panel_KhoangTrang = new JPanel();
		panel_KhoangTrang.setBackground(new Color(138, 43, 226));
		jmenu_Border.add(panel_KhoangTrang);

		txt_ThongTin = new JLabel("Chức vụ + Thông tin   ");
		txt_ThongTin.setForeground(new Color(255, 255, 255));
		txt_ThongTin.setFont(new Font("UTM Cabaret", Font.BOLD, 40));
		jmenu_Border.add(txt_ThongTin);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(jmenu_Border,
				GroupLayout.PREFERRED_SIZE, 1920, GroupLayout.PREFERRED_SIZE)); // Mới chỉnh
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addComponent(jmenu_Border,
				GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE));
		panel_2.setLayout(gl_panel_2);

		// sự kiện
		hienThiTT(maNV);
		mntm_TrangChu.addActionListener(this);
		mntm_ThongTinCaNhan.addActionListener(this);
		mntm_NhanVien.addActionListener(this);
		mntm_DichVu.addActionListener(this);
		mntm_TroGiup.addActionListener(this);
		mntm_DangXuat.addActionListener(this);
		mntm_Thoat.addActionListener(this);
		mntm_KhachHang.addActionListener(this);
		mntm_DatPhongHat.addActionListener(this);
		mntm_ThongKeDoanhThu.addActionListener(this);
		mntm_ThongKeKH.addActionListener(this);
		mntm_PhongHat.addActionListener(this);
		mntm_TaiKhoan.addActionListener(this);
		mntm_LoaiDichVu.addActionListener(this);
		mntm_LoaiNhanVien.addActionListener(this);
		mntm_LoaiKhachHang.addActionListener(this);
		mntm_DonVi.addActionListener(this);
		mntm_LoaiPhongHat.addActionListener(this);
		mntm_TimKiemPhong.addActionListener(this);
		mntm_TimPhieuDat.addActionListener(this);
		mntm_TimKiemDichVu.addActionListener(this);
		mntm_TimKiemNhanVien.addActionListener(this);
		mntm_TimKiemHoaDon.addActionListener(this);
		mntm_PhongDangSD.addActionListener(this);
		mntm_PhongCho.addActionListener(this);
		mntm_TimKiemKhachHang.addActionListener(this);
		mntm_TimKiemTaiKhoan.addActionListener(this);
		mntm_ThongKeNV.addActionListener(this);

		// phím tắt - CHƯA XONG
		mntm_TrangChu.setMnemonic(KeyEvent.VK_ESCAPE);
		mntm_ThongTinCaNhan.setMnemonic(KeyEvent.VK_2);
		mntm_TroGiup.setMnemonic(KeyEvent.VK_T);
		mntm_DangXuat.setMnemonic(KeyEvent.VK_D);
		mntm_Thoat.setMnemonic(KeyEvent.VK_X);
		mntm_KhachHang.setMnemonic(KeyEvent.VK_K);
		mntm_NhanVien.setMnemonic(KeyEvent.VK_N);
		mntm_DichVu.setMnemonic(KeyEvent.VK_V);
		mntm_DatPhongHat.setMnemonic(KeyEvent.VK_N);
		mntm_TimPhieuDat.setMnemonic(KeyEvent.VK_T);
		mntm_ThongKeDoanhThu.setMnemonic(KeyEvent.VK_8);
		mntm_ThongKeKH.setMnemonic(KeyEvent.VK_F9);
		// kiểm tra sinh nhật
		kiemTraSinhNhat();
	}

	public void kiemTraSinhNhat() throws RemoteException {
		NhanVien nVien = dao_NhanVien.getNhanVienTheoMa(maNhanVienTruyen);
		LocalDateTime ngayHT = LocalDateTime.now();
		if (nVien.getNgaySinh().getDayOfYear() == ngayHT.getDayOfYear()
				&& nVien.getNgaySinh().getMonthValue() == ngayHT.getMonthValue()) {
			txt_ThongTin.setIcon(new ImageIcon("src/main/java/img/icons8-gift-32.png"));
			txt_ThongTin.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					thongBaoSinhNhat();
				}
			});
		}
	}

	public void thongBaoSinhNhat() {
		JOptionPane.showMessageDialog(this, "Chúc bạn sinh nhật vui vẻ!", "Mừng sinh nhật",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void kiemTraChucVu(String maNhanVien) throws RemoteException {
		if (maNhanVien.equals("admin")) {
			chucNangQL = true;
			chucNangTN = true;
			chucNangKT = true;
			return;
		}
		NhanVien nhanVien = dao_NhanVien.getNhanVienTheoMa(maNhanVien.trim());
		if (nhanVien.getLoaiNhanVien().getMaLoaiVN().equals("LNV001")) {
			chucNangQL = true;
			return;
		}
		if (nhanVien.getLoaiNhanVien().getMaLoaiVN().equals("LNV002")) {
			chucNangTN = true;
			return;
		}
		if (nhanVien.getLoaiNhanVien().getMaLoaiVN().equals("LNV003")) {
			chucNangKT = true;
			return;
		}
	}

	public void hienThiTT(String maNhanVien) throws RemoteException {
		// kiểm tra admin
		if (maNhanVien.equals("admin")) {
			txt_ThongTin.setText("Admin - Toàn quyền ");
			maNhanVienTruyen = "admin";
			return;
		}
		maNhanVienTruyen = maNhanVien;
		// vì đã vượt qua được đăng nhập nên chắc chắn rằng mã nv tồn tại trong danh
		// sách nên ko xét trường hợp ngoại lệ;
		for (NhanVien nv : dao_NhanVien.getALLNhanVien())
			if (nv.getMaNhanVien().equals(maNhanVien)) {
				txt_ThongTin.setText(nv.getTenNhanVien() + " - " + nv.getLoaiNhanVien().getTenLoai() + " ");
				return;
			}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// Hệ thống
		if (obj.equals(mntm_TrangChu)) {
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(panel_TrangChu);
		} else if (obj.equals(mntm_ThongTinCaNhan)) {
			FrmXemThongTinTK frmXemThongTinTK = null;
			try {
				frmXemThongTinTK = new FrmXemThongTinTK(maNhanVienTruyen);
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmXemThongTinTK.pnBorder);
		} else if (obj.equals(mntm_TroGiup)) {
			try {
				// Lấy đường dẫn project + dường dẫn file help
				String url = "";
				NhanVien nhanVien = dao_NhanVien.getNhanVienTheoMa(maNhanVienTruyen);
				if (maNhanVienTruyen.equals("admin"))
					url = System.getProperty("user.dir")
							+ "\\help\\Nhom07_2022_7_ApplicationDevelopment_UserManual.docx";
				else if (nhanVien.getLoaiNhanVien().getMaLoaiVN().equals("LNV001"))
					url = System.getProperty("user.dir") + "\\help\\HuongDanQuanLy.pdf";
				else if (nhanVien.getLoaiNhanVien().getMaLoaiVN().equals("LNV002"))
					url = System.getProperty("user.dir") + "\\help\\HuongDanThuNgan.pdf";
				else if (nhanVien.getLoaiNhanVien().getMaLoaiVN().equals("LNV003"))
					url = System.getProperty("user.dir") + "\\help\\HuongDanKeToan.pdf";
				File file = new File(url);
				Desktop dt = Desktop.getDesktop();
				dt.open(file);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else if (obj.equals(mntm_DangXuat)) {
			if (JOptionPane.showConfirmDialog(null, "Bạn có muốn đăng xuất ?", null,
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				FrmDangNhap dangNhap = null;
				try {
					dangNhap = new FrmDangNhap();
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				dangNhap.setVisible(true);
				dangNhap.setLocationRelativeTo(null);
				dispose();
			}
		} else if (obj.equals(mntm_Thoat)) {
			System.exit(0);
		}
		// Danh mục
		else if (obj.equals(mntm_KhachHang)) {
			FrmQuanLyKhachHang frmQuanLyKhachHang = null;
			try {
				frmQuanLyKhachHang = new FrmQuanLyKhachHang(maNhanVienTruyen, "", "");
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmQuanLyKhachHang.pnBorder);
		}
		else if (obj.equals(mntm_NhanVien)) {
			FrmQuanLyNhanVien frmQuanLyNhanVien = null;
			try {
				frmQuanLyNhanVien = new FrmQuanLyNhanVien(dao_NhanVien.getALLNhanVienKhongCoADMIN());
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmQuanLyNhanVien.pnBorder);
		}
		else if (obj.equals(mntm_DichVu)) {
			FrmQuanLyDichVu frmQuanLyDichVu = null;
			try {
				frmQuanLyDichVu = new FrmQuanLyDichVu((ArrayList<DichVu>) dao_DichVu.getAllDichVu());
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmQuanLyDichVu.pnlBorder);
		}
		else if (obj.equals(mntm_LoaiDichVu)) {
			FrmQuanLyLoaiDichVu frmQuanLyLoaiDichVu = null;
			try {
				frmQuanLyLoaiDichVu = new FrmQuanLyLoaiDichVu();
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmQuanLyLoaiDichVu.pnlBorder);
		}
		else if (obj.equals(mntm_DonVi)) {
			FrmQuanLyDonVi frmQuanLyDonVi = null;
			try {
				frmQuanLyDonVi = new FrmQuanLyDonVi();
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmQuanLyDonVi.pnlBorder);
		}
		else if (obj.equals(mntm_PhongHat)) {
			FrmQuanLyPhong frmQuanLyPhong = null;
			try {
				frmQuanLyPhong = new FrmQuanLyPhong(dao_Phong.getDanhSachPhong());
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmQuanLyPhong.pnlBorder);
		} else if (obj.equals(mntm_LoaiPhongHat)) {
			FrmQuanLyLoaiPhong frmQuanLyLoaiPhong = null;
			try {
				frmQuanLyLoaiPhong = new FrmQuanLyLoaiPhong();
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmQuanLyLoaiPhong.pnlBorder);
		} else if (obj.equals(mntm_TaiKhoan)) {
			FrmQuanLyTaiKhoan frmQuanLyTaiKhoan = null;
			try {
				frmQuanLyTaiKhoan = new FrmQuanLyTaiKhoan();
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmQuanLyTaiKhoan.pnBorder);
		}
		else if (obj.equals(mntm_LoaiKhachHang)) {
			FrmQuanLyLoaiKhacHang frmQuanLyLoaiKH = null;
			try {
				frmQuanLyLoaiKH = new FrmQuanLyLoaiKhacHang();
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmQuanLyLoaiKH.pnBorder);
		}
		else if (obj.equals(mntm_LoaiNhanVien)) {
			FrmQuanLyLoaiNhanVien frmQuanLyLoaiNV = null;
			try {
				frmQuanLyLoaiNV = new FrmQuanLyLoaiNhanVien();
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmQuanLyLoaiNV.pnBorder);
		}

		// Xử lý
		else if (obj.equals(mntm_DatPhongHat)) {
			FrmXyLyDatPhong frmDatPhong = null;
			try {
				frmDatPhong = new FrmXyLyDatPhong(maNhanVienTruyen);
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmDatPhong.pnBorder);
		} else if (obj.equals(mntm_PhongCho)) {
			FrmXyLyPhieuDatPhong frmDatPhongDSD = null;
			try {
				frmDatPhongDSD = new FrmXyLyPhieuDatPhong(maNhanVienTruyen, "");
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmDatPhongDSD.pnBorder);
		} else if (obj.equals(mntm_PhongDangSD)) {
			FrmXuLyPhongDangSuDung frmDatPhongDSD = null;
			try {
				frmDatPhongDSD = new FrmXuLyPhongDangSuDung(maNhanVienTruyen, "");
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmDatPhongDSD.pnBorder);
		}
//		// Tìm kiếm
		else if (obj.equals(mntm_TimKiemPhong)) {
			FrmTimKiemPhong frmTimKiemPhong = null;
			try {
				frmTimKiemPhong = new FrmTimKiemPhong(maNhanVienTruyen);
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmTimKiemPhong.pnlBorder);
		}
		else if (obj.equals(mntm_TimPhieuDat)) {
			FrmTimKiemPhieuDatPhong frmTimKiemPhieuDatPhong = null;
			try {
				frmTimKiemPhieuDatPhong = new FrmTimKiemPhieuDatPhong();
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmTimKiemPhieuDatPhong.pnBorder);
		}
		else if (obj.equals(mntm_TimKiemDichVu)) {
			FrmTimKiemDichVu frmTimKiemDichVu = null;
			try {
				frmTimKiemDichVu = new FrmTimKiemDichVu(maNhanVienTruyen);
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmTimKiemDichVu.pnlBorder);
		} else if (obj.equals(mntm_TimKiemNhanVien)) {
			FrmTimKiemNhanVien frmTimKiemNhanVien = null;
			try {
				frmTimKiemNhanVien = new FrmTimKiemNhanVien(maNhanVienTruyen);
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmTimKiemNhanVien.pnBorder);
		}
		else if (obj.equals(mntm_TimKiemHoaDon)) {
			FrmTimKiemHoaDon frmTimKiemHoaDon = null;
			try {
				frmTimKiemHoaDon = new FrmTimKiemHoaDon();
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmTimKiemHoaDon.pnlBorder);
		}
		else if (obj.equals(mntm_TimKiemKhachHang)) {
			FrmTimKiemKhachHang frmTimKiemKhachHang = null;
			try {
				frmTimKiemKhachHang = new FrmTimKiemKhachHang();
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmTimKiemKhachHang.pnBorder);
		}
		else if (obj.equals(mntm_TimKiemTaiKhoan)) {
			FrmTimKiemTaiKhoan frmTimKiemTaiKhoan = null;
			try {
				frmTimKiemTaiKhoan = new FrmTimKiemTaiKhoan();
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmTimKiemTaiKhoan.pnBorder);
		}
//		 Thống kê
		else if (obj.equals(mntm_ThongKeDoanhThu)) {
			FrmThongKeDoanhThu frmThongKeDoanhThu = null;
			try {
				frmThongKeDoanhThu = new FrmThongKeDoanhThu();
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmThongKeDoanhThu.pnlBorder);
		} else if (obj.equals(mntm_ThongKeKH)) {
			FrmThongKeKhachHang frmThongKeKH = null;
			try {
				frmThongKeKH = new FrmThongKeKhachHang();
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmThongKeKH.pnlBorder);
		} else if (obj.equals(mntm_ThongKeNV)) {
			FrmThongKeNhanVien frmTKNhanVien = null;
			try {
				frmTKNhanVien = new FrmThongKeNhanVien();
			} catch (MalformedURLException ex) {
				throw new RuntimeException(ex);
			} catch (NotBoundException ex) {
				throw new RuntimeException(ex);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			jtab_NoiDung.remove(jtab_NoiDung.getSelectedComponent());
			jtab_NoiDung.add(frmTKNhanVien.pnlBorder);
		}

		// Mở frm khác

	}

	public void windowClosing(WindowEvent e) {
		dispose();
	}

}
