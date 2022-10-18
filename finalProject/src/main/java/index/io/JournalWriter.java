package index.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import type.JournalEntity;

public class JournalWriter implements Runnable {

    private boolean work = true;
    private ConcurrentLinkedQueue<JournalEntity> journalQueue;
    private final File journal;
    private Lock lock = new ReentrantLock();

    private AtomicBoolean flag = new AtomicBoolean(false);
    public JournalWriter(ConcurrentLinkedQueue<JournalEntity> journalQueue, File journal) {
        this.journalQueue = journalQueue;
        this.journal = journal;

    }

    @Override
    public void run() {
        while (work) {
            if (!journalQueue.isEmpty()) {
                try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(journal, true))) {
                    while (!journalQueue.isEmpty()) {
                        JournalEntity journalEntity = journalQueue.poll();
                        fileWriter.write(journalEntity.toString());
                        fileWriter.write(System.getProperty("line.separator"));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            if (flag.get()) {
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter(journal);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                writer.print("");
                writer.close();
                flag.set(false);
            }
        }
    }


    public void clearFile() throws IOException, InterruptedException {
        flag.set(true);

    }

    public void stop() {
        this.work = false;
    }


}
