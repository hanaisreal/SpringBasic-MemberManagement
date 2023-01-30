package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    public void findAllBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class); //여기에 클래스 정보를 넘기면 해당 클래스가 스프링 빈으로 자동 등록된다.
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy"); //주의! FixDiscountPolicy.java에 @Component가 붙어있는지 확인하자!
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPrice).isEqualTo(2000);
    }

    static class DiscountService {
        //DiscountPolicy가 주입된다. 이때 fixDiscountPolicy , rateDiscountPolicy도 주입된다
        private final Map<String, DiscountPolicy> policyMap;  //key = 스프링 빈 이름, value= DiscountPolicy 타입으로 조회한 모든 스프링 빈
        private final List<DiscountPolicy> policies;

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);  //discountCode로 "fixDiscountPolicy"가 넘어오면 map에서 fixDiscountPolicy 또는 rateDiscountPolicy스프링 빈을 찾아서 실행한다
            return discountPolicy.discount(member, price);
        }
    }
}
