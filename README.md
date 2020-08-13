# ARSW-2020-2
## Andres Sotelo - Juan Romero 

PRIMER PUNTO:
Diferencia entre ejecutar Start() y Run()

Start(): si ejecutamos los tres hilos con start se van a ajeutar de manera concurrente y la salida cambiara en cada ejecucion por que depende de la velocidad del procesador y la manera como atiende cada uno de los subprocesos
Run(): siejecutamos run() cada hilo espera a que termine el anterior ya que la ejecucion de todos sera de manera iterativa entonces la salida imprimira los numeros en orden

