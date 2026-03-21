#!/bin/bash

MVN=/opt/jeat/idea-IU-253.32098.37/plugins/maven/lib/maven3/bin/mvn

cd /home/juan/Proyectos/practica/practica

# Compilar
$MVN package -DskipTests

# Copiar WAR al deploy (como fichero, no directorio)
cp target/app.war /home/juan/Proyectos/practica/practica-tomcat/deploy/app.war

# Reiniciar Tomcat para que recoja el WAR del volumen
cd /home/juan/Proyectos/practica/practica-tomcat
docker compose restart tomcat

echo "Desplegado en http://localhost:8080/app/home"