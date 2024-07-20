package com.ajar.boardsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ajar.boardsystem.model.Tuto;
//@Repository를 안 넣었는데도 잘 되는 건 Jpa~를 상속했기 때문에 어련히 bean 이겠거니 해서 동작
public interface TutoRepository extends JpaRepository<Tuto, Long>{
    
    List<Tuto> findByPublished(boolean published);
    List<Tuto> findByTitleContaining(String title);
}

// @Modifying
//     @Transactional
//     @Query("UPDATE Tuto t SET t.description = :newDescription WHERE t.title = :title")
//     int updateDescriptionByTitle(@Param("title") String title, @Param("newDescription") String newDescription);


// Now we can use JpaRepository’s methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()… without implementing these methods.
//
// We also define custom finder methods:
// – findByPublished(): returns all Tutorials with published having value as input published.
// – findByTitleContaining(): returns all Tutorials which title contains input title.
// save(S entity):

// 엔티티를 저장하거나 업데이트합니다.
// 새로운 엔티티인 경우에는 저장하고, 이미 존재하는 엔티티의 경우에는 업데이트합니다.
// 반환값은 저장된 엔티티입니다.
// saveAll(Iterable<S> entities):

// 여러 엔티티를 저장하거나 업데이트합니다.
// 반환값은 저장된 엔티티의 리스트입니다.
// findById(ID id):

// 주어진 ID에 해당하는 엔티티를 찾습니다.
// 해당 ID에 해당하는 엔티티가 없으면 Optional.empty()를 반환합니다.
// existsById(ID id):

// 주어진 ID에 해당하는 엔티티가 존재하는지 확인합니다.
// 존재하면 true, 그렇지 않으면 false를 반환합니다.
// findAll():

// 모든 엔티티를 조회합니다.
// 반환값은 Iterable 타입입니다.
// findAllById(Iterable<ID> ids):

// 주어진 ID 리스트에 해당하는 모든 엔티티를 조회합니다.
// 반환값은 Iterable 타입입니다.
// count():

// 데이터베이스에 저장된 엔티티의 수를 반환합니다.
// deleteById(ID id):

// 주어진 ID에 해당하는 엔티티를 삭제합니다.
// delete(T entity):

// 주어진 엔티티를 삭제합니다.
// deleteAll(Iterable<? extends T> entities):

// 주어진 엔티티 리스트에 해당하는 모든 엔티티를 삭제합니다.
// deleteAll():

// 데이터베이스에 있는 모든 엔티티를 삭제합니다.
// flush():

// 영속성 컨텍스트의 변경 내용을 데이터베이스에 즉시 반영합니다.
// findBy{PropertyName}():

// 지정된 엔티티 속성에 따라 엔티티를 검색합니다. 예를 들어, findByTitle(String title)은 title 속성이 주어진 값과 일치하는 엔티티를 검색합니다.