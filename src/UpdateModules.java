import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UpdateModules extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateModules frame = new UpdateModules();
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
	public UpdateModules() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 605, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 569, 175);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel title = new JLabel("Edit module details:");
		title.setFont(new Font("Tahoma", Font.PLAIN, 14));
		title.setBounds(10, 11, 187, 22);
		panel.add(title);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(223, 0, 345, 161);
		panel.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel initLabel = new JLabel("Initial Grade:");
		initLabel.setBounds(10, 44, 92, 14);
		panel.add(initLabel);
		
		JLabel resitLabel = new JLabel("Resit Grade:");
		resitLabel.setBounds(10, 78, 92, 14);
		panel.add(resitLabel);
		
		textField = new JTextField();
		textField.setBounds(77, 44, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(77, 75, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.setBounds(13, 120, 89, 23);
		panel.add(btnNewButton);
	}

}
