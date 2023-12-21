package kr.ne.abc.lootbayapi.file.controller;

import kr.ne.abc.lootbayapi.file.model.FileReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FileReadController {

    private final FileReadService fileReadService;

    @GetMapping("/read-json")
    public ResponseEntity<String> readJsonFile() {
        String filePath = "assets/metadata/service002/Jack Daniel's.json";
        String jsonContent = fileReadService.readJsonFile(filePath);
        System.out.println(jsonContent);
        if (jsonContent != null) {
            return ResponseEntity.ok(jsonContent);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to read JSON file");
        }
    }
}