package com.snails.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @description: JDBC 工具类；内部包含一个连接池对象，并对外提供一个获取连接和回收连接的方法。
 * @author: Snails
 * @create: 2023-05-29 21:13
 * @version: v1.0
 */
public class JdbcDruid {
    // 连接池对象
    private static DataSource dataSource = null;
    // 线程本来变量存储连接池
    private static ThreadLocal<Connection> thread = new ThreadLocal<>();

    // 静态代码块，初始化对象池
    static {
        // 读取配置文件
        Properties properties = new Properties();
        InputStream inputStream = JdbcDruid.class.getClassLoader().getResourceAsStream("druid.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 工厂模式,创建连接
        try {
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取连接
     * @return the connection
     * @throws SQLException the sql exception
     */
    public static Connection getConnection() throws SQLException {
        // 获取线程本地变量中的 connection
        Connection connection = thread.get();

        if (connection == null) {
            // 获取连接
            connection = dataSource.getConnection();
            // 设置连接信息到线程本地变量中
            thread.set(connection);
        }
        return connection;
    }


    /**
     * 收回连接
     * @throws SQLException the sql exception
     */
    public static void reclaimConnection() throws SQLException {
        // 获取线程本地变量中的 connection
        Connection connection = thread.get();

        if (connection != null) {
            // 清空线程本地变量数据
            thread.remove();
            // 开启事务提交
            connection.setAutoCommit(true);
            // 回收连接到连接池
            connection.close();
        }
    }

}
