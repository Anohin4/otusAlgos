package test.java.ticketTest;

import main.java.middle.SimpleRecursiveLuckyTicket;

public class SimpleRecursiveTest extends AbstractLuckyTicketTest{

  @Override
  long getLuckyCounter(int n) {
    SimpleRecursiveLuckyTicket ticket = new SimpleRecursiveLuckyTicket();
    return ticket.getLuckyСounter(n);
  }
}
