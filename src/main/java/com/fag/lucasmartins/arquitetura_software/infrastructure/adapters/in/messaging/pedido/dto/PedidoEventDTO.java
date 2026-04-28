package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto;

import java.util.List;

public class PedidoEventDTO {

    private String zipCode;
    private Integer customerId;
    private List<PedidoItemEventDTO> orderItems;
    private String origin;
    private String occurredAt;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<PedidoItemEventDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<PedidoItemEventDTO> orderItems) {
        this.orderItems = orderItems;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOccurredAt() {
        return occurredAt;
    }

    public void setOccurredAt(String occurredAt) {
        this.occurredAt = occurredAt;
    }
}