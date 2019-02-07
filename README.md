Запуск
git clone https://github.com/NSergeyI/gwtp-appengine-guice
Open as project
Add Google App Engine Standard framework
исправить pom.xml 47 стр <webappDirectory>
Project structure \ Project compiler output \ C:\Users\JavaDev\workspace\test\gwtp-appengine-guice\target
run configuration проверить artifact to deploy
run 

Регистрация GAE
https://console.developers.google.com/
create new project
api&services/credentional /OAuyh consent screen 
Application name  testLection2
Authorized domain testLection2.appspot.com
save
выбрать web application 
Authorised redirect URIs 
 "http://testlection2.appspot.com/oauth2callback",
 "http://127.0.0.1:8080/oauth2callback"
save
если все ок появиться окно с clientID и clientSecret
вносим данные в clien_secret.json &clien_secret_dev.json 
run

