package com.project.legendsofleague.domain.rate.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRate is a Querydsl query type for Rate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRate extends EntityPathBase<Rate> {

    private static final long serialVersionUID = -1165402526L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRate rate = new QRate("rate");

    public final QGame game;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.project.legendsofleague.domain.member.domain.QMember member;

    public final QPlayer player;

    public final NumberPath<Integer> score = createNumber("score", Integer.class);

    public QRate(String variable) {
        this(Rate.class, forVariable(variable), INITS);
    }

    public QRate(Path<? extends Rate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRate(PathMetadata metadata, PathInits inits) {
        this(Rate.class, metadata, inits);
    }

    public QRate(Class<? extends Rate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.game = inits.isInitialized("game") ? new QGame(forProperty("game"), inits.get("game")) : null;
        this.member = inits.isInitialized("member") ? new com.project.legendsofleague.domain.member.domain.QMember(forProperty("member")) : null;
        this.player = inits.isInitialized("player") ? new QPlayer(forProperty("player"), inits.get("player")) : null;
    }

}

