package com.base.ecommerce.service;

import com.base.ecommerce.model.Image;
import com.base.ecommerce.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final ProductService productService;

    public ImageService(ImageRepository imageRepository, ProductService productService) {
        this.imageRepository = imageRepository;
        this.productService = productService;
    }


    public String UploadAlbums(MultipartFile[] multipartFile, int id) throws FileSystemException {
        List<Image> successImageList = new ArrayList<>();
        Map<String,Object> failedImageListLog = new HashMap<>();

        if (multipartFile == null){
            throw new FileSystemException("ops");
        }
        for (MultipartFile file : multipartFile) {
            try {
                Image image = imageRepository.findById(id).orElseThrow();

                byte[] newFile = file.getBytes();
                image.setPictureAlbums(newFile);
                successImageList.add(image);

                imageRepository.saveAll(successImageList);
            } catch (IOException e) {
                failedImageListLog.put("failed file name" ,file.getOriginalFilename());
                throw new FileSystemException(failedImageListLog.get(file.getOriginalFilename()).toString());
            }
        }
        return successImageList.stream().map(Image::getPictureAlbums).collect(Collectors.toList()).toString();
    }

    public Image SingletonProfilePicture(MultipartFile multipartFile, int id) throws IOException {

        Image image = imageRepository.findById(id).orElseThrow(() -> new SecurityException("image not found"));

        try {
            image.setProfilePicture(multipartFile.getBytes());
            imageRepository.save(image);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return image;
    }

    public byte[] getImage(int imageId) {
        Image image = imageRepository.findById(imageId).orElse(new Image());
        return image.getProfilePicture();
    }
    public byte[] getAlbumImage(int imageId) {
        Image image = imageRepository.findById(imageId).orElse(new Image());
        return  image.getPictureAlbums();
    }

}