package com.abstudio.recipes.categories_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.abstudio.mytodo.fragments.list.adapter.CategoriesDiffUtil
import com.abstudio.recipes.R
import com.abstudio.recipes.data.entities.Category

class CategoriesListAdapter: RecyclerView.Adapter<CategoriesListAdapter.MyViewHolder>() {

    var dataList = emptyList<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_row_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: CategoriesListAdapter.MyViewHolder, position: Int) {
        //val category_photo_img = holder.itemView.findViewById<ImageView>(R.id.category_photo_img)
        val category_name_txt = holder.itemView.findViewById<TextView>(R.id.category_name_txt)
        val recipes_amount_txt = holder.itemView.findViewById<TextView>(R.id.recipes_amount_txt)

        //category_photo_img
        category_name_txt.text = dataList[position].name
        recipes_amount_txt.text = "Всего рецептов: "
    }

    fun setData(categoriesList: List<Category>){
        val toDoDiffUtil = CategoriesDiffUtil(dataList, categoriesList)
        val toDoDiffResult = DiffUtil.calculateDiff(toDoDiffUtil)
        this.dataList = categoriesList
        toDoDiffResult.dispatchUpdatesTo(this)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}