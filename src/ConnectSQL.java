import java.sql.*;

public class ConnectSQL {
    private ResultSet result;

    public ConnectSQL() {
    }
    private static Connection getConnection() throws SQLException {
        String username = "postgres";
        String password = "0903302271";
        String URL = "jdbc:postgresql://localhost:5432/postgres";
        return DriverManager.getConnection(URL, username, password);
    }

    public Statement getStatement() {
        try {
            Connection connection = getConnection();
            return connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getResult(String sql) {
        try {
            Statement statement = getStatement();
            result = statement.executeQuery(sql);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getLength(String sql) {
        try {
            result = getResult(sql);
            if (result.last())
                return result.getRow();
            else return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void disconect()
    {
        try {
            result.close();
            getStatement().close();
            getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
