/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.HelpingClasses;

import FunctionLayer.Enum.Paid;
import FunctionLayer.Enum.Status;

/**
 *
 * @author aamandajuhl
 */
public class Order
{
    private int order_id;
    private User user;
    private Carport carport;
    private String order_date;
    private Status status;
    private String shipped;
    private Paid paid;
    private double sales_price;

    public Order(int order_id, User user, Carport carport, String order_date, Status status, String shipped, Paid paid, double sales_price)
    {
        this.order_id = order_id;
        this.user = user;
        this.carport = carport;
        this.order_date = order_date;
        this.status = status;
        this.shipped = shipped;
        this.paid = paid;
        this.sales_price = sales_price;
    }

    public Order(User user, Carport carport)
    {
        this.user = user;
        this.carport = carport;
        this.paid = Paid.IKKE_BETALT;
        this.status = Status.MODTAGET;
        calcSalesPrice();
    }

    public int getOrder_id()
    {
        return order_id;
    }

    public void setOrder_id(int order_id)
    {
        this.order_id = order_id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Carport getCarport()
    {
        return carport;
    }

    public void setCarport(Carport carport)
    {
        this.carport = carport;
    }

    public String getOrder_date()
    {
        return order_date;
    }

    public void setOrder_date(String order_date)
    {
        this.order_date = order_date;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public String getShipped()
    {
        if (shipped == null)
        {
            return "Ordren er endnu ikke afsendt";
        } else
        {
            return shipped;
        }
    }

    public void setShipped(String shipped)
    {
        this.shipped = shipped;
    }

    public Paid getPaid()
    {
        return paid;
    }

    public void setPaid(Paid paid)
    {
        this.paid = paid;
    }

    public void calcSalesPrice()
    {
        this.sales_price = carport.getTotal_price() * 1.25;
        this.sales_price = Math.round(this.sales_price * 100.0) / 100.0;
    }

    public double getSales_price()
    {
        return sales_price;
    }

    public void setSales_price(double sales_price)
    {
        this.sales_price = sales_price;
    }

    public double getProfit()
    {
        double profit = ((sales_price - carport.getTotal_price()) / carport.getTotal_price()) * 100;

        profit = Math.round(profit * 100.0) / 100.0;

        return profit;

    }

    @Override
    public String toString()
    {
        return "Ordrer: " + order_id + ", ordrer dato: " + order_date + ", status: " + status + ", betalt: " + paid;
    }

}
