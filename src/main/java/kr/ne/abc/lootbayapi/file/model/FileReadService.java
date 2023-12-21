package kr.ne.abc.lootbayapi.file.model;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

@Service
public class FileReadService {

    public String readJsonFile(String filePath) {
        try {
            Resource resource = new ClassPathResource("static/" + filePath);
            byte[] data = FileCopyUtils.copyToByteArray(resource.getInputStream());
            return new String(data, "UTF-8");
        } catch (IOException e) {
            // 파일 읽기 실패 시 예외 처리
            e.printStackTrace();
            return null;
        }
    }
}