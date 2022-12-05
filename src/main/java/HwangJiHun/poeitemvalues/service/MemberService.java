package HwangJiHun.poeitemvalues.service;

import HwangJiHun.poeitemvalues.model.members.Member;
import HwangJiHun.poeitemvalues.repository.mybatis.H2MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    private final H2MemberRepository repository;

    public MemberService(H2MemberRepository repository) {
        this.repository = repository;
    }

    public Optional<Member> findByUserId(String userId) {
        return repository.findByUserId(userId);
    }

    public Optional<Member> findById(Long id) {
        return repository.findById(id);
    }
}
