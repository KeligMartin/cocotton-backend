package fr.esgi.cocotton.infrastructure.user.persistance;

import fr.esgi.cocotton.domain.models.user.User;
import fr.esgi.cocotton.domain.models.user.UserDao;
import fr.esgi.cocotton.infrastructure.common.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaUserDao implements UserDao {
    private final JpaUserRepository repository;
    private final UserMapper mapper;

    @Autowired
    public JpaUserDao(JpaUserRepository repository, UserMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<User> findAll(){
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<User> findById(String id){
        Optional<JpaUser> jpaUser = repository.findById(id);
        return jpaUser.map(user -> new User(
                user.getId(), user.getFirstName(),
                user.getLastName(), user.getEmail(),
                user.getPassword(), user.getGender(),
                user.getBirthDate()
        ));
    }

    public String save(User user){
        JpaUser jpaUser = new JpaUser( user.getId(),
                user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getPassword(),
                user.getGender(), user.getBirthDate());
        repository.save(jpaUser);

        return jpaUser.getId();
    }

    @Transactional
    public void deleteById(String id){
        repository.deleteById(id);
    }
}
