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
     * Initializes the values of a newly created User.
     * 
     * @param id an Integer
     * @param email a String
     * @param password a String
     * @param name a String
     * @param address a String
     * @param zipcode an String
     * @param phone an String
     * @param role an object from the enum Role
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
     * 
     * Initializes the values of a newly created User.
     * 
     * @param email a String
     * @param password a String
     * @param name a String
     * @param address a String
     * @param zipcode a String
     * @param phone a String
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
     * The user's role identifies as a customer, employee or admin
     * @return the role of the User
     */
    public Role getRole()
    {
        return role;
    }
    
    /**
     *
     * @return the id
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Sets the id to id
     * 
     * @param id an Integer
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Sets the name to name
     * 
     * @param name a String
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     *
     * @return the zipcode
     */
    public String getZipcode()
    {
        return zipcode;
    }

    /**
     * Sets the zipcode to zipcode
     * 
     * @param zipcode a String
     */
    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }
    
    /**
     *
     * @return the email
     */
    public String getEmail()
    {
        return email;
    }
    
    /**
     * Sets the email to email
     * 
     * @param email a String 
     */
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    /**
     * 
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Sets the password to password
     * 
     * @param password a String
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     *
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }
    
    /**
     * Sets the address to address 
     * 
     * @param address a String
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     *
     * @return the phonenumber
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * Sets the phone to phone
     * 
     * @param phone a String
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /**
     * @return a String with the values of email, name, and phone
     */
    @Override
    public String toString()
    {
        return "Bruger: " + email + ", navn: " + name + ", mobilnr: " + phone;
    }
}
