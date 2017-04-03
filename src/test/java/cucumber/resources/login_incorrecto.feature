# language: es

 Característica: El administrador debe de hacer login en la aplicacion
 
 	Escenario: Iniciar sesion administrador con un email con formato incorrecto
 		
 		Cuando el usuario se encuentra en la pagina de login
 		Entonces inserta su mail incorrecto "mariagmailcom" y su password "123456"
 		Entonces se logea de manera incorrecta