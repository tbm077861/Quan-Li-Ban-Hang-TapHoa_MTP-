
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import connect.ConnectDB;

public class FrameThongKeKhachHang extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JDateChooser dateChooserFrom, dateChooserTo;
    private JPanel chartPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FrameThongKeKhachHang frame = new FrameThongKeKhachHang();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrameThongKeKhachHang() {
        setTitle("Thống kê khách hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        initComponents();

        setLocationRelativeTo(null);
    }

    private void initComponents() {
        contentPane.setLayout(null);

        // Title
        JLabel title = new JLabel("Thống kê khách hàng", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(278, 10, 580, 30);
        contentPane.add(title);

        // Date panel and button
        dateChooserFrom = new JDateChooser();
        dateChooserFrom.setBounds(10, 82, 208, 20);
        contentPane.add(dateChooserFrom);

        dateChooserTo = new JDateChooser();
        dateChooserTo.setBounds(10, 113, 208, 20);
        contentPane.add(dateChooserTo);

        JButton btnThongKe = new JButton("Thống kê");
        btnThongKe.setBounds(38, 162, 120, 38);
        btnThongKe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                capNhatThongKe();
            }
        });
        contentPane.add(btnThongKe);

        // Chart panel
        chartPanel = new JPanel();
        chartPanel.setBorder(BorderFactory.createTitledBorder("Thống kê lượt mua"));
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBounds(220, 50, 878, 531);
        contentPane.add(chartPanel);

        // Set kích thước cho JFrame
        setSize(1185, 643);
        setPreferredSize(new Dimension(620, 370));
        setMinimumSize(new Dimension(620, 370));
        revalidate();
        repaint();
    }


	private void capNhatThongKe() {
	    String fromDate = ((JTextField) dateChooserFrom.getDateEditor().getUiComponent()).getText();
	    String toDate = ((JTextField) dateChooserTo.getDateEditor().getUiComponent()).getText();
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat displayFormat = new SimpleDateFormat("dd/MM/yyyy");
	
	    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	
	    try {
	        Connection connection = ConnectDB.getConnection("DB_QLBH");
	        Statement statement = connection.createStatement();
	        ResultSet resultSet = null;
	
	        String query = "SELECT HoaDon.NgayLap, COUNT(HoaDon.MaHD) AS LuotMua " +
	                       "FROM HoaDon " +
	                       "WHERE HoaDon.NgayLap BETWEEN '" + fromDate + "' AND '" + toDate + "' " +
	                       "GROUP BY HoaDon.NgayLap";
	        resultSet = statement.executeQuery(query);
	
	        while (resultSet.next()) {
	            int luotMua = resultSet.getInt("LuotMua");
	            Date ngayLap = dateFormat.parse(resultSet.getString("NgayLap"));
	            dataset.addValue(luotMua, "Lượt mua", displayFormat.format(ngayLap));
	        }
	
	        if (dataset.getRowCount() == 0) {
	            JOptionPane.showMessageDialog(this, "No data found for the selected date range.", "No Data", JOptionPane.INFORMATION_MESSAGE);
	        }
	
	        JFreeChart barChart = ChartFactory.createBarChart(
	            "Thống kê lượt mua",
	            "Ngày",
	            "Lượt mua",
	            dataset,
	            PlotOrientation.VERTICAL,
	            true, true, false);
	
	        ChartPanel chartPanelContainer = new ChartPanel(barChart);
	        chartPanelContainer.setPreferredSize(new Dimension(878, 531));
	        chartPanel.removeAll();
	        chartPanel.add(chartPanelContainer);
	        chartPanel.revalidate();
	        chartPanel.repaint();
	
	        resultSet.close();
	        statement.close();
	        connection.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
}

}
