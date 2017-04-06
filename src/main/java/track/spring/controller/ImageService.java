package track.spring.controller;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class ImageService {

    public BufferedImage loadImage(Long imageId) throws Exception {
        // STUB
        InputStream in = this.getClass().getResourceAsStream("/images/service.png");
        BufferedImage bufferedImage = ImageIO.read(in);
        return bufferedImage;
    }

    public BufferedImage rotate(BufferedImage image, int angle) {
        AffineTransform at = new AffineTransform();

        // Поворот относительно центра
        at.rotate(Math.toRadians(angle), image.getWidth() / 2, image.getHeight() / 2);
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);

        BufferedImage out = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        op.filter(image, out);
        return out;
    }

    public BufferedImage scale(BufferedImage image, double scale) {
        AffineTransform at = new AffineTransform();

        // Пропорциональное масштабирование
        at.scale(scale, scale);
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);

        int width = (int) (image.getWidth() * scale);
        int height = (int) (image.getHeight() * scale);

        BufferedImage out = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        op.filter(image, out);
        return out;
    }
}
