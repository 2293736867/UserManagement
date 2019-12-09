import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class DBUtils
{
    private final static String driver = "com.mysql.cj.jdbc.Driver";
    private final static String username = "aa";
    private final static String password = "123456";
    private final static String url = "jdbc:mysql://127.0.0.1/user_test";
    private final static String insert = "insert into user(id,username,password) values(?,?,?)";
    private final static String update = "update user set username = ?,password = ? where id = ?";
    private final static String delete = "delete from user where id = ?";
    private final static String select = "select * from user where username = ?";

    private static Connection connection;

    static
    {
        try
        {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,username,password);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            connection = null;
        }
    }

    public static boolean exists(User user)
    {
        try
        {
            PreparedStatement exist = connection.prepareStatement(select);
            exist.setString(1,user.getName());
            ResultSet existResult = exist.executeQuery();
            return existResult.next();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean add(User user)
    {
        try
        {
            PreparedStatement add = connection.prepareStatement(insert);
            user.setId(UUID.randomUUID().toString().substring(0, 8));
            add.setString(1, user.getId());
            add.setString(2, user.getName());
            add.setString(3, user.getPassword());
            return add.executeUpdate() == 1;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean modify(User user)
    {
        try
        {
            PreparedStatement modify = connection.prepareStatement(update);
            System.out.println(user.getName());
            modify.setString(1, user.getName());
            modify.setString(2, user.getPassword());
            modify.setString(3, user.getId());
            return modify.executeUpdate() == 1;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(User user)
    {
        if(exists(user))
        {
            try
            {
                PreparedStatement del = connection.prepareStatement(delete);
                del.setString(1, user.getId());
                return del.executeUpdate() == 1;
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static User getByName(String name)
    {
        try
        {
            PreparedStatement exist = connection.prepareStatement(select);
            exist.setString(1, name);
            ResultSet existResult = exist.executeQuery();
            if(existResult.next())
            {
                User user = new User();
                user.setId(existResult.getString("id"));
                user.setName(existResult.getString("username"));
                user.setPassword(existResult.getString("password"));
                return user;
            }
            return null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}