package com.efm.crjj.ismo.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.efm.crjj.ismo.metier.IMetier;
import com.efm.crjj.ismo.metier.MetierEmploye;
import com.efm.crjj.ismo.model.Employe;
import com.efm.crjj.ismo.utils.DoubleValidate;

import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class Principale extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	IMetier<Employe> emp=new MetierEmploye();
	private JTable table;
	private static String[] depart = { "RH", "Finance", "Production" };
	private DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(depart);
	private JLabel lblNewLabel_3_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principale frame = new Principale();
					frame.setVisible(true);
					frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principale() {
		setTitle("Gestion des employes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1197, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Nouvel Employe", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(10, 21, 242, 219);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom et prenom :");
		lblNewLabel.setBounds(10, 16, 222, 14);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 41, 222, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Departement");
		lblNewLabel_1.setBounds(10, 72, 222, 14);
		panel.add(lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 91, 222, 22);
		panel.add(comboBox);
		comboBox.setModel(model);
		
		JLabel lblNewLabel_2 = new JLabel("Salaire : ");
		lblNewLabel_2.setBounds(10, 129, 222, 14);
		panel.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 154, 222, 20);
		panel.add(textField_1);
		textField_1.setDocument(new DoubleValidate());
		textField_1.setColumns(10);

		//JButton btnNewButton = new MyButton("Engestrer" ,"/com/efm/crjj/ismo/img/more.png");
		JButton btnNewButton = new JButton("Enregistrer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText()=="" || textField_1.getText()=="") {
					JOptionPane.showMessageDialog(null, "Remplir Tous les champs");
					return;
				}
				Employe em = new Employe();
				em.setId(12);
				em.setNom(textField.getText());
				em.setDepartement(depart[comboBox.getSelectedIndex()]);
				em.setSalaire(Double.valueOf(textField_1.getText()));
				if(emp.save(em)) {
					JOptionPane.showMessageDialog(null, "Ajouter Avec Success");
					load();
				}
				
			}
		});

		ImageIcon image6= new ImageIcon(getClass().getResource("/com/efm/crjj/ismo/img/more.png"));
		btnNewButton.setIcon(image6);
		btnNewButton.setIconTextGap(5);
		btnNewButton.setBounds(10, 185, 222, 23);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Liste des employes", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 251, 616, 322);
		contentPane.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 27, 576, 241);
		panel_1.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new EmployeModel(emp.getAll()));
		
		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index=table.getSelectedRow();
				if(index==-1) {
					JOptionPane.showMessageDialog(null, "Rien");
					return;
				}
				if(JOptionPane.showConfirmDialog(null, "Are you sure")==JOptionPane.YES_OPTION) {
					Employe em= emp.getOne((int)table.getModel().getValueAt(index, 0));
					if(emp.delete(em)) {
						JOptionPane.showMessageDialog(null, "Supprimmer Avec Success");
						load();
					}
				}
			}
		});
		btnNewButton_1.setHorizontalAlignment(SwingConstants.LEFT);
		image6 = new ImageIcon(getClass().getResource("/com/efm/crjj/ismo/img/bin.png"));
		btnNewButton_1.setIcon(image6);
		btnNewButton_1.setIconTextGap(5);
		btnNewButton_1.setBounds(472, 286, 124, 23);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Imprimer");

		image6= new ImageIcon(getClass().getResource("/com/efm/crjj/ismo/img/printer.png"));
		btnNewButton_1_1.setIcon(image6);
		btnNewButton_1_1.setIconTextGap(5);
		btnNewButton_1_1.setBounds(327, 286, 135, 23);
		panel_1.add(btnNewButton_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel_2.setBounds(262, 35, 364, 147);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre des employes ");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 33));
		lblNewLabel_3.setBounds(10, 25, 344, 44);
		panel_2.add(lblNewLabel_3);
		
		lblNewLabel_3_1 = new JLabel("0");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel_3_1.setBounds(10, 80, 344, 44);
		panel_2.add(lblNewLabel_3_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Nombre Employe par departement", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(646, 124, 537, 387);
		contentPane.add(panel_3);
		load();
	}
	public void load() {
		table.setModel(new EmployeModel(emp.getAll()));
		lblNewLabel_3_1.setText(String.valueOf(table.getModel().getRowCount()));
	}
	private static PieDataset createDataset() {
		DefaultPieDataset dataset = new DefaultPieDataset();
		
       for(String d : depart) {
			
			int nb =0;
			for (Employe e : employes)
				if(e.getDepartement().equals(d))
				nb ++;
			
			dataset.setValue(d, nb);
		}
		
		return dataset;
	}

	private static JFreeChart createChart(PieDataset dataset) {
		JFreeChart chart = ChartFactory.createPieChart("Les employes par departement", // chart title
				dataset, // data
				true, // include legend
				true, false);

		return chart;
	}

	public static ChartPanel createPanelDepense() {
		JFreeChart chart = createChart(createDataset());
		ChartPanel panel = new ChartPanel(chart);
		panel.setPreferredSize(new Dimension(500,290));
		return panel;
	}
}
