package com.snails.service;

import com.snails.dao.CustomerDao;
import com.snails.javabean.Customer;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * @description: 业务层
 * @author: Snails
 * @create: 2023-05-30 12:33
 * @version: v1.0
 */
public class CustomerService {
    private CustomerDao customerDao = new CustomerDao();


    /**
     * 添加新客户
     * @param customer 指定添加的客户的对象
     */
    public void addCustomer(Customer customer) {
        try {
            customerDao.addCustomer(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取指定 ID 对应的客户对象信息
     * @param id 指定 ID
     * @return 对应 ID 的客户对象
     */
    public Customer getCustomer(int id) {
        try {
            return customerDao.selectById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改指定 ID 的客户对象信息
     * @param id   指定客户 ID
     * @param cust 修改客户对象
     * @return true 表示修改成功，false 表示修改失败
     */
    public boolean modifyCustomer(int id, Customer cust) {
        int row = 0;
        try {
            row = customerDao.updateById(cust);
            if (row == 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除对应 ID 的客户对象信息
     * @param id 指定客户对象的 ID
     * @return true 表示删除成功，false 表示删除失败
     */
    public boolean removeCustomer(int id) {
        try {
            int row = customerDao.deleteById(id);
            if (row == 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取所有客户对象
     * @return 客户对象集合
     */
    public List<Customer> getCustomerList() {
        try {
            return customerDao.selectAllCustomer();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
