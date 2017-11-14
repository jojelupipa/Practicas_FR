# Instrucciones de instalación

Para los más atrevidos, dejaremos unas instrucciones de
instalación. En nuestro caso, para una distribución basada en debian
como es Ubuntu.

## Desde línea de comandos
1. Añadimos los repositorios.

` sudo apt-add-repository ppa:i2p-maintainers/i2p `

2. Actualizamos e instalamos.

```
sudo apt-get update
sudo apt-get install i2p
```

## Usando Synaptic
1. Añadimos los paquetes personales de archivos con Synaptic: En el
   apartado configuración seleccionamos *“Repositorios”*. 
   
2. En la ventana que nos aparece, accedemos a *“Otro Software”* y añadimos
   “ppa:i2p-maintainers/i2p” en la "Línea apt" que nos aparece al
   hacer click en Añadir. 
   
3. Pulsamos en añadir origen. 
   
4. Abandonamos esta ventana y recargamos los repositorios.
   
5. En el filtro buscamos “i2p”, click derecho sobre “i2p” y luego
   “marcar para instalar”. 

6. Click en “Aplicar”



Ahora para lanzar un router i2p simplemente hay que escribir en la
terminal. **Importante nunca ejecutar este comando como super
usuario**.


`i2prouter start`

Ahora podemos configurar la NAT/firewall, el port forwarding,
accediendo desde el navegador a http://127.0.0.1:7657/confignet. Para
esta demostración no vamos a modificar esto.

Los ajustes del ancho de banda se pueden configurar en esa misma
página. Esto sí que es recomendable configurarlo si se desea una
velocidad potencial mayor.

Para acceder a los sitios .i2p en nuestro navegador debemos configurar
el proxy. En nuestro caso, Firefox, la configuración es la siguiente. En
Menú-Preferencias-General-Proxy de Red-Configuración debemos
configurar lo siguiente:


<!-- <p align="center"> -->
![Configuración](img/Configuración Proxy Firefox.jpg)
 $$ Configuraci\acute{o}n\ proxy$$ 
<!-- </p> -->

Para probar que todo esté bien, se puede intentar conectar a la [wiki
de i2p](i2pwiki.i2p)
