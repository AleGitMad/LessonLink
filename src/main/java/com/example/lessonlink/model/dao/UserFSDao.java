package com.example.lessonlink.model.dao;

import com.example.lessonlink.exceptions.FailedFileAccessException;
import com.example.lessonlink.model.User;

import javax.security.auth.login.FailedLoginException;
import java.io.*;

public class UserFSDao {
    public String checkCredentials(String email, String password) throws FailedLoginException, FailedFileAccessException {
        File file = new File("src/main/res/Users.txt");
        String str;

        String role;

        try (BufferedReader br = new BufferedReader(new FileReader(file))){

            while ((str = br.readLine())!= null){
                if(str.equals(email)){
                    str = br.readLine();
                    if(str.equals(password)){
                        role = br.readLine();
                        return role;
                    }else {
                        throw new FailedLoginException("Incorrect email or password. Try again");
                    }
                }
            }

            throw new FailedLoginException("Email not registered!");

        }catch (IOException e){

            throw new FailedFileAccessException("The user file is inaccessible. Reason: ",e.getCause());
        }
    }

    public void setUser(User user, String email) throws FailedFileAccessException {
        File file = new File("src/main/res/Users.txt");
        String str;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((str = br.readLine()) != null) {

                if (str.equals(email)) {
                    user.setEmail(br.readLine());
                    str = br.readLine(); //skip password line
                    user.setUserId(Integer.parseInt(br.readLine()));
                    user.setName(br.readLine());
                }
            }

        } catch (IOException e) {
            throw new FailedFileAccessException("The user file is inaccessible. Reason: ",e.getCause());
        }
    }
}
