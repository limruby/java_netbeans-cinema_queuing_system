# java_netbeans-cinema_queuing_system

The requirements of the task:
1. here are two types of customers to be served which are VIP member and non-member customers

2. The number of tickets wanted to be bought by the customer are in a range of 1 to 10 tickets

3. Must be at least 2 queues in the system

4. Must be one priority queue which only allow VIP member to queue up.

5. There are 4 service counters in the queueing system.
● Counter 01: Required 10 seconds to sell 1 ticket.
● Counter 02: Required 15 seconds to sell 1 ticket.
● Counter 03: Required 30 seconds to sell 1 ticket.
● Counter 04: Required 15 seconds to sell 1 ticket.

6. When a customer arrive at the cinema, he/she will go to the queue. If the customer is a VIP
member, he/she will go to the priority queue, or else if he/she is a non-member, he/she will go
to the normal queue.

7. VIP member customer must be served before any non-member customer.

8. Whenever there are customers waiting in the queue, the available service counters has to call
the next customer and serve them.

9. There should not be any idle service counter except there is no more customer to serve.
