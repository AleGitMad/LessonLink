package com.example.lessonlink.model.dao;

import com.example.lessonlink.model.Account;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AccountFSDao {
    public void setAccount(Account account, String email) {

    String url = "C:\\Users\\alexs\\Desktop\\Users.txt";

    File file = new File(url);
    String str;

    try (BufferedReader br = new BufferedReader(new FileReader(file))){

        while ((str = br.readLine())!= null){

            if(str.equals(email)){
                account.setEmail(email);
                str = br.readLine();
                str = br.readLine();
                account.setName(br.readLine());
                account.setLastName(br.readLine());
                account.setUserId(br.readLine().substring(3));
            }
        }

    }catch (IOException e){
        //unhandled
    }

}
}