package br.com.des.core.model;

import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Created by diogobernard on 22/04/17.
 */
public class Client {

    @Id
    private String login;
    private String name;
    private Integer age;
    private Date birthday;


    public Client(){

    }

    public Client(String login, String name, Integer age, Date birthday) {
        this.login = login;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Client{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
