/**
 * @author Stepan Pijacek(223313)
 **/
package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static volatile Connection dbConnection;

    public DBConnection(){

    }

    public static Connection getDbConnection(){
        if(dbConnection == null){
            synchronized (DBConnection.class){
                if(dbConnection == null){
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        dbConnection = DriverManager.getConnection("jdbc:mysql://localhost/projektpc2t?user=root&password=");
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return dbConnection;
    }

    public static void closeConnection(){
        try{
            dbConnection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
