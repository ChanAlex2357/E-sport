@ECHO OFF
:: Declaration des variables
rem Le nom de l'app web compresser en war
set war_name=EsprtManager

rem Le chemin du poste de travail
set work_dir=.

rem Le chemin de deployement final
set webapps=E:\Softwares\Tomcat 10\webapps

set bin = %work_dir%\bin 
rem Le dossier de configuration du web xml
set web_xml=%work_dir%\web.xml

rem Le dossier temp dans le dossier de travail
@REM set temp=%work_dir%\temp
set temp=%work_dir%\temp

rem Le dossier contenant les contenus web 
set web=%work_dir%\web

rem Le dossier de librairies d'independances
set lib=%work_dir%\lib

rem Le dossier des fichiers sources java
set src=%work_dir%\src

rem Le dossier web inf de temp
set web_inf=%temp%\WEB-INF


@rem Tester si le dossier temp existe deja
    if exist "%temp%" (
        :: Supprimer le dossier temp si il existe deja
        rmdir "%temp%" /s
    )
    :: Recree le dossier temp
    mkdir "%temp%"

@rem Creation de la structure de deployement
    :: Creation du dossier WebInf
    mkdir "%web_inf%"
    :: Creation de web inf lib
    set  web_inf_lib=%web_inf%\lib
    mkdir "%web_inf_lib%"
    :: Creation de web inf classes
    set  web_inf_cls=%web_inf%\classes
    mkdir "%web_inf_cls%"

@rem Copie des fichiers avan deployement
    xcopy .\bin %web_inf_cls% /s /e /h
    :: Copier le contenue de web dans temp
    xcopy "%web%" "%temp%" /s /e /h
    :: Copier le fichier web xml
    xcopy "%web_xml%" "%web_inf%"
    :: Transfert des librairies
    xcopy "%lib%" "%web_inf%\lib" /s /e /h

@REM Compression en War
    cd %temp%
    jar -cvf "%war_name%.war" *

@REM Deployement du war
    xcopy /f "%war_name%.war" "%webapps%"