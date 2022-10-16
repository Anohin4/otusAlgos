package index.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import type.JournalEntity;

public class JournalWriter implements Runnable {

    private boolean work = true;
    private ConcurrentLinkedQueue<JournalEntity> journalQueue;
    private final File journal;
    private Lock lock = new ReentrantLock();

    public JournalWriter(ConcurrentLinkedQueue<JournalEntity> journalQueue, File journal) {
        this.journalQueue = journalQueue;
        this.journal = journal;

    }

    @Override
    public void run() {
        while (work) {
            if (!journalQueue.isEmpty()) {
                lock.lock();
                try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(journal, true))) {
                    while (!journalQueue.isEmpty()) {
                        JournalEntity journalEntity = journalQueue.poll();
                        fileWriter.write(journalEntity.toString());
                        fileWriter.write(System.getProperty("line.separator"));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                lock.unlock();
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }


    public void clearFile() throws IOException {
        lock.lock();
        PrintWriter writer = new PrintWriter(journal);
        writer.print("");
        writer.close();
        lock.unlock();

    }

    public void stop() {
        this.work = false;
    }


}
