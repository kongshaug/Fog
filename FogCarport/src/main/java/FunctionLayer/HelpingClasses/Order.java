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

    /**
     * Initializes the values of a newly created Order.
     * 
     * @param order_id an Integer
     * @param user an object from the class User 
     * @param carport an object from the class Carport
     * @param order_date a String
     * @param status an object from the enum class Status
     * @param shipped a String
     * @param paid an object from the enum class Paid
     * @param sales_price a double
     */
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

    /**
     * Initializes the values of a newly created Order 
     * as well as calculate the salesprice of the Order.
     * 
     * @param user an object from the class User 
     * @param carport an object from the class Carport
     */
    public Order(User user, Carport carport)
    {
        this.user = user;
        this.carport = carport;
        this.paid = Paid.IKKE_BETALT;
        this.status = Status.MODTAGET;
        calcSalesPrice();
    }

    /**
     *
     * @return the order_id
     */
    public int getOrder_id()
    {
        return order_id;
    }

    /**
     * Sets the order_id to order_id.
     * 
     * @param order_id an Integer
     */
    public void setOrder_id(int order_id)
    {
        this.order_id = order_id;
    }

    /**
     *
     * @return the user
     */
    public User getUser()
    {
        return user;
    }

    /**
     * @return the carport
     */
    public Carport getCarport()
    {
        return carport;
    }

    /**
     * @return order date as a String
     */
    public String getOrder_date()
    {
        return order_date;
    }

    /**
     * Sets the order_date to order_date.
     * 
     * @param order_date a String
     */
    public void setOrder_date(String order_date)
    {
        this.order_date = order_date;
    }

    /**
     *
     * @return status 
     */
    public Status getStatus()
    {
        return status;
    }

    /**
     * Sets the status to status.
     * 
     * @param status an object of the enum class Status
     */
    public void setStatus(Status status)
    {
        this.status = status;
    }

    /**
     * Checks if the order is shipped.
     * 
     * @return shipped, as a String
     */
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

    /**
     * Sets shipped to shipped.
     * 
     * @param shipped a String
     */
    public void setShipped(String shipped)
    {
        this.shipped = shipped;
    }

    /**
     *
     * @return paid
     */
    public Paid getPaid()
    {
        return paid;
    }

    /**
     * Sets paid to paid.
     * 
     * @param paid an object of the enum class Paid
     */
    public void setPaid(Paid paid)
    {
        this.paid = paid;
    }

    /**
     * Calculates the sales_price of the order.
     */
    public void calcSalesPrice()
    {
        this.sales_price = carport.getTotal_price() * 1.25;
        this.sales_price = Math.round(this.sales_price * 100.0) / 100.0;
    }

    /**
     *
     * @return sales_price as a double
     */
    public double getSales_price()
    {
        return sales_price;
    }

    /**
     * Sets the sales_price to sales_price.
     * 
     * @param sales_price a double
     */
    public void setSales_price(double sales_price)
    {
        this.sales_price = sales_price;
    }

    /**
     * Calculates the profit based on the 
     * sales_price and the carports total_price.
     * 
     * @return the profit as a double
     */
    public double getProfit()
    {
        double profit = ((sales_price - carport.getTotal_price()) / carport.getTotal_price()) * 100;

        profit = Math.round(profit * 100.0) / 100.0;

        return profit;

    }

    /**
     * @return a String with the values of order_id, order_date, status and paid 
     */
    @Override
    public String toString()
    {
        return "Ordrer: " + order_id + ", ordrer dato: " + order_date + ", status: " + status + ", betalt: " + paid;
    }

}
