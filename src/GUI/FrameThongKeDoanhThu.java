
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.toedter.calendar.JDateChooser;

import connectDB.*;

public class FrameThongKeDoanhThu extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> cbLoaiThongKe;
    private JDateChooser dateChooserFrom, dateChooserTo;
    private JComboBox<String> cbMonthFrom, cbMonthTo, cbYear, cbYearForQuarter;
    private JPanel chartPanel;
    private JLabel lblnngy;

//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                FrameThongKeDoanhThu frame = new FrameThongKeDoanhThu();
//                frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }

    public FrameThongKeDoanhThu() {
        setLayout(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(254, 222, 192));
        contentPane.setBounds(0, 0, 1543, 794);
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(contentPane);

        initComponents();

    }

    private void initComponents() {
        contentPane.setLayout(null);

        // Title
        JLabel title = new JLabel("Thống kê doanh thu", JLabel.CENTER);
        title.setFont(new Font("Times New Roman", Font.BOLD, 25));
        title.setBounds(771, 10, 580, 30);
        contentPane.add(title);

        // Control panel components
        JLabel lblLoaiThongKe = new JLabel("Lựa chọn loại thống kê");
        lblLoaiThongKe.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblLoaiThongKe.setBounds(118, 6, 343, 39);
        contentPane.add(lblLoaiThongKe);

        cbLoaiThongKe = new JComboBox<>(new String[]{
            "Thống kê doanh thu theo ngày",
            "Thống kê doanh thu theo tháng",
            "Thống kê doanh thu theo quý"
        });
        cbLoaiThongKe.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        cbLoaiThongKe.setBounds(32, 80, 433, 39);
        cbLoaiThongKe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateChartTitle();
                updateControlVisibility();
            }
        });
        contentPane.add(cbLoaiThongKe);

        // Date panel and button
        dateChooserFrom = new JDateChooser();
        dateChooserFrom.setBounds(28, 190, 433, 39);
        contentPane.add(dateChooserFrom);

        dateChooserTo = new JDateChooser();
        dateChooserTo.setBounds(32, 283, 433, 39);
        contentPane.add(dateChooserTo);

        cbMonthFrom = new JComboBox<>(getMonths());
        cbMonthFrom.setBounds(32, 382, 181, 39);
        contentPane.add(cbMonthFrom);

        cbMonthTo = new JComboBox<>(getMonths());
        cbMonthTo.setBounds(284, 382, 181, 39);
        contentPane.add(cbMonthTo);

        cbYear = new JComboBox<>(getYears());
        cbYear.setBounds(32, 478, 181, 39);
        contentPane.add(cbYear);

        cbYearForQuarter = new JComboBox<>(getYears());
        cbYearForQuarter.setBounds(32, 572, 181, 39);
        contentPane.add(cbYearForQuarter);

        JButton btnThongKe = new JButton("Thống kê");
        btnThongKe.setFont(new Font("Times New Roman", Font.BOLD, 25));
        btnThongKe.setBounds(181, 657, 162, 44);
        btnThongKe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                capNhatThongKe();
            }
        });
        contentPane.add(btnThongKe);

        // Chart panel
        chartPanel = new JPanel();
        chartPanel.setBorder(BorderFactory.createTitledBorder("Thống kê doanh thu"));
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBounds(542, 80, 991, 691);
        contentPane.add(chartPanel);
        
        JLabel lblNewLabel = new JLabel("Khoảng ngày:");
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblNewLabel.setBounds(32, 150, 181, 30);
        contentPane.add(lblNewLabel);
        
        lblnngy = new JLabel("Đến ngày:");
        lblnngy.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        lblnngy.setBounds(32, 243, 181, 30);
        contentPane.add(lblnngy);

        // Set kích thước cho JFrame
        setSize(1171, 762);
        setPreferredSize(new Dimension(1544, 794));
        setMinimumSize(new Dimension(620, 370));
        revalidate();
        repaint();

        updateControlVisibility();
    }

    private void updateChartTitle() {
        String loaiThongKe = (String) cbLoaiThongKe.getSelectedItem();
        chartPanel.setBorder(BorderFactory.createTitledBorder(loaiThongKe));
        chartPanel.revalidate();
        chartPanel.repaint();
    }

    private void updateControlVisibility() {
        String loaiThongKe = (String) cbLoaiThongKe.getSelectedItem();
        boolean isDay = loaiThongKe.equals("Thống kê doanh thu theo ngày");
        boolean isMonth = loaiThongKe.equals("Thống kê doanh thu theo tháng");
        boolean isQuarter = loaiThongKe.equals("Thống kê doanh thu theo quý");

        dateChooserFrom.setVisible(isDay);
        dateChooserTo.setVisible(isDay);
        cbMonthFrom.setVisible(isMonth);
        cbMonthTo.setVisible(isMonth);
        cbYear.setVisible(isMonth);
        cbYearForQuarter.setVisible(isQuarter);
    }







private void capNhatThongKe() {
    String loaiThongKe = (String) cbLoaiThongKe.getSelectedItem();
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat displayFormat = new SimpleDateFormat("dd/MM/yyyy");

    try {
        Connection connection = ConnectDB.getConnection("DB_QLBH");
        Statement statement = connection.createStatement();
        ResultSet resultSet = null;

        if (loaiThongKe.equals("Thống kê doanh thu theo ngày")) {
            String fromDate = dateChooserFrom.getDate() != null ? dateFormat.format(dateChooserFrom.getDate()) : "";
            String toDate = dateChooserTo.getDate() != null ? dateFormat.format(dateChooserTo.getDate()) : "";

            String query = "SELECT HoaDon.NgayLap, SUM(ChiTietHD.SoLuongSanPham * ChiTietHD.DonGia) AS DoanhThu " +
                           "FROM ChiTietHD " +
                           "JOIN HoaDon ON ChiTietHD.MaHD = HoaDon.MaHD " +
                           "WHERE HoaDon.NgayLap BETWEEN '" + fromDate + "' AND '" + toDate + "' " +
                           "GROUP BY HoaDon.NgayLap";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int doanhThu = resultSet.getInt("DoanhThu");
                Date ngayLap = resultSet.getDate("NgayLap");
                dataset.addValue((Number) doanhThu, "Doanh thu", displayFormat.format(ngayLap));
            }

        } else if (loaiThongKe.equals("Thống kê doanh thu theo tháng")) {
            String monthFrom = (String) cbMonthFrom.getSelectedItem();
            String monthTo = (String) cbMonthTo.getSelectedItem();
            String year = (String) cbYear.getSelectedItem();
            String query = "SELECT MONTH(HoaDon.NgayLap) AS Thang, SUM(ChiTietHD.SoLuongSanPham * ChiTietHD.DonGia) AS DoanhThu " +
                           "FROM ChiTietHD " +
                           "JOIN HoaDon ON ChiTietHD.MaHD = HoaDon.MaHD " +
                           "WHERE YEAR(HoaDon.NgayLap) = '" + year + "' AND MONTH(HoaDon.NgayLap) BETWEEN '" + monthFrom + "' AND '" + monthTo + "' " +
                           "GROUP BY MONTH(HoaDon.NgayLap)";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int thang = resultSet.getInt("Thang");
                int doanhThu = resultSet.getInt("DoanhThu");
                dataset.addValue((Number) doanhThu, "Doanh thu", thang);
            }

        } else if (loaiThongKe.equals("Thống kê doanh thu theo quý")) {
            String year = (String) cbYearForQuarter.getSelectedItem();
            String query = "SELECT CASE " +
                           "WHEN DATEPART(MONTH, HoaDon.NgayLap) IN (1, 2, 3) THEN 1 " +
                           "WHEN DATEPART(MONTH, HoaDon.NgayLap) IN (4, 5, 6) THEN 2 " +
                           "WHEN DATEPART(MONTH, HoaDon.NgayLap) IN (7, 8, 9) THEN 3 " +
                           "ELSE 4 END AS Quy, " +
                           "SUM(ChiTietHD.SoLuongSanPham * ChiTietHD.DonGia) AS DoanhThu " +
                           "FROM ChiTietHD " +
                           "JOIN HoaDon ON ChiTietHD.MaHD = HoaDon.MaHD " +
                           "WHERE YEAR(HoaDon.NgayLap) = '" + year + "' " +
                           "GROUP BY CASE " +
                           "WHEN DATEPART(MONTH, HoaDon.NgayLap) IN (1, 2, 3) THEN 1 " +
                           "WHEN DATEPART(MONTH, HoaDon.NgayLap) IN (4, 5, 6) THEN 2 " +
                           "WHEN DATEPART(MONTH, HoaDon.NgayLap) IN (7, 8, 9) THEN 3 " +
                           "ELSE 4 END";
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int quy = resultSet.getInt("Quy");
                int doanhThu = resultSet.getInt("DoanhThu");
                dataset.addValue((Number) doanhThu, "Doanh thu", "Quý " + quy);
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                loaiThongKe,
                loaiThongKe.equals("Thống kê doanh thu theo ngày") ? "Ngày" :
                loaiThongKe.equals("Thống kê doanh thu theo tháng") ? "Tháng" : "Quý",
                "Doanh thu",
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

    private String[] getMonths() {
        String[] months = new String[12];
        for (int i = 1; i <= 12; i++) {
            months[i - 1] = String.valueOf(i);
        }
        return months;
    }

    private String[] getYears() {
        String[] years = new String[10];
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        for (int i = 0; i < 10; i++) {
            years[i] = String.valueOf(currentYear - i);
        }
        return years;
    }
}
