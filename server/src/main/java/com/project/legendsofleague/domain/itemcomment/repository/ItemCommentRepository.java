package com.project.legendsofleague.domain.itemcomment.repository;

import com.project.legendsofleague.domain.itemcomment.domain.ItemComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCommentRepository extends JpaRepository<ItemComment, Long>, CustomItemCommentRepository {

}
