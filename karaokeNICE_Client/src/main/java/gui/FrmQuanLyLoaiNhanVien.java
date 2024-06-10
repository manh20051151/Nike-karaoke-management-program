package gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
//
//import connectDB.ConnectDB;
//import dao.DAO_LoaiNhanVien;
import dao.DaoLoaiNhanVien;
import entity.LoaiNhanVien;

public class FrmQuanLyLoaiNhanVien extends JFrame implements ActionListener, MouseListener {
	private static final String URL = "rmi://26.173.247.75:7878/";
	DaoLoaiNhanVien daoLoaiNV = (DaoLoaiNhanVien) Naming.lookup(URL + "daoLoaiNhanVien");

//	private DAO_LoaiNhanVien daoLoaiNV = new DAO_LoaiNhanVien();
	public static JPanel pnBorder;

	private DefaultTableModel modelLNV;
	private JButton btnThem;
	private JTextField txtMaLNV;
	private JTextField txtTenLNV;
	private JTextField txtHSL;
	private JTextField txtDiem;
	private JButton btnSua;
	private JButton btnXoaTrang;
	private JTextArea taGhiChu;
	private JComboBox cbLoaiKH;
	private JButton btnXoa;
	private JButton btnLuu;
	private JTable tbLoaiNhanVien;
	private JButton btnTimKiem;
	private JComboBox cbLuaChon;
	private JTextField txtTimKiem;
	private JButton btnLamMoi;

	public FrmQuanLyLoaiNhanVien() throws MalformedURLException, NotBoundException, RemoteException {
		this.setTitle("Quản lý loại nhân viên");
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

		Box bThongTinLNV = Box.createVerticalBox();
		Box bLbMaLNV = Box.createHorizontalBox();
		Box bTxtMaLNV = Box.createHorizontalBox();
		Box bLbTenLNV = Box.createHorizontalBox();
		Box bTxtTenLNV = Box.createHorizontalBox();
		Box bLbHSL = Box.createHorizontalBox();
		Box bTxtHSL = Box.createHorizontalBox();
		Box bThemSua = Box.createHorizontalBox();
		Box bXoaTrang = Box.createHorizontalBox();

		JLabel lbMaLNV = new JLabel("Mã loại nhân viên:");
		lbMaLNV.setFont(font2);
		JLabel lbTenLNV = new JLabel("Tên loại nhân viên:");
		lbTenLNV.setFont(font2);
		JLabel lbHSL = new JLabel("Hệ số lương:");
		lbHSL.setFont(font2);

		bLbMaLNV.add(lbMaLNV);
		bLbMaLNV.add(Box.createVerticalStrut(0));
		bLbTenLNV.add(lbTenLNV);
		bLbTenLNV.add(Box.createVerticalStrut(0));
		bLbHSL.add(lbHSL);
		bLbHSL.add(Box.createVerticalStrut(0));

		txtMaLNV = new JTextField(17);
		txtMaLNV.setFont(font2);
		txtMaLNV.setBackground(color2);
		txtTenLNV = new JTextField(17);
		txtTenLNV.setFont(font2);
		txtTenLNV.setBackground(color2);
		txtHSL = new JTextField(17);
		txtHSL.setFont(font2);
		txtHSL.setBackground(color2);

		btnThem = new JButton("Thêm");
		btnThem.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Them.png"));
		btnThem.setFont(font2);
		btnThem.setBackground(color1);
		btnThem.setForeground(Color.WHITE);
		btnSua = new JButton("Sửa");
		btnSua.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Sua.png"));
		btnSua.setFont(font2);
		btnSua.setBackground(color1);
		btnSua.setForeground(Color.WHITE);
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoaTrang.setIcon(new ImageIcon("src/main/java/img/Button-Refresh-icon2.png"));
		btnXoaTrang.setFont(font2);
		btnXoaTrang.setBackground(color1);
		btnXoaTrang.setForeground(Color.WHITE);

		bTxtMaLNV.add(txtMaLNV);
		bTxtTenLNV.add(txtTenLNV);
		bTxtHSL.add(txtHSL);

		/*
		 * bThemSua.add(btnThem); bThemSua.add(Box.createHorizontalStrut(15));
		 * bThemSua.add(btnSua); bXoaTrang.add(btnXoaTrang);
		 */

		bThongTinLNV.add(bLbMaLNV);
		bThongTinLNV.add(Box.createVerticalStrut(3));
		bThongTinLNV.add(bTxtMaLNV);
		bThongTinLNV.add(Box.createVerticalStrut(20));
		bThongTinLNV.add(bLbTenLNV);
		bThongTinLNV.add(Box.createVerticalStrut(3));
		bThongTinLNV.add(bTxtTenLNV);
		bThongTinLNV.add(Box.createVerticalStrut(20));
		bThongTinLNV.add(bLbHSL);
		bThongTinLNV.add(Box.createVerticalStrut(3));
		bThongTinLNV.add(bTxtHSL);
		bThongTinLNV.add(Box.createVerticalStrut(20));

		bThongTinLNV.add(Box.createVerticalStrut(30));
		bThongTinLNV.add(bThemSua);
		bThongTinLNV.add(Box.createVerticalStrut(15));
		bThongTinLNV.add(bXoaTrang);
		bThongTinLNV.add(Box.createVerticalStrut(30));

		pnLeftContent.add(bThongTinLNV);

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

		JLabel lbDSKH = new JLabel("Danh sách loại nhân viên");
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
		 * cbLuaChon = new JComboBox<String>(); cbLuaChon.addItem("Mã loại nhân viên");
		 * cbLuaChon.addItem("Tên loại nhân viên"); cbLuaChon.addItem("Hệ số lương");
		 * cbLuaChon.setFont(font2); cbLuaChon.setBackground(color2);
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

		modelLNV = new DefaultTableModel();
		modelLNV.addColumn("Mã loại nhân viên");
		modelLNV.addColumn("Tên loại nhân viên");
		modelLNV.addColumn("Hệ số lương");

		tbLoaiNhanVien = new JTable(modelLNV);
		JScrollPane jScrollPane = new JScrollPane(tbLoaiNhanVien, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JTableHeader tbHeaderKH = tbLoaiNhanVien.getTableHeader();
		tbHeaderKH.setFont(font2);
		tbHeaderKH.setBackground(color2);
		tbLoaiNhanVien.setFont(font2);
		tbLoaiNhanVien.setRowHeight(35);
		pnRightCenter.add(jScrollPane);
		loadData(daoLoaiNV.getALLLoaiNhanVien());

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
		pnRightBottom.add(btnSua);
		pnRightBottom.add(Box.createHorizontalStrut(7));
		pnRightBottom.add(btnXoa);
		pnRightBottom.add(Box.createHorizontalStrut(7));
		pnRightBottom.add(btnLuu);
		pnRightBottom.add(Box.createHorizontalStrut(7));
		pnRightBottom.add(btnXoaTrang);

		pnRight.add(pnRightTop, BorderLayout.NORTH);
		pnRight.add(pnRightCenter, BorderLayout.CENTER);
		pnRight.add(pnRightBottom, BorderLayout.SOUTH);

		pnBorder.add(pnLeft, BorderLayout.WEST);
		pnBorder.add(pnRight, BorderLayout.CENTER);
		getContentPane().add(pnBorder);

		tbLoaiNhanVien.setDefaultEditor(Object.class, null);

		dongMoNhapLieu(false);

		btnXoaTrang.setEnabled(false);
		btnLuu.setEnabled(false);

		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		// btnTimKiem.addActionListener(this);
		btnLuu.addActionListener(this);
		// btnLamMoi.addActionListener(this);

		tbLoaiNhanVien.addMouseListener(this);
	}

	public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
		new FrmQuanLyLoaiNhanVien().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int hangDuocChon = tbLoaiNhanVien.getSelectedRow();
		txtMaLNV.setText(modelLNV.getValueAt(hangDuocChon, 0).toString());
		txtTenLNV.setText(modelLNV.getValueAt(hangDuocChon, 1).toString());
		txtHSL.setText(modelLNV.getValueAt(hangDuocChon, 2).toString());

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
				btnSua.setEnabled(false);
				btnXoa.setEnabled(false);
				btnXoaTrang.setEnabled(true);
				// btnLamMoi.setEnabled(false);
				btnThem.setText("Hủy");
				tbLoaiNhanVien.removeMouseListener(this);
				xoaRongNhapLieu();
				int soHang = modelLNV.getRowCount();
				// Mã có dạng LNVXXX, XXX: từ 001 -> 999
				if (soHang < 999) {
					int soCuoi = taoSoCuoiMaLNV();
					// TH1: số cuối tìm được có 1 chữ số
					if (soCuoi < 10) {
						txtMaLNV.setText("LNV00" + soCuoi);
					} else {
						// TH2: Số cuối tìm được có 2 chữ số
						if (soCuoi < 100) {
							txtMaLNV.setText("LNV0" + soCuoi);
						} else {
							// TH3: Số cuối tìm được có 3 chữ số
							txtMaLNV.setText("KH" + soCuoi);
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Dữ liệu đầy, không thể thêm!");
				}
			} else {
				tbLoaiNhanVien.addMouseListener(this);
				dongMoNhapLieu(false);
				btnThem.setText("Thêm");
				btnSua.setEnabled(true);
				// btnLamMoi.setEnabled(true);
				btnXoa.setEnabled(true);
				btnXoaTrang.setEnabled(false);
				btnLuu.setEnabled(false);
				tbLoaiNhanVien.addMouseListener(this);
				txtMaLNV.setText("");
				xoaRongNhapLieu();
				try {
					loadData(daoLoaiNV.getALLLoaiNhanVien());
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			}

		}
		if (obj.equals(btnXoaTrang)) {
			xoaRongNhapLieu();
		}
		if (obj.equals(btnXoa)) {
			int hangDuocChon = tbLoaiNhanVien.getSelectedRow();
			if (hangDuocChon > -1) {
				if (JOptionPane.showConfirmDialog(this, "Chắc chắn xóa dòng này?") == JOptionPane.YES_OPTION) {
					try {
						if (daoLoaiNV.deleteLoaiNVTheoMa(modelLNV.getValueAt(hangDuocChon, 0).toString())) {
							loadData(daoLoaiNV.getALLLoaiNhanVien());
							JOptionPane.showMessageDialog(this, "Xóa thành công.");
							txtMaLNV.setText("");
							xoaRongNhapLieu();
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Chọn 1 hàng trong bảng danh sách loại nhân viên cần xóa!");
			}
		}
		if (obj.equals(btnSua)) {
			if (btnSua.getText().equals("Sửa")) {
				int hangDuocChon = tbLoaiNhanVien.getSelectedRow();
				if (hangDuocChon > -1) {
					dongMoNhapLieu(true);
					btnSua.setText("Hủy");
					btnThem.setEnabled(false);
					// btnLamMoi.setEnabled(false);
					btnXoa.setEnabled(false);
					btnXoaTrang.setEnabled(true);
					btnLuu.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(this,
							"Vui lòng chọn 1 hàng trong bảng danh sách loại nhân viên, trước khi sửa!");
				}
			} else {
				dongMoNhapLieu(false);
				btnSua.setText("Sửa");
				btnSua.setText("Sửa");
				btnThem.setEnabled(true);
				// btnLamMoi.setEnabled(true);
				btnXoa.setEnabled(true);
				btnXoaTrang.setEnabled(false);
				btnLuu.setEnabled(false);
				txtMaLNV.setText("");
				xoaRongNhapLieu();
			}
		}

		if (obj.equals(btnLuu)) {
			if (btnThem.getText().equals("Hủy")) {
				if (validData()) {
					String maLNV = txtMaLNV.getText().trim();
					String tenLNV = txtTenLNV.getText().trim();
					String hsl = txtHSL.getText().trim();
					try {
						if (daoLoaiNV.addLoaiNhanVien(new LoaiNhanVien(maLNV, tenLNV, Float.parseFloat(hsl)))) {
							loadData(daoLoaiNV.getALLLoaiNhanVien());
							JOptionPane.showMessageDialog(this, "Thêm loại nhân viên mới thành công");
							dongMoNhapLieu(false);
							btnThem.setText("Thêm");
							xoaRongNhapLieu();
							btnSua.setEnabled(true);
							// btnLamMoi.setEnabled(true);
							btnXoa.setEnabled(true);
							btnXoaTrang.setEnabled(false);
							btnLuu.setEnabled(false);
							txtMaLNV.setText("");
							tbLoaiNhanVien.addMouseListener(this);

						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			}
			if (btnSua.getText().equals("Hủy")) {
				if (validData()) {
					String maLNV = txtMaLNV.getText().trim();
					String tenLNV = txtTenLNV.getText().trim();
					String hsl = txtHSL.getText().trim();
					try {
						if (daoLoaiNV.updateLoaiNV(new LoaiNhanVien(maLNV, tenLNV, Float.parseFloat(hsl)))) {
							loadData(daoLoaiNV.getALLLoaiNhanVien());
							JOptionPane.showMessageDialog(this, "Sửa thông tin loại nhân viên thành công");
							dongMoNhapLieu(false);
							btnSua.setText("Sửa");
							btnThem.setEnabled(true);
							// btnLamMoi.setEnabled(true);
							btnXoa.setEnabled(true);
							btnXoaTrang.setEnabled(false);
							btnLuu.setEnabled(false);
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			}

		}
		/*
		 * if(obj.equals(btnLamMoi)) { loadData(daoLoaiNV.getALLLoaiNhanVien()); }
		 */

	}

	/**
	 * Tạo số cuối của mã mới không trùng với các mã đã có trong table. Lưu ý: Cần
	 * sắp xếp mã trên table theo thứ tự tăng dần.
	 * 
	 * @return Số cuối của mả mới.
	 */
	private int taoSoCuoiMaLNV() {
		int i = 0; // thứ tự hàng
		while (i < modelLNV.getRowCount()) {
			// Lấy mã ở hàng thứ i trong table bỏ "DV" và chuyển sang kiểu int.
			// Tìm mã không khớp với hàng thứ i. Mã không khớp sẽ có số cuối mã
			// - i > 1
			if (Integer.parseInt(modelLNV.getValueAt(i, 0).toString().substring(3)) == (i + 1))
				// nếu mã khớp thì tăng i lên để xét hàng tiếp theo.
				i++;
			else
				// nếu mã không khớp thì thoát vòng lập
				break;
		}
		// i ra khỏi vòng lập là hàng có mã không khớp hoặc bằng với số hàng, ta
		// chỉ cần + 1 để có số cuối của mã mới.
		return i + 1;
	}

	private boolean validData() {
		String tenLNV = txtTenLNV.getText().trim();
		String hsl = txtHSL.getText().trim();
		if (tenLNV.length() == 0) {
			showMessage(txtTenLNV, "Nhập tên loại nhân viên!");
			return false;
		}
		if (!tenLNV.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]*\\s?)+$")) {
			showMessage(txtTenLNV,
					"Tên loại nhân viên bao gồm chữ cái, chữ số tiếng Việt, không bao gồm ký tự đặc biệt!");
			return false;
		}
		if (hsl.length() == 0) {
			showMessage(txtHSL, "Nhập hệ số lương!");
			return false;
		}
		if (!hsl.matches("^\\d$") && Float.parseFloat(hsl) <= 0) {
			showMessage(txtHSL, "Điểm là số lớn hơn 0");
			return false;
		}
		return true;
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}

	private void xoaRongNhapLieu() {
		txtTenLNV.requestFocus();
		txtTenLNV.setText("");
		txtHSL.setText("");
	}

	private void dongMoNhapLieu(Boolean b) {
		txtMaLNV.setEditable(false);
		txtTenLNV.setEditable(b);
		txtHSL.setEnabled(b);
	}

	private void loadData(LoaiNhanVien lnv) {
		modelLNV.setRowCount(0);
		String maLNV = lnv.getMaLoaiVN();
		String tenLNV = lnv.getTenLoai();
		String hsl = lnv.getHeSoLuong() + "";
		String row[] = { maLNV, tenLNV, hsl };
		modelLNV.addRow(row);
	}

	private void loadData(ArrayList<LoaiNhanVien> dsLNV) {
		modelLNV.setRowCount(0);
		for (int i = 0; i < dsLNV.size(); i++) {
			String maLNV = dsLNV.get(i).getMaLoaiVN();
			String tenLNV = dsLNV.get(i).getTenLoai();
			String hsl = dsLNV.get(i).getHeSoLuong() + "";
			String row[] = { maLNV, tenLNV, hsl };
			modelLNV.addRow(row);
		}
	}

}
