package com.nray.nttback.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("api/file")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    public static final String CUSTOMERS_JSON = "customers.json";
    public static final String CUSTOMER_LIST_NTT = "customer-list-ntt";
    private final S3Client s3;

    public FileController() {
        this.s3 = S3Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }

    @GetMapping("/download-json")
    public String downloadJson() {
        try {
            logger.info("Starting the process to download the JSON file.");

            GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                    .bucket(CUSTOMER_LIST_NTT)
                    .key(CUSTOMERS_JSON)
                    .build();

            String downloadFolder = System.getProperty("user.home") + "\\Downloads";
            Path saveLocation = Path.of(downloadFolder, CUSTOMERS_JSON);

            logger.info("Download location: {}", saveLocation.toAbsolutePath());
            logger.info("Downloading file from S3 bucket: {}", CUSTOMER_LIST_NTT);

            try (InputStream inputStream = s3.getObject(getObjectRequest)) {
                Files.copy(inputStream, saveLocation, StandardCopyOption.REPLACE_EXISTING);
                logger.info("File downloaded successfully to: {}", saveLocation.toAbsolutePath());
            }

            return "File downloaded successfully to: " + saveLocation.toAbsolutePath();

        } catch (S3Exception e) {
            logger.error("Error downloading file from S3: {}", e.awsErrorDetails().errorMessage());
            return "Error downloading file: " + e.awsErrorDetails().errorMessage();
        } catch (Exception e) {
            logger.error("An error occurred: {}", e.getMessage(), e);
            return "An error occurred: " + e.getMessage();
        }
    }
}
