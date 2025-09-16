```
##VR
- Desktop App для V/S Arena
Проект на Kotlin Multiplatform с использованием Jetpack Compose и VLCJ для создания медиаплеера / видеоплатформы под десктоп.

📁 Структура проекта
/
├── composeApp/          # Общий код для приложений на Compose Multiplatform
│     ├── commonMain/    # Код, работающий на всех платформах
│     └── <platform>Main # Код, специфичный для платформы (Windows, macOS и др.)
├── gradle/              # Конфигурации Gradle
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew / gradlew.bat
├── LICENSE              # Лицензия проекта (GPL-3.0)
└── README.md            # Этот файл


⚙ Технологии
1. Kotlin Multiplatform — код, разделённый на общую часть (common) + платформо-ориентированные модули. 
2. Jetpack Compose Multiplatform — UI-фреймворк для построения интерфейса под несколько платформ.
3. VLCJ — Java-обёртка над VLC, для воспроизведения видео и работы с медиа.


🚀 Установка и запуск
Вот примерный шаг за шагом процесс, как собрать и запустить проект на твоей машине:
Склонируй репозиторий:
git clone https://github.com/lkeey/VR.git
cd VR
git checkout dev


Убедись, что у тебя установлен JDK (рекомендуется версия Java 17+).
Выполни сборку через Gradle:

./gradlew clean build
Запусти приложение (например, через Gradle или через IDE):

./gradlew run
— или нажми соответствующий запуск в IDE (IntelliJ IDEA или аналогичной).
```