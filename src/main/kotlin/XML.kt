import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory

class XML(pathI: String = "") {

    var path = pathI

    val xmlFile: File
    val xmlDoc: Document

    init {

        xmlFile = File(path)
        xmlDoc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlFile)
        xmlDoc.documentElement.normalize()
    }

    fun searchByName(name: String){

        var name = name
        val xmlList = xmlDoc.getElementsByTagName("*")

        var characterFound = false

        //Voy nodo por nodo comparando si el nombre que se ha introducido en la funcion coincide con el nombre de alguna etiqueta hardcodeada
        for (i in 0 until xmlList.length){

            var nombre = xmlList.item(i).nodeName.toString()

            if (nombre.lowercase(Locale.getDefault()) == name.lowercase(Locale.getDefault())){

                characterFound = true
                println("Se ha encontrado el personaje y se está generando un informe")

                //Como un XML tiene un órden preestablecido que seguir, me he limitado a iterar manualmente ya que funcionará con todo XML que sigan las mismas normas
                //Si no siguiese un órden preestablecido habría iterado a partir de el nodo del nombre hasta encontrar aquella cuyo tag coincida con lo que busco
                generarTXT(xmlList.item(i + 4).textContent,xmlList.item(i + 5).textContent, xmlList.item(i + 6).textContent, xmlList.item(i + 20).textContent)
            }
            else if (i == xmlList.length - 1 && characterFound == false)
            {
                println("")
                print("No se ha podido encontrar, puedes introducir un nuevo parámetro: ")
                searchByName(readln())
                println("")

            }


        }


    }

    fun generarTXT(name: String, title: String, blurb: String, tags: String){

        //Creo el directorio de la carpeta en el escritorio
        val os = System.getProperty("file.separator")
        var desktopPath = System.getProperty("user.home") + "${os}Desktop"
        var folder = desktopPath + "${os}personajes"

        //Compruebo si existen el directorio y el archivo respectivamente, si no, los creo
        if (!File(folder).isDirectory) { Files.createDirectories(Paths.get(folder)) }
        if (!File(folder + "${os}${name}.txt").isFile) { File(folder + "${os}${name}.txt").createNewFile() }

        //Y en el txt generado escribo los parámetros
        //La forma más fácil de hacerlo es concatenar todo el texto en un string y luego insertarlo en un writeText, así evitamos abrir y cerrar buffers
        var contenidoFile = name + "\n" + title + "\n" + blurb + "\n" + tags
        File(folder + "${os}${name}.txt").writeText(contenidoFile)

    }

}