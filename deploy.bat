@echo off

echo Compilando...
cd /d "D:\practica\practica"
call "D:\JetBrains\plugins\maven\lib\maven3\bin\mvn.cmd" clean package -q
echo Paso 1 OK

echo Copiando WAR...
copy /Y "D:\practica\practica\target\app.war" "D:\practica\practica\practica-tomcat\deploy\app.war"
echo Paso 2 OK

echo Reiniciando Tomcat...
cd /d "D:\practica\practica\practica-tomcat"
docker compose restart tomcat
echo Paso 3 OK

echo.
echo Deploy completado - http://localhost:8080/app/