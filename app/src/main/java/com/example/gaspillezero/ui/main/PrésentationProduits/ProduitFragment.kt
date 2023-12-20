package com.example.gaspillezero.ui.main.PrésentationProduits

import android.Manifest
import android.app.Activity
import android.content.Context
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
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.DossierPanier.MyDatabase
import com.example.gaspillezero.ui.main.PrésentationDenrées.DenréesAdapter
import com.example.gaspillezero.ui.main.PrésentationDenrées.GabaritFragment
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritAdapter
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritsModèle
import com.example.gaspillezero.ui.main.PrésentationGabarits.GabaritsPrésenteur
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits
import com.example.gaspillezero.ui.main.sourceDeDonnées.Produits
import com.example.gaspillezero.ui.main.sourceDeDonnées.Épicerie
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

class ProduitFragment : Fragment(), AdapterView.OnItemSelectedListener,ProduitVue {

    private lateinit var adapter: ProduitAdapter
    private lateinit var présentateur: ProduitsPrésentateur
    private var imageBase64: String = "1"


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        présentateur = ProduitPrésentateur(this, ProduitModèle())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gestion_produit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
        présentateur.obtenirDonnées()
        view.findViewById<Button>(R.id.btnAddProduit).setOnClickListener{
            afficherBoiteDialogueAjout()
        }

    }

    private fun setupRecyclerView(view: View) {
        adapter = ProduitAdapter(mutableListOf(),
            onDeleteClick = { produit ->
                présentateur.supprimerProduit(produit)
            },
            onEditClick = { produit ->
                afficherBoiteDialogueModification(produit)
            }
        )

        view.findViewById<RecyclerView>(R.id.recyclerViewProduits).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ProduitFragment.adapter
        }
    }

    private fun afficherBoiteDialogueModification(produits: Produits) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_produit, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        // Pré-remplir les champs avec les données actuelles du gabarit
        dialogView.findViewById<EditText>(R.id.editTextNomProduit).setText(produits.nom)
        dialogView.findViewById<EditText>(R.id.editTextPrixProduit).setText(produits.prix.toString())
        dialogView.findViewById<EditText>(R.id.editTextDateExpProduit).setText(produits.date_exp)
        dialogView.findViewById<EditText>(R.id.editTextQuantiteStockProduit).setText(produits.quantite_stock)
        dialogView.findViewById<EditText>(R.id.editTextDescriptionProduit).setText(produits.description)

        builder.setPositiveButton("Modifier") { dialog, which ->
            // récupére les nouvelles valeurs et met à jour le gabarit
            val nouveauNom = dialogView.findViewById<EditText>(R.id.editTextNomProduit).text.toString()
            val nouvellePrix = dialogView.findViewById<EditText>(R.id.editTextPrixProduit).text.toString()
            val nouvelleDateExp = dialogView.findViewById<EditText>(R.id.editTextDateExpProduit).text.toString()
            val nouvelleQuantiteStock = dialogView.findViewById<EditText>(R.id.editTextQuantiteStockProduit).text.toString()
            val nouvelleDescription = dialogView.findViewById<EditText>(R.id.editTextDescriptionProduit).text.toString()
            val nouvelleImage = if (imageBase64 != "1") imageBase64 else produits.photo_url

            //val nouvelleImage = if (imageBase64 != "1") imageBase64 else gabarit.image

            val produitModifié = Produits(produits.code, nouveauNom,nouvelleDescription,nouvellePrix.toDouble(), nouvelleDateExp, nouvelleQuantiteStock.toInt(),nouvelleImage,produits.épicerie,produits.gabarit)

            adapter.modifierProduits(produits, produitModifié)
            présentateur.modifierProduit(produitModifié)
        }
        builder.setNegativeButton("Annuler", null)

        val dialog = builder.create()
        dialog.show()
    }

    private fun afficherBoiteDialogueAjout() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_produit, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        builder.setPositiveButton("Ajouter") { dialog, _ ->
            val nom = dialogView.findViewById<EditText>(R.id.editTextNomProduit).text.toString()
            val prix = dialogView.findViewById<EditText>(R.id.editTextPrixProduit).text.toString()
            val description = dialogView.findViewById<EditText>(R.id.editTextDescriptionProduit).text.toString()
            val dateExp = dialogView.findViewById<EditText>(R.id.editTextDateExpProduit).text.toString()
            val quantiteStock = dialogView.findViewById<EditText>(R.id.editTextQuantiteStockProduit).text.toString()
            val épicerie = Épicerie("1",null,null,"1","1","1","1") // Implémentez Épicerie du gérant connecté
            val gabarit = Gabarits("1","Poulet","Viande",null,"Viande",épicerie)
            val code = "1" // n'affecte pas car autoincrement

            val nouveauProduits = Produits(code, nom,description,prix.toDouble(),dateExp,quantiteStock.toInt(),gabarit.image,épicerie, gabarit)
            adapter.ajouterProduit(nouveauProduits)
            présentateur.ajouterProduit(nouveauProduits)
        }

        builder.setNegativeButton("Annuler", null)
        val dialog = builder.create()
        dialog.show()
    }

    override fun afficherDonnées(données: List<Produits>) {
        adapter.setProduits(données)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // peut ajouter logique ici
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onResume() {
        super.onResume()
        présentateur.obtenirDonnées()
    }
}
