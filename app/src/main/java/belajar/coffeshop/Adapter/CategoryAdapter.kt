package belajar.coffeshop.Adapter

import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import belajar.coffeshop.Model.CategoryModel
import belajar.coffeshop.R
import belajar.coffeshop.databinding.ViewholderCategoryBinding

class CategoryAdapter(val items:MutableList<CategoryModel>):RecyclerView.Adapter<CategoryAdapter.Viewholder>() {
    private lateinit var context:Context
//    -1 yang berarti tidak ada item yang dipilih.
    private var selectedPosition=-1
    private var lastSelectedPosition=-1

//    Menggunakan view binding ViewholderCategoryBinding untuk mengakses elemen dari layout.
    inner class Viewholder(val binding:ViewholderCategoryBinding):RecyclerView.ViewHolder(binding.root)

//    onCreateViewHolder: Dipanggil saat RecyclerView butuh view baru.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryAdapter.Viewholder {
        context=parent.context
        val binding = ViewholderCategoryBinding.inflate(LayoutInflater.from(context),parent, false)
        return Viewholder(binding)
    }
//    Dipanggil saat RecyclerView butuh mengikat data ke ViewHolder pada posisi tertentu.
    override fun onBindViewHolder(holder: CategoryAdapter.Viewholder, position: Int) {
        val item = items[position]
//    Mengisi teks dari TextView (bernama titleCat) dengan nilai title dari objek item./
        holder.binding.titleCat.text = item.title
        holder.binding.root.setOnClickListener{
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if(selectedPosition==position){
            holder.binding.titleCat.setBackgroundResource(R.drawable.orange_bg)
        } else {
            holder.binding.titleCat.setBackgroundResource(R.drawable.editiext_bg)
        }
    }

    override fun getItemCount(): Int = items.size

}