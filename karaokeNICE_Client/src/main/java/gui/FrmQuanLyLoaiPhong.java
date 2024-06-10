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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

//import connectDB.ConnectDB;
//import dao.DAO_LoaiPhong;
import dao.DaoLoaiPhong;
import entity.LoaiPhong;

public class FrmQuanLyLoaiPhong extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final String URL = "rmi://26.173.247.75:7878/";
	DaoLoaiPhong daoLoaiPhong = (DaoLoaiPhong) Naming.lookup(URL + "daoLoaiPhong");

//	private DAO_LoaiPhong daoLoaiPhong = new DAO_LoaiPhong();

	private static final long serialVersionUID = 1L;
	public static JPanel pnlBorder;
	private JTextField txtMaLoaiPhong;
	private JTextField txtTenLoaiPhong;
	private JTextField txtGia;
	private JTextArea txaMieuTa;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnLuu;
	private DefaultTableModel modelLoaiPhong;
	private JTable tblLoaiPhong;

	private Color color1;
	private Color color2;

	public FrmQuanLyLoaiPhong() throws MalformedURLException, NotBoundException, RemoteException {
		this.setTitle("Quản lý loại dịch vụ");
		this.setSize(1920, 1040);
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
		pnlLeftContent.setBorder(new EmptyBorder(50, 50, 50, 50));
		pnlLeftContent.setBackground(Color.WHITE);

		Box bThongTinLoaiPhong = Box.createVerticalBox();
		Box bLblMaLoaiPhong = Box.createHorizontalBox();
		Box bTxtMaLoaiPhong = Box.createHorizontalBox();
		Box bLblTenLoaiPhong = Box.createHorizontalBox();
		Box bTxtTenLoaiPhong = Box.createHorizontalBox();
		Box bLblGia = Box.createHorizontalBox();
		Box bTxtGia = Box.createHorizontalBox();
		Box bLblMieuTa = Box.createHorizontalBox();
		Box bTxaMieuTa = Box.createHorizontalBox();

		JLabel lblMaLoaiPhong = new JLabel("Mã loại phòng:");
		lblMaLoaiPhong.setFont(font2);
		JLabel lblTenLoaiPhong = new JLabel("Tên loại phòng:");
		lblTenLoaiPhong.setFont(font2);
		JLabel lblGia = new JLabel("Giá phòng:");
		lblGia.setFont(font2);
		JLabel lblMieuTa = new JLabel("Miêu tả:");
		lblMieuTa.setFont(font2);

		bLblMaLoaiPhong.add(lblMaLoaiPhong);
		bLblMaLoaiPhong.add(Box.createVerticalStrut(0));
		bLblTenLoaiPhong.add(lblTenLoaiPhong);
		bLblTenLoaiPhong.add(Box.createVerticalStrut(0));
		bLblGia.add(lblGia);
		bLblGia.add(Box.createVerticalStrut(0));
		bLblMieuTa.add(lblMieuTa);
		bLblMieuTa.add(Box.createVerticalStrut(0));

		txtMaLoaiPhong = new JTextField(17);
		txtMaLoaiPhong.setFont(font2);
		txtMaLoaiPhong.setBackground(color2);
		txtTenLoaiPhong = new JTextField(17);
		txtTenLoaiPhong.setFont(font2);
		txtTenLoaiPhong.setBackground(color2);
		txtGia = new JTextField(17);
		txtGia.setFont(font2);
		txtGia.setBackground(color2);
		txaMieuTa = new JTextArea(3, 17);
		txaMieuTa.setLineWrap(true);
		txaMieuTa.setFont(font2);
		txaMieuTa.setBackground(color2);

		bTxtMaLoaiPhong.add(txtMaLoaiPhong);
		bTxtTenLoaiPhong.add(txtTenLoaiPhong);
		bTxtGia.add(txtGia);
		bTxaMieuTa.add(txaMieuTa);

		bThongTinLoaiPhong.add(bLblMaLoaiPhong);
		bThongTinLoaiPhong.add(Box.createVerticalStrut(3));
		bThongTinLoaiPhong.add(bTxtMaLoaiPhong);
		bThongTinLoaiPhong.add(Box.createVerticalStrut(20));
		bThongTinLoaiPhong.add(bLblTenLoaiPhong);
		bThongTinLoaiPhong.add(Box.createVerticalStrut(3));
		bThongTinLoaiPhong.add(bTxtTenLoaiPhong);
		bThongTinLoaiPhong.add(Box.createVerticalStrut(20));
		bThongTinLoaiPhong.add(bLblGia);
		bThongTinLoaiPhong.add(Box.createVerticalStrut(3));
		bThongTinLoaiPhong.add(bTxtGia);
		bThongTinLoaiPhong.add(Box.createVerticalStrut(20));
		bThongTinLoaiPhong.add(bLblMieuTa);
		bThongTinLoaiPhong.add(Box.createVerticalStrut(3));
		bThongTinLoaiPhong.add(bTxaMieuTa);
		bThongTinLoaiPhong.add(Box.createVerticalStrut(20));

		pnlLeftContent.add(bThongTinLoaiPhong);

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

		JLabel lblTittle = new JLabel("QUẢN LÝ LOẠI PHÒNG HÁT");
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

		modelLoaiPhong = new DefaultTableModel();
		modelLoaiPhong.addColumn("STT");
		modelLoaiPhong.addColumn("Mã loại phòng");
		modelLoaiPhong.addColumn("Tên loại phòng");
		modelLoaiPhong.addColumn("Giá phòng");
		modelLoaiPhong.addColumn("Miêu tả");
		tblLoaiPhong = new JTable(modelLoaiPhong);
		JScrollPane jScrollPane = new JScrollPane(tblLoaiPhong, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JTableHeader tbhLoaiPhong = tblLoaiPhong.getTableHeader();
		tbhLoaiPhong.setFont(font2);
		tblLoaiPhong.setFont(font2);
		tblLoaiPhong.setRowHeight(35);
		tblLoaiPhong.setDefaultEditor(Object.class, null);
		pnRightCenterContent.add(jScrollPane, BorderLayout.CENTER);

		dieuChinhDoRongCotTable(tblLoaiPhong.getColumn("Mã loại phòng"), 100);
		dieuChinhDoRongCotTable(tblLoaiPhong.getColumn("Tên loại phòng"), 200);
		dieuChinhDoRongCotTable(tblLoaiPhong.getColumn("Giá phòng"), 200);
		dieuChinhDoRongCotTable(tblLoaiPhong.getColumn("Miêu tả"), 500);

		((DefaultTableCellRenderer) tblLoaiPhong.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(JLabel.CENTER);

		canChinhCotTable(tblLoaiPhong.getColumn("STT"), DefaultTableCellRenderer.CENTER);
		canChinhCotTable(tblLoaiPhong.getColumn("Mã loại phòng"), DefaultTableCellRenderer.CENTER);
		canChinhCotTable(tblLoaiPhong.getColumn("Giá phòng"), DefaultTableCellRenderer.CENTER);

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

		loadData(daoLoaiPhong.getALLLoaiPhong());

		tblLoaiPhong.addMouseListener(this);

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
				int soHang = modelLoaiPhong.getRowCount();
				// Mã có dạng DVXXX, XXX: từ 001 -> 999
				if (soHang < 999) {
					dongMoNhapLieu(true);

					btnThem.setText("Hủy");
					btnThem.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Huy.png"));
					btnSua.setEnabled(false);
					btnSua.setBackground(Color.WHITE);
					btnXoa.setEnabled(false);
					btnXoa.setBackground(Color.WHITE);
					btnLuu.setEnabled(true);
					btnLuu.setBackground(color1);

					xoaRongNhapLieu();

					// Không cho load dữ liệu table vào textfield
					tblLoaiPhong.removeMouseListener(this);

					// Lấy mã tự động
					int soCuoi = taoSoCuoiMaLoaiDV();
					// TH1: số cuối tìm được có 1 chữ số
					if (soCuoi < 10) {
						txtMaLoaiPhong.setText("LP00" + soCuoi);
					} else {
						// TH2: Số cuối tìm được có 2 chữ số
						if (soCuoi < 100) {
							txtMaLoaiPhong.setText("LP0" + soCuoi);
						} else {
							// TH3: Số cuối tìm được có 3 chữ số
							txtMaLoaiPhong.setText("LP" + soCuoi);
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Dữ liệu đầy, không thể thêm!", "Lỗi!",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				// Khi nhấn hủy thì thêm lại sự kiện click chọn hàng load dữ liệ lên textfield
				tblLoaiPhong.addMouseListener(this);

				dongMoNhapLieu(false);

				btnThem.setText("Thêm");
				btnThem.setIcon(new ImageIcon("src/main/java/img/DanhMuc_Them.png"));
				btnSua.setEnabled(true);
				btnSua.setBackground(color1);
				btnXoa.setEnabled(true);
				btnXoa.setBackground(color1);
				btnLuu.setEnabled(false);
				btnLuu.setBackground(Color.WHITE);

				// Xóa chọn hàng trong table
				tblLoaiPhong.getSelectionModel().clearSelection();

				txtMaLoaiPhong.setText("");
				xoaRongNhapLieu();
			}
		}
		if (o.equals(btnSua)) {
			if (btnSua.getText().equals("Sửa")) {
				int hangDuocChon = tblLoaiPhong.getSelectedRow();
				if (hangDuocChon > -1) {
					dongMoNhapLieu(true);

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
							"Vui lòng chọn 1 hàng trong bảng danh sách loại phòng trước khi sửa!", "Cảnh báo!",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				dongMoNhapLieu(false);

				// Load lại dữ liệu ban đầu của hàng được chọn lại textfield cho trường hợp
				// người dùng sửa nhưng ko lưu mà nhấn hủy.
				int hangDuocChon = tblLoaiPhong.getSelectedRow();
				txtMaLoaiPhong.setText(modelLoaiPhong.getValueAt(hangDuocChon, 1).toString());
				txtTenLoaiPhong.setText(modelLoaiPhong.getValueAt(hangDuocChon, 2).toString());
				txtGia.setText(modelLoaiPhong.getValueAt(hangDuocChon, 3).toString());
				txaMieuTa.setText(modelLoaiPhong.getValueAt(hangDuocChon, 4).toString());

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
			int hangDuocChon = tblLoaiPhong.getSelectedRow();
			if (hangDuocChon > -1) {
				if (JOptionPane.showConfirmDialog(this, "Chắc chắn xóa dòng này?", "Thông báo!",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String maLoaiPhong = modelLoaiPhong.getValueAt(hangDuocChon, 1).toString();
					try {
						if (daoLoaiPhong.deleteLoaiPhong(maLoaiPhong)) {
							lamMoiTrang();
							JOptionPane.showMessageDialog(this, "Đã xóa loại phòng " + maLoaiPhong, "Thành công",
									JOptionPane.PLAIN_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(this, "Có phòng thuộc loại này, không thể xóa!", "Lỗi!",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng trong bảng danh sách loại phòng cần xóa!",
						"Cảnh báo!", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (o.equals(btnLuu)) {
			if (validData() == true) {
				String maLoaiPhong = txtMaLoaiPhong.getText().trim();
				String tenLoaiPhong = txtTenLoaiPhong.getText().trim();
				double gia = Double.parseDouble(txtGia.getText().trim());
				String mieuTa = txaMieuTa.getText().trim();
				if (btnThem.getText().equals("Hủy")) {
					try {
						if (daoLoaiPhong.addLoaiPhong(new LoaiPhong(maLoaiPhong, tenLoaiPhong, mieuTa, gia))) {
							lamMoiTrang();

							JOptionPane.showMessageDialog(this, "Đã thêm loại phòng " + maLoaiPhong, "Thành công",
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
							tblLoaiPhong.addMouseListener(this);

						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
				if (btnSua.getText().equals("Hủy")) {
					try {
						if (daoLoaiPhong.updateLoaiPhong(new LoaiPhong(maLoaiPhong, tenLoaiPhong, mieuTa, gia))) {
							lamMoiTrang();

							JOptionPane.showMessageDialog(this, "Đã sửa thông tin loại phòng " + maLoaiPhong, "Thành công",
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
		loadData(daoLoaiPhong.getALLLoaiPhong());
		txtMaLoaiPhong.setText("");
		xoaRongNhapLieu();
	}

	private void dongMoNhapLieu(Boolean b) {
		txtMaLoaiPhong.setEditable(false);
		txtTenLoaiPhong.setEditable(b);
		txtGia.setEditable(b);
		txaMieuTa.setEditable(b);
	}

	private void xoaRongNhapLieu() {
		txtTenLoaiPhong.setText("");
		txtGia.setText("");
		txaMieuTa.setText("");
	}

	/**
	 * Tạo số cuối của mã mới không trùng với các mã đã có trong table. Lưu ý: Cần
	 * sắp xếp mã trên table theo thứ tự tăng dần.
	 * 
	 * @return Số cuối của mả mới.
	 */
	private int taoSoCuoiMaLoaiDV() {
		int i = 0; // thứ tự hàng
		while (i < modelLoaiPhong.getRowCount()) {
			// Lấy mã ở hàng thứ i trong table bỏ "LP" và chuyển sang kiểu int.
			// Tìm mã không khớp với hàng thứ i. Mã không khớp sẽ có số cuối mã
			// - i > 1
			if (Integer.parseInt(modelLoaiPhong.getValueAt(i, 1).toString().substring(2)) == (i + 1))
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

	private void loadData(ArrayList<LoaiPhong> dsLoaiPhong) {
		DecimalFormat df = new DecimalFormat("#,##0đ");
		modelLoaiPhong.setRowCount(0);
		for (int i = 0; i < dsLoaiPhong.size(); i++) {
			String maLoaiPhong = dsLoaiPhong.get(i).getMaLoaiPhong();
			String tenLoaiPhong = dsLoaiPhong.get(i).getTenLoaiPhong();
			String gia = df.format(dsLoaiPhong.get(i).getGia());
			String mieuTa = dsLoaiPhong.get(i).getMieuTa();
			String row[] = { (i + 1) + "", maLoaiPhong, tenLoaiPhong, gia, mieuTa };
			modelLoaiPhong.addRow(row);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		DecimalFormat df = new DecimalFormat("#,##0đ");
		int hangDuocChon = tblLoaiPhong.getSelectedRow();
		txtMaLoaiPhong.setText(modelLoaiPhong.getValueAt(hangDuocChon, 1).toString());
		txtTenLoaiPhong.setText(modelLoaiPhong.getValueAt(hangDuocChon, 2).toString());

		// Bỏ định dạng tiền
		String gia = modelLoaiPhong.getValueAt(hangDuocChon, 3).toString();
		try {
			txtGia.setText(df.parse(gia).toString());
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
		txaMieuTa.setText(modelLoaiPhong.getValueAt(hangDuocChon, 4).toString());
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
		String tenLoaiPhong = txtTenLoaiPhong.getText().trim();
		String gia = txtGia.getText().trim();
		String mieuTa = txaMieuTa.getText().trim();
		if (tenLoaiPhong.length() == 0) {
			showMessage(txtTenLoaiPhong, "Nhập tên loại phòng!");
			return false;
		}
		if (tenLoaiPhong.length() > 15) {
			showMessage(txtTenLoaiPhong, "Tên loại phòng không được vượt quá 15 ký tự!");
			return false;
		}
		if (!tenLoaiPhong.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ]+\\s?)+$")) {
			showMessage(txtTenLoaiPhong,
					"Tên loại phòng bao gồm chữ cái (có thể viết tiếng việt), có thể có 1 khoảng trắng ngăn cách giữa 2 từ và không bao gồm chữ số và ký tự đặc biệt!");
			return false;
		}
		if (gia.length() == 0) {
			showMessage(txtGia, "Nhập giá phòng!");
			return false;
		}
		if (!gia.matches("^([1-9]\\d{5,})$")) {
			showMessage(txtGia, "Giá phòng là số nguyên >= 100000 và không được bắt đầu bằng 0!");
			return false;
		}
		if (mieuTa.length() > 50) {
			txaMieuTa.setText("");
			txaMieuTa.requestFocus();
			JOptionPane.showMessageDialog(this, "Miêu tả không được vượt quá 50 ký tự!", "Error",
					JOptionPane.ERROR_MESSAGE);
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
