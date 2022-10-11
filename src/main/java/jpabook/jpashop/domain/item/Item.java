package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //JOINED 정규화 SINGLE_TABLE 한테이블에 때려박기 TABLE_PER_CLASS 각각의 테이블을 생성
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//

    /**
     * stock 증가
     */
    public void addStock(int quantity) { this.stockQuantity += quantity; }

    /**
     * stock 감소
     * 재고보다 많은 수량을 주문하면 재고부족 에러
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) throw new NotEnoughStockException("need more stock");
        this.stockQuantity = restStock;
    }
}
