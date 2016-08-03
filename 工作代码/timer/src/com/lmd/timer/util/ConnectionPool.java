package com.lmd.timer.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.Vector;

public class ConnectionPool {
    private Vector<Connection> pool;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private int poolSize = 1;
    private static ConnectionPool instance = null;
    
    //私有构造方法，禁止外部创建本类的对象，要想获得本类的对象，通过<code>getInstance</code>方法
    private ConnectionPool(){
//        System.out.println("构造函数");
        init();
    }
    
    //连接池初始化方法，读取属性文件的内容，建立连接池中的初始连接
    private void init(){
        readConfig();
        pool = new Vector<Connection>(poolSize);
        addConnection();
    }
    
    //返回连接到连接池中
    public synchronized void release(Connection coon){
        pool.add(coon);
    }
    
    //关闭连接池中的所有数据库连接
    public synchronized void closePool(){
        for (int i = 0; i < pool.size(); i++) {
            try {
                ((Connection)pool.get(i)).close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            pool.remove(i);
        }
    }
    
    //返回当前连接池的一个对象
    public static ConnectionPool getInstance(){
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }
    
    //返回连接池中的一个数据库连接
    public synchronized Connection getConnection(){
        if (pool.size() > 0) {
            Connection conn = pool.get(0);
            pool.remove(conn);
            return conn;
        }else {
            return null;
        }
    }
    
    //在连接池中创建初始设置的数据库连接
    private void addConnection(){
        Connection coon = null;
        for (int i = 0; i < poolSize; i++) {
            try {
                Class.forName(driverClassName);
                coon = java.sql.DriverManager.getConnection(url, username, password);
                pool.add(coon);
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    //读取设置连接池的属性文件
    private void readConfig(){
        try {
            FileInputStream is = new FileInputStream(ConnectionPool.class.getResource("").getPath()+"/dbpool.properties");
            Properties props = new Properties();
            props.load(is);
//            System.out.println(props.get("driverClassName"));
//            this.driverClassName = "oracle.jdbc.driver.OracleDriver";
//            this.username = "rc7";
//            this.password = "rc7";
//            this.url = "jdbc:oracle:thin:@localhost:1521:orcl";
//            this.poolSize = 20;
            this.driverClassName = (String) props.get("driverClassName");
            this.username = (String) props.get("username");
            this.password = (String) props.get("password");
            this.url = (String) props.get("url");
            this.poolSize = Integer.parseInt(props.get("poolSize").toString());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.err.println("属性文件找不到");
        
    }
    
}
    public static void main(String[] args) {
       ConnectionPool pool = null;
           pool = ConnectionPool.getInstance();
           Connection conn = pool.getConnection();
           String id = UUID.randomUUID().toString();
           String mobile="325342543";
           String content="lkjglks会计电算化空间撒";
//           String sql="insert into smssend (ID, MOBILE, CONTENT)values ('{"+id+"}', '"+mobile+"', '"+content+"')";
//			try {
//				Statement stmt = conn.createStatement();
//				int count=stmt.executeUpdate(sql);
//	           System.out.println(count+"        f sdafdsafsa");
//	            stmt.close();
//			} catch (SQLException e) {
//				// TODO �Զ���� catch ��
//				e.printStackTrace();
//			}
           System.out.println(conn);
           pool.release(conn);
//       pool.closePool();
   
	}
    
}