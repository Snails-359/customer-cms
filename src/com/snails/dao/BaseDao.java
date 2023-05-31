package com.snails.dao;

import com.snails.util.JdbcDruid;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: BaseDao 工具类； 基于 JDBC 工具类封装增删改查;
 * -- 封装两个方法：一个简化DQL(数据查询语言)，一个简化DML(数据操纵语言)
 * @author: Snails
 * @create: 2023-05-29 22:10
 * @version: v1.0
 */
public class BaseDao {
    /**
     * 封装一个简化DML(数据操纵语言)方法
     * @param sql    带占位符的 SQL 语句。
     * @param params 占位符的值；
     *               注意传入的占位符的值必须与 SQL 语句中的占位符的位置一一对应！
     *               角标从 1 开始。
     * @return 执行影响的行数
     * @throws SQLException the sql exception
     */
    public int executeUpdate(String sql, Object... params) throws SQLException {
        // 获取连接
        Connection connection = JdbcDruid.getConnection();
        // 创建 statement，并接收SQL 语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 占位符赋值
        for (int i = 1; i <= params.length; i++) {
            preparedStatement.setObject(i, params[i - 1]);
        }
        // 发送 SQL 语句
        int row = preparedStatement.executeUpdate();
        // 结果集解析...
        // 释放资源
        JdbcDruid.reclaimConnection();

        return row;
    }

    public <T> List<T> executeQuery(Class<T> clazz, String sql, Object... params) throws SQLException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException,
            NoSuchFieldException {
        // 获取连接
        Connection connection = JdbcDruid.getConnection();
        // 创建 statement，并接收 SQL 语句
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // 占位符赋值
        if (params != null && params.length != 0) {
            for (int i = 1; i <= params.length; i++) {
                preparedStatement.setObject(i, params[i - 1]);
            }
        }
        // 发送 SQL 语句
        ResultSet resultSet = preparedStatement.executeQuery();

        // 创建结果集接收对象集合
        List<T> list = new ArrayList<>();
        // 获取结果集对象当前列的信息
        ResultSetMetaData metaData = resultSet.getMetaData();
        // 获取当前列的长度，用于遍历当前列的信息
        int columnCount = metaData.getColumnCount();

        // 遍历结果集
        while (resultSet.next()) {
            // 反射，调用类的构造器实例化结果集对象
            T t = clazz.getDeclaredConstructor().newInstance();
            // 遍历当前指定列
            for (int i = 1; i <= columnCount; i++) {
                // 获取指定列下标的列的名称
                String propertyName = metaData.getColumnLabel(i);
                // 获取指定列下标的列的属性值
                Object value = resultSet.getObject(i);

                // 反射，获取对象的属性值
                Field field = clazz.getDeclaredField(propertyName);

                // 打破 private 修饰限制，使属性可以设置
                field.setAccessible(true);
                // 给对象的属性赋值
                field.set(t, value);
            }
            list.add(t);
        }
        // 释放资源
        resultSet.close();
        preparedStatement.close();

        JdbcDruid.reclaimConnection();

        return list;
    }
}
