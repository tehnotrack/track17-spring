package track.lessons.l7threads.future;

/**
 *
 */
public class Transformer<T extends Image> {

    public Image transform(Image image) {

        // sleep for 2 seconds - imitate long transformation
//        Util.sleepQuietly(TimeUnit.SECONDS, 2);
        return image;
    }
}
