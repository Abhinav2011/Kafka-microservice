package com.abhinav.base.domains.dto;

public class OrderEvent {
    private String message;
    private String orderStatus;
    private Order order;

    public OrderEvent() {

    }

    public OrderEvent(String message, String orderStatus, Order order) {
        this.message = message;
        this.orderStatus = orderStatus;
        this.order = order;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderEvent{" +
                "message='" + message + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", order=" + order +
                '}';
    }
}
