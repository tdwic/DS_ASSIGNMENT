
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.xml.ws.soap.Addressing;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class SensorAdmin extends JFrame {

	private JPanel contentPane;
	private JTextField txtFloorNo;
	private JTextField txtRoomNo;
	private JTable table;
	private int roomNumber;
	private int floorNumber;
	private int co2Level;
	private int smokeLevel;
	private boolean sensorStaus;
	SensorModel sensorModel = new SensorModel();
	private JTextField txtCo2Level;
	private JTextField txtSmokeLevel;
	JComboBox txtSensorStatus = new JComboBox();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SensorAdmin frame = new SensorAdmin();
					frame.setVisible(true);
					JOptionPane.showMessageDialog(null, "Select Records To Edit..\n If You Want To Add New Record Just Skip This Message !");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SensorAdmin() {
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 682, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sensor Admin");
		lblNewLabel.setFont(new Font("MingLiU_HKSCS-ExtB", Font.BOLD, 33));
		lblNewLabel.setBounds(164, 11, 328, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sensor Status");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblNewLabel_1.setBounds(26, 152, 126, 21);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Floor Number");
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblNewLabel_2.setBounds(26, 77, 126, 28);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Room Number");
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblNewLabel_3.setBounds(26, 116, 126, 23);
		contentPane.add(lblNewLabel_3);
		
		txtFloorNo = new JTextField();
		txtFloorNo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtFloorNo.setBounds(175, 81, 184, 20);
		contentPane.add(txtFloorNo);
		txtFloorNo.setColumns(10);
		
		txtRoomNo = new JTextField();
		txtRoomNo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtRoomNo.setBounds(175, 117, 184, 20);
		contentPane.add(txtRoomNo);
		txtRoomNo.setColumns(10);
		
		JButton btnAddDetails = new JButton("Add Details");

		btnAddDetails.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnAddDetails.setBounds(392, 75, 126, 34);
		contentPane.add(btnAddDetails);
		
		JButton btnSave = new JButton("Save");
		btnSave.setEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				//Get inputs from the fields and convert in to int
				
				roomNumber =Integer.parseInt(txtRoomNo.getText()); //Int
				floorNumber =Integer.parseInt(txtFloorNo.getText());//Int
				co2Level =Integer.parseInt(txtCo2Level.getText()); //Int
				smokeLevel = Integer.parseInt(txtSmokeLevel.getText()); //Int
				
				//Sensor staus boolean set acording to the dropdiown sellections
				if (txtSensorStatus.getSelectedItem().toString().equals("Activate")) {
					sensorStaus = true; //If Sensor staus 'Activate' this boolean true
				}else {
					sensorStaus = false; //Else False
				}
				
				//asignin filtered inputs to the model class via getters and setters

				sensorModel.setRoomNumber(roomNumber);
				sensorModel.setFloorNumber(floorNumber);
				sensorModel.setSmokeLevel(smokeLevel);
				sensorModel.setCO2Level(co2Level);
				sensorModel.setStatus(sensorStaus);
		
				ArrayList<SensorModel> xArrayList =new ArrayList<>();
				try {
					ISensor tt = (ISensor) Naming.lookup("rmi://localhost/ABC");
					tt.updatesensor(sensorModel);
					xArrayList = tt.getSensors();
				
				} catch (Exception e1) {
					
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnSave.setEnabled(false);
				tableLoad();
				JOptionPane.showMessageDialog(null,"Details Updated Sucessfully!");
				

			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSave.setBounds(392, 137, 126, 34);
		contentPane.add(btnSave);
		
		txtSensorStatus = new JComboBox();
		txtSensorStatus.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtSensorStatus.setModel(new DefaultComboBoxModel(new String[] {"Activate", "Deactivate"}));
		txtSensorStatus.setBounds(175, 150, 184, 21);
		contentPane.add(txtSensorStatus);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 186, 613, 221);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int rowNumber = table.getSelectedRow();
				sensorModel.setId(Integer.parseInt(table.getValueAt(rowNumber, 0).toString()));
				txtRoomNo.setText(table.getValueAt(rowNumber, 1).toString());
				txtFloorNo.setText(table.getValueAt(rowNumber, 2).toString());
				txtCo2Level.setText(table.getValueAt(rowNumber, 3).toString());
				txtSmokeLevel.setText(table.getValueAt(rowNumber, 4).toString());
				if (table.getValueAt(rowNumber, 5).toString()=="true") {
					txtSensorStatus.setSelectedItem("Activate");
					//System.out.println(table.getValueAt(rowNumber, 5));
				}else if(table.getValueAt(rowNumber, 5).toString()=="false") {
					txtSensorStatus.setSelectedItem("Deactivate");
//					System.out.println(table.getValueAt(rowNumber, 5));
				}
				btnSave.setEnabled(true);
			}
		});
		scrollPane.setViewportView(table);
		
		txtCo2Level = new JTextField();
		txtCo2Level.setEnabled(false);
		txtCo2Level.setText("9");
		txtCo2Level.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtCo2Level.setColumns(10);
		txtCo2Level.setBounds(175, 151, -34, 20);
		contentPane.add(txtCo2Level);
		
		txtSmokeLevel = new JTextField();
		txtSmokeLevel.setEnabled(false);
		txtSmokeLevel.setText("9");
		txtSmokeLevel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtSmokeLevel.setColumns(10);
		txtSmokeLevel.setBounds(175, 183, -22, 20);
		contentPane.add(txtSmokeLevel);
		
		
		
		
		//Add Details button action perfome
		btnAddDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Get inputs from the fields and convert in to int
				
				roomNumber =Integer.parseInt(txtRoomNo.getText()); //Int
				floorNumber =Integer.parseInt(txtFloorNo.getText());//Int
				co2Level =Integer.parseInt(txtCo2Level.getText()); //Int
				smokeLevel = Integer.parseInt(txtSmokeLevel.getText()); //Int
				
				//Sensor staus boolean set acording to the dropdiown sellections
				if (txtSensorStatus.getSelectedItem().toString().equals("Activate")) {
					sensorStaus = true; //If Sensor staus 'Activate' this boolean true
				}else {
					sensorStaus = false; //Else False
				}
				
				//asignin filtered inputs to the model class via getters and setters
				sensorModel.setRoomNumber(roomNumber);
				sensorModel.setFloorNumber(floorNumber);
				sensorModel.setSmokeLevel(smokeLevel);
				sensorModel.setCO2Level(co2Level);
				sensorModel.setStatus(sensorStaus);
				
				//Add data to the remote
				ArrayList<SensorModel> xArrayList =new ArrayList<>();
				try {
					ISensor obj = (ISensor) Naming.lookup("rmi://localhost/ABC");
					int i =obj.addsensor(sensorModel);
					xArrayList = obj.getSensors();
				
				} catch (Exception e1) {
					
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				//Setting data to the table
				Object[] cols = new String[] {
						"Id","Room No","Floor No", "CO2 Level", "Smoke Level", "Status"
				};
				
				Object[][] dataRow = new Object[xArrayList.size()][6];
				for(int t = 0; t<xArrayList.size();t++) {
					dataRow[t][0] = xArrayList.get(t).getId();
					dataRow[t][1] = xArrayList.get(t).getRoomNumber();
					dataRow[t][2] = xArrayList.get(t).getFloorNumber();
					dataRow[t][3] = xArrayList.get(t).getCO2Level();
					dataRow[t][4] = xArrayList.get(t).getSmokeLevel();
					dataRow[t][5] = xArrayList.get(t).getStatus();
				}
				
				TableModel tableModel = new DefaultTableModel(dataRow,cols);
				
				table.setModel(tableModel);
							
			}
		});
		
		
		tableLoad();
		
	}
	
	//Load data to table
	
	public void tableLoad() {
		ArrayList<SensorModel> xArrayList =new ArrayList<>();
		try {
			ISensor obj = (ISensor) Naming.lookup("rmi://localhost/ABC");
//			int i =obj.addsensor(sensorModel);
			xArrayList = obj.getSensors();
		
		} catch (Exception e1) {
			
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		Object[] cols = new String[] {
				"Id","Room No","Floor No", "CO2 Level", "Smoke Level", "Status"
		};
		
		Object[][] dataRow = new Object[xArrayList.size()][6];
		for(int t = 0; t<xArrayList.size();t++) {
			dataRow[t][0] = xArrayList.get(t).getId();
			dataRow[t][1] = xArrayList.get(t).getRoomNumber();
			dataRow[t][2] = xArrayList.get(t).getFloorNumber();
			dataRow[t][3] = xArrayList.get(t).getCO2Level();
			dataRow[t][4] = xArrayList.get(t).getSmokeLevel();
			dataRow[t][5] = xArrayList.get(t).getStatus();
		}
		
		TableModel tableModel = new DefaultTableModel(dataRow,cols);
		
		table.setModel(tableModel);
		
		
	}
	
}
