package com.solodoukhin.model;

import javax.persistence.*;

/**
 * Author: Solodoukhin Viktor
 * Date: 14.07.18
 * Description: TODO
 */

@Entity
@Table(name = "picture")
public class Picture {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String address;

    @ManyToOne
    @JoinColumn(name = "fk_partner_id")
    private Partner partner;

    public Picture() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Partner getPartner() {
        return partner;
    }

    public void setPartner(Partner partner) {
        this.partner = partner;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", partner=" + partner +
                '}';
    }
}