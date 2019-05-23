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
     *
     * @param id
     * @param email
     * @param password
     * @param name
     * @param address
     * @param zipcode
     * @param phone
     * @param role
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
     * @param email
     * @param password
     * @param name
     * @param address
     * @param zipcode
     * @param phone
     * @param role
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
     *
     * @return
     */
    public Role getRole()
    {
        return role;
    }
    
    /**
     *
     * @return
     */
    public int getId()
    {
        return id;
    }
    
    /**
     *
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName()
    {
        return name;
    }
    
    /**
     *
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     *
     * @return
     */
    public String getZipcode()
    {
        return zipcode;
    }

    /**
     *
     * @param zipcode
     */
    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }
    
    /**
     *
     * @return
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
     * @return
     */
    public String getPassword()
    {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getAddress()
    {
        return address;
    }
    
    /**
     *
     * @param address
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     *
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
