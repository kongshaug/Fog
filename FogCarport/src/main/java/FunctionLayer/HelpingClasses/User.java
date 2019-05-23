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

    /**
     * constructor for user from database
     * @param id int
     * @param email String
     * @param password String
     * @param name String
     * @param address String
     * @param zipcode int
     * @param phone int
     * @param role enum
     */
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

    /**
     * constructor for user
     * @param email String
     * @param password String
     * @param name String
     * @param address String
     * @param zipcode int
     * @param phone int
     * @param role enum
     */
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

    /**
     * the role identifyes if the user is a customer, employee or admin
     * @return the role of the user
     */
    public Role getRole()
    {
        return role;
    }
    
    /**
     *
     * @return the id of the user
     */
    public int getId()
    {
        return id;
    }
    
    /**
     *sets the id of a user
     * @param id int
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     *
     * @return the name of the user as a string
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * sets the name of the user
     * @param name String
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     *
     * @return the zipcode of the user
     */
    public String getZipcode()
    {
        return zipcode;
    }

    /**
     * sets the zipcode of the user
     * @param zipcode int
     */
    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }
    
    /**
     *
     * @return the email of the user
     */
    public String getEmail()
    {
        return email;
    }
    
    /**
     *
     * @param email
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    /**
     *
     * @return the password of the user
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * sets the password of the user
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     *
     * @return the address of the user
     */
    public String getAddress()
    {
        return address;
    }
    
    /**
     *
     * @param address String
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     *
     * @return the phonenumber of the user
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * sets the phonenumber of the user
     * @param phone
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Override
    public String toString()
    {
        return "Bruger: " + email + ", navn: " + name + ", mobilnr: " + phone;
    }
}
