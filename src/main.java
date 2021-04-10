import dbConnect.DBConnection;

import java.sql.Connection;

public class main {
    public static void main(String[] args){
        Connection conn = DBConnection.getDbConnection();
        System.out.println(conn);
    }
}
