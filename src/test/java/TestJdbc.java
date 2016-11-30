import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by ligq01 on 2016/11/3.
 */
public class TestJdbc {
	public static void main(String[] args){
		String driverClass = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@//localhost:1521/XE";
		String name = "study";
		String passwd = "study";

		try {
			Class.forName(driverClass);
			Connection con = DriverManager.getConnection(url,name,passwd);
			Statement state = con.createStatement();
			state.execute("insert into pet values('ligq','qxq','man','f',sysdate,'')");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
