package com.snails.main;

import com.snails.view.CustomerView;

/**
 * @description: 程序入口
 * @author: Snails
 * @create: 2023-05-30 13:18
 * @version: v1.0
 */
public class CustomerMain {
    public static void main(String[] args) {
        CustomerView cView = new CustomerView();
        cView.enterMainMenu();
    }
}
