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
import android.widget.*
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
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ProduitFragment : Fragment(), AdapterView.OnItemSelectedListener,ProduitVue {

    private lateinit var adapter: ProduitAdapter
    private lateinit var présentateur: ProduitsPrésentateur
    private lateinit var listeGabaritsSpinner: List<Gabarits>


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
        présentateur.recevoirDonnéesGabarits()
        view.findViewById<Button>(R.id.btnAddProduit).setOnClickListener{
            afficherBoiteDialogueAjout(listeGabaritsSpinner)
        }

    }

    private fun setupRecyclerView(view: View) {
        adapter = ProduitAdapter(mutableListOf(),
            onDeleteClick = { produit ->
                présentateur.supprimerProduit(produit)
            },
            onEditClick = { produit ->
                afficherBoiteDialogueModification(produit,listeGabaritsSpinner)
            }
        )

        view.findViewById<RecyclerView>(R.id.recyclerViewProduits).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ProduitFragment.adapter
        }
    }

    private fun afficherBoiteDialogueModification(produit: Produits, gabarits: List<Gabarits>) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_produit, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        // Pré-remplir les champs avec les données actuelles du produit
        dialogView.findViewById<EditText>(R.id.editTextNomProduit).setText(produit.nom)
        dialogView.findViewById<EditText>(R.id.editTextPrixProduit).setText(produit.prix.toString())
        dialogView.findViewById<EditText>(R.id.editTextDateExpProduit).setText(produit.date_exp)
        dialogView.findViewById<EditText>(R.id.editTextQuantiteStockProduit).setText(produit.quantite_stock.toString())

        val spinnerGabarit = dialogView.findViewById<Spinner>(R.id.spinnerGabarit)
        val adapterSpinner = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, gabarits)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGabarit.adapter = adapterSpinner

        spinnerGabarit.setSelection(gabarits.indexOfFirst { it.code == produit.gabarit.code })

        builder.setPositiveButton("Modifier") { dialog, which ->
            // récupére les nouvelles valeurs et met à jour le produit
            val nomModifié = dialogView.findViewById<EditText>(R.id.editTextNomProduit).text.toString()
            val prixModifié = dialogView.findViewById<EditText>(R.id.editTextPrixProduit).text.toString()
            val dateExpirationModifié = dialogView.findViewById<EditText>(R.id.editTextDateExpProduit).text.toString()
            val quantitéModifié = dialogView.findViewById<EditText>(R.id.editTextQuantiteStockProduit).text.toString()
            val gabaritModifié = spinnerGabarit.selectedItem as Gabarits

            val produitModifié = Produits(produit.code, nomModifié,produit.gabarit.description,prixModifié.toDouble(), dateExpirationModifié, quantitéModifié.toInt(),produit.gabarit.image,produit.épicerie,gabaritModifié)

            adapter.modifierProduits(produit, produitModifié)
            présentateur.modifierProduit(produitModifié)
        }
        builder.setNegativeButton("Annuler", null)

        val dialog = builder.create()
        dialog.show()
    }

    private fun afficherBoiteDialogueAjout(gabarits: List<Gabarits>) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_produit, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        val spinnerGabarit = dialogView.findViewById<Spinner>(R.id.spinnerGabarit)
        val adapterSpinner = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, gabarits)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGabarit.adapter = adapterSpinner

        spinnerGabarit.setSelection(gabarits.indexOfFirst { it.code == gabarits[0].code })

        builder.setPositiveButton("Ajouter") { dialog, _ ->
            val nom = dialogView.findViewById<EditText>(R.id.editTextNomProduit).text.toString()
            val prix = dialogView.findViewById<EditText>(R.id.editTextPrixProduit).text.toString()
            val dateExpirationModifié = dialogView.findViewById<EditText>(R.id.editTextDateExpProduit).text.toString()
            val quantiteStock = dialogView.findViewById<EditText>(R.id.editTextQuantiteStockProduit).text.toString()
            val épicerie = Épicerie("1",null,null,"1","1","1","1") // Implémentez Épicerie du gérant connecté
            val gabarit = spinnerGabarit.selectedItem as Gabarits
            val code = "1" // n'affecte pas car autoincrement

            val nouveauProduits = Produits(code, nom,gabarit.description,prix.toDouble(),dateExpirationModifié,quantiteStock.toInt(),gabarit.image,épicerie, gabarit)
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
    override fun recevoirDonnéesGabarits(donnéesGabarits: List<Gabarits>) {
        //pour afficher les gabarits dans le spinner de la boite de dialogue Ajout et Modification
        listeGabaritsSpinner = donnéesGabarits
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
