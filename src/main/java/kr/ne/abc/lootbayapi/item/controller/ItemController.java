package kr.ne.abc.lootbayapi.item.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.ne.abc.lootbayapi.item.entity.ItemData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class ItemController {

    @Value("${metadata.folder.path}")
    private String metadataFolderPath;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/send-data")
    public void receiveDataFromJavaScript(@RequestBody String jsonData) {
        try {
            System.out.println("Received data from JavaScript: " + jsonData);

            // Convert JSON data to a list of ItemData
            List<ItemData> itemDataList = objectMapper.readValue(
                    jsonData,
                    new TypeReference<List<ItemData>>() {}
            );

            // Check if the list is not null and not empty before processing
            if (itemDataList == null || itemDataList.isEmpty()) {
                System.err.println("Received data from JavaScript is null or empty");
                return;
            }

            // Save each item to a file
            itemDataList.forEach(this::saveDataToFile);

        } catch (IOException e) {
            // Handle the exception in a more appropriate way, e.g., sending an error response to the client
            e.printStackTrace();
        }
    }

    private void saveDataToFile(ItemData itemData) {
        createMetadataFolderIfNotExists(itemData.getServiceId());

        // Construct the file path
        String fileName = itemData.getName() + ".json";
        String filePath = metadataFolderPath + File.separator + itemData.getServiceId() + File.separator + fileName;

        // Save the data to the file
        try {
            objectMapper.writeValue(new File(filePath), itemData);
        } catch (IOException e) {
            // Handle the exception in a more appropriate way, e.g., sending an error response to the client
            e.printStackTrace();
        }
    }

    private void createMetadataFolderIfNotExists(String serviceId) {
        // Create the metadata folder if it doesn't exist
        File folder = new File(metadataFolderPath + File.separator + serviceId);
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                System.out.println("Metadata folder created: " + folder.getPath());
            } else {
                System.err.println("Failed to create metadata folder: " + folder.getPath());
            }
        }
    }
}