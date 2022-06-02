package com.base.ecommerce.core.image;

import com.cloudinary.Cloudinary;
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


    public ImplUploadService(final Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String upload(final MultipartFile multipartFile) {
        try {
            final File file = convert(multipartFile);
            final Map<?, ?> uploadResult = this.cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            return uploadResult.get("url").toString();
        } catch (Exception e) {
            LOGGER.log(java.util.logging.Level.SEVERE, "Error in upload image", e);
            throw new RuntimeException(e);
        }
    }

    private File convert(final MultipartFile multipartFile) throws IOException {
        final File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try(final FileOutputStream stream = new FileOutputStream(file)) {
            byte[] bytes = multipartFile.getBytes();
            stream.write(bytes);
            stream.flush();
            return file;
        }
    }

    // this method returning list of url of images
    public final List<String> uploadMultipleImage(final MultipartFile[] multipartFiles) {
        final StringBuilder sb = new StringBuilder();

        final Consumer<StringBuilder> consumer = s -> Arrays
                .stream(multipartFiles)
                .forEach(mp -> s.append(upload(mp)).append(IMAGE_SPLIT_PREFIX));

        consumer.accept(sb);

        final String[] split = sb.toString().split(IMAGE_SPLIT_PREFIX);

        return Arrays.stream(split).collect(Collectors.toList());

    }


}
