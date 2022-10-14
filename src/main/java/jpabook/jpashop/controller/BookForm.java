package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class BookForm {

    private Long id;

    @NotEmpty(message = "책 이름을 입력해 주세요.")
    private String name;

    private int price;
    private int stockQuantity;

    @NotEmpty(message = "ISBN을 입력해 주세요.")
    private String isbn;

    private String author;

}
