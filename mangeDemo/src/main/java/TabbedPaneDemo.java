import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
public class TabbedPaneDemo extends JPanel
{
    public static void main(String[] args)
    {
        JFrame frame=new JFrame("日常管理");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TabbedPaneDemo(),BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(300,200,600,600);
    }
    public TabbedPaneDemo()
    {
        super(new CardLayout(1,1));
        JTabbedPane tabbedPane=new JTabbedPane();
        ImageIcon icon=createImageIcon("tab.jp1g");
        JComponent panel1=makeTextPanel("生成报告");

        tabbedPane.addTab("计算机名",icon, panel1);
        tabbedPane.setMnemonicAt(0,KeyEvent.VK_1);
        add(tabbedPane);

    }
    protected JComponent makeTextPanel(String text)
    {
        JPanel panel=new JPanel(false);
        JButton jButton = new JButton("生成");

        JLabel label=new JLabel("测试进度");    //创建标签
        final JTextField txtfield=new JTextField(10);    //创建文本框
        panel.add(label);
        panel.add(txtfield);
        TabbedPaneDemo.registerListner(jButton,txtfield);

        //
        JLabel label1=new JLabel("已解决bug");    //创建标签
        final JTextField txtfield1=new JTextField(10);    //创建文本框
        panel.add(label1);
        panel.add(txtfield1);
        TabbedPaneDemo.registerListner(jButton,txtfield1);

        JLabel label2=new JLabel("未解决bug");
        final JTextField txtfield2=new JTextField(10);
        panel.add(label2);
        panel.add(txtfield2);
        TabbedPaneDemo.registerListner(jButton,txtfield2);

        JLabel label3=new JLabel("相关负责人");    //创建标签
        final JTextField txtfield3=new JTextField(10);    //创建文本框
        panel.add(label3);
        panel.add(txtfield3);
        TabbedPaneDemo.registerListner(jButton,txtfield3);

        JLabel label4=new JLabel("bug列表");    //创建标签
        final JTextField txtfield4=new JTextField(10);    //创建文本框
        panel.add(label4);
        panel.add(txtfield4);
        TabbedPaneDemo.registerListner(jButton,txtfield4);

        panel.add(jButton);

        JTextArea jta=new JTextArea("请输入内容",7,30);
        jta.setLineWrap(true);    //设置文本域中的文本为自动换行
        panel.add(jta);

        return panel;
    }
    class button1ActionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            label.setText("按钮被单击了 "+(clicks++)+" 次");
        }
    }
    protected static ImageIcon createImageIcon(String path)
    {
        java.net.URL imgURL=TabbedPaneDemo.class.getResource(path);
        if(imgURL!=null)
        {
            return new ImageIcon(imgURL);
        }
        else
        {
            System.err.println("Couldn't find file: "+path);
            return null;
        }
    }
}