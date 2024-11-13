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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import connectDB.*;

public class FrameThongKeSanPham extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JDateChooser dateChooserFrom, dateChooserTo;
    private JPanel chartPanel;
    private JLabel lblNewLabel;
    private JLabel lblnNgy;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FrameThongKeSanPham frame = new FrameThongKeSanPham();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public FrameThongKeSanPham() {
    	setLayout(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(254, 222, 192));
        contentPane.setBounds(0, 0, 1542, 767);
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(contentPane);

        initComponents();

    }

    private void initComponents() {
        contentPane.setLayout(null);

        // Title
        JLabel title = new JLabel("Thống kê sản phẩm", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setBounds(754, 10, 580, 30);
        contentPane.add(title);

        // Date panel and button
        dateChooserFrom = new JDateChooser();
        dateChooserFrom.setBounds(46, 122, 259, 37);
        contentPane.add(dateChooserFrom);

        dateChooserTo = new JDateChooser();
        dateChooserTo.setBounds(46, 260, 259, 37);
        contentPane.add(dateChooserTo);

        JButton btnThongKe = new JButton("Thống kê");
        btnThongKe.setFont(new Font("Times New Roman", Font.BOLD, 25));
        btnThongKe.setBounds(83, 331, 175, 55);
        btnThongKe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                capNhatThongKe();
            }
        });
        contentPane.add(btnThongKe);

        // Chart panel
        chartPanel = new JPanel();
        chartPanel.setBorder(BorderFactory.createTitledBorder("Thống kê lượt mua sản phẩm"));
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBounds(366, 50, 1154, 696);
        contentPane.add(chartPanel);
        
        lblNewLabel = new JLabel("Từ Ngày");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblNewLabel.setBounds(46, 68, 150, 47);
        contentPane.add(lblNewLabel);
        
        lblnNgy = new JLabel("Đến Ngày");
        lblnNgy.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblnNgy.setBounds(46, 203, 150, 47);
        contentPane.add(lblnNgy);

        // Set kích thước cho JFrame
        setSize(1176, 640);
        setPreferredSize(new Dimension(1535, 768));
        setMinimumSize(new Dimension(620, 370));
        revalidate();
        repaint();
    }

    private void capNhatThongKe() {
        String fromDate = ((JTextField) dateChooserFrom.getDateEditor().getUiComponent()).getText();
        String toDate = ((JTextField) dateChooserTo.getDateEditor().getUiComponent()).getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            Connection connection = ConnectDB.getConnection("DB_QLBH");
            Statement statement = connection.createStatement();
            ResultSet resultSet = null;

            String query = "SELECT DanhSachSanPham.TenHang, SUM(ChiTietHD.SoLuongSanPham) AS TongSoLuong " +
                           "FROM ChiTietHD " +
                           "JOIN DanhSachSanPham ON ChiTietHD.MaHang = DanhSachSanPham.MaHang " +
                           "JOIN HoaDon ON ChiTietHD.MaHD = HoaDon.MaHD " +
                           "WHERE HoaDon.NgayLap BETWEEN '" + fromDate + "' AND '" + toDate + "' " +
                           "GROUP BY DanhSachSanPham.TenHang";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String tenHang = resultSet.getString("TenHang");
                int tongSoLuong = resultSet.getInt("TongSoLuong");
                dataset.addValue(tongSoLuong, "Số lượng", tenHang);
            }

            JFreeChart barChart = ChartFactory.createBarChart(
                    "Thống kê sản phẩm",
                    "Sản phẩm",
                    "Số lượng",
                    dataset,
                    PlotOrientation.VERTICAL,
                    false, true, false);

            ChartPanel chartPanelContainer = new ChartPanel(barChart);
            chartPanelContainer.setPreferredSize(new Dimension(800, 400));
            chartPanel.removeAll();
            chartPanel.add(chartPanelContainer);
            chartPanel.revalidate();
            chartPanel.repaint();

            ConnectDB.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
