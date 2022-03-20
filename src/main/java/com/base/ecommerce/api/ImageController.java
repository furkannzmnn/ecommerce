package com.base.ecommerce.api;

import com.base.ecommerce.core.utils.ResponseApi;
import com.base.ecommerce.service.ImageService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController extends BaseRestController{

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(value = "/upload/{id}" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ResponseApi> uploadFiles(@RequestParam("files") MultipartFile multipartFiles,  @PathVariable int id) throws IOException {
        return ResponseEntity.ok(
                new ResponseApi.ResponseBuilder()
                        .Message("IMAGE SAVE")
                        .Data(Map.of("images->", this.imageService.SingletonProfilePicture(multipartFiles,id)))
                        .builder());
    }

    @GetMapping("/get{id}")
    public ResponseEntity<?> getImage(@PathVariable int id) {
        byte[] image = imageService.getImage(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"${System.currentTimeMillis()}\"")
                .body(image);
    }

    @GetMapping("/albums{id}")
    public ResponseEntity<?> getAlbumsImage(@PathVariable int id) {
        byte[] image = imageService.getAlbumImage(id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=png")
                .body(image);
    }


    @PostMapping("/albums/{id}")
    public ResponseEntity<?> uploadAlbums(@RequestParam("files") MultipartFile[] multipartFiles, @PathVariable int id) throws FileSystemException {
        String image = imageService.UploadAlbums(multipartFiles,id);
        String fileName = Arrays.stream(multipartFiles).map(MultipartFile::getOriginalFilename).toString();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .body(image);
    }


}
