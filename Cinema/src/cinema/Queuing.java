/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class Queuing {
    private ArrayList<Integer>arv=new ArrayList<>();   //arrival time
    private ArrayList<String>stats=new ArrayList<>();     //status: VIP or Normal
    private ArrayList<Integer>num=new ArrayList<>();         
    private ArrayList<Integer>endcounter=new ArrayList<>();
    private int numberofcounter=4;   //Number of counters
    private int start,end,processtime,waittime,counterNum;
    MyTimerTask time = new MyTimerTask();
   
    Queuing(){
    endcounter.add(0);//1st
    endcounter.add(0);//2nd
    endcounter.add(0);//3rd
    endcounter.add(0);//4th             // endcounter to get start value and set end
    end=0;
    }
   
    public void queuing(int a,String b,int c){
        arv.add(a);
        stats.add(b);
        num.add(c);
    }
    
    public void ascending(){
        //ascending order for arrival time
      
    for(int j=1;j<arv.size();j++){    
        for(int i=0;i<arv.size()-1;i++){
            if(arv.get(i)>arv.get(i+1)){        //arrival time at i=1 > arrival time at i=2
                int temp=arv.get(i);               // temporary queue= arrival time at i=1
                arv.set(i, arv.get(i+1));           // Set the default queue based on loop index(i=1,i=2)
                arv.set(i+1, temp);                  //Sort the queue based on ascending arrival time: (i=2, i=1 is in temporary queue)     
                
		// status
                String temp2=stats.get(i);             // At arrival time i=1, temporary2 receive character at i=1 as well
                stats.set(i, stats.get(i+1));        // Status set default queue based on loop index (i=1, i=2)
                stats.set(i+1, temp2);		     // Reset the status queue based on ascending arrival time as well: (i=2, i=1 is in temporary queue)
                
		//num of ticket
                int temp3=num.get(i);		    // Temporary3 queue receive number of tickets
                num.set(i, num.get(i+1));	    // Set default queue based on loop index
                num.set(i+1, temp3);		    // Reset the queue based on ascending arrival time as well
            } // STEP1 :PURE SORTING BASED ON ARRIVAL TIME (FIRST COME FIRST SERVE)

           
            else if(arv.get(i)==arv.get(i+1)){ //IF SAME ARRIVAL TIME
                if(stats.get(i).equals("Normal")&& stats.get(i+1).equals("VIP")){ // IF AGAIN, arrival time at i=1 is normal, while arrival time at i=2 is VIP.
                    int temp=arv.get(i);               // Arrival time at i=1 is sorted into temporary queue
                    arv.set(i, arv.get(i+1));           // Set the default queue based on loop index(i=1,i=2)
                    arv.set(i+1, temp);                  //Sort the queue based on status: (i=2 is VIP, i=1 is Normal)
                    
		    // status
                    String temp2=stats.get(i);		// Arrival time at i=1 is sorted into temporary2 queue
                    stats.set(i, stats.get(i+1));	// Set the default queue based on loop index(i=1,i=2)
                    stats.set(i+1, temp2);		// Sort the queue based on status: (i=2 is VIP, i=1 is Normal)
                    
		//num of ticket
                    int temp3=num.get(i);
                    num.set(i, num.get(i+1));
                    num.set(i+1, temp3);
                } //STEP2: IF ARRIVAL TIME IS SAME, THEN START SORTING BASED ON STATUS (SERVE VIP FIRST)  (got problem when more than 2 person)
                
            }
             
        }
    }
    System.out.println(arv.toString());            //Display final arrival time in array
    System.out.println(stats.toString());          //Display final status in array
    System.out.println(num.toString());            //Display no. of tickets in array
    
    }

    
    public String counter(){
       //while there is customer
       while(arv.size()!=0){  
       if(isempty(arv.get(0))!=666){               //If the counter is not empty, then start the process
       return methodatcounter(isempty(arv.get(0)));
       }
       else{
       return methodatcounter(getminimumendtime(arv.get(0)));  //If the counter is empty, then obtain the minimum time of it.
       }
       }
        return "";
      
       }
    
    public int isempty(int ar){
        for (int i=0;i<endcounter.size()-1;i++){   //If counter at index i=0 is smaller than parameter, then return that index
            if (endcounter.get(i)<ar){
                return i;
            }
        }
        return 666;
        
    }
    public int getminimumendtime(int ar){ // which counter empty first
        int minindex=0;
        int min=endcounter.get(0);
        for (int i=0;i<endcounter.size()-1;i++){
                if(min>endcounter.get(i+1)){     
                    minindex=i+1;
                    min=endcounter.get(i+1);
                }
           
        }
        return minindex;
    }
    public int getprocesstime(int counter,int num){
        int ans;
        switch (counter){
            case (0):
                return num*10;
                
            case (1):
                return num*15;
                
            case (2):
                return num*30;
                
            case (3):
                return num*15;
                
        }
        return 0;
    
    }
       
    
    public String methodatcounter(int i){
        System.out.print("Counter"+ i +" :"); //Counter 1 :
        this.counterNum=i;
       if(endcounter.get(i)==0){
            start=arv.get(0);
        }
        else{
            start=endcounter.get(i);
        }
        int tparv= arv.get(0);           
        String tpsts=stats.get(0);
        int tpnum=num.get(0);
        arv.remove(0);
        stats.remove(0);
        num.remove(0);
        processtime=getprocesstime(i,tpnum);
        end=start+processtime;
        endcounter.set(i, end);
        waittime=start-tparv;
        
        if(waittime<=0){
            waittime=0;
        }
        return "Counter: "+i+" Arrival time: "+ tparv+" Start: "+start+" End: "+end+" Number of ticket: "+ tpnum+" Process time:"+processtime+" Waitime: "+waittime+" Status:"+tpsts; 
        //System.out.println( "Counter: "+i+" Arrival time: "+ tparv+" Start: "+start+" End: "+end+" Number of ticket: "+ tpnum+" Process time:"+processtime+" Waitime: "+waittime+" Status:"+tpsts);
    
    }
    @Override
    public String toString (){
        return "Start: "+start+" End: "+end+" Process time:"+processtime+" Waitime: "+waittime+" Counter: "+ counterNum;
    }
        public int waitTime(){
            return waittime;
        }
        public int processTime(){
            return processtime;
        }
    
        
            
    
  
}
