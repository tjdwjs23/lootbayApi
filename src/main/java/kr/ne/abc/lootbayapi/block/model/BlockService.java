package kr.ne.abc.lootbayapi.block.model;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ne.abc.lootbayapi.block.entity.Block;
import kr.ne.abc.lootbayapi.block.entity.BlockRepository;
import kr.ne.abc.lootbayapi.block.entity.QBlock;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class BlockService {

    private final JPAQueryFactory queryFactory;

    QBlock qBlock = QBlock.block;

    public List<Block> findAll(Integer page, Integer size){
        QBlock qBlock = QBlock.block;

        List<Block> blockList = queryFactory
                .selectFrom(qBlock)
                .orderBy(qBlock.id.desc())
                .offset(page * size)
                .limit(size)
                .fetch();

        return blockList;
    }


}
