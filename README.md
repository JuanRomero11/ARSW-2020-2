# 🛠️ LABORATORIO 1 
# ARSW-2020-2
## Andres Sotelo - Juan Romero 
## 📄 Black List Search
### Part I - Introduction to threads in JAVA
  
  1. In agreement with the lectures, complete the classes CountThread, so that they define the life cycle of a thread that prints the numbers between A and B on the screen.
 
 2. Complete the main method of the CountMainThreads class so that:
      
      1. Create 3 threads of type CountThread, assigning the first interval [0..99], the second [99..199], and the third [200..299]. 
      
      2. Start the three threads with start(). Run and check the output on the screen. 
      
      3. Change the beginning with start() to run(). How does the output change? Why?
      
        PRIMER PUNTO:
        Diferencia entre ejecutar Start() y Run()

        Start(): si ejecutamos los tres hilos con start se van a ajeutar de manera concurrente y la salida cambiara en cada ejecucion por que depende de la velocidad del               procesador y la manera como atiende cada uno de los subprocesos
        Run(): siejecutamos run() cada hilo espera a que termine el anterior ya que la ejecucion de todos sera de manera iterativa entonces la salida imprimira los numeros en          orden
   
### Part II-III- Black List Search Exercise

  For an automatic computer security surveillance software, a component is being developed to validate the IP addresses in several thousands of known malicious blacklists (of
  malicious hosts), and to report those that exist in at least five of said lists.
  
  Said component is designed according to the following diagram, where:
 
 * HostBlackListsDataSourceFacade is a class that offers a facade for queries in any of the N registered blacklists (method 'isInBlacklistServer'), and which also allows a
 report to a local database of when an IP address is considered dangerous. This class is NOT MODIFIED, but it is known to be 'Thread-Safe'.
  
 * HostBlackListsValidator is a class that offers the checkHost method, which, through the HostBlackListDataSourceFacade class, validates a given host in each of the
  blacklists. In this method is considered the policy that when a HOST is found in at least five blacklists, it will be registered as not reliable, or as reliable otherwise.
  Additionally, it will return the list of the numbers of the blacklists where the HOST was registered.
    
  IMAGEN
 
 When using the module, the evidence that the registration was made as reliable or not reliable is given by the messages of LOGs:
 
    INFO: HOST 205.24.34.55 Reported as trustworthy
    INFO: HOST 205.24.34.55 Reported as NOT trustworthy
    
    
The test program provided (Main), takes only a few seconds to analyze and report the address provided (200.24.34.55), since it is registered more than five times in the first servers, so it does not need to cover all of them. However, doing the search in cases where there are NO reports, or where they are scattered in the thousands of blacklists, takes a lot of time.

This, like any search method, can be seen as a shamefully parallel problem, since there are no dependencies between one part of the problem and another.

To refactor this code, and have it exploit the multi-core capability of the computer's CPU, do the following:

  1. Create a Thread class that represents the life cycle of a thread that searches for a segment of the pool of available servers. Add to that class a method that allows you
  to ask the instances of it (the threads) how many occurrences of malicious servers it has found or found.
  
  2. Add to the checkHost method an integer parameter N, corresponding to the number of threads between which the search will be carried out (remember to take into account if
  N is even or odd!). Modify the code of this method so that it divides the search space between the indicated N parts, and parallels the search through N threads. Have that
  function wait until the N threads finish solving their respective sub-problem, add the occurrences found for each thread to the list that returns the method, and then
  calculate (adding the total number of occurrences found for each thread) if the Number of occurrences is greater than or equal to BLACK_LIST_ALARM_COUNT. If this is the
  case, in the end the host MUST be reported as reliable or not reliable, and the list should be shown with the numbers of the respective blacklists. To achieve this wait
  behavior, review the join method of the Java concurrency API. Also keep in mind:
  
     


calculo de tiempo con diferentes pruebas.


1) 1 hilo: 65179 milisegundos = 1.0863167 minutos
2) tantos subprocesos como nucleos en el pc (hay 4 nueclos): 32594 milisegundos = 0.54323333 minutos
3) tantos hilos como el doble de nucleos (8): 16465 milisegundos = 0.27441667 minutos
4) 50 hilos: 2907 milisegundos = 0.04845 minutos
5) 100 hilos: 1707 milisegundos = 0.02845 minutos

