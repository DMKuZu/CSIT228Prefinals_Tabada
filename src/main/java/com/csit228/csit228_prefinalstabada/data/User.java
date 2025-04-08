package com.csit228.csit228_prefinalstabada.data;

import java.io.Serializable;

public class User implements Serializable {
    public String username;
    public String password;

    public String firstname;
    public String lastname;
    public int type;

    public User(String name, String pass, int what,String fname, String lname){
        username = name;
        password = pass;
        type = what;
        firstname = fname;
        lastname = lname;
    }
}
