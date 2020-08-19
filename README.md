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
   
### Part II- Black List Search Exercise

 For an automatic computer security surveillance software, a component is being developed to validate the IP addresses in several thousands of known malicious blacklists (of malicious hosts), and to report those that exist in at least five of said lists.
Said component is designed according to the following diagram, where:
HostBlackListsDataSourceFacade is a class that offers a facade for queries in any of the N registered blacklists (method 'isInBlacklistServer'), and which also allows a report to a local database of when an IP address is considered dangerous. This class is NOT MODIFIED, but it is known to be 'Thread-Safe'.
HostBlackListsValidator is a class that offers the checkHost method, which, through the HostBlackListDataSourceFacade class, validates a given host in each of the blacklists. In this method is considered the policy that when a HOST is found in at least five blacklists, it will be registered as not reliable, or as reliable otherwise. Additionally, it will return the list of the numbers of the blacklists where the HOST was registered.

When using the module, the evidence that the registration was made as reliable or not reliable is given by the messages of LOGs:
INFO: HOST 205.24.34.55 Reported as trustworthy
INFO: HOST 205.24.34.55 Reported as NOT trustworthy
The test program provided (Main), takes only a few seconds to analyze and report the address provided (200.24.34.55), since it is registered more than five times in the first servers, so it does not need to cover all of them. However, doing the search in cases where there are NO reports, or where they are scattered in the thousands of blacklists, takes a lot of time.
This, like any search method, can be seen as a shamefully parallel problem, since there are no dependencies between one part of the problem and another.
To refactor this code, and have it exploit the multi-core capability of the computer's CPU, do the following:
Create a Thread class that represents the life cycle of a thread that searches for a segment of the pool of available servers. Add to that class a method that allows you to ask the instances of it (the threads) how many occurrences of malicious servers it has found or found.
Add to the checkHost method an integer parameter N, corresponding to the number of threads between which the search will be carried out (remember to take into account if N is even or odd!). Modify the code of this method so that it divides the search space between the indicated N parts, and parallels the search through N threads. Have that function wait until the N threads finish solving their respective sub-problem, add the occurrences found for each thread to the list that returns the method, and then calculate (adding the total number of occurrences found for each thread) if the Number of occurrences is greater than or equal to BLACK_LIST_ALARM_COUNT. If this is the case, in the end the host MUST be reported as reliable or not reliable, and the list should be shown with the numbers of the respective blacklists. To achieve this wait behavior, review the join method of the Java concurrency API. Also keep in mind:
Inside the checkHost method the LOG must be kept informing, before returning the result, the number of revised blacklists VS. the number of total blacklists (line 60). It must be guaranteed that said information is true under the new parallel processing scheme proposed.
It is known that HOST 202.24.34.55 is blacklisted in a more dispersed way, and that host 212.24.24.55 is NOT on any blacklist.

### Part III - Discussion
     
The strategy of parallelism previously implemented is inefficient in certain cases, since the search is still carried out even when the N threads (as a whole) have already found the minimum number of occurrences required to report to the server as malicious. How could the implementation be modified to minimize the number of queries in these cases? What new element would this bring to the problem?

- En la instancia de cada hilo se podria tener un contador global para todos los hilo, y que cada hilo mantenga su ejecucion mientras esta variable no llegue al limite de servidores.

### Part IV - Performance Evaluation
From the above, implement the following sequence of experiments to perform the validation of dispersed IP addresses (for example 202.24.34.55), taking the execution times of them (be sure to do them on the same machine):
  1. A single thread. 
  
  2. As many threads as processing cores (have the program determine this using the Runtime API). 
  
  3. As many threads as twice the number of processing cores. 
  
  4. 50 threads 
  
  5. 100 threads

With the above, and with the given execution times, make a graph of solution time vs. Number of threads. Analyze and hypothesize with your partner for the following questions (you can take into account what was reported by jVisualVM):

- According to Amdahls law, where S(n) is the theoretical improvement of performance, P the parallel fraction of the algorithm, and n the number of threads, the greater n, the better this improvement should be. Why is the best performance not achieved with the 500 threads? How is this performance compared when using 200 ?.

- How does the solution behave using as many processing threads as cores compared to the result of using twice as much?

Se usan tantos hilos como nucleos, esto se vera afectado en el tiempo, un claro ejemplo es en el momento de realizar 4 hilos que tarda aproximadamente 36 segundos en realizar la busqueda, si se llega a usar el doble de hilos, el programa tarda 16 segundos. Aqui se ve el efecto cuando el tiempo aproxidamente se reduce a la mitad.


- According to the above, if for this problem instead of 100 threads in a single CPU could be used 1 thread in each of 100 hypothetical machines, Amdahls law would apply better ?. If x threads are used instead of 100/x distributed machines (where x is the number of cores of these machines), would it be improved? Explain your answer.

Al momento de correr 100 hilos, se espera que el tiempo de ejecucion sea mucho mejor pero al analizar detalladamente observabamos que el tiempo de ejecucion se ve afectado a la hora de juntar las multiples de respuestas.

calculo de tiempo con diferentes pruebas.


1) 1 hilo: 65179 milisegundos = 1.0863167 minutos
2) tantos subprocesos como nucleos en el pc (hay 4 nueclos): 32594 milisegundos = 0.54323333 minutos
3) tantos hilos como el doble de nucleos (8): 16465 milisegundos = 0.27441667 minutos
4) 50 hilos: 2907 milisegundos = 0.04845 minutos
5) 100 hilos: 1707 milisegundos = 0.02845 minutos

