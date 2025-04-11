package data;
import java.sql.*;


public class DBConnect 
{
    public static Connection getConnect() throws Exception
    {
        try
        {
            Class.forName("org.postgresql.Driver");
            Connection connect=DriverManager.getConnection("jdbc:postgresql://localhost:5432/trip","admin","test");
            return connect;
        }
        catch(Exception e)
        {
            System.out.println(e);
            throw e;
        }
    }
}
