package org.senac.tirafoto

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.core.content.FileProvider
import java.io.File

class MainActivity : AppCompatActivity() {

    var file : File? = null
    val RESQUEST_CODE = 0

    lateinit var imgView : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgView = findViewById<ImageView>(R.id.imgView)

    }

    fun abrirImagem(view: View) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, RESQUEST_CODE)
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val imageBitmap = data?.extras?.get("data") as Bitmap
                imgView.setImageBitmap(imageBitmap)
            }
        }
    }




    fun getSdCard(fileName: String) : File {
        val sdCard = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (sdCard != null && sdCard.exists()) {
            sdCard.mkdir()
        }
        return File(sdCard, fileName)
    }
}
