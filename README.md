# Laboratorio 13: Animaciones

Aplicación de Android con Kotlin y Jetpack Compose para practicar animaciones de interfaz.

## Contenido

- `AnimatedVisibility`: tarjeta con `fadeIn` y `fadeOut`.
- `animateColorAsState`: transición elástica entre azul y verde.
- `animateDpAsState` y `Modifier.offset`: cambio de posición y tamaño.
- `AnimatedContent`: estados Cargando, Contenido y Error.
- **Nebula Quest**: prototipo de videojuego que combina color, posición, giro, escala y visibilidad.
- **Codelab de animaciones**: repetición infinita, entrada/salida, expansión y gesto horizontal con `Animatable`.

## Ejecutar

Abrir `Lab13Animaciones` en Android Studio y ejecutar `app` en un emulador o dispositivo. Para compilar desde la terminal:

```powershell
.\gradlew.bat assembleDebug
```

## Evidencias

En `evidence` están las capturas PNG reales tomadas del emulador y la grabación completa de la demostración: [Lab13_DemoCompleta.mp4](evidence/Lab13_DemoCompleta.mp4). El archivo [Lab13_Animaciones.mp4](evidence/Lab13_Animaciones.mp4) conserva la grabación corta anterior. En el DOCX original inserté varias capturas de cada ejercicio dentro de sus recuadros.

El código fuente principal está en `app/src/main/java/com/tecsup/lab13animaciones/MainActivity.kt`.

## Codelab

[Cómo animar elementos en Jetpack Compose](https://developer.android.com/codelabs/jetpack-compose-animation?hl=es-419), Android Developers.
