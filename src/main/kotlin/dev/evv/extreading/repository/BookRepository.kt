package dev.evv.extreading.repository

import dev.evv.extreading.model.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.querydsl.QuerydslPredicateExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BookRepository : JpaRepository<BookEntity, UUID>, QuerydslPredicateExecutor<BookEntity> {

//    search(searchRequest:Book, Pageable pageable): Page<BookEntity> {
//
//         bookEntity:QBookEntity = QWordCardEntity.wordCardEntity;

//        JPAQuery<WordCardEntity> query = new JPAQuery<>(getEm());
//
//        BooleanBuilder whereCause = new BooleanBuilder();
//        if(searchRequest != null){
//            if(ObjectUtils.isNotEmpty(searchRequest.getLanguageId())) {
//                whereCause.and(wordCardEntity.language.id.eq(searchRequest.getLanguageId()));
//            }
//            if(ObjectUtils.isNotEmpty(searchRequest.getWord())) {
//                whereCause.and(wordCardEntity.word.likeIgnoreCase(searchRequest.getWord() + "%"));
//            }
//            if(!CollectionUtils.isEmpty(searchRequest.getTags())) {
//                final List<Long> tagIds = searchRequest.getTags().stream()
//                    .map(tag -> tag.getId())
//                .collect(Collectors.toList());
//                whereCause.and(wordCardEntity.tags.any().id.in(tagIds));
//            }
//        }
//
//        query.from(wordCardEntity).where(whereCause);
//        if(pageable.isPaged()){
//            query.offset(pageable.getOffset()).limit(pageable.getPageSize());
//        }
//        // default order
//        query.orderBy(wordCardEntity.dateCreated.desc());
//
//        QueryResults<WordCardEntity> results = query.fetchResults();
//        // Convert back to a normal spring search result.
//        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
//    }

}