package test.java.ticketTest;

import main.java.middle.AdvancedRecursiveLuckyTicket;

public class AdvancedRecursiveTest extends AbstractLuckyTicketTest{

  @Override
  long getLuckyCounter(int n) {
    AdvancedRecursiveLuckyTicket ticket = new AdvancedRecursiveLuckyTicket();
    return ticket.getLuckyCounter(n);
  }
}
