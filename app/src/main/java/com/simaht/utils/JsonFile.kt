package com.simaht.utils

import android.util.Log
import java.io.BufferedReader
import java.io.File
import java.io.FileOutputStream
import java.io.FileReader

class JsonFile {

    companion object {
        var PATH = ""
        val pathDocuments: String = "/Documents/"
        private val TAG: String = JsonFile::class.java.name

        fun existeArchivo(path: String): Boolean {
            var existe = false
            val file = File(PATH + path)
            if (file.exists()) {
                existe = true
            }
            return existe
        }

        fun crearDirectorio(nombre: String): Boolean {
            var b = true
            val path = PATH + nombre
            val docum = File(path)
            if (!docum.exists() && !docum.isDirectory()) {
                b = docum.mkdir()
            } else {
                Log.e(TAG, "Carpeta ya existe  $path")
            }
            return b
        }

        fun leerArchivo(nombreArchivo: String): String {
            val path = PATH + nombreArchivo
            var cadena = ""
            val br: BufferedReader
            try {
                br = BufferedReader(FileReader(path))
                val sb = StringBuilder()
                var line = br.readLine()
                while (line != null) {
                    sb.append(line)
                    line = br.readLine()
                }
                cadena = sb.toString()
                br.close()
            } catch (e: Exception) {
                Log.e(TAG, e.message)
            }
            return cadena
        }

        fun guardarArchivo(nombreArchivo: String, cadena: String): Boolean {
            var nombreArchivo = nombreArchivo
            try {
                val file = File(PATH + nombreArchivo)
                val directory = File(file.parent!!)
                if (!directory.exists() && !directory.mkdirs()) {
                    Log.e(TAG, "No se pudo crear el directorio: " + directory.getAbsolutePath())
                    return false
                }
                nombreArchivo = file.getAbsolutePath()
                val outputStream = FileOutputStream(File(nombreArchivo))
                outputStream.write(cadena.toByteArray())
                outputStream.close()
                return true
            } catch (e: Exception) {
                Log.e(TAG, "No guardo "+e.message)
                return false
            }

        }
    }
}