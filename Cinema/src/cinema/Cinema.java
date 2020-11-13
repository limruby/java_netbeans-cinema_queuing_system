/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import java.util.Scanner;

/**
 *
 * @author User
 */
public class Cinema {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner s =new Scanner(System.in);
        System.out.println("Number of customer ");
        int a =s.nextInt();
//get info for each customer
        Queuing customer = new Queuing();
        for(int i =1;i<=a;i++){
            System.out.println("Arrival time, status(N-normal/V-vip),number of ticket purchase by each customer.");
            int ar = s.nextInt();
            String st =s.next();
            int no= s.nextInt();
            customer.queuing(ar, st, no);
            //to array
        }
        
        //RESULT
       //customer.ascending();
       //customer.counter();
       QueueSimulator sim = new QueueSimulator();
       sim.setVisible(true);
        
    }
    
}
