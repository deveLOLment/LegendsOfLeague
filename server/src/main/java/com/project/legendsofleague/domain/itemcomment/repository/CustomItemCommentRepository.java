package com.project.legendsofleague.domain.itemcomment.repository;

import com.project.legendsofleague.domain.itemcomment.domain.ItemComment;
import java.util.List;

public interface CustomItemCommentRepository {
    public List<ItemComment> queryParentCommentList(Long itemId);
}
