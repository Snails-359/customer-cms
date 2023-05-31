package com.snails.dao;

import com.snails.javabean.Customer;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @description: t_customer 表数据操作类
 * @author: Snails
 * @create: 2023-05-29 22:57
 * @version: v1.0
 */
public class CustomerDao extends BaseDao {
    /**
     * 添加客户
     * @param customer the customer
     * @return 执行影响的行数
     * @throws SQLException the sql exception
     */
    public int addCustomer(Customer customer) throws SQLException {
        String sql = "insert into t_customer(id,name,gender,age,phone,email) values(?,?,?,?,?,?)";
        int rows = executeUpdate(sql, customer.getId(), customer.getName(), customer.getGender(), customer.getAge(),
                customer.getPhone(), customer.getEmail());

        return rows;
    }

    /**
     * 根据指定 ID 修改客户信息
     * @param cust 修改的客户信息
     * @return 执行影响行数
     * @throws SQLException the sql exception
     */
    public int updateById(Customer cust) throws SQLException {
        String sql = "update t_customer set name = ?,gender = ?,age =?,phone =?,email = ? where id =?";
        int row = executeUpdate(sql, cust.getName(), cust.getGender(), cust.getAge(), cust.getPhone(), cust.getEmail(),
                cust.getId());

        return row;
    }

    /**
     * 根据指定 ID 查询对应的客户信息
     * @param id 指定 ID
     * @return 客户信息
     * @throws SQLException              the sql exception
     * @throws NoSuchFieldException      the no such field exception
     * @throws InvocationTargetException the invocation target exception
     * @throws NoSuchMethodException     the no such method exception
     * @throws InstantiationException    the instantiation exception
     * @throws IllegalAccessException    the illegal access exception
     */
    public Customer selectById(int id) throws SQLException, NoSuchFieldException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {

        String sql = "select * from t_customer where id = ?";
        List<Customer> customerList = executeQuery(Customer.class, sql, id);

        if (customerList != null && customerList.size() > 0) {
            return customerList.get(0);
        }
        return null;
    }

    /**
     * 根据指定 ID 删除对应的客户信息
     * @param id 指定 ID
     * @return 执行影响行数
     * @throws SQLException the sql exception
     */
    public int deleteById(int id) throws SQLException {
        String sql = "delete from t_customer where id =?";
        int row = executeUpdate(sql, id);

        return row;
    }


    /**
     * 查询所有客户信息
     * @return 客户list 集合
     */
    public List<Customer> selectAllCustomer() throws SQLException, NoSuchFieldException, InvocationTargetException,
            NoSuchMethodException, InstantiationException, IllegalAccessException {

        List<Customer> customerList = executeQuery(Customer.class, "select * from t_customer");

        return customerList;
    }
}
