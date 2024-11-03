package lt.ancienttexts.controller.webapp;

import lt.ancienttexts.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("image")
public class PicturesController {

    @Autowired
    private ImageService imageService;

    @GetMapping(value = "/original/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    byte[] fetchOriginalImage(@PathVariable Long id){
        byte[] response = imageService.fetchOriginalImage(id);
        return response;
    }
}
