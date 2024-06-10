package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

//import dao.DAO_NhanVien;
//import dao.DAO_TaiKhoan;
import dao.DaoNhanVien;
import dao.DaoTaiKhoan;
import entity.KhachHang;
import entity.LoaiKhachHang;
import entity.NhanVien;
import entity.TaiKhoan;

public class FrmQuanLyTaiKhoan extends JFrame implements ActionListener, MouseListener {
	private static final String URL = "rmi://26.173.247.75:7878/";

	DaoTaiKhoan daoTaiKhoan = (DaoTaiKhoan) Naming.lookup(URL + "daoTaiKhoan");
	DaoNhanVien daoNhanVien = (DaoNhanVien) Naming.lookup(URL + "daoNhanVien");

//	private DAO_NhanVien daoNhanVien = new DAO_NhanVien();
//	private DAO_TaiKhoan daoTaiKhoan = new DAO_TaiKhoan();
	public static JPanel pnBorder;
	private JTextField txtTenTK;
	private JComboBox<String> cbMaChuTK;

	private JButton btnThem;
	private JButton btnResetMK;
	private JButton btnXoaTrang;
	private JTextField txtMK;
	private Box bTimKiem_Nhap;
	private JLabel lbLuaChon;
	private JLabel lbNhap;
	private JComboBox<String> cbLuaChon;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private JLabel lbDSTK;
	private DefaultTableModel modelTK;
	private JTable tbTaiKhoan;
	private JButton btnXoa;
	private JButton btnLuu;
	private JButton btnLamMoi;

	public FrmQuanLyTaiKhoan() throws MalformedURLException, NotBoundException, RemoteException {
		this.setTitle("Quản lý tài khoản");
		// this.setSize(1920, 1030);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Toàn màn hình
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.createUI();
	}

	private void createUI() throws RemoteException {
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

		Box bThongTinTK = Box.createVerticalBox();
		Box bLbTenTK = Box.createHorizontalBox();
		Box bTxtTenTK = Box.createHorizontalBox();
		Box bLbMK = Box.createHorizontalBox();
		Box bTxtMK = Box.createHorizontalBox();
		Box bLbMaChuTK = Box.createHorizontalBox();
		Box bCbMaChuTK = Box.createHorizontalBox();
		Box bThemSua = Box.createHorizontalBox();
		Box bXoaTrang = Box.createHorizontalBox();

		JLabel lbTenTK = new JLabel("Tên tài khoản:");
		lbTenTK.setFont(font2);
		JLabel lbMK = new JLabel("Mật khẩu:");
		lbMK.setFont(font2);
		JLabel lbMaChuTK = new JLabel("Mã chủ tài khoản:");
		lbMaChuTK.setFont(font2);

		bLbTenTK.add(lbTenTK);
		bLbTenTK.add(Box.createVerticalStrut(0));
		bLbMK.add(lbMK);
		bLbMK.add(Box.createVerticalStrut(0));
		bLbMaChuTK.add(lbMaChuTK);
		bLbMaChuTK.add(Box.createVerticalStrut(0));

		txtTenTK = new JTextField(17);
		txtTenTK.setFont(font2);
		txtTenTK.setBackground(color2);

		// txtMKK = new JPasswordField();

		txtMK = new JPasswordField(17);
		txtMK.setFont(font2);
		txtMK.setBackground(color2);
		cbMaChuTK = new JComboBox<String>();
		ArrayList<NhanVien> maChuTK = daoNhanVien.getALLNhanVien();
		for (int i = 0; i < maChuTK.size(); i++) {
			cbMaChuTK.addItem(maChuTK.get(i).getMaNhanVien());
		}
		cbMaChuTK.setFont(font2);
		cbMaChuTK.setBackground(color2);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Them.png"));
		btnThem.setFont(font2);
		btnThem.setBackground(color1);
		btnThem.setForeground(Color.WHITE);
		btnResetMK = new JButton("Reset mật khẩu");
		btnResetMK.setIcon(new ImageIcon("src/main/java/img/Button-Refresh-icon2.png"));
		btnResetMK.setFont(font2);
		btnResetMK.setBackground(color1);
		btnResetMK.setForeground(Color.WHITE);
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setIcon(new ImageIcon("src/main/java/img/Button-Refresh-icon2.png"));
		btnXoaTrang.setFont(font2);
		btnXoaTrang.setBackground(color1);
		btnXoaTrang.setForeground(Color.WHITE);

		bTxtTenTK.add(txtTenTK);
		bTxtMK.add(txtMK);
		bCbMaChuTK.add(cbMaChuTK);

		/*
		 * bThemSua.add(btnThem); bThemSua.add(Box.createHorizontalStrut(15));
		 * bThemSua.add(btnResetMK); bXoaTrang.add(btnXoaTrang);
		 */

		bThongTinTK.add(bLbTenTK);
		bThongTinTK.add(Box.createVerticalStrut(3));
		bThongTinTK.add(bTxtTenTK);
		bThongTinTK.add(Box.createVerticalStrut(15));
		bThongTinTK.add(bLbMK);
		bThongTinTK.add(Box.createVerticalStrut(3));
		bThongTinTK.add(bTxtMK);
		bThongTinTK.add(Box.createVerticalStrut(15));
		bThongTinTK.add(bLbMaChuTK);
		bThongTinTK.add(Box.createVerticalStrut(3));
		bThongTinTK.add(bCbMaChuTK);

		bThongTinTK.add(Box.createVerticalStrut(30));
		bThongTinTK.add(bThemSua);
		bThongTinTK.add(Box.createVerticalStrut(15));
		bThongTinTK.add(bXoaTrang);
		bThongTinTK.add(Box.createVerticalStrut(30));

		pnLeftContent.add(bThongTinTK);

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

		lbDSTK = new JLabel("Danh sách tài khoản");
		lbDSTK.setFont(font1);
		lbDSTK.setForeground(color1);

		/*
		 * JPanel pnTimKiem = new JPanel(); pnTimKiem.setBackground(Color.WHITE);
		 * pnTimKiem.setLayout(new BoxLayout(pnTimKiem, BoxLayout.Y_AXIS));
		 * 
		 * 
		 * Box bTimKiem = Box.createVerticalBox(); Box bTimKiem_LuaChon =
		 * Box.createHorizontalBox(); bTimKiem_Nhap = Box.createHorizontalBox();
		 * 
		 * lbLuaChon = new JLabel("Tìm theo:"); lbLuaChon.setFont(font2); lbNhap = new
		 * JLabel("Tìm kiếm:"); lbNhap.setFont(font2);
		 * lbLuaChon.setPreferredSize(lbNhap.getPreferredSize());
		 * 
		 * cbLuaChon = new JComboBox<String>(); cbLuaChon.addItem("Tên tài khoản");
		 * cbLuaChon.addItem("Mã chủ tài khoản"); cbLuaChon.setFont(font2);
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
		pnRightTop.add(lbDSTK);

		// Right-Center
		JPanel pnRightCenter = new JPanel();
		pnRightCenter.setLayout(new BorderLayout());
		pnRightCenter.setBackground(Color.WHITE);
		pnRightCenter.setBorder(new EmptyBorder(20, 0, 20, 0));

		modelTK = new DefaultTableModel();
		modelTK.addColumn("Tên tài khoản");
		// modelTK.addColumn("Mật khẩu");
		modelTK.addColumn("Mã chủ tài khoản");

		tbTaiKhoan = new JTable(modelTK);
		JScrollPane jScrollPane = new JScrollPane(tbTaiKhoan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JTableHeader tbHeaderKH = tbTaiKhoan.getTableHeader();
		tbHeaderKH.setFont(font2);
		tbHeaderKH.setBackground(color2);
		tbTaiKhoan.setFont(font2);
		tbTaiKhoan.setRowHeight(35);
		pnRightCenter.add(jScrollPane);
		loadData(daoTaiKhoan.getAllTaiKhoan());

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

		pnRightBottom.add(btnThem);
		pnRightBottom.add(Box.createHorizontalStrut(7));
		pnRightBottom.add(btnXoa);
		pnRightBottom.add(Box.createHorizontalStrut(7));
		pnRightBottom.add(btnLuu);
		pnRightBottom.add(Box.createHorizontalStrut(7));
		pnRightBottom.add(btnXoaTrang);
		pnRightBottom.add(Box.createHorizontalStrut(7));
		pnRightBottom.add(btnResetMK);

		pnRight.add(pnRightTop, BorderLayout.NORTH);
		pnRight.add(pnRightCenter, BorderLayout.CENTER);
		pnRight.add(pnRightBottom, BorderLayout.SOUTH);

		pnBorder.add(pnLeft, BorderLayout.WEST);
		pnBorder.add(pnRight, BorderLayout.CENTER);
		getContentPane().add(pnBorder);

		tbTaiKhoan.setDefaultEditor(Object.class, null);

		dongMoNhapLieu(false);

		btnXoaTrang.setEnabled(false);
		btnLuu.setEnabled(false);

		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnResetMK.addActionListener(this);
		// btnTimKiem.addActionListener(this);
		btnLuu.addActionListener(this);
		// btnLamMoi.addActionListener(this);

		tbTaiKhoan.addMouseListener(this);

	}

	private void xoaRongNhapLieu() {
		txtMK.requestFocus();
		txtMK.setText("");
		cbMaChuTK.setSelectedIndex(0);
	}

	private void dongMoNhapLieu(Boolean b) {
		txtTenTK.setEditable(b);
		txtMK.setEditable(b);
		cbMaChuTK.setEnabled(b);
	}

	private void loadData(TaiKhoan tk) {
		modelTK.setRowCount(0);
		String tenTK = tk.getTenTaiKhoan();
		String mk = tk.getMatKhau();
		String maChuTK = tk.getNhanVien().getMaNhanVien();
		String row[] = { tenTK, mk, maChuTK };
		modelTK.addRow(row);
	}

	private void loadData(ArrayList<TaiKhoan> dsTK) {
		modelTK.setRowCount(0);
		for (int i = 0; i < dsTK.size(); i++) {
			String tenTK = dsTK.get(i).getTenTaiKhoan();
			// String mk = dsTK.get(i).getMatKhau();
			String maChuTK = dsTK.get(i).getNhanVien().getMaNhanVien();
			String row[] = { tenTK, maChuTK };
			modelTK.addRow(row);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int hangDuocChon = tbTaiKhoan.getSelectedRow();
		txtTenTK.setText(modelTK.getValueAt(hangDuocChon, 0).toString());
		cbMaChuTK.setSelectedItem(modelTK.getValueAt(hangDuocChon, 1).toString());

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btnThem)) {
			if (btnThem.getText().equals("Thêm")) {
				dongMoNhapLieu(true);
				btnLuu.setEnabled(true);
				btnResetMK.setEnabled(false);
				btnXoa.setEnabled(false);
				btnThem.setText("Hủy");
				btnXoaTrang.setEnabled(true);
				tbTaiKhoan.removeMouseListener(this);
				xoaRongNhapLieu();
				txtMK.setText("123456");
			} else {
				tbTaiKhoan.addMouseListener(this);
				dongMoNhapLieu(false);
				btnThem.setText("Thêm");
				btnResetMK.setEnabled(true);
				btnXoa.setEnabled(true);
				btnXoaTrang.setEnabled(false);
				btnLuu.setEnabled(false);
				tbTaiKhoan.addMouseListener(this);
				txtMK.setText("");
				xoaRongNhapLieu();
				try {
					loadData(daoTaiKhoan.getAllTaiKhoan());
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			}

		}
		if (obj.equals(btnResetMK)) {
			if (btnResetMK.getText().equals("Reset mật khẩu")) {
				int hangDuocChon = tbTaiKhoan.getSelectedRow();
				if (hangDuocChon > -1) {
					dongMoNhapLieu(true);
					txtTenTK.setEditable(false);
				} else {
					JOptionPane.showMessageDialog(this,
							"Vui lòng chọn 1 hàng trong bảng danh sách tài khoản, trước khi sửa!");
				}
				String tenTK = txtTenTK.getText().trim();
				String mk = "123456";
				String maChuTK = cbMaChuTK.getSelectedItem().toString();
				NhanVien maNV = null;
				try {
					maNV = daoNhanVien.getNhanVienTheoMa(maChuTK);
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				try {
					if (daoTaiKhoan.capNhapTaiKhoan(new TaiKhoan(tenTK, mk, maNV))) {
						loadData(daoTaiKhoan.getAllTaiKhoan());
						JOptionPane.showMessageDialog(this, "Reset mật khẩu thành công");
						dongMoNhapLieu(false);
					}
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			} else {
				xoaRongNhapLieu();
			}
		}
		if (obj.equals(btnXoa)) {
			int hangDuocChon = tbTaiKhoan.getSelectedRow();
			if (hangDuocChon > -1) {
				if (JOptionPane.showConfirmDialog(this, "Chắc chắn xóa dòng này?") == JOptionPane.YES_OPTION) {
					try {
						if (daoTaiKhoan.xoaTKTheoMa(modelTK.getValueAt(hangDuocChon, 0).toString())) {
							loadData(daoTaiKhoan.getAllTaiKhoan());
							JOptionPane.showMessageDialog(this, "Xóa thành công.");
							txtTenTK.setText("");
							xoaRongNhapLieu();
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Chọn 1 hàng trong bảng danh sách dịch vụ cần xóa!");
			}
		}
		/*
		 * if(obj.equals(btnTimKiem)) { if (cbLuaChon.getSelectedIndex() == 0) { String
		 * giaTriTimKiem = txtTimKiem.getText(); ArrayList<TaiKhoan> dsTimDuoc =
		 * daoTaiKhoan.getDSTaiKhoanTheoTen(giaTriTimKiem); if (dsTimDuoc.size() > 0) {
		 * loadData(dsTimDuoc); } else { JOptionPane.showMessageDialog(this,
		 * "Không tìm thấy!\nVui lòng kiểm tra lại thông tin tìm kiếm."); } } if
		 * (cbLuaChon.getSelectedIndex() == 1) { String giaTriTimKiem =
		 * txtTimKiem.getText(); ArrayList<TaiKhoan> dsTimDuoc =
		 * daoTaiKhoan.getDSTaiKhoanTheoMa(giaTriTimKiem); if (dsTimDuoc.size() > 0) {
		 * loadData(dsTimDuoc); } else { JOptionPane.showMessageDialog(this,
		 * "Không tìm thấy!\nVui lòng kiểm tra lại thông tin tìm kiếm."); } } }
		 */
		if (obj.equals(btnXoaTrang)) {
			xoaRongNhapLieu();
		}
		if (obj.equals(btnLuu)) {
			if (btnThem.getText().equals("Hủy")) {
				if (validData()) {
					String tenTK = txtTenTK.getText().trim();
					String mk = txtMK.getText().trim();
					String maChuTK = cbMaChuTK.getSelectedItem().toString();
					NhanVien maNV = null;
					try {
						maNV = daoNhanVien.getNhanVienTheoMa(maChuTK);
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
					try {
						if (daoTaiKhoan.addTaiKhoan(new TaiKhoan(tenTK, mk, maNV))) {
							loadData(daoTaiKhoan.getAllTaiKhoan());
							JOptionPane.showMessageDialog(this, "Thêm tài khoản mới thành công");
							dongMoNhapLieu(false);
							btnThem.setText("Thêm");
							xoaRongNhapLieu();
							btnResetMK.setEnabled(true);
							btnXoa.setEnabled(true);
							btnXoaTrang.setEnabled(false);
							btnLuu.setEnabled(false);
							txtTenTK.setText("");
							tbTaiKhoan.addMouseListener(this);
						} else {
							JOptionPane.showMessageDialog(this,
									"Tên tài khoản đã tồn tại\n Vui lòng nhập tên tài khoản mới!", "Lỗi",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}

			}
			/*
			 * if (btnResetMK.getText().equals("Hủy")) { if(validData()) {
			 * 
			 * }
			 * 
			 * }
			 */
		}

	}

	private boolean validData() {
		String tenTK = txtTenTK.getText().trim();
		String mk = txtMK.getText().trim();
		if (tenTK.length() == 0) {
			showMessage(txtTenTK, "Nhập tên tài khoản!");
			return false;
		}
		if (!tenTK.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]*\\s?)+$")) {
			showMessage(txtTenTK, "Tên tài khoản bao gồm chữ cái, chữ số tiếng Việt, không bao gồm ký tự đặc biệt!");
			return false;
		}
		if (mk.length() == 0) {
			showMessage(txtMK, "Nhập tên mật khẩu!");
			return false;
		}
		return true;

	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}

	public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
		new FrmQuanLyTaiKhoan().setVisible(true);
	}

}