
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;


class MyThread extends Thread
{
            static  boolean flag= false;
               static Semaphore semCarWash = new Semaphore(3);
             static   Semaphore semA = new Semaphore(1);
               static Semaphore semB = new Semaphore(1);
             static   Semaphore semC = new Semaphore(1);
	int nameThread;
	public MyThread( int nameThread)
	{
                
	this.nameThread = nameThread;
	}
 public void waitingForEnterCarWash() {
        System.out.println("car .No " + nameThread + " Waiting For Enter The CarWash ");
    }
    
    public void enteredCarWash() {
        System.out.println("car .No " +nameThread + " Entered CarWash");
    }
    public void exitCarWash() {
        System.out.println("car .No " +nameThread + " Exit CarWash");
    }
    public void enterBoothA() {
        System.out.println("car .No " +nameThread + " Enter Booth A");
    }
    public void exitBoothA() {
        System.out.println("car .No " +nameThread + " Exit Booth A");
    }
    public void enterBoothB() {
        System.out.println("car .No " +nameThread + " Enter Booth B");
    }
    public void exitBoothB() {
        System.out.println("car .No " +nameThread + " Exit Booth B");
    }
    public void enterBoothC() {
        System.out.println("car .No " +nameThread + " Enter Booth C");
    }
    public void exitBoothC() {
        System.out.println("car .No " +nameThread + " Exit Booth C");
    }
    
	@Override
	public void run() {
		 waitingForEnterCarWash();
            try {
                semCarWash.acquire();
                enteredCarWash();
            } catch (InterruptedException ex) {
                Logger.getLogger(MyThread.class.getName()).log(Level.SEVERE, null, ex);
            }
                                                                    
                                                                     Random rand = new Random();
                         int  randCarNumber = rand.nextInt(2);
           
		if(randCarNumber==1)
  
		{
                    try{
                                                                 
                        semA.acquire();  
                            enterBoothA() ;	
                            Thread.sleep(100);
                            exitBoothA() ;
                            semA.release();	
                             	
			} catch (InterruptedException exc) {
					System.out.println(exc);
				}
		
				
		}
                                else if(randCarNumber==0)
		{
                    try{
                                                                 
                        semB.acquire();  
                            enterBoothB() ;	
                            Thread.sleep(100);
                            exitBoothB() ;
                            semB.release();	
                             	
			} catch (InterruptedException exc) {
					System.out.println(exc);
				}
		
		
		}
                
                                         
                                     try{
                                                        
                        semC.acquire(); 
                           flag = true;
                            enterBoothC() ;	
                            Thread.sleep(300);
                            exitBoothC() ;
                                  flag=false;
                                     
                            semC.release();	
                             	
			} catch (InterruptedException exc) {
					System.out.println(exc);
				}
		exitCarWash();
                semCarWash.release();
                
	}
}

public class  OSProject22
{
            static public void sleep() {
        System.out.println("Worker is Sleeping");
    }
	public static void main(String args[]) throws InterruptedException
	{
         Scanner in = new Scanner(System.in);
        System.out.println("Please Enter Number Of CAR : ");
        int n = in.nextInt();
     try{
           for (int i = 1; i <= n; i++) {
                new Thread(new MyThread(i)).start(); 
                 
            }
           if(MyThread.flag==false){
                 while(true){
                                                sleep();
                                                if(MyThread.flag==true){
                                                   break;
                                                } 
                                            }
           }
             
             
     }catch(Exception e){
         
     }
		
	}
}