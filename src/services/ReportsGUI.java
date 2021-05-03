package services;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ReportsGUI {

	private JFrame frmReports;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportsGUI window = new ReportsGUI();
					window.frmReports.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReportsGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmReports = new JFrame();
		frmReports.setTitle("REPORTS");
		frmReports.setBounds(100, 100, 795, 489);
		frmReports.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmReports.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("average saleries");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					JOptionPane.showMessageDialog(null, "the average is salary is " + Report.getService().getAvgSal());
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.getMessage();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setForeground(Color.GREEN);
		btnNewButton.setBackground(new Color(0, 0, 128));
		btnNewButton.setBounds(10, 23, 135, 57);
		frmReports.getContentPane().add(btnNewButton);
		
		JButton btnNumberOfEmployees = new JButton("number of employees");
		btnNumberOfEmployees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					JOptionPane.showMessageDialog(null, "the number of currently working employees is  " + Report.getService().getNumberOfemployee());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.getMessage();
				}
				
			}
		});
		btnNumberOfEmployees.setBackground(Color.BLUE);
		btnNumberOfEmployees.setForeground(Color.GREEN);
		btnNumberOfEmployees.setBounds(254, 23, 135, 57);
		frmReports.getContentPane().add(btnNumberOfEmployees);
		
		JButton btnEmployeeWithHighest = new JButton("employee with highest salary");
		btnEmployeeWithHighest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(null, "the employees with highest salaries are  " + Report.getService().getMaxSalary());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.getMessage();
				}
			}
		});
		btnEmployeeWithHighest.setBackground(Color.BLUE);
		btnEmployeeWithHighest.setForeground(Color.GREEN);
		btnEmployeeWithHighest.setBounds(10, 146, 171, 57);
		frmReports.getContentPane().add(btnEmployeeWithHighest);
		
		JButton btnExpiredDrugs = new JButton("expired drugs");
		btnExpiredDrugs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(null, " " + Report.getService().getExpiredDr());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.getMessage();
				}
			}
		});
		btnExpiredDrugs.setBackground(Color.BLUE);
		btnExpiredDrugs.setForeground(Color.GREEN);
		btnExpiredDrugs.setBounds(465, 23, 120, 57);
		frmReports.getContentPane().add(btnExpiredDrugs);
		
		JButton btnTotalProfitFrom = new JButton("total drugs profit");
		btnTotalProfitFrom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					JOptionPane.showMessageDialog(null, "the total drug proftis is equal to   " + Report.getService().getProfit());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.getMessage();
				}
			}
			
		});
		btnTotalProfitFrom.setBackground(Color.BLUE);
		btnTotalProfitFrom.setForeground(Color.GREEN);
		btnTotalProfitFrom.setBounds(254, 146, 135, 57);
		frmReports.getContentPane().add(btnTotalProfitFrom);
		
		JButton btnClose = new JButton("close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmReports.setVisible(false);
				
			}
		});
		btnClose.setForeground(Color.RED);
		btnClose.setBounds(522, 341, 108, 57);
		frmReports.getContentPane().add(btnClose);
	}
}

