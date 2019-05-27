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
     * constructor for order from database with assigned id
     * @param order_id int
     * @param user object
     * @param carport object
     * @param order_date String
     * @param status enum
     * @param shipped enum
     * @param paid enum
     * @param sales_price double
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
     * constructor for new order
     * @param user object
     * @param carport object
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
     * @return the id of order
     */
    public int getOrder_id()
    {
        return order_id;
    }

    /**
     * sets the id of an order
     * @param order_id
     */
    public void setOrder_id(int order_id)
    {
        this.order_id = order_id;
    }

    /**
     *
     * @return user associated with the order
     */
    public User getUser()
    {
        return user;
    }

    /**
     * gets the carport associated with the order
     * @return user associated with order
     */
    public Carport getCarport()
    {
        return carport;
    }

    /**
     *gets the order date from the order
     * @return order date
     */
    public String getOrder_date()
    {
        return order_date;
    }

    /**
     * sets the date of the order
     * @param order_date
     */
    public void setOrder_date(String order_date)
    {
        this.order_date = order_date;
    }

    /**
     *
     * @return status of order
     */
    public Status getStatus()
    {
        return status;
    }

    /**
     * sets the status of the order
     * @param status
     */
    public void setStatus(Status status)
    {
        this.status = status;
    }

    /**
     *
     * @return shipping status of the order
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
     *sets the shipping status of the order
     * @param shipped
     */
    public void setShipped(String shipped)
    {
        this.shipped = shipped;
    }

    /**
     *
     * @return payment status of the order
     */
    public Paid getPaid()
    {
        return paid;
    }

    /**
     * sets the payment status of the order
     * @param paid
     */
    public void setPaid(Paid paid)
    {
        this.paid = paid;
    }

    /**
     * calculates the sales price of the order
     */
    public void calcSalesPrice()
    {
        this.sales_price = carport.getTotal_price() * 1.25;
        this.sales_price = Math.round(this.sales_price * 100.0) / 100.0;
    }

    /**
     *
     * @return get the total price of the order for the customer
     */
    public double getSales_price()
    {
        return sales_price;
    }

    /**
     * sets the total price of the order for the customer
     * @param sales_price
     */
    public void setSales_price(double sales_price)
    {
        this.sales_price = sales_price;
    }

    /**
     *
     * @return the profit of the sale
     */
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
