package com.example.gaspillezero.ui.main.PrésentationGabarits

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gaspillezero.R
import com.example.gaspillezero.ui.main.sourceDeDonnées.Gabarits

class GabaritAdapter(
    private val dataSet: MutableList<Gabarits>,
    private val onDeleteClick: (Gabarits) -> Unit,
    private val onEditClick: (Gabarits) -> Unit
) : RecyclerView.Adapter<GabaritAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nomGabarit: TextView = view.findViewById(R.id.nomGabarit)
        //val codeGabarit: TextView = view.findViewById(R.id.codeGabarit)
        val descriptionGabarit: TextView = view.findViewById(R.id.descriptionGabarit)
        val categorieGabarit: TextView = view.findViewById(R.id.categorieGabarit)
        val imageGabarit: ImageView = view.findViewById(R.id.imageGabarit)
        val deleteButton: Button = view.findViewById(R.id.btnDelete2)
        val editButton: Button = view.findViewById(R.id.btnEdit2)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_gabarit, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val gabarit = dataSet[position]

        viewHolder.nomGabarit.text = gabarit.nom
        //viewHolder.codeGabarit.text = gabarit.code
        viewHolder.descriptionGabarit.text = gabarit.description
        viewHolder.categorieGabarit.text = gabarit.catégorie

        //val imageUri = viewHolder.imageGabarit.context.resources.getIdentifier(gabarit.image, "drawable", viewHolder.imageGabarit.context.packageName)
        //if (imageUri != 0) {
        //    Picasso.get().load(imageUri).into(viewHolder.imageGabarit)
        //} else {
        //    viewHolder.imageGabarit.setImageResource(R.drawable.pomme)
        //}

        if (gabarit.image != null && gabarit.image.isNotEmpty()) {
            try {
                val imageBytes = Base64.decode(gabarit.image, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                viewHolder.imageGabarit.setImageBitmap(bitmap)
            } catch (e: IllegalArgumentException) {
                // Gérer l'exception si la chaîne Base64 n'est pas valide
                viewHolder.imageGabarit.setImageResource(R.drawable.pomme)
            }
        } else {
            viewHolder.imageGabarit.setImageResource(R.drawable.pomme)
        }


        viewHolder.deleteButton.setOnClickListener {
            onDeleteClick(gabarit)
            removeItem(gabarit)
        }
        viewHolder.editButton.setOnClickListener {
            onEditClick(gabarit)
        }
    }

    fun setGabarits(gabarits: List<Gabarits>) {
        dataSet.clear()
        dataSet.addAll(gabarits)
        notifyDataSetChanged()
    }

    fun removeItem(gabarit: Gabarits) {
        val position = dataSet.indexOf(gabarit)
        if (position >= 0) {
            dataSet.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun modifierGabarit(gabarit: Gabarits, nouveauGabarit: Gabarits) {
        val position = dataSet.indexOf(gabarit)
        if (position >= 0) {
            dataSet[position] = nouveauGabarit
            notifyItemChanged(position)
        }
    }

    fun ajouterGabarit(gabarit: Gabarits) {
        dataSet.add(gabarit)
        notifyItemInserted(dataSet.size - 1)
    }

    override fun getItemCount() = dataSet.size
}
