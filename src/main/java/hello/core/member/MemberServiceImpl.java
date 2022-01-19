package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

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
