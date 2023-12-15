package collector;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.studentDaoImpl;
import model.student;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

public class studentUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField chi;
	private JTextField eng;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					studentUI frame = new studentUI();
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
	public studentUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 637, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 255, 255));
		panel.setBounds(22, 10, 570, 69);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("學員管理系統");
		lblNewLabel.setFont(new Font("新細明體", Font.PLAIN, 35));
		lblNewLabel.setBounds(178, 10, 267, 49);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 128));
		panel_1.setBounds(22, 87, 580, 97);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("名");
		lblNewLabel_1.setBounds(32, 10, 46, 15);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("國文");
		lblNewLabel_2.setBounds(225, 10, 46, 15);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("英文");
		lblNewLabel_3.setBounds(409, 10, 46, 15);
		panel_1.add(lblNewLabel_3);
		
		name = new JTextField();
		name.setBounds(72, 7, 96, 21);
		panel_1.add(name);
		name.setColumns(10);
		
		chi = new JTextField();
		chi.setBounds(268, 7, 96, 21);
		panel_1.add(chi);
		chi.setColumns(10);
		
		eng = new JTextField();
		eng.setBounds(440, 4, 96, 21);
		panel_1.add(eng);
		eng.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 194, 580, 110);
		contentPane.add(scrollPane);
		
		JTextArea output = new JTextArea();
		scrollPane.setViewportView(output);
		
		
		JButton btnNewButton_1 = new JButton("查詢(String)");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 1.queryAll2()--->List
				 * 2.String show="";
				 */
				
				output.setText(new studentDaoImpl().queryAll1());
			}
		});
		btnNewButton_1.setBounds(111, 53, 87, 23);
		panel_1.add(btnNewButton_1);
		
		JButton add = new JButton("新增");
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 1.擷取 name,chi,eng getText()
				 * 2.name,chi-->轉整數
				 * 3.new student(name,chi,eng);
				 * 4.add(s);
				 */
				String Name=name.getText();
				int CHI=Integer.parseInt(chi.getText());
				int ENG=Integer.parseInt(eng.getText());
				
				student s=new student(Name,CHI,ENG);
				
				new studentDaoImpl().add(s);
			}
		});
		add.setBounds(10, 53, 87, 23);
		panel_1.add(add);
		
		JButton btnNewButton = new JButton("查詢(list)");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 1.List-->queryAll2();
				 * 2.show:String;
				 * 
				 */
				List<student> l=new studentDaoImpl().queryAll2();
				String show="";
				int sum=0;
				int i=0;
				for(student o:l)
				{
					i++;
					sum=sum+(o.getChi()+o.getEng());
					
					show=show+"id:"+o.getId()+
							"\t名:"+o.getName()+
							"\t國文:"+o.getChi()+
							"\t英文:"+o.getEng()+
							"\t總分:"+(o.getChi()+o.getEng())+"\n";
							
				}
				show=show+"\n總分合計="+sum+"\t平均="+(sum/i);
				output.setText(show);
			}
		});
		btnNewButton.setBounds(208, 53, 87, 23);
		panel_1.add(btnNewButton);
		
	
		
	}
}

