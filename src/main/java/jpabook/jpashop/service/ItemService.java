package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /*
    * 준영속 엔티티를 수정하기위한 방법 1. 변경 감지(Dirty Checking)
    * JPA가 관리할 수 있도록 트랜잭션 안에서 영속성을 가진 객체를 만든다
    * '그곳에' 받아온 파라미터 or DTO 에 있는 변경되어야할 값들을 적용/입력 한다.
    *
    * 트랜잭션이 끝나면서 커밋시점에 JPA가 변경점을 알아보고 id와 같은 식별자를 이용해 DB를 탐색해서 변경된 점을 알아서 바꿔준다.
    *
    * 이 방법이 2. 병합(Merge) 사용 보다 좋다.
    * */
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.change(name, price, stockQuantity);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long id) {
        return itemRepository.findOne(id);
    }
}
