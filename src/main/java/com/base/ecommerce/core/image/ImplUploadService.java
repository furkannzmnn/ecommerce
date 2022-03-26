package com.base.ecommerce.core.image;

import com.cloudinary.Cloudinary;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class ImplUploadService implements UploadService {

    private final Cloudinary cloudinary;
    public static final String IMAGE_SPLIT_PREFIX = "image_";
    private static final Logger LOGGER = Logger.getLogger(ImplUploadService.class.getName());


    public ImplUploadService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String upload(MultipartFile multipartFile) {


        try {
            File file = convert(multipartFile);
            Map<?, ?> uploadResult = this.cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            return uploadResult.get("url").toString();
        } catch (Exception e) {
            LOGGER.log(java.util.logging.Level.SEVERE, "Error in upload image", e);
            throw new RuntimeException(e);
        }
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream stream = new FileOutputStream(file);
        byte[] bytes = multipartFile.getBytes();
        stream.write(bytes);
        stream.close();
        return file;
    }

    // this method returning list of url of images
    public List<String> uploadMultipleImage(MultipartFile[] multipartFiles) {
        StringBuilder sb = new StringBuilder();

        Consumer<StringBuilder> consumer = s -> Arrays.stream(multipartFiles).forEach(multipartFile ->
                s.append(multipartFile.getOriginalFilename()).append(IMAGE_SPLIT_PREFIX));

        consumer.accept(sb);

        final String[] split = sb.toString().split(IMAGE_SPLIT_PREFIX);

        return Arrays.stream(split).collect(Collectors.toList());


    }

    private ApiResponse createFolderForApi() {
        try {
           return cloudinary.api().createFolder("/kullanatnoktacom", ObjectUtils.emptyMap());
        } catch (Exception e) {
            LOGGER.log(java.util.logging.Level.SEVERE, "Error in upload image", e);
            throw new RuntimeException(e);
        }
    }

    private String downloadArchive() {
        try {
            return cloudinary.downloadFolder("/kullanatnoktacom", ObjectUtils.emptyMap());
        } catch (Exception e) {
            LOGGER.log(java.util.logging.Level.SEVERE, "Error in upload image", e);
            throw new RuntimeException(e);
        }
    }


}
