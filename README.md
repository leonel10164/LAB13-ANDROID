# Laboratorio 13: Animaciones

Aplicación de Android con Kotlin y Jetpack Compose para practicar animaciones de interfaz.

## Contenido

- `AnimatedVisibility`: mostrar y ocultar una tarjeta con `fadeIn` y `fadeOut`.
- `animateColorAsState`: transición elástica entre azul y verde.
- `animateDpAsState` y `Modifier.offset`: cambiar posición y tamaño de un elemento.
- `AnimatedContent`: alternar entre Cargando, Contenido y Error.
- **Nebula Quest**: prototipo de videojuego que combina cambios de color, posición, rotación, escala y visibilidad al atacar.
- **Codelab de animaciones**: repetición infinita, entrada/salida personalizada, expansión del contenido y gesto horizontal gestionado con `Animatable`.

## Ejecutar

Abrir la carpeta `Lab13Animaciones` en Android Studio y ejecutar `app` en un emulador o dispositivo. El proyecto se creó con la plantilla Empty Activity de Jetpack Compose.

También se puede compilar desde la terminal de Android Studio:

```powershell
.\gradlew.bat assembleDebug
```

## Evidencias

La carpeta `evidence` contiene capturas PNG tomadas directamente del emulador Android y una grabación MP4 de la interacción. Las capturas del código se guardan allí desde Android Studio.

## Codelab de referencia

Se siguieron las APIs descritas en [Cómo animar elementos en Jetpack Compose](https://developer.android.com/codelabs/jetpack-compose-animation?hl=es-419), de Android Developers.
