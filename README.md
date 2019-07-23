# The App Movie DB Series & Movies
Aplicación que muestra un listado de peliculas, consumiendo el API "The movie DB"

## Las capas de aplicación 

La aplicación fue desarrollada con el patrón de diseño Modelo-Vista-Presentador 

- Persistencia de datos -

sessionHelper 		- clase donde se decalaran los objetos de la clase SharedPreferences y métodos getSharedPreferences.

- Vistas - 

popularFragment		- clase que se encarga en mostrar los elementos visuales de la lista de las peliculas más populares 
detailFragment		- clase que se encarga en mostrar los elementos visuales de el  detalle de las peliculas
searchFragment		- clase que se encarga en mostrar los elementos visuales de la lista de busqueda
topRatedFragment	- clase que se encarga en mostrar los elementos visuales de la lista de las peliculas más valoradas
upcomingFragment	- clase que se encarga en mostrar los elementos visuales de la lista de las peliculas proximas en salir

- Modelos -

popularModel	- clase que se encarga en de consumir los servicios web con tareas asincronas para iterar con el response al servidor para mostrar las peliculas mas populares 
detailModel		- clase que se encarga en de consumir los servicios web con tareas asincronas para iterar con el response al servidor para mostrar el detalle de las peliculas
searchModel		- clase que se encarga en de consumir los servicios web con tareas asincronas para iterar con el response al servidor para mostrar el resultado de la busqueda
topRatedModel	- clase que se encarga en de consumir los servicios web con tareas asincronas para iterar con el response al servidor para mostrar las peliculas mas votadas 
upcomingModel	- clase que se encarga en de consumir los servicios web con tareas asincronas para iterar con el response al servidor para mostrar las peliculas poroximas en salir

- Presenter -

popularPresenter	- clase que se encarga en de interarctuar con la vista y el modelo para mostrar las peticiones para mostrar las peliculas más populares
detailPresenter		- clase que se encarga en de interarctuar con la vista y el modelo para mostrar las peticiones para mostrar el detalle de las peliculas
searchPresenter		- clase que se encarga en de interarctuar con la vista y el modelo para mostrar las peticiones para mostrar las busquedas de peliculas
topRatedPresenter	- clase que se encarga en de interarctuar con la vista y el modelo para mostrar las peticiones para mostrar las peliculas más votadas
upcomingPresenter	- clase que se encarga en de interarctuar con la vista y el modelo para mostrar las peticiones para mostrar las peliculas proximas en salir

- Adapter - 

itemAdapter		- clase que se encarga de poblar el recyclerview 


### Preguntas

1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?

Consiste  en que cada objeto o clase  debe tener una sola una funcionaldidad, esto permite tener un orden y una claridad en el desarrollo y cuando se quiera realizar una actualización o modificación, se podra realizar con mayor agilidad 

2. Qué características tiene, según su opinión, un “buen” código o código limpio?

Un código limpio debe tener simplicidad, fácil de entender y de darle mantenimiento, esto utilizando las buenas practicas. 
