
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class FrameThongKeDoanhThu extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> cbLoaiThongKe;
    private JDateChooser dateChooserFrom, dateChooserTo;
    private JLabel lblTongSanPham, lblTongKhachHang, lblTongNhanVien;
    private JPanel chartPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	FrameThongKeDoanhThu frame = new FrameThongKeDoanhThu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrameThongKeDoanhThu() {
    	setLayout(null);
    	
        contentPane = new JPanel();
        contentPane.setBounds(10, 10, 1542, 767);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(contentPane);

        initComponents();
    }

    private void initComponents() {
        contentPane.setLayout(null);

        // Title
        JLabel title = new JLabel("Thống kê doanh số", JLabel.CENTER);
        title.setFont(new Font("Times New Roman", Font.BOLD, 30));
        title.setBounds(754, 0, 603, 42);
        contentPane.add(title);

        // Control panel components
        JLabel lblLoaiThongKe = new JLabel("Lựa chọn loại thống kê");
        lblLoaiThongKe.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblLoaiThongKe.setBounds(10, 53, 376, 44);
        contentPane.add(lblLoaiThongKe);

        cbLoaiThongKe = new JComboBox<>(new String[]{
            "Thống kê doanh số 8 ngày gần nhất",
            "Thống kê số lượng sản phẩm bán ra",
            "Thống kê sản phẩm do nhân viên bán ra"
        });
        cbLoaiThongKe.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cbLoaiThongKe.setBounds(10, 133, 451, 39);
        cbLoaiThongKe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateChartTitle();
            }
        });
        contentPane.add(cbLoaiThongKe);

        // Date panel and button
        dateChooserFrom = new JDateChooser();
        dateChooserFrom.setBounds(10, 300, 200, 39);
        contentPane.add(dateChooserFrom);

        dateChooserTo = new JDateChooser();
        dateChooserTo.setBounds(261, 300, 200, 39);
        contentPane.add(dateChooserTo);

        JButton btnThongKe = new JButton("Hiển thị");
        btnThongKe.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnThongKe.setBounds(172, 380, 139, 39);
        btnThongKe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                capNhatThongKe();
            }
        });
        contentPane.add(btnThongKe);

        // Labels for summary
        lblTongSanPham = new JLabel("Tổng sản phẩm: 0");
        lblTongSanPham.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        lblTongSanPham.setBounds(59, 446, 252, 66);
        contentPane.add(lblTongSanPham);

        lblTongKhachHang = new JLabel("Tổng khách hàng: 0");
        lblTongKhachHang.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        lblTongKhachHang.setBounds(59, 522, 252, 77);
        contentPane.add(lblTongKhachHang);

        lblTongNhanVien = new JLabel("Tổng nhân viên: 0");
        lblTongNhanVien.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        lblTongNhanVien.setBounds(59, 620, 252, 56);
        contentPane.add(lblTongNhanVien);

        // Chart panel
        chartPanel = new JPanel();
        chartPanel.setBorder(BorderFactory.createTitledBorder("Thống kê doanh thu"));
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBounds(500, 53, 996, 676);
        contentPane.add(chartPanel);
        
        JLabel lblNgayThongKe = new JLabel("Ngày thống kê");
        lblNgayThongKe.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblNgayThongKe.setBounds(10, 222, 200, 39);
        contentPane.add(lblNgayThongKe);

        // Set kích thước cho JFrame
        setSize(998, 604);
        setPreferredSize(new Dimension(1528, 759));
        setMinimumSize(new Dimension(620, 370));
        revalidate();
        repaint();
    }

    private void updateChartTitle() {
        String loaiThongKe = (String) cbLoaiThongKe.getSelectedItem();
        chartPanel.setBorder(BorderFactory.createTitledBorder(loaiThongKe));
        chartPanel.revalidate();
        chartPanel.repaint();
    }

    private void capNhatThongKe() {
        String loaiThongKe = (String) cbLoaiThongKe.getSelectedItem();
        String fromDate = ((JTextField) dateChooserFrom.getDateEditor().getUiComponent()).getText();
        String toDate = ((JTextField) dateChooserTo.getDateEditor().getUiComponent()).getText();

        // Update chart based on selected options
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (loaiThongKe.equals("Thống kê doanh số 8 ngày gần nhất")) {
            dataset.addValue(100, "Doanh thu", "Day 1");
            dataset.addValue(150, "Doanh thu", "Day 2");
            dataset.addValue(200, "Doanh thu", "Day 3");
            dataset.addValue(250, "Doanh thu", "Day 4");
            dataset.addValue(300, "Doanh thu", "Day 5");
            dataset.addValue(350, "Doanh thu", "Day 6");
            dataset.addValue(400, "Doanh thu", "Day 7");
            dataset.addValue(450, "Doanh thu", "Day 8");
        } else if (loaiThongKe.equals("Thống kê số lượng sản phẩm bán ra")) {
            // Add data for product sales statistics
        } else if (loaiThongKe.equals("Thống kê sản phẩm do nhân viên bán ra")) {
            // Add data for employee sales statistics
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                loaiThongKe,
                "Ngày",
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
    }
}
