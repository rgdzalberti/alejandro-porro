fun menu(){

    //Importante introducir en esta variable el path del XML
    var path = "A:\\BORRAR CLASES\\personajesLol.xml"

    println("")
    println("-------------------Menú---------------------")
    println("0 - Salir del programa")
    println("1 - Buscar personaje")
    println("--------------------------------------------")
    println("")

    var inputUsuario : Int = 10
    try { inputUsuario = readln().toInt() }
    catch (_:Exception) { println("Número introducido incorrecto, terminando programa..."); inputUsuario = 0}


    while (inputUsuario != 0){

        when{
            inputUsuario == 1 ->
            {
                var characterName = ""
                var xmlObject = XML(path)

                println("")
                print("Introduce el nombre del personaje a buscar: ")
                characterName = readln()
                println("")

                xmlObject.searchByName(characterName)

                inputUsuario = 0

            }
            inputUsuario != 1 -> {print("Número introducido incorrecto, introduce uno correcto: "); inputUsuario = readln().toInt()
            }
        }

    }

}