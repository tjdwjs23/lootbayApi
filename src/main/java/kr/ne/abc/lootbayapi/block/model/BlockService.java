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
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class BlockService {

    private final BlockRepository blockRepository;

    public Map<String, Object> findAll(Integer page, Integer size){
        Map<String, Object> resultMap = new HashMap<>();

        Page<Block> list = blockRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id")));

        resultMap.put("list", list.stream().map(Block.ResponseDto::new).collect(Collectors.toList()));
        resultMap.put("paging", list.getPageable());
        resultMap.put("totalCnt", list.getTotalElements());
        resultMap.put("totalPage", list.getTotalPages());

        return resultMap;
    }


}
