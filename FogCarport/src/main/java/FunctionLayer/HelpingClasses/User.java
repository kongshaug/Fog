/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.HelpingClasses;

import FunctionLayer.Enum.Role;

/**
 *
 * @author aamandajuhl
 */
public class User
{

    private int id;
    private String email;
    private String password;
    private String name;
    private String address;
    private String zipcode;
    private String phone;
    private Role role;

    public User(int id, String email, String password, String name, String address, String zipcode, String phone, Role role)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.phone = phone;
        this.role = role;
    }

    public User(String email, String password, String name, String address, String zipcode, String phone, Role role)
    {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.phone = phone;
        this.role = role;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    @Override
    public String toString()
    {
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", address=" + address + ", zipcode=" + zipcode + ", phone=" + phone + ", role=" + role + '}';
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getId()
    {
        return id;
    }

    public String getEmail()
    {
        return email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getName()
    {
        return name;
    }

    public String getAddress()
    {
        return address;
    }

    public String getPhone()
    {
        return phone;
    }

    public Role getRole()
    {
        return role;
    }

}
