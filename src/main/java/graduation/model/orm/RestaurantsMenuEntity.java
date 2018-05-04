package graduation.model.orm;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "restaurants_menu")
public class RestaurantsMenuEntity extends AbstractBaseEntity {
    @Column(name = "item_name")
    @NotNull
    @NotEmpty
    private String ItemName;

    @Column(name = "item_price")
    @NotNull
    @NotEmpty
    private Double itemPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantsEntity restaurantsEntity;
    public RestaurantsMenuEntity() {
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public Double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
