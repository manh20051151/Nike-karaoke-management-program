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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
//
//import connectDB.ConnectDB;
//import dao.DAO_LoaiKhachHang;
import dao.DaoLoaiKhachHang;
import entity.LoaiKhachHang;

public class FrmQuanLyLoaiKhacHang extends JFrame implements ActionListener, MouseListener {
	private static final String URL = "rmi://26.173.247.75:7878/";

	DaoLoaiKhachHang daoLoaiKH = (DaoLoaiKhachHang) Naming.lookup(URL + "daoLoaiKhachHang");


//	private DAO_LoaiKhachHang daoLoaiKH = new DAO_LoaiKhachHang();

	public static JPanel pnBorder;
	private DefaultTableModel modelLKH;
	private JButton btnThem;
	private JTextField txtMaLKH;
	private JTextField txtTenLKH;
	private JButton btnSua;
	private JButton btnXoaTrang;
	private JButton btnXoa;
	private JButton btnLuu;
	private JTable tbLoaiKhachHang;
	private JButton btnTimKiem;
	private JComboBox cbLuaChon;
	private JTextField txtTimKiem;
	private JButton btnLamMoi;
	
	
	
	public FrmQuanLyLoaiKhacHang() throws MalformedURLException, NotBoundException, RemoteException {
		this.setTitle("Quản lý loại khách hàng");
		//this.setSize(1920, 1030);
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
		Color color1 = new Color(138,43,226); // BlueViolet
		Color color2 = new Color(233,221,244); 

		
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

		Box bThongTinLKH = Box.createVerticalBox();
		Box bLbMaLKH = Box.createHorizontalBox();
		Box bTxtMaLKH = Box.createHorizontalBox();
		Box bLbTenLKH = Box.createHorizontalBox();
		Box bTxtTenLKH = Box.createHorizontalBox();
		Box bThemSua = Box.createHorizontalBox();
		Box bXoaTrang = Box.createHorizontalBox();
		
		JLabel lbMaLKH = new JLabel("Mã loại khách hàng:");
		lbMaLKH.setFont(font2);
		JLabel lbTenLKH = new JLabel("Tên loại khách hàng:");
		lbTenLKH.setFont(font2);
	
		

		bLbMaLKH.add(lbMaLKH);
		bLbMaLKH.add(Box.createVerticalStrut(0));
		bLbTenLKH.add(lbTenLKH);
		bLbTenLKH.add(Box.createVerticalStrut(0));
		

		txtMaLKH = new JTextField(17);
		txtMaLKH.setFont(font2);
		txtMaLKH.setBackground(color2);
		txtTenLKH = new JTextField(17);
		txtTenLKH.setFont(font2);
		txtTenLKH.setBackground(color2);
		


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

		bTxtMaLKH.add(txtMaLKH);
		bTxtTenLKH.add(txtTenLKH);

		/*
		bThemSua.add(btnThem);
		bThemSua.add(Box.createHorizontalStrut(15));
		bThemSua.add(btnSua);
		bXoaTrang.add(btnXoaTrang);
		*/

		bThongTinLKH.add(bLbMaLKH);
		bThongTinLKH.add(Box.createVerticalStrut(3));
		bThongTinLKH.add(bTxtMaLKH);
		bThongTinLKH.add(Box.createVerticalStrut(20));
		bThongTinLKH.add(bLbTenLKH);
		bThongTinLKH.add(Box.createVerticalStrut(3));
		bThongTinLKH.add(bTxtTenLKH);
		bThongTinLKH.add(Box.createVerticalStrut(20));
		
		
		
		bThongTinLKH.add(Box.createVerticalStrut(30));
		bThongTinLKH.add(bThemSua);
		bThongTinLKH.add(Box.createVerticalStrut(15));
		bThongTinLKH.add(bXoaTrang);
		bThongTinLKH.add(Box.createVerticalStrut(30));

		pnLeftContent.add(bThongTinLKH);

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
		//pnRightTop.setLayout(new BorderLayout());

		JLabel lbDSKH = new JLabel("Danh sách loại khách hàng");
		lbDSKH.setFont(font1);
		lbDSKH.setForeground(color1);

		
		/*
		JPanel pnTimKiem = new JPanel();
		pnTimKiem.setBackground(Color.WHITE);
		pnTimKiem.setLayout(new BoxLayout(pnTimKiem, BoxLayout.Y_AXIS));

		Box bTimKiem = Box.createVerticalBox();
		Box bTimKiem_LuaChon = Box.createHorizontalBox();
		Box bTimKiem_Nhap = Box.createHorizontalBox();

		JLabel lbLuaChon = new JLabel("Tìm theo:");
		lbLuaChon.setFont(font2);
		JLabel lbNhap = new JLabel("Tìm kiếm:");
		lbNhap.setFont(font2);
		lbLuaChon.setPreferredSize(lbNhap.getPreferredSize());

		cbLuaChon = new JComboBox<String>();
		cbLuaChon.addItem("Mã loại khách hàng");
		cbLuaChon.addItem("Tên loại khách hàng");
		cbLuaChon.setFont(font2);
		cbLuaChon.setBackground(color2);

		txtTimKiem = new JTextField(17);
		txtTimKiem.setFont(font2);
		txtTimKiem.setBackground(color2);

		btnTimKiem = new JButton();
		btnTimKiem.setIcon(new ImageIcon(FrmQuanLyHoaDon.class.getResource("/img/Toolbar-Browser-Search-icon.png")));
		btnTimKiem.setFont(font2);
		btnTimKiem.setBackground(color1);
		btnTimKiem.setForeground(Color.WHITE);

		bTimKiem_LuaChon.add(lbLuaChon);
		bTimKiem_LuaChon.add(Box.createHorizontalStrut(7));
		bTimKiem_LuaChon.add(cbLuaChon);
		bTimKiem_Nhap.add(lbNhap);
		bTimKiem_Nhap.add(Box.createHorizontalStrut(7));
		bTimKiem_Nhap.add(txtTimKiem);
		bTimKiem_Nhap.add(btnTimKiem);

		bTimKiem.add(bTimKiem_LuaChon);
		bTimKiem.add(Box.createVerticalStrut(10));
		bTimKiem.add(bTimKiem_Nhap);

		pnTimKiem.add(bTimKiem);

		pnRightTop.add(pnTimKiem, BorderLayout.EAST);
		*/
		pnRightTop.add(lbDSKH);

		// Right-Center
		JPanel pnRightCenter = new JPanel();
		pnRightCenter.setLayout(new BorderLayout());
		pnRightCenter.setBackground(Color.WHITE);
		pnRightCenter.setBorder(new EmptyBorder(20, 0, 20, 0));

		modelLKH = new DefaultTableModel();
		modelLKH.addColumn("Mã loại khách hàng");
		modelLKH.addColumn("Tên loại khách hàng");
		
		tbLoaiKhachHang = new JTable(modelLKH);
		JScrollPane jScrollPane = new JScrollPane(tbLoaiKhachHang,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JTableHeader tbHeaderKH = tbLoaiKhachHang.getTableHeader();
		tbHeaderKH.setFont(font2);
		tbHeaderKH.setBackground(color2);
		tbLoaiKhachHang.setFont(font2);
		tbLoaiKhachHang.setRowHeight(35);
		pnRightCenter.add(jScrollPane);
		loadData(daoLoaiKH.getALLLoaiKhachHang());

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
		btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(color1);
		btnLamMoi.setForeground(Color.WHITE);
		btnLamMoi.setFont(font2);
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
		
		tbLoaiKhachHang.setDefaultEditor(Object.class, null);
		
		dongMoNhapLieu(false);
	
		btnXoaTrang.setEnabled(false);
		btnLuu.setEnabled(false);
		
		btnThem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		//btnTimKiem.addActionListener(this);
		btnLuu.addActionListener(this);
		//btnLamMoi.addActionListener(this);
		
		tbLoaiKhachHang.addMouseListener(this);
	}

	public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
		new FrmQuanLyLoaiKhacHang().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int hangDuocChon = tbLoaiKhachHang.getSelectedRow();
		txtMaLKH.setText(modelLKH.getValueAt(hangDuocChon, 0).toString());
		txtTenLKH.setText(modelLKH.getValueAt(hangDuocChon, 1).toString());	
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
				btnXoaTrang.setEnabled(true);
				btnXoa.setEnabled(false);
				//btnLamMoi.setEnabled(false);
				btnThem.setText("Hủy");
				tbLoaiKhachHang.removeMouseListener(this);
				xoaRongNhapLieu();
				int soHang = modelLKH.getRowCount();
				// Mã có dạng LKHXXX, XXX: từ 001 -> 999
				if (soHang < 999) {
					int soCuoi = taoSoCuoiMaLKH();
					// TH1: số cuối tìm được có 1 chữ số
					if (soCuoi < 10) {
						txtMaLKH.setText("LKH" + soCuoi);
					}
				} else {
					JOptionPane.showMessageDialog(this, "Dữ liệu đầy, không thể thêm!");
				}
			}
			else {
				tbLoaiKhachHang.addMouseListener(this);
				dongMoNhapLieu(false);
				btnThem.setText("Thêm");
				btnSua.setEnabled(true);
				//btnLamMoi.setEnabled(true);
				btnXoa.setEnabled(true);
				btnXoaTrang.setEnabled(false);
				btnLuu.setEnabled(false);
				tbLoaiKhachHang.addMouseListener(this);
				txtMaLKH.setText("");
				xoaRongNhapLieu();
				try {
					loadData(daoLoaiKH.getALLLoaiKhachHang());
				} catch (RemoteException ex) {
					throw new RuntimeException(ex);
				}
			}
			
		}
		if(obj.equals(btnXoaTrang)) {
			xoaRongNhapLieu();
		}
		if (obj.equals(btnXoa)) {
			int hangDuocChon = tbLoaiKhachHang.getSelectedRow();
			if (hangDuocChon > -1) {
				if (JOptionPane.showConfirmDialog(this, "Chắc chắn xóa dòng này?") == JOptionPane.YES_OPTION) {
					try {
						if (daoLoaiKH.deleteLoaiKHTheoMa(modelLKH.getValueAt(hangDuocChon, 0).toString())) {
							loadData(daoLoaiKH.getALLLoaiKhachHang());
							JOptionPane.showMessageDialog(this, "Xóa thành công.");
							txtMaLKH.setText("");
							xoaRongNhapLieu();
						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			} else {
				JOptionPane.showMessageDialog(this, "Chọn 1 hàng trong bảng danh sách loại khách hàng cần xóa!");
			}
		}
		if(obj.equals(btnSua)) {
			if (btnSua.getText().equals("Sửa")) {
				int hangDuocChon = tbLoaiKhachHang.getSelectedRow();
				if (hangDuocChon > -1) {
					dongMoNhapLieu(true);
					btnSua.setText("Hủy");
					btnThem.setEnabled(false);
					//btnLamMoi.setEnabled(false);
					btnXoa.setEnabled(false);
					btnXoaTrang.setEnabled(true);
					btnLuu.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(this,
							"Vui lòng chọn 1 hàng trong bảng danh sách loại khách hàng, trước khi sửa!");
				}
			} else {
				dongMoNhapLieu(false);
				btnSua.setText("Sửa");
				btnSua.setText("Sửa");
				btnThem.setEnabled(true);
				//btnLamMoi.setEnabled(true);
				btnXoa.setEnabled(true);
				btnXoaTrang.setEnabled(false);
				btnLuu.setEnabled(false);
				txtMaLKH.setText("");
				xoaRongNhapLieu();
			}
		}
		if(obj.equals(btnLuu)) {
			if (btnThem.getText().equals("Hủy")) {
				if(validData()) {
					String maLKH = txtMaLKH.getText().trim();
					String tenLKH = txtTenLKH.getText().trim();
					try {
						if (daoLoaiKH.addLoaiKhachHang(new LoaiKhachHang(maLKH, tenLKH))) {
							loadData(daoLoaiKH.getALLLoaiKhachHang());
							JOptionPane.showMessageDialog(this, "Thêm loại khách hàng mới thành công");
							dongMoNhapLieu(false);
							btnThem.setText("Thêm");
							xoaRongNhapLieu();
							btnSua.setEnabled(true);
							//btnLamMoi.setEnabled(true);
							btnXoa.setEnabled(true);
							btnXoaTrang.setEnabled(false);
							btnLuu.setEnabled(false);
							txtMaLKH.setText("");
							tbLoaiKhachHang.addMouseListener(this);

						}
					} catch (RemoteException ex) {
						throw new RuntimeException(ex);
					}
				}
			}
			if (btnSua.getText().equals("Hủy")) {
				if (validData()) {
					String maLKH = txtMaLKH.getText().trim();
					String tenLKH = txtTenLKH.getText().trim();
					try {
						if (daoLoaiKH.updateLoaiKH(new LoaiKhachHang(maLKH, tenLKH))) {
							loadData(daoLoaiKH.getALLLoaiKhachHang());
							JOptionPane.showMessageDialog(this, "Sửa thông tin loại khách hàng thành công");
							dongMoNhapLieu(false);
							btnSua.setText("Sửa");
							btnThem.setEnabled(true);
							//btnLamMoi.setEnabled(true);
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
		if(obj.equals(btnLamMoi)) {
			loadData(daoLoaiKH.getALLLoaiKhachHang());
		}
		*/
		
	}
	/**
	 * Tạo số cuối của mã mới không trùng với các mã đã có trong table. Lưu ý: Cần
	 * sắp xếp mã trên table theo thứ tự tăng dần.
	 * 
	 * @return Số cuối của mả mới.
	 */
	private int taoSoCuoiMaLKH() {
		int i = 0; // thứ tự hàng
		while (i < modelLKH.getRowCount()) {
			// Lấy mã ở hàng thứ i trong table bỏ "DV" và chuyển sang kiểu int.
			// Tìm mã không khớp với hàng thứ i. Mã không khớp sẽ có số cuối mã
			// - i > 1
			if (Integer.parseInt(modelLKH.getValueAt(i, 0).toString().substring(3)) == (i + 1))
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
		String tenLKH = txtTenLKH.getText().trim();
		if (tenLKH.length() == 0) {
			showMessage(txtTenLKH, "Nhập tên loại khách hàng!");
			return false;
		}
		if (!tenLKH.matches(
				"^([A-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪỬỮỰỲỴÝỶỸa-záàảãạăắằẳẵặâấầẩẫậéèẻẽẹêếềểễệóòỏõọôốồổỗộơớờởỡợíìỉĩịúùủũụưứừửữựýỳỷỹỵđ\\d]*\\s?)+$")) {
			showMessage(txtTenLKH, "Tên loại khách hàng bao gồm chữ cái, chữ số tiếng Việt, không bao gồm ký tự đặc biệt!");
			return false;
		}
		return true;
	}
	private void showMessage(JTextField txt, String message) {
		txt.setText("");
		JOptionPane.showMessageDialog(this, message);
	}
	private void xoaRongNhapLieu() {
		txtTenLKH.requestFocus();
		txtTenLKH.setText("");
	}
	private void dongMoNhapLieu(Boolean b) {
		txtMaLKH.setEditable(false);
		txtTenLKH.setEditable(b);
	}
	private void loadData(LoaiKhachHang lkh) {
		modelLKH.setRowCount(0);
		String maLKH = lkh.getMaLoaiKhachHang();
		String tenLKH = lkh.getTenLoaiKhachHang();
		String row[] = { maLKH, tenLKH };
		modelLKH.addRow(row);
	}
	private void loadData(ArrayList<LoaiKhachHang> dsLKH) {
		modelLKH.setRowCount(0);
		for (int i = 0; i < dsLKH.size(); i++) {
			String maLKH = dsLKH.get(i).getMaLoaiKhachHang();
			String tenLKH = dsLKH.get(i).getTenLoaiKhachHang();
			String row[] = { maLKH, tenLKH };
			modelLKH.addRow(row);
		}
	}

}
