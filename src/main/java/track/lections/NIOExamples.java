package track.lections;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioExamples {
    public static void main(String[] args) {

    }

    public void basicChannel() throws IOException {
        RandomAccessFile file = new RandomAccessFile("data/nio-data.txt", "rw");
        FileChannel inChannel = file.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            buf.flip();

            while (buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        file.close();
    }

    public void basicBufferUsage() throws IOException {
        RandomAccessFile file = new RandomAccessFile("data/nio-data.txt", "rw");
        FileChannel inChannel = file.getChannel();

//create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf); //read into buffer.
        while (bytesRead != -1) {

            buf.flip();  //make buffer ready for read

            while (buf.hasRemaining()){
                System.out.print((char) buf.get()); // read 1 byte at a time
            }

            buf.clear(); //make buffer ready for writing
            bytesRead = inChannel.read(buf);
        }
        file.close();
    }
}
