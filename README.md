# ARSW-2020-2
## Andres Sotelo - Juan Romero 

PRIMER PUNTO:
Diferencia entre ejecutar Start() y Run()

Start(): si ejecutamos los tres hilos con start se van a ajeutar de manera concurrente y la salida cambiara en cada ejecucion por que depende de la velocidad del procesador y la manera como atiende cada uno de los subprocesos
Run(): siejecutamos run() cada hilo espera a que termine el anterior ya que la ejecucion de todos sera de manera iterativa entonces la salida imprimira los numeros en orden

parte 3:

calcuo de tiempo con diferentes pruebas.

1) 1 hilo: 65179 milisegundos = 1.0863167 minutos
2) tantos subprocesos como nucleos en el pc (hay 4 nueclos): 32594 milisegundos = 0.54323333 minutos
3) tantos hilos como el doble de nucleos (8): 16465 milisegundos = 0.27441667 minutos
4) 50 hilos: 2907 milisegundos = 0.04845 minutos
5) 100 hilos: 1707 milisegundos = 0.02845 minutos

