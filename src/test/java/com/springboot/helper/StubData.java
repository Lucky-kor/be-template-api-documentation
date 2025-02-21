package com.springboot.helper;

import com.springboot.member.dto.MemberDto;
import com.springboot.member.entity.Member;
import com.springboot.order.entity.Order;
import com.springboot.stamp.Stamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class StubData {
    private static Map<HttpMethod, Object> stubRequestBody;
    static {
        stubRequestBody = new HashMap<>();
        stubRequestBody.put(HttpMethod.POST, new MemberDto.Post("hgd@gmail.com","홍길동",
                "010-1111-1111"));
        stubRequestBody.put(HttpMethod.PATCH, new MemberDto.Patch(0, null, "010-2222-2222", null));
    }

    public static class MockMember {
        public static Object getRequestBody(HttpMethod method) {
            return stubRequestBody.get(method);
        }

        public static Member getSingleResponseBody() {
            Member member = new Member("hgd1@gmail.com", "홍길동1", "010-1111-1111");
            member.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
            member.setStamp(new Stamp());


            return member;
        }

        public static Page<Member> getMultiResponseBody() {
            Member member1 = new Member("hgd1@gmail.com", "홍길동1", "010-1111-1111");
            member1.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
            member1.setStamp(new Stamp());

            Member member2 = new Member("hgd2@gmail.com", "홍길동2", "010-2222-2222");
            member2.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
            member2.setStamp(new Stamp());

            return new PageImpl<>(List.of(member1, member2),
                    PageRequest.of(0, 10, Sort.by("memberId").descending()),
                    2);
        }

        public static Member getMultiResponseBody(long memberId) {
            Member member = new Member("hgd@gmail.com", "홍길동", "010-1111-1111");
            member.setMemberId(memberId);
            member.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
            member.setStamp(new Stamp());
            return member;
        }

        public static Member getMultiResponseBody(long memberId, Map<String, String> updatedInfo) {
            String name = Optional.ofNullable(updatedInfo.get("name")).orElse("홍길동");
            String phone = Optional.ofNullable(updatedInfo.get("phone")).orElse("010-1111-1111");
            Member member = new Member("hgd@gmail.com", name, phone);
            member.setMemberId(memberId);
            member.setMemberStatus(Member.MemberStatus.MEMBER_ACTIVE);
            member.setStamp(new Stamp());
            return member;
        }
    }

    public static class MockOrder {
        public static Order getResponseBody(long orderId) {
            Order order = new Order();
            order.setOrderId(orderId);
            order.setOrderStatus(Order.OrderStatus.ORDER_CONFIRM);

            return order;
        }
    }
}
