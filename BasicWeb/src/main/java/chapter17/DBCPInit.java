package chapter17;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;

import javax.management.RuntimeErrorException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

@WebServlet(loadOnStartup = 1, 
	initParams = {
			@WebInitParam(name="jdbcdriver", value = "oracle.jdbc.OracleDriver"),
			@WebInitParam(name="url", value = "jdbc:oracle:thin:@nextit.or.kr:1521:xe"),
			@WebInitParam(name="username", value = "std115"),
			@WebInitParam(name="password", value = "oracle21c"),
			@WebInitParam(name="poolName", value = "chapter17"),
			}
)
public class DBCPInit extends HttpServlet{
	
//	드라이브 로딩, 접속을 모든 서블릿에 중복 작성하지 않기 위해 DBCPInit이라는 새로운 클래스에 분리하는 작업 
// DBCP DataBase Connectivity Pool
	
	@Override
	public void init() throws ServletException{
		System.out.println("JDBC 드라이버 로딩중 . . .");
		loadJDBCDriver();
		System.out.println("Connection Pool 생성중. . .");
		initConnectionPool();
		System.out.println("Connection Pool 생성 완료");
	}
	
	/**
	 * JDBC Driver를 로딩하는 메소드 (loadJDBCDriver)
	 */
	private void loadJDBCDriver() {
		String driverClass = getInitParameter("jdbcdriver");
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("fail to load JDBC Driver", e);
		}
	}
	/**
	 * 커넥션풀을 이용해서 데이터베이스 연결하는 메소드 (initConnectionPool)
	 */
	private void initConnectionPool(){
			String connectionUri = getInitParameter("url");
			String userName = getInitParameter("username");
			String userPassword = getInitParameter("password");
			String poolName = getInitParameter("poolName");
		try {
			DriverManagerConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectionUri,userName,userPassword);
			PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
			
			poolableConnectionFactory.setValidationQuery("select 1 from dual");
			
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRuns(Duration.ofMillis(1000L * 60L * 5L));
			poolConfig.setTestWhileIdle(true);
			poolConfig.setMinIdle(4);
			poolConfig.setMaxTotal(50);
			
			GenericObjectPool connectionPool = new GenericObjectPool(poolableConnectionFactory, poolConfig);
			poolableConnectionFactory.setPool(connectionPool);
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			driver.registerPool(poolName, (ObjectPool<? extends Connection>) connectionPool);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
	}
}
