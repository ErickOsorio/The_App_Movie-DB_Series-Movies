# The App Movie DB Series & Movies
Aplicación que muestra un listado de peliculas, consumiendo el API "The movie DB"

## Las capas de aplicación 

La aplicación fue desarrollada con el patrón de diseño Modelo-Vista-Presentador 

Persistencia de datos 

sessionHelper 		- clase donde se decalaran los objetos de la clase SharedPreferences y métodos getSharedPreferences.

Vistas 

popularFragment		- 
detailFragment
searchFragment
topRatedFragment
upcomingFragment

Modelos

popularModel
detailModel
searchModel
topRatedModel
upcomingModel

Presenter

popularPresenter
detailPresenter
searchPresenter
topRatedPresenter
upcomingPresenter




### Preguntas

1. En qué consiste el principio de responsabilidad única? Cuál es su propósito?

Consiste  en que cada objeto o clase  debe tener una sola una funcionaldidad, esto permite tener un orden y una claridad en el desarrollo y cuando se quiera realizar una actualización o modificación, se podra realizar con mayor agilidad 

2. Qué características tiene, según su opinión, un “buen” código o código limpio?

Un código limpio debe tener simplicidad, fácil de entender y de darle mantenimiento, esto utilizando las buenas practicas. 
