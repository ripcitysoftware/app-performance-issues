package com.ripcitysoftware.apm;

import org.springframework.stereotype.Service;

/**
 * Service to simulate a deadlock
 *
 * http://www.java2novice.com/java-interview-programs/thread-deadlock/ - impl https://www.tutorialspoint.com/java/java_thread_deadlock.htm
 * -
 */
@Service
public class DeadLock {

  String resource1 = "first resource";
  String resource2 = "second resource";

  Thread first = new Thread("Thread 1") {
    @Override
    public void run() {
      while (true) {
        synchronized (resource1) {
          System.out.println("Thread 1 got resource 1");
          synchronized (resource2) {
            System.out.println("Thread 1 got resource 2");
          }
        }
      }

    }
  };

  Thread second = new Thread("Thread 2") {
    @Override
    public void run() {
      while (true) {
        synchronized (resource2) {
          System.out.println("Thread 2 got resource 2");
          synchronized (resource1) {
            System.out.println("Thread 2 got resource 1");
          }
        }
      }
    }
  };

  public void deadlock() {
    first.start();
    second.start();
  }

  public static void main(String[] args) {
    DeadLock ls = new DeadLock();
    ls.first.start();
    ls.second.start();
  }
}
