package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPoliocy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPoliocy();


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);

        int discountPrice = discountPolicy.discount(member, itemPrice);
        // 설계가 잘된 이유는 discountPolisy가 알아서 해주기 때문이다.
        // 단일 책임 원칙을 잘 지킨 경우라고 할 수 있다.

        return new Order(memberId, itemName, itemPrice, discountPrice);


    }
}
