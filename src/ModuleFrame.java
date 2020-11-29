import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import java.awt.Color;

public class ModuleFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModuleFrame frame = new ModuleFrame();
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
	public ModuleFrame() {
		setTitle("University Project");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 448, 565);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel add = new JPanel();
		add.setBounds(10, 40, 412, 150);
		contentPane.add(add);
		
		JPanel delete = new JPanel();
		delete.setBounds(10, 290, 412, 150);
		contentPane.add(delete);
		delete.setLayout(null);
		
		JList removeModules = new JList();
		removeModules.setBounds(195, 11, 207, 128);
		delete.add(removeModules);
		
		JTextArea txtrDeleteModulesBy = new JTextArea();
		txtrDeleteModulesBy.setText("Delete modules by selecting from the list and click remove");
		txtrDeleteModulesBy.setRows(2);
		txtrDeleteModulesBy.setLineWrap(true);
		txtrDeleteModulesBy.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrDeleteModulesBy.setEditable(false);
		txtrDeleteModulesBy.setBackground(Color.WHITE);
		txtrDeleteModulesBy.setBounds(10, 7, 168, 98);
		delete.add(txtrDeleteModulesBy);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(10, 116, 89, 23);
		delete.add(btnRemove);
		
		JLabel addTitle = new JLabel("Add optional modules");
		addTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addTitle.setBounds(10, 11, 256, 22);
		contentPane.add(addTitle);
		add.setLayout(null);
		
		JList optionalModules = new JList();
		optionalModules.setBounds(188, 11, 214, 128);
		add.add(optionalModules);
		
		JLabel RemoveTitle = new JLabel("Remove Modules");
		RemoveTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		RemoveTitle.setBounds(10, 259, 118, 22);
		contentPane.add(RemoveTitle);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBounds(10, 116, 89, 23);
		add.add(btnNewButton);
		
		JTextArea addText = new JTextArea();
		addText.setBackground(Color.WHITE);
		addText.setEditable(false);
		addText.setLineWrap(true);
		addText.setRows(2);
		addText.setFont(new Font("Tahoma", Font.PLAIN, 11));
		addText.setText("Select an module from the list and click add below");
		addText.setBounds(10, 7, 168, 98);
		add.add(addText);
	}
}
