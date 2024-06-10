package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

//import dao.DAO_TaiKhoan;
import dao.DaoTaiKhoan;
import entity.NhanVien;
import entity.TaiKhoan;

public class FrmDangNhap extends JFrame {
//	Registry registry = LocateRegistry.getRegistry("26.91.197.38",7878);
	private static final String URL = "rmi://26.173.247.75:7878/";
	private JPanel contentPane;
//	private DAO_TaiKhoan dao_TaiKhoan = new DAO_TaiKhoan();
	private static DaoTaiKhoan dao_TaiKhoan;

	static {
		try {
			dao_TaiKhoan = (DaoTaiKhoan) Naming.lookup(URL + "daoTaiKhoan");
		} catch (NotBoundException e) {
			throw new RuntimeException(e);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	private ArrayList<TaiKhoan> dsTaiKhoan = dao_TaiKhoan.getAllTaiKhoan();
	private String tenTKAdmin = "admin";
	private String matKhauAdmin = "123";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					FrmDangNhap frame = new FrmDangNhap();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//	private JPanel contentPane;
	private JTextField txt_TenDangNhap;
	private JButton btnDangNhap;
	private JLabel lbl_Anhpass;
	private JLabel lbl_TieuDe;
	private JPasswordField txt_MatKhau;
	private JLabel txt_ThongBao;
	private JRadioButton rad_HienMatKhau;
	private JLabel lbl_AnhDN;
	private JTextField txt_QuenMK;
	private JLabel Jlab_Background;
	private JTextField txt_TenDNMK;
	private JTextField txt_SDTlMK;
	private JButton btn_XacNhanMK;
	private JTextField txt_QuayVeDN;

	public FrmDangNhap() throws RemoteException {

		setTitle("Đăng Nhập");
		setResizable(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1040, 723);
		setLocationRelativeTo(null);
		//setIconImage(Toolkit.getDefaultToolkit().getImage(FrmDangNhap.class.getResource("/img/icons8-login-100.png")));
		setIconImage(new ImageIcon("src/main/java/img/icons8-login-100.png").getImage());
		contentPane = new JPanel();
		contentPane.setEnabled(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		Jlab_Background = new JLabel();
		Jlab_Background.setBounds(0, 0, 1029, 689);
		Jlab_Background.setForeground(Color.WHITE);
		Jlab_Background.setFont(new Font("UTM American Sans", Font.PLAIN, 30));
		Jlab_Background.setIcon(new ImageIcon("src/main/java/img/Login-02 (1).jpg"));
		Jlab_Background.setLayout(null);
		contentPane.setLayout(null);

		txt_QuenMK = new JTextField();
		txt_QuenMK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txt_QuenMK.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				txt_QuenMK.setForeground(new Color(30, 144, 255));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				hienThiDangNhap(false);
				txt_TenDNMK.setText(txt_TenDangNhap.getText());
				txt_TenDNMK.setForeground(Color.black);
				txt_SDTlMK.setText("Số điện thoại...");
			}
		});

		btn_XacNhanMK = new JButton();
		btn_XacNhanMK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (quenMatKhau(txt_TenDNMK.getText().trim(), txt_SDTlMK.getText().trim())) {
						veDangNhap(txt_TenDNMK.getText().trim());
					}
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			}
		});
		btn_XacNhanMK.setText("Xác nhận");
		btn_XacNhanMK.setForeground(Color.WHITE);
		btn_XacNhanMK.setFont(new Font("Tahoma", Font.BOLD, 20));
		btn_XacNhanMK.setFocusable(false);
		btn_XacNhanMK.setBackground(new Color(254, 91, 58));
		btn_XacNhanMK.setBounds(124, 380, 313, 50);
		contentPane.add(btn_XacNhanMK);

		txt_TenDNMK = new JTextField("Tên đăng nhập...");
		txt_TenDNMK.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txt_TenDNMK.getText().equals("Tên đăng nhập...")) {
					txt_TenDNMK.setText("");
					txt_TenDNMK.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txt_TenDNMK.getText().equals("")) {
					txt_TenDNMK.setText("Tên đăng nhập...");
					txt_TenDNMK.setForeground(new Color(131, 131, 133));
				}
			}
		});
		txt_TenDNMK.setHorizontalAlignment(SwingConstants.CENTER);
		txt_TenDNMK.setForeground(new Color(131, 131, 133));
		txt_TenDNMK.setFont(new Font("Tahoma", Font.BOLD, 20));
		txt_TenDNMK.setColumns(10);
		txt_TenDNMK.setBounds(124, 195, 313, 50);
		contentPane.add(txt_TenDNMK);

		txt_SDTlMK = new JTextField("Số điện thoại...");
		txt_SDTlMK.setHorizontalAlignment(SwingConstants.CENTER);
		txt_SDTlMK.setForeground(new Color(131, 131, 133));
		txt_SDTlMK.setFont(new Font("Tahoma", Font.BOLD, 20));
		txt_SDTlMK.setBounds(124, 267, 313, 50);
		txt_SDTlMK.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txt_SDTlMK.getText().equals("Số điện thoại...")) {
					txt_SDTlMK.setText("");
					txt_SDTlMK.setForeground(Color.black);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txt_SDTlMK.getText().equals("")) {
					txt_SDTlMK.setText("Số điện thoại...");
					txt_SDTlMK.setForeground(new Color(131, 131, 133));
				}
			}
		});
		contentPane.add(txt_SDTlMK);

		txt_QuenMK.setHorizontalAlignment(SwingConstants.CENTER);
		txt_QuenMK.setForeground(new Color(30, 144, 255));
		txt_QuenMK.setBackground(Color.WHITE);
		txt_QuenMK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_QuenMK.setText("Quên mật khẩu ?");
		txt_QuenMK.setBounds(653, 431, 234, 33);
		contentPane.add(txt_QuenMK);
		txt_QuenMK.setColumns(10);
		txt_QuenMK.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txt_QuenMK.setFocusable(false);

		txt_QuayVeDN = new JTextField();
		txt_QuayVeDN.setHorizontalAlignment(SwingConstants.CENTER);
		txt_QuayVeDN.setForeground(new Color(30, 144, 255));
		txt_QuayVeDN.setBackground(Color.WHITE);
		txt_QuayVeDN.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_QuayVeDN.setText("Về đăng nhập");
		txt_QuayVeDN.setBounds(122, 431, 315, 33);
		contentPane.add(txt_QuayVeDN);
		txt_QuayVeDN.setColumns(10);
		txt_QuayVeDN.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txt_QuayVeDN.setFocusable(false);
		txt_QuayVeDN.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txt_QuayVeDN.setForeground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				txt_QuayVeDN.setForeground(new Color(30, 144, 255));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				hienThiDangNhap(true);
				txt_TenDangNhap.setText(txt_TenDNMK.getText());
				txt_MatKhau.setText("");
			}
		});

		rad_HienMatKhau = new JRadioButton("Hiển thị mật khẩu");
		contentPane.add(rad_HienMatKhau);
		rad_HienMatKhau.setBounds(653, 324, 211, 20);
		rad_HienMatKhau.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		rad_HienMatKhau.setBackground(Color.white);
		rad_HienMatKhau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (rad_HienMatKhau.isSelected()) {
					txt_MatKhau.setEchoChar((char) 0);
					txt_MatKhau.setEchoChar((char) 0);
				} else if (rad_HienMatKhau.isSelected() == false) {
					txt_MatKhau.setEchoChar('*');
					txt_MatKhau.setEchoChar('*');
				}

			}
		});

		txt_MatKhau = new JPasswordField();
		contentPane.add(txt_MatKhau);
		txt_MatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		txt_MatKhau.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						try {
							dangNhap();
						} catch (MalformedURLException ex) {
							throw new RuntimeException(ex);
						} catch (NotBoundException ex) {
							throw new RuntimeException(ex);
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			}
		});
		txt_MatKhau.setText("");
		txt_MatKhau.setForeground(Color.red);
		txt_MatKhau.setBounds(653, 267, 234, 50);
		txt_MatKhau.setFont(new Font("Tahoma", Font.BOLD, 20));

		lbl_Anhpass = new JLabel();
		contentPane.add(lbl_Anhpass);
		lbl_Anhpass.setIcon(new ImageIcon("src/main/java/img/pass.png"));
		lbl_Anhpass.setBounds(598, 274, 70, 43);

		lbl_AnhDN = new JLabel();
		contentPane.add(lbl_AnhDN);
		lbl_AnhDN.setIcon(new ImageIcon("src/main/java/img/user.png"));
		lbl_AnhDN.setBounds(598, 195, 70, 43);

		txt_TenDangNhap = new JTextField("");
		contentPane.add(txt_TenDangNhap);
		txt_TenDangNhap.setHorizontalAlignment(SwingConstants.CENTER);
		txt_TenDangNhap.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String tenDN = txt_TenDangNhap.getText().trim();
				TaiKhoan taiKhoan = null;
				try {
					taiKhoan = dao_TaiKhoan.getTaiKhoanTheoMa(tenDN);
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				if (tenDN.equals(tenTKAdmin)) {
					txt_ThongBao.setFont(new Font("Tahoma", Font.BOLD, 40));
					txt_ThongBao.setForeground(Color.black);
					txt_ThongBao.setText("ADMIN");
				} else if (taiKhoan.getTenTaiKhoan() != null) {
					String chucVuString = taiKhoan.getNhanVien().getLoaiNhanVien().getTenLoai();
					txt_ThongBao.setFont(new Font("Tahoma", Font.BOLD, 40));
					txt_ThongBao.setForeground(Color.black);
					txt_ThongBao.setText(chucVuString);
				} else
					txt_ThongBao.setText("");
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						try {
							dangNhap();
						} catch (MalformedURLException ex) {
							throw new RuntimeException(ex);
						} catch (NotBoundException ex) {
							throw new RuntimeException(ex);
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			}
		});
		txt_TenDangNhap.setForeground(Color.red);
		txt_TenDangNhap.setBounds(653, 195, 234, 50);
		txt_TenDangNhap.setFont(new Font("Tahoma", Font.BOLD, 20));
		txt_TenDangNhap.setColumns(10);

		lbl_TieuDe = new JLabel("ĐĂNG NHẬP");
		lbl_TieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lbl_TieuDe);
		lbl_TieuDe.setFont(new Font("UTM Americana EB", Font.BOLD, 45));
		lbl_TieuDe.setForeground(Color.BLACK);
		lbl_TieuDe.setBounds(73, 53, 879, 81);

		btnDangNhap = new JButton();
		contentPane.add(btnDangNhap);
		btnDangNhap.setBounds(653, 380, 234, 50);
		btnDangNhap.setIcon(new ImageIcon("src/main/java/img/icons8-login-32.png"));
		btnDangNhap.setText("Đăng nhập");
		btnDangNhap.setForeground(Color.white);
		btnDangNhap.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnDangNhap.setBackground(new Color(153, 51, 255));
		btnDangNhap.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					try {
						dangNhap();
					} catch (MalformedURLException ex) {
						throw new RuntimeException(ex);
					} catch (NotBoundException ex) {
						throw new RuntimeException(ex);
					}
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnDangNhap.setBackground(Color.red);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnDangNhap.setBackground(new Color(153, 51, 255));
			}
		});

		btnDangNhap.setBackground(new Color(153, 51, 255));
		btnDangNhap.setFocusable(false);

		txt_ThongBao = new JLabel("ADMIN");
		contentPane.add(txt_ThongBao);
		txt_ThongBao.setHorizontalAlignment(SwingConstants.CENTER);
		txt_ThongBao.setBackground(Color.PINK);
		txt_ThongBao.setFont(new Font("Tahoma", Font.BOLD, 40));
		txt_ThongBao.setForeground(Color.BLACK);
		txt_ThongBao.setBounds(73, 507, 879, 98);
		contentPane.add(Jlab_Background);
		// sự kiện
		hienThiDangNhap(true);
	}

	public String kiemTraLoaiNhanVien(String tenDangNhap, String matKhau) throws RemoteException {
		// đăng nhập với tư cách là admin- có tất cả quyền
		if (tenDangNhap.equals(tenTKAdmin))
			if (matKhau.equals(matKhauAdmin))
				return "admin";
			else
				return "-1";
		// đăng nhập với tư cách là quản lý, thu nhân,kế toán
		TaiKhoan tkTim = dao_TaiKhoan.getTaiKhoanTheoMa(tenDangNhap);
		if (tkTim.getNhanVien() != null)
			if (tkTim.getMatKhau().trim().equals(matKhau.trim()))
				return tkTim.getNhanVien().getMaNhanVien();
			else
				return "-1";
		return "0";
	}

	private void thongBaoLoi(String noiDung, String tieuDe) {
		txt_ThongBao.setFont(new Font("Tahoma", Font.ITALIC, 20));
		txt_ThongBao.setText("*** " + noiDung + " ***");
		txt_ThongBao.setForeground(Color.red);
		JOptionPane.showMessageDialog(this, noiDung, tieuDe, JOptionPane.ERROR_MESSAGE);
	}

	public boolean KiemTraRangBuoc() {
		String tenDNNhap = txt_TenDangNhap.getText().trim();
		String matKhauNhap = txt_MatKhau.getPassword().toString();
		if (txt_TenDangNhap.getText().equals("") || matKhauNhap.equals("")) {
			if (tenDNNhap.equals(""))
				thongBaoLoi(" Bạn chưa nhập tên tài khoản ", "Lỗi đăng nhập");
			else
				thongBaoLoi("Bạn chưa nhập mật khẩu ", "Lỗi đăng nhập");
			return false;
		}
		// tên đăng nhập chỉ chứa số và chữ va không có ký tự đặc biệt
		// độ dài tên đăng nhập từ 5-20
		boolean rangBuocTen;
		rangBuocTen = tenDNNhap.matches("[a-zA-z0-9 ]{3,20}");
		if (rangBuocTen != true) {
			thongBaoLoi(" Tên đăng nhập sai định dạng ", "Lỗi đăng nhập");
			return false;
		} else
			return true;
	}

	public boolean quenMatKhau(String tenDN, String sdt) throws RemoteException {
		for (TaiKhoan tk : dsTaiKhoan)
			if (tk.getTenTaiKhoan().equals(tenDN)) {
				NhanVien nhanVienKQ = tk.getNhanVien();
				if (nhanVienKQ.getSoDienThoai() == null) {
					thongBaoLoi(
							" Tài khoản không chưa cập nhập số điện thoại trên hệ thống.\n Liên lạc quản lý để đổi mật khẩu ",
							"Thông báo");
					return false;
				}
				if (nhanVienKQ.getSoDienThoai().equals(sdt.trim())) {
					tk.setMatKhau("123");
					return dao_TaiKhoan.capNhapMKTaiKhoan(tk);
				} else {
					thongBaoLoi(" Số điện thoại nhập sai ", "Đổi mật khẩu");
					return false;
				}
			}
		thongBaoLoi(" Tên đăng nhập sai ", "Đổi mật khẩu");
		return false;
	}

	public void veDangNhap(String TenDN) {
		Object[] options = { "Ở lại đây", "Về đăng nhập" };
		int hoiLai = JOptionPane.showOptionDialog(this, "Thay đổi mật khẩu thành công !\n Mật khẩu về mặc định:'123' ",
				"Đổi mật khẩu thành công", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
				options[0]);
		if (hoiLai == 1) {
			hienThiDangNhap(true);
			txt_TenDangNhap.setText(TenDN);
			txt_MatKhau.setText("123");
		}
	}

	public void dangNhap() throws RemoteException, MalformedURLException, NotBoundException {
		if (!KiemTraRangBuoc())// sai kiểu định dạng khi nhập
			return;
		String maNhanVien = kiemTraLoaiNhanVien(txt_TenDangNhap.getText().trim(), txt_MatKhau.getText());
		if (maNhanVien.equals("0")) {
			thongBaoLoi("Tài khoản không tồn tại", "Đăng nhập không thành công");
			return;
		}
		if (maNhanVien.equals("-1")) {
			thongBaoLoi("Mật khẩu nhập bị sai", "Đăng nhập không thành công");
			return;
		}
		FrmTrangChu trangChu = new FrmTrangChu(maNhanVien);
		trangChu.setVisible(true);
//		trangChu.setLocationRelativeTo(null);
		trangChu.setResizable(false);
		trangChu.setExtendedState(JFrame.MAXIMIZED_BOTH);
		dispose();

	}

	public void hienThiDangNhap(boolean bol) {
		txt_ThongBao.setText("");
		if (!bol) {
			txt_MatKhau.hide();
			txt_TenDangNhap.hide();
			btnDangNhap.hide();
			txt_QuenMK.hide();
			rad_HienMatKhau.hide();
			lbl_AnhDN.hide();
			lbl_Anhpass.hide();
			lbl_TieuDe.setText("QUÊN MẬT KHẨU");
			Jlab_Background.setIcon(new ImageIcon(getClass().getResource("/img/Login-01.jpg")));
			txt_SDTlMK.show();
			txt_TenDNMK.show();
			txt_QuayVeDN.show();
			btn_XacNhanMK.show();
			return;
		}
		txt_MatKhau.show();
		txt_ThongBao.show();
		txt_TenDangNhap.show();
		btnDangNhap.show();
		txt_QuenMK.show();
		rad_HienMatKhau.show();
		lbl_AnhDN.show();
		lbl_Anhpass.show();
		lbl_TieuDe.setText("ĐĂNG NHẬP");
		Jlab_Background.setIcon(new ImageIcon("src/main/java/img/Login-02 (1).jpg"));
		txt_SDTlMK.hide();
		txt_TenDNMK.hide();
		btn_XacNhanMK.hide();
		txt_QuayVeDN.hide();
	}
}
