package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//import dao.DAO_NhanVien;
//import dao.DAO_TaiKhoan;
import dao.DaoNhanVien;
import dao.DaoTaiKhoan;
import entity.LoaiNhanVien;
import entity.NhanVien;
import entity.TaiKhoan;

import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import com.toedter.calendar.JDateChooser;
import java.awt.Dimension;

public class FrmXemThongTinTK extends JFrame implements ActionListener {
	private static final String URL = "rmi://26.173.247.75:7878/";
	DaoNhanVien dao_NhanVien = (DaoNhanVien) Naming.lookup(URL + "daoNhanVien");
	DaoTaiKhoan dao_TaiKhoan = (DaoTaiKhoan) Naming.lookup(URL + "daoTaiKhoan");

//	private DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
//	private DAO_TaiKhoan dao_TaiKhoan = new DAO_TaiKhoan();
//	private DAO_NhanVien dao_NhanVien = new DAO_NhanVien();
//	private DAO_TaiKhoan dao_TaiKhoan = new DAO_TaiKhoan();

	public static JPanel pnBorder;

	private JPanel pnThongTinTK;
	private JPanel pnTTTK;
	private JPanel pnDoiMK;
	private JTextField txtHoTen;
	private JTextField txtCMND;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JLabel lbMKC;
	private JLabel lbMKM;
	private JLabel lbXNMK;
	private JButton btnDoiMK;
	private JPasswordField txtMKC;
	private JPasswordField txtMKM;
	private JPasswordField txtXNMK;


	private JComboBox cbGioiTinh;
	private JLabel lblHinhAnh;
	private JButton btnCapNhat;
	private JButton btnLuu;
	private String ma;
	private String tentk;
	private JButton btnLuuMK;
	private JRadioButton rdbtnHienMK;

	private JDateChooser txtNgaySinh;
	private JTextField txtGmail;

	public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
		new FrmXemThongTinTK("NV001").setVisible(true);
	}

	public FrmXemThongTinTK(String maNV) throws MalformedURLException, NotBoundException, RemoteException {
		ma = maNV;
		tentk = dao_TaiKhoan.getTaiKhoanTheoMa(maNV).getTenTaiKhoan();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setHgap(20);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);

		Font font1 = new Font("Times New Roman", Font.BOLD, 36);
		Font font2 = new Font("Times New Roman", Font.PLAIN, 24);

		// Màu chữ
		Color color1 = new Color(138, 43, 226); // BlueViolet
		Color color2 = new Color(233, 221, 244);

		// Nội dung
		pnBorder = new JPanel();
		pnBorder.setBackground(new Color(233, 221, 244));
		pnBorder.setLayout(null);

		// pnBorder = new JTabbedPane(JTabbedPane.EAST);
		pnBorder.setBorder(null);
		pnBorder.setBackground(Color.WHITE);
		getContentPane().add(pnBorder, BorderLayout.CENTER);
		pnBorder.setLayout(new BorderLayout());
		// pnBorder.setBorder(new EmptyBorder(20, 20, 20, 20));
		pnBorder.setBackground(color2);

		pnThongTinTK = new JPanel();
		pnThongTinTK.setBackground(Color.WHITE);
		pnThongTinTK.setBorder(null);
		pnBorder.add(pnThongTinTK);
		pnThongTinTK.setLayout(null);

		pnTTTK = new JPanel();
		pnTTTK.setBackground(Color.WHITE);
		pnTTTK.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin t\u00E0i kho\u1EA3n", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pnTTTK.setBounds(0, 0, 1923, 528);
		pnThongTinTK.add(pnTTTK);
		pnTTTK.setLayout(null);

		JLabel lbHoTen = new JLabel("Họ tên:");
		lbHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbHoTen.setBounds(30, 71, 154, 29);
		pnTTTK.add(lbHoTen);

		JLabel lbCMND = new JLabel("CMND:");
		lbCMND.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbCMND.setBounds(30, 271, 154, 29);
		pnTTTK.add(lbCMND);

		JLabel lbDiaChi = new JLabel("Địa chỉ:");
		lbDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbDiaChi.setBounds(30, 171, 154, 29);
		pnTTTK.add(lbDiaChi);

		txtHoTen = new JTextField(17);
		txtHoTen.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtHoTen.setBackground(new Color(233, 221, 244));
		txtHoTen.setBounds(194, 68, 600, 35);
		pnTTTK.add(txtHoTen);

		JLabel lbNgaySinh = new JLabel("Ngày sinh:");
		lbNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbNgaySinh.setBounds(866, 71, 154, 29);
		pnTTTK.add(lbNgaySinh);

		txtCMND = new JTextField(17);
		txtCMND.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtCMND.setBackground(new Color(233, 221, 244));
		txtCMND.setBounds(194, 268, 600, 35);
		pnTTTK.add(txtCMND);

		txtDiaChi = new JTextField(17);
		txtDiaChi.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtDiaChi.setBackground(new Color(233, 221, 244));
		txtDiaChi.setBounds(194, 168, 600, 35);
		pnTTTK.add(txtDiaChi);

		JLabel lbGioiTinh = new JLabel("Giới tính:");
		lbGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbGioiTinh.setBounds(866, 171, 154, 29);
		pnTTTK.add(lbGioiTinh);

		JLabel lbSDT = new JLabel("Số điện thoại:");
		lbSDT.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbSDT.setBounds(866, 271, 154, 29);
		pnTTTK.add(lbSDT);

		txtSDT = new JTextField(17);
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtSDT.setBackground(new Color(233, 221, 244));
		txtSDT.setBounds(1030, 268, 600, 35);
		pnTTTK.add(txtSDT);

		btnCapNhat = new JButton("Cập nhật");
		btnCapNhat.setForeground(Color.WHITE);
		btnCapNhat.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnCapNhat.setBackground(new Color(138, 43, 226));
		btnCapNhat.setBounds(760, 456, 159, 37);
		pnTTTK.add(btnCapNhat);

		cbGioiTinh = new JComboBox();
		cbGioiTinh.setModel(new DefaultComboBoxModel(new String[] { "Nam", "Nữ" }));
		cbGioiTinh.setBounds(1030, 167, 600, 37);
		pnTTTK.add(cbGioiTinh);
		cbGioiTinh.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		cbGioiTinh.setBackground(new Color(233, 221, 244));

		lblHinhAnh = new JLabel("");
		lblHinhAnh.setBackground(Color.WHITE);
		lblHinhAnh.setBounds(1686, 71, 195, 229);
		pnTTTK.add(lblHinhAnh);

		btnLuu = new JButton("Lưu");
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnLuu.setBackground(new Color(138, 43, 226));
		btnLuu.setBounds(980, 456, 159, 37);
		pnTTTK.add(btnLuu);

		pnDoiMK = new JPanel();
		pnDoiMK.setBackground(Color.WHITE);
		pnDoiMK.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"\u0110\u1ED5i m\u1EADt kh\u1EA9u", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnDoiMK.setBounds(0, 539, 1923, 418);
		pnThongTinTK.add(pnDoiMK);
		pnDoiMK.setLayout(null);

		lbMKC = new JLabel("Mật khẩu cũ:");
		lbMKC.setBounds(30, 58, 253, 29);
		lbMKC.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		pnDoiMK.add(lbMKC);

		lbMKM = new JLabel("Mật khẩu mới:");
		lbMKM.setBackground(Color.WHITE);
		lbMKM.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbMKM.setBounds(30, 145, 253, 29);
		pnDoiMK.add(lbMKM);

		lbXNMK = new JLabel("Xác nhận mật khẩu mới:");
		lbXNMK.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbXNMK.setBounds(30, 232, 253, 29);
		pnDoiMK.add(lbXNMK);

		btnDoiMK = new JButton("Đổi mật khẩu");
		btnDoiMK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDoiMK.setForeground(Color.WHITE);
		btnDoiMK.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnDoiMK.setBackground(new Color(138, 43, 226));
		btnDoiMK.setBounds(673, 319, 238, 37);
		pnDoiMK.add(btnDoiMK);

		txtMKC = new JPasswordField();
		txtMKC.setBackground(new Color(233, 221, 244));
		txtMKC.setBounds(293, 52, 1595, 34);
		pnDoiMK.add(txtMKC);

		txtMKM = new JPasswordField();
		txtMKM.setBackground(new Color(233, 221, 244));
		txtMKM.setBounds(293, 146, 1595, 34);
		pnDoiMK.add(txtMKM);

		txtXNMK = new JPasswordField();
		txtXNMK.setBackground(new Color(233, 221, 244));
		txtXNMK.setBounds(293, 233, 1595, 34);
		pnDoiMK.add(txtXNMK);
		rdbtnHienMK = new JRadioButton("Hiển thị mật khẩu");
		rdbtnHienMK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdbtnHienMK.setBackground(Color.WHITE);
		rdbtnHienMK.setBounds(1682, 271, 206, 30);
		rdbtnHienMK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rdbtnHienMK.isSelected()) {
					txtMKC.setEchoChar((char) 0);
					txtMKM.setEchoChar((char) 0);
					txtXNMK.setEchoChar((char) 0);
				} else if (rdbtnHienMK.isSelected() == false) {
					txtMKC.setEchoChar('*');
					txtMKM.setEchoChar('*');
					txtXNMK.setEchoChar('*');
				}

			}
		});
		pnDoiMK.add(rdbtnHienMK);

		btnLuuMK = new JButton("Lưu mật khẩu mới");
		btnLuuMK.setForeground(Color.WHITE);
		btnLuuMK.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnLuuMK.setBackground(new Color(138, 43, 226));
		btnLuuMK.setBounds(977, 319, 238, 37);
		pnDoiMK.add(btnLuuMK);
		txtNgaySinh = new JDateChooser();
		txtNgaySinh.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtNgaySinh.setPreferredSize(new Dimension(30, 35));
		txtNgaySinh.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		txtNgaySinh.setEnabled(false);
		txtNgaySinh.setBackground(new Color(233, 221, 244));
		txtNgaySinh.setBounds(1030, 71, 600, 35);
		pnTTTK.add(txtNgaySinh);
		btnLuuMK.setEnabled(false);

		hienThiTT(maNV);
		// getMa(maNV);

		btnLuu.setEnabled(false);

		JLabel lbgmail = new JLabel("Gmail:");
		lbgmail.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lbgmail.setBounds(30, 375, 154, 29);
		pnTTTK.add(lbgmail);

		txtGmail = new JTextField(17);
		txtGmail.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtGmail.setEditable(false);
		txtGmail.setBackground(new Color(233, 221, 244));
		txtGmail.setBounds(194, 372, 600, 35);
		pnTTTK.add(txtGmail);

		dongMoNhapLieu(false);
		dongMoNhapLieuMK(false);

		btnCapNhat.addActionListener(this);
		btnLuu.addActionListener(this);
		btnLuuMK.addActionListener(this);
		btnDoiMK.addActionListener(this);

	}

	private void dongMoNhapLieu(Boolean b) {
		txtHoTen.setEditable(b);
		cbGioiTinh.setEnabled(b);
		txtDiaChi.setEditable(b);
		txtSDT.setEditable(b);
		txtCMND.setEditable(b);
		txtNgaySinh.setEnabled(b);
		txtGmail.setEditable(b);
	}

	private void dongMoNhapLieuMK(Boolean b) {
		txtMKC.setEditable(b);
		txtMKM.setEditable(b);
		txtXNMK.setEditable(b);

	}

	public ImageIcon ResizeImage(String ImagePath) {
		ImageIcon MyImage = new ImageIcon(ImagePath);
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(lblHinhAnh.getWidth(), lblHinhAnh.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);

		return image;
	}

	private void hienThiTT(String maNhanVien) throws RemoteException {
		for (NhanVien nv : dao_NhanVien.getALLNhanVien()) {
			if (nv.getMaNhanVien().equals(maNhanVien)) {
				txtHoTen.setText(nv.getTenNhanVien());
				boolean gt = nv.getGioiTinh();
				if (gt == true) {
					cbGioiTinh.setSelectedIndex(0);
				}
				if (gt == false) {
					cbGioiTinh.setSelectedIndex(1);
				}
				txtCMND.setText(nv.getCmnd());
				txtDiaChi.setText(nv.getDiaChi());

				DateTimeFormatter dateFormatter2 = DateTimeFormatter.ofPattern("MMMM dd, yyyy");

				try {
					Date date2 = new SimpleDateFormat("MMMM dd, yyyy")
							.parse(nv.getNgaySinh().format(dateFormatter2));
					txtNgaySinh.setDate(date2);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtSDT.setText(nv.getSoDienThoai());

				byte[] byteArray = dao_NhanVien.getHinh(maNhanVien);
				if (byteArray == null) {
					lblHinhAnh.setIcon(new ImageIcon("src/main/java/img/anhtam.jpg"));
				} else {
					String str = new String(byteArray);
					lblHinhAnh.setIcon(ResizeImage(String.valueOf(str)));
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btnCapNhat)) {
			if (btnCapNhat.getText().equals("Cập nhật")) {
				dongMoNhapLieu(true);
				btnCapNhat.setText("Hủy");
				btnLuu.setEnabled(true);
			} else {
				dongMoNhapLieu(false);
				btnCapNhat.setText("Cập nhật");
				btnCapNhat.setEnabled(true);
				btnLuu.setEnabled(false);
			}
		}
		if (obj.equals(btnDoiMK)) {
			if (btnDoiMK.getText().equals("Đổi mật khẩu")) {
				dongMoNhapLieuMK(true);
				btnDoiMK.setText("Hủy");
				btnLuuMK.setEnabled(true);
			} else {
				dongMoNhapLieuMK(false);
				btnDoiMK.setText("Đổi mật khẩu");
				btnDoiMK.setEnabled(true);
				btnLuuMK.setEnabled(false);
			}
		}
		if (obj.equals(btnLuu)) {
			if (btnCapNhat.getText().equals("Hủy")) {
				if (validData()) {
					String maNV = ma;
					System.out.println("Ma: " + maNV);
					String tenNV = txtHoTen.getText().trim();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String ngaySinh = sdf.format(txtNgaySinh.getDate());
					LocalDate date = LocalDate.parse(ngaySinh);
					String cmnd = txtCMND.getText().trim();
					String diaChi = txtDiaChi.getText().trim();
					String sdt = txtSDT.getText().trim();
					boolean gioiTinh = cbGioiTinh.getSelectedItem().toString().equals("Nam");
					// String chucVu = cbChucVu.getSelectedItem().toString();
					LoaiNhanVien loaiNV = null; // = daoLoaiNV.getLoaiNhanVienTheoTen(chucVu);
					byte[] hinhAnh = null;
					String gmail = txtGmail.getText().trim();
					try {
						if (dao_NhanVien.updateTTNhanVien(
								new NhanVien(maNV, tenNV, gioiTinh, date, cmnd, diaChi, sdt, loaiNV, hinhAnh, gmail))) {
							JOptionPane.showMessageDialog(this, "Sửa thông tin thành công");
							dongMoNhapLieu(false);
							btnCapNhat.setText("Cập nhật");
							btnLuu.setEnabled(false);
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			}
		}
		if (obj.equals(btnLuuMK)) {
			if (btnDoiMK.getText().equals("Hủy")) {
				try {
					if (validDataMK()) {
						String ten = tentk;
						String mk = txtMKM.getText().trim();
						NhanVien maNV = null;
						try {
							if (dao_TaiKhoan.capNhapMKTaiKhoan(new TaiKhoan(tentk, mk, maNV))) {
								JOptionPane.showMessageDialog(this, "Sửa tài khoản thành công");
								dongMoNhapLieuMK(false);
								btnDoiMK.setText("Đổi mật khẩu");
								btnLuuMK.setEnabled(false);
							}
						} catch (RemoteException ex) {
							throw new RuntimeException(ex);
						}
					}
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			}
		}
	}

	private boolean validData() {
		String tenNV = txtHoTen.getText().trim();
		String sdt = txtSDT.getText().trim();
		String cmnd = txtCMND.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String gmail = txtGmail.getText().trim();
		if (tenNV.length() == 0) {
			showMessage(txtHoTen, "Nhập tên nhân viên!");
			return false;
		}
		if (!tenNV.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]*\\s?)+$")) {
			showMessage(txtHoTen, "Tên nhân viên bao gồm chữ cái, chữ số tiếng Việt, không bao gồm ký tự đặc biệt!");
			return false;
		}
		if (cmnd.length() == 0) {
			showMessage(txtCMND, "Nhập CMND!");
			return false;
		}
		if (!cmnd.matches("^\\d{9,12}$") && Integer.parseInt(cmnd) < 0) {
			showMessage(txtCMND, "CMND là số nguyên (9-12 số)");
			return false;
		}
		if (sdt.length() == 0) {
			showMessage(txtSDT, "Nhập số điện thoại!");
			return false;
		}
		if (!sdt.matches("^\\d{10}$")) {
			showMessage(txtSDT, "Số điện thoại là số nguyên (10 số)");
			return false;
		}
		if (diaChi.length() == 0) {
			showMessage(txtDiaChi, "Nhập địa chỉ!");
			return false;
		}
		if (!gmail.matches(
				"^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*" + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")) {
			showMessage(txtGmail, "Gmail có dạng: username@domain.com");
			return false;
		}
		return true;
	}

	private boolean validDataMK() throws RemoteException {
		TaiKhoan tk = dao_TaiKhoan.getTaiKhoanTheoMa(tentk);
		String mkc = tk.getMatKhau().trim();
		String matKhauCu = txtMKC.getText().trim();
		String matKhauMoi = txtMKM.getText().trim();
		String xacNhanMK = txtXNMK.getText().trim();
		if (matKhauCu.length() == 0) {
			showMessage(txtMKC, "Nhập mật khẩu cũ!");
			return false;
		}
		if (!mkc.equals(matKhauCu)) {
			showMessage(txtMKC, "Mật khẩu cũ không đúng!");
			return false;
		}
		if (matKhauMoi.length() == 0) {
			showMessage(txtMKM, "Nhập mật khẩu mới!");
			return false;
		}
		if (!matKhauMoi.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")) {
			showMessage(txtMKM,
					"Mật khẩu có ít nhất 1 ký tự viết hoa,1 ký tự viết thường, 1 chữ số, 1 ký tự đặc biệt. Mật khẩu phải có độ dài ít nhất 8 ký tự và tối đa là 20 ký tự ");
			return false;
		}
		if (xacNhanMK.length() == 0) {
			showMessage(txtXNMK, "Nhập xác nhân mật khẩu mới!");
			return false;
		}
		if (!matKhauMoi.equals(xacNhanMK)) {
			showMessage(txtMKC, "Xác nhận mật khẩu mới không đúng!");
			return false;
		}

		return true;
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}
}
