package com.example.gaspillezero.ui.main.PrésentationDenrées

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.PrésentationGabarits.*
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits
import com.example.gaspillezero.ui.main.sourceDeDonnées.Épicerie
import java.io.ByteArrayOutputStream

class GabaritFragment : Fragment(), AdapterView.OnItemSelectedListener, GabaritVue {

    private lateinit var adapter: GabaritAdapter
    private lateinit var présentateur: GabaritPrésentateur
    private var imageBase64: String = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        présentateur = GabaritsPrésenteur(this,GabaritsModèle())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_gestion_gabarit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        présentateur.obtenirDonnées()
        view.findViewById<Button>(R.id.btnAddGabarit).setOnClickListener {
            afficherBoiteDialogueAjout()
        }
    }

    private fun setupRecyclerView(view: View) {
        adapter = GabaritAdapter(mutableListOf(),
            onDeleteClick = { gabarit ->
                présentateur.supprimerGabarit(gabarit)
            },
            onEditClick = { gabarit ->
                afficherBoiteDialogueModification(gabarit)
            }
        )

        view.findViewById<RecyclerView>(R.id.recyclerViewGabarits).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@GabaritFragment.adapter
        }
    }

    private fun afficherBoiteDialogueModification(gabarit: Gabarits) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_gabarit, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val imageViewCamera = dialogView.findViewById<ImageView>(R.id.imageViewCamera)
        imageViewCamera.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
            } else {
                val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)

            }
        }

        // Pré-remplir les champs avec les données actuelles du gabarit
        dialogView.findViewById<EditText>(R.id.editTextNomGabarit).setText(gabarit.nom)
        dialogView.findViewById<EditText>(R.id.editTextCategorieGabarit).setText(gabarit.catégorie)
        dialogView.findViewById<EditText>(R.id.editTextDescriptionGabarit).setText(gabarit.description)

        builder.setPositiveButton("Modifier") { dialog, which ->
            // récupére les nouvelles valeurs et met à jour le gabarit
            val nouveauNom = dialogView.findViewById<EditText>(R.id.editTextNomGabarit).text.toString()
            val nouvelleCategorie = dialogView.findViewById<EditText>(R.id.editTextCategorieGabarit).text.toString()
            val nouvelleDescription = dialogView.findViewById<EditText>(R.id.editTextDescriptionGabarit).text.toString()
            val nouvelleImage = if (imageBase64 != "1") imageBase64 else gabarit.image

            val gabaritModifié = Gabarits(gabarit.code, nouveauNom, nouvelleDescription, nouvelleImage, nouvelleCategorie, gabarit.épicerie)

            adapter.modifierGabarit(gabarit, gabaritModifié)
            présentateur.modifierGabarit(gabaritModifié)
        }
        builder.setNegativeButton("Annuler", null)

        val dialog = builder.create()
        dialog.show()
    }

    // Nous assumons que l'utilisateur 2 est connecté et donc les produits ajoutés sont associés à l'épicerie avec l'id 1
    private fun afficherBoiteDialogueAjout() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_gabarit, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val imageViewCamera = dialogView.findViewById<ImageView>(R.id.imageViewCamera)
        imageViewCamera.setOnClickListener {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
            } else {
                val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)

            }
        }

        builder.setPositiveButton("Ajouter") { dialog, _ ->
            val nom = dialogView.findViewById<EditText>(R.id.editTextNomGabarit).text.toString()
            val categorie = dialogView.findViewById<EditText>(R.id.editTextCategorieGabarit).text.toString()
            val description = dialogView.findViewById<EditText>(R.id.editTextDescriptionGabarit).text.toString()
            val code = "1" // n'affecte pas car autoincrement
            val image = imageBase64 // Implémentez fonctionnalité ajout image
            val épicerie = Épicerie("1",null,null,"1","1","1","1") // Implémentez Épicerie du gérant connecté

            val nouveauGabarit = Gabarits(code, nom, description, image, categorie, épicerie)
            adapter.ajouterGabarit(nouveauGabarit)
            présentateur.ajouterGabarit(nouveauGabarit)
        }

        builder.setNegativeButton("Annuler", null)
        val dialog = builder.create()
        dialog.show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST) {
            val selectedImageUri = data?.data
            selectedImageUri?.let {
                val inputStream = requireContext().contentResolver.openInputStream(it)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                val outputStream = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                val byteArray = outputStream.toByteArray()
                imageBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT)
            }
        }
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 1
        private const val PICK_IMAGE_REQUEST = 2
    }


    override fun afficherDonnées(données: List<Gabarits>) {
        adapter.setGabarits(données)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {}

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onResume() {
        super.onResume()
        présentateur.obtenirDonnées()
    }
}
