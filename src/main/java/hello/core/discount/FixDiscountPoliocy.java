package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FixDiscountPoliocy implements DiscountPolicy {

    private int discountFixAmount = 1000; // 1000월 고정 할인 금액

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) { // Enum 타입은 ==을 사용한다
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
