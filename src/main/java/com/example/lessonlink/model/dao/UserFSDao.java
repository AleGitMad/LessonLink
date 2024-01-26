package com.example.lessonlink.model.dao;

import com.example.lessonlink.model.User;

import javax.security.auth.login.FailedLoginException;
import java.io.*;

public class UserFSDao {
    public String checkCredentials(String email, String password) throws FailedLoginException {
        String utente = System.getenv("UTENTE");
        final String utente_alexs = "alexs";
        String url = null;

        if(utente.equals(utente_alexs)) {
            url = "C:\\Users\\alexs\\Desktop\\Users.txt";
        }else {
            url = "C:\\Users\\Leonardo\\Desktop\\Users.txt";
        }

        File file = new File(url);
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
                        throw new FailedLoginException("Wrong password");
                    }
                }
            }

            throw new FailedLoginException("Email not registered!");

        }catch (IOException e){

            throw new FailedLoginException("Failed Login");
        }
    }

    public void setUser(User user, String email) {
        String utente = System.getenv("UTENTE");
        final String utente_alexs = "alexs";
        String url = null;

        if(utente.equals(utente_alexs)) {
            url = "C:\\Users\\alexs\\Desktop\\Users.txt";
        }else {
            url = "C:\\Users\\Leonardo\\Desktop\\Users.txt";
        }
        //String url = "C:\\Users\\alexs\\Desktop\\Users.txt";

        File file = new File(url);
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
            //unhandled
        }
    }

    /*
    public void registerNewAccount(String name, String email, String password, String role) throws FailedRegistrationException{

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\alexs\\Desktop\\Users.txt", true))){
            String content  = Files.readString(Path.of("C:\\Users\\alexs\\Desktop\\Users.txt"));

            int occurrences = countOccurrences(content, "id");
            occurrences = occurrences + 1;

            if(!content.contains(email)) {
                if (occurrences != 1) {
                    writer.newLine();
                }

                writer.write(email);
                writer.newLine();
                writer.write(password);
                writer.newLine();
                writer.write(role);
                writer.newLine();
                writer.write(firstName);
                writer.newLine();
                writer.write(lastName);
                writer.newLine();
                writer.write("id:" + occurrences);
                writer.newLine();
                writer.write("//");

            }else {
                throw new FailedRegistrationException("email already registered");
            }

        }catch (IOException e){
            //unhandled
        }
    }
    */
}
