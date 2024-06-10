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
import dao.DaoDichVu;
import dao.DaoDonVi;
import dao.DaoLoaiDichVu;
import entity.DichVu;
import entity.DonVi;
import entity.LoaiDichVu;

public class FrmQuanLyDichVu extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final String URL = "rmi://26.173.247.75:7878/";
	DaoDonVi daoDonVi = (DaoDonVi) Naming.lookup(URL + "daoDonVi");
	DaoLoaiDichVu daoLoaiDV = (DaoLoaiDichVu) Naming.lookup(URL + "daoLoaiDichVu");
	DaoDichVu daoDichVu = (DaoDichVu) Naming.lookup(URL + "daoDichVu");

//	private DAO_DichVu daoDichVu = new DAO_DichVu();
//	private DAO_LoaiDichVu daoLoaiDV = new DAO_LoaiDichVu();
//	private DAO_DonVi daoDonVi = new DAO_DonVi();

	private static final long serialVersionUID = 1L;
	public static JPanel pnlBorder;
	private JTextField txtMaDV;
	private JTextField txtTenDV;
	private JComboBox<String> cmbDonVi;
	private JComboBox<String> cmbLoaiDV;
	private JTextField txtGiaBan;
	private JTextField txtSoLuong;
	private JTextArea txaGhiChu;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnLuu;
	private DefaultTableModel modelDV;
	private JTable tblDichVu;

	private JButton btnChonAnh;
	private JLabel lblAnh;
	private JTextField txtDonVi;
	private JTextField txtLoaiDV;
	private Box bTxtDonVi;
	private Box bTxtLoaiDV;
	private byte[] imageDichVu;
	private boolean kiemTraChonAnh;
	private Color color1;
	private Color color2;

	public FrmQuanLyDichVu(ArrayList<DichVu> ds) throws MalformedURLException, NotBoundException, RemoteException {
		this.setTitle("Quản lý dịch vụ");
		this.setSize(1920, 1040);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // Toàn màn hình
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.createUI(ds);
	}

	private void createUI(ArrayList<DichVu> ds) throws RemoteException {
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

		Box bThongTinDV = Box.createVerticalBox();
		Box bLblMaDV = Box.createHorizontalBox();
		Box bTxtMaDV = Box.createHorizontalBox();
		Box bLblTenDV = Box.createHorizontalBox();
		Box bTxtTenDV = Box.createHorizontalBox();
		Box bLblDonVi = Box.createHorizontalBox();
		bTxtDonVi = Box.createHorizontalBox();
		Box bLblLoaiDV = Box.createHorizontalBox();
		bTxtLoaiDV = Box.createHorizontalBox();
		Box blblGiaBan = Box.createHorizontalBox();
		Box bTxtGiaBan = Box.createHorizontalBox();
		Box bLblSoLuong = Box.createHorizontalBox();
		Box bTxtSoLuong = Box.createHorizontalBox();
		Box bLblGhiChu = Box.createHorizontalBox();
		Box bTxaGhiChu = Box.createHorizontalBox();

		JLabel lblMaDV = new JLabel("Mã dịch vụ:");
		lblMaDV.setFont(font2);
		JLabel lblTenDV = new JLabel("Tên dịch vụ:");
		lblTenDV.setFont(font2);
		JLabel lblDonVi = new JLabel("Đơn vị:");
		lblDonVi.setFont(font2);
		JLabel lblLoaiDV = new JLabel("Loại dịch vụ:");
		lblLoaiDV.setFont(font2);
		JLabel lblGiaBan = new JLabel("Giá bán:");
		lblGiaBan.setFont(font2);
		JLabel lblSoLuong = new JLabel("Số lượng:");
		lblSoLuong.setFont(font2);
		JLabel lblGhiChu = new JLabel("Ghi chú:");
		lblGhiChu.setFont(font2);

		bLblMaDV.add(lblMaDV);
		bLblMaDV.add(Box.createVerticalStrut(0));
		bLblTenDV.add(lblTenDV);
		bLblTenDV.add(Box.createVerticalStrut(0));
		bLblDonVi.add(lblDonVi);
		bLblDonVi.add(Box.createVerticalStrut(0));
		bLblLoaiDV.add(lblLoaiDV);
		bLblLoaiDV.add(Box.createVerticalStrut(0));
		blblGiaBan.add(lblGiaBan);
		blblGiaBan.add(Box.createVerticalStrut(0));
		bLblSoLuong.add(lblSoLuong);
		bLblSoLuong.add(Box.createVerticalStrut(0));
		bLblGhiChu.add(lblGhiChu);
		bLblGhiChu.add(Box.createVerticalStrut(0));

		txtMaDV = new JTextField(17);
		txtMaDV.setFont(font2);
		txtMaDV.setBackground(color2);
		txtTenDV = new JTextField(17);
		txtTenDV.setFont(font2);
		txtTenDV.setBackground(color2);
		txtDonVi = new JTextField(17);
		txtDonVi.setFont(font2);
		txtDonVi.setBackground(color2);
		txtLoaiDV = new JTextField(17);
		txtLoaiDV.setFont(font2);
		txtLoaiDV.setBackground(color2);

		cmbDonVi = new JComboBox<String>();
		ArrayList<DonVi> donVi = (ArrayList<DonVi>) daoDonVi.getALLDonVi();
		for (int i = 0; i < donVi.size(); i++) {
			cmbDonVi.addItem(donVi.get(i).getTenDonVi());
		}
		cmbDonVi.setFont(font2);
		cmbLoaiDV = new JComboBox<String>();
		ArrayList<LoaiDichVu> loaiDichVu = daoLoaiDV.getAllDichVu();
		for (int i = 0; i < loaiDichVu.size(); i++) {
			cmbLoaiDV.addItem(loaiDichVu.get(i).getTenLoaiDV());
		}
		cmbLoaiDV.setFont(font2);

		txtGiaBan = new JTextField(17);
		txtGiaBan.setFont(font2);
		txtGiaBan.setBackground(color2);
		txtSoLuong = new JTextField(17);
		txtSoLuong.setFont(font2);
		txtSoLuong.setBackground(color2);

		txaGhiChu = new JTextArea(2, 17);
		txaGhiChu.setFont(font2);
		txaGhiChu.setBackground(color2);
		txaGhiChu.setLineWrap(true);

		// Vùng chọn ảnh dịch vụ
		JPanel pnlHinhAnh = new JPanel();
		pnlHinhAnh.setLayout(new BorderLayout());
		pnlHinhAnh.setBackground(Color.WHITE);
		pnlHinhAnh.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(color1), "Hình ảnh",
				TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, font2, color1));

		JPanel pnlChuaAnh = new JPanel();
		pnlChuaAnh.setBackground(Color.WHITE);
		lblAnh = new JLabel();
		pnlChuaAnh.add(lblAnh);

		JPanel pnlChonAnh = new JPanel();
		pnlChonAnh.setBackground(Color.WHITE);
		btnChonAnh = new JButton(new ImageIcon("src/main/java/img/icons8-add-new-48.png"));
		btnChonAnh.setBackground(Color.WHITE);
		btnChonAnh.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnlChonAnh.add(btnChonAnh);
		pnlHinhAnh.add(pnlChuaAnh, BorderLayout.CENTER);
		pnlHinhAnh.add(pnlChonAnh, BorderLayout.SOUTH);

		bTxtMaDV.add(txtMaDV);
		bTxtTenDV.add(txtTenDV);
		bTxtDonVi.add(txtDonVi);
		bTxtLoaiDV.add(txtLoaiDV);
		bTxtGiaBan.add(txtGiaBan);
		bTxtSoLuong.add(txtSoLuong);

		bTxaGhiChu.add(txaGhiChu);

		bThongTinDV.add(bLblMaDV);
		bThongTinDV.add(Box.createVerticalStrut(1));
		bThongTinDV.add(bTxtMaDV);
		bThongTinDV.add(Box.createVerticalStrut(10));
		bThongTinDV.add(bLblTenDV);
		bThongTinDV.add(Box.createVerticalStrut(1));
		bThongTinDV.add(bTxtTenDV);
		bThongTinDV.add(Box.createVerticalStrut(10));
		bThongTinDV.add(bLblDonVi);
		bThongTinDV.add(Box.createVerticalStrut(1));
		bThongTinDV.add(bTxtDonVi);
		bThongTinDV.add(Box.createVerticalStrut(10));
		bThongTinDV.add(bLblLoaiDV);
		bThongTinDV.add(Box.createVerticalStrut(1));
		bThongTinDV.add(bTxtLoaiDV);
		bThongTinDV.add(Box.createVerticalStrut(10));
		bThongTinDV.add(blblGiaBan);
		bThongTinDV.add(Box.createVerticalStrut(1));
		bThongTinDV.add(bTxtGiaBan);
		bThongTinDV.add(Box.createVerticalStrut(10));
		bThongTinDV.add(bLblSoLuong);
		bThongTinDV.add(Box.createVerticalStrut(1));
		bThongTinDV.add(bTxtSoLuong);
		bThongTinDV.add(Box.createVerticalStrut(10));
		bThongTinDV.add(bLblGhiChu);
		bThongTinDV.add(Box.createVerticalStrut(1));
		bThongTinDV.add(bTxaGhiChu);
		bThongTinDV.add(Box.createVerticalStrut(10));
		bThongTinDV.add(pnlHinhAnh);

		pnlLeftContent.add(bThongTinDV);

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

		JLabel lblTittle = new JLabel("QUẢN LÝ DỊCH VỤ");
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

		modelDV = new DefaultTableModel();
		modelDV.addColumn("STT");
		modelDV.addColumn("Mã dịch vụ");
		modelDV.addColumn("Tên dịch vụ");
		modelDV.addColumn("Đơn vị");
		modelDV.addColumn("Loại dịch vụ");
		modelDV.addColumn("Giá bán");
		modelDV.addColumn("Số lượng");
		modelDV.addColumn("Ghi chú");
		tblDichVu = new JTable(modelDV);
		JScrollPane jScrollPane = new JScrollPane(tblDichVu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JTableHeader tbhDichVu = tblDichVu.getTableHeader();
		tbhDichVu.setFont(font2);
		tblDichVu.setFont(font2);
		tblDichVu.setRowHeight(35);
		tblDichVu.setDefaultEditor(Object.class, null);
		pnRightCenterContent.add(jScrollPane, BorderLayout.CENTER);

		dieuChinhDoRongCotTable(tblDichVu.getColumn("Mã dịch vụ"), 140);
		dieuChinhDoRongCotTable(tblDichVu.getColumn("Tên dịch vụ"), 200);
		dieuChinhDoRongCotTable(tblDichVu.getColumn("Đơn vị"), 100);
		dieuChinhDoRongCotTable(tblDichVu.getColumn("Loại dịch vụ"), 140);
		dieuChinhDoRongCotTable(tblDichVu.getColumn("Giá bán"), 150);
		dieuChinhDoRongCotTable(tblDichVu.getColumn("Số lượng"), 100);
		dieuChinhDoRongCotTable(tblDichVu.getColumn("Ghi chú"), 300);

		((DefaultTableCellRenderer) tblDichVu.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(JLabel.CENTER);

		canChinhCotTable(tblDichVu.getColumn("STT"), DefaultTableCellRenderer.CENTER);
		canChinhCotTable(tblDichVu.getColumn("Mã dịch vụ"), DefaultTableCellRenderer.CENTER);
		canChinhCotTable(tblDichVu.getColumn("Đơn vị"), DefaultTableCellRenderer.CENTER);
		canChinhCotTable(tblDichVu.getColumn("Loại dịch vụ"), DefaultTableCellRenderer.CENTER);
		canChinhCotTable(tblDichVu.getColumn("Giá bán"), DefaultTableCellRenderer.RIGHT);
		canChinhCotTable(tblDichVu.getColumn("Số lượng"), DefaultTableCellRenderer.CENTER);

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

		btnChonAnh.setEnabled(false);
		btnLuu.setEnabled(false);

		loadData(ds);

		tblDichVu.addMouseListener(this);

		btnChonAnh.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnLuu.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnChonAnh)) {
			JFileChooser fch = new JFileChooser();
			if (fch.showOpenDialog(this) == JFileChooser.CANCEL_OPTION) {
				return;
			}
			kiemTraChonAnh = true;
			File f = fch.getSelectedFile();
			String filename = f.getAbsolutePath();
			ImageIcon imageIcon = new ImageIcon(
					new ImageIcon(filename).getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH));
			lblAnh.setText("");
			lblAnh.setIcon(imageIcon);
			btnChonAnh.setIcon(
					new ImageIcon("src/main/java/img/icons8-update-left-rotation-24.png"));
			try {
				File image = new File(filename);
				try (FileInputStream fis = new FileInputStream(image)) {
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] b = new byte[2014];
					for (int readNum; (readNum = fis.read(b)) != -1;) {
						bos.write(b, 0, readNum);
					}
					imageDichVu = bos.toByteArray();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if (o.equals(btnThem)) {
			if (btnThem.getText().equals("Thêm")) {
				int soHang = modelDV.getRowCount();
				// Mã có dạng DVXXX, XXX: từ 001 -> 999
				if (soHang < 999) {
					dongMoNhapLieu(true);

					// Chuyển txtDonVi thành cbDonVi
					bTxtDonVi.remove(txtDonVi);
					bTxtDonVi.add(cmbDonVi);

					// Chuyển txtLoaiDV thành cbLoaiDV
					bTxtLoaiDV.remove(txtLoaiDV);
					bTxtLoaiDV.add(cmbLoaiDV);

					btnThem.setText("Hủy");
					btnThem.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Huy.png"));
					btnChonAnh.setEnabled(true);
					btnSua.setEnabled(false);
					btnSua.setBackground(Color.WHITE);
					btnXoa.setEnabled(false);
					btnXoa.setBackground(Color.WHITE);
					btnLuu.setEnabled(true);
					btnLuu.setBackground(color1);

					lblAnh.setText("");
					lblAnh.setIcon(null);
					imageDichVu = null;
					btnChonAnh.setIcon(new ImageIcon("src/main/java/img/icons8-add-new-48.png"));

					xoaRongNhapLieu();

					// Không cho load dữ liệu table vào textfield
					tblDichVu.removeMouseListener(this);

					// Lấy mã tự động
					int soCuoi = 0;
					try {
						soCuoi = taoSoCuoiMaDV();
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
					// TH1: số cuối tìm được có 1 chữ số
					if (soCuoi < 10) {
						txtMaDV.setText("DV00" + soCuoi);
					} else {
						// TH2: Số cuối tìm được có 2 chữ số
						if (soCuoi < 100) {
							txtMaDV.setText("DV0" + soCuoi);
						} else {
							// TH3: Số cuối tìm được có 3 chữ số
							txtMaDV.setText("DV" + soCuoi);
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Dữ liệu đầy, không thể thêm!", "Lỗi!",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				// Khi nhấn hủy thì thêm lại sự kiện click chọn hàng load dữ liệ lên textfield
				tblDichVu.addMouseListener(this);

				dongMoNhapLieu(false);

				// Chuyển cbDonVi thành txtDonVi
				bTxtDonVi.remove(cmbDonVi);
				bTxtDonVi.add(txtDonVi);

				// Chuyển cbLoaiDV thành txtLoaiDV
				bTxtLoaiDV.remove(cmbLoaiDV);
				bTxtLoaiDV.add(txtLoaiDV);

				lblAnh.setText("");
				lblAnh.setIcon(null);

				btnThem.setText("Thêm");
				btnThem.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Them.png"));
				btnChonAnh.setEnabled(false);
				btnSua.setEnabled(true);
				btnSua.setBackground(color1);
				btnXoa.setEnabled(true);
				btnXoa.setBackground(color1);
				btnLuu.setEnabled(false);
				btnLuu.setBackground(Color.WHITE);

				// Xóa chọn hàng trong table
				tblDichVu.getSelectionModel().clearSelection();

				txtMaDV.setText("");
				xoaRongNhapLieu();
			}
		}
		if (o.equals(btnSua)) {
			if (btnSua.getText().equals("Sửa")) {
				int hangDuocChon = tblDichVu.getSelectedRow();
				if (hangDuocChon > -1) {
					dongMoNhapLieu(true);

					// Chuyển txtDonVi thành cbDonVi
					bTxtDonVi.remove(txtDonVi);
					bTxtDonVi.add(cmbDonVi);
					cmbDonVi.setSelectedItem(txtDonVi.getText());

					// Chuyển txtLoaiDV thành cbLoaiDV
					bTxtLoaiDV.remove(txtLoaiDV);
					bTxtLoaiDV.add(cmbLoaiDV);
					cmbLoaiDV.setSelectedItem(txtLoaiDV.getText());

					btnSua.setText("Hủy");
					btnSua.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Huy.png"));
					btnChonAnh.setEnabled(true);
					btnThem.setEnabled(false);
					btnThem.setBackground(Color.WHITE);
					btnXoa.setEnabled(false);
					btnXoa.setBackground(Color.WHITE);
					btnLuu.setEnabled(true);
					btnLuu.setBackground(color1);
				} else {
					JOptionPane.showMessageDialog(this,
							"Vui lòng chọn 1 hàng trong bảng danh sách dịch vụ, trước khi sửa!", "Cảnh báo!",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				dongMoNhapLieu(false);

				// Chuyển cbDonVi thành txtDonVi
				bTxtDonVi.remove(cmbDonVi);
				bTxtDonVi.add(txtDonVi);

				// Chuyển cbLoaiDV thành txtLoaiDV
				bTxtLoaiDV.remove(cmbLoaiDV);
				bTxtLoaiDV.add(txtLoaiDV);

				// Load lại dữ liệu ban đầu của hàng được chọn lại textfield cho trường hợp
				// người dùng sửa nhưng ko lưu mà nhấn hủy.
				DecimalFormat df = new DecimalFormat("#,##0đ");
				int hangDuocChon = tblDichVu.getSelectedRow();
				String maDV = modelDV.getValueAt(hangDuocChon, 1).toString();
				txtMaDV.setText(maDV);
				txtTenDV.setText(modelDV.getValueAt(hangDuocChon, 2).toString());
				txtDonVi.setText(modelDV.getValueAt(hangDuocChon, 3).toString());
				txtLoaiDV.setText(modelDV.getValueAt(hangDuocChon, 4).toString());

				// Bỏ định dạng tiền
				String gia = modelDV.getValueAt(hangDuocChon, 5).toString();
				try {
					txtGiaBan.setText(df.parse(gia).toString());
				} catch (ParseException e2) {
					e2.printStackTrace();
				}

				txtSoLuong.setText(modelDV.getValueAt(hangDuocChon, 6).toString());
				txaGhiChu.setText(modelDV.getValueAt(hangDuocChon, 7).toString());

				// load ảnh
				byte[] hinhAnh = new byte[0];
				try {
					hinhAnh = daoDichVu.getHinhAnhTheoMaDichVu(maDV);
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				if (hinhAnh != null) {
					lblAnh.setText("");
					lblAnh.setIcon(new ImageIcon(
							new ImageIcon(hinhAnh).getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH)));
					btnChonAnh.setIcon(new ImageIcon(
							"src/main/java/img/icons8-update-left-rotation-24.png"));
				} else {
					lblAnh.setText("Chưa có ảnh");
					lblAnh.setIcon(null);
					btnChonAnh.setIcon(new ImageIcon("src/main/java/img/icons8-add-new-48.png"));
				}

				btnSua.setText("Sửa");
				btnSua.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Sua.png"));
				btnChonAnh.setEnabled(false);
				btnThem.setEnabled(true);
				btnThem.setBackground(color1);
				btnXoa.setEnabled(true);
				btnXoa.setBackground(color1);
				btnLuu.setEnabled(false);
				btnLuu.setBackground(Color.WHITE);
			}
		}
		if (o.equals(btnXoa)) {
			int hangDuocChon = tblDichVu.getSelectedRow();
			if (hangDuocChon > -1) {
				if (JOptionPane.showConfirmDialog(this, "Chắc chắn xóa dòng này?", "Thông báo",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String maDV = modelDV.getValueAt(hangDuocChon, 1).toString();
					try {
						if (daoDichVu.deleteDichVuTheoMa(maDV)) {
							lamMoiTrang();
							JOptionPane.showMessageDialog(this, "Đã xóa dịch vụ " + maDV, "Thành công!",
									JOptionPane.PLAIN_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(this, "Dịch vụ này có tồn tại trong hóa đơn, không thể xóa!",
									"Lỗi!", JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng trong bảng danh sách dịch vụ cần xóa!",
						"Cảnh báo!", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (o.equals(btnLuu)) {
			if (validData() == true) {
				String maDV = txtMaDV.getText().trim();
				String tenDV = txtTenDV.getText().trim();
				String tenDonVi = cmbDonVi.getSelectedItem().toString();
				String tenLoaiDV = cmbLoaiDV.getSelectedItem().toString();
				String gia = txtGiaBan.getText().trim();
				String soLuong = txtSoLuong.getText().trim();
				String ghiChu = txaGhiChu.getText().trim();
				DonVi donVi = null;
				try {
					donVi = daoDonVi.getDonViTheoTen(tenDonVi);
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				LoaiDichVu loaiDV = null;
				try {
					loaiDV = daoLoaiDV.getLoaiTheoTen(tenLoaiDV);
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
				if (btnThem.getText().equals("Hủy")) {
					try {
						if (daoDichVu.addDichVu(new DichVu(maDV, tenDV, Long.parseLong(gia), ghiChu,
								Integer.parseInt(soLuong), donVi, loaiDV, imageDichVu))) {
							// Chuyển cbDonVi thành txtDonVi
							bTxtDonVi.remove(cmbDonVi);
							bTxtDonVi.add(txtDonVi);

							// Chuyển cbLoaiDV thành txtLoaiDV
							bTxtLoaiDV.remove(cmbLoaiDV);
							bTxtLoaiDV.add(txtLoaiDV);

							lamMoiTrang();

							JOptionPane.showMessageDialog(this, "Đã thêm dịch vụ " + maDV, "Thành công",
									JOptionPane.PLAIN_MESSAGE);

							dongMoNhapLieu(false);

							btnThem.setText("Thêm");
							btnThem.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Them.png"));
							btnChonAnh.setEnabled(false);
							btnSua.setEnabled(true);
							btnSua.setBackground(color1);
							btnXoa.setEnabled(true);
							btnXoa.setBackground(color1);
							btnLuu.setEnabled(false);
							btnLuu.setBackground(Color.WHITE);

							kiemTraChonAnh = false;

							// Lưu thành công thì thêm lại sự kiện click chọn hàng load dữ liệ lên textfield
							tblDichVu.addMouseListener(this);
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
				if (btnSua.getText().equals("Hủy")) {
					try {
						if (daoDichVu.updateDichVu(new DichVu(maDV, tenDV, Long.parseLong(gia), ghiChu,
								Integer.parseInt(soLuong), donVi, loaiDV, imageDichVu), kiemTraChonAnh)) {
							// Chuyển cbDonVi thành txtDonVi
							bTxtDonVi.remove(cmbDonVi);
							bTxtDonVi.add(txtDonVi);

							// Chuyển cbLoaiDV thành txtLoaiDV
							bTxtLoaiDV.remove(cmbLoaiDV);
							bTxtLoaiDV.add(txtLoaiDV);

							lamMoiTrang();

							JOptionPane.showMessageDialog(this, "Đã sửa thông tin dịch vụ " + maDV, "Thành công",
									JOptionPane.PLAIN_MESSAGE);

							dongMoNhapLieu(false);

							btnSua.setText("Sửa");
							btnSua.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Sua.png"));
							btnChonAnh.setEnabled(false);
							btnThem.setEnabled(true);
							btnThem.setBackground(color1);
							btnXoa.setEnabled(true);
							btnXoa.setBackground(color1);
							btnLuu.setEnabled(false);
							btnLuu.setBackground(Color.WHITE);

							kiemTraChonAnh = false;
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}

				}
			}
		}
	}

	private void lamMoiTrang() throws RemoteException {
		loadData((ArrayList<DichVu>) daoDichVu.getAllDichVu());
		txtMaDV.setText("");
		xoaRongNhapLieu();
		lblAnh.setText("");
		lblAnh.setIcon(null);
		btnChonAnh.setIcon(new ImageIcon("src/main/java/img/icons8-add-new-48.png"));
	}

	private void dongMoNhapLieu(Boolean b) {
		txtMaDV.setEditable(false);
		txtDonVi.setEditable(false);
		txtLoaiDV.setEditable(false);
		txtTenDV.setEditable(b);
		txtGiaBan.setEditable(b);
		txtSoLuong.setEditable(b);
		txaGhiChu.setEditable(b);
	}

	private void xoaRongNhapLieu() {
		txtTenDV.setText("");
		txtDonVi.setText("");
		txtLoaiDV.setText("");
		txtGiaBan.setText("");
		txtSoLuong.setText("");
		txaGhiChu.setText("");
	}

	/**
	 * Tạo số cuối của mã mới không trùng với các mã đã có trong table. Lưu ý: Cần
	 * sắp xếp mã trên table theo thứ tự tăng dần.
	 * 
	 * @return Số cuối của mả mới.
	 */
	private int taoSoCuoiMaDV() throws RemoteException {
		ArrayList<DichVu> ds = (ArrayList<DichVu>) daoDichVu.getAllDichVu();
		int i = 0; // thứ tự hàng
		while (i < ds.size()) {
			// Lấy mã ở hàng thứ i trong table bỏ "DV" và chuyển sang kiểu int.
			// Tìm mã không khớp với hàng thứ i. Mã không khớp sẽ có số cuối mã
			// - i > 1
			if (Integer.parseInt(ds.get(i).getMaDV().substring(2)) == (i + 1))
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

	private void loadData(ArrayList<DichVu> dsDV) {
		DecimalFormat df = new DecimalFormat("#,##0đ");
		modelDV.setRowCount(0);
		for (int i = 0; i < dsDV.size(); i++) {
			String maDV = dsDV.get(i).getMaDV();
			String tenDV = dsDV.get(i).getTenDV();
			String tenDonVi = dsDV.get(i).getDonVi().getTenDonVi();
			String tenLoaiDichVu = dsDV.get(i).getLoai().getTenLoaiDV();
			String gia = df.format(dsDV.get(i).getGia());
			String soLuong = dsDV.get(i).getSoLuong() + "";
			String ghiChu = dsDV.get(i).getGhiChu();
			String row[] = { (i + 1) + "", maDV, tenDV, tenDonVi, tenLoaiDichVu, gia, soLuong, ghiChu };
			modelDV.addRow(row);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		DecimalFormat df = new DecimalFormat("#,##0đ");
		int hangDuocChon = tblDichVu.getSelectedRow();
		String maDV = modelDV.getValueAt(hangDuocChon, 1).toString();
		txtMaDV.setText(maDV);
		txtTenDV.setText(modelDV.getValueAt(hangDuocChon, 2).toString());
		txtDonVi.setText(modelDV.getValueAt(hangDuocChon, 3).toString());
		txtLoaiDV.setText(modelDV.getValueAt(hangDuocChon, 4).toString());

		// Bỏ định dạng tiền
		String gia = modelDV.getValueAt(hangDuocChon, 5).toString();
		try {
			txtGiaBan.setText(df.parse(gia).toString());
		} catch (ParseException e2) {
			e2.printStackTrace();
		}

		txtSoLuong.setText(modelDV.getValueAt(hangDuocChon, 6).toString());
		txaGhiChu.setText(modelDV.getValueAt(hangDuocChon, 7).toString());

		// load ảnh
		byte[] hinhAnh = new byte[0];
		try {
			hinhAnh = daoDichVu.getHinhAnhTheoMaDichVu(maDV);
		} catch (RemoteException ex) {
			throw new RuntimeException(ex);
		}
		if (hinhAnh != null) {
			lblAnh.setText("");
			lblAnh.setIcon(
					new ImageIcon(new ImageIcon(hinhAnh).getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH)));
			btnChonAnh.setIcon(
					new ImageIcon("src/main/java/img/icons8-update-left-rotation-24.png"));
		} else {
			lblAnh.setText("Chưa có ảnh");
			lblAnh.setIcon(null);
			btnChonAnh.setIcon(new ImageIcon("src/main/java/img/icons8-add-new-48.png"));
		}
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private boolean validData() {
		String tenDV = txtTenDV.getText().trim();
		String gia = txtGiaBan.getText().trim();
		String soLuong = txtSoLuong.getText().trim();
		String ghiChu = txaGhiChu.getText().trim();
		if (tenDV.length() == 0) {
			showMessage(txtTenDV, "Nhập tên dịch vụ!");
			return false;
		}
		if (tenDV.length() > 30) {
			showMessage(txtTenDV, "Tên dịch vụ không được vượt quá 30 ký tự!");
			return false;
		}
		if (!tenDV.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]+\\s?)+$")) {
			showMessage(txtTenDV,
					"Tên dịch vụ bao gồm chữ cái (có thể viết tiếng việt), chữ số, có thể có 1 khoảng trắng ngăn cách giữa 2 từ và không bao gồm ký tự đặc biệt!");
			return false;
		}

		if (gia.length() == 0) {
			showMessage(txtGiaBan, "Nhập giá bán!");
			return false;
		}
		if (!gia.matches("^([1-9]\\d{3,})$")) {
			showMessage(txtGiaBan, "Giá bán là số nguyên >= 1000 và không được bắt đầu bằng 0!");
			return false;
		}

		if (soLuong.length() == 0) {
			showMessage(txtSoLuong, "Nhập số lượng!");
			return false;
		}
		if (!soLuong.matches("^([1-9]\\d*)$")) {
			showMessage(txtSoLuong, "Số lượng là số nguyên > 0 và không được bắt đầu bằng 0!");
			return false;
		}
		if (ghiChu.length() > 50) {
			txaGhiChu.setText("");
			txaGhiChu.requestFocus();
			JOptionPane.showMessageDialog(this, "Ghi chú không được vượt quá 50 ký tự");
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
