package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

//import dao.DAO_PhieuDatPhong;
import dao.DaoPhieuDatPhong;
import entity.PhieuDatPhong;

public class FrmTimKiemPhieuDatPhong extends JFrame implements ActionListener  {
	private static final String URL = "rmi://26.173.247.75:7878/";

	DaoPhieuDatPhong daoPhieuDatPhong = (DaoPhieuDatPhong) Naming.lookup(URL + "daoPhieuDatPhong");

//	private DAO_PhieuDatPhong daoPhieuDatPhong = new DAO_PhieuDatPhong();

	public static JPanel pnBorder;

	private JPanel pnTimKiemPhieuDatPhong;
	private JPanel pnNorth;
	private JPanel panel_5;
	private JPanel pnTop;
	private JPanel panel_7;
	private JLabel lblNewLabel;
	private JPanel pnSouth;
	private JPanel pnCenter;
	private JPanel panel_12;
	private JPanel panel_13;
	private JPanel panel_14;
	private Component horizontalStrut;
	private JLabel lbMaPhieuDat;
	private Component horizontalStrut_1;
	private JTextField txtMPD;
	private Component horizontalStrut_2;
	private JLabel lbSDT;
	private Component horizontalStrut_3;
	private JTextField txtSDT;
	private Component horizontalStrut_4;
	private JButton btnTimKiem;
	private Component horizontalStrut_5;
	private Component verticalStrut;

	private DefaultTableModel modelTK;
	private DefaultTableModel modelPDP;
	private JTable tbTimKiem;
	private JTableHeader tbHeaderTK;
	private DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm - dd/MM/yyy");

	public static void main(String[] args) throws MalformedURLException, NotBoundException, RemoteException {
		new FrmTimKiemPhieuDatPhong().setVisible(true);
	}

	public FrmTimKiemPhieuDatPhong() throws MalformedURLException, NotBoundException, RemoteException {
		setBackground(Color.WHITE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setHgap(20);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1936, 996);

		// Kiểu chữ
				Font font1 = new Font("Times New Roman", Font.BOLD, 36);
				Font font2 = new Font("Times New Roman", Font.PLAIN, 24);

		// Màu chữ
		Color color1 = new Color(138,43,226); // BlueViolet
		Color color2 = new Color(233,221,244); 
				
		
		// Nội dung
		pnBorder = new JPanel();
		pnBorder.setBackground(new Color(233, 221, 244));
		pnBorder.setLayout(null);
		
		//pnBorder = new JTabbedPane(JTabbedPane.EAST);
		pnBorder.setBorder(null);
		pnBorder.setBackground(Color.WHITE);
		getContentPane().add(pnBorder, BorderLayout.CENTER);
		pnBorder.setLayout(new BorderLayout());
		//pnBorder.setBorder(new EmptyBorder(20, 20, 20, 20));
		pnBorder.setBackground(color2);
		
		
		pnTimKiemPhieuDatPhong = new JPanel();
		pnTimKiemPhieuDatPhong.setBackground(Color.WHITE);
		pnTimKiemPhieuDatPhong.setBorder(null);
		pnBorder.add(pnTimKiemPhieuDatPhong);
		pnTimKiemPhieuDatPhong.setLayout(null);
		
		pnNorth = new JPanel();
		pnNorth.setBackground(Color.WHITE);
		pnNorth.setBounds(0, 0, 1904, 170);
		pnTimKiemPhieuDatPhong.add(pnNorth);
		pnNorth.setLayout(new BorderLayout(0, 0));
		
		
	
		pnTop = new JPanel();
		pnTop.setBackground(Color.WHITE);
		pnNorth.add(pnTop, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("Tìm kiếm phiếu đặt phòng");
		lblNewLabel.setForeground(new Color(138, 43, 226));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 36));
		pnTop.add(lblNewLabel);
		
		pnCenter = new JPanel();
		pnNorth.add(pnCenter, BorderLayout.CENTER);
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		
		panel_12 = new JPanel();
		panel_12.setBackground(Color.WHITE);
		pnCenter.add(panel_12);
		
		verticalStrut = Box.createVerticalStrut(30);
		panel_12.add(verticalStrut);
		
		panel_13 = new JPanel();
		panel_13.setBackground(Color.WHITE);
		pnCenter.add(panel_13);
		panel_13.setLayout(new BoxLayout(panel_13, BoxLayout.X_AXIS));
		
		horizontalStrut = Box.createHorizontalStrut(50);
		panel_13.add(horizontalStrut);
		
		lbMaPhieuDat = new JLabel("Mã phiếu đặt:");
		lbMaPhieuDat.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		panel_13.add(lbMaPhieuDat);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		panel_13.add(horizontalStrut_1);
		
		txtMPD = new JTextField(17);
		txtMPD.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtMPD.setBackground(new Color(233, 221, 244));
		panel_13.add(txtMPD);
		
		horizontalStrut_2 = Box.createHorizontalStrut(20);
		panel_13.add(horizontalStrut_2);
		
		lbSDT = new JLabel("SĐT khách hàng:");
		lbSDT.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		panel_13.add(lbSDT);
		
		horizontalStrut_3 = Box.createHorizontalStrut(20);
		panel_13.add(horizontalStrut_3);
		
		txtSDT = new JTextField(17);
		txtSDT.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		txtSDT.setBackground(new Color(233, 221, 244));
		panel_13.add(txtSDT);
		
		horizontalStrut_4 = Box.createHorizontalStrut(20);
		panel_13.add(horizontalStrut_4);
		
		btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnTimKiem.setBackground(new Color(138, 43, 226));
		panel_13.add(btnTimKiem);
		
		horizontalStrut_5 = Box.createHorizontalStrut(50);
		panel_13.add(horizontalStrut_5);
		
		panel_14 = new JPanel();
		panel_14.setBackground(Color.WHITE);
		pnCenter.add(panel_14);
		
		panel_7 = new JPanel();
		pnNorth.add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.X_AXIS));
		
		pnSouth = new JPanel();
		pnSouth.setBackground(Color.WHITE);
		pnNorth.add(pnSouth, BorderLayout.SOUTH);
		pnSouth.setLayout(new BoxLayout(pnSouth, BoxLayout.X_AXIS));
		Component verticalStrut_1 = Box.createVerticalStrut(30);
		verticalStrut_1.setBackground(Color.WHITE);
		pnSouth.add(verticalStrut_1);
		
		//panel_5 = new JPanel();
		//pnThongTinTK.add(panel_5, BorderLayout.CENTER);
		DefaultTableModel modelKH = new DefaultTableModel();
		modelKH.addColumn("Mã khách hàng");
		modelKH.addColumn("Tên khách hàng");
		modelKH.addColumn("Số điện thoại");
		modelKH.addColumn("Điểm");
		modelKH.addColumn("Loại khách hàng");
		modelKH.addColumn("Ghi chú");
		
		modelPDP = new DefaultTableModel();
		modelPDP.addColumn("Mã phiếu đặt");
		modelPDP.addColumn("Mã phòng");
		modelPDP.addColumn("Số điện thoại khách hàng");
		modelPDP.addColumn("Thời gian lập phiếu");
		modelPDP.addColumn("Thời gian nhận phòng");
		
		tbTimKiem = new JTable(modelPDP);
		JScrollPane jScrollPane = new JScrollPane(tbTimKiem,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPane.setBounds(56, 170, 1800, 680);
		tbHeaderTK = tbTimKiem.getTableHeader();
		tbHeaderTK.setFont(font2);
		tbHeaderTK.setBackground(color2);
		tbTimKiem.setFont(font2);
		tbTimKiem.setRowHeight(35);
		loadData(daoPhieuDatPhong.getAllPhieu());
		//String row[] = {"MPD001","P010","0123456789","17:01-10/08/2022","19:01-10/08/2022"};
		//modelPDP.addRow(row);
		
		//panel_5.add(jScrollPane);
		pnTimKiemPhieuDatPhong.add(jScrollPane);
		tbTimKiem.setDefaultEditor(Object.class, null);
		btnTimKiem.addActionListener(this);

	}
	private void loadData(ArrayList<PhieuDatPhong> dsPDP) {
		modelPDP.setRowCount(0);
		for (int i = 0; i < dsPDP.size(); i++) {
	
			String tenPDP = dsPDP.get(i).getMaPhieuDatPhong();
			String maPhong = dsPDP.get(i).getPhong().getMaPhong();
			String sdt = dsPDP.get(i).getKhachHang().getSoDienThoai();
			//String tglp =  dsPDP.get(i).getGioTaoPhieu()+"";
			String tglp =  dtf2.format(dsPDP.get(i).getGioTaoPhieu());
			String tgnp =  dtf2.format(dsPDP.get(i).getGioVaoPhong());
			// tgnp = dsPDP.get(i).getGioVaoPhong()+"";
			String row[] = { tenPDP, maPhong, sdt,tglp,tgnp};
			modelPDP.addRow(row);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj.equals(btnTimKiem)) {
			String mdp = txtMPD.getText();
			String sdt = txtSDT.getText();
			ArrayList<PhieuDatPhong> dsTimDuoc = null;
			try {
				dsTimDuoc = daoPhieuDatPhong.getDSPhieuTheoMa(mdp, sdt);
			} catch (RemoteException ex) {
				throw new RuntimeException(ex);
			}
			if (dsTimDuoc.size() > 0) {
				loadData(dsTimDuoc);
			} else {
				JOptionPane.showMessageDialog(this, "Không tìm thấy!\nVui lòng kiểm tra lại thông tin tìm kiếm.");
			}
		}
		/*
		if(obj.equals(btnLamMoi)) {
			loadData(daoPhieuDatPhong.getAllPhieu());
			txtMPD.setText("");
			txtSDT.setText("");
		}
		if(obj.equals(btnNhanPhong)) {
			int hangDuocChon = tbTimKiem.getSelectedRow();
			if (hangDuocChon > -1) {
				String maPhong =  modelPDP.getValueAt(hangDuocChon, 1).toString();
				String maPhieu =  modelPDP.getValueAt(hangDuocChon, 0).toString();
				if(daoPhieuDatPhong.nhanPhongCho(maPhong) && daoPhieuDatPhong.xoaPhieuTheoMa(maPhieu)) {
					JOptionPane.showMessageDialog(this, "Nhận phòng thành công.");
					loadData(daoPhieuDatPhong.getAllPhieu());
				}
				else {
					JOptionPane.showMessageDialog(this, "Nhận phòng không thành công.");
				}
			} else {
				JOptionPane.showMessageDialog(this,
						"Vui lòng chọn 1 hàng trong bảng danh sách phiếu đặt phòng, trước khi nhận phòng!");
			}
		}
		if(obj.equals(btnHuyPhong)) {
			int hangDuocChon = tbTimKiem.getSelectedRow();
			if (hangDuocChon > -1) {
				String maPhong =  modelPDP.getValueAt(hangDuocChon, 1).toString();
				String maPhieu =  modelPDP.getValueAt(hangDuocChon, 0).toString();
				if(daoPhieuDatPhong.nhanPhongCho(maPhong) && daoPhieuDatPhong.xoaPhieuTheoMa(maPhieu) && daoPhieuDatPhong.capNhatTrangThai(maPhong)) {
					JOptionPane.showMessageDialog(this, "Xóa phiếu đặt phòng thành công.");
					loadData(daoPhieuDatPhong.getAllPhieu());
				}
				else {
					JOptionPane.showMessageDialog(this, "Xóa phiếu đặt phòng không thành công.");
				}
			} else {
				JOptionPane.showMessageDialog(this,
						"Vui lòng chọn 1 hàng trong bảng danh sách phiếu đặt phòng, trước khi xóa!");
			}
		}
		*/
	}

	

	
}
