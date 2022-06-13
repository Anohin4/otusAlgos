package main.improvedSort;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class ArrayStructure {
    private short[] currentArray;
    private DataInputStream currentStream;
    private int currentArrayIndex;
    private final int arraySize;
    private final int currentDivider;
    private int numberOfRun;
    private short currentShortValue;
    boolean isFinished;

    public ArrayStructure(DataInputStream currentStream,
            int currentDivider, int arraySize) throws IOException {
        this.currentStream = currentStream;
        this.currentArrayIndex = 0;
        this.arraySize = arraySize;
        this.currentDivider = currentDivider;
        this.currentArray = readShortArray();
        this.currentShortValue = this.currentArray[0];
        this.isFinished = false;
    }

    private short[] readShortArray() throws IOException {
        int size = arraySize / 2;
        byte[] byteArray =
                currentStream.available() >= size ? currentStream.readNBytes(size) : currentStream.readAllBytes();
        short[] shortArray = new short[byteArray.length / 2];
        ByteBuffer.wrap(byteArray).order(ByteOrder.BIG_ENDIAN).asShortBuffer().get(shortArray);
        return shortArray;
    }

    public short getAndUpdate() throws IOException {
        short tempResult = getCurrentShortValue();
        setNextShortValue();
        return tempResult;
    }

    private void setNextShortValue() throws IOException {
        if (isFinished) {
            return;
        }
        currentArrayIndex++;

        if(currentArrayIndex >= currentArray.length) {
            currentArrayIndex = 0;
            updateShortArray();
        }

        if (currentArray.length == 0) {
            isFinished = true;
            return;
        }
        currentShortValue = currentArray[currentArrayIndex];
    }

    private void updateShortArray() throws IOException {
        numberOfRun++;
        if (numberOfRun == currentDivider) {
            isFinished = true;
            return;
        }
        currentArray = readShortArray();
    }

    public short getCurrentShortValue() {
        return currentShortValue;
    }

    public boolean isFinished() {
        return isFinished;
    }

}
