import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Enumeration;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class UserInformation
{
    private JFrame mainFrame = new JFrame("用户信息");
    private Container container = mainFrame.getContentPane();
    private JLabel titleLabel = new JLabel("用户信息", JLabel.CENTER);

    private JPanel inputField = new JPanel();
    private JLabel idLabel = new JLabel("Id",JLabel.CENTER);
    private JTextField id = new JTextField();
    private JLabel usernameLabel = new JLabel("Username", JLabel.CENTER);
    private JTextField username = new JTextField();
    private JLabel passwordLabel = new JLabel("Password", JLabel.CENTER);
    private JPasswordField password = new JPasswordField();

    private JPanel buttonField = new JPanel();
    private JButton update = new JButton("更新");
    private User user;

    public UserInformation(User user)
    {
        if(user == null)
            mainFrame.dispose();
        this.user = user;
        init();
        setFont(new Font("微软雅黑", Font.PLAIN, 14));
        addEvent();
    }

    private void init()
    {
        container.setLayout(new GridLayout(3,1,0,10));
        container.add(titleLabel);

        inputField.setLayout(new GridLayout(3,2,0,3));
        inputField.add(idLabel);
        inputField.add(id);
        id.setText(user.getId());
        id.setEditable(false);
        inputField.add(usernameLabel);
        username.setText(user.getName());
        inputField.add(username);
        inputField.add(passwordLabel);
        password.setText(user.getPassword());
        inputField.add(password);
        container.add(inputField);

        buttonField.setLayout(new FlowLayout());
        buttonField.add(update);
        container.add(buttonField);

        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setSize(300,250);
    }

    private void setFont(Font font)
    {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();)
        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
    }

    private void addEvent()
    {
        update.addActionListener(
            e->
            {
                user.setName(username.getText());
                user.setPassword(new String(password.getPassword()));
                JOptionPane.showConfirmDialog(null, "更新"+(DBUtils.modify(user) ? "成功" : "失败"),"确认",JOptionPane.CLOSED_OPTION);
            }
        );
    }
}