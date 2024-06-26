package akatsuki.moodholic.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {
    @Value("${spring.cloud.gcp.storage.bucket}") // application.yml에 써둔 bucket 이름
    private  String bucketName;
    @Value("${spring.cloud.gcp.storage.project-id}")
    private  String projectId;
    @Value("${spring.cloud.gcp.storage.credentials.location}")
    private String credentialsLocation;

    public String uploadImage(MultipartFile file) throws IOException {
        ClassPathResource resource = new ClassPathResource(credentialsLocation);
        GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream());
        Storage storage = getStorage(credentials);
        String fileName = generateFileName(file.getOriginalFilename());
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        byte[] bytes = file.getBytes();
        Blob blob = storage.create(blobInfo, bytes);
        return blob.getMediaLink();
    }

    public String profileUploadImage(long memberId,MultipartFile file) throws IOException {
        ClassPathResource resource = new ClassPathResource(credentialsLocation);
        GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream());
        Storage storage = getStorage(credentials);
        String fileName = generateFileName(file.getOriginalFilename());
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        byte[] bytes = file.getBytes();
        Blob blob = storage.create(blobInfo, bytes);
        return blob.getMediaLink();
    }

    public String diaryUploadImage(int diaryId,MultipartFile file) throws IOException {
        ClassPathResource resource = new ClassPathResource(credentialsLocation);
        GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream());
        Storage storage = getStorage(credentials);
        String fileName = generateFileName(file.getOriginalFilename());
        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        byte[] bytes = file.getBytes();
        Blob blob = storage.create(blobInfo, bytes);
        return blob.getMediaLink();
    }

    private Storage getStorage(GoogleCredentials credentials) {
        return StorageOptions.newBuilder()
                .setCredentials(credentials)
                .setProjectId(projectId)
                .build()
                .getService();
    }

    private String generateFileName(String originalName) {
        return UUID.randomUUID().toString();
    }

}
