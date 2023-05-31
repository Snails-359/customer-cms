package com.snails.view;

import com.snails.javabean.Customer;
import com.snails.service.CustomerService;
import com.snails.util.CMUtility;

import java.util.List;

/**
 * @description: 为主模块，负责菜单的显示和处理用户操作
 * @author: Snails
 * @create: 2023-05-30 13:05
 * @version: v1.0
 */
public class CustomerView {

    private CustomerService customersService = new CustomerService();

    /**
     * 显示主菜单，响应用户输入，根据用户操作分别调用其他相应的成员方法，以完成客户信息处理。
     */
    public void enterMainMenu() {
        boolean isFlag = true;
        do {
            listAllCustomers();
            System.out.println("\n---------------------客户管理系统-----------------\n");
            System.out.println("                   1 添 加 客 户");
            System.out.println("                   2 修 改 客 户");
            System.out.println("                   3 删 除 客 户");
            System.out.println("                   4 客 户 列 表");
            System.out.println("                   5 退       出\n");

            System.out.print("                   请选择(1-5)：");
            char key = CMUtility.readMenuSelection();

            switch (key) {
                case '1':
                    addNewCustomer();
                    break;
                case '2':
                    modifyCustomer();
                    break;
                case '3':
                    deleteCustomer();
                    break;
                case '4':
                    listAllCustomers();
                    break;
                case '5':
                    System.out.print("确认是否退出(Y/N)：");
                    char yn = CMUtility.readConfirmSelection();
                    if (yn == 'Y')
                        isFlag = false;
                    break;
            }
        } while (isFlag);
    }

    private void addNewCustomer() {
        System.out.println("---------------------添加客户---------------------");
        System.out.print("姓名：");
        String name = CMUtility.readString(4);
        System.out.print("性别：");
        String gender = CMUtility.readString(1);
        System.out.print("年龄：");
        int age = CMUtility.readInt();
        System.out.print("电话：");
        String phone = CMUtility.readString(15);
        System.out.print("邮箱：");
        String email = CMUtility.readString(15);

        Customer customer = new Customer(name, gender, age, phone, email);
        customersService.addCustomer(customer);
        // if (flag) {
        System.out.println("---------------------添加完成---------------------");
        // } else {
        //     System.out.println("----------------记录已满,无法添加-----------------");
        // }
    }

    private void modifyCustomer() {
        System.out.println("---------------------修改客户---------------------");

        int index = 0;
        Customer cust = null;
        for (; ; ) {
            System.out.print("请选择待修改客户编号(-1退出)：");
            index = CMUtility.readInt();
            if (index == -1) {
                return;
            }

            cust = customersService.getCustomer(index);
            if (cust == null) {
                System.out.println("无法找到指定客户！");
            } else
                break;
        }

        System.out.print("姓名(" + cust.getName() + ")：");
        String name = CMUtility.readString(4, cust.getName());

        System.out.print("性别(" + cust.getGender() + ")：");
        String gender = CMUtility.readString(1, cust.getGender());

        System.out.print("年龄(" + cust.getAge() + ")：");
        int age = CMUtility.readInt(cust.getAge());

        System.out.print("电话(" + cust.getPhone() + ")：");
        String phone = CMUtility.readString(15, cust.getPhone());

        System.out.print("邮箱(" + cust.getEmail() + ")：");
        String email = CMUtility.readString(15, cust.getEmail());

        cust = new Customer(name, gender, age, phone, email);

        boolean flag = customersService.modifyCustomer(index - 1, cust);
        if (flag) {
            System.out.println("---------------------修改完成---------------------");
        } else {
            System.out.println("----------无法找到指定客户,修改失败--------------");
        }
    }

    private void deleteCustomer() {
        System.out.println("---------------------删除客户---------------------");

        int index = 0;
        Customer cust = null;
        for (; ; ) {
            System.out.print("请选择待删除客户编号(-1退出)：");
            index = CMUtility.readInt();
            if (index == -1) {
                return;
            }

            cust = customersService.getCustomer(index - 1);
            if (cust == null) {
                System.out.println("无法找到指定客户！");
            } else
                break;
        }

        System.out.print("确认是否删除(Y/N)：");
        char yn = CMUtility.readConfirmSelection();
        if (yn == 'N')
            return;

        boolean flag = customersService.removeCustomer(index - 1);
        if (flag) {
            System.out.println("---------------------删除完成---------------------");
        } else {
            System.out.println("----------无法找到指定客户,删除失败--------------");
        }
    }


    private void listAllCustomers() {
        System.out.println("---------------------------客户列表---------------------------");
        List<Customer> customerList = customersService.getCustomerList();
        if (customerList.size() == 0) {
            System.out.println("没有客户记录！");
        } else {
            System.out.println("ID\t姓名    \t性别  \t年龄    \t电话    \t\t\t邮箱");
            for (int i = 0; i < customerList.size(); i++) {
                // System.out.println((i + 1) + "\t\t" + customerList.get(i).toString());
                System.out.print(customerList.get(i).getId());
                System.out.print("\t" + customerList.get(i).getName());
                System.out.print("\t\t" + customerList.get(i).getGender());
                System.out.print("\t\t" + customerList.get(i).getAge());
                System.out.print("\t\t" + customerList.get(i).getPhone());
                System.out.print("\t\t" + customerList.get(i).getEmail());
                System.out.println();
            }
        }
        System.out.println("-------------------------客户列表完成-------------------------");
    }
}
