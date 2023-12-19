package kr.ne.abc.lootbayapi.block.controller;

import kr.ne.abc.lootbayapi.block.entity.Block;
import kr.ne.abc.lootbayapi.block.model.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class BlockController {

    private final BlockService blockService;

    /**
     * getBlock()
     *
     * @return
     */
    @GetMapping("/list")
    public String getBlock(Model model,
                           @RequestParam(required=false, defaultValue = "0") Integer page,
                           @RequestParam(required=false, defaultValue = "5") Integer size
        ) {
        List<Block> blockList = blockService.findAll(page, size); // Assuming Bridge is the type returned by getHistory method
        model.addAttribute("blockList", blockList);
        return "block/list";
    }
}
