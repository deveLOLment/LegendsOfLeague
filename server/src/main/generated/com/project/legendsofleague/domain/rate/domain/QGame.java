package com.project.legendsofleague.domain.rate.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QGame is a Querydsl query type for Game
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QGame extends EntityPathBase<Game> {

    private static final long serialVersionUID = -1165730444L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QGame game = new QGame("game");

    public final QTeam blueTeam;

    public final NumberPath<Integer> blueTeamScore = createNumber("blueTeamScore", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isBlueTeamVictory = createBoolean("isBlueTeamVictory");

    public final BooleanPath isRedTeamVictory = createBoolean("isRedTeamVictory");

    public final DateTimePath<java.time.LocalDateTime> matchDate = createDateTime("matchDate", java.time.LocalDateTime.class);

    public final QTeam redTeam;

    public final NumberPath<Integer> redTeamScore = createNumber("redTeamScore", Integer.class);

    public QGame(String variable) {
        this(Game.class, forVariable(variable), INITS);
    }

    public QGame(Path<? extends Game> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QGame(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QGame(PathMetadata metadata, PathInits inits) {
        this(Game.class, metadata, inits);
    }

    public QGame(Class<? extends Game> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.blueTeam = inits.isInitialized("blueTeam") ? new QTeam(forProperty("blueTeam")) : null;
        this.redTeam = inits.isInitialized("redTeam") ? new QTeam(forProperty("redTeam")) : null;
    }

}

