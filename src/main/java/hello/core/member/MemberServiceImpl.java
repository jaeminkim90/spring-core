package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired // 마치 ac.getBean(MemberRepository.class)와 같이 해당 타입으로 조회되는 Bean을 찾아서 등록한다.
    public MemberServiceImpl(MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // AppConfig에서 2번 생성된 MemberRepository 객체의 동일 여부 확인을 위해 임시로 만든 메서드
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
