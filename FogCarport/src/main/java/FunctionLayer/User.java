/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

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
    private int zipcode;
    private int phone;
    private String role;

    public User(int id, String email, String password, String name, String address, int zipcode, int phone, String role)
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

    public User(String email, String password, String name, String address, int zipcode, int phone, String role)
    {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.zipcode = zipcode;
        this.phone = phone;
        this.role = role;
    }

    public int getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(int zipcode)
    {
        this.zipcode = zipcode;
    }

    public int getId()
    {
        return id;
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

    public void setPhone(int phone)
    {
        this.phone = phone;
    }

    public void setRole(String role)
    {
        this.role = role;
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

    public int getPhone()
    {
        return phone;
    }

    public String getRole()
    {
        return role;
    }

}
