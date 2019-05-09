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

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
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
