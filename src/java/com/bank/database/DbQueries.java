/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank.database;

/**
 *
 * @author Saranya Srinivasan
 */
public interface DbQueries {

//    Admin Side
    public static final String ADMIN_ACCOUNT_CREAT_FOR_USER = "insert into user_registration (bankname,firstname,lastname,mail,gender,age,branchname,address,accno,ifsc,password,datetime)values(?,?,?,?,?,?,?,?,?,?,?,?)";
    public static final String ADMIN_VIEW_USER_ACCOUNT = "select * from user_registration";
    public static final String ADMIN_VIEW_ACCOUNT_ALREADY_HAVE = "select * from deposite where acno=?";
    public static final String ADMIN_DEPOSIT_FOR_USER = "insert into deposite (byname,acno,ifsc,branch,amount,datetime) values (?,?,?,?,?,?)";
    public static final String ADMIN_ALL_ACCOUNT_DETAILS = "insert into all_account_data (byname,acno,ifsc,branch,amount,datetime,status) values (?,?,?,?,?,?,?)";
    public static final String ADMIN_UPDATE_ACCOUNT = "update deposite set amount=? where acno=?";
    public static final String ADMIN_SELECT_EXSISTING_USER_ACCOUNT = "select * from deposite where acno=? and ifsc=?";
    public static final String ADMIN_UPDATE_WITHDRAW = "update deposite set amount=? where acno=? and ifsc=?";
    public static final String ADMIN_WITHDRAW_FOR_USER = "insert into withdraw (acno,ifsc,branch,amount,datetime) values (?,?,?,?,?)";
    public static final String ADMIN_CHECK_DEPOSITE_STATEMENT = "select * from deposite";
    public static final String ADMIN_CHECK_WITHDRAW_STATEMENT = "select * from withdraw";
    public static final String ADMIN_SEARCH_USERS_DATA = "select * from user_registration where accno=?";
    public static final String ADMIN_EDIT_USERS_DATA = "update user_registration set firstname=?,lastname=?,mail=?,gender=?,age=?,branchname=?,address=?,ifsc=?,datetime=? where accno=?";
    public static final String ADMIN_DELETE_USERS_DATA = "delete from user_registration where accno=?";

    //End Admin Side
//     ********************************************************************************************************************************************************************************
    //User Side
    public static final String USER_LOGIN = "select * from user_registration where accno=? and password=?";
    public static final String USER_VIEW_PROFILE = "select * from user_registration where accno=?";
    public static final String USER_GET_AMOUNT = "select * from deposite where acno=?";
    public static final String USER_TRANSFER_MONEY = "update deposite set amount=? where acno=?";
    public static final String USER_TRANSFER_MONEY_TO_BENI = "insert into transfer (bname,fromaccno,toaccno,ifsc,branch,amount,datetime) values (?,?,?,?,?,?,?)";
    public static final String USER_GET_BALANCE = "select * from deposite where acno=?";
    public static final String USER_DEPOSIT_MONEY = "insert into deposite (byname,acno,ifsc,branch,amount,datetime) values (?,?,?,?,?,?)";
    public static final String UESR_SELECT_EXSISTING_USER_ACCOUNT = "select * from deposite where acno=? and ifsc=?";
    public static final String USER_UPDATE_WITHDRAW = "update deposite set amount=? where acno=? and ifsc=?";
    public static final String USER_WITHDRAW_FOR_USER = "insert into withdraw (acno,ifsc,branch,amount,datetime) values (?,?,?,?,?)";

    public static final String USER_CHECK_DEPOSITE_STATEMENT = "select * from deposite where acno=?";
    public static final String USER_CHECK_WITHDRAW_STATEMENT = "select * from withdraw where acno=?";
    public static final String USER_CHECK_TRANSFER_STATEMENT = "select * from transfer where fromaccno=?";

    public static final String USER_VIEW_ACCOUNT_ALREADY_HAVE = "select * from deposite where acno=?";
    public static final String USER_UPDATE_ACCOUNT = "update deposite set amount=? where acno=?";
    public static final String USER_ALL_ACCOUNT_DETAILS = "insert into all_account_data (byname,acno,ifsc,branch,amount,datetime,status) values (?,?,?,?,?,?,?)";
    public static final String USER_VIEW_MINI_STATEMENT = "select * from all_account_data where acno=?";

    //End User Side
}
