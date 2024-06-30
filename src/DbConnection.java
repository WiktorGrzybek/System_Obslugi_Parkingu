import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbConnection
{
    private  Statement statement;

    public void Connect(){
        String url="jdbc:mysql://localhost:3306/parking";
        String username="root";
        String password="";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            statement  = connection.createStatement();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public void AddLog(String rejestracja, String typ, String rzad, boolean czyPrzyjechal){
        try {
            var sql = "INSERT INTO logi (Rzad, Rejestracja, Przyjechal, Typ) VALUES ('" + rzad +"', '" + rejestracja +"', " + czyPrzyjechal +", '"+typ+"');";
            statement.execute(sql);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public String LoadLogs(){
        try {
            var sql = "Select * From logi";
            var queryResult = statement.executeQuery(sql);

            var sb = new StringBuilder();

            while (queryResult.next()){
                var rodzaj = queryResult.getInt(3) == 0 ? "Odjechal" : "Przyjechal";
                sb.append("Nr rej. ").append(queryResult.getString(1)).append(" Typ: ")
                        .append(queryResult.getString(2)).append(" Rodzaj: ").append(rodzaj).append(" Rzad: ").append(queryResult.getString(4));
                sb.append('\n');
            }

            return  sb.toString();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return "";
    }

    public String LoadLogs(String tablica){
        try {
            var sql = "Select * From logi WHERE Rejestracja = \"" + tablica + "\"";
            var queryResult = statement.executeQuery(sql);

            var sb = new StringBuilder();

            while (queryResult.next()){
                var rodzaj = queryResult.getInt(3) == 0 ? "Odjechal" : "Przyjechal";
                sb.append("Nr rej. ").append(queryResult.getString(1)).append(" Typ: ")
                        .append(queryResult.getString(2)).append(" Rodzaj: ").append(rodzaj).append(queryResult.getString(4));
                sb.append('\n');
            }

            return  sb.toString();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return "";
    }
}
