package hello.core.order;

public interface OrderService {  //주문 생성 부분, 3개의 인자를 받는다

    Order createOrder(Long memberId, String itemName, int itemPrice);
}
