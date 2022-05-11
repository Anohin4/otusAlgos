package test.java.ticketTest;

import main.java.middle.AdvancedLuckyTicket;

public class AdvancedRecursiveTest extends AbstractLuckyTicketTest{

  @Override
  long getLuckyCounter(int n) {
    AdvancedLuckyTicket ticket = new AdvancedLuckyTicket();
    return ticket.getLuckyCounter(n);
  }
}
