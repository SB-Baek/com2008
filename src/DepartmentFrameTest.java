import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class DepartmentFrameTest extends JFrame {

	private JPanel contentPane;
	private JTextField nameField;
	private JTextField abbreviationField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentFrameTest frame = new DepartmentFrameTest();
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
	public DepartmentFrameTest() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 710, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 643, 245);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add a new department");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 200, 17);
		panel.add(lblNewLabel);
		
		JButton addButton = new JButton("Add");
		addButton.setBounds(10, 96, 89, 23);
		panel.add(addButton);
		
		nameField = new JTextField();
		nameField.setBounds(96, 36, 97, 20);
		panel.add(nameField);
		nameField.setColumns(10);
		
		JLabel name = new JLabel("name:");
		name.setBounds(20, 39, 46, 14);
		panel.add(name);
		
		abbreviationField = new JTextField();
		abbreviationField.setBounds(96, 61, 97, 20);
		panel.add(abbreviationField);
		abbreviationField.setColumns(10);
		
		JLabel abbreviation = new JLabel("abbreviation:");
		abbreviation.setBounds(20, 64, 104, 14);
		panel.add(abbreviation);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(236, 30, 178, 163);
		panel.add(scrollPane);
		
		JList departmentList = new JList();
		scrollPane.setViewportView(departmentList);
		
		JLabel lblNewLabel_1 = new JLabel("Remove Degree");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(236, 13, 178, 14);
		panel.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Remove");
		btnNewButton.setBounds(236, 204, 89, 23);
		panel.add(btnNewButton);
		
		JScrollPane linkFrame = new JScrollPane();
		linkFrame.setBounds(424, 30, 209, 163);
		panel.add(linkFrame);
		
		JLabel lblNewLabel_1_1 = new JLabel("Link to department");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(424, 14, 178, 14);
		panel.add(lblNewLabel_1_1);
		
		JButton btnLink = new JButton("Link");
		btnLink.setBounds(424, 204, 89, 23);
		panel.add(btnLink);
	}
}
