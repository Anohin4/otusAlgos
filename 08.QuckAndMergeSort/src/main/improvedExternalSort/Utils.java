package main.improvedExternalSort;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public class Utils {

    public static short[] readShortArray(DataInputStream inputStream, int size) throws IOException {
        byte[] byteArray =
                inputStream.available() >= size ? inputStream.readNBytes(size) : inputStream.readAllBytes();
        short[] shortArray = new short[byteArray.length / 2];
        ByteBuffer.wrap(byteArray).order(ByteOrder.BIG_ENDIAN).asShortBuffer().get(shortArray);
        return shortArray;
    }

    public static void writeShortArray(DataOutputStream outputStream, short[] array) throws IOException {
        ByteBuffer myByteBuffer = ByteBuffer.allocate(array.length * 2);
        myByteBuffer.order(ByteOrder.BIG_ENDIAN);

        ShortBuffer myShortBuffer = myByteBuffer.asShortBuffer();
        myShortBuffer.put(array);

        outputStream.write(myByteBuffer.array());
    }

}
