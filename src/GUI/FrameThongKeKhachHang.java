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

import connectDB.*;

public class FrameThongKeKhachHang extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JDateChooser dateChooserFrom, dateChooserTo;
    private JPanel chartPanel;
    private JLabel lblnNgy;

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
        setLayout(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(254, 222, 192));
        contentPane.setBounds(0, 0, 1542, 771);
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(contentPane);
        initComponents();
    }

    private void initComponents() {
        contentPane.setLayout(null);

        // Title
        JLabel title = new JLabel("Thống kê khách hàng", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(691, 10, 580, 30);
        contentPane.add(title);

        // Date panel and button
        dateChooserFrom = new JDateChooser();
        dateChooserFrom.setBounds(52, 127, 330, 38);
        contentPane.add(dateChooserFrom);

        dateChooserTo = new JDateChooser();
        dateChooserTo.setBounds(52, 270, 330, 38);
        contentPane.add(dateChooserTo);

        JButton btnThongKe = new JButton("Thống kê");
        btnThongKe.setFont(new Font("Times New Roman", Font.BOLD, 25));
        btnThongKe.setBounds(117, 360, 187, 61);
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
        chartPanel.setBounds(409, 50, 1098, 691);
        contentPane.add(chartPanel);
        
        JLabel lblNewLabel = new JLabel("Từ Ngày");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblNewLabel.setBounds(52, 61, 125, 55);
        contentPane.add(lblNewLabel);
        
        lblnNgy = new JLabel("Đến Ngày");
        lblnNgy.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblnNgy.setBounds(52, 205, 125, 55);
        contentPane.add(lblnNgy);

        // Set kích thước cho JFrame
//        setSize(1185, 643);
//        setPreferredSize(new Dimension(620, 370));
//        setMinimumSize(new Dimension(620, 370));
//        revalidate();
//        repaint();
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
