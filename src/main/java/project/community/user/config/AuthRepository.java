//package project.community.user.config;
//
//import org.apache.ibatis.annotations.Mapper;
//import org.springframework.data.domain.Example;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.query.FluentQuery;
//import project.community.user.dto.CustomUser;
//
//
//import java.util.List;
//import java.util.Optional;
//import java.util.function.Function;
//
//@Mapper
//public interface AuthRepository extends JpaRepository<CustomUser, Long> {
//
//    @Override
//    default List<CustomUser> findAll() {
//        return null;
//    }
//
//    @Override
//    default List<CustomUser> findAll(Sort sort) {
//        return null;
//    }
//
//    @Override
//    default List<CustomUser> findAllById(Iterable<Long> longs) {
//        return null;
//    }
//
//    @Override
//    default <S extends CustomUser> List<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    default void flush() {
//
//    }
//
//    @Override
//    default <S extends CustomUser> S saveAndFlush(S entity) {
//        return null;
//    }
//
//    @Override
//    default <S extends CustomUser> List<S> saveAllAndFlush(Iterable<S> entities) {
//        return null;
//    }
//
//    @Override
//    default void deleteAllInBatch(Iterable<CustomUser> entities) {
//
//    }
//
//    @Override
//    default void deleteAllByIdInBatch(Iterable<Long> longs) {
//
//    }
//
//    @Override
//    default void deleteAllInBatch() {
//
//    }
//
//    @Override
//    default CustomUser getOne(Long aLong) {
//        return null;
//    }
//
//    @Override
//    default CustomUser getById(Long aLong) {
//        return null;
//    }
//
//    @Override
//    default CustomUser getReferenceById(Long aLong) {
//        return null;
//    }
//
//    @Override
//    default <S extends CustomUser> List<S> findAll(Example<S> example) {
//        return null;
//    }
//
//    @Override
//    default <S extends CustomUser> List<S> findAll(Example<S> example, Sort sort) {
//        return null;
//    }
//
//    @Override
//    default Page<CustomUser> findAll(Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    default <S extends CustomUser> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    default Optional<CustomUser> findById(Long aLong) {
//        return Optional.empty();
//    }
//
//    @Override
//    default boolean existsById(Long aLong) {
//        return false;
//    }
//
//    @Override
//    default long count() {
//        return 0;
//    }
//
//    @Override
//    default void deleteById(Long aLong) {
//
//    }
//
//    @Override
//    default void delete(CustomUser entity) {
//
//    }
//
//    @Override
//    default void deleteAllById(Iterable<? extends Long> longs) {
//
//    }
//
//    @Override
//    default void deleteAll(Iterable<? extends CustomUser> entities) {
//
//    }
//
//    @Override
//    default void deleteAll() {
//
//    }
//
//    @Override
//    default <S extends CustomUser> Optional<S> findOne(Example<S> example) {
//        return Optional.empty();
//    }
//
//    @Override
//    default <S extends CustomUser> Page<S> findAll(Example<S> example, Pageable pageable) {
//        return null;
//    }
//
//    @Override
//    default <S extends CustomUser> long count(Example<S> example) {
//        return 0;
//    }
//
//    @Override
//    default <S extends CustomUser> boolean exists(Example<S> example) {
//        return false;
//    }
//
//    @Override
//    default <S extends CustomUser, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
//        return null;
//    }
//
//    CustomUser findCustomUserByEmail(String email);
//}
