package Modal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectDB {
    public Connection conn = null;
    
    //Function Nhận đầu vào: URL, userName(sa), password(123456)
    //URL: connection string: address, port, database of server
    public ConnectDB(String URL, String userName, String password) {
        try {
            //Call driver: Khởi tạo JDPC: Thêm JDBC vào libraries
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try {
                //Connection driver
                conn = DriverManager.getConnection(URL, userName, password);
                System.out.println("Connected");//In ra để check connected
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public ConnectDB() {
        this("jdbc:sqlserver://localhost:1433;databaseName=FoodOrderOnline", "sa", "123456");
    }
    //Khởi tạo và chạy ResultSet
    public ResultSet getData(String sql){
        ResultSet rs = null;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = state.executeQuery(sql);//Dùng cho select
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return rs;
    }
    public static void main(String[] args) {
        new ConnectDB();
    }
}
