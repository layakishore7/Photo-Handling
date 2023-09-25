package PhotoHandling.service;

import PhotoHandling.entity.Photo;
import PhotoHandling.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    private final String FOLDER_PATH = "C:/Users/Developer/Desktop/sendphotos/";


    public String uploadImage(MultipartFile file) throws IOException {
        String filepath = FOLDER_PATH+file.getOriginalFilename();

        Photo photo = photoRepository.save(Photo.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(filepath).build());
        file.transferTo(new File(filepath));
        return "Photo Uploaded Successfully" + filepath;
    }


    public  byte[] downloadImage(String fileName) throws IOException {
        Optional<Photo> optionalPhoto = photoRepository.findByName(fileName);
        String image = optionalPhoto.get().getImage();
        byte[] images = Files.readAllBytes(new File(image).toPath());
        return images;
    }



}
