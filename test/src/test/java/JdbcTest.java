import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by ligq01 on 2016/11/4.
*/
public class JdbcTest {
	public static void main(String[] args){
		ApplicationContext con = new FileSystemXmlApplicationContext("src/main/resources/spring-mybatis.xml");
		DataSource dataSource = (DataSource)con.getBean("dataSource");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("insert into pet values('ligq','qxq','man','f',sysdate,'')");
	}
}
