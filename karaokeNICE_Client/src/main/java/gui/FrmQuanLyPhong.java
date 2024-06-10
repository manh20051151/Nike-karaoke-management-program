package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

//import connectDB.ConnectDB;
//import dao.DAO_DichVu;
//import dao.DAO_DonVi;
//import dao.DAO_LoaiDichVu;
//import dao.DAO_LoaiPhong;
//import dao.DAO_Phong;
import dao.DaoLoaiPhong;
import dao.DaoPhong;
import entity.DichVu;
import entity.DonVi;
import entity.LoaiDichVu;
import entity.LoaiPhong;
import entity.Phong;

public class FrmQuanLyPhong extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final String URL = "rmi://26.173.247.75:7878/";
	DaoLoaiPhong daoLoaiPhong = (DaoLoaiPhong) Naming.lookup(URL + "daoLoaiPhong");
	DaoPhong daoPhong = (DaoPhong) Naming.lookup(URL + "daoPhong");

//	private DAO_Phong daoPhong = new DAO_Phong();
//	private DAO_LoaiPhong daoLoaiPhong = new DAO_LoaiPhong();
	private static final long serialVersionUID = 1L;
	public static JPanel pnlBorder;
	private JTextField txtMaPhong;
	private JTextField txtTenPhong;
	private JTextField txtViTri;
	private JTextField txtLoaiPhong;
	private JTextField txtSucChua;
	private JTextField txtTrangThai;
	private JComboBox<String> cmbLoaiPhong;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnLuu;
	private DefaultTableModel modelPhong;
	private JTable tblPhong;

	private Box bTxtLoaiPhong;
	private Color color1;
	private Color color2;

	public FrmQuanLyPhong(ArrayList<Phong> ds) throws MalformedURLException, NotBoundException, RemoteException {
		this.setTitle("Quản lý phòng");
		this.setSize(1920, 1040);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Toàn màn hình
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.createUI(ds);
	}

	private void createUI(ArrayList<Phong> ds) throws RemoteException {
//		ConnectDB.getInstance().connect();

		// Kiểu chữ
		Font font1 = new Font("Times New Roman", Font.BOLD, 36);
		Font font2 = new Font("Times New Roman", Font.PLAIN, 24);
		Font font3 = new Font("Times New Roman", Font.BOLD, 24);

		// Màu chữ
		color1 = new Color(138, 43, 226); // BlueViolet
		color2 = new Color(233, 221, 244);

		pnlBorder = new JPanel();
		pnlBorder.setLayout(new BorderLayout());
		pnlBorder.setBorder(new EmptyBorder(20, 20, 20, 20));
		pnlBorder.setBackground(color2);

		// Left
		JPanel pnlLeft = new JPanel();
		pnlLeft.setLayout(new BorderLayout());
		pnlLeft.setBackground(color2);

		// Left-Content
		JPanel pnlLeftContent = new JPanel();
		pnlLeftContent.setBorder(new EmptyBorder(30, 50, 30, 50));
		pnlLeftContent.setBackground(Color.WHITE);

		Box bThongTinPhong = Box.createVerticalBox();
		Box bLblMaPhong = Box.createHorizontalBox();
		Box bTxtMaPhong = Box.createHorizontalBox();
		Box bLblTenPhong = Box.createHorizontalBox();
		Box bTxtTenPhong = Box.createHorizontalBox();
		Box bLblViTri = Box.createHorizontalBox();
		Box bTxtViTri = Box.createHorizontalBox();
		Box bLblLoaiPhong = Box.createHorizontalBox();
		bTxtLoaiPhong = Box.createHorizontalBox();
		Box blblSucChua = Box.createHorizontalBox();
		Box bTxtSucChua = Box.createHorizontalBox();
		Box bLblTrangThai = Box.createHorizontalBox();
		Box bTxtTrangThai = Box.createHorizontalBox();

		JLabel lblMaPhong = new JLabel("Mã phòng");
		lblMaPhong.setFont(font2);
		JLabel lblTenPhong = new JLabel("Tên phòng:");
		lblTenPhong.setFont(font2);
		JLabel lblViTri = new JLabel("Vị trí:");
		lblViTri.setFont(font2);
		JLabel lblLoaiPhong = new JLabel("Loại phòng:");
		lblLoaiPhong.setFont(font2);
		JLabel lblSucChua = new JLabel("Sức chứa:");
		lblSucChua.setFont(font2);
		JLabel lblTrangThai = new JLabel("Trạng thái:");
		lblTrangThai.setFont(font2);

		bLblMaPhong.add(lblMaPhong);
		bLblMaPhong.add(Box.createVerticalStrut(0));
		bLblTenPhong.add(lblTenPhong);
		bLblTenPhong.add(Box.createVerticalStrut(0));
		bLblViTri.add(lblViTri);
		bLblViTri.add(Box.createVerticalStrut(0));
		bLblLoaiPhong.add(lblLoaiPhong);
		bLblLoaiPhong.add(Box.createVerticalStrut(0));
		blblSucChua.add(lblSucChua);
		blblSucChua.add(Box.createVerticalStrut(0));
		bLblTrangThai.add(lblTrangThai);
		bLblTrangThai.add(Box.createVerticalStrut(0));

		txtMaPhong = new JTextField(17);
		txtMaPhong.setFont(font2);
		txtMaPhong.setBackground(color2);
		txtTenPhong = new JTextField(17);
		txtTenPhong.setFont(font2);
		txtTenPhong.setBackground(color2);
		txtViTri = new JTextField(17);
		txtViTri.setFont(font2);
		txtViTri.setBackground(color2);
		txtLoaiPhong = new JTextField(17);
		txtLoaiPhong.setFont(font2);
		txtLoaiPhong.setBackground(color2);

		cmbLoaiPhong = new JComboBox<String>();
		ArrayList<LoaiPhong> loaiDichVu = daoLoaiPhong.getALLLoaiPhong();
		for (int i = 0; i < loaiDichVu.size(); i++) {
			cmbLoaiPhong.addItem(loaiDichVu.get(i).getTenLoaiPhong());
		}
		cmbLoaiPhong.setFont(font2);

		txtSucChua = new JTextField(17);
		txtSucChua.setFont(font2);
		txtSucChua.setBackground(color2);
		txtTrangThai = new JTextField(17);
		txtTrangThai.setFont(font2);
		txtTrangThai.setBackground(color2);

		bTxtMaPhong.add(txtMaPhong);
		bTxtTenPhong.add(txtTenPhong);
		bTxtViTri.add(txtViTri);
		bTxtLoaiPhong.add(txtLoaiPhong);
		bTxtSucChua.add(txtSucChua);
		bTxtTrangThai.add(txtTrangThai);

		bThongTinPhong.add(bLblMaPhong);
		bThongTinPhong.add(Box.createVerticalStrut(1));
		bThongTinPhong.add(bTxtMaPhong);
		bThongTinPhong.add(Box.createVerticalStrut(10));
		bThongTinPhong.add(bLblTenPhong);
		bThongTinPhong.add(Box.createVerticalStrut(1));
		bThongTinPhong.add(bTxtTenPhong);
		bThongTinPhong.add(Box.createVerticalStrut(10));
		bThongTinPhong.add(bLblViTri);
		bThongTinPhong.add(Box.createVerticalStrut(1));
		bThongTinPhong.add(bTxtViTri);
		bThongTinPhong.add(Box.createVerticalStrut(10));
		bThongTinPhong.add(bLblLoaiPhong);
		bThongTinPhong.add(Box.createVerticalStrut(1));
		bThongTinPhong.add(bTxtLoaiPhong);
		bThongTinPhong.add(Box.createVerticalStrut(10));
		bThongTinPhong.add(blblSucChua);
		bThongTinPhong.add(Box.createVerticalStrut(1));
		bThongTinPhong.add(bTxtSucChua);
		bThongTinPhong.add(Box.createVerticalStrut(10));
		bThongTinPhong.add(bLblTrangThai);
		bThongTinPhong.add(Box.createVerticalStrut(1));
		bThongTinPhong.add(bTxtTrangThai);

		pnlLeftContent.add(bThongTinPhong);

		pnlLeft.add(pnlLeftContent, BorderLayout.CENTER);
		pnlLeft.add(Box.createHorizontalStrut(20), BorderLayout.EAST);

		// Right
		JPanel pnlRight = new JPanel();
		pnlRight.setBackground(color1);
		pnlRight.setLayout(new BorderLayout());

		// Right-Top
		JPanel pnlRightTop = new JPanel();
		pnlRightTop.setBackground(color2);
		pnlRightTop.setLayout(new BorderLayout());

		// Right-Top-Content
		JPanel pnlRightTopContent = new JPanel();
		pnlRightTopContent.setBorder(new EmptyBorder(25, 20, 25, 20));
		pnlRightTopContent.setBackground(Color.WHITE);

		JLabel lblTittle = new JLabel("QUẢN LÝ PHÒNG");
		lblTittle.setFont(font1);
		lblTittle.setForeground(color1);
		pnlRightTopContent.add(lblTittle);

		pnlRightTop.add(pnlRightTopContent, BorderLayout.CENTER);
		pnlRightTop.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);

		// Right-Center
		JPanel pnlRightCenter = new JPanel();
		pnlRightCenter.setBackground(color2);
		pnlRightCenter.setLayout(new BorderLayout());

		// Right-Center-Content
		JPanel pnRightCenterContent = new JPanel();
		pnRightCenterContent.setLayout(new BorderLayout());

		modelPhong = new DefaultTableModel();
		modelPhong.addColumn("STT");
		modelPhong.addColumn("Mã phòng");
		modelPhong.addColumn("Tên phòng");
		modelPhong.addColumn("Vị trí");
		modelPhong.addColumn("Loại phòng");
		modelPhong.addColumn("Sức chứa");
		modelPhong.addColumn("Trạng thái");
		tblPhong = new JTable(modelPhong);
		JScrollPane jScrollPane = new JScrollPane(tblPhong, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JTableHeader tbhPhong = tblPhong.getTableHeader();
		tbhPhong.setFont(font2);
		tblPhong.setFont(font2);
		tblPhong.setRowHeight(35);
		tblPhong.setDefaultEditor(Object.class, null);
		pnRightCenterContent.add(jScrollPane, BorderLayout.CENTER);

		dieuChinhDoRongCotTable(tblPhong.getColumn("Mã phòng"), 120);
		dieuChinhDoRongCotTable(tblPhong.getColumn("Tên phòng"), 300);
		dieuChinhDoRongCotTable(tblPhong.getColumn("Vị trí"), 300);
		dieuChinhDoRongCotTable(tblPhong.getColumn("Loại phòng"), 120);
		dieuChinhDoRongCotTable(tblPhong.getColumn("Sức chứa"), 120);
		dieuChinhDoRongCotTable(tblPhong.getColumn("Trạng thái"), 180);

		((DefaultTableCellRenderer) tblPhong.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(JLabel.CENTER);

		canChinhCotTable(tblPhong.getColumn("STT"), DefaultTableCellRenderer.CENTER);
		canChinhCotTable(tblPhong.getColumn("Mã phòng"), DefaultTableCellRenderer.CENTER);
		canChinhCotTable(tblPhong.getColumn("Loại phòng"), DefaultTableCellRenderer.CENTER);
		canChinhCotTable(tblPhong.getColumn("Sức chứa"), DefaultTableCellRenderer.CENTER);
		canChinhCotTable(tblPhong.getColumn("Trạng thái"), DefaultTableCellRenderer.CENTER);

		pnlRightCenter.add(pnRightCenterContent, BorderLayout.CENTER);
		pnlRightCenter.add(Box.createVerticalStrut(20), BorderLayout.SOUTH);

		// Right-Bottom
		JPanel pnlRightBottom = new JPanel();
		pnlRightBottom.setBackground(Color.WHITE);
		pnlRightBottom.setBorder(new EmptyBorder(25, 25, 25, 25));

		btnThem = new JButton("Thêm");
		btnThem.setFont(font3);
		btnThem.setBackground(color1);
		btnThem.setForeground(Color.WHITE);
		btnThem.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Them.png"));
		btnSua = new JButton("Sửa");
		btnSua.setFont(font3);
		btnSua.setBackground(color1);
		btnSua.setForeground(Color.WHITE);
		btnSua.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Sua.png"));
		btnXoa = new JButton("Xóa");
		btnXoa.setBackground(color1);
		btnXoa.setForeground(Color.WHITE);
		btnXoa.setFont(font3);
		btnXoa.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Xoa.png"));
		btnLuu = new JButton("Lưu");
		btnLuu.setBackground(Color.WHITE);
		btnLuu.setForeground(Color.WHITE);
		btnLuu.setFont(font3);
		btnLuu.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Luu.png"));

		btnThem.setPreferredSize(btnThem.getPreferredSize());
		btnSua.setPreferredSize(btnThem.getPreferredSize());
		btnXoa.setPreferredSize(btnThem.getPreferredSize());
		btnLuu.setPreferredSize(btnThem.getPreferredSize());

		pnlRightBottom.add(btnThem);
		pnlRightBottom.add(Box.createHorizontalStrut(40));
		pnlRightBottom.add(btnSua);
		pnlRightBottom.add(Box.createHorizontalStrut(40));
		pnlRightBottom.add(btnXoa);
		pnlRightBottom.add(Box.createHorizontalStrut(40));
		pnlRightBottom.add(btnLuu);

		pnlRight.add(pnlRightTop, BorderLayout.NORTH);
		pnlRight.add(pnlRightCenter, BorderLayout.CENTER);
		pnlRight.add(pnlRightBottom, BorderLayout.SOUTH);

		pnlBorder.add(pnlLeft, BorderLayout.WEST);
		pnlBorder.add(pnlRight, BorderLayout.CENTER);
		this.add(pnlBorder);

		dongMoNhapLieu(false);

		btnLuu.setEnabled(false);

		loadData(ds);

		tblPhong.addMouseListener(this);

		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (btnThem.getText().equals("Thêm")) {
				int soHang = modelPhong.getRowCount();
				// Mã có dạng PXXX, XXX: từ 001 -> 999
				if (soHang < 999) {
					dongMoNhapLieu(true);

					// Chuyển txtLoaiPhong thành cmbLoaiPhong
					bTxtLoaiPhong.remove(txtLoaiPhong);
					bTxtLoaiPhong.add(cmbLoaiPhong);

					btnThem.setText("Hủy");
					btnThem.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Huy.png"));
					btnSua.setEnabled(false);
					btnSua.setBackground(Color.WHITE);
					btnXoa.setEnabled(false);
					btnXoa.setBackground(Color.WHITE);
					btnLuu.setEnabled(true);
					btnLuu.setBackground(color1);

					xoaRongNhapLieu();

					// Trạng thái khi thêm phòng mới mặc định là trống, không cho người dùng nhập
					txtTrangThai.setText("Trống");

					// Không cho load dữ liệu table vào textfield
					tblPhong.removeMouseListener(this);

					// Lấy mã tự động
					int soCuoi = 0;
					try {
						soCuoi = taoSoCuoiMaPhong();
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
					// TH1: số cuối tìm được có 1 chữ số
					if (soCuoi < 10) {
						txtMaPhong.setText("P00" + soCuoi);
					} else {
						// TH2: Số cuối tìm được có 2 chữ số
						if (soCuoi < 100) {
							txtMaPhong.setText("P0" + soCuoi);
						} else {
							// TH3: Số cuối tìm được có 3 chữ số
							txtMaPhong.setText("P" + soCuoi);
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Dữ liệu đầy, không thể thêm!", "Lỗi",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				// Khi nhấn hủy thì thêm lại sự kiện click chọn hàng load dữ liệ lên textfield
				tblPhong.addMouseListener(this);

				dongMoNhapLieu(false);

				// Chuyển cmbLoaiPhong thành txtLoaiPhong
				bTxtLoaiPhong.remove(cmbLoaiPhong);
				bTxtLoaiPhong.add(txtLoaiPhong);

				btnThem.setText("Thêm");
				btnThem.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Them.png"));
				btnSua.setEnabled(true);
				btnSua.setBackground(color1);
				btnXoa.setEnabled(true);
				btnXoa.setBackground(color1);
				btnLuu.setEnabled(false);
				btnLuu.setBackground(Color.WHITE);

				// Xóa chọn hàng trong table
				tblPhong.getSelectionModel().clearSelection();

				txtMaPhong.setText("");
				txtTrangThai.setText("");
				xoaRongNhapLieu();
			}
		}
		if (o.equals(btnSua)) {
			if (btnSua.getText().equals("Sửa")) {
				int hangDuocChon = tblPhong.getSelectedRow();
				if (hangDuocChon > -1) {
					dongMoNhapLieu(true);

					// Chuyển txtLoaiPhong thành cmbLoaiPhong
					bTxtLoaiPhong.remove(txtLoaiPhong);
					bTxtLoaiPhong.add(cmbLoaiPhong);
					cmbLoaiPhong.setSelectedItem(txtLoaiPhong.getText());

					btnSua.setText("Hủy");
					btnSua.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Huy.png"));
					btnThem.setEnabled(false);
					btnThem.setBackground(Color.WHITE);
					btnXoa.setEnabled(false);
					btnXoa.setBackground(Color.WHITE);
					btnLuu.setEnabled(true);
					btnLuu.setBackground(color1);
				} else {
					JOptionPane.showMessageDialog(this,
							"Vui lòng chọn 1 hàng trong bảng danh sách phòng trước khi sửa!", "Cảnh báo!",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				dongMoNhapLieu(false);

				// Chuyển cmbLoaiPhong thành txtLoaiPhong
				bTxtLoaiPhong.remove(cmbLoaiPhong);
				bTxtLoaiPhong.add(txtLoaiPhong);

				// Load lại dữ liệu ban đầu của hàng được chọn lại textfield cho trường hợp
				// người dùng sửa nhưng ko lưu mà nhấn hủy.
				int hangDuocChon = tblPhong.getSelectedRow();
				txtMaPhong.setText(modelPhong.getValueAt(hangDuocChon, 1).toString());
				txtTenPhong.setText(modelPhong.getValueAt(hangDuocChon, 2).toString());
				txtViTri.setText(modelPhong.getValueAt(hangDuocChon, 3).toString());
				txtLoaiPhong.setText(modelPhong.getValueAt(hangDuocChon, 4).toString());
				txtSucChua.setText(modelPhong.getValueAt(hangDuocChon, 5).toString());
				txtTrangThai.setText(modelPhong.getValueAt(hangDuocChon, 6).toString());

				btnSua.setText("Sửa");
				btnSua.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Sua.png"));
				btnThem.setEnabled(true);
				btnThem.setBackground(color1);
				btnXoa.setEnabled(true);
				btnXoa.setBackground(color1);
				btnLuu.setEnabled(false);
				btnLuu.setBackground(Color.WHITE);
			}
		}
		if (o.equals(btnXoa)) {
			int hangDuocChon = tblPhong.getSelectedRow();
			if (hangDuocChon > -1) {
				if (JOptionPane.showConfirmDialog(this, "Chắc chắn xóa dòng này?", "Thông báo",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String maPhong = modelPhong.getValueAt(hangDuocChon, 1).toString();
					try {
						if (daoPhong.xoaPhongTheoMa(maPhong)) {
							lamMoiTrang();
							JOptionPane.showMessageDialog(this, "Đã xóa phòng " + maPhong, "Thành công",
									JOptionPane.PLAIN_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(this, "Phòng này có tồn tại trong hóa đơn, không thể xóa!",
									"Lỗi!", JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng trong bảng danh sách phòng cần xóa!",
						"Cảnh báo!", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (o.equals(btnLuu)) {
			if (validData() == true) {
				String maPhong = txtMaPhong.getText().trim();
				String tenPhong = txtTenPhong.getText().trim();
				String viTri = txtViTri.getText().trim();
				String tenLoaiPhong = cmbLoaiPhong.getSelectedItem().toString();
				String sucChua = txtSucChua.getText().trim();
				String trangThai = txtTrangThai.getText().trim();
				LoaiPhong loaiPhong = null;
				try {
					loaiPhong = daoLoaiPhong.getLoaiTheoTen(tenLoaiPhong);
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				if (btnThem.getText().equals("Hủy")) {
					try {
						if (daoPhong.addPhong(
								new Phong(maPhong, tenPhong, viTri, trangThai, loaiPhong, Integer.parseInt(sucChua)))) {
							// Chuyển cbLoaiPhong thành txtLoaiPhong
							bTxtLoaiPhong.remove(cmbLoaiPhong);
							bTxtLoaiPhong.add(txtLoaiPhong);

							lamMoiTrang();

							JOptionPane.showMessageDialog(this, "Đã thêm phòng " + maPhong, "Thành công",
									JOptionPane.PLAIN_MESSAGE);

							dongMoNhapLieu(false);

							btnThem.setText("Thêm");
							btnThem.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Them.png"));
							btnSua.setEnabled(true);
							btnSua.setBackground(color1);
							btnXoa.setEnabled(true);
							btnXoa.setBackground(color1);
							btnLuu.setEnabled(false);
							btnLuu.setBackground(Color.WHITE);

							// Lưu thành công thì thêm lại sự kiện click chọn hàng load dữ liệ lên textfield
							tblPhong.addMouseListener(this);

						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
				if (btnSua.getText().equals("Hủy")) {
					try {
						if (daoPhong.capNhapPhong(
								new Phong(maPhong, tenPhong, viTri, trangThai, loaiPhong, Integer.parseInt(sucChua)))) {
							// Chuyển cbLoaiPhong thành txtLoaiPhong
							bTxtLoaiPhong.remove(cmbLoaiPhong);
							bTxtLoaiPhong.add(txtLoaiPhong);

							lamMoiTrang();

							JOptionPane.showMessageDialog(this, "Đã sửa thông tin phòng " + maPhong, "Thành công",
									JOptionPane.PLAIN_MESSAGE);

							dongMoNhapLieu(false);

							btnSua.setText("Sửa");
							btnSua.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Sua.png"));
							btnThem.setEnabled(true);
							btnThem.setBackground(color1);
							btnXoa.setEnabled(true);
							btnXoa.setBackground(color1);
							btnLuu.setEnabled(false);
							btnLuu.setBackground(Color.WHITE);
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}

				}
			}
		}
	}

	private void lamMoiTrang() throws RemoteException {
		loadData(daoPhong.getDanhSachPhong());
		txtMaPhong.setText("");
		txtTrangThai.setText("");
		xoaRongNhapLieu();
	}

	private void dongMoNhapLieu(Boolean b) {
		txtMaPhong.setEditable(false);
		txtLoaiPhong.setEditable(false);
		txtTrangThai.setEditable(false);
		txtTenPhong.setEditable(b);
		txtViTri.setEditable(b);
		txtSucChua.setEditable(b);
	}

	private void xoaRongNhapLieu() {
		txtTenPhong.setText("");
		txtViTri.setText("");
		cmbLoaiPhong.setSelectedIndex(0);
		txtLoaiPhong.setText("");
		txtSucChua.setText("");
		txtTenPhong.requestFocus();
	}

	/**
	 * Tạo số cuối của mã mới không trùng với các mã đã có trong table. Lưu ý: Cần
	 * sắp xếp mã trên table theo thứ tự tăng dần.
	 * 
	 * @return Số cuối của mả mới.
	 */
	private int taoSoCuoiMaPhong() throws RemoteException {
		ArrayList<Phong> ds = daoPhong.getDanhSachPhong();
		int i = 0; // thứ tự hàng
		while (i < ds.size()) {
			// Lấy mã ở hàng thứ i trong table bỏ "P" và chuyển sang kiểu int.
			// Tìm mã không khớp với hàng thứ i. Mã không khớp sẽ có số cuối mã
			// - i > 1
			if (Integer.parseInt(ds.get(i).getMaPhong().substring(1)) == (i + 1))
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

	private void loadData(ArrayList<Phong> dsPhong) {
		modelPhong.setRowCount(0);
		for (int i = 0; i < dsPhong.size(); i++) {
			String maPhong = dsPhong.get(i).getMaPhong();
			String tenPhong = dsPhong.get(i).getTenPhong();
			String viTri = dsPhong.get(i).getViTri();
			String tenLoaiPhong = dsPhong.get(i).getLoaiPhong().getTenLoaiPhong();
			String sucChua = dsPhong.get(i).getSucChua() + "";
			String trangThai = dsPhong.get(i).getTrangThaiPhong();
			String row[] = { (i + 1) + "", maPhong, tenPhong, viTri, tenLoaiPhong, sucChua, trangThai };
			modelPhong.addRow(row);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int hangDuocChon = tblPhong.getSelectedRow();
		txtMaPhong.setText(modelPhong.getValueAt(hangDuocChon, 1).toString());
		txtTenPhong.setText(modelPhong.getValueAt(hangDuocChon, 2).toString());
		txtViTri.setText(modelPhong.getValueAt(hangDuocChon, 3).toString());
		txtLoaiPhong.setText(modelPhong.getValueAt(hangDuocChon, 4).toString());
		txtSucChua.setText(modelPhong.getValueAt(hangDuocChon, 5).toString());
		txtTrangThai.setText(modelPhong.getValueAt(hangDuocChon, 6).toString());
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	private boolean validData() {
		String tenPhong = txtTenPhong.getText().trim();
		String viTri = txtViTri.getText().trim();
		String sucChua = txtSucChua.getText().trim();
		if (tenPhong.length() == 0) {
			showMessage(txtTenPhong, "Nhập tên phòng!");
			return false;
		}
		if (tenPhong.length() > 20) {
			showMessage(txtTenPhong, "Tên phòng không được vượt quá 20 ký tự!");
			return false;
		}
		if (!tenPhong.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]+\\s?)+$")) {
			showMessage(txtTenPhong,
					"Tên phòng bao gồm chữ cái (có thể viết tiếng việt), chữ số, có thể có 1 khoảng trắng ngăn cách giữa 2 từ và không bao gồm ký tự đặc biệt!");
			return false;
		}

		if (viTri.length() == 0) {
			showMessage(txtViTri, "Nhập vị trí!");
			return false;
		}
		if (viTri.length() > 60) {
			showMessage(txtTenPhong, "Vị trí không được vượt quá 60 ký tự!");
			return false;
		}
		if (!viTri.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]+\\s?(,|-)?\\s?)+$")) {
			showMessage(txtViTri,
					"Vị trí bao gồm chữ cái (có thể viết tiếng việt), chữ số, dấu phẩy, dấu gạch nối, khoảng trắng!");
			return false;
		}
		if (sucChua.length() == 0) {
			showMessage(txtSucChua, "Nhập sức chứa!");
			return false;
		}
		if (!sucChua.matches("^([1-9]\\d*)$")) {
			showMessage(txtSucChua, "Số lượng là số nguyên > 0 và không được bắt đầu bằng 0!");
			return false;
		}
		return true;
	}

	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		txt.requestFocus();
		JOptionPane.showMessageDialog(this, message, "Lỗi!", JOptionPane.ERROR_MESSAGE);
	}

	private void dieuChinhDoRongCotTable(TableColumn tableColumn, int doRong) {
		tableColumn.setPreferredWidth(doRong);
	}

	private void canChinhCotTable(TableColumn tableColumn, int canChinh) {
		DefaultTableCellRenderer dRenderer = new DefaultTableCellRenderer();
		dRenderer.setHorizontalAlignment(canChinh);
		tableColumn.setCellRenderer(dRenderer);
	}
}
