package kr.ne.abc.lootbayapi.block.model;

import com.querydsl.core.QueryResults;
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

    public Map<String, Object> findAll(Integer page, Integer size){
        QBlock qBlock = QBlock.block;

        // 페이징 처리를 위한 QueryDSL 사용
        QueryResults<Block> results = queryFactory
                .selectFrom(qBlock)
                .orderBy(qBlock.id.desc())
                .offset(page * size)
                .limit(size)
                .fetchResults();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", results.getResults().stream().map(Block.ResponseDto::new).collect(Collectors.toList()));
        resultMap.put("paging", results.getOffset());
        resultMap.put("totalCnt", results.getTotal());
        resultMap.put("totalPage", results.getTotal() / size + (results.getTotal() % size == 0 ? 0 : 1));

        return resultMap;
    }


}
