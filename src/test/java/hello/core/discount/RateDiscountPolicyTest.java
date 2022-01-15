package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP 10% 할인 적용 테스트")
    void VIP_정률_고정_할인() {
        // given
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        // when
        int discountPrice = discountPolicy.discount(member, 10000);

        // then
        assertThat(discountPrice).isEqualTo(1000);

    }

    @Test
    @DisplayName("VIP가 아닐 경우 할인 미적용 테스트")
    void NON_VIP_할인_미적용(){
        // given
        Member member = new Member(2L, "memberBASIC", Grade.BASIC);

        // when
        int discountPrice = discountPolicy.discount(member, 10000);

        // then
        assertThat(discountPrice).isEqualTo(0);

    }
}