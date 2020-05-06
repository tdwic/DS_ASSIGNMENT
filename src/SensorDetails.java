
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Font;

public class SensorDetails {

	private JFrame frame;
	
	SensorModel sensorModel = new SensorModel();
	Login logingPage = new Login();
	private JTable table;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SensorDetails window = new SensorDetails();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SensorDetails() {
		initialize();
		tableLoad();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 596, 364);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSensoreDetails = new JLabel("Sensore Details");
		lblSensoreDetails.setFont(new Font("Sitka Subheading", Font.PLAIN, 39));
		lblSensoreDetails.setBounds(59, 25, 302, 36);
		frame.getContentPane().add(lblSensoreDetails);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				logingPage.setVisible(true);
			}
		});
		
		btnNewButton.setBounds(461, 41, 109, 36);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 93, 560, 206);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}
	
	//Load data to the table
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
		
		//Creating colunm names
		Object[] cols = new String[] {
				"Id","Room No","Floor No", "CO2 Level", "Smoke Level", "Status"
		};
		
		
		//Setiing data to the table
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
