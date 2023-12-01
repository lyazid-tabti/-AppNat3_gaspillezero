package com.example.gaspillezero.ui.main.PrésentationDenrées

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.PrésentationGabarits.*
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits
import com.example.gaspillezero.ui.main.sourceDeDonnées.Épicerie

class GabaritFragment : Fragment(), AdapterView.OnItemSelectedListener, GabaritVue {

    private lateinit var adapter: GabaritAdapter
    private lateinit var présentateur: GabaritPrésentateur

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

        // Pré-remplir les champs avec les données actuelles du gabarit
        dialogView.findViewById<EditText>(R.id.editTextNomGabarit).setText(gabarit.nom)
        dialogView.findViewById<EditText>(R.id.editTextCategorieGabarit).setText(gabarit.catégorie)
        dialogView.findViewById<EditText>(R.id.editTextDescriptionGabarit).setText(gabarit.description)

        builder.setPositiveButton("Modifier") { dialog, which ->
            // récupére les nouvelles valeurs et met à jour le gabarit
            val nouveauNom = dialogView.findViewById<EditText>(R.id.editTextNomGabarit).text.toString()
            val nouvelleCategorie = dialogView.findViewById<EditText>(R.id.editTextCategorieGabarit).text.toString()
            val nouvelleDescription = dialogView.findViewById<EditText>(R.id.editTextDescriptionGabarit).text.toString()

            val gabaritModifié = Gabarits(gabarit.code, nouveauNom, nouvelleDescription, gabarit.image, nouvelleCategorie, gabarit.épicerie)

            adapter.modifierGabarit(gabarit, gabaritModifié)
            présentateur.modifierGabarit(gabaritModifié)
        }
        builder.setNegativeButton("Annuler", null)

        val dialog = builder.create()
        dialog.show()
    }

    private fun afficherBoiteDialogueAjout() {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_gabarit, null)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(dialogView)

        builder.setPositiveButton("Ajouter") { dialog, _ ->
            val nom = dialogView.findViewById<EditText>(R.id.editTextNomGabarit).text.toString()
            val categorie = dialogView.findViewById<EditText>(R.id.editTextCategorieGabarit).text.toString()
            val description = dialogView.findViewById<EditText>(R.id.editTextDescriptionGabarit).text.toString()
            val code = ""
            val image = "pomme"
            val épicerie = Épicerie("",null,null,"","","","")

            val nouveauGabarit = Gabarits(code, nom, description, image, categorie, épicerie)
            adapter.ajouterGabarit(nouveauGabarit)
            présentateur.ajouterGabarit(nouveauGabarit)
        }

        builder.setNegativeButton("Annuler", null)
        val dialog = builder.create()
        dialog.show()
    }


    override fun afficherDonnées(données: List<Gabarits>) {
        adapter.setGabarits(données)
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
