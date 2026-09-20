FROM maven:3.9.11-eclipse-temurin-21 AS builder
#maven 3.9.11 sürümünde java 21 sürümü kurulu olan bir linux imajı oluşturuyoruz. Bu imajı kullanarak uygulamayı derleyeceğiz.
WORKDIR /app
# Çalışma dizinini /app olarak ayarlıyoruz. Bu dizin, uygulamanın kaynak kodunu ve derleme çıktısını içerecek.
COPY pom.xml .
# pom.xml dosyasını çalışma dizinine kopyalıyoruz. Bu dosya, Maven projesinin yapılandırmasını içerir ve bağımlılıkları tanımlar.
RUN mvn dependency:go-offline
# Maven bağımlılıklarını önceden indiriyoruz. Bu, uygulamanın derlenmesini hızlandırır
COPY src ./src
# Uygulamanın kaynak kodunu çalışma dizinine kopyalıyoruz. Bu, src klasörü, Java sınıflarını ve diğer kaynak dosyalarını içerir.
RUN mvn clean package -DskipTests
# Uygulamayı derliyoruz ve testleri atlıyoruz. Bu, hedef klasöründe bir JAR dosyası oluşturur.
FROM eclipse-temurin:21-jre-alpine
#  Alpine Linux tabanlı bir imaj kullanarak uygulamayı çalıştırmak için daha küçük bir imaj oluşturuyoruz. Bu imaj, Java 21 JRE'yi içerir.
WORKDIR /app
# Çalışma dizinini /app olarak ayarlıyoruz. Bu dizin, uygulamanın çalıştırılabilir dosyasını içerecek.
COPY --from=builder /app/target/*.jar app.jar
# /app/target dan jar dosyasını alıp çalışma dizinine kopyala ismini app.jar yap proğramın çalıştırılabilir dosyasını oluşturuyoruz.
ENTRYPOINT ["java","-jar","app.jar"]
# Uygulamayı çalıştırmak için bir giriş noktası belirliyoruz. Bu, Docker konteyneri başlatıldığında çalıştırılacak komutu tanımlar.
