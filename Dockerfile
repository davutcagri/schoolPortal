# Temel imaj olarak OpenJDK 8 kullanın
FROM openjdk:17

# Uygulama dosyalarını Docker içindeki /app klasörüne kopyalayın
COPY target/schoolPortal-0.0.1-SNAPSHOT.jar app.jar

# Uygulamayı başlatmak için komutu tanımlayın
CMD ["java", "-jar", "app.jar"]
