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
//import dao.DAO_DonVi;
import dao.DaoDonVi;
import entity.DonVi;

public class FrmQuanLyDonVi extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final String URL = "rmi://26.173.247.75:7878/";
	DaoDonVi daoDonVi = (DaoDonVi) Naming.lookup(URL + "daoDonVi");

//	private DAO_DonVi daoDonVi = new DAO_DonVi();
	private static final long serialVersionUID = 1L;
	public static JPanel pnlBorder;
	private JTextField txtMaDonVi;
	private JTextField txtTenDonVi;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnLuu;
	private DefaultTableModel modelDonVi;
	private JTable tblDonVi;

	private Color color1;
	private Color color2;

	public FrmQuanLyDonVi() throws MalformedURLException, NotBoundException, RemoteException {
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

		Box bThongTinDonVi = Box.createVerticalBox();
		Box bLblMaDonVi = Box.createHorizontalBox();
		Box bTxtMaDonVi = Box.createHorizontalBox();
		Box bLblTenDonVi = Box.createHorizontalBox();
		Box bTxtTenDonVi = Box.createHorizontalBox();

		JLabel lblMaDonVi = new JLabel("Mã đơn vị:");
		lblMaDonVi.setFont(font2);
		JLabel lblTenDonVi = new JLabel("Tên đơn vị:");
		lblTenDonVi.setFont(font2);

		bLblMaDonVi.add(lblMaDonVi);
		bLblMaDonVi.add(Box.createVerticalStrut(0));
		bLblTenDonVi.add(lblTenDonVi);
		bLblTenDonVi.add(Box.createVerticalStrut(0));

		txtMaDonVi = new JTextField(17);
		txtMaDonVi.setFont(font2);
		txtMaDonVi.setBackground(color2);
		txtTenDonVi = new JTextField(17);
		txtTenDonVi.setFont(font2);
		txtTenDonVi.setBackground(color2);

		bTxtMaDonVi.add(txtMaDonVi);
		bTxtTenDonVi.add(txtTenDonVi);

		bThongTinDonVi.add(bLblMaDonVi);
		bThongTinDonVi.add(Box.createVerticalStrut(3));
		bThongTinDonVi.add(bTxtMaDonVi);
		bThongTinDonVi.add(Box.createVerticalStrut(20));
		bThongTinDonVi.add(bLblTenDonVi);
		bThongTinDonVi.add(Box.createVerticalStrut(3));
		bThongTinDonVi.add(bTxtTenDonVi);
		bThongTinDonVi.add(Box.createVerticalStrut(20));

		pnlLeftContent.add(bThongTinDonVi);

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

		JLabel lblTittle = new JLabel("QUẢN LÝ ĐƠN VỊ");
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

		modelDonVi = new DefaultTableModel();
		modelDonVi.addColumn("STT");
		modelDonVi.addColumn("Mã đơn vị");
		modelDonVi.addColumn("Tên đơn vị");
		tblDonVi = new JTable(modelDonVi);
		JScrollPane jScrollPane = new JScrollPane(tblDonVi, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JTableHeader tbhDonVi = tblDonVi.getTableHeader();
		tbhDonVi.setFont(font2);
		tblDonVi.setFont(font2);
		tblDonVi.setRowHeight(35);
		tblDonVi.setDefaultEditor(Object.class, null);
		pnRightCenterContent.add(jScrollPane, BorderLayout.CENTER);

		dieuChinhDoRongCotTable(tblDonVi.getColumn("Mã đơn vị"), 500);
		dieuChinhDoRongCotTable(tblDonVi.getColumn("Tên đơn vị"), 500);

		((DefaultTableCellRenderer) tblDonVi.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(JLabel.CENTER);

		canChinhCotTable(tblDonVi.getColumn("STT"), DefaultTableCellRenderer.CENTER);
		canChinhCotTable(tblDonVi.getColumn("Mã đơn vị"), DefaultTableCellRenderer.CENTER);
		canChinhCotTable(tblDonVi.getColumn("Tên đơn vị"), DefaultTableCellRenderer.CENTER);

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

		loadData((ArrayList<DonVi>) daoDonVi.getALLDonVi());

		tblDonVi.addMouseListener(this);

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
				int soHang = modelDonVi.getRowCount();
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

					txtTenDonVi.setText("");

					// Không cho load dữ liệu table vào textfield
					tblDonVi.removeMouseListener(this);

					// Lấy mã tự động
					int soCuoi = taoSoCuoiMaLoaiDV();
					// TH1: số cuối tìm được có 1 chữ số
					if (soCuoi < 10) {
						txtMaDonVi.setText("MDV00" + soCuoi);
					} else {
						// TH2: Số cuối tìm được có 2 chữ số
						if (soCuoi < 100) {
							txtMaDonVi.setText("MDV0" + soCuoi);
						} else {
							// TH3: Số cuối tìm được có 3 chữ số
							txtMaDonVi.setText("MDV" + soCuoi);
						}
					}
				} else {
					JOptionPane.showMessageDialog(this, "Dữ liệu đầy, không thể thêm!", "Lỗi!",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				// Khi nhấn hủy thì thêm lại sự kiện click chọn hàng load dữ liệ lên textfield
				tblDonVi.addMouseListener(this);

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
				tblDonVi.getSelectionModel().clearSelection();

				txtMaDonVi.setText("");
				txtTenDonVi.setText("");
			}
		}
		if (o.equals(btnSua)) {
			if (btnSua.getText().equals("Sửa")) {
				int hangDuocChon = tblDonVi.getSelectedRow();
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
							"Vui lòng chọn 1 hàng trong bảng danh sách đơn vị trước khi sửa!", "Cảnh báo!",
							JOptionPane.WARNING_MESSAGE);
				}
			} else {
				dongMoNhapLieu(false);

				// Load lại dữ liệu ban đầu của hàng được chọn lại textfield cho trường hợp
				// người dùng sửa nhưng ko lưu mà nhấn hủy.
				int hangDuocChon = tblDonVi.getSelectedRow();
				txtMaDonVi.setText(modelDonVi.getValueAt(hangDuocChon, 1).toString());
				txtTenDonVi.setText(modelDonVi.getValueAt(hangDuocChon, 2).toString());

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
			int hangDuocChon = tblDonVi.getSelectedRow();
			if (hangDuocChon > -1) {
				if (JOptionPane.showConfirmDialog(this, "Chắc chắn xóa dòng này?", "Thông báo",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					String maDonVi = modelDonVi.getValueAt(hangDuocChon, 1).toString();
					try {
						if (daoDonVi.deleteDonVi(maDonVi)) {
							try {
								lamMoiTrang();
							} catch (RemoteException ex) {
								throw new RuntimeException(ex);
							}
							JOptionPane.showMessageDialog(this, "Đã xóa đơn vị " + maDonVi, "Thành công",
									JOptionPane.PLAIN_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(this, "Có dịch vụ thuộc đơn vị này, không thể xóa!", "Lỗi!",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hàng trong bảng danh sách đơn vị cần xóa!",
						"Cảnh báo!", JOptionPane.WARNING_MESSAGE);
			}
		}
		if (o.equals(btnLuu)) {
			if (validData() == true) {
				String maDonVi = txtMaDonVi.getText().trim();
				String tenDonVi = txtTenDonVi.getText().trim();
				if (btnThem.getText().equals("Hủy")) {
					try {
						if (daoDonVi.addDonVi(new DonVi(maDonVi, tenDonVi))) {
							try {
								lamMoiTrang();
							} catch (RemoteException ex) {
								throw new RuntimeException(ex);
							}

							JOptionPane.showMessageDialog(this, "Đã thêm đơn vị " + maDonVi, "Thành công",
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
							tblDonVi.addMouseListener(this);

						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
				if (btnSua.getText().equals("Hủy")) {
					try {
						if (daoDonVi.updateDonVi(new DonVi(maDonVi, tenDonVi))) {
							lamMoiTrang();

							JOptionPane.showMessageDialog(this, "Đã sửa thông tin đơn vị " + maDonVi, "Thành công",
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
		loadData((ArrayList<DonVi>) daoDonVi.getALLDonVi());
		txtMaDonVi.setText("");
		txtTenDonVi.setText("");
	}

	private void dongMoNhapLieu(Boolean b) {
		txtMaDonVi.setEditable(false);
		txtTenDonVi.setEditable(b);
	}

	/**
	 * Tạo số cuối của mã mới không trùng với các mã đã có trong table. Lưu ý: Cần
	 * sắp xếp mã trên table theo thứ tự tăng dần.
	 * 
	 * @return Số cuối của mả mới.
	 */
	private int taoSoCuoiMaLoaiDV() {
		int i = 0; // thứ tự hàng
		while (i < modelDonVi.getRowCount()) {
			// Lấy mã ở hàng thứ i trong table bỏ "MDV" và chuyển sang kiểu int.
			// Tìm mã không khớp với hàng thứ i. Mã không khớp sẽ có số cuối mã
			// - i > 1
			if (Integer.parseInt(modelDonVi.getValueAt(i, 1).toString().substring(3)) == (i + 1))
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

	private void loadData(ArrayList<DonVi> dsDonVi) {
		modelDonVi.setRowCount(0);
		for (int i = 0; i < dsDonVi.size(); i++) {
			String maDonVi = dsDonVi.get(i).getMaDonVi();
			String tenDonVi = dsDonVi.get(i).getTenDonVi();
			String row[] = { (i + 1) + "", maDonVi, tenDonVi };
			modelDonVi.addRow(row);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int hangDuocChon = tblDonVi.getSelectedRow();
		txtMaDonVi.setText(modelDonVi.getValueAt(hangDuocChon, 1).toString());
		txtTenDonVi.setText(modelDonVi.getValueAt(hangDuocChon, 2).toString());
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
		String tenDonVi = txtTenDonVi.getText().trim();
		if (tenDonVi.length() == 0) {
			showMessage(txtTenDonVi, "Nhập tên đơn vị!");
			return false;
		}
		if (tenDonVi.length() > 30) {
			showMessage(txtTenDonVi, "Tên đơn vị không được vượt quá 30 ký tự!");
			return false;
		}
		if (!tenDonVi.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ]+\\s?)+$")) {
			showMessage(txtTenDonVi,
					"Tên đơn vị bao gồm chữ cái (có thể viết tiếng việt), có thể có 1 khoảng trắng ngăn cách giữa 2 từ và không bao gồm chữ số và ký tự đặc biệt!");
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
