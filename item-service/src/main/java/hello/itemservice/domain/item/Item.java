package hello.itemservice.domain.item;

import lombok.Data;

@Data
//@Getter @Setter를 쓰는걸 권장한다. 예제이기 때문에 @Data를 사용.
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
