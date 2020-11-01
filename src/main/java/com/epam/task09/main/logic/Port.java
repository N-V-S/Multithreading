package com.epam.task09.main.logic;

import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class Port {

    private static final ReentrantLock INSTANCE_LOCK = new ReentrantLock();
    private static final ReentrantLock PIERS_LOCK = new ReentrantLock();

    private static final AtomicBoolean instanceCreated = new AtomicBoolean(false);
    private static final int PIERS_NUMBER = 3;

    private static Port instance;
    private final Queue<Pier> piers;
    private final Semaphore semaphore = new Semaphore(PIERS_NUMBER, true);

    private Port() {
        piers = new LinkedList<Pier>();
        for(int i = 0; i < PIERS_NUMBER; i++){
            Pier pier = new Pier(this);
            piers.add(pier);
        }
    }

    public static Port getInstance(){
        if (!instanceCreated.get()) {
            INSTANCE_LOCK.lock();
            try {
                if (!instanceCreated.get()) {
                    instance = new Port();
                    instanceCreated.set(true);
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return instance;
    }

    public Pier acquirePier() throws PortException {
        try {
            semaphore.acquire();
            PIERS_LOCK.lock();
            Pier pier = piers.poll();
            return pier;
        } catch (InterruptedException e) {
            throw new PortException(e.getMessage(), e);
        } finally {
            PIERS_LOCK.unlock();
        }
    }

    public void releasePier(Pier pier) {
        PIERS_LOCK.lock();
        try {
            piers.offer(pier);
            semaphore.release();
        } finally {
            PIERS_LOCK.unlock();
        }
    }
}