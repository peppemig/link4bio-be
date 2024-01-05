package it.peppemig.link4bio.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;
    private final Environment env;

    public CloudinaryService(Environment env) {
        this.env = env;
        Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("cloud_name", this.env.getProperty("cloudinary.cloud-name"));
        valuesMap.put("api_key", this.env.getProperty("cloudinary.api-key"));
        valuesMap.put("api_secret", this.env.getProperty("cloudinary.api-secret"));
        cloudinary = new Cloudinary(valuesMap);
    }

    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.asMap("transformation", new Transformation().width(500).height(500)));
        if (!Files.deleteIfExists(file.toPath())) {
            throw new IOException("Failed to delete temporary file: " + file.getAbsolutePath());
        }
        return result;
    }

    public Map delete(String id) throws IOException {
        return cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
    }

    public String getImageIdFromUrl(String url) {
        int lastSlashIndex = url.lastIndexOf("/");
        String valueAfterLastSlash = url.substring(lastSlashIndex + 1);
        int lastDotIndex = valueAfterLastSlash.lastIndexOf(".");
        return valueAfterLastSlash.substring(0, lastDotIndex);
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
