package main;

public interface ExternalSort {

    default  <T> T changeStream(T currentStream, T tempFileStream1,
            T tempFileStream2) {
        if (currentStream == tempFileStream1) {
            return tempFileStream2;
        } else {
            return tempFileStream1;
        }
    }

}
