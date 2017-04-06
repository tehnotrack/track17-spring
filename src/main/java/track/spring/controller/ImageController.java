package track.spring.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class ImageController {

    private Logger log = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;

    @ResponseBody
    @RequestMapping(value = "/transform/{imageId}", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] transform(
            @PathVariable("imageId") Long imageId,
            @RequestParam(value = "op", required = true) String op,
            @RequestParam(value = "angle", required = false) Integer angle,
            @RequestParam(value = "scale", required = false) Double scale,
            HttpServletResponse response) throws IOException {

        response.addHeader("Access-Control-Allow-Origin", "*");

        try {
            final BufferedImage image = imageService.loadImage(imageId);
            BufferedImage out = null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            switch (op) {
                case "rotate":
                    log.info(String.format("Rotate %d on %d", imageId, angle));
                    out = imageService.rotate(image, angle);
                    break;
                case "scale":
                    log.info(String.format("Scale %d ", imageId));
                    out = imageService.scale(image, scale);
                    break;
                default:
                    throw new Exception("Invalid op: " + op);
            }
            ImageIO.write(out, "png", baos);
            return baos.toByteArray();

        } catch (Exception e) {

        }

        InputStream in = this.getClass().getResourceAsStream("/images/service.png");
        return IOUtils.toByteArray(in);
    }
}
