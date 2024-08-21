package com.eduardocaio.inventory_control_project.entities.enums;

public enum OrderStatus {

    WAITING_PAYMENT("WP", "Aguardando pagamento"),
    PAID("P", "Pago"),
    IN_SEPARATION("IS", "Em separacao"),
    WAITING_SHIPPING("WS", "Aguardando envio"),
    SHIPPED("S", "Enviado"),
    OUT_DELIVERY("OD", "Saiu para entrega"),
    DELIVERED("D", "Entregue"),
    CANCELED("C", "Cancelado"),
    RETURNING("R", "Produto retornando"),
    REFUND("RF", "Reembolsado");

    private String code;
    private String description;
    
    private OrderStatus(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
