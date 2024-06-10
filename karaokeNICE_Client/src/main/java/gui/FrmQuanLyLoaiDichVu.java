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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

//import connectDB.ConnectDB;
//import dao.DAO_LoaiDichVu;
import dao.DaoLoaiDichVu;
import entity.LoaiDichVu;

public class FrmQuanLyLoaiDichVu extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final String URL = "rmi://26.173.247.75:7878/";
	DaoLoaiDichVu daoLoaiDV = (DaoLoaiDichVu) Naming.lookup(URL + "daoLoaiDichVu");

//	private DAO_LoaiDichVu daoLoaiDV = new DAO_LoaiDichVu();
	private static final long serialVersionUID = 1L;
	public static JPanel pnlBorder;
	private JTextField txtMaLoaiDV;
	private JTextField txtTenLoaiDV;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnLuu;
	private DefaultTableModel modelLoaiDV;
	private JTable tblLoaiDichVu;

	private Color color1;
	private Color color2;

	public FrmQuanLyLoaiDichVu() throws MalformedURLException, NotBoundException, RemoteException {
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

		Box bThongTinLoaiDV = Box.createVerticalBox();
		Box bLblMaLoaiDV = Box.createHorizontalBox();
		Box bTxtMaLoaiDV = Box.createHorizontalBox();
		Box bLblTenLoaiDV = Box.createHorizontalBox();
		Box bTxtTenLoaiDV = Box.createHorizontalBox();

		JLabel lblMaLoaiDV = new JLabel("Mã loại dịch vụ:");
		lblMaLoaiDV.setFont(font2);
		JLabel lblTenDV = new JLabel("Tên loại dịch vụ:");
		lblTenDV.setFont(font2);

		bLblMaLoaiDV.add(lblMaLoaiDV);
		bLblMaLoaiDV.add(Box.createVerticalStrut(0));
		bLblTenLoaiDV.add(lblTenDV);
		bLblTenLoaiDV.add(Box.createVerticalStrut(0));

		txtMaLoaiDV = new JTextField(17);
		txtMaLoaiDV.setFont(font2);
		txtMaLoaiDV.setBackground(color2);
		txtTenLoaiDV = new JTextField(17);
		txtTenLoaiDV.setFont(font2);
		txtTenLoaiDV.setBackground(color2);

		bTxtMaLoaiDV.add(txtMaLoaiDV);
		bTxtTenLoaiDV.add(txtTenLoaiDV);

		bThongTinLoaiDV.add(bLblMaLoaiDV);
		bThongTinLoaiDV.add(Box.createVerticalStrut(3));
		bThongTinLoaiDV.add(bTxtMaLoaiDV);
		bThongTinLoaiDV.add(Box.createVerticalStrut(20));
		bThongTinLoaiDV.add(bLblTenLoaiDV);
		bThongTinLoaiDV.add(Box.createVerticalStrut(3));
		bThongTinLoaiDV.add(bTxtTenLoaiDV);
		bThongTinLoaiDV.add(Box.createVerticalStrut(20));

		pnlLeftContent.add(bThongTinLoaiDV);

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

		JLabel lblTittle = new JLabel("QUẢN LÝ LOẠI DỊCH VỤ");
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

		modelLoaiDV = new DefaultTableModel();
		modelLoaiDV.addColumn("STT");
		modelLoaiDV.addColumn("Mã loại dịch vụ");
		modelLoaiDV.addColumn("Tên loại dịch vụ");
		tblLoaiDichVu = new JTable(modelLoaiDV);
		JScrollPane jScrollPane = new JScrollPane(tblLoaiDichVu, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JTableHeader tbhLoaiDV = tblLoaiDichVu.getTableHeader();
		tbhLoaiDV.setFont(font2);
		tblLoaiDichVu.setFont(font2);
		tblLoaiDichVu.setRowHeight(35);
		tblLoaiDichVu.setDefaultEditor(Object.class, null);
		pnRightCenterContent.add(jScrollPane, BorderLayout.CENTER);

		dieuChinhDoRongCotTable(tblLoaiDichVu.getColumn("Mã loại dịch vụ"), 500);
		dieuChinhDoRongCotTable(tblLoaiDichVu.getColumn("Tên loại dịch vụ"), 500);

		((DefaultTableCellRenderer) tblLoaiDichVu.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(JLabel.CENTER);

		canChinhCotTable(tblLoaiDichVu.getColumn("STT"), DefaultTableCellRenderer.CENTER);
		canChinhCotTable(tblLoaiDichVu.getColumn("Mã loại dịch vụ"), DefaultTableCellRenderer.CENTER);
		canChinhCotTable(tblLoaiDichVu.getColumn("Tên loại dịch vụ"), DefaultTableCellRenderer.CENTER);

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

		loadData(daoLoaiDV.getAllDichVu());

		tblLoaiDichVu.addMouseListener(this);

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
				int soHang = modelLoaiDV.getRowCount();
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

					txtTenLoaiDV.setText("");

					// Không cho load dữ liệu table vào textfield
					tblLoaiDichVu.removeMouseListener(this);

					// Lấy mã tự động
					int soCuoi = taoSoCuoiMaLoaiDV();
					// TH1: số cuối tìm được có 1 chữ số
					if (soCuoi < 10) {
						txtMaLoaiDV.setText("LDV00" + soCuoi);
					} else {
						// TH2: Số cuối tìm được có 2 chữ số
						if (soCuoi < 100) {
							txtMaLoaiDV.setText("LDV0" + soCuoi);
						} else {
							// TH3: Số cuối tìm được có 3 chữ số
							txtMaLoaiDV.setText("LDV" + soCuoi);
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Dữ liệu đầy, không thể thêm!", "Lỗi!",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				// Khi nhấn hủy thì thêm lại sự kiện click chọn hàng load dữ liệ lên textfield
				tblLoaiDichVu.addMouseListener(this);

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
				tblLoaiDichVu.getSelectionModel().clearSelection();

				txtMaLoaiDV.setText("");
				txtTenLoaiDV.setText("");
			}
		}
		if (o.equals(btnSua)) {
			if (btnSua.getText().equals("Sửa")) {
				int hangDuocChon = tblLoaiDichVu.getSelectedRow();
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
							"Vui lòng chọn 1 hàng trong bảng danh sách loại dịch vụ trước khi sửa!", "Cảnh báo!",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				dongMoNhapLieu(false);

				// Load lại dữ liệu ban đầu của hàng được chọn lại textfield cho trường hợp
				// người dùng sửa nhưng ko lưu mà nhấn hủy.
				int hangDuocChon = tblLoaiDichVu.getSelectedRow();
				txtMaLoaiDV.setText(modelLoaiDV.getValueAt(hangDuocChon, 1).toString());
				txtTenLoaiDV.setText(modelLoaiDV.getValueAt(hangDuocChon, 2).toString());

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
			int hangDuocChon = tblLoaiDichVu.getSelectedRow();
			if (hangDuocChon > -1) {
				if (JOptionPane.showConfirmDialog(this, "Chắc chắn xóa dòng này?", "Thông báo",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String maLoaiDV = modelLoaiDV.getValueAt(hangDuocChon, 1).toString();
					try {
						if (daoLoaiDV.deleteLoaiDichVu(maLoaiDV)) {
							lamMoiTrang();
							JOptionPane.showMessageDialog(this, "Đã xóa loại dịch vụ " + maLoaiDV, "Thành công",
									JOptionPane.PLAIN_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(this, "Có dịch vụ thuộc loại này, không thể xóa!", "Lỗi!",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng trong bảng danh sách loại dịch vụ cần xóa!",
						"Cảnh báo!", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (o.equals(btnLuu)) {
			if (validData() == true) {
				String maLoaiDV = txtMaLoaiDV.getText().trim();
				String tenLoaiDV = txtTenLoaiDV.getText().trim();
				if (btnThem.getText().equals("Hủy")) {
					try {
						if (daoLoaiDV.addLoaiDichVu(new LoaiDichVu(maLoaiDV, tenLoaiDV))) {
							lamMoiTrang();

							JOptionPane.showMessageDialog(this, "Đã thêm loại dịch vụ " + maLoaiDV, "Thành công",
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
							tblLoaiDichVu.addMouseListener(this);

						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
				if (btnSua.getText().equals("Hủy")) {
					try {
						if (daoLoaiDV.updateLoaiDichVu(new LoaiDichVu(maLoaiDV, tenLoaiDV))) {
							lamMoiTrang();

							JOptionPane.showMessageDialog(this, "Đã sửa thông tin loại dịch vụ " + maLoaiDV, "Thành công",
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

	private void lamMoiTrang() {
		try {
			loadData(daoLoaiDV.getAllDichVu());
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
		txtMaLoaiDV.setText("");
		txtTenLoaiDV.setText("");
	}

	private void dongMoNhapLieu(Boolean b) {
		txtMaLoaiDV.setEditable(false);
		txtTenLoaiDV.setEditable(b);
	}

	/**
	 * Tạo số cuối của mã mới không trùng với các mã đã có trong table. Lưu ý: Cần
	 * sắp xếp mã trên table theo thứ tự tăng dần.
	 * 
	 * @return Số cuối của mả mới.
	 */
	private int taoSoCuoiMaLoaiDV() {
		int i = 0; // thứ tự hàng
		while (i < modelLoaiDV.getRowCount()) {
			// Lấy mã ở hàng thứ i trong table bỏ "LDV" và chuyển sang kiểu int.
			// Tìm mã không khớp với hàng thứ i. Mã không khớp sẽ có số cuối mã
			// - i > 1
			if (Integer.parseInt(modelLoaiDV.getValueAt(i, 1).toString().substring(3)) == (i + 1))
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

	private void loadData(ArrayList<LoaiDichVu> dsLoaiDV) {
		modelLoaiDV.setRowCount(0);
		for (int i = 0; i < dsLoaiDV.size(); i++) {
			String maLoaiDV = dsLoaiDV.get(i).getMaLoaiDV();
			String tenLoaiDV = dsLoaiDV.get(i).getTenLoaiDV();
			String row[] = { (i + 1) + "", maLoaiDV, tenLoaiDV };
			modelLoaiDV.addRow(row);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int hangDuocChon = tblLoaiDichVu.getSelectedRow();
		txtMaLoaiDV.setText(modelLoaiDV.getValueAt(hangDuocChon, 1).toString());
		txtTenLoaiDV.setText(modelLoaiDV.getValueAt(hangDuocChon, 2).toString());
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
		String tenLoaiDV = txtTenLoaiDV.getText().trim();
		if (tenLoaiDV.length() == 0) {
			showMessage(txtTenLoaiDV, "Nhập tên loại dịch vụ!");
			return false;
		}
		if (tenLoaiDV.length() > 20) {
			showMessage(txtTenLoaiDV, "Tên loại dịch vụ không được vượt quá 20 ký tự!");
			return false;
		}
		if (!tenLoaiDV.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ]+\\s?)+$")) {
			showMessage(txtTenLoaiDV,
					"Tên loại dịch vụ bao gồm chữ cái (có thể viết tiếng việt), có thể có 1 khoảng trắng ngăn cách giữa 2 từ và không bao gồm chữ số và ký tự đặc biệt!");
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
