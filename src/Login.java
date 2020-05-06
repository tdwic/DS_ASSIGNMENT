import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField uName;
	private JTextField pWord;
	//Login frameLogin = new Login();
	SensorAdmin sensorAdmin = new SensorAdmin();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User Name");
		lblNewLabel.setBounds(42, 74, 98, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(42, 117, 98, 14);
		contentPane.add(lblNewLabel_1);
		
		uName = new JTextField();
		uName.setBounds(152, 71, 223, 20);
		contentPane.add(uName);
		uName.setColumns(10);
		
		pWord = new JTextField();
		pWord.setBounds(152, 114, 223, 20);
		contentPane.add(pWord);
		pWord.setColumns(10);
		
		//Checking is the userName = admin and password = admin
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (uName.getText().equals("admin") && pWord.getText().equals("admin") ) {
					Login login = new Login();
					login.setVisible(false);
					//frameLogin.dispose();
					sensorAdmin.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Wrong Password");	
				}
			}
		});
		btnNewButton.setBounds(143, 169, 160, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 44));
		lblLogin.setBounds(171, 13, 105, 48);
		contentPane.add(lblLogin);
	}
}

