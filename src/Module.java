import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Module extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Module frame = new Module();
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
	public Module() {
		setTitle("Module Viewer");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 812, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel List = new JPanel();
		List.setBounds(10, 0, 781, 320);
		contentPane.add(List);
		List.setLayout(null);
		
		JList modules = new JList();
		modules.setBounds(10, 288, 761, -253);
		List.add(modules);
		
		JLabel title = new JLabel("Modules");
		title.setFont(new Font("Tahoma", Font.PLAIN, 14));
		title.setBounds(0, 0, 123, 19);
		List.add(title);
		
		table = new JTable();
		table.setBounds(0, 312, 420, -311);
		List.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 2, 2);
		List.add(scrollPane);
	}
}
