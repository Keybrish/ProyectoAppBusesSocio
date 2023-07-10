# 🚍Safey - Aplicativo Móvil para el Socio / Controlador del Bus

Safey es un sistema que permite la gestión y venta de pasajes a usuarios de buses interprovinciales en Ecuador. 
La finalidad de esta aplicación móvil es facilitar al conductor o encargado del bus la gestión de la validación y visualización de la información de los pasajeros según el viaje.

## 👣 Primeros Pasos

Estas instrucciones te guiarán en la configuración y ejecución de la aplicación en tu entorno local para propósitos de desarrollo y pruebas.

#### ✔️  Prerrequisitos

Asegúrate de tener instalado lo siguiente:

- [Android Studio](https://developer.android.com/studio) [🔗](https://developer.android.com/studio)
  Este proyecto se desarrolló en el IDE de Android Studio, versión: Dolphin | 2021.3.1 Patch 1
  
  ⚠️Para evitar errores de compatibilidad, te recomendamos ejecutar el proyecto en la misma versión mencionada del IDE, o en superiores.
  
- [Repositorio - Backend](https://github.com/YadiraAllauca/ServiciosProyectoDAS) 
  Los enlaces a los servicios y la base de datos que se encuentran implicados en el código de este proyecto están alojados en un HostingWeb. Pero puedes verificar la estructura en el repositorio de backend. Te recomendamos revisar la documentación [🔗](https://github.com/YadiraAllauca/ServiciosProyectoDAS)

## ⚙️ Instalación

1. Clona el repositorio en tu máquina local dentro de htdocs del directorio de XAMPP:

```bash
git clone https://github.com/Keybrish/ProyectoAppBusesSocio.git
```

2. La app actualmente está conectado a un proyecto personal en Firebase <img src="https://www.gstatic.com/mobilesdk/160503_mobilesdk/logo/2x/firebase_28dp.png" alt="Logo de Firebase" width="20">, si deseas efectuar tu propia conexión con tu cuenta, revisa la documentación proporcionada por esta plataforma [🔗](https://firebase.google.com/docs/android/setup?hl=es-419)

3. Verifica que la ejecución se realice sin problema, de lo contrario puedes actualizar las versiones de las dependencias en el archivo build.gradle (Module). 
Principalmente las relacionadas a: 
* Picasso: Carga de imágenes [🔗](https://github.com/square/picasso)
* Firebase: Autenticación y almacenamiento [🔗](https://firebase.google.com/docs?hl=es-419)
* Retrofit: Consumo de servicios [🔗](https://github.com/square/retrofit)

## 💻 Uso
#### Login

Te permitirá ingresar como usuario socio para obtener detalle de los pasajeros del bus. Su funcionalidad en código está presente en **appBusesDriver/app/src/main/java/dev/android/appbusesdriver/LoginActivity.kt**

<div style="text-align:center">
<img src="https://cdn.glitch.global/67cd472b-72c6-4b72-8f91-3c3387cbf446/e599368f-c69c-4a93-8d66-27892f6badbc.image.png?v=1688961858676" width="200">
</div>

#### Viaje

Esta pantalla permitirá al usuario obtener detalle del viaje. Además le permite escanear el código QR de la compra que haya hecho un pasajero. Su funcionalidad en código está presente en **appBusesDriver/app/src/main/java/dev/android/appbusesdriver/MainActivity.kt**

<div style="text-align:center">
<img src="https://cdn.glitch.global/67cd472b-72c6-4b72-8f91-3c3387cbf446/35a11ed2-0c34-4684-bff2-83b2c8ce41c4.image.png?v=1688964166546" width="200">
</div>


#### Perfil de usuario

En esta pantalla el usuario puede editar sus datos. Su funcionalidad en código está presente en

- **appBusesDriver/app/src/main/java/dev/android/appbusesdriver/ProfileInfoActivity.kt**
- **appBusesDriver/app/src/main/java/dev/android/appbusesdriver/ProfileActivity.kt**
<div style="text-align:center">
<img src="https://cdn.glitch.global/67cd472b-72c6-4b72-8f91-3c3387cbf446/6bf4042a-7283-4278-bfde-7c6058cf58cb.image.png?v=1688964172024" width="200">
</div>


## 🤝 Contribución
Si deseas contribuir a este proyecto, sigue los siguientes pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama:
```bash
git checkout -b nombre-de-la-rama
```
3. Realiza los cambios y haz commit:
```bash
git commit -m "Descripción de los cambios"
```
4. Envía los cambios a tu fork:
```bash
git push origin nombre-de-la-rama
```
5. Crea una pull request en este repositorio.


## ©️ Licencia
Este proyecto académico no tiene una licencia específica asignada. Todos los derechos de autor pertenecen a los miembros del equipo de desarrollo. Ten en cuenta que esto significa que no se otorgan permisos explícitos para utilizar, modificar o distribuir el código fuente o los archivos relacionados. Cualquier uso, reproducción o distribución del proyecto debe obtener permiso previo.

## 📧 Contacto
Si tienes alguna pregunta o comentario, puedes contactarte con los miembros del equipo de desarrollo:

* kzamora7938@uta.edu.ec
* anaranjo1676@uta.edu.ec
* dpinchao9519@uta.edu.ec
* tarmijos0985@uta.edu.ec
* yallauca3770@uta.edu.ec
